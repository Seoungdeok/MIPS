/*
 * TCSS 372 – Spring 2020
 * Project - MIPS Simulator
 */
package application;

import javax.swing.SwingUtilities;
import view.MainFrame;

/**
 * Runs the MIPS Simulator program.
 * 
 * @author Seoungdeok Jeon
 * @version Spring 2020
 */

public class SimulatorMain {
	

    /**
     * Private constructor to prevent construction of instances.
     */
    private SimulatorMain() {
        // do nothing
    }
    
    
    /**
     * Constructs the main GUI window frame.
     * 
     * @param theArgs Command line arguments (ignored).
     */
    
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainFrame();
			}
		});

	}
}
