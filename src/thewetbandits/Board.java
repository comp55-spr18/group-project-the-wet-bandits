/**
 * @author Jacob Faulk
 * Created Mar 30, 2018
 */

package thewetbandits;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Line2D;

import acm.graphics.GObject;
import acm.graphics.GRectangle;

public class Board extends GObject
{
	private GamePiece[][] board;
	private int pieceSize;
	private int boardLength;
	private int spaceSize;
	private GRectangle border;
	private Line2D.Double[] verticalLines;
	private Line2D.Double[] horizontalLines;
	private int screenSize;
	private MatchThreeGame app;

	/**
	 * Constructor for Board specifying the dimensions of the Screen it will reside
	 * in
	 * 
	 * @param screenSize
	 *            the size (in pixels) of the Screen the Board will be added to
	 * @param boardLength
	 *            the length of both sides of the array holding the GamePieces
	 */
	public Board(int screenSize, int boardLength, MatchThreeGame app)
	{
		super();
		this.app = app;
		this.screenSize = screenSize;
		this.boardLength = boardLength;
		this.spaceSize = this.screenSize / (boardLength + 2);
		this.pieceSize = (spaceSize / 7) * 5;
		border = new GRectangle(spaceSize - ((spaceSize - pieceSize) / 2), spaceSize - ((spaceSize - pieceSize) / 2),
				spaceSize * boardLength, spaceSize * boardLength);
		board = new GamePiece[boardLength][boardLength];
		for(int r = 0; r < board.length; r++)
			for(int c = 0; c < board[0].length; c++)
				board[r][c] = new GamePiece(spaceSize * (r + 1), spaceSize * (c + 1), pieceSize);
		verticalLines = new Line2D.Double[boardLength - 1];
		horizontalLines = new Line2D.Double[boardLength - 1];
		for(int i = 2; i <= boardLength; i++)
		{
			verticalLines[i - 2] = new Line2D.Double(spaceSize * i - ((spaceSize - pieceSize) / 2),
					spaceSize - ((spaceSize - pieceSize) / 2), spaceSize * i - ((spaceSize - pieceSize) / 2),
					spaceSize * (boardLength + 1) - ((spaceSize - pieceSize) / 2));
			horizontalLines[i - 2] = new Line2D.Double(spaceSize - ((spaceSize - pieceSize) / 2),
					spaceSize * i - ((spaceSize - pieceSize) / 2),
					spaceSize * (boardLength + 1) - ((spaceSize - pieceSize) / 2),
					spaceSize * i - ((spaceSize - pieceSize) / 2));
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

	public void updateBounds(int screenSize)
	{
		// TODO don't copy code here
		this.screenSize = screenSize;
		this.spaceSize = this.screenSize / (boardLength + 2);
		this.pieceSize = (spaceSize / 7) * 5;
		border.setBounds(spaceSize - ((spaceSize - pieceSize) / 2), spaceSize - ((spaceSize - pieceSize) / 2),
				spaceSize * boardLength, spaceSize * boardLength);
		for(int r = 0; r < board.length; r++)
			for(int c = 0; c < board[0].length; c++)
				board[r][c].reposition(spaceSize * (r + 1), spaceSize * (c + 1), pieceSize);
		for(int i = 2; i <= boardLength; i++)
		{
			verticalLines[i - 2].setLine(spaceSize * i - ((spaceSize - pieceSize) / 2),
					spaceSize - ((spaceSize - pieceSize) / 2), spaceSize * i - ((spaceSize - pieceSize) / 2),
					spaceSize * (boardLength + 1) - ((spaceSize - pieceSize) / 2));
			horizontalLines[i - 2].setLine(spaceSize - ((spaceSize - pieceSize) / 2),
					spaceSize * i - ((spaceSize - pieceSize) / 2),
					spaceSize * (boardLength + 1) - ((spaceSize - pieceSize) / 2),
					spaceSize * i - ((spaceSize - pieceSize) / 2));
		}

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
		for(int i = 0; i < verticalLines.length; i++)
		{
			g.drawLine((int) verticalLines[i].getX1(), (int) verticalLines[i].getY1(), (int) verticalLines[i].getX2(),
					(int) verticalLines[i].getY2());
			g.drawLine((int) horizontalLines[i].getX1(), (int) horizontalLines[i].getY1(),
					(int) horizontalLines[i].getX2(), (int) horizontalLines[i].getY2());
		}
		for(int r = 0; r < board.length; r++)
			for(int c = 0; c < board[0].length; c++)
				board[r][c].paint(g);
	}

}
