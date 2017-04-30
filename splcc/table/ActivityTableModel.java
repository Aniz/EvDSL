//#if ${ActivityMinicurso} == "T" or ${ActivityTutorial} == "T" or ${ActivityPainel} == "T" or ${ActivityWorkshop} == "T" or ${ActivityMainTrack} == "T"
package {{systemName|lower}}.ev.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import {{systemName|lower}}.ev.data.Activity;

public class ActivityTableModel extends AbstractTableModel{

	// Nome das Colunas
	
		private static final int COL_ACTIVITYID = 0;
		private static final int COL_IDEVENT = 1;
		private static final int COL_NAMEACTIVITY = 2;
		private static final int COL_DESCRIPTIONACTIVITY = 3;
		private static final int COL_VALUE = 4;
		private static final int COL_HOURLYLOAD =5;
		private static final int COL_DATE =6;
		private static final int COL_HOUR = 7;
		private static final int COL_NUMBEROFPARTICIPANTS =8;
		private static final int COL_REGISTRATIONLIMIT =9;
		{% for property in data.option.properties %}
		private static final int COL_{{property.name|upper}} ={{loop.index+9}};
		{% endfor %}
		{% if data.option.categories|length > 0 %}
		private static final int COL_TYPE{{data.option.entity|upper}} = {{10 + data.option.properties|length}};
		{% endif %}
		// Lista de Valores
		private List<Activity> rows;
		
		public ActivityTableModel(List<Activity> values){
			this.rows = values;
		}
		
		public int getRowCount() {
			return rows.size();
		}
		
		//Quantidade de Colunas
		public int getColumnCount() {
			return {{9 + data.option.properties|length + (data.option.properties is defined)}};
		}
		
		//Preenchimento de cada coluna
			public Object getValueAt(int rowIndex, int columnIndex) {
				Activity activity = rows.get(rowIndex);
				if (columnIndex == COL_ACTIVITYID) {
					return activity.getIdActivity();
				}  else if (columnIndex == COL_IDEVENT) {
					return activity.getIdEvent();
				}  else if (columnIndex == COL_NAMEACTIVITY) {
					return activity.getNameActivity();
				} else if (columnIndex == COL_DESCRIPTIONACTIVITY) {
					return activity.getDescriptionActivity();
				} else if (columnIndex == COL_VALUE) {
					return activity.getValue();
				} else if (columnIndex == COL_HOURLYLOAD) {
					return activity.getHourlyLoad();
				} else if (columnIndex == COL_DATE) {
					return activity.getDate();
				} else if (columnIndex == COL_HOUR) {
					return activity.getHour();
				} else if (columnIndex == COL_NUMBEROFPARTICIPANTS) {
					return activity.getNumberOfParticipants();
				} else if (columnIndex == COL_REGISTRATIONLIMIT) {
					return activity.getRegistrationLimit();
				}
				{% for property in data.option.properties %}
				else if (columnIndex == COL_{{property.name|upper}}) {
					return activity.get{{property.name|capitalize}}();
				}
				{% endfor %}
				{% if data.option.categories|length > 0 %}
				else if (columnIndex == COL_TYPE{{data.option.entity|upper}}) {
					return activity.getType{{data.option.entity}}();
				}
				{% endif %}
				return null;
			}
			
			@Override
			public String getColumnName(int column) {
				String coluna = "";
				switch (column) {
				case COL_ACTIVITYID:
					coluna = "Activity Id";
					break;
				case COL_IDEVENT:
					coluna = "IDEvent";
					break;
				case COL_NAMEACTIVITY:
					coluna = "Name";
					break;
				case COL_DESCRIPTIONACTIVITY:
					coluna = "Description";
					break;
				case COL_VALUE:
					coluna = "Value";
					break;
				case COL_HOURLYLOAD:
					coluna = "Hourly Load";
					break;
				case COL_DATE:
					coluna = "Date";
					break;
				case COL_HOUR:
					coluna = "Hour";
					break;
				case COL_NUMBEROFPARTICIPANTS:
					coluna = "N of Participants";
					break;
				case COL_REGISTRATIONLIMIT:
					coluna = "REgistration Limit";
					break;
				{% for property in data.option.properties %}
				case COL_{{property.name|upper}}:
					coluna = "{{property.alias}}";
					break;
				{% endfor %}
				{% if data.option.categories|length > 0 %}
				case COL_TYPE{{data.option.entity|upper}}:
					coluna = "Tipo";
					break;
				{% endif %}
				default:
					throw new IllegalArgumentException("Coluna Invalida!");
				}
				return coluna;
			}
			
