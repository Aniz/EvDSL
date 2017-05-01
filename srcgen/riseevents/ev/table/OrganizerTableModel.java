//#if ${Organizer} == "T"

package riseevents.ev.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import riseevents.ev.data.Organizer;

public class OrganizerTableModel extends AbstractTableModel{

	// Nome das Colunas
	
		private static final int COL_USERID = 0;
		//private static final int COL_PASSWORD = 1;
		private static final int COL_NAMEUSER = 1;
		private static final int COL_EMAIL = 2;
		private static final int COL_CONTATO =3;
		private static final int COL_TYPEORGANIZER = 4;
		
		private static final int COL_AAAA =5;
		private static final int COL_BBBB =6;
		private static final int COL_TYPEUSER = 8;
		
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
			return 7;
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
				else if (columnIndex == COL_CONTATO) {
					return organizer.getContato();
				}
				else if (columnIndex == COL_TYPEORGANIZER) {
					return organizer.getTypeOrganizer();
				}
				else if (columnIndex == COL_AAAA) {
					return organizer.getAaaa();
				}
				else if (columnIndex == COL_BBBB) {
					return organizer.getBbbb();
				}
				else if (columnIndex == COL_TYPEUSER) {
					return organizer.getTypeUser();
				}
		
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
				case COL_CONTATO:
					coluna = "";
					break;
				case COL_ORGANIZER:
					coluna = "Tipo";
					break;
				case COL_AAAA:
					coluna = "";
					break;
				case COL_BBBB:
					coluna = "";
					break;
				case COL_TYPEUSER:
					coluna = "Tipo User";
					break;
			
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
				else if (columnIndex == COL_CONTATO) {
					return String.class;
				}
				else if (columnIndex == COL_TYPEORGANIZER) {
					return String.class;
				}
				else if (columnIndex == COL_AAAA) {
					return int.class;
				}
				else if (columnIndex == COL_BBBB) {
					return String.class;
				}
				else if (columnIndex == COL_TYPEUSER) {
					return String.class;
				}
			
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
				rows.get(indiceLinha).setContato(organizer.getContato());
				rows.get(indiceLinha).setTypeOrganizer(organizer.getTypeOrganizer());
				rows.get(indiceLinha).setAaaa(organizer.getAaaa());
				rows.get(indiceLinha).setBbbb(organizer.getBbbb());
				rows.get(indiceLinha).setTypeUser(organizer.getTypeOrganizer());
			
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