package {{systemName|lower}}.ev.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;


import {{systemName|lower}}.ev.data.Event;

public class EventTableModel extends AbstractTableModel{

	// Nome das Colunas
	
		private static final int COL_EVENTID = 0;
		private static final int COL_NAME = 1;
		private static final int COL_PERIOD = 2;
		private static final int COL_PLACE = 3;
		private static final int COL_INSTITUTION = 4;
		private static final int COL_SPONSORS = 5;
		{% for property in data.option.properties %}
		private static final int COL_{{property.name|upper}} ={{loop.index+5}};
		{% endfor %}
		{% if data.option.categories|length > 0 %}
		private static final int COL_TYPE{{data.option.entity|upper}} = {{6 + data.option.properties|length}};
		{% endif %}
		
		// Lista de Valores
		private List<Event> rows;
		
		public EventTableModel(List<Event> values){
			this.rows = values;
		}
		
		public int getRowCount() {
			return rows.size();
		}
		
		//Quantidade de Colunas
		public int getColumnCount() {
			return {{5 + data.option.properties|length + (data.option.properties is defined)}};
		}
		
		//Preenchimento de cada coluna
			public Object getValueAt(int rowIndex, int columnIndex) {
				Event event = rows.get(rowIndex);
				if (columnIndex == COL_EVENTID) {
					return event.getIdEvent();
				} else if (columnIndex == COL_NAME) {
					return event.getEventName();
				} else if (columnIndex == COL_PERIOD) {
					return event.getPeriod();
				} else if (columnIndex == COL_PLACE) {
					return event.getPlace();
				} else if (columnIndex == COL_INSTITUTION) {
					return event.getInstitution();
				} else if (columnIndex == COL_SPONSORS) {
					return event.getSponsors();
				}
				{% for property in data.option.properties %}
				else if (columnIndex == COL_{{property.name|upper}}) {
					return event.get{{property.name|capitalize}}();
				}
				{% endfor %}
				{% if data.option.categories|length > 0 %}
				else if (columnIndex == COL_TYPE{{data.option.entity|upper}}) {
					return event.getType{{data.option.entity}}();
				}
				{% endif %}
				return null;
			}
			
			@Override
			public String getColumnName(int column) {
				String coluna = "";
				switch (column) {
				case COL_EVENTID:
					coluna = "Event Id";
					break;
				case COL_NAME:
					coluna = "Name";
					break;
				case COL_PERIOD:
					coluna = "Period";
					break;
				case COL_PLACE:
					coluna = "Place";
					break;
				case COL_INSTITUTION:
					coluna = "Institution";
					break;
				case COL_SPONSORS:
					coluna = "Sponsors";
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
				if (columnIndex == COL_EVENTID) {
					return int.class;
				} else if (columnIndex == COL_NAME) {
					return String.class;
				} else if (columnIndex == COL_PERIOD) {
					return String.class;
				} else if (columnIndex == COL_PLACE) {
					return String.class;
				} else if (columnIndex == COL_INSTITUTION) {
					return String.class;
				} else if (columnIndex == COL_SPONSORS) {
					return String.class;
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
			public Event get(int row) {
				return rows.get(row);
			}

			public void addEvent(Event event) {
				rows.add(event);
				int ultimoIndice = getRowCount() - 1;
				fireTableRowsInserted(ultimoIndice, ultimoIndice);
			}
			
			public void removeEvent(int indiceLinha) {
				rows.remove(indiceLinha);
				fireTableRowsDeleted(indiceLinha, indiceLinha);
			}
			
			public void alterarEvent(int indiceLinha, Event event) {
				rows.get(indiceLinha).setEventName(event.getEventName());
				rows.get(indiceLinha).setPeriod(event.getPeriod());
				rows.get(indiceLinha).setPlace(event.getPlace());
				rows.get(indiceLinha).setInstitution(event.getInstitution());
				rows.get(indiceLinha).setSponsors(event.getSponsors());		
				{% for property in data.option.properties %}
				rows.get(indiceLinha).set{{property.name|capitalize}}(activity.get{{property.name|capitalize}}());
				{% endfor %}
				{% if data.option.categories|length > 0 %}
				rows.get(indiceLinha).setType{{data.option.entity}}(activity.getType{{data.option.entity}}());
				{% endif %}
			
				fireTableDataChanged();
			}
			
			public void addListaDeEventos(List<Event> events) {
				int indice = getRowCount();
				rows.addAll(events);
				fireTableRowsInserted(indice, indice + events.size());
			}
			
			public int retornarIndice(Event event) {
				return rows.indexOf(event);
			}
			
			// Remove todos os registros.
			public void limpar() {
				rows.clear();

				// Notifica a mudança.
				fireTableDataChanged();
			}
}
