//#if ${Organizer} == "T"

package {{systemName|lower}}.ev.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import {{systemName|lower}}.ev.data.Organizer;

public class OrganizerTableModel extends AbstractTableModel{

	// Nome das Colunas
	
		private static final int COL_USERID = 0;
		//private static final int COL_PASSWORD = 1;
		private static final int COL_NAMEUSER = 1;
		private static final int COL_EMAIL = 2;
		{% if data.option.properties|length > 0 %}
        {% for property in data.option.properties %}
		private static final int COL_{{property.name|upper}} ={{loop.index + 2}};
		{% endfor %}
		{% endif %}
		{% if data.option.categories|length > 0 %}
		private static final int COL_TYPE{{data.option.entity|upper}} = {{3 + data.option.properties|length}};
		{% endif %}
		
		{% if extraData.option.properties|length > 0 %}
        {% for property in extraData.option.properties %}
		private static final int COL_{{property.name|upper}} ={{loop.index + 2 + data.option.properties|length + data.option.categories|length}};
		{% endfor %}
		{% if extraData.option.categories|length > 0 %}
		private static final int COL_TYPE{{extraData.option.entity|upper}} = {{3 + extraData.option.properties|length + extraData.option.properties|length  + data.option.categories|length}};
		{% endif %}
		{% endif %}
		
		// Lista de Valores
		private List<Organizer> rows;
		
		public OrganizerTableModel(List<Organizer> values){
			this.rows = values;
		}
		
		public int getRowCount() {
			return rows.size();
		}
		
		//Quantidade de Colunas
		public int getColumnCount() {
			return {{2 + data.option.properties|length + (data.option.properties is defined) + extraData.option.properties|length + (extraData.option.properties is defined)}};
		}
		
		//Preenchimento de cada coluna
			public Object getValueAt(int rowIndex, int columnIndex) {
				Organizer organizer = rows.get(rowIndex);
				if (columnIndex == COL_USERID) {
					return organizer.getIdUser();
				}  else if (columnIndex == COL_NAMEUSER) {
					return organizer.getNameUser();
				} else if (columnIndex == COL_TYPEUSER) {
					return organizer.getTypeUser().name();
				} else if (columnIndex == COL_EMAIL) {
					return organizer.getEmail();
				}  else if (columnIndex == COL_TYPEORGANIZER) {
					return organizer.getTypeOrganizer().name();
				}
				{% for property in data.option.properties %}
				else if (columnIndex == COL_{{property.name|upper}}) {
					return organizer.get{{property.name|capitalize}}();
				}
				{% endfor %}
				{% if data.option.categories|length > 0 %}
				else if (columnIndex == COL_TYPE{{data.option.entity|upper}}) {
					return organizer.getType{{data.option.entity}}();
				}
				{% endif %}
				{% for property in extraData.option.properties %}
				else if (columnIndex == COL_{{property.name|upper}}) {
					return organizer.get{{property.name|capitalize}}();
				}
				{% endfor %}
				{% if extraData.option.categories|length > 0 %}
				else if (columnIndex == COL_TYPE{{extraData.option.entity|upper}}) {
					return organizer.getType{{extraData.option.entity}}();
				}
				{% endif %}
		
				return null;
			}
			
			@Override
			public String getColumnName(int column) {
				String coluna = "";
				switch (column) {
				case COL_USERID:
					coluna = "User Id";
					break;
				case COL_NAMEUSER:
					coluna = "Name";
					break;
				case COL_EMAIL:
					coluna = "Email";
					break;  
				{% for property in data.option.properties %}
				case COL_{{property.name|upper}}:
					coluna = "{{property.alias}}";
					break;
				{% endfor %}
				{% if data.option.categories|length > 0 %}
				case COL_{{data.option.entity|upper}}:
					coluna = "Tipo";
					break;
				{% endif %}
				{% for property in extraData.option.properties %}
				case COL_{{property.name|upper}}:
					coluna = "{{property.alias}}";
					break;
				{% endfor %}
				{% if extraData.option.categories|length > 0 %}
				case COL_TYPE{{extraData.option.entity|upper}}:
					coluna = "Tipo {{extraData.option.entity}}";
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
				if (columnIndex == COL_USERID) {
					return int.class;
				} else if (columnIndex == COL_NAMEUSER) {
					return String.class;
				} else if (columnIndex == COL_EMAIL) {
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
				{% for property in extraData.option.properties %}
				else if (columnIndex == COL_{{property.name|upper}}) {
					return {{property.type|javatype}}.class;
				}
				{% endfor %}
				{% if extraData.option.categories|length > 0 %}
				else if (columnIndex == COL_TYPE{{extraData.option.entity|upper}}) {
					return String.class;
				}
				{% endif %}
			
				return null;
			}
			
			//Abaixo metodos de InserÁ„o, remoÁ„o, update e etc;
			public Organizer get(int row) {
				return rows.get(row);
			}

			public void addOrganizer(Organizer organizer) {
				rows.add(organizer);
				int ultimoIndice = getRowCount() - 1;
				fireTableRowsInserted(ultimoIndice, ultimoIndice);
			}
			
			public void removeOrganizer(int indiceLinha) {
				rows.remove(indiceLinha);
				fireTableRowsDeleted(indiceLinha, indiceLinha);
			}
			
			public void alterarReviewer(int indiceLinha, Organizer organizer) {
				rows.get(indiceLinha).setIdUser(organizer.getIdUser());
				rows.get(indiceLinha).setNameUser(organizer.getNameUser());
				rows.get(indiceLinha).setEmail(organizer.getEmail());
				{% for property in data.option.properties %}
				rows.get(indiceLinha).set{{property.name|capitalize}}(activity.get{{property.name|capitalize}}());
				{% endfor %}
				{% if data.option.categories|length > 0 %}
				rows.get(indiceLinha).setType{{data.option.entity}}(activity.getType{{data.option.entity}}());
				{% endif %}
				{% for property in extraData.option.properties %}
				rows.get(indiceLinha).set{{property.name|capitalize}}(activity.get{{property.name|capitalize}}());
				{% endfor %}
				{% if extraData.option.categories|length > 0 %}
				rows.get(indiceLinha).setType{{extraData.option.entity}}(activity.getType{{data.option.entity}}());
				{% endif %}
			
				fireTableDataChanged();
			}
			
			public void addListaDeReviewers(List<Organizer> organizers) {
				int indice = getRowCount();
				rows.addAll(organizers);
				fireTableRowsInserted(indice, indice + organizers.size());
			}
			
			public int retornarIndice(Organizer organizer) {
				return rows.indexOf(organizer);
			}
			
			// Remove todos os registros.
			public void limpar() {
				rows.clear();

				// Notifica a mudança.
				fireTableDataChanged();
			}
}
//#endif