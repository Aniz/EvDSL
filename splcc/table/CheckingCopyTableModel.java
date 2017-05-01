//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
package {{systemName|lower}}.ev.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import {{systemName|lower}}.ev.data.CheckingCopy;
import {{systemName|lower}}.ev.data.CheckingCopy.TypeCheckingCopy;

public class CheckingCopyTableModel extends AbstractTableModel{

	// Nome das Colunas
	
		private static final int COL_CHECKINGCOPYID = 0;
		private static final int COL_REGISTRATIONID = 1;
		private static final int COL_USERID = 2;
		private static final int COL_DATE = 3;
		{% for property in data.option.properties %}
		private static final int COL_{{property.name|upper}} ={{loop.index+3}};
		{% endfor %}
		{% if data.option.categories|length > 0 %}
		private static final int COL_TYPE{{data.option.entity|upper}} = {{4 + data.option.properties|length}};
		{% endif %}
		
		// Lista de Valores
		private List<CheckingCopy> rows;
		
		public CheckingCopyTableModel(List<CheckingCopy> values){
			this.rows = values;
		}
		
		public int getRowCount() {
			return rows.size();
		}
		
		//Quantidade de Colunas
		public int getColumnCount() {
			return {{4 + data.option.properties|length + (data.option.categories is defined)}};
		}
		
		//Preenchimento de cada coluna
			public Object getValueAt(int rowIndex, int columnIndex) {
				CheckingCopy checkingCopy = rows.get(rowIndex);
				if (columnIndex == COL_CHECKINGCOPYID) {
					return checkingCopy.getIdCheckingCopy();
				} else if (columnIndex == COL_REGISTRATIONID) {
					return checkingCopy.getIdRegistration();
				} else if (columnIndex == COL_USERID) {
					return checkingCopy.getIdUser();
				} else if (columnIndex == COL_DATE) {
					return checkingCopy.getDateOfIssue();
				} 
				{% for property in data.option.properties %}
				else if (columnIndex == COL_{{property.name|upper}}) {
					return checkingCopy.get{{property.name|capitalize}}();
				}
				{% endfor %}
				{% if data.option.categories|length > 0 %}
				else if (columnIndex == COL_TYPE{{data.option.entity|upper}}) {
					return checkingCopy.getType{{data.option.entity}}();
				}
				{% endif %}
			
				return null;
			}
			
			@Override
			public String getColumnName(int column) {
				String coluna = "";
				switch (column) {
				case COL_CHECKINGCOPYID:
					coluna = "Checking Copy Id";
					break;
				case COL_REGISTRATIONID:
					coluna = "Registration Id";
					break;
				case COL_USERID:
					coluna = "User Id";
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
				if (columnIndex == COL_CHECKINGCOPYID) {
					return int.class;
				} else if (columnIndex == COL_REGISTRATIONID) {
					return int.class;
				} else if (columnIndex == COL_USERID) {
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
			public CheckingCopy get(int row) {
				return rows.get(row);
			}

			public void addCheckingCopy(CheckingCopy checkingCopy) {
				rows.add(checkingCopy);
				int ultimoIndice = getRowCount() - 1;
				fireTableRowsInserted(ultimoIndice, ultimoIndice);
			}
			
			public void removeCheckingCopy(int indiceLinha) {
				rows.remove(indiceLinha);
				fireTableRowsDeleted(indiceLinha, indiceLinha);
			}
			
			public void alterarCheckingCopy(int indiceLinha, CheckingCopy checkingCopy) {
				rows.get(indiceLinha).setDateOfIssue(checkingCopy.getDateOfIssue());
				rows.get(indiceLinha).setIdRegistration(checkingCopy.getIdRegistration());	
				{% for property in data.option.properties %}
				rows.get(indiceLinha).set{{property.name|capitalize}}(checkingCopy.get{{property.name|capitalize}}());
				{% endfor %}
				{% if data.option.categories|length > 0 %}
				rows.get(indiceLinha).setType{{data.option.entity}}(checkingCopy.getType{{data.option.entity}}());
				{% endif %}
			
				fireTableDataChanged();
			}
			
			public void addListaDeCheckingCopyos(List<CheckingCopy> checkingCopys) {
				int indice = getRowCount();
				rows.addAll(checkingCopys);
				fireTableRowsInserted(indice, indice + checkingCopys.size());
			}
			
			public int retornarIndice(CheckingCopy checkingCopy) {
				return rows.indexOf(checkingCopy);
			}
			
			// Remove todos os registros.
			public void limpar() {
				rows.clear();

				// Notifica a mudança.
				fireTableDataChanged();
			}
}
//#endif