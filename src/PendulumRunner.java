import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * Sets up the window to set parameters
 * Adds the interface in the window
 * @author 06ahmedA
 *
 */
public class PendulumRunner 
{

	
	public static void main(String[] args) 
	{
		JFrame myFrame = new JFrame();
		PendulumInterface myPendulum = new PendulumInterface();
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		myFrame.add(myPendulum);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setLocation(0, 0);
		myFrame.setSize(screenWidth, screenHeight);
		myFrame.setTitle("Pendulum Simulator");
		myFrame.setResizable(false);
		myFrame.setVisible(true);
		
	}

}
