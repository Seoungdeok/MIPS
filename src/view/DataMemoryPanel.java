/*
 * TCSS 372 – Spring 2020
 * Project - MIPS Simulator
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;
import model.DataMemory;

/**
 * Data memory panel which displays the data's addresses and bit-strings.
 *
 * @author Seoungdeok Jeon
 * @version Spring 2020
 */
public class DataMemoryPanel extends JPanel {

	private JTable myTable;
	private DataMemoryTableModel myDataTableModel;
	private RowColorRenderer myRenderer;

    /**
     * Constructs a DataMemoryPanel.
     */
	public DataMemoryPanel() {
		myDataTableModel = new DataMemoryTableModel();
		myTable = new JTable(myDataTableModel);
		myRenderer = new RowColorRenderer();
		
		// set up renderer
		myRenderer.setHorizontalAlignment(JLabel.CENTER );
		myTable.getColumnModel().getColumn(0).setCellRenderer(myRenderer);
		myTable.getColumnModel().getColumn(1).setCellRenderer(myRenderer);

		TableColumnModel columnModel = myTable.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(200);
		columnModel.getColumn(1).setPreferredWidth(800);
		JScrollPane scrollpane = new JScrollPane(myTable);
		scrollpane.setPreferredSize(new Dimension(1000, 300));

		setBorder(BorderFactory.createTitledBorder( BorderFactory.createEmptyBorder(25, 25, 25, 25), "Data Memory"));
		setLayout(new BorderLayout());
		add(scrollpane, BorderLayout.CENTER);
	}

	/**
	 * Set data memory on myDataTableModel.
	 * 
	 * @param theDataMemory the data memory to be set
	 */
	public void setDataMemory(DataMemory theDataMemory) {
		myDataTableModel.setDataMemory(theDataMemory);
	}

	/**
	 * Highlight the used data on the panel.
	 * 
	 * @param theDataAddress the address of used data
	 */
	public void highlightData(int theDataAddress) {
		// make all cells' color to white before setting.
		for (int rowIndex = 0; rowIndex < myDataTableModel.getRowCount(); rowIndex++)
		{
			myRenderer.setRowColor(rowIndex, Color.WHITE);
		}
		if(theDataAddress != -1) {
			myRenderer.setRowColor(theDataAddress/4, Color.YELLOW);
		}
	}
	
	/**
	 * Update the change to myDataTableModel.
	 */
	public void refresh() {
		myDataTableModel.fireTableDataChanged();
	}
}

