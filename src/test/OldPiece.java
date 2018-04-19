package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import acm.graphics.GObject;
import acm.graphics.GRectangle;

/*
 * @author Miguel
 * @author Jacob Faulk
 */

public class OldPiece extends GObject
{
	private static final long serialVersionUID = 7820255406765396471L;
	private static Random rand = new Random();
	private static final Color YELLOW = new Color(250, 240, 66);
	private static final Color GREEN = new Color(67, 153, 58);
	private static final Color BLUE = new Color(24, 30, 219);
	private int x;
	private int y;
	private int size;
	private Color color;
	private BufferedImage img;

	/**
	 * Constructor where color is provided and image is null
	 * 
	 * @param x
	 *            the x position of the piece
	 * @param y
	 *            the y position of the piece
	 * @param size
	 *            the width and height of the piece
	 * @param color
	 *            the color of the piece
	 */
	public OldPiece(int x, int y, int size, Color color)
	{
		super();
		this.x = x;
		this.y = y;
		this.size = size;
		this.color = color;
	}

	/**
	 * Constructor where color and image are randomly chosen out of a predefined set
	 * 
	 * @param x
	 *            the x position of the piece
	 * @param y
	 *            the y position of the piece
	 * @param size
	 *            the width and height of the piece
	 */
	public OldPiece(int x, int y, int size)
	{
		super();
		this.x = x;
		this.y = y;
		this.size = size;
		try
		{
			switch(rand.nextInt(4))
			{
			case 0:
				this.color = Color.RED;
				this.img = ImageIO.read(new File("red_gem.png"));
				break;
			case 1:
				this.color = BLUE;
				this.img = ImageIO.read(new File("blue_gem.png"));
				break;
			case 2:
				this.color = GREEN;
				this.img = ImageIO.read(new File("green_gem.png"));
				break;
			default:
				this.img = ImageIO.read(new File("yellow_gem.png"));
				this.color = YELLOW;
			}
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Constructor where image is provided and color is randomly chosen in case img
	 * is null
	 * 
	 * @param x
	 *            the x position of the piece
	 * @param y
	 *            the y position of the piece
	 * @param size
	 *            the width and height of the piece
	 * @param img
	 *            the image that the piece displays when paint() is called
	 */
	public OldPiece(int x, int y, int size, BufferedImage img)
	{
		super();
		this.x = x;
		this.y = y;
		this.size = size;
		this.img = img;
		switch(rand.nextInt(4))
		{
		case 0:
			this.color = Color.RED;
			break;
		case 1:
			this.color = BLUE;
			break;
		case 2:
			this.color = GREEN;
			break;
		default:
			this.color = YELLOW;
		}
	}

	public void reposition(int x, int y, int size)
	{
		this.x = x;
		this.y = y;
		this.size = size;
	}

	/**
	 * draws the GamePiece in its position with the specified image if img is null,
	 * its backup color is displayed
	 * 
	 * @param g
	 *            the graphics element that draws the piece
	 */
	public void paint(Graphics g)
	{
		if(img == null)
		{
			g.setColor(color);
			g.fillOval(x, y, size, size);
		}
		else
		{
			g.drawImage(img, x, y, size, size, null);
		}
	}

	/**
	 * returns a GRectangle detailing the bounds of the piece
	 * 
	 * @return the bounding box of the piece
	 */
	@Override
	public GRectangle getBounds()
	{
		return new GRectangle(x, y, size, size);
	}

}
