package thewetbandits;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRectangle;

/*
 * 
 * @author Miguel
 */

public class GamePiece extends GObject
{
	private static Random rand = new Random();
	private static final Color YELLOW = new Color(250, 240, 66);
	private static final Color GREEN = new Color(67, 153, 58);
	private static final Color BLUE = new Color(24, 30, 219);
	private int x;
	private int y;
	private int size;
	private Color color;
	private GImage img;

	public GamePiece(int x, int y, int size, Color color)
	{
		super();
		this.x = x;
		this.y = y;
		this.size = size;
		this.color = color;
	}

	public GamePiece(int x, int y, int size)
	{
		super();
		this.x = x;
		this.y = y;
		this.size = size;
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

	public void paint(Graphics g)
	{
		// Starting colors for the gamepiece will be blue, red, green, and yellow.
		// More to come
		g.setColor(color);
		g.fillOval(x, y, size, size);
	}

	@Override
	public GRectangle getBounds()
	{
		return new GRectangle(x, y, size, size);
	}

}
