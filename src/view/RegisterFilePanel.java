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
import model.RegisterFile;

/**
 * Register file panel which displays the register 
 * names, numbers, and bit-strings.
 *
 * @author Seoungdeok Jeon
 * @version Spring 2020
 */
public class RegisterFilePanel extends JPanel {

	private JTable myTable;
	private RegisterFileTableModel myRegisterFileTableModel;
	private RowColorRenderer myRenderer;
	
	/**
	 * Constructs a RegisterFilePanel.
	 */
	public RegisterFilePanel() {
		myRegisterFileTableModel = new RegisterFileTableModel();
		myTable = new JTable(myRegisterFileTableModel);
		myRenderer = new RowColorRenderer();
		
		// set up renderer
		myRenderer.setHorizontalAlignment(JLabel.CENTER );
		myTable.getColumnModel().getColumn(0).setCellRenderer(myRenderer);
		myTable.getColumnModel().getColumn(1).setCellRenderer(myRenderer);
		myTable.getColumnModel().getColumn(2).setCellRenderer(myRenderer);
		
		TableColumnModel columnModel = myTable.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(75);
		columnModel.getColumn(1).setPreferredWidth(75);
		columnModel.getColumn(2).setPreferredWidth(300);
		JScrollPane scrollpane = new JScrollPane(myTable);
		scrollpane.setPreferredSize(new Dimension(450, 300));
		setBorder(BorderFactory.createTitledBorder( BorderFactory.createEmptyBorder(25, 25, 25, 25), "Register File"));
		setLayout(new BorderLayout());
		add(scrollpane, BorderLayout.CENTER);
	}
	
	/**
	 * Set register file on myRegisterFileTableModel.
	 * @param theRegisterFile the register file to be set
	 */
	public void setRegisterFile(RegisterFile theRegisterFile) {
		myRegisterFileTableModel.setRegisterFile(theRegisterFile);
	}
	
	/**
	 * Highlight the used registers on the panel.
	 * 
	 * @param theRegisterNumbers array of used register numbers
	 */
	public void highlightRegisters(int[] theRegisterNumbers) {
		// make all cells' color to white before setting.
		for (int rowIndex = 0; rowIndex < myRegisterFileTableModel.getRowCount(); rowIndex++)
		{
			myRenderer.setRowColor(rowIndex, Color.WHITE);
		}
		myRenderer.setRowColor(theRegisterNumbers[0], Color.YELLOW);
		myRenderer.setRowColor(theRegisterNumbers[1], Color.YELLOW);
		myRenderer.setRowColor(theRegisterNumbers[2], Color.YELLOW);
	}
	
	/**
	 * Update the change to myRegisterFileTableModel.
	 */
	public void refresh() {
		myRegisterFileTableModel.fireTableDataChanged();
	}
	
}
