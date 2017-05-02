"""
Generate java code from textX model using jinja2
template engine (http://jinja.pocoo.org/docs/dev/)
"""
from os import mkdir, walk
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

    systemName = event_model.name
    general_folder = createFolder(this_folder, 'srcgen')
    general_ev_folder = createFolder(general_folder, systemName.lower())
    srcgen_folder = createFolder(general_ev_folder, 'ev')
    
    dotFolder = createFolder(srcgen_folder, 'dot')
    createDotFiles(event_mm,event_model,dotFolder)
    
    entityFolder = createFolder(srcgen_folder, 'data')
    controllerFolder = createFolder(srcgen_folder, 'business')
    exceptionFolder = createFolder(srcgen_folder, 'exception')
    repositoryFolder = createFolder(srcgen_folder, 'repository')
    tableFolder = createFolder(srcgen_folder, 'table')
    facadeFolder = createFolder(srcgen_folder, 'facade')
    viewFolder = createFolder(srcgen_folder, 'ui2')
    utilFolder = createFolder(srcgen_folder,'util')
    
    codeFolder = join(this_folder, 'splcc')
    entityCodeFolder = createFolder(codeFolder, 'data')
    controllerCodeFolder = createFolder(codeFolder, 'business')
    exceptionCodeFolder = createFolder(codeFolder, 'exception')
    repositoryCodeFolder = createFolder(codeFolder, 'repository')
    tableCodeFolder = createFolder(codeFolder, 'table')
    facadeCodeFolder = createFolder(codeFolder, 'facade')
    viewCodeFolder = createFolder(codeFolder, 'ui2')
    utilCodeFolder = createFolder(codeFolder,'util')
    
    # Get template folders
    templateFolder = join(this_folder, 'templates')
    
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
    dependenceDict = {}
    
    avaliableOptions = ["User","Speaker","Organizer","Event","Payment","Reviewer","Activity","Assignment","Submission","Review","Author","Receipt","CheckingCopy"]
    avaliableFunctions = ["Insert","Remove","Update","Search","ListAll","Search","Management"]
    avaliableDependencesArray = ["Review","ActivityUser","ActivitySpeaker","ActivityOrganizer","SubmissionAuthor","SubmissionUser","Registration"]
    chosenFunctions = []
    
    #Get options from model
    for component in event_model.components:
        if component.__class__.__name__ == 'Action':
            actionsArray.append(component.function)
            
        elif component.__class__.__name__ == 'Option' or component.__class__.__name__ == 'NewOption':
            if component.__class__.__name__ == 'NewOption':
                component.new = True
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
        if statment.entity in selectedOptionsArray or statment.entity in avaliableDependencesArray:
            statmentDict = {}
            if statment.entity in selectedOptionsArray:
                statmentDict[statment.actionType] = statment
                componentDict[statment.entity]["statments"] = statmentDict    
            if statment.entity in avaliableDependencesArray:
                statmentDict[statment.actionType] = statment
                dependenceDict[statment.entity] = {}       
                dependenceDict[statment.entity]["statments"] = statmentDict               
        else:
            print("[warning] Option '%s' of method '%s' not found" % (statment.entity,statment.actionType)) 
    
    #Classes Definition
    for key,value in componentDict.items():
        componentData = ""
        componentExtraData = ""
        actionsKeyArray = []
        #Remove Option if the dependence is not avaliable and print a error
        
        if 'InterestConflict' in actionsArray:
            #dependencesDict["Conflict"] = "Assigment","Author"
            if("Assigment" in selectedOptionsArray and "Author" in selectedOptionsArray):
                actionsArray.append("InterestConflict")
                value.actions = actionsKeyArray
                 
        if key in avaliableOptions:
        
            if key == "CheckingCopy":
                if componentDict.get("Activity"):
                    componentExtraData = componentDict["Activity"]
                else:
                   avaliableOptions.remove("CheckingCopy")
                   print("[Dependence Error] 'CheckingCopy' defined whitout Option 'Activity'")
           
            if key == "Receipt":
                if componentDict.get("Payment"):
                    componentExtraData = componentDict["Payment"]
                else:
                   avaliableOptions.remove("Receipt")
                   print("[Dependence Error] '%s' defined whitout Option 'Payment'"%'Receipt')

            if key in ["Speaker","Organizer","Reviewer"]:
                if componentDict.get("User"):
                    componentExtraData = componentDict["User"]
                else:
                   avaliableOptions.remove(key)
                   print("[Dependence Error] '%s' defined whitout Option 'Activity'"%key)
            if key in avaliableOptions:
                copyCodeFile(entityCodeFolder,entityFolder,key,jinja_env,value,componentExtraData,systemName)
                copyCodeFile(controllerCodeFolder,controllerFolder,key+"Control",jinja_env,value,componentExtraData,systemName)
                copyCodeFile(repositoryCodeFolder,repositoryFolder,key+"Repository",jinja_env,value,componentExtraData,systemName)
                copyCodeFile(repositoryCodeFolder,repositoryFolder,key+"RepositoryBDR",jinja_env,value,componentExtraData,systemName)
                copyCodeFile(exceptionCodeFolder,exceptionFolder,key+"AlreadyInsertedException",jinja_env,value,componentExtraData,systemName)
                copyCodeFile(exceptionCodeFolder,exceptionFolder,key+"NotFoundException",jinja_env,value,componentExtraData,systemName)
                
                if key not in ["Author"]:
                    #generateFile(templateFolder,tableFolder,'java.tableTemplate',key+"TableModel",jinja_env,value,componentExtraData,systemName)
                    copyCodeFile(tableCodeFolder,tableFolder,key+"TableModel",jinja_env,value,componentExtraData,systemName)
                
                if key not in ["Assignment","Author","Receipt","CheckingCopy"]:
                    #generateFile(templateFolder,tableFolder,'java.tableRenderTemplate',key+"TableRender",jinja_env,value,componentExtraData,systemName)
                    copyCodeFile(tableCodeFolder,tableFolder,key+"TableRender",jinja_env,value,componentExtraData,systemName)
            
                for keyCommand,view in enumerate(value["commands"]):
                    copyCodeFile(viewCodeFolder,viewFolder,key+view+"ScreenP",jinja_env,value,componentExtraData,systemName)
        else :
            #print("[Option Error] '%s' not found. Option is undefined or their dependences are missing" % key)
            print("[New] Option '%s' created" % key)
            generateFile(templateFolder,entityFolder,'java.template',key,jinja_env,value,componentExtraData,systemName)
            generateFile(templateFolder,controllerFolder,'java.controllerTemplate',key+"Control",jinja_env,value,componentExtraData,systemName)
            generateFile(templateFolder,repositoryFolder,'java.repositoryTemplate',key+"Repository",jinja_env,value,componentExtraData,systemName)
            generateFile(templateFolder,repositoryFolder,'java.repositoryBDRTemplate',key+"RepositoryBDR",jinja_env,value,componentExtraData,systemName)
            generateFile(templateFolder,exceptionFolder,'java.exceptionAlreadyInsertedTemplate',key+"AlreadyInsertedException",jinja_env,value,componentExtraData,systemName)
            generateFile(templateFolder,exceptionFolder,'java.exceptionNotFoundTemplate',key+"NotFoundException",jinja_env,value,componentExtraData,systemName)
            generateFile(templateFolder,tableFolder,'java.tableTemplate',key+"TableModel",jinja_env,value,componentExtraData,systemName)
            generateFile(templateFolder,tableFolder,'java.tableRenderTemplate',key+"TableRender",jinja_env,value,componentExtraData,systemName)
            generateFile(templateFolder,viewFolder,'java.screenTemplate',key+'Managment'+"ScreenP",jinja_env,value,componentExtraData,systemName)

    #DepedentClassses
    dependencesDict = {} 
    dependencesDict["Review"] = "Reviewer","Submission"
    dependencesDict["ActivityUser"] = "User","Activity"
    dependencesDict["ActivitySpeaker"] = "Speaker","Activity"
    dependencesDict["ActivityOrganizer"] = "Organizer","Activity"
    dependencesDict["SubmissionAuthor"] = "Submission","Author"
    dependencesDict["SubmissionUser"] = "Submission","User"
    dependencesDict["Registration"] = "User","Event"
                
    dependencesList = []
    for key,value in dependencesDict.items():
        if all((w in selectedOptionsArray for w in value)):
            componentExtraData = ""
            componentData = ""
            dependencesList.append(key)
            
            if key in dependenceDict:
                componentData = dependenceDict[key]
                
            copyCodeFile(entityCodeFolder,entityFolder,key,jinja_env,componentData,componentExtraData,systemName)
            copyCodeFile(controllerCodeFolder,controllerFolder,key +"Control",jinja_env,componentData,componentExtraData,systemName) 
            copyCodeFile(repositoryCodeFolder,repositoryFolder,key+"Repository",jinja_env,componentData,componentExtraData,systemName)
            copyCodeFile(repositoryCodeFolder,repositoryFolder,key+"RepositoryBDR",jinja_env,componentData,componentExtraData,systemName)
            copyCodeFile(exceptionCodeFolder,exceptionFolder,key+"AlreadyInsertedException",jinja_env,componentData,componentExtraData,systemName)
            copyCodeFile(exceptionCodeFolder,exceptionFolder,key+"NotFoundException",jinja_env,componentData,componentExtraData,systemName)

            if key not in ["SubmissionAuthor","SubmissionUser"]:            
                copyCodeFile(tableCodeFolder,tableFolder,key+"TableModel",jinja_env,componentData,componentExtraData,systemName)
            if key not in ["SubmissionAuthor","SubmissionUser","Registration"]:
                copyCodeFile(tableCodeFolder,tableFolder,key+"TableRender",jinja_env,componentData,componentExtraData,systemName)
            
            if key not in ["SubmissionAuthor","SubmissionUser"]:
                copyCodeFile(viewCodeFolder,viewFolder,key+'Management'+"ScreenP",jinja_env,value,componentExtraData,systemName)
           
            for keyCommand,view in enumerate(avaliableFunctions):
                keyView = key+view
                if not ((keyView == "ActivityUserInsert") or (key != "ActivitySpeakerRemove") or (key != "ActivityUserRemove")):
                    copyCodeFile(viewCodeFolder,viewFolder,keyView+"ScreenP",jinja_env,value,componentExtraData,systemName)
            
    for keyStatmentView,valueStatment in enumerate(statmentsArray):
        if (valueStatment.condition == 'def'):
            copyCodeFile(viewCodeFolder,viewFolder,key+valueStatment.actionType+"ScreenP",jinja_env,value,componentExtraData,systemName)
       
    copyCodeFile(exceptionCodeFolder,exceptionFolder,"RepositoryException",jinja_env,"","",systemName)
    copy(join(this_folder,'lib'),join(srcgen_folder,'lib'))
    generateCodeRecursively(utilCodeFolder,utilFolder,jinja_env,componentDict,"",systemName)
        
    #Unique files template
    sqlTemplate =jinja_env.get_template(join(templateFolder,'sql.template'))
    with open(join(srcgen_folder,
                   "%s.sql" % ("EventsDSL")), 'w') as f:
        f.write(sqlTemplate.render(data=componentDict,systemName=systemName))

    facadeTemplate =jinja_env.get_template(join(templateFolder,'java.facadeTemplate'))
    with open(join(facadeFolder,
                   "%s.java" % (systemName+"Facade")), 'w') as f:
        f.write(facadeTemplate.render(data=componentDict,systemName=systemName,dependences=dependencesList))
    
    mainViewTemplate =jinja_env.get_template(join(templateFolder,'java.mainScreenTemplate'))
    with open(join(viewFolder,
                   "%s.java" % (systemName+"MainScreenP")), 'w') as f:
        f.write(mainViewTemplate.render(data=componentDict,systemName=systemName))
    
    
