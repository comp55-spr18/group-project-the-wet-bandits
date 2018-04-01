package test;

import java.awt.event.MouseEvent;

import acm.graphics.GCompound;
import acm.graphics.GLine;
import acm.graphics.GObject;
import acm.graphics.GRect;

/**
 * 
 * @author Austin
 *
 *         Created; Mar 31, 2018
 */
public class BetterBoard extends GCompound {

	private static final int PADDING = 10;

	private int x, y;
	private int pieceSize;
	private int boardSize;

	private GRect rectangle;

	private BetterPiece[][] board;

	public BetterBoard(int x, int y, int pieceSize, int boardSize) {
		this.x = x;
		this.y = y;
		this.pieceSize = pieceSize;
		this.boardSize = boardSize;
		this.board = new BetterPiece[boardSize][boardSize];
		this.initialize();
	}

	private void initialize() {
		setLocation(this.x, this.y);
		int effectiveSize = boardSize * pieceSize + PADDING * (boardSize + 1);
		this.rectangle = new GRect(0, 0, effectiveSize, effectiveSize);
		add(rectangle);
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				this.board[i][j] = new BetterPiece(((pieceSize + PADDING) * i) + PADDING,
						((pieceSize + PADDING) * j) + PADDING, pieceSize);
				add(this.board[i][j]);
			}
		}
	}

	public void onClick(MouseEvent evt) {
		GObject o = this.getElementAt(translateXToLocalSpace(evt.getX()), translateYToLocalSpace(evt.getY()));
		if (o != null && o instanceof BetterPiece) {
			((BetterPiece) o).toggleActive();
		}
	}

	private int translateXToLocalSpace(int x) {
		return x - this.x;
	}

	private int translateYToLocalSpace(int y) {
		return y - this.y;
	}

}