/**
 * @author Jacob Faulk
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
	private GamePiece[][] board;
	private int pieceSize;
	private int boardLength;
	private int spaceSize;
	private GRectangle border;
	private int screenSize;

	/**
	 * Constructor for Board specifying the dimensions of the Screen it will reside
	 * in
	 * 
	 * @param screenSize
	 *            the size (in pixels) of the Screen the Board will be added to
	 * @param boardLength
	 *            the length of both sides of the array holding the GamePieces
	 */
	public Board(int screenSize, int boardLength)
	{
		super();
		this.screenSize = screenSize;
		this.boardLength = boardLength;
		this.spaceSize = this.screenSize / (boardLength + 2);
		this.pieceSize = (spaceSize / 7) * 5;
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

	/**
	 * returns the bounding box surrounding the Board
	 * 
	 * @return GRectangle the bounds of the Board
	 */
	@Override
	public GRectangle getBounds()
	{
		return border;
	}

	/**
	 * draws the Board and all of its elements
	 * 
	 * @param g
	 *            the Graphics object used to draw the Board
	 */
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
