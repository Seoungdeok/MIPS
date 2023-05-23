/*
 * TCSS 372 – Spring 2020
 * Project - MIPS Simulator
 */
package view;

import java.awt.Color;
import java.awt.Component;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Table cell renderer which used to set the row color.
 * 
 * @author Seoungdeok Jeon
 * @version Spring 2020
 */
public class RowColorRenderer extends DefaultTableCellRenderer {
	private Map<Integer, Color> mapColors;

	/**
	 * Constructs RowColorRenderer.
	 */
	public RowColorRenderer() {
		mapColors = new HashMap<Integer, Color>();
	}

	/**
	 * Set color of the row.
	 * 
	 * @param theRow the row number
	 * @param theColor the color
	 */
	public void setRowColor(int theRow, Color theColor) {
		mapColors.put(theRow, theColor);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object obj, boolean isSelected, boolean hasFocus, int row, int column) {

		Component cell = super.getTableCellRendererComponent(table, obj, isSelected, hasFocus, row, column);
		Color color = mapColors.get(row);
		if (color != null) {
			cell.setBackground(color);
		} else {
			cell.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
		}
		return cell;
	}
}