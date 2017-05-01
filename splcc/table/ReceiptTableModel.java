//#if ${Receipt} == "T" 
package {{systemName|lower}}.ev.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import {{systemName|lower}}.ev.data.Receipt;

public class ReceiptTableModel extends AbstractTableModel{

	// Nome das Colunas
	
		private static final int COL_RECEIPTID = 0;
		private static final int COL_PAYMENTID = 1;
		private static final int COL_TOTALVALUE = 2;
		private static final int COL_DATE = 3;
		{% for property in data.option.properties %}
		private static final int COL_{{property.name|upper}} ={{loop.index+3}};
		{% endfor %}
		{% if data.option.categories|length > 0 %}
		private static final int COL_TYPE{{data.option.entity|upper}} = {{3 + data.option.properties|length}};
		{% endif %}
		
		// Lista de Valores
		private List<Receipt> rows;
		
		public ReceiptTableModel(List<Receipt> values){
			this.rows = values;
		}
		
		public int getRowCount() {
			return rows.size();
		}
		
		//Quantidade de Colunas
		public int getColumnCount() {
			return {{4 + data.option.properties|length + (data.option.properties is defined)}};
		}
		
		//Preenchimento de cada coluna
			public Object getValueAt(int rowIndex, int columnIndex) {
				Receipt receipt = rows.get(rowIndex);
				if (columnIndex == COL_RECEIPTID) {
					return receipt.getIdReceipt();
				} else if (columnIndex == COL_PAYMENTID) {
					return receipt.getIdPayment();
				} else if (columnIndex == COL_TOTALVALUE) {
					return receipt.getTotalValue();
				} else if (columnIndex == COL_DATE) {
					return receipt.getDateOfIssue();
				} 
				{% for property in data.option.properties %}
				else if (columnIndex == COL_{{property.name|upper}}) {
					return receipt.get{{property.name|capitalize}}();
				}
				{% endfor %}
				{% if data.option.categories|length > 0 %}
				else if (columnIndex == COL_TYPE{{data.option.entity|upper}}) {
					return receipt.getType{{data.option.entity}}();
				}
				{% endif %}
			
				return null;
			}
			
			@Override
			public String getColumnName(int column) {
				String coluna = "";
				switch (column) {
				case COL_RECEIPTID:
					coluna = "Receipt Id";
					break;
				case COL_PAYMENTID:
					coluna = "Payment Id";
					break;
				case COL_TOTALVALUE:
					coluna = "Total Value";
					break;
				case COL_DATE:
					coluna = "Date Of Issue";
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
				if (columnIndex == COL_RECEIPTID) {
					return int.class;
				} else if (columnIndex == COL_PAYMENTID) {
					return int.class;
				} else if (columnIndex == COL_TOTALVALUE) {
					return float.class;
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
			public Receipt get(int row) {
				return rows.get(row);
			}

			public void addReceipt(Receipt receipt) {
				rows.add(receipt);
				int ultimoIndice = getRowCount() - 1;
				fireTableRowsInserted(ultimoIndice, ultimoIndice);
			}
			
			public void removeReceipt(int indiceLinha) {
				rows.remove(indiceLinha);
				fireTableRowsDeleted(indiceLinha, indiceLinha);
			}
			
			public void alterarReceipt(int indiceLinha, Receipt receipt) {
				rows.get(indiceLinha).setIdPayment(receipt.getIdReceipt());
				rows.get(indiceLinha).setDateOfIssue(receipt.getDateOfIssue());
				rows.get(indiceLinha).setTotalValue(receipt.getTotalValue());	
				{% for property in data.option.properties %}
				rows.get(indiceLinha).set{{property.name|capitalize}}(receipt.get{{property.name|capitalize}}());
				{% endfor %}
				{% if data.option.categories|length > 0 %}
				rows.get(indiceLinha).setType{{data.option.entity}}(receipt.getType{{data.option.entity}}());
				{% endif %}
			
				fireTableDataChanged();
			}
			
			public void addListaDeReceiptos(List<Receipt> receipts) {
				int indice = getRowCount();
				rows.addAll(receipts);
				fireTableRowsInserted(indice, indice + receipts.size());
			}
			
			public int retornarIndice(Receipt receipt) {
				return rows.indexOf(receipt);
			}
			
			// Remove todos os registros.
			public void limpar() {
				rows.clear();

				// Notifica a mudança.
				fireTableDataChanged();
			}
}
//#endif