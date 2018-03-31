package thewetbandits;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.graphics.GRectangle;

/**
 * @author JacobFaulk
 * Created Mar 30, 2018
 */
public class Board extends GObject
{
	private static final Color YELLOW = new Color(250, 240, 66);
	private static final Color GREEN = new Color(67, 153, 58);
	private static final Color BLUE = new Color(24, 30, 219);
	private Random rand = new Random();
	private GamePiece[][] board;
	private int pieceSize;
	private int boardLength;
	private int spaceSize;
	private GRect border;
	
	
	@Override
	public GRectangle getBounds()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void paint(Graphics g)
	{
		// TODO Auto-generated method stub

	}

}
