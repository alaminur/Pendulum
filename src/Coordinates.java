import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Holds an x and y coordinate which can be set or returned
 * @author 06ahmedA
 *
 */
public class Coordinates 
{
	private double myYCoord;
	private double myXCoord;
	
	/**
	 * Sets the x coordinate for this class
	 * @param x a double value, measured in pixels
	 */
	public void setXCoord(double x)
	{
		myXCoord = x;
	}
	
	/**
	 * Sets the y coordinate for this class
	 * @param y a double value, measured in pixels
	 */
	public void setYCoord(double y)
	{
		myYCoord = y;
	}
	
	/**
	 * Returns the x coordinate of this class
	 * @return A double value, measured in pixels
	 */
	public double getXCoord()
	{
		return myXCoord;
	}
	
	/**
	 * Returns the y coordinate of this class
	 * @return A double value, measured in pixels
	 */
	public double getYCoord()
	{
		return myYCoord;
	}
}
