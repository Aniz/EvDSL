//#if ${RegistrationOrganizerActivity} == "T"
package rise.splcc.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import rise.splcc.data.{{entity.name}};

public class {{entity.name}}TableModel extends AbstractTableModel{
	// Nome das Colunas
	
			private static final int COL_ACTIVITYID = 0;
			private static final int COL_ORGANIZERID = 1;
			
			// Lista de Valores
			private List<{{entity.name}}> rows;
			
			public {{entity.name}}TableModel(List<{{entity.name|lower}}> values){
				this.rows = values;
			}
			
			public int getRowCount() {
				return rows.size();
			}
			
			//Quantidade de Colunas
			public int getColumnCount() {
				return 2;
			}
			
			//Preenchimento de cada coluna
				public Object getValueAt(int rowIndex, int columnIndex) {
					{{entity.name}} {{entity.name|lower}} = rows.get(rowIndex);
					if (columnIndex == COL_ACTIVITYID) {
						return {{entity.name|lower}}.getIdActivity();
					}  else if (columnIndex == COL_ORGANIZERID) {
						return {{entity.name|lower}}.getIdOrganizer();
					} 
					return null;
				}
				
				@Override
				public String getColumnName(int column) {
					String coluna = "";
					switch (column) {
					{% for property in entity.properties %}
  						case COL_{{property.name|upper}}
						coluna = "{{property.label}}";		
  					{% endfor %}
					
					default:
						throw new IllegalArgumentException(EXC_INVALID_COL);
					}
					return coluna;
				}
				
				//Tipo de valor da colna
				@Override
				public Class<?> getColumnClass(int columnIndex) {
					{% for property in entity.properties %}
					if (columnIndex == {{property.name|upper}}) {
						return {{property.type.name}}.class;
					} 
					{% endfor %}
					return null;
				}
				
				//Abaixo metodos de InserÁ„o, remoÁ„o, update e etc;
				public {{entity.name}} get(int row) {
					return rows.get(row);
				}

				public void add{{entity.name}}({{entity.name}} {{entity.name|lower}}) {
					rows.add({{entity.name|lower}});
					int ultimoIndice = getRowCount() - 1;
					fireTableRowsInserted(ultimoIndice, ultimoIndice);
				}
				
				public void remove{{entity.name}}(int indiceLinha) {
					rows.remove(indiceLinha);
					fireTableRowsDeleted(indiceLinha, indiceLinha);
				}
				
				public void alterar{{entity.name}}(int indiceLinha, {{entity.name}} {{entity.name|lower}}) {
					{% for property in entity.properties %}
					rows.get(indiceLinha).set{{property.name}}({{entity.name|lower}}.get{{property.name}}());		
					{% endfor %}
					fireTableDataChanged();
				}
				
				//real name listade....
				public void add{{entity.name}}List(List<{{entity.name}}> {{entity.name|lower}}List) {
					int indice = getRowCount();
					rows.addAll({{entity.name}}s);
					fireTableRowsInserted(indice, indice + {{entity.name}}s.size());
				}
				
				public int retornarIndice({{entity.name}} {{entity.name|lower}}) {
					return rows.indexOf({{entity.name}});
				}
				
				// Remove todos os registros.
				public void limpar() {
					rows.clear();

					// Notifica a mudança.
					fireTableDataChanged();
				}
}
//#endif