"""
Generate java code from textX model using jinja2
template engine (http://jinja.pocoo.org/docs/dev/)
"""
from os import mkdir
from os.path import exists, dirname, join
import jinja2
import pydot
from event_test import get_event_mm
from pprint import pprint
import shutil
import sys
from textx.metamodel import metamodel_from_file
from textx.export import metamodel_export, model_export

def main(debug=False):

    this_folder = dirname(__file__)
    event_mm = get_event_mm(debug)

    # Build Event model from person.ent file
    event_model = event_mm.model_from_file(join(this_folder, 'event.ev'))

    def javatype(s):
        """
        Maps type names from PrimitiveType to Java.
        """
        return {
                'integer': 'int',
                'string': 'String'
        }.get(s.name, s.name)

    # Create output folders
    srcgen_folder = join(this_folder, 'srcgen')
    if exists(srcgen_folder):
        shutil.rmtree(srcgen_folder)

    srcgen_folder = createFolder(this_folder, 'srcgen')
    
    dotFolder = createFolder(srcgen_folder, 'dot')
    createDotFiles(event_mm,event_model,dotFolder)
    
    entityFolder = createFolder(srcgen_folder, 'data')
    controllerFolder = createFolder(srcgen_folder, 'business')
    exceptionFolder = createFolder(srcgen_folder, 'exception')
    repositoryFolder = createFolder(srcgen_folder, 'repository')
    tableFolder = createFolder(srcgen_folder, 'table')
    facadeFolder = createFolder(srcgen_folder, 'facade')
    viewFolder = createFolder(srcgen_folder, 'ui2')
    
    codeFolder = join(this_folder, 'splcc')
    entityCodeFolder = createFolder(codeFolder, 'data')
    controllerCodeFolder = createFolder(codeFolder, 'business')
    exceptionCodeFolder = createFolder(codeFolder, 'exception')
    repositoryCodeFolder = createFolder(codeFolder, 'repository')
    tableCodeFolder = createFolder(codeFolder, 'table')
    facadeCodeFolder = createFolder(codeFolder, 'facade')
    viewCodeFolder = createFolder(codeFolder, 'ui2')
    
    # Get template folders
    templateFolder = join(this_folder, 'templates')
    #copy files
    copy(join(this_folder,'lib'),join(srcgen_folder,'lib'))
    copy(join(this_folder,'util'),join(srcgen_folder,'util'))

    # Initialize template engine.
    jinja_env = jinja2.Environment(
        loader=jinja2.FileSystemLoader(this_folder),
        trim_blocks=True,
        lstrip_blocks=True)
    
    # Register filter for mapping event type names to Java type names.
    jinja_env.filters['javatype'] = javatype

    componentData = ""
    componentExtraData = ""

    selectedOptionsArray = []
    actionsArray = []
    statmentsArray = []
    commandsArray = []
    commandsOptionArray = []

    componentDict = {}
    statmentDict = {}
    commandsDict = {}
    commandsOptionDict = {}

    avaliableOptions = ["User","Speaker","Organizer","Event","Payment","Reviewer","Activity","Assignment","Submission","Review","Author","Receipt","CheckingCopy"]
    avaliableFunctions = ["Insert","Remove","Update","Search","ListAll","Search","Management"]
    chosenFunctions = []
    
    #Get options from model
    for component in event_model.components:
        if component.__class__.__name__ == 'Action':
            actionsArray.append(component)
        
        elif component.__class__.__name__ == 'Option':
            selectedOptionsArray.append(component.entity)
            componentDict[component.entity] = {"option":component}
            
            #Get commands from model
            commandsArray = []
            if component.command.__class__.__name__ == 'SubCommandInOption':
                componentDict[component.command.entity]["extraCommand"] = component.entity
            if component.command.__class__.__name__ == 'CommandOption':
                for command in component.command.commandsOption:
                    commandsArray.append(command)
            componentDict[component.entity]["commands"] = commandsArray
            
        elif component.__class__.__name__ == 'Statment':
            statmentsArray.append(component)
    
    #Get statments from model
    statmentDict = {}
    for statment in statmentsArray:
        if statment.entity in selectedOptionsArray:
            statmentDict[statment.actionType] = statment
            componentDict[statment.entity]["statments"] = statmentDict    
        else:
            print("[warning] Option '%s' of method '%s' not found" % (statment.entity,statment.actionType)) 
    
    #Unique files template
    sqlTemplate =jinja_env.get_template(join(templateFolder,'sql.template'))
    with open(join(srcgen_folder,
                   "%s.sql" % ("EventsDSL")), 'w') as f:
        f.write(sqlTemplate.render(data=componentDict))

    facadeTemplate =jinja_env.get_template(join(templateFolder,'java.facadeTemplate'))
    with open(join(facadeFolder,
                   "%s.java" % ("RiSEventFacade")), 'w') as f:
        f.write(facadeTemplate.render(data=componentDict))
    
    mainViewTemplate =jinja_env.get_template(join(templateFolder,'java.mainScreenTemplate'))
    with open(join(viewFolder,
                   "%s.java" % ("RiseEventMainScreenP")), 'w') as f:
        f.write(mainViewTemplate.render(data=componentDict))
    sys.exit()
    #Classes Definition
    for key,value in componentDict.items():
        componentData = ""
        componentExtraData = ""
        #Remove Option if the dependence is not avaliable and print a error
        if key == "CheckingCopy":
            if componentDict.get("Activity"):
                componentExtraData = componentDict["Activity"]
            else:
               avaliableOptions.remove("CheckingCopy")
               print("[Dependence Error] 'CheckingCopy' defined whitout Option 'Activity'")
       
        if key in ["Speaker","Organizer","Reviewer"]:
            if componentDict.get("User"):
                componentExtraData = componentDict["User"]
            else:
               avaliableOptions.remove(key)
               print("[Dependence Error] '%s' defined whitout Option 'Activity'"%key)
                 
        if key in avaliableOptions:
            copyCodeFile(entityCodeFolder,entityFolder,key,jinja_env,value,componentExtraData)
            copyCodeFile(controllerCodeFolder,controllerFolder,key+"Control",jinja_env,value,componentExtraData)
            copyCodeFile(repositoryCodeFolder,repositoryFolder,key+"Repository",jinja_env,value,componentExtraData)
            copyCodeFile(repositoryCodeFolder,repositoryFolder,key+"RepositoryBDR",jinja_env,value,componentExtraData)
            copyCodeFile(exceptionCodeFolder,exceptionFolder,key+"AlreadyInsertedException",jinja_env,value,componentExtraData)
            copyCodeFile(exceptionCodeFolder,exceptionFolder,key+"NotFoundException",jinja_env,value,componentExtraData)
            
            if key not in ["Author"]:
                copyCodeFile(tableCodeFolder,tableFolder,key+"TableModel",jinja_env,value,componentExtraData)
            
            if key not in ["Assignment","Author","Receipt","CheckingCopy"]:
                copyCodeFile(tableCodeFolder,tableFolder,key+"TableRender",jinja_env,value,componentExtraData)
        
            for keyCommand,view in enumerate(value["commands"]):
                copyCodeFile(viewCodeFolder,viewFolder,key+view+"ScreenP",jinja_env,value,componentExtraData)
        else :
            print("[Option Error] '%s' not found. Option is undefined or their dependences are missing" % key)

    #DepedentClassses
    dependencesDict = {} 
    dependencesDict["Review"] = "Reviewer","Submission"
    dependencesDict["ActivityUser"] = "User","Activity"
    dependencesDict["ActivitySpeaker"] = "Speaker","Activity"
    dependencesDict["ActivityOrganizer"] = "Organizer","Activity"
    dependencesDict["SubmissionAuthor"] = "Submission","Author"
    dependencesDict["SubmissionUser"] = "Submission","User"
    dependencesDict["Registration"] = "User","Event"
    
    for key,value in dependencesDict.items():
        if all((w in selectedOptionsArray for w in value)):
            copyCodeFile(entityCodeFolder,entityFolder,key,jinja_env,componentData,componentExtraData)
            copyCodeFile(controllerCodeFolder,controllerFolder,key +"Control",jinja_env,componentData,componentExtraData) 
            copyCodeFile(repositoryCodeFolder,repositoryFolder,key+"Repository",jinja_env,componentData,componentExtraData)
            copyCodeFile(repositoryCodeFolder,repositoryFolder,key+"RepositoryBDR",jinja_env,componentData,componentExtraData)
            copyCodeFile(exceptionCodeFolder,exceptionFolder,key+"AlreadyInsertedException",jinja_env,componentData,componentExtraData)
            copyCodeFile(exceptionCodeFolder,exceptionFolder,key+"NotFoundException",jinja_env,componentData,componentExtraData)
        
        if key not in ["SubmissionAuthor","SubmissionUser","Registration"]:
            copyCodeFile(tableCodeFolder,tableFolder,key+"TableModel",jinja_env,componentData,componentExtraData)
            copyCodeFile(tableCodeFolder,tableFolder,key+"TableRender",jinja_env,componentData,componentExtraData)
        
        if key not in ["SubmissionAuthor","SubmissionUser"]:
            copyCodeFile(viewCodeFolder,viewFolder,key+'Management'+"ScreenP",jinja_env,value,componentExtraData)
       
        for keyCommand,view in enumerate(avaliableFunctions):
            keyView = key+view
            if not ((keyView == "ActivityUserInsert") or (key != "ActivitySpeakerRemove") or (key != "ActivityUserRemove")):
                copyCodeFile(viewCodeFolder,viewFolder,keyView+"ScreenP",jinja_env,value,componentExtraData)
        
    for keyStatmentView,valueStatment in enumerate(statmentsArray):
        if (valueStatment.condition == 'def'):
            copyCodeFile(viewCodeFolder,viewFolder,valueStatment.actionType+"ScreenP",jinja_env,value,componentExtraData)
       
