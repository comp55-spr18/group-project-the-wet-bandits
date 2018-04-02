package thewetbandits.screens;

import java.awt.event.MouseEvent;

import acm.graphics.GObject;
import test.BetterBoard;
import test.BetterPiece;
import thewetbandits.Board;
import thewetbandits.MatchThreeGame;

/**
 * @author Jacob Faulk Created Mar 30, 2018
 */
public class TestGameScreen extends Screen
{
	private static final int BOARD_SIZE = 8;
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
