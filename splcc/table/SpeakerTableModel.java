//#if ${Speaker} == "T"
package {{systemName|lower}}.ev.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import {{systemName|lower}}.ev.data.Speaker;
import {{systemName|lower}}.ev.data.User;

public class SpeakerTableModel extends AbstractTableModel{

	// Nome das Colunas
	
		private static final int COL_USERID = 0;
		//private static final int COL_PASSWORD = 1;
		private static final int COL_NAMEUSER = 1;
		private static final int COL_EMAIL = 2;
		private static final int COL_BIOGRAPHY = 3;
		{% for property in data.option.properties %}
		private static final int COL_{{property.name|upper}} ={{loop.index+3}};
		{% endfor %}
		{% if data.option.categories|length > 0 %}
		private static final int COL_TYPE{{data.option.entity|upper}} = {{4 + data.option.properties|length}};
		{% endif %}
		
		{% for property in extraData.option.properties %}
		private static final int COL_{{property.name|upper}} ={{loop.index+3 + data.option.properties|length + (data.option.categories is defined)}};
		{% endfor %}
		{% if extraData.option.categories|length > 0 %}
		private static final int COL_TYPE{{extraData.option.entity|upper}} = {{4 + extraData.option.properties|length + extraData.option.properties|length  + (data.option.categories is defined)}};
		{% endif %}
		
		// Lista de Valores
		private List<Speaker> rows;
		
		public SpeakerTableModel(List<Speaker> values){
			this.rows = values;
		}
		
		public int getRowCount() {
			return rows.size();
		}
		
		//Quantidade de Colunas
		public int getColumnCount() {
			return {{4 + data.option.properties|length + (data.option.categories is defined) + extraData.option.properties|length + (extraData.option.categories is defined)}};
		}
		
		//Preenchimento de cada coluna
			public Object getValueAt(int rowIndex, int columnIndex) {
				Speaker speaker = rows.get(rowIndex);
				if (columnIndex == COL_USERID) {
					return speaker.getIdUser();
				}  else if (columnIndex == COL_NAMEUSER) {
					return speaker.getNameUser();
				} else if (columnIndex == COL_EMAIL) {
					return speaker.getEmail();
				}  else if (columnIndex == COL_BIOGRAPHY) {
					return speaker.getBiography();
				}
				{% for property in data.option.properties %}
				else if (columnIndex == COL_{{property.name|upper}}) {
					return speaker.get{{property.name|capitalize}}();
				}
				{% endfor %}
				{% if data.option.categories|length > 0 %}
				else if (columnIndex == COL_TYPE{{data.option.entity|upper}}) {
					return speaker.getType{{data.option.entity}}();
				}
				{% endif %}
				{% for property in extraData.option.properties %}
				else if (columnIndex == COL_{{property.name|upper}}) {
					return speaker.get{{property.name|capitalize}}();
				}
				{% endfor %}
				{% if extraData.option.categories|length > 0 %}
				else if (columnIndex == COL_TYPE{{extraData.option.entity|upper}}) {
					return speaker.getType{{extraData.option.entity}}();
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
				case COL_BIOGRAPHY:
					coluna = "Biography";
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
				}  else if (columnIndex == COL_BIOGRAPHY) {
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
			public Speaker get(int row) {
				return rows.get(row);
			}

			public void addSpeaker(Speaker speaker) {
				rows.add(speaker);
				int ultimoIndice = getRowCount() - 1;
				fireTableRowsInserted(ultimoIndice, ultimoIndice);
			}
			
			public void removeSpeaker(int indiceLinha) {
				rows.remove(indiceLinha);
				fireTableRowsDeleted(indiceLinha, indiceLinha);
			}
			
			public void alterarSpeaker(int indiceLinha, Speaker speaker) {
				rows.get(indiceLinha).setIdUser(speaker.getIdUser());
				rows.get(indiceLinha).setNameUser(speaker.getNameUser());
				rows.get(indiceLinha).setEmail(speaker.getEmail());	
				rows.get(indiceLinha).setBiography(speaker.getBiography());
				{% for property in data.option.properties %}
				rows.get(indiceLinha).set{{property.name|capitalize}}(speaker.get{{property.name|capitalize}}());
				{% endfor %}
				{% if data.option.categories|length > 0 %}
				rows.get(indiceLinha).setType{{data.option.entity}}(speaker.getType{{data.option.entity}}());
				{% endif %}
				{% for property in extraData.option.properties %}
				rows.get(indiceLinha).set{{property.name|capitalize}}(speaker.get{{property.name|capitalize}}());
				{% endfor %}
				{% if extraData.option.categories|length > 0 %}
					rows.get(indiceLinha).setType{{extraData.option.entity}}(speaker.getType{{extraData.option.entity}}());
				{% endif %}
			
				fireTableDataChanged();
			}
			
			public void addListaDeSpeakers(List<Speaker> speakers) {
				int indice = getRowCount();
				rows.addAll(speakers);
				fireTableRowsInserted(indice, indice + speakers.size());
			}
			
			public int retornarIndice(Speaker speaker) {
				return rows.indexOf(speaker);
			}
			
			// Remove todos os registros.
			public void limpar() {
				rows.clear();

				// Notifica a mudança.
				fireTableDataChanged();
			}
}
//#endif