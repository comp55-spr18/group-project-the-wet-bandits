package thewetbandits.screens;

import java.awt.Color;
import java.util.Random;

import acm.graphics.GRect;
import thewetbandits.Board;
import thewetbandits.GamePiece;
import thewetbandits.MatchThreeGame;

/**
 * @author JacobFaulk Created Mar 30, 2018
 */
public class TestGameScreen extends Screen
{
	private static final int PIECE_SIZE = 50;
	private static final int BOARD_SIZE = 8;
	private static final int SPACE_SIZE = PIECE_SIZE + (PIECE_SIZE / 5 * 2);
	private static final Color YELLOW = new Color(250, 240, 66);
	private static final Color GREEN = new Color(67, 153, 58);
	private static final Color BLUE = new Color(24, 30, 219);
	private GamePiece[][] testBoard;
	private Random rand = new Random();
	private GRect border;
	private Board board;

	public TestGameScreen(MatchThreeGame app)
	{
		super(app);
		this.addComponents();
	}

	private void addComponents()
	{
		board = new Board(BOARD_SIZE, PIECE_SIZE);
		this.add(board);
//		border = new GRect(SPACE_SIZE - ((SPACE_SIZE - PIECE_SIZE) / 2), SPACE_SIZE - ((SPACE_SIZE - PIECE_SIZE) / 2),
//				SPACE_SIZE * BOARD_SIZE, SPACE_SIZE * BOARD_SIZE);
//		add(border);
//		testBoard = new GamePiece[BOARD_SIZE][BOARD_SIZE];
//		Color tempColor = Color.BLACK;
//		for(int r = 0; r < testBoard.length; r++)
//		{
//			for(int c = 0; c < testBoard[0].length; c++)
//			{
//				switch(rand.nextInt(4))
//				{
//				case 0:
//					tempColor = Color.RED;
//					break;
//				case 1:
//					tempColor = BLUE;
//					break;
//				case 2:
//					tempColor = GREEN;
//					break;
//				default:
//					tempColor = YELLOW;
//				}
//				testBoard[r][c] = new GamePiece(SPACE_SIZE * (r + 1), SPACE_SIZE * (c + 1), PIECE_SIZE, tempColor);
//				this.add(testBoard[r][c]);
//			}
//		}
	}
}
