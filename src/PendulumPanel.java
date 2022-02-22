import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Adds the pendulum to the screen
 * Sends initial values to set up the pendulum
 * Every time this is called the pendulum is drawn and updated
 * @author 06ahmedA
 *
 */
public class PendulumPanel extends JPanel implements ActionListener
{
	private ThePendulum myPen = new ThePendulum(300, 0, 0);
	private Timer myTimer = new Timer(1, this);
	
	public PendulumPanel()
	{
		myTimer.start();
		myTimer.setActionCommand("Timer");
	}
	
	public ThePendulum getPendulum()
	{
		return myPen;
	}
	/**
	 * Initiates all the default values for the pendulum
	 * e.g. the balls initial radius (for the ball to be visible on screen)
	 */
	public void setupPendulum()
	{
		myPen.setBallRadius(35);
		myPen.setOriginX(0);
		myPen.setOriginY(0);
	}
	/**
	 * When called this checks if the class's timer is running
	 * If the timer is running then it will stop it
	 * Else if the timer is stopped it will start it 
	 */
	public void startstop()
	{
		if (myTimer.isRunning())
		{	myTimer.stop(); }
		else
		{	myTimer.start(); }
	}
	
	/**
	 * When called this method recalls the method to reset the pendulums initial values
	 * This inputs the current values into a new pendulum which will in turn delete the old pendulum
	 */
	public void reset()
	{
		myPen = new ThePendulum(myPen.getStringLength(), myPen.getOriginX(), myPen.getOriginY());
		this.setupPendulum();
	}
	
	/**
	 * Calls the draw method from ThePendulum class which draws the pendulum
	 */
	public void paint(Graphics g)
	{
		super.paint(g);
		myPen.draw(g);
		
	}	
	
	/**
	 * Uses an action listener to call this method every time the class is called
	 */
	public void actionPerformed(ActionEvent s) 
	{
		if (s.getActionCommand().equals("Stop"))
		{
			this.startstop();
		}
		else if (s.getActionCommand().equals("Reset"))
		{
			this.reset();
			
		}
		else if (s.getActionCommand().equals("Timer"))
		{
			myPen.setOriginX(this.getWidth()/2);
			myPen.setOriginY(this.getHeight()*0.075d);			
			myPen.updatePosition();
			repaint();
		}
	}
}
