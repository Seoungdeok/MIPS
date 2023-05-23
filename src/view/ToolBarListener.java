/*
 * TCSS 372 – Spring 2020
 * Project - MIPS Simulator
 */
package view;

import java.util.EventListener;

/**
 * Interface which contains all functionalities for tool bar.
 * 
 * @author Seoungdeok Jeon
 * @version Spring 2020
 */
public interface ToolBarListener extends EventListener {
	/** Execute a instruction when runOneStepBtn is clicked. */
	public void runOneStepEventOccurred();
	/** Show values in decimal when showInDecimalCheckBox is checked. */
	public void showInDecimalEventOccurred();
	/** Show values in binary when showInDecimalCheckBox is not checked. */
	public void showInBinaryEventOccurred();
}