			//Tipo de valor da colna
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if (columnIndex == COL_ACTIVITYID) {
					return int.class;
				} else if (columnIndex == COL_IDEVENT) {
					return int.class;
				} else if (columnIndex == COL_NAMEACTIVITY) {
					return String.class;
				} else if (columnIndex == COL_DESCRIPTIONACTIVITY) {
					return String.class;
				} else if (columnIndex == COL_ACTIVITYTYPE) {
					return String.class;
				}  else if (columnIndex == COL_VALUE) {
					return float.class;
				} else if (columnIndex == COL_HOURLYLOAD) {
					return float.class;
				} else if (columnIndex == COL_DATE) {
					return String.class;
				} else if (columnIndex == COL_HOUR) {
					return String.class;
				} else if (columnIndex == COL_NUMBEROFPARTICIPANTS) {
					return int.class;
				} else if (columnIndex == COL_REGISTRATIONLIMIT) {
					return int.class;
				}
				{% for property in data.option.properties %}
				else if (columnIndex == COL_{{property.name|upper}}) {
					return {{property.type|javatype}}.class;
				}
				{% endfor %}
				{% if data.option.categories|length > 0 %}
				else if (columnIndex == COL_TYPE{{data.option.entity|upper}}) {
					return String.class;
				}
				{% endif %}
				return null;
			}
			
			//Abaixo metodos de InserÁ„o, remoÁ„o, update e etc;
			public Activity get(int row) {
				return rows.get(row);
			}

			public void addActivity(Activity activity) {
				rows.add(activity);
				int ultimoIndice = getRowCount() - 1;
				fireTableRowsInserted(ultimoIndice, ultimoIndice);
			}
			
			public void removeActivity(int indiceLinha) {
				rows.remove(indiceLinha);
				fireTableRowsDeleted(indiceLinha, indiceLinha);
			}
			
			public void alterarActivity(int indiceLinha, Activity activity) {
				rows.get(indiceLinha).setIdActivity(activity.getIdActivity());
				rows.get(indiceLinha).setNameActivity(activity.getNameActivity());
				rows.get(indiceLinha).setDescriptionActivity(activity.getDescriptionActivity());
				rows.get(indiceLinha).setTypeActivity(activity.getTypeActivity());
				rows.get(indiceLinha).setValue(activity.getValue());
				rows.get(indiceLinha).setHourlyLoad(activity.getHourlyLoad());
				rows.get(indiceLinha).setDate(activity.getDate());
				rows.get(indiceLinha).setHour(activity.getHour());
				rows.get(indiceLinha).setNumberOfParticipants(activity.getNumberOfParticipants());
				rows.get(indiceLinha).setRegistrationLimit(activity.getRegistrationLimit());
				{% for property in data.option.properties %}
				rows.get(indiceLinha).set{{property.name|capitalize}}(activity.get{{property.name|capitalize}}());
				{% endfor %}
				{% if data.option.categories|length > 0 %}
				rows.get(indiceLinha).setType{{data.option.entity}}(activity.getType{{data.option.entity}}());
				{% endif %}
			
				fireTableDataChanged();
			}
			
			public void addListaDeActivities(List<Activity> activities) {
				int indice = getRowCount();
				rows.addAll(activities);
				fireTableRowsInserted(indice, indice + activities.size());
			}
			
			public int retornarIndice(Activity activity) {
				return rows.indexOf(activity);
			}
			
			// Remove todos os registros.
			public void limpar() {
				rows.clear();

				// Notifica a mudança.
				fireTableDataChanged();
			}
}
//#endif