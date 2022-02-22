import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

/**
 * Adds the given panels to the screen 
 * Adds any buttons and labels to the screen
 * Holds the key listeners for key controls
 * 
 * @author 06ahmedA
 *
 */
public class PendulumInterface extends JPanel implements ActionListener, KeyListener
{
	private PendulumPanel myPendulum = new PendulumPanel();
	private GraphPanel myGraph = new GraphPanel();
	private GridBagConstraints gbc = new GridBagConstraints();
	private Timer myTimer = new Timer(1, this);
	
	private int counter;
	
	private JButton myButton;
	private JLabel myAccel;
	private JLabel myVelocity;
	
	/**
	 * Places every button and panel into the window with the set parameters
	 * Uses a grid bag layout
	 */
	public PendulumInterface()
	{
		this.setLayout(new GridBagLayout());
		
		myButton = new JButton("[S]tart / Stop");
		gbc.fill = GridBagConstraints.BOTH; 
		gbc.gridheight = 1;
		gbc.weighty = 0.5;
		gbc.anchor = gbc.WEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		myButton.addActionListener(myPendulum);
		myButton.addActionListener(myGraph);
		myButton.setActionCommand("Stop");
		myButton.setFocusable(false);
		this.add(myButton, gbc);
//_______________________________________________________________________		
		
		myButton = new JButton("[R]eset");
		gbc.gridx = 0;
		gbc.gridy = 1;
		
		myButton.addActionListener(myPendulum);
		myButton.addActionListener(myGraph);
		myButton.setActionCommand("Reset");
		myButton.setFocusable(false);	
		this.add(myButton, gbc);
//_______________________________________________________________________	
		
		myVelocity = new JLabel("Velocity");
		myVelocity.setForeground(Color.BLUE);
		myVelocity.setHorizontalAlignment(JLabel.CENTER);
		myVelocity.setFont(new Font(null, Font.BOLD, 17));
		gbc.gridx = 0;
		gbc.gridy = 3;
		
		this.add(myVelocity, gbc);
//_______________________________________________________________________	

		myAccel = new JLabel("Acceleration");
		myAccel.setForeground(Color.RED);
		myAccel.setHorizontalAlignment(JLabel.CENTER);
		myAccel.setFont(new Font(null, Font.BOLD, 17));
		gbc.gridx = 0;
		gbc.gridy = 4;
		
		this.add(myAccel, gbc);
//_______________________________________________________________________
		
		//gbc.insets = new Insets(0,0,0,0);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 1; 
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.ipadx = 500;
		gbc.ipady = 50;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		gbc.gridheight = 2;
		this.add(myPendulum, gbc);
//______________________________________________________________________		

		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.ipadx = 0;
		gbc.ipady = 0;
		gbc.gridheight = 2;
		
		this.add(myGraph, gbc);
//______________________________________________________________________		
		
		myPendulum.setupPendulum();
		myGraph.setupGraph();
		addKeyListener(this);
		this.setFocusable(true);
		myTimer.start();
	}
	
	/**
	 * When this is called it sends the values for velocity and acceleration across the class it needs
	 * Converts the values of velocity and acceleration to string
	 */
	public void actionPerformed(ActionEvent a) 
	{
			myGraph.setVelocity(myPendulum.getPendulum().getVelocity()*100*-1);
			myGraph.setAcceleration(myPendulum.getPendulum().getAccel()*525*-1);
		counter++;
		if (counter >= 500)
		{
			myVelocity.setText(Double.toString(Math.round(myPendulum.getPendulum().getVelocity()*100)));
			myAccel.setText(Double.toString(Math.round(myPendulum.getPendulum().getAccel()*500)));
		}
	}

	/**
	 * The S key will pause timers across classes
	 * The R key will call the reset classes across classes
	 * The Left/Right directional key will change the value for the scrolling in graph panel
	 */
	public void keyPressed(KeyEvent k) 
	{
		if (k.getKeyCode() == KeyEvent.VK_S)
		{
			myGraph.startstop();
			myPendulum.startstop();
			
		}
		else if (k.getKeyCode() == KeyEvent.VK_R)
		{
			myGraph.reset();
			myPendulum.reset();
		}
		else if (k.getKeyCode() == KeyEvent.VK_LEFT)
		{
			myGraph.changeScroller(5);
		}
		else if (k.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			myGraph.changeScroller(-5);
		}
	}

	@Override
	public void keyReleased(KeyEvent k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent k) {
		// TODO Auto-generated method stub
		
	}
}
