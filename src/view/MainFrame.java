/*
 * TCSS 372 – Spring 2020
 * Project - MIPS Simulator
 */
package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import controller.Controller;

/**
 * MainFrame provides the user interface for a MIPS Simulator program.
 * 
 * @author Seoungdeok Jeon
 * @version Spring 2020
 */
public class MainFrame extends JFrame {

	private final InstructionMemoryPanel myInstructionMemoryPanel;
	private final DataMemoryPanel myDataMemoryPanel;
	private final RegisterFilePanel myRegisterFilePanel;
	private final ToolBar myToolBar;
	private final Controller myController;
	
	/**
	 * Constructs a new MIPS Simulator GUI.
	 */
	public MainFrame() {
		super("MIPS Simulator");

		myInstructionMemoryPanel = new InstructionMemoryPanel();
		myDataMemoryPanel = new DataMemoryPanel();
		myRegisterFilePanel = new RegisterFilePanel();
		myToolBar = new ToolBar();
		myController = new Controller();

		// Set up panel contents based on the model.
		myInstructionMemoryPanel.setInstructionMemory(myController.getInstructionMemory());
		myRegisterFilePanel.setRegisterFile(myController.getRegisterFile());
		myDataMemoryPanel.setDataMemory(myController.getDataMemory());

		// Create functionalities for the ToolBar.
		myToolBar.setToolBarListener(new ToolBarListener() {
			public void runOneStepEventOccurred() {
				try {
					myController.runOneStep();
					myInstructionMemoryPanel.highlightInstruction(myController.getUsedInstructionAddress());
					myDataMemoryPanel.highlightData(myController.getUsedDataAddress());
					myRegisterFilePanel.highlightRegisters(myController.getUsedRegisters());
					myInstructionMemoryPanel.refresh();
					myDataMemoryPanel.refresh();
					myRegisterFilePanel.refresh();
				} catch(NullPointerException e) {
					JOptionPane.showMessageDialog(MainFrame.this, "You have reached the end of the Instructions!", "Error",
							JOptionPane.ERROR_MESSAGE);
				} catch(IOException e) {
					JOptionPane.showMessageDialog(MainFrame.this, "Address is not aligned on word boundary!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
			public void showInDecimalEventOccurred() {
				myController.showInDecimal(true);
				myInstructionMemoryPanel.refresh();
				myDataMemoryPanel.refresh();
				myRegisterFilePanel.refresh();
			}
			public void showInBinaryEventOccurred() {
				myController.showInDecimal(false);
				myInstructionMemoryPanel.refresh();
				myDataMemoryPanel.refresh();
				myRegisterFilePanel.refresh();
			}
		});

		setJMenuBar(createMenuBar());
		setLayout(new BorderLayout());
		add(myInstructionMemoryPanel, BorderLayout.CENTER);
		add(myRegisterFilePanel, BorderLayout.EAST);
		add(myDataMemoryPanel, BorderLayout.SOUTH);
		add(myToolBar, BorderLayout.NORTH);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	/**
	 * Returns a JMenuBar with importing file functionality.
	 * (File must contain MIPS machine code instructions)
	 * 
	 * @return a JMenuBar
	 */
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem importInstructionItem = new JMenuItem("Import Instructions...");
		JMenuItem importDataItem = new JMenuItem("Import Data...");
		JMenuItem exitItem = new JMenuItem("Exit");
		fileMenu.add(importInstructionItem);
		fileMenu.addSeparator();
		fileMenu.add(importDataItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		menuBar.add(fileMenu);

		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int action = JOptionPane.showConfirmDialog(MainFrame.this,
						"Do you really want to exit the application?", "Confrim Exit", JOptionPane.OK_CANCEL_OPTION);
				if (action == JOptionPane.OK_OPTION) {
					System.exit(0);
				}
			}
		});

		importInstructionItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.addChoosableFileFilter(new FileFilter() {
				    public String getDescription() {
				        return "Txt File (*.txt)";
				    }
				    public boolean accept(File f) {
				        if (f.isDirectory()) {
				            return true;
				        } else {
				            return f.getName().toLowerCase().endsWith(".txt");
				        }
				    }
				});
				if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					try {
						myController.loadInstructionsFromFile(fileChooser.getSelectedFile());
						myInstructionMemoryPanel.refresh();
						myToolBar.getRunOneStepBtn().setEnabled(true);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(MainFrame.this, "Could not load instructions from file.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		importDataItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.addChoosableFileFilter(new FileFilter() {
				    public String getDescription() {
				        return "Txt File (*.txt)";
				    }
				    public boolean accept(File f) {
				        if (f.isDirectory()) {
				            return true;
				        } else {
				            return f.getName().toLowerCase().endsWith(".txt");
				        }
				    }
				});
				if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					try {
						myController.loadDataFromFile(fileChooser.getSelectedFile());
						myDataMemoryPanel.refresh();
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(MainFrame.this, "Could not load data from file.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		return menuBar;
	}
}
