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
import model.InstructionMemory;

/**
 * Instruction memory panel which displays the instruction 
 * addresses, bit-strings, and assembly code.
 *
 * @author Seoungdeok Jeon
 * @version Spring 2020
 */
public class InstructionMemoryPanel extends JPanel {

	private JTable myTable;
	private InstructionMemoryTableModel myInstructionTableModel;
	private RowColorRenderer myRenderer;

	/**
	 * Constructs a InstructionMemoryPanel.
	 */
	public InstructionMemoryPanel() {
		myInstructionTableModel = new InstructionMemoryTableModel();
		myTable = new JTable(myInstructionTableModel);
		myRenderer = new RowColorRenderer();

		// set up renderer
		myRenderer.setHorizontalAlignment(JLabel.CENTER );
		myTable.getColumnModel().getColumn(0).setCellRenderer(myRenderer);
		myTable.getColumnModel().getColumn(1).setCellRenderer(myRenderer);
		myTable.getColumnModel().getColumn(2).setCellRenderer(myRenderer);

		TableColumnModel columnModel = myTable.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(100);
		columnModel.getColumn(1).setPreferredWidth(300);
		columnModel.getColumn(2).setPreferredWidth(150);
		JScrollPane scrollpane = new JScrollPane(myTable);
		scrollpane.setPreferredSize(new Dimension(550, 300));
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Instruction Memory"));
		setLayout(new BorderLayout());
		add(new JScrollPane(myTable), BorderLayout.CENTER);
	}

	/**
	 * Set instruction memory on myInstructionTableModel.
	 * 
	 * @param InstructionMemory the instruction memory to be set
	 */
	public void setInstructionMemory(InstructionMemory theInstructionMemory) {
		myInstructionTableModel.setInstructionMemory(theInstructionMemory);
	}

	/**
	 * Highlight the used instruction on the panel.
	 * 
	 * @param theInstructionAddress the address of used instruction
	 */
	public void highlightInstruction(int theInstructionAddress) {
		// make all cells' color to white before setting.
		for (int rowIndex = 0; rowIndex < myInstructionTableModel.getRowCount(); rowIndex++)
		{
			myRenderer.setRowColor(rowIndex, Color.WHITE);
		}
		int row = theInstructionAddress/4;
		myRenderer.setRowColor(row, Color.YELLOW);
	}

	/**
	 * Update the change to myInstructionTableModel.
	 */
	public void refresh() {
		myInstructionTableModel.fireTableDataChanged();
	}
}