def createDotFiles(event_mm,event_model,dotFolder):
    # Export to .dot file for visualization
    metamodel_export(event_mm, join(dotFolder, 'event_meta.dot'))
    # Export to .dot file for visualization
    model_export(event_model, join(dotFolder, 'event.dot'))

    (graph,) = pydot.graph_from_dot_file(join(dotFolder,'event_meta.dot'))
    graph.write_png(join(dotFolder,'event_meta.png'))
    
    (graph,) = pydot.graph_from_dot_file(join(dotFolder,'event.dot'))
    graph.write_png(join(dotFolder,'event.png'))
    
def copyCodeFile(src,dest,nameFile,jinja_env,var,extraVar,systemName):
    codeFileTemplate = jinja_env.get_template(join(src,nameFile+'.java'))
    with open(join(dest,"%s.java" % nameFile), 'w') as f:
            f.write(codeFileTemplate.render(data=var,extraData=extraVar,systemName=systemName))

def generateFile(src,dest,file,nameFile,jinja_env,var,extraVar,systemName):
    codeFileTemplate = jinja_env.get_template(join(src,file))
    with open(join(dest,"%s.java" % nameFile), 'w') as f:
            f.write(codeFileTemplate.render(data=var,extraData=extraVar,systemName=systemName))

def createFolder(dest,name):
    folder = join(dest, name)
    if not exists(folder):
        mkdir(folder)

    return folder

def generateCodeRecursively(src, dest,jinja_env,var,extraVar,systemName):
    for (dirpath,dirnames,filenames) in walk (src):
        for file in filenames:
            codeFileTemplate = jinja_env.get_template(join(src,file))
            with open(join(dest,file), 'w') as f:
                f.write(codeFileTemplate.render(data=var,extraData=extraVar,systemName=systemName))

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
