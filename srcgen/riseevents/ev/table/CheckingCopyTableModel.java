//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
package riseevents.ev.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import riseevents.ev.data.CheckingCopy;
import riseevents.ev.data.CheckingCopy.TypeCheckingCopy;

public class CheckingCopyTableModel extends AbstractTableModel{

	// Nome das Colunas
	
		private static final int COL_CHECKINGCOPYID = 0;
		private static final int COL_REGISTRATIONID = 1;
		private static final int COL_USERID = 2;
		private static final int COL_DATE = 3;
		private static final int COL_BANANA =11;
		private static final int COL_TYPECHECKINGCOPY = 11;
		
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
			return 6;
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
				} else if (columnIndex == COL_TYPE) {
					return checkingCopy.getTypeCheckingCopy();
				} 
				else if (columnIndex == COL_BANANA) {
					return activity.getBanana();
				}
				else if (columnIndex == COL_TYPECHECKINGCOPY) {
					return activity.getTypeCheckingCopy();
				}
			
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
				case COL_TYPE:
					coluna = "Type";
					break;
				case COL_BANANA:
					coluna = "";
					break;
				case COL_TYPECHECKINGCOPY:
					coluna = "Tipo";
					break;
				
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
				else if (columnIndex == COL_BANANA) {
					return String.class;
				}
				else if (columnIndex == COL_TYPECHECKINGCOPY) {
					return String.class;
				}
			
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
				rows.get(indiceLinha).setBanana(activity.getBanana());
				rows.get(indiceLinha).setTypeCheckingCopy(activity.getTypeCheckingCopy());
			
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