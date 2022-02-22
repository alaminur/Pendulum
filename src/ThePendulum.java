import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Where all of the pendulums parameters are set
 * Updates the position and values of the pendulum according to the values its given
 * @author 06ahmedA
 *
 */
public class ThePendulum 
{
	//Ball parameters
	private double myRadius;
	private double centreX;
	private double centreY;
	
	private BufferedImage ballGraphic;
	private boolean ballLoaded;
	
	
	//String parameters
	private double stringlength;
	private double originX;
	private double originY;
	
	
	//Values
	private double angle = Math.PI / 2;
	double angleAccel;
	double angleVelocity = 0;
	double dt = 0.1;
	
	
	
//________________________________________________________________________________________________________	
	
	/**
	 * For use when the class is setup
	 * The class requires an initial string length and the initial anchor for the pendulum
	 * @param sl initial string length
	 * @param oX string anchors x coordinate
	 * @param oY string anchors y coordinate
	 */
	public ThePendulum(double sl, double oX, double oY)
	{
		try
		{
			ballGraphic = ImageIO.read(new File("glass_sphere.png"));
			ballLoaded = true;
		} 
		catch (IOException e) 
		{
			ballLoaded = false;
		}

		stringlength = sl;
		originX = oX;
		originY = oY;
	}
	
	/**
	 * Used for changing the string length without the use of the constructor
	 * @param sl how long the string is, measured in pixels
	 */
	public void setStringlength(double sl)
	{
		stringlength = sl;
	}
	
	/**
	 * Used for setting the origins (x position) of the string (the anchor)
	 * @param x = 'x1' of the the string for g.drawLine(x1, y1, x2, y2);
	 */
	public void setOriginX(double x)
	{
		originX = x;
	}
	
	/**
	 * Used for setting the origin (y position) of the string (the anchor)
	 * @param y = 'y1' of the the string for g.drawLine(x1, y1, x2, y2);
	 */
	public void setOriginY(double y)
	{
		originY = y;
	}
	
	/**
	 * Sets the size of the ball
	 * @param Size the value is used for the x and y coordinate to get a sphere
	 */
	public void setBallRadius (float Size)
	{
		myRadius = Size;
	}

//_______________________________________________________________________________________________	
	
	/**
	 * Returns the string length of the current pendulum
	 * @return The length of the string
	 */
	public double getStringLength()
	{
		return stringlength;
	}
	
	/**
	 * Returns the origin of the string (the anchor X position)
	 * @return x value of the anchor
	 */
	public double getOriginX()
	{
		return originX;
	}
	
	/**
	 * Returns the origin of the string (the anchorY X position)
	 * @return y value of the anchor
	 */
	public double getOriginY()
	{
		return originY;
	}
	
	/**
	 * Returns the set X position of the ball
	 */
	public double getXPos()
	{
		return centreX;
	}
	
	/**
	 * Returns the set Y position of the ball
	 */
	public double getYPos()
	{
		return centreY;
	}
	
	/**
	 * Returns the size of the ball 
	 */
	public double getBallRadius()
	{
		return myRadius;
	}
	
	/**
	 * The acceleration value at the anchor
	 * @return the angles acceleration
	 */
	public double getAccel()
	{
		return angleAccel;
	}
	
	/**
	 * The velocity value at the anchor
	 * @return velocity at the point of the anchor
	 */
	public double getVelocity()
	{
		return angleVelocity;
	}
	
	/**
	 * Draws the string and the ball on the screen/ panel.
	 * This uses the values from all of the set methods
	 * If the ball glass_sphere isn't loaded then a default ball will be drawn in its place. 
	 * @param g graphic object to draw with
	 */
	public void draw(Graphics g)
	{
		g.drawLine((int)originX, (int)originY, (int)centreX, (int)centreY);
		if (ballLoaded)
		{
			g.drawImage(ballGraphic, (int)Math.round(centreX-(myRadius)), (int)Math.round(centreY-(myRadius)), (int)Math.round(myRadius*2), (int)Math.round(myRadius*2), null);
		}
			else
		{
			g.setColor(Color.BLACK);
			g.fillOval((int)Math.round(centreX-(myRadius)), (int)Math.round(centreY-(myRadius)), (int)Math.round(myRadius*2), (int)Math.round(myRadius*2));
		}
		
		
	}
	
	
//________________________________________________________________________________________	
	
	
	/**
	 * Updates the position of the pendulum
	 * whilst adding drag to the pendulum
	 */
	public void updatePosition()
	{
		centreX = (int)originX - (int) (Math.sin(angle) * stringlength);
		centreY = (int)originY + (int) (Math.cos(angle) * stringlength);
		angleAccel = (9.81 / stringlength * Math.sin(angle));
		angleAccel = angleAccel * (Math.abs(centreX - originX) / stringlength);
		angleVelocity -= (angleAccel * dt);
		angle += angleVelocity * dt;
		
		if ((angleAccel < 0.00001d) && (angleAccel > -0.00001d))
		{
			angleVelocity *= 0.985;
		}
		
		
		
		
		
	}
}
