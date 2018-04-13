/**
 * @author Jacob Faulk
 * Created Mar 30, 2018
 */

package thewetbandits;

import acm.graphics.*;
import test.BetterPiece;
import thewetbandits.utils.Clickable;

import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

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
	private BetterPiece selectedPiece;
	private int score;

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
		this.score = 0;
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
				board[r][c] = new BetterPiece(spaceSize * (r + 1), spaceSize * (c + 1), pieceSize, r, c);
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
		BetterPiece b;
		for(int i = 0; i < this.board.length; i++)
		{
			for(int j = 0; j < this.board[i].length; j++)
			{
				b = pieces.remove(r.nextInt(pieces.size()));
				b.updateRowCol(i, j);
				board[i][j] = b;
			}
		}

		this.updatePieceLocations();
	}

	public void clearBoard()
	{
		for(int i = 0; i < this.board.length; i++)
		{
			for(int j = 0; j < this.board[i].length; j++)
			{
				if(board[i][j] != null)
					remove(board[i][j]);
				board[i][j] = null;
			}
		}
	}

	public void resetBoard()
	{
		this.clearBoard();
		for(int r = 0; r < board.length; r++)
		{
			for(int c = 0; c < board[0].length; c++)
			{
				board[r][c] = new BetterPiece(spaceSize * (r + 1), spaceSize * (c + 1), pieceSize, r, c);
				add(board[r][c]);
			}
		}
	}

	public void updatePieceLocations()
	{
		for(int i = 0; i < this.board.length; i++)
		{
			for(int j = 0; j < this.board[i].length; j++)
			{
				if(board[i][j] != null)
					board[i][j].setTargetLocation(spaceSize * (i + 1), spaceSize * (j + 1));
			}
		}
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

	private void removeMatches()
	{
		System.out.println("removeMatches() called");
		BetterPiece p;
		for(int r = 0; r < boardLength; r++)
		{
			for(int c = 0; c < boardLength; c++)
			{
				p = board[r][c];
				if(p == null)
					continue;
				HashSet<BetterPiece> rowChain = buildChain(r, c, 1, 0);
				HashSet<BetterPiece> colChain = buildChain(r, c, 0, 1);

				if(rowChain.size() >= 3)
				{
					score += 100 * (rowChain.size() - 2);
					for(BetterPiece p1 : rowChain)
					{
						board[p1.getR()][p1.getC()] = null;
						remove(p1);
					}
				}
				if(colChain.size() >= 3)
				{
					score += 100 * (colChain.size() - 2);
					for(BetterPiece p1 : colChain)
					{
						board[p1.getR()][p1.getC()] = null;
						remove(p1);
					}
				}
			}
		}
	}

	/**
	 * @return the score
	 */
	public int getScore()
	{
		return score;
	}

	/**
	 * Constructs a chain of pieces (going in the positive row and col)
	 *
	 * @param row
	 *            The start row
	 * @param col
	 *            The end row
	 * @param deltaR
	 *            The change in row
	 * @param deltaC
	 *            The change in column
	 * @return The chain
	 */
	private HashSet<BetterPiece> buildChain(int row, int col, int deltaR, int deltaC)
	{
		BetterPiece piece = board[row][col];
		HashSet<BetterPiece> chain = new HashSet<>();
		for(int i = 0; i < boardLength; i++)
		{
			int targetRow = row + (i * deltaR);
			int targetCol = col + (i * deltaC);
			if(!inBounds(targetRow, targetCol))
				break;
			BetterPiece target = board[targetRow][targetCol];
			if(target == null)
			{
				break;
			}
			if(target.getColorType() != piece.getColorType())
				break;
			chain.add(target);
		}
		return chain;
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

	public void shiftDown()
	{
		boolean changed;
		do
		{
			changed = false;
			for(int screenCol = 0; screenCol < this.boardLength; screenCol++)
			{
				for(int screenRow = 0; screenRow < this.boardLength; screenRow++)
				{
					BetterPiece p = this.getPiece(screenRow, screenCol);
					if(p == null)
						continue;
					int targetRow = screenRow + 1;
					if(!this.inBounds(screenCol, targetRow))
					{
						continue;
					}
					if(this.getPiece(targetRow, screenCol) == null)
					{
						// We found a piece with an empty piece below it, shift down
						this.setPiece(null, screenRow, screenCol);
						this.setPiece(p, targetRow, screenCol);
						changed = true;
					}
				}
			}
		}while(changed);
		updatePieceLocations();
	}

	public boolean refill()
	{
		boolean refilled = false;
		for(int screenCol = 0; screenCol < this.boardLength; screenCol++)
		{
			int screenRow = 0;
			if(this.getPiece(screenRow, screenCol) == null)
			{
				BetterPiece piece = new BetterPiece(spaceSize * (screenCol + 1), spaceSize * (screenRow + 1), pieceSize,
						screenCol, screenRow);
				add(piece);
				this.setPiece(piece, screenRow, screenCol);
				refilled = true;
			}
		}
		return refilled;
	}

	public boolean hasEmptySpaces()
	{
		for(int r = 0; r < board.length; r++)
			for(int c = 0; c < board[0].length; c++)
				if(board[r][c] == null)
					return true;
		return false;
	}

	public BetterPiece getPiece(int screenRow, int screenCol)
	{
		return this.board[screenCol][screenRow];
	}

	public void setPiece(BetterPiece piece, int screenRow, int screenCol)
	{
		this.board[screenCol][screenRow] = piece;
		if(piece != null)
		{
			piece.updateRowCol(screenCol, screenRow);
		}
	}

	@Override
	public void onClick(MouseEvent evt)
	{
		GObject o = this.getElementAt(translateXToLocalSpace(evt.getX()), translateYToLocalSpace(evt.getY()));
		if(o != null && o instanceof BetterPiece)
		{
			BetterPiece clickedPiece = (BetterPiece) o;
			if(selectedPiece == null)
			{
				clickedPiece.toggleActive();
				selectedPiece = clickedPiece;
			}
			else
			{
				selectedPiece.toggleActive();
				if((Math.abs(clickedPiece.getR() - selectedPiece.getR()) == 1
						&& Math.abs(clickedPiece.getC() - selectedPiece.getC()) == 0)
						|| (Math.abs(clickedPiece.getR() - selectedPiece.getR()) == 0
								&& Math.abs(clickedPiece.getC() - selectedPiece.getC()) == 1))
				{
					int targetRow, targetCol;
					targetRow = clickedPiece.getR();
					targetCol = clickedPiece.getC();

					// Perform the swap
					this.swapPiece(this.selectedPiece.getR(), this.selectedPiece.getC(), targetRow, targetCol);
					this.updatePieceLocations();
					this.removeMatches();
					this.updatePieceLocations();
				}
				selectedPiece = null;
			}
		}
	}

	private void swapPiece(int r1, int c1, int r2, int c2)
	{
		BetterPiece p1 = this.board[r1][c1];
		BetterPiece p2 = this.board[r2][c2];

		if(p2 != null)
			p2.updateRowCol(r1, c1);
		this.board[r1][c1] = p2;
		if(p1 != null)
			p1.updateRowCol(r2, c2);
		this.board[r2][c2] = p1;
	}

	private int translateXToLocalSpace(int x)
	{
		return x - (int) this.getX();
	}

	private int translateYToLocalSpace(int y)
	{
		return y - (int) this.getY();
	}
}