def createDotFiles(event_mm,event_model,dotFolder):
    # Export to .dot file for visualization
    metamodel_export(event_mm, join(dotFolder, 'event_meta.dot'))
    # Export to .dot file for visualization
    model_export(event_model, join(dotFolder, 'event.dot'))

    (graph,) = pydot.graph_from_dot_file(join(dotFolder,'event_meta.dot'))
    graph.write_png(join(dotFolder,'event_meta.png'))
    
    (graph,) = pydot.graph_from_dot_file(join(dotFolder,'event.dot'))
    graph.write_png(join(dotFolder,'event.png'))
    
def copyCodeFile(src,dest,nameFile,jinja_env,var,extraVar):
    codeFileTemplate = jinja_env.get_template(join(src,nameFile+'.java'))
    with open(join(dest,
                      "%s.java" % nameFile), 'w') as f:
            f.write(codeFileTemplate.render(data=var,extraData=extraVar))

def createFolder(dest,name):
    folder = join(dest, name)
    if not exists(folder):
        mkdir(folder)

    return folder

def copy(src, dest):
    if exists(dest):
        shutil.rmtree(dest)

    try:
        shutil.copytree(src, dest)
    except OSError as e:
        # If the error was caused because the source wasn't a directory
        if e.errno == errno.ENOTDIR:
            shutil.copy(src, dest)
        else:
            print('Directory not copied. Error: %s' % e)

if __name__ == "__main__":
    main()
