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
import re
from textx.metamodel import metamodel_from_file
from textx.export import metamodel_export, model_export

def main(fileName="event.ev",debug=False):

    this_folder = dirname(__file__)
    event_mm = get_event_mm(debug)

    if sys.argv[1:]:
        fileName = sys.argv[1]
    
    # Build Event model from person.ent file
    event_model = event_mm.model_from_file(join(this_folder, fileName))

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
    systemEmail = event_model.email
    systemPassword = event_model.password
    general_folder = createFolder(this_folder, 'srcgen')
    generally_folder = createFolder(general_folder, 'src')
    general_ev_folder = createFolder(generally_folder, systemName.lower())
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
    jinja_env.filters['upperfirst'] = upperfirst
    jinja_env.filters['splitName'] = splitName

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
    allStatmentsDict = {}

    avaliableOptions = ["User","Speaker","Organizer","Event","Payment","Reviewer","Activity","Submission","Review","Author","Receipt","CheckingCopy","Assignment"]
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
            componentDict[component.entity]["statments"] = {}
            
            #Get commands from model
            commandsArray = []
            if component.command.__class__.__name__ == 'SubCommandInOption':
                componentDict[component.command.entity]["extraCommand"] = component.entity
            if component.command.__class__.__name__ == 'Command':
                for command in component.command.commands:
                    commandsArray.append(command)
            componentDict[component.entity]["commands"] = commandsArray
            
        elif component.__class__.__name__ == 'Statment':
            statmentsArray.append(component)
            allStatmentsDict[component.actionType] = component
    
    #DepedentClassses
    dependencesDict = {} 
    dependencesDict["Review"] = "Reviewer","Submission"
    dependencesDict["ActivityUser"] = "User","Activity"
    dependencesDict["ActivitySpeaker"] = "Speaker","Activity"
    dependencesDict["ActivityOrganizer"] = "Organizer","Activity"
    dependencesDict["SubmissionAuthor"] = "Submission","Author"
    dependencesDict["SubmissionUser"] = "Submission","User"
    dependencesDict["Registration"] = "User","Event"
    # dependencesDict["Assignment"] = "User","Reviewer","Submission","Author"
    
    dependenceDict = {} 
    for depK,depV in dependencesDict.items():
        dependenceDict[depK] = {}
        dependenceDict[depK]["statments"] = {}
       
    avaliableDict = {}
    for key, value in componentDict.items():
        avaliableDict[key] = True
    for key, value in dependencesDict.items():
        avaliableDict[key] = True
    
    #Get statments from model
    for statment in statmentsArray:
        if statment.entity in selectedOptionsArray or statment.entity in avaliableDependencesArray:
            if statment.entity in selectedOptionsArray:
                componentDict[statment.entity]["statments"][statment.actionType] = {}    
                componentDict[statment.entity]["statments"][statment.actionType] = statment    
            elif statment.entity in avaliableDependencesArray:
                dependenceDict[statment.entity]["statments"][statment.actionType] = {}    
                dependenceDict[statment.entity]["statments"][statment.actionType] = statment    
        else:
            print("[warning] Option '%s' of method '%s' not found" % (statment.entity,statment.actionType)) 
    
    #Classes Definition
    for key,value in componentDict.items():
        componentData = ""
        componentExtraData = ""
        #Remove Option if the dependence is not avaliable and print a error
        if key in avaliableOptions:
            if key == "Assignment":
                componentExtraData = {}
                if componentDict.get("Author"):
                        componentExtraData["Author"] = True
                
                componentExtraData["SubmissionUser"] = True
                    
                for n in ["Submission","Reviewer"]:
                    if componentDict.get(n):
                        componentExtraData[n] = True
                    else: 
                        print("[Dependence Error] 'Assignment' defined whitout Option '%s'"%n)
                
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
                copyCodeFile(entityCodeFolder,entityFolder,key,jinja_env,value,componentExtraData,systemName,avaliableDict)
                copyCodeFile(controllerCodeFolder,controllerFolder,key+"Control",jinja_env,value,componentExtraData,systemName,avaliableDict)
                copyCodeFile(repositoryCodeFolder,repositoryFolder,key+"Repository",jinja_env,value,componentExtraData,systemName,avaliableDict)
                copyCodeFile(repositoryCodeFolder,repositoryFolder,key+"RepositoryBDR",jinja_env,value,componentExtraData,systemName,avaliableDict)
                copyCodeFile(exceptionCodeFolder,exceptionFolder,key+"AlreadyInsertedException",jinja_env,value,componentExtraData,systemName,avaliableDict)
                copyCodeFile(exceptionCodeFolder,exceptionFolder,key+"NotFoundException",jinja_env,value,componentExtraData,systemName,avaliableDict)
                
                if key not in ["Author"]:
                    #generateFile(templateFolder,tableFolder,'java.tableTemplate',key+"TableModel",jinja_env,value,componentExtraData,systemName)
                    copyCodeFile(tableCodeFolder,tableFolder,key+"TableModel",jinja_env,value,componentExtraData,systemName,avaliableDict)
                
                if key not in ["Author","Receipt","CheckingCopy","Assignment"]:
                    #generateFile(templateFolder,tableFolder,'java.tableRenderTemplate',key+"TableRender",jinja_env,value,componentExtraData,systemName,avaliableDict)
                    copyCodeFile(tableCodeFolder,tableFolder,key+"TableRender",jinja_env,value,componentExtraData,systemName,avaliableDict)
                
                for keyCommand,view in enumerate(value["commands"]):
                    copyCodeFile(viewCodeFolder,viewFolder,key+view+"ScreenP",jinja_env,value,componentExtraData,systemName,avaliableDict)
                
                if len(value["statments"]) > 0:
                    for keyStatment,viewStatment in value["statments"].items():
                        if (viewStatment.condition == 'def'):
                            if viewStatment.actionType in ["reportsFrequencyPerEvent","reportsFrequencyPerActivity","reportsListOfAuthors"]:
                               copyCodeFile(viewCodeFolder,viewFolder,key+upperfirst(viewStatment.actionType)+"ScreenP",jinja_env,value,componentExtraData,systemName,avaliableDict)
                            else:
                               copyCodeFile(viewCodeFolder,viewFolder,upperfirst(viewStatment.actionType)+"ScreenP",jinja_env,value,componentExtraData,systemName,avaliableDict)
        
        else :
            #print("[Option Error] '%s' not found. Option is undefined or their dependences are missing" % key)
            print("[New] Option '%s' created" % key)
            generateFile(templateFolder,entityFolder,'java.template',key,jinja_env,value,componentExtraData,systemName,avaliableDict)
            generateFile(templateFolder,controllerFolder,'java.controllerTemplate',key+"Control",jinja_env,value,componentExtraData,systemName,avaliableDict)
            generateFile(templateFolder,repositoryFolder,'java.repositoryTemplate',key+"Repository",jinja_env,value,componentExtraData,systemName,avaliableDict)
            generateFile(templateFolder,repositoryFolder,'java.repositoryBDRTemplate',key+"RepositoryBDR",jinja_env,value,componentExtraData,systemName,avaliableDict)
            generateFile(templateFolder,exceptionFolder,'java.exceptionAlreadyInsertedTemplate',key+"AlreadyInsertedException",jinja_env,value,componentExtraData,systemName,avaliableDict)
            generateFile(templateFolder,exceptionFolder,'java.exceptionNotFoundTemplate',key+"NotFoundException",jinja_env,value,componentExtraData,systemName,avaliableDict)
            generateFile(templateFolder,tableFolder,'java.tableTemplate',key+"TableModel",jinja_env,value,componentExtraData,systemName,avaliableDict)
            generateFile(templateFolder,tableFolder,'java.tableRenderTemplate',key+"TableRender",jinja_env,value,componentExtraData,systemName,avaliableDict)
            # generateFile(templateFolder,viewFolder,'java.screenTemplate',key+'Management'+"ScreenP",jinja_env,value,componentExtraData,systemName,avaliableDict)
            for com,comm in enumerate(value["commands"]):
                generateFile(templateFolder,viewFolder,'java.screenTemplate',key+comm+"ScreenP",jinja_env,value,comm,systemName,avaliableDict)
                
    dependencesList = []
    for key,value in dependencesDict.items():
        actionsKeyArray = []
        if all((w in selectedOptionsArray for w in value)):
            componentExtraData = {}
            componentData = ""
            dependencesList.append(key)
            
            if key in dependenceDict:
                componentData = dependenceDict[key]
            
            for op in dependencesDict[key]:
                componentExtraData[op] = componentDict[op]

            if key in ["Review"]:
                componentExtraData = componentDict["Reviewer"]
            
            if key in ["Assignment"]:
                componentExtraData["SubmissionUser"] = True
                componentExtraData["SubmissionAuthor"] = True
                componentExtraData["Review"] = True

            copyCodeFile(entityCodeFolder,entityFolder,key,jinja_env,componentData,componentExtraData,systemName,avaliableDict)
            copyCodeFile(controllerCodeFolder,controllerFolder,key +"Control",jinja_env,componentData,componentExtraData,systemName,avaliableDict) 
            copyCodeFile(repositoryCodeFolder,repositoryFolder,key+"Repository",jinja_env,componentData,componentExtraData,systemName,avaliableDict)
            copyCodeFile(repositoryCodeFolder,repositoryFolder,key+"RepositoryBDR",jinja_env,componentData,componentExtraData,systemName,avaliableDict)
            copyCodeFile(exceptionCodeFolder,exceptionFolder,key+"AlreadyInsertedException",jinja_env,componentData,componentExtraData,systemName,avaliableDict)
            copyCodeFile(exceptionCodeFolder,exceptionFolder,key+"NotFoundException",jinja_env,componentData,componentExtraData,systemName,avaliableDict)

            if key not in ["SubmissionAuthor","SubmissionUser"]:            
                copyCodeFile(tableCodeFolder,tableFolder,key+"TableModel",jinja_env,componentData,componentExtraData,systemName,avaliableDict)
            if key not in ["SubmissionAuthor","SubmissionUser","Registration","Assignment"]:
                copyCodeFile(tableCodeFolder,tableFolder,key+"TableRender",jinja_env,componentData,componentExtraData,systemName,avaliableDict)
            
            for keyCommand,view in enumerate(avaliableFunctions):
                keyView = key+view
                if key not in ["SubmissionAuthor","SubmissionUser"] and keyView not in ["AssignmentUpdate"]:
                    if key in ["ActivityUser","ActivitySpeaker","ActivityOrganizer"]:
                        copyCodeFile(viewCodeFolder,viewFolder,key+"ManagementScreenP",jinja_env,componentData,componentExtraData,systemName,avaliableDict)
                    else:
                        copyCodeFile(viewCodeFolder,viewFolder,keyView+"ScreenP",jinja_env,componentData,componentExtraData,systemName,avaliableDict)
  
    copyCodeFile(exceptionCodeFolder,exceptionFolder,"RepositoryException",jinja_env,"","",systemName,avaliableDict)
    copy(join(this_folder,'lib'),join(general_folder,'lib'))
    copy(join(this_folder,'images'),join(general_folder,'images'))
    propertiesCodeFolder = join(this_folder,'properties')
    propertiesFolder = createFolder(general_folder, 'properties')
    generateFile(propertiesCodeFolder,propertiesFolder,'config.properties',"config",jinja_env,value,"",systemName,avaliableDict,".properties")
    generateCodeRecursively(utilCodeFolder,utilFolder,jinja_env,componentDict,dependencesList,allStatmentsDict,systemName, systemEmail, systemPassword)
    generateFile(templateFolder,general_folder,'xml.buildTemplate',"build",jinja_env,value,"",systemName,avaliableDict,".xml")
    shutil.copy(join(templateFolder,'.project'),general_folder)
    shutil.copy(join(templateFolder,'.classpath'),general_folder)
    
    #Unique files template
    sqlTemplate =jinja_env.get_template(join(templateFolder,'sql.template'))
    with open(join(general_folder,
                   "%s.sql" % (systemName)), 'w') as f:
        f.write(sqlTemplate.render(data=componentDict,systemName=systemName))

    facadeTemplate =jinja_env.get_template(join(templateFolder,'java.facadeTemplate'))
    with open(join(facadeFolder,
                   "%s.java" % (systemName+"Facade")), 'w') as f:
        f.write(facadeTemplate.render(data=componentDict,systemName=systemName,dependences=dependencesList,statments=allStatmentsDict))
    
    mainViewTemplate =jinja_env.get_template(join(templateFolder,'java.mainScreenTemplate'))
    with open(join(viewFolder,
                   "%s.java" % (systemName+"MainScreenP")), 'w') as f:
        f.write(mainViewTemplate.render(data=componentDict,systemName=systemName,dependences=dependencesList,avaliable=avaliableOptions))
    
