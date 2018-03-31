/**
 * @author JacobFaulk
 * Created Mar 30, 2018
 */

package thewetbandits;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.graphics.GRectangle;

public class Board extends GObject
{
	private static final Color YELLOW = new Color(250, 240, 66);
	private static final Color GREEN = new Color(67, 153, 58);
	private static final Color BLUE = new Color(24, 30, 219);
	private Random rand;
	private GamePiece[][] board;
	private int pieceSize;
	private int boardLength;
	private int spaceSize;
	private GRectangle border;
	private int screenSize;

	public Board(int screenSize, int boardLength)
	{
		super();
		this.screenSize = screenSize;
		this.boardLength = boardLength;
		this.spaceSize = screenSize / (boardLength + 2);
		this.pieceSize = (spaceSize / 7) * 5;
		rand = new Random();
		border = new GRectangle(spaceSize - ((spaceSize - pieceSize) / 2), spaceSize - ((spaceSize - pieceSize) / 2),
				spaceSize * boardLength, spaceSize * boardLength);
		board = new GamePiece[boardLength][boardLength];
		for(int r = 0; r < board.length; r++)
		{
			for(int c = 0; c < board[0].length; c++)
			{
				board[r][c] = new GamePiece(spaceSize * (r + 1), spaceSize * (c + 1), pieceSize);
			}
		}
	}

	@Override
	public GRectangle getBounds()
	{
		return border;
	}

	@Override
	public void paint(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.drawRect(spaceSize - ((spaceSize - pieceSize) / 2), spaceSize - ((spaceSize - pieceSize) / 2),
				spaceSize * boardLength, spaceSize * boardLength);
		for(int r = 0; r < board.length; r++)
			for(int c = 0; c < board[0].length; c++)
				board[r][c].paint(g);
	}

}
