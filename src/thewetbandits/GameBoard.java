package thewetbandits;

import java.awt.event.MouseEvent;

import acm.graphics.GCompound;
import acm.graphics.GLine;
import acm.graphics.GObject;
import acm.graphics.GRect;
import thewetbandits.utils.Clickable;

/**
 * 
 * @author Austin
 *
 *         Created; Mar 31, 2018
 */
public class GameBoard extends GCompound implements Clickable {

	private static final int PADDING = 10;

	private int x, y;
	private int pieceSize;
	private int boardSize;

	private GRect rectangle;

	private GamePiece[][] board;

	public GameBoard(int x, int y, int pieceSize, int boardSize) {
		this.x = x;
		this.y = y;
		this.pieceSize = pieceSize;
		this.boardSize = boardSize;
		this.board = new GamePiece[boardSize][boardSize];
		this.initialize();
	}

	/**
	 * Initializes the board (adds all the components)
	 */
	private void initialize() {
		setLocation(this.x, this.y);
		int effectiveSize = boardSize * pieceSize + PADDING * (boardSize + 1);
		// Draws the outline on the board
		this.rectangle = new GRect(0, 0, effectiveSize, effectiveSize);
		add(rectangle);

		// Add the pieces
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				this.board[i][j] = new GamePiece(((pieceSize + PADDING) * i) + PADDING,
						((pieceSize + PADDING) * j) + PADDING, pieceSize);
				add(this.board[i][j]);
			}
		}
	}

	@Override
	public void onClick(MouseEvent evt) {
		// Toggle the "active" part of the GamePiece (makes it spin!)
		GObject o = this.getElementAt(translateXToLocalSpace(evt.getX()), translateYToLocalSpace(evt.getY()));
		if (o != null && o instanceof GamePiece) {
			((GamePiece) o).toggleActive();
		}
	}

	/**
	 * Translates an absolute position on the applet into a relative position in the
	 * GCompound
	 * 
	 * @param x
	 *            The X position
	 * @return The local X position
	 */
	private int translateXToLocalSpace(int x) {
		return x - this.x;
	}

	/**
	 * Translates an absolute Y position on the applet into a relative position
	 * 
	 * @param y
	 *            The Y position
	 * @return THe local Y position
	 */
	private int translateYToLocalSpace(int y) {
		return y - this.y;
	}

}
