//#if ${Reviewer} == "T"
package {{systemName|lower}}.ev.table;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ReviewerTableRender extends DefaultTableCellRenderer {
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
				row, column);
		//Cor quando for selecionado, e quando não tiver selecionado.
		if (row % 2 == 0) {
			setBackground(Color.LIGHT_GRAY);
		} else {
			setBackground(null);
		}
		if (isSelected) {
			setBackground(Color.GREEN);
		}
		//Tamanho das Colunas
		table.getColumnModel().getColumn(0).setMaxWidth(137);
		table.getColumnModel().getColumn(1).setMaxWidth(137);
		table.getColumnModel().getColumn(2).setMaxWidth(137);
		table.getColumnModel().getColumn(3).setMaxWidth(137);
		table.getColumnModel().getColumn(4).setMaxWidth(137);

		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		
		{% for p in data.option.properties %}
		table.getColumnModel().getColumn({{loop.index + 4}}).setMaxWidth(13);
		table.getColumnModel().getColumn({{loop.index + 4}}).setResizable(false);
		{% endfor %}
		
		{% if data.option.categories|length > 0 %}
		table.getColumnModel().getColumn({{4 + data.option.properties|length}}).setMaxWidth(13);
		table.getColumnModel().getColumn({{4 + data.option.properties|length}}).setResizable(false);
		{% endif %}
		
		{% for p in extraData.option.properties %}
		table.getColumnModel().getColumn({{4+loop.index + data.option.categories|length + data.option.properties|length}}).setMaxWidth(13);
		table.getColumnModel().getColumn({{4+loop.index + data.option.categories|length + data.option.properties|length}}).setResizable(false);
		{% endfor %}
		
		{% if extraData.option.categories|length > 0 %}
		table.getColumnModel().getColumn({{4 + data.option.categories|length + data.option.properties|length + extraData.option.properties|length}}).setMaxWidth(13);
		table.getColumnModel().getColumn({{4 + data.option.categories|length + data.option.properties|length + extraData.option.properties|length}}).setResizable(false);
		{% endif %}
		
		//Texto Centralizado nas Colunas
		this.setHorizontalAlignment(CENTER);
		return this;
	}
}
//#endif