import java.awt.Color;
import java.awt.Graphics;

import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRectangle;

/*
 * 
 * @author Miguel
 */

public class GamePiece extends GObject
{
	private int x;
	private int y;
	private int size;
	private Color color;

	public GamePiece(int x, int y, int size, Color color)
	{
		super();
		this.x = x;
		this.y = y;
		this.size = size;
		this.color = color;
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
