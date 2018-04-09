package thewetbandits.screens;

import java.awt.event.MouseEvent;

import acm.graphics.GObject;
import test.BetterBoard;
import test.BetterPiece;
import thewetbandits.Board;
import thewetbandits.MatchThreeGame;
import thewetbandits.utils.ClickAction;
import thewetbandits.utils.GButton;

/**
 * @author Jacob Faulk Created Mar 30, 2018
 */
public class TestGameScreen extends Screen
{
	private static final int BOARD_SIZE = 10;
	public Board board;
	private int width;
	private int height;

	/**
	 * Constructor that specifies the MatchThreeGame and the dimensions of that
	 * MatchThreeGame
	 * 
	 * @param app
	 *            the GraphicsApplication that this Screen will be added to
	 * @param width
	 *            the width of the GraphicsApplication
	 * @param height
	 *            the height of the GraphicsApplication
	 */
	public TestGameScreen(MatchThreeGame app, int width, int height)
	{
		super(app);
		this.width = width;
		this.height = height;
		board = new Board(width < height ? width : height, BOARD_SIZE, app);
		while(board.numberOfMatches() > 0 || board.numberOfPossibleMoves() <= 0)
			board.shuffleBoard();
		this.add(new GButton("Randomize", 20, 20, 100, 50, new ClickAction() {

			@Override
			public void onClick(MouseEvent event) {
				System.out.println("Shuffling board");
				do {
					board.shuffleBoard();
				} while(board.numberOfMatches() > 0 || board.numberOfPossibleMoves() <= 0);
			}
			
		}));
		this.addComponents();
	}

	/**
	 * Adds the board to itself and draws it
	 */
	private void addComponents()
	{
		this.add(board);//new BetterBoard(30, 30, 50, 8));
	}
}
