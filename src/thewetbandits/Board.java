package thewetbandits;

import acm.graphics.*;
import thewetbandits.screens.MainGameplayScreen;
import thewetbandits.utils.Clickable;
import thewetbandits.utils.GraphicsPane;

import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author Jacob Faulk Created Mar 30, 2018
 */
public class Board extends GCompound implements Clickable
{
	private static final long serialVersionUID = -7200286741625467434L;
	private GamePiece[][] board;
	private int pieceSize;
	private int boardLength;
	private int spaceSize;
	private GRectangle border;
	private Line2D.Double[] verticalLines;
	private Line2D.Double[] horizontalLines;
	private int screenSize;
	private MatchThreeGame app;
	private GamePiece selectedPiece;
	private int score;
	private int scoreMultiplier;

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
		this.scoreMultiplier = 1;
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
				board[r][c] = new GamePiece(spaceSize * (r + 1), spaceSize * (c + 1), pieceSize, r, c);
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

	/**
	 * shuffles the board into a completely random, possibly invalid or unplayable,
	 * combination of pieces
	 */
	public void shuffleBoard()
	{
		List<GamePiece> pieces = new ArrayList<>();
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
		GamePiece b;
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

	/**
	 * sets all pieces to null
	 */
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

	/**
	 * resets the board with completely new, randomized pieces
	 */
	public void resetBoard()
	{
		this.clearBoard();
		for(int r = 0; r < board.length; r++)
		{
			for(int c = 0; c < board[0].length; c++)
			{
				board[r][c] = new GamePiece(spaceSize * (r + 1), spaceSize * (c + 1), pieceSize, r, c);
				add(board[r][c]);
			}
		}
	}

	/**
	 * visually moves every piece to the place it is supposed to be in the array
	 */
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

	/**
	 * resizes the board based on a new size
	 * 
	 * @param screenSize
	 *            the size of the area the board is to fit in
	 */
	public void updateBounds(int screenSize)
	{
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
		GamePiece p;
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
		GamePiece p;
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

	/**
	 * removes all matches of 3 or more from the board and gives the user points
	 * based on the size of the match
	 */
	private void removeMatches()
	{
		System.out.println("removeMatches() called");
		GamePiece p;
		for(int r = 0; r < boardLength; r++)
		{
			for(int c = 0; c < boardLength; c++)
			{
				p = board[r][c];
				if(p == null)
					continue;
				HashSet<GamePiece> rowChain = buildChain(r, c, 1, 0);
				HashSet<GamePiece> colChain = buildChain(r, c, 0, 1);

				if(rowChain.size() >= 3)
				{
					score += removeChain(rowChain);
				}
				if(colChain.size() >= 3)
				{
					score += removeChain(colChain);
				}
			}
		}
	}

	private int removeChain(HashSet<GamePiece> chain)
	{
		if(chain.size() < 3)
			return 0;
		int score = (100 * (chain.size() - 2)) * scoreMultiplier;
		for(GamePiece p : chain)
		{
			board[p.getR()][p.getC()] = null;
			remove(p);
		}
		return score;
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
	private HashSet<GamePiece> buildChain(int row, int col, int deltaR, int deltaC)
	{
		GamePiece piece = board[row][col];
		HashSet<GamePiece> chain = new HashSet<>();
		for(int i = 0; i < boardLength; i++)
		{
			int targetRow = row + (i * deltaR);
			int targetCol = col + (i * deltaC);
			if(!inBounds(targetRow, targetCol))
				break;
			GamePiece target = board[targetRow][targetCol];
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

	/**
	 * adds the guiding lines, border, and pieces to the graphics of the board
	 */
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

	/**
	 * shifts pieces down to the lowest position without a piece present, as if
	 * there was a gravity effect on them
	 */
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
					GamePiece p = this.getPiece(screenRow, screenCol);
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

	/**
	 * refills all the empty pieces in the topmost rows of the board
	 * 
	 * @return whether the refill was successful
	 */
	public void refill()
	{
		boolean refilled = false;
		for(int screenCol = 0; screenCol < this.boardLength; screenCol++)
		{
			int screenRow = 0;
			if(this.getPiece(screenRow, screenCol) == null)
			{
				GamePiece piece = new GamePiece(spaceSize * (screenCol + 1), spaceSize * (screenRow + 1), pieceSize,
						screenCol, screenRow);
				add(piece);
				this.setPiece(piece, screenRow, screenCol);
				refilled = true;
			}
		}
	}

	/**
	 * returns whether there are any empty spaces on the board
	 * 
	 * @return true if there is at least one empty space on the board
	 */
	public boolean hasEmptySpaces()
	{
		for(int r = 0; r < board.length; r++)
			for(int c = 0; c < board[0].length; c++)
				if(board[r][c] == null)
					return true;
		return false;
	}

	/**
	 * Helper method to translate where the piece appears visually to its actual
	 * position in the array. This method is needed because what appears on the
	 * board is different than what is actually in the array. For example, what
	 * would appear to be board[row][col] on the board would actually be represented
	 * by board[col][row] in the array.
	 * 
	 * @param screenRow
	 *            the row at which the piece appears to be on the visual
	 *            representation of the board
	 * @param screenCol
	 *            the column at which the piece appears to be on the visual
	 *            representation of the board
	 * @return a reference to the piece that appears to be at
	 *         board[screenRow][screenCol]
	 */
	public GamePiece getPiece(int screenRow, int screenCol)
	{
		return this.board[screenCol][screenRow];
	}

	/**
	 * Sets the piece at board[screenCol][screenRow] to the specified piece, and
	 * updates the references inside the piece accordingly. This row and column
	 * references are reversed because what appears on the board is different than
	 * what is actually in the array. For example, what would appear to be
	 * board[row][col] on the board would actually be represented by board[col][row]
	 * in the array.
	 * 
	 * @param piece
	 *            the piece to be inserted into the board at
	 *            board[screenCol][screenRow]
	 * @param screenRow
	 *            the row at which the piece will appear to be on the visual
	 *            representation of the board
	 * @param screenCol
	 *            the column at which the piece will appear to be on the visual
	 *            representation of the board
	 */
	public void setPiece(GamePiece piece, int screenRow, int screenCol)
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
		if(o != null && o instanceof GamePiece)
		{
			final GamePiece clickedPiece = (GamePiece) o;
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
					final int targetRow, targetCol;
					targetRow = clickedPiece.getR();
					targetCol = clickedPiece.getC();

					// Perform the swap
					this.swapPiece(this.selectedPiece.getR(), this.selectedPiece.getC(), targetRow, targetCol);
					this.updatePieceLocations();
					if(this.numberOfMatches() < 1)
					{
						System.out.println("No matches");
						// no matches
						MatchThreeGame.executor.schedule(new Runnable()
						{
							@Override
							public void run()
							{
								System.out.println("Swapping back");
								swapPiece(clickedPiece.getR(), clickedPiece.getC(), targetRow, targetCol);
								updatePieceLocations();
							}
						}, 250, TimeUnit.MILLISECONDS);
						selectedPiece = null;
						return;
					}
					MatchThreeGame.executor.submit(new Runnable()
					{
						@Override
						public void run()
						{
							try
							{
								while(GamePiece.arePiecesAnimating())
								{
									// Do nothing
									Thread.yield();
								}
								removeMatches();
								updatePieceLocations();
								Thread.sleep(100);
								while(GamePiece.arePiecesAnimating())
								{
									Thread.yield();
								}
								while(hasEmptySpaces() || numberOfMatches() > 0)
								{
									Thread.sleep(100);
									scoreMultiplier++;
									removeMatches();
									while(hasEmptySpaces())
									{
										Thread.sleep(100);
										shiftDown();
										refill();
									}
								}
								scoreMultiplier = 1;
							}catch(Exception e)
							{
								// ignore
							}
						}
					});
				}
				selectedPiece = null;
			}
		}
		System.out.println(numberOfPossibleMoves());
		if(numberOfPossibleMoves() <= 0)
		{
			MatchThreeGame.executor.submit(new Runnable() {
				@Override
				public void run() {
					GraphicsPane p = app.getCurrentPane();
					MainGameplayScreen s = null;
					if(p instanceof MainGameplayScreen){
						s = (MainGameplayScreen) p;

						s.showNoMoves();

						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					resetBoard();
					do
					{
						shuffleBoard();
					} while(numberOfMatches() > 0 || numberOfPossibleMoves() <= 0);
					if(s != null)
						s.hideNoMoves();
				}
			});
		}
	}

	/**
	 * swaps the piece at board[r1][c1] with the piece at board[r2][c2]
	 * 
	 * @param r1
	 *            the row of the first piece
	 * @param c1
	 *            the column of the first piece
	 * @param r2
	 *            the row of the second piece
	 * @param c2
	 *            the column of the second piece
	 */
	private void swapPiece(int r1, int c1, int r2, int c2)
	{
		GamePiece p1 = this.board[r1][c1];
		GamePiece p2 = this.board[r2][c2];

		if(p2 != null)
			p2.updateRowCol(r1, c1);
		this.board[r1][c1] = p2;
		if(p1 != null)
			p1.updateRowCol(r2, c2);
		this.board[r2][c2] = p1;
	}

	/**
	 * helper method to translate an x coordinate on the screen to an x coordinate
	 * on the board
	 * 
	 * @param x
	 *            the x location on the screen
	 * @return the x location on the board
	 */
	private int translateXToLocalSpace(int x)
	{
		return x - (int) this.getX();
	}

	/**
	 * helper method to translate a y coordinate on the screen to a y coordinate on
	 * the board
	 * 
	 * @param y
	 *            the y location on the screen
	 * @return the y location on the board
	 */
	private int translateYToLocalSpace(int y)
	{
		return y - (int) this.getY();
	}
}
