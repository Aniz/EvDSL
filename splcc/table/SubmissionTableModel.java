//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
package {{systemName|lower}}.ev.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import {{systemName|lower}}.ev.data.Submission;
import {{systemName|lower}}.ev.data.Submission.TypeSubmission;

public class SubmissionTableModel extends AbstractTableModel{

	// Nome das Colunas
	
		private static final int COL_SUBMISSIONID = 0;
		private static final int COL_ACTIVITYID = 1;
		private static final int COL_ABSTRACT = 2;
		private static final int COL_KEYWORDS = 3;
		{% for property in data.option.properties %}
		private static final int COL_{{property.name|upper}} ={{loop.index+3}};
		{% endfor %}
		{% if data.option.categories|length > 0 %}
		private static final int COL_TYPE{{data.option.entity|upper}} = {{4 + data.option.properties|length}};
		{% endif %}
		
		// Lista de Valores
		private List<Submission> rows;
		
		public SubmissionTableModel(List<Submission> values){
			this.rows = values;
		}
		
		public int getRowCount() {
			return rows.size();
		}
		
		//Quantidade de Colunas
		public int getColumnCount() {
			return {{4 + data.option.properties|length}} {% if data.option.categories|length > 0 %}+1{% endif %};
		}
		
		//Preenchimento de cada coluna
			public Object getValueAt(int rowIndex, int columnIndex) {
				Submission submission = rows.get(rowIndex);
				if (columnIndex == COL_SUBMISSIONID) {
					return submission.getIdSubmission();
				} else if (columnIndex == COL_ACTIVITYID) {
					return submission.getIdActivity();
				} else if (columnIndex == COL_ABSTRACT) {
					return submission.getAbstractPaper();
				} else if (columnIndex == COL_KEYWORDS) {
					return submission.getKeywords();
				} 
				{% for property in data.option.properties %}
				else if (columnIndex == COL_{{property.name|upper}}) {
					return submission.get{{property.name|capitalize}}();
				}
				{% endfor %}
				{% if data.option.categories|length > 0 %}
				else if (columnIndex == COL_TYPE{{data.option.entity|upper}}) {
					return submission.getType{{data.option.entity}}();
				}
				{% endif %}
			
				return null;
			}
			
			@Override
			public String getColumnName(int column) {
				String coluna = "";
				switch (column) {
				case COL_SUBMISSIONID:
					coluna = "Submission Id";
					break;
				case COL_ACTIVITYID:
					coluna = "Activity Id";
					break;
				case COL_ABSTRACT:
					coluna = "Abstract";
					break;
				case COL_KEYWORDS:
					coluna = "Keywords";
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
				if (columnIndex == COL_SUBMISSIONID) {
					return int.class;
				} else if (columnIndex == COL_ACTIVITYID) {
					return int.class;
				} else if (columnIndex == COL_ABSTRACT) {
					return String.class;
				} else if (columnIndex == COL_KEYWORDS) {
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
			public Submission get(int row) {
				return rows.get(row);
			}

			public void addSubmission(Submission submission) {
				rows.add(submission);
				int ultimoIndice = getRowCount() - 1;
				fireTableRowsInserted(ultimoIndice, ultimoIndice);
			}
			
			public void removeSubmission(int indiceLinha) {
				rows.remove(indiceLinha);
				fireTableRowsDeleted(indiceLinha, indiceLinha);
			}
			
			public void alterarSubmission(int indiceLinha, Submission submission) {
				rows.get(indiceLinha).setKeywords(submission.getKeywords());
				rows.get(indiceLinha).setAbstractPaper(submission.getAbstractPaper());	
				{% for property in data.option.properties %}
				rows.get(indiceLinha).set{{property.name|capitalize}}(submission.get{{property.name|capitalize}}());
				{% endfor %}
				{% if data.option.categories|length > 0 %}
				rows.get(indiceLinha).setType{{data.option.entity}}(submission.getType{{data.option.entity}}());
				{% endif %}
			
				fireTableDataChanged();
			}
			
			public void addListaDeSubmissionos(List<Submission> submissions) {
				int indice = getRowCount();
				rows.addAll(submissions);
				fireTableRowsInserted(indice, indice + submissions.size());
			}
			
			public int retornarIndice(Submission submission) {
				return rows.indexOf(submission);
			}
			
			// Remove todos os registros.
			public void limpar() {
				rows.clear();

				// Notifica a mudança.
				fireTableDataChanged();
			}
}
//#endif