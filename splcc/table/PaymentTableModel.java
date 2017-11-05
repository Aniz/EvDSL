//#if ${PaymentAvista} == "T" or ${PaymentDeposito} == "T" or ${PaymentCartao} == "T"
package {{systemName|lower}}.ev.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;


import {{systemName|lower}}.ev.data.Payment;

public class PaymentTableModel extends AbstractTableModel{

	// Nome das Colunas
	
		private static final int COL_PAYMENTID = 0;
		private static final int COL_REGISTRATIONID = 1;
		private static final int COL_STATUS = 2;
		private static final int COL_DATE = 3;
		private static final int COL_TYPE = 4;
		private static final int COL_BARCODE = 5;
		private static final int COL_VALUE = 6;
		{% for property in data.option.properties %}
		private static final int COL_{{property.name|upper}} ={{loop.index+6}};
		{% endfor %}
		{% if data.option.categories|length > 0 %}
		private static final int COL_TYPE{{data.option.entity|upper}} = {{7 + data.option.properties|length}};
		{% endif %}
		
		// Lista de Valores
		private List<Payment> rows;
		
		public PaymentTableModel(List<Payment> values){
			this.rows = values;
		}
		
		public int getRowCount() {
			return rows.size();
		}
		
		//Quantidade de Colunas
		public int getColumnCount() {
			return {{7 + data.option.properties|length}} {% if data.option.categories|length > 0 %}+1{% endif %};
		}
		
		//Preenchimento de cada coluna
			public Object getValueAt(int rowIndex, int columnIndex) {
				Payment payment = rows.get(rowIndex);
				if (columnIndex == COL_PAYMENTID) {
					return payment.getIdPayment();
				} else if (columnIndex == COL_REGISTRATIONID) {
					return payment.getIdRegistration();
				} else if (columnIndex == COL_STATUS) {
					return payment.getStatus();
				} else if (columnIndex == COL_DATE) {
					return payment.getDate();
				} else if (columnIndex == COL_TYPE) {
					return payment.getTypePayment();
				} else if (columnIndex == COL_BARCODE) {
					return payment.getBarcode();
				} else if (columnIndex == COL_VALUE) {
					return payment.getValue();
				} 
				{% for property in data.option.properties %}
				else if (columnIndex == COL_{{property.name|upper}}) {
					return payment.get{{property.name|capitalize}}();
				}
				{% endfor %}
				{% if data.option.categories|length > 0 %}
				else if (columnIndex == COL_TYPE{{data.option.entity|upper}}) {
					return payment.getType{{data.option.entity}}();
				}
				{% endif %}
				return null;
			}
			
			@Override
			public String getColumnName(int column) {
				String coluna = "";
				switch (column) {
				case COL_PAYMENTID:
					coluna = "Payment Id";
					break;
				case COL_REGISTRATIONID:
					coluna = "Registration Id";
					break;
				case COL_STATUS:
					coluna = "Status";
					break;
				case COL_DATE:
					coluna = "Date";
					break;
				case COL_TYPE:
					coluna = "Type";
					break;
				case COL_BARCODE:
					coluna = "Barcode";
					break;
				case COL_VALUE:
					coluna = "Value";
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
				if (columnIndex == COL_PAYMENTID) {
					return int.class;
				} else if (columnIndex == COL_REGISTRATIONID) {
					return int.class;
				} else if (columnIndex == COL_STATUS) {
					return String.class;
				} else if (columnIndex == COL_DATE) {
					return String.class;
				} else if (columnIndex == COL_TYPE) {
					return String.class;
				} else if (columnIndex == COL_BARCODE) {
					return String.class;
				} else if (columnIndex == COL_VALUE) {
					return Float.class;
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
			public Payment get(int row) {
				return rows.get(row);
			}

			public void addPayment(Payment payment) {
				rows.add(payment);
				int ultimoIndice = getRowCount() - 1;
				fireTableRowsInserted(ultimoIndice, ultimoIndice);
			}
			
			public void removePayment(int indiceLinha) {
				rows.remove(indiceLinha);
				fireTableRowsDeleted(indiceLinha, indiceLinha);
			}
			
			public void alterarPayment(int indiceLinha, Payment payment) {
				rows.get(indiceLinha).setBarcode(payment.getBarcode());
				rows.get(indiceLinha).setStatus(payment.getStatus());
				rows.get(indiceLinha).setDate(payment.getDate());
				rows.get(indiceLinha).setTypePayment(payment.getTypePayment());
				rows.get(indiceLinha).setValue(payment.getValue());
				rows.get(indiceLinha).setIdRegistration(payment.getIdRegistration());
				{% for property in data.option.properties %}
				rows.get(indiceLinha).set{{property.name|capitalize}}(payment.get{{property.name|capitalize}}());
				{% endfor %}
				{% if data.option.categories|length > 0 %}
				rows.get(indiceLinha).setType{{data.option.entity}}(payment.getType{{data.option.entity}}());
				{% endif %}
			
				fireTableDataChanged();
			}
			
			public void addListaDePaymentos(List<Payment> payments) {
				int indice = getRowCount();
				rows.addAll(payments);
				fireTableRowsInserted(indice, indice + payments.size());
			}
			
			public int retornarIndice(Payment payment) {
				return rows.indexOf(payment);
			}
			
			// Remove todos os registros.
			public void limpar() {
				rows.clear();

				// Notifica a mudança.
				fireTableDataChanged();
			}
}
//#endif