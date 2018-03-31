package thewetbandits;

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
	private BufferedImage img;

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

	public GamePiece(int x, int y, int size, BufferedImage img)
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

	@Override
	public GRectangle getBounds()
	{
		return new GRectangle(x, y, size, size);
	}

}
