//#if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T"
package {{systemName|lower}}.ev.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import {{systemName|lower}}.ev.data.Assignment;

public class AssignmentTableModel extends AbstractTableModel{

	// Nome das Colunas
	
		private static final int COL_REVIEWERID = 0;
		private static final int COL_SUBMISSIONID = 1;
		private static final int COL_REVIEWID = 2;
		private static final int COL_DATE = 3;
		{% for property in data.option.properties %}
		private static final int COL_{{property.name|upper}} ={{loop.index+3}};
		{% endfor %}
		{% if data.option.categories|length > 0 %}
		private static final int COL_TYPE{{data.option.entity|upper}} = {{4 + data.option.properties|length}};
		{% endif %}
		
		// Lista de Valores
		private List<Assignment> rows;
		
		public AssignmentTableModel(List<Assignment> values){
			this.rows = values;
		}
		
		public int getRowCount() {
			return rows.size();
		}
		
		//Quantidade de Colunas
		public int getColumnCount() {
			return {{3 + data.option.properties|length + data.option.categories|length}};
		}
		
		//Preenchimento de cada coluna
			public Object getValueAt(int rowIndex, int columnIndex) {
				Assignment assignment = rows.get(rowIndex);
				if (columnIndex == COL_REVIEWERID) {
					return assignment.getIdReviwerUser();
				}  else if (columnIndex == COL_SUBMISSIONID) {
					return assignment.getIdReviewSubmission();
				}	else if (columnIndex == COL_REVIEWID) {
					return assignment.getIdReview();
				}	else if (columnIndex == COL_DATE) {
					return assignment.getDate();
				}
				{% for property in data.option.properties %}
				else if (columnIndex == COL_{{property.name|upper}}) {
					return assignment.get{{property.name|capitalize}}();
				}
				{% endfor %}
				{% if data.option.categories|length > 0 %}
				else if (columnIndex == COL_TYPE{{data.option.entity|upper}}) {
					return assignment.getType{{data.option.entity}}();
				}
				{% endif %}
				return null;
			}
			
			@Override
			public String getColumnName(int column) {
				String coluna = "";
				switch (column) {
				case COL_REVIEWERID:
					coluna = "Reviewer Id";
					break;
				case COL_SUBMISSIONID:
					coluna = "Submission Id";
					break;
				case COL_REVIEWID:
					coluna = "Review Id";
					break;
				case COL_DATE:
					coluna = "Date";
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
				if (columnIndex == COL_REVIEWERID) {
					return int.class;
				} else if (columnIndex == COL_SUBMISSIONID) {
					return int.class;
				}else if (columnIndex == COL_REVIEWID) {
					return int.class;
				} else if (columnIndex == COL_DATE) {
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
			public Assignment get(int row) {
				return rows.get(row);
			}

			public void addAssignment(Assignment assignment) {
				rows.add(assignment);
				int ultimoIndice = getRowCount() - 1;
				fireTableRowsInserted(ultimoIndice, ultimoIndice);
			}
			
			public void removeAssignment(int indiceLinha) {
				rows.remove(indiceLinha);
				fireTableRowsDeleted(indiceLinha, indiceLinha);
			}
			
			public void alterarReviewer(int indiceLinha, Assignment assignment) {
				rows.get(indiceLinha).setIdReview(assignment.getIdReview());
				rows.get(indiceLinha).setIdReviwerUser(assignment.getIdReviwerUser());
				rows.get(indiceLinha).setIdReviewSubmission(assignment.getIdReviewSubmission());
				rows.get(indiceLinha).setDate(assignment.getDate());
				{% for property in data.option.properties %}
				rows.get(indiceLinha).set{{property.name|capitalize}}(assignment.get{{property.name|capitalize}}());
				{% endfor %}
				{% if data.option.categories|length > 0 %}
				rows.get(indiceLinha).setType{{data.option.entity}}(assignment.getType{{data.option.entity}}());
				{% endif %}
			
				fireTableDataChanged();
			}
			
			public void addListaDeReviewers(List<Assignment> assignments) {
				int indice = getRowCount();
				rows.addAll(assignments);
				fireTableRowsInserted(indice, indice + assignments.size());
			}
			
			public int retornarIndice(Assignment assignment) {
				return rows.indexOf(assignment);
			}
			
			// Remove todos os registros.
			public void limpar() {
				rows.clear();

				// Notifica a mudança.
				fireTableDataChanged();
			}
}
//#endif