import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Adds the graph coordinates into an array
 * The array is drawn onto the panel when this class is called
 * Scrolls the graph along when part of the array is full
 * @author 06ahmedA
 *
 */
public class GraphPanel extends JPanel implements ActionListener
{
	private int MAXSIZE = 10000;
	private int XSCALE = 5;
	private int YSCALE = 6;
	private double myXVector;
	private double myYVector;
	private double SCROLLER;
	
	private Coordinates[] VelGraph = new Coordinates[MAXSIZE];
	private Coordinates[] AccGraph = new Coordinates[MAXSIZE];
	private double gVelocity;
	private double gAccel;
	private int CURRENTSIZE;
	
	private Timer myTimer = new Timer(1, this);
	
	/**
	 * Initialises every cell of the array
	 * Starts the timer, sets the action command to "Timer"
	 */
	public GraphPanel()
	{
		myTimer.start();
		myTimer.setActionCommand("Timer");
		
		for (int i = 0; i < MAXSIZE; i++)
		{
			VelGraph[i] = new Coordinates();
			AccGraph[i] = new Coordinates();
		}
		
	}
	
	/**
	 * Sets the value for gVelocity in the class
	 * @param vel the value set by this method which gVelocity is then set to
	 */
	public void setVelocity(double vel)
	{
		gVelocity = vel;
	}
	/**
	 * sets the value for gAccel in the class
	 * @param acc the value set by the method which gAccel is then set to
	 */
	public void setAcceleration(double acc)
	{
		gAccel = acc;
	}
	/**
	 * The second value for the graphs scrolling function
	 * If this value is zero the graph isn't scrolling
	 * @param scr
	 */
	public void changeScroller(double scr)
	{
		SCROLLER += scr;
	}
	
	/**
	 * Call to set the initial x and y coordinate of the graph
	 */
	public void setupGraph()
	{
		myXVector = this.getWidth();
		myYVector = this.getHeight();
	}
	/**
	 * Controls whether the timer is running or stopped
	 * If called when running it will stop the timer
	 * If called when stopped it will start the timer
	 */
	public void startstop()
	{
		if (myTimer.isRunning())
		{	myTimer.stop(); }
		else
		{	myTimer.start(); }
	}
	/**
	 * Sets the values of the graph back to default values
	 * Re-initialises the array
	 * Sets the value for the scroll displacement to zero
	 * sets the size of the used parts of the array to zero
	 */
	public void reset()
	{
		for (int i = 0; i < MAXSIZE; i++)
		{
			VelGraph[i] = new Coordinates();
			AccGraph[i] = new Coordinates();
		}
		SCROLLER = 0;
		CURRENTSIZE = 0;
	}
	/**
	 * Every time the timer is recalled this method is called
	 * Sets the current point of the array to values of gVelocity and gAccel
	 * Repaints the class
	 * Handles when to scroll the graph 
	 */
	public void actionPerformed(ActionEvent a) 
	{
		if (a.getActionCommand().equals("Stop"))
		{
			this.startstop();
		}
		else if (a.getActionCommand().equals("Reset"))
		{
			this.reset();
		}
		else if (a.getActionCommand().equals("Timer"))
		{
			if (CURRENTSIZE < MAXSIZE)
			{
				VelGraph[CURRENTSIZE].setXCoord(CURRENTSIZE); 
				VelGraph[CURRENTSIZE].setYCoord(gVelocity);

				AccGraph[CURRENTSIZE].setXCoord(CURRENTSIZE);
				AccGraph[CURRENTSIZE].setYCoord(gAccel);
				CURRENTSIZE++; 
				
			}
			if (SCROLLER < 0)
				SCROLLER = 0;
			if (SCROLLER > 1500)
				SCROLLER = 1500;
			if((CURRENTSIZE > 5000) && (CURRENTSIZE < MAXSIZE-10))
			{
				SCROLLER += 0.2;
			}
			repaint();
			
		}
	}
	
	/**
	 * Draws the axis of the graph
	 * Draws every point of the array every time this is called
	 */
	public void paint(Graphics g)
	{
		myXVector = this.getWidth()*0.05d;
		myYVector = this.getHeight()*0.5d;
		super.paint(g);
		
		int x1 = (int) myXVector;
		int y1 = (int) (this.getHeight()*0.1);
		int x2 = (int) myXVector;
		int y2 = (int) (this.getHeight()*0.9);
		g.drawLine(x1, y1, x2, y2);
		
		x1 = (int) myXVector;
		y1 = (int) myYVector;
		x2 = (int) (this.getWidth()*0.9);
		y2 = (int) myYVector;
		g.drawLine(x1, y1, x2, y2);
		
		
		myXVector -= SCROLLER;
		g.setColor(Color.blue);
		for (int i = 15; i < CURRENTSIZE; i++)
		{
			x1 = (int)(myXVector + (VelGraph[i - 15].getXCoord() / XSCALE));
			y1 = (int)(myYVector + (VelGraph[i - 1].getYCoord() * YSCALE));
			x2 = (int)(myXVector + (VelGraph[i].getXCoord() / XSCALE));
			y2 = (int)(myYVector + (VelGraph[i].getYCoord() * YSCALE));
			if ((x1 >= this.getWidth()*0.05d) && (x2 <= this.getWidth()*0.9))
			g.drawLine(x1, y1, x2, y2);
		}
		g.setColor(Color.red);
		for (int i = 15; i < CURRENTSIZE; i++)
		{
			x1 = (int)(myXVector + (AccGraph[i - 15].getXCoord() / XSCALE));
			y1 = (int)(myYVector + (AccGraph[i - 1].getYCoord() * YSCALE));
			x2 = (int)(myXVector + (AccGraph[i].getXCoord() / XSCALE));
			y2 = (int)(myYVector + (AccGraph[i].getYCoord() * YSCALE));
			if ((x1 >= this.getWidth()*0.05d) && (x2 <= this.getWidth()*0.9))
			g.drawLine(x1, y1, x2, y2);
		}
	
		
	}
}
