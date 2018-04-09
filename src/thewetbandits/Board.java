/**
 * @author Jacob Faulk
 * Created Mar 30, 2018
 */

package thewetbandits;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import acm.graphics.GCompound;
import acm.graphics.GLine;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.graphics.GRectangle;
import test.BetterPiece;
import thewetbandits.utils.ClickAction;
import thewetbandits.utils.Clickable;

public class Board extends GCompound implements Clickable
{
	private BetterPiece[][] board;
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
		this.app = app;
		this.screenSize = screenSize;
		this.boardLength = boardLength;
		this.spaceSize = this.screenSize / (boardLength + 2);
		this.pieceSize = (spaceSize / 7) * 5;
		border = new GRectangle(spaceSize - ((spaceSize - pieceSize) / 2), spaceSize - ((spaceSize - pieceSize) / 2),
				spaceSize * boardLength, spaceSize * boardLength);
		board = new BetterPiece[boardLength][boardLength];
		for(int r = 0; r < board.length; r++)
			for(int c = 0; c < board[0].length; c++)
				board[r][c] = new BetterPiece(spaceSize * (r + 1), spaceSize * (c + 1), pieceSize);
	/*	while (rerandomizer(board) != true)
		{
			rerandomizer(board);
		} */
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
		this.addComponents();
	}

	public void shuffleBoard()
	{
		List<BetterPiece> pieces = new ArrayList<>();
		Random r = new Random();
		// Randomly shuffle the board
		for(int i = 0; i < this.board.length; i++)
		{
			for(int j = 0; j < this.board[i].length; j++)
			{
				pieces.add(board[i][j]);
				board[i][j] = null;
			}
		}

		for(int i = 0; i < this.board.length; i++)
		{
			for(int j = 0; j < this.board[i].length; j++)
			{
				board[i][j] = pieces.remove(r.nextInt(pieces.size()));
				board[i][j].setTargetLocation(spaceSize * (i + 1), spaceSize * (j + 1));
			}
		}
		System.out.println(numberOfPossibleMoves());
		System.out.println(numberOfMatches());
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
	 * helper method to find out if certain coordinates are valid
	 * 
	 * @param r
	 *            the row number
	 * @param c
	 *            the column number
	 * @return whether the r,c is a valid set of coordinates in the board
	 */
	private boolean inBounds(int r, int c)
	{
		return r >= 0 && r < boardLength && c >= 0 && c < boardLength;
	}

	/**
	 * returns the number of possible moves the user could make to get matches
	 * 
	 * @return the number of possible moves that would make matches
	 */
	public int numberOfPossibleMoves()
	{
		int numPossible = 0;
		BetterPiece p;
		for(int r = 0; r < boardLength; r++)
		{
			for(int c = 0; c < boardLength; c++)
			{
				p = board[r][c];
				if(inBounds(r, c + 1) && board[r][c + 1].getColorType() == p.getColorType())
				{
					if((inBounds(r - 1, c - 1) && board[r - 1][c - 1].getColorType() == p.getColorType())
							|| (inBounds(r + 1, c - 1) && board[r + 1][c - 1].getColorType() == p.getColorType())
							|| (inBounds(r - 1, c + 2) && board[r - 1][c + 2].getColorType() == p.getColorType())
							|| (inBounds(r + 1, c + 2) && board[r + 1][c + 2].getColorType() == p.getColorType())
							|| (inBounds(r, c - 2) && board[r][c - 2].getColorType() == p.getColorType())
							|| (inBounds(r, c + 3) && board[r][c + 3].getColorType() == p.getColorType()))
						numPossible++;
				}
				else if(inBounds(r, c + 2) && board[r][c + 2].getColorType() == p.getColorType())
				{
					if((inBounds(r - 1, c + 1) && board[r - 1][c + 1].getColorType() == p.getColorType())
							|| (inBounds(r + 1, c + 1) && board[r + 1][c + 1].getColorType() == p.getColorType()))
						numPossible++;
				}
				if(inBounds(r + 1, c) && board[r + 1][c].getColorType() == p.getColorType())
				{
					if((inBounds(r - 1, c - 1) && board[r - 1][c - 1].getColorType() == p.getColorType())
							|| (inBounds(r - 1, c + 1) && board[r - 1][c + 1].getColorType() == p.getColorType())
							|| (inBounds(r + 2, c - 1) && board[r + 2][c - 1].getColorType() == p.getColorType())
							|| (inBounds(r + 2, c + 1) && board[r + 2][c + 1].getColorType() == p.getColorType())
							|| (inBounds(r - 2, c) && board[r - 1][c].getColorType() == p.getColorType())
							|| (inBounds(r + 3, c) && board[r + 3][c].getColorType() == p.getColorType()))
						numPossible++;
				}
				else if(inBounds(r + 2, c) && board[r + 2][c].getColorType() == p.getColorType())
				{
					if((inBounds(r + 1, c - 1) && board[r + 1][c - 1].getColorType() == p.getColorType())
							|| (inBounds(r + 1, c + 1) && board[r + 1][c + 1].getColorType() == p.getColorType()))
						numPossible++;
				}
			}
		}

		return numPossible;
	}

	/**
	 * returns the number of instances where the same color GamePiece is present 3
	 * times in a row
	 * 
	 * @return the number of matches on the board
	 */
	public int numberOfMatches()
	{
		int numMatches = 0;
		BetterPiece p;
		for(int r = 0; r < boardLength; r++)
		{
			for(int c = 0; c < boardLength; c++)
			{
				p = board[r][c];
				if(inBounds(r, c + 1) && p.getColorType() == board[r][c + 1].getColorType() && inBounds(r, c + 2)
						&& p.getColorType() == board[r][c + 2].getColorType())
					numMatches++;
				if(inBounds(r + 1, c) && p.getColorType() == board[r + 1][c].getColorType() && inBounds(r + 2, c)
						&& p.getColorType() == board[r + 2][c].getColorType())
					numMatches++;
			}
		}
		return numMatches;
	}

	private void addComponents()
	{
		GRect r = new GRect(spaceSize - ((spaceSize - pieceSize)) / 2, spaceSize - ((spaceSize - pieceSize) / 2),
				spaceSize * boardLength, spaceSize * boardLength);
		add(r);
		for(int i = 0; i < verticalLines.length; i++)
		{
			GLine horizLine = new GLine(verticalLines[i].getX1(), verticalLines[i].getY1(), verticalLines[i].getX2(),
					verticalLines[i].getY2());
			add(horizLine);
			GLine vertLine = new GLine(horizontalLines[i].getX1(), horizontalLines[i].getY1(),
					horizontalLines[i].getX2(), horizontalLines[i].getY2());
			add(vertLine);
		}
		for(int i = 0; i < board.length; i++)
		{
			for(int j = 0; j < board[i].length; j++)
			{
				add(board[i][j]);
			}
		}
	}

	@Override
	public void onClick(MouseEvent evt)
	{
		GObject o = this.getElementAt(translateXToLocalSpace(evt.getX()), translateYToLocalSpace(evt.getY()));
		if(o != null && o instanceof BetterPiece)
		{
			((BetterPiece) o).toggleActive();
		}
	}

	private int translateXToLocalSpace(int x)
	{
		return x;// - this.x;
	}

	private int translateYToLocalSpace(int y)
	{
		return y;// - this.y;
	}
	
	/**
	 * 
	 * @param b the array of
	 * @return true if the board has been successfully rerandomized
	 */
/*	public boolean rerandomizer(BetterPiece[][] b)
	{
		int comparison = 0;
		for(int r = 0; r < b.length; r++)
		{
			for(int c = 0; c < b.length; c++)
			{
				if (comparison < 2)
				{
					if (r < b.length - 2  && c < b.length - 2)
					{
						while (b[r][c].getColor() == b[r][c + 2].getColor() )
						{
							b[r][c + 2].setGemColor(b);
						}
						while (b[r][c].getColor() != b[r + 2][c].getColor())
						{
							b[r + 2][c].setGemColor(b);
						}
					}
				}
			}
		}
		return true;
	}
*/
}
