package generator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;

import annotations.BoolColumn;
import annotations.DateColumn;
import annotations.IntColumn;
import annotations.LongColumn;
import annotations.Table;
import annotations.VarcharColumn;

public class CreateEntityViewGenerator {

	private static Table table;

	private static ArrayList<VarcharColumn> varcharList;
	private static ArrayList<LongColumn> longList;
	private static ArrayList<IntColumn> intList;
	private static ArrayList<DateColumn> dateList;
	private static ArrayList<BoolColumn> boolList;

	private static String fields;
	private static String labels;

	private static String instacies;
	private static String fileString;
	private static String clearActions;
	private static String entityProperties;
	private static String loadFields;


	public static void generate(Class<?> T) {
		prepareFields(T);
		generateViewProperties();

		try {
			InputStream inputStream = CreateEntityViewGenerator.class.getResourceAsStream("createViewModel");
			fileString = IOUtils.toString(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		fileString = fileString.replace("<Labels>", labels);
		fileString = fileString.replace("<Fields>", fields);
		fileString = fileString.replace("<Instancies>", instacies);
		fileString = fileString.replace("<ClearActions>", clearActions);
		fileString = fileString.replace("<EntityName>", table.name());
		fileString = fileString.replace("<SetEntity>", entityProperties);
		fileString = fileString.replace("<LoadField>", loadFields);
		
		File file = new File("src/view");
		file.mkdir();
		try{
			file = new File("src/view/"+T.getName().split("model.")[1]+"CreateForm.java");
			FileOutputStream fop = new FileOutputStream(file);
			file.createNewFile();
			byte[] contentInBytes = fileString.getBytes();
			fop.write(contentInBytes);
			fop.flush();
			fop.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	private static void prepareFields(Class<?> T) {

		table = AnnotationGetter.getTableAnnotation(T);

		varcharList = AnnotationGetter.getVarcharColumnAnnotations(T);
		longList = AnnotationGetter.getLongColumnAnnotations(T);
		intList = AnnotationGetter.getIntColumnAnnotations(T);
		dateList = AnnotationGetter.getDateColumnAnnotations(T);
		boolList = AnnotationGetter.getBoolColumnAnnotations(T);

		labels = new String();
		fields = new String();

		instacies = new String();
		fileString = new String();
		clearActions = new String();
		entityProperties = new String();
		loadFields = new String();

	}


	private static void generatePropertiesStrings(String type, String name, String annotation){

		String labelName = name + "Label";

		String fieldName = name + "Field";

		labels = labels + "private JLabel " + labelName +";\n";
		fields = fields + "private " + type + " " + fieldName + ";\n";

		String fieldInstacies = new String();

		if (type.equals("JCheckBox")) {

			fieldInstacies = fieldName  + " = new " + type + "(\"\");\n contentPane.add(" + fieldName + ");\n\n";

			loadFields = loadFields + fieldName + ".setSelected(entity.get" + name.substring(0, 1).toUpperCase() + name.substring(1) + "());\n";
			clearActions = clearActions + fieldName + ".setSelected(false);\n";
			entityProperties = entityProperties + "entity.set" + name.substring(0, 1).toUpperCase() + name.substring(1) + "(" + fieldName + ".isSelected());\n";

		}

		else if (type.equals("JFormattedTextField")) {

			String mask = "try {  \n " + fieldName + ".setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter(\"##-##-####\")));  \n } catch (ParseException e) { \n e.printStackTrace(); \n\n }"; 

			fieldInstacies = fieldName  + " = new " + type + "();\n contentPane.add(" + fieldName + ");\n" + mask + "\n";

			loadFields = loadFields + "DateFormat formatter = new SimpleDateFormat(\"dd-MM-yy\");\n" + fieldName + ".setText(formatter.format(entity.get" + name.substring(0, 1).toUpperCase() + name.substring(1) + "()));\n";

			clearActions = clearActions + fieldName + ".setText(\"\");\n";
			entityProperties = entityProperties + "DateFormat formatter = new SimpleDateFormat(\"dd-MM-yy\");\n Date date = null;\n try{ \n date = (Date)formatter.parse(" + fieldName + ".getText());\n } catch (ParseException e) { \n e.printStackTrace(); } \nentity.set" + name.substring(0, 1).toUpperCase() + name.substring(1) + "(date);\n";

		}
		else{

			fieldInstacies = fieldName  + " = new " + type + "();\n contentPane.add(" + fieldName + ");\n" + fieldName + ".setColumns(10);\n\n";


			String propertyParameter = new String();
			String clearActionParameter = new String();
			String loadFieldsParameter = new String();

			if(annotation.equals("int")){
				propertyParameter = "Integer.parseInt(" + fieldName + ".getText())";
				clearActionParameter = "String.valueOf(\"\")";
				loadFieldsParameter = "String.valueOf(entity.get" + name.substring(0, 1).toUpperCase() + name.substring(1) + "())";
			}
			else if (annotation.equals("long")){
				propertyParameter = "Long.parseLong(" + fieldName + ".getText())";
				clearActionParameter = "String.valueOf(\"\")";
				loadFieldsParameter = "String.valueOf(entity.get" + name.substring(0, 1).toUpperCase() + name.substring(1) + "())";
			}
			else{
				propertyParameter = fieldName + ".getText()";
				clearActionParameter = "\"\"";
				loadFieldsParameter = "entity.get" + name.substring(0, 1).toUpperCase() + name.substring(1) + "()";
			}

			loadFields = loadFields + fieldName + ".setText(" + loadFieldsParameter + ");\n";
			clearActions = clearActions + fieldName + ".setText(" + clearActionParameter + ");\n";
			entityProperties = entityProperties + "entity.set" + name.substring(0, 1).toUpperCase() + name.substring(1) + "(" + propertyParameter + ");\n";
		}

		String labelsInstacies = labelName + " = new JLabel(\""  +name + "\");\n contentPane.add(" + labelName + ");\n\n";


		instacies = instacies + labelsInstacies + fieldInstacies;


	}


	private static void generateViewProperties(){

		for (VarcharColumn var : varcharList) {
			if (!var.name().equals("id")) {
				generatePropertiesStrings("JTextField", var.name(), "varchar");
			}

		}
		for (LongColumn var : longList) {
			if (!var.name().equals("id")) {
				generatePropertiesStrings("JTextField", var.name(), "long");
			}

		}
		for (IntColumn var : intList) {
			if (!var.name().equals("id")) {
				generatePropertiesStrings("JTextField", var.name(), "int");
			}

		}
		for (DateColumn var : dateList) {
			if (!var.name().equals("id")) {
				generatePropertiesStrings("JFormattedTextField", var.name(), "Date");
			}

		}

		for (BoolColumn var : boolList) {
			if (!var.name().equals("id")) {
				generatePropertiesStrings("JCheckBox", var.name(), "Bool");
			}

		}
	}

}