def upperfirst(x):
    return x[0].upper()+x[1:]

def splitName(fullName):
    fullName = upperfirst(fullName)
    return re.sub('([a-z])([A-Z])',"\g<1> \g<2>",fullName)

def createDotFiles(event_mm,event_model,dotFolder):
    # Export to .dot file for visualization
    metamodel_export(event_mm, join(dotFolder, 'event_meta.dot'))
    # Export to .dot file for visualization
    model_export(event_model, join(dotFolder, 'event.dot'))

    (graph,) = pydot.graph_from_dot_file(join(dotFolder,'event_meta.dot'))
    graph.write_png(join(dotFolder,'event_meta.png'))
    
    (graph,) = pydot.graph_from_dot_file(join(dotFolder,'event.dot'))
    graph.write_png(join(dotFolder,'event.png'))
    
def copyCodeFile(src,dest,nameFile,jinja_env,var,extraVar,systemName,avaliableDict):
    codeFileTemplate = jinja_env.get_template(join(src,nameFile+'.java'))
    with open(join(dest,"%s.java" % nameFile), 'w') as f:
            f.write(codeFileTemplate.render(data=var,extraData=extraVar,systemName=systemName,avaliableDict=avaliableDict))

def generateFile(src,dest,file,nameFile,jinja_env,var,extraVar,systemName,avaliableDict,ext=".java"):
    codeFileTemplate = jinja_env.get_template(join(src,file))
    with open(join(dest,"%s" % nameFile+ext), 'w') as f:
            f.write(codeFileTemplate.render(data=var,extraData=extraVar,systemName=systemName,avaliableDict=avaliableDict))

def createFolder(dest,name):
    folder = join(dest, name)
    if not exists(folder):
        mkdir(folder)

    return folder

def generateCodeRecursively(src, dest,jinja_env,var,extraVar,statments,systemName,systemEmail,systemPass):
    for (dirpath,dirnames,filenames) in walk (src):
        for file in filenames:
            codeFileTemplate = jinja_env.get_template(join(src,file))
            with open(join(dest,file), 'w') as f:
                f.write(codeFileTemplate.render(data=var,extraData=extraVar,statments=statments,systemName=systemName,systemEmail=systemEmail,systemPassword=systemPass))

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
