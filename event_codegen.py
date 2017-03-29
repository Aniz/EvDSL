"""
An example how to generate java code from textX model using jinja2
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
    
    entityFolder = createFolder(srcgen_folder, 'data')
    controllerFolder = createFolder(srcgen_folder, 'business')
    exceptionFolder = createFolder(srcgen_folder, 'exception')
    repositoryFolder = createFolder(srcgen_folder, 'repository')
    tableFolder = createFolder(srcgen_folder, 'table')
    facadeFolder = createFolder(srcgen_folder, 'facade')
    viewFolder = createFolder(srcgen_folder, 'ui2')
    dotFolder = createFolder(srcgen_folder, 'dot')

    codeFolder = join(this_folder, 'splcc')
    entityCodeFolder = createFolder(codeFolder, 'data')
    controllerCodeFolder = createFolder(codeFolder, 'business')
    exceptionCodeFolder = createFolder(codeFolder, 'exception')
    repositoryCodeFolder = createFolder(codeFolder, 'repository')
    tableCodeFolder = createFolder(codeFolder, 'table')
    facadeCodeFolder = createFolder(codeFolder, 'facade')
    viewCodeFolder = createFolder(codeFolder, 'ui2')
    dotCodeFolder = createFolder(codeFolder, 'dot')

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

    empty = ""
    selectedOptionsArray = []
    actionsArray = []
    statmentsArray = []
    componentDict = {}
    statmentDict = {}

    for component in event_model.components:
        if component.__class__.__name__ == 'Action':
            actionsArray.append(component)
        
        elif component.__class__.__name__ == 'Option':
            selectedOptionsArray.append(component.entity)
            componentDict[component.entity] = {"option":component}
            
        elif component.__class__.__name__ == 'Statment':
            statmentsArray.append(component)
        
    for key,value in componentDict.items(): 
        statmentDict = {}
        for statment in statmentsArray:
            if key == statment.entity:
                statmentDict[statment.actionType] = statment
        

        componentDict[key]["statments"] = statmentDict    
    
    # sys.exit()  
    avaliableOptions = ["User","Speaker","Organizer","Event","Payment","Reviewer","Activity","Assignment","Submission","Review","CheckingCopy","Author","Receipt"]
    avaliableFunctions = ["Insert","Delete","Update"]
    chosenEntities = []
    chosenFunctions = []
    
    #Classes Definition
    for key,value in componentDict.items():
        if key in avaliableOptions:
            copyCodeFile(entityCodeFolder,entityFolder,key,jinja_env,value)
            copyCodeFile(controllerCodeFolder,controllerFolder,key+"Control",jinja_env,value)
            copyCodeFile(repositoryCodeFolder,repositoryFolder,key+"Repository",jinja_env,value)
            copyCodeFile(repositoryCodeFolder,repositoryFolder,key+"RepositoryBDR",jinja_env,value)
            copyCodeFile(exceptionCodeFolder,exceptionFolder,key+"AlreadyInsertedException",jinja_env,value)
            copyCodeFile(exceptionCodeFolder,exceptionFolder,key+"NotFoundException",jinja_env,value)
            
            if key not in ["Author"]:
                copyCodeFile(tableCodeFolder,tableFolder,key+"TableModel",jinja_env,value)
            
            if key not in ["Assignment","Author","CheckingCopy","Receipt"]:
                copyCodeFile(tableCodeFolder,tableFolder,key+"TableRender",jinja_env,value)
    
            # for view in op.views:
            #     copyCodeFile(viewCodeFolder,viewFolder,key+view+"ScreenP",jinja_env,value)
        else :
            print("Option %s not found" % key)

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
            copyCodeFile(entityCodeFolder,entityFolder,key,jinja_env,empty)
            copyCodeFile(controllerCodeFolder,controllerFolder,key +"Control",jinja_env,empty)    
            copyCodeFile(repositoryCodeFolder,repositoryFolder,key+"Repository",jinja_env,empty)
            copyCodeFile(repositoryCodeFolder,repositoryFolder,key+"RepositoryBDR",jinja_env,empty)
            copyCodeFile(exceptionCodeFolder,exceptionFolder,key+"AlreadyInsertedException",jinja_env,empty)
            copyCodeFile(exceptionCodeFolder,exceptionFolder,key+"NotFoundException",jinja_env,empty)
           
        if key not in ["SubmissionAuthor","SubmissionUser","Registration"]:
            copyCodeFile(tableCodeFolder,tableFolder,key+"TableModel",jinja_env,empty)
            copyCodeFile(tableCodeFolder,tableFolder,key+"TableRender",jinja_env,empty)

    # facadeTemplate =jinja_env.get_template(join(templateFolder,'java.facadeTemplate'))
    # with open(join(facadeFolder,
    #                "%s.java" % ("RiSEventFacade")), 'w') as f:
    #     f.write(facadeTemplate.render(entitiesArray=entitiesArray))

    # dot = pydot.Dot()
    # dot = pydot.graph_from_dot_file('Model_parse_tree.dot')
    # dot.write_png('somefile.png')
 
def copyCodeFile(src,dest,nameFile,jinja_env,var):
    codeFileTemplate = jinja_env.get_template(join(src,nameFile+'.java'))
    with open(join(dest,
                      "%s.java" % nameFile), 'w') as f:
            f.write(codeFileTemplate.render(data=var))

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
