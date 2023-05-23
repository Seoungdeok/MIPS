/*
 * TCSS 372 – Spring 2020
 * Project - MIPS Simulator
 */
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JToolBar;

/**
 * Tool bar for MIPS simulator which contains all the functionalities.
 * 
 * @author Seoungdeok Jeon
 * @version Spring 2020
 */
public class ToolBar extends JToolBar {
	private JButton runOneStepBtn;
	private JCheckBox showInDecimalCheckBox;
	private ToolBarListener myToolBarListener;
	
	/**
	 * Constructs a tool bar.
	 */
	public ToolBar() {
		runOneStepBtn = new JButton("run one step");
		showInDecimalCheckBox = new JCheckBox("show in decimal");
		
		runOneStepBtn.setEnabled(false);
		runOneStepBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (myToolBarListener != null) {
					myToolBarListener.runOneStepEventOccurred();
				}
			}
			
		});
		
		showInDecimalCheckBox.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent event) {
		        JCheckBox cb = (JCheckBox) event.getSource();
		        if (cb.isSelected()) {
		        	if (myToolBarListener != null) {
						myToolBarListener.showInDecimalEventOccurred();
					}
		        } else {
		        	if (myToolBarListener != null) {
						myToolBarListener.showInBinaryEventOccurred();
					}
		        }
		    }
		});
		
		add(runOneStepBtn);
		add(showInDecimalCheckBox);
	}

	/**
	 * Set listener for tool bar.
	 * 
	 * @param theListener the tool bar listener
	 */
	public void setToolBarListener(ToolBarListener theListener) {
		myToolBarListener = theListener;
	}
	
	/**
	 * Get runOneStepBtn.
	 * 
	 * @return runOneStepBtn
	 */
	public JButton getRunOneStepBtn() {
		return runOneStepBtn;
	}
}
