//#if ${Speaker} == "T"
package riseevents.ev.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import riseevents.ev.data.Speaker;
import riseevents.ev.data.User;

public class SpeakerTableModel extends AbstractTableModel{

	// Nome das Colunas
	
		private static final int COL_USERID = 0;
		//private static final int COL_PASSWORD = 1;
		private static final int COL_NAMEUSER = 1;
		private static final int COL_EMAIL = 2;
		private static final int COL_BIOGRAPHY = 3;
		
		private static final int COL_TYPEUSER = 5;
		
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
			return 6;
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
				else if (columnIndex == COL_TYPEUSER) {
					return speaker.getTypeUser();
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
				case COL_BIOGRAPHY:
					coluna = "Biography";
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
				}  else if (columnIndex == COL_BIOGRAPHY) {
					return String.class;
				}
				else if (columnIndex == COL_TYPEUSER) {
					return String.class;
				}
			
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
					rows.get(indiceLinha).setTypeUser(speaker.getTypeUser());
			
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