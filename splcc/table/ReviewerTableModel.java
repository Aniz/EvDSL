//#if ${Reviewer} == "T"
package {{systemName|lower}}.ev.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import {{systemName|lower}}.ev.data.Reviewer;

public class ReviewerTableModel extends AbstractTableModel{

	// Nome das Colunas
	
		private static final int COL_USERID = 0;
		//private static final int COL_PASSWORD = 1;
		private static final int COL_NAMEUSER = 1;
		private static final int COL_EMAIL = 2;
		private static final int COL_KNOWLEDGEAREA = 3;
		
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
		private List<Reviewer> rows;
		
		public ReviewerTableModel(List<Reviewer> values){
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
				Reviewer reviewer = rows.get(rowIndex);
				if (columnIndex == COL_USERID) {
					return reviewer.getIdUser();
				}  else if (columnIndex == COL_NAMEUSER) {
					return reviewer.getNameUser();
				} else if (columnIndex == COL_TYPEUSER) {
					return reviewer.getTypeUser().name();
				} else if (columnIndex == COL_EMAIL) {
					return reviewer.getEmail();
				}  else if (columnIndex == COL_KNOWLEDGEAREA) {
					return reviewer.getKnowledgeArea();
				}
				{% for property in data.option.properties %}
				else if (columnIndex == COL_{{property.name|upper}}) {
					return reviewer.get{{property.name|capitalize}}();
				}
				{% endfor %}
				{% if data.option.categories|length > 0 %}
				else if (columnIndex == COL_TYPE{{data.option.entity|upper}}) {
					return reviewer.getType{{data.option.entity}}();
				}
				{% endif %}
				{% for property in extraData.option.properties %}
				else if (columnIndex == COL_{{property.name|upper}}) {
					return reviewer.get{{property.name|capitalize}}();
				}
				{% endfor %}
				{% if extraData.option.categories|length > 0 %}
				else if (columnIndex == COL_TYPE{{extraData.option.entity|upper}}) {
					return reviewer.getType{{extraData.option.entity}}();
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
				case COL_TYPEUSER:
					coluna = "Type";
					break;
				case COL_EMAIL:
					coluna = "Email";
					break;  
				case COL_KNOWLEDGEAREA:
					coluna = "Knowledge Area";
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
				} else if (columnIndex == COL_TYPEUSER) {
					return String.class;
				} else if (columnIndex == COL_EMAIL) {
					return String.class;
				}  else if (columnIndex == COL_KNOWLEDGEAREA) {
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
			public Reviewer get(int row) {
				return rows.get(row);
			}

			public void addReviewer(Reviewer reviewer) {
				rows.add(reviewer);
				int ultimoIndice = getRowCount() - 1;
				fireTableRowsInserted(ultimoIndice, ultimoIndice);
			}
			
			public void removeReviewer(int indiceLinha) {
				rows.remove(indiceLinha);
				fireTableRowsDeleted(indiceLinha, indiceLinha);
			}
			
			public void alterarReviewer(int indiceLinha, Reviewer reviewer) {
				rows.get(indiceLinha).setIdUser(reviewer.getIdUser());
				rows.get(indiceLinha).setNameUser(reviewer.getNameUser());
				rows.get(indiceLinha).setEmail(reviewer.getEmail());
				rows.get(indiceLinha).setKnowledgeArea(reviewer.getKnowledgeArea());
				{% for property in data.option.properties %}
				rows.get(indiceLinha).set{{property.name|capitalize}}(reviewer.get{{property.name|capitalize}}());
				{% endfor %}
				{% if data.option.categories|length > 0 %}
				rows.get(indiceLinha).setType{{data.option.entity}}(reviewer.getType{{data.option.entity}}());
				{% endif %}
				{% for property in extraData.option.properties %}
				rows.get(indiceLinha).set{{property.name|capitalize}}(reviewer.get{{property.name|capitalize}}());
				{% endfor %}
				{% if extraData.option.categories|length > 0 %}
				rows.get(indiceLinha).setType{{extraData.option.entity}}(reviewer.getType{{data.option.entity}}());
				{% endif %}
			
				fireTableDataChanged();
			}
			
			public void addListaDeReviewers(List<Reviewer> reviewers) {
				int indice = getRowCount();
				rows.addAll(reviewers);
				fireTableRowsInserted(indice, indice + reviewers.size());
			}
			
			public int retornarIndice(Reviewer reviewer) {
				return rows.indexOf(reviewer);
			}
			
			// Remove todos os registros.
			public void limpar() {
				rows.clear();

				// Notifica a mudança.
				fireTableDataChanged();
			}
}
//#endif