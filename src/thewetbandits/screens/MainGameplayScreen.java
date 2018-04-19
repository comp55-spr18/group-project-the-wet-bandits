package thewetbandits.screens;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import thewetbandits.Board;
import thewetbandits.MatchThreeGame;
import thewetbandits.utils.ClickAction;
import thewetbandits.utils.GButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

/**
 * @author Jacob Faulk Created Mar 30, 2018
 *
 *         John Thao - extra add-on buttons and context
 */
public class MainGameplayScreen extends Screen implements ActionListener
{
	public static final int WINDOW_WIDTH = 1000;
	public static final int WINDOW_HEIGHT = 700;
	private static final int BOARD_SIZE = 5;
	protected Board board;
	private int width;
	private int height;
	private String gameMode;

	private GButton pauseButton;
	private GButton quitButton;
	protected int secs = 0;
	protected int mins;
	protected GLabel myTime;
	protected GLabel displayScore;
	protected Timer someTimerVar = new Timer(1000, this);
	private Color buttonColor = new Color(255, 154, 0);

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
	public MainGameplayScreen(MatchThreeGame app, int width, int height)
	{
		super(app);
		this.width = width;
		this.height = height;
		this.gameMode = gameMode;
		GImage boardBG = new GImage("board1.gif", 0, 0);
		boardBG.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		add(boardBG);
		
		board = new Board(width < height ? width : height, BOARD_SIZE, app);
		board.setLocation(300, 25);
		while(board.numberOfMatches() > 0 || board.numberOfPossibleMoves() <= 0)
			board.shuffleBoard();
		this.add(new GButton("Randomize", 75, 250, 100, 50, new ClickAction()
		{

			@Override
			public void onClick(MouseEvent event)
			{
				System.out.println("Shuffling board");
				if(event.isShiftDown())
				{
					board.resetBoard();
				}
				do
				{
					board.shuffleBoard();
				}while(board.numberOfMatches() > 0 || board.numberOfPossibleMoves() <= 0);
			}

		}));
		// TODO 4/10/18: This is only a temporary button for testing, we probably should
		// remove this eventually
		this.add(new GButton("Shift Down", 25, 600, 100, 50, new ClickAction()
		{
			@Override
			public void onClick(MouseEvent event)
			{
				board.shiftDown();
			}
		}));
		// TODO remove this one as well
		this.add(new GButton("Refill", 125, 600, 50, 50, new ClickAction()
		{
			@Override
			public void onClick(MouseEvent event)
			{
				while(board.hasEmptySpaces())
				{
					board.refill();
					board.shiftDown();
				}
			}
		}));
		this.addComponents();
		run();
	}

	/**
	 * Adds the board to itself and draws it
	 */
	private void addComponents()
	{
		this.add(board);// new BetterBoard(30, 30, 50, 8));
	}

	/**
	 * Calls all the displays/buttons (Also start the timer for the Timed Game Mode,
	 * not yet implemented)
	 */
	public void run()
	{
		displayTitle();
		displayScore();
		this.mins = 0;
		myTime = new GLabel("Time Elapsed: ", 350, 40);
		add(myTime);
		myTime.setFont("Bold-15");
		if (gameMode == "Limited Moves") {
			displayMovesTime();
		}
		someTimerVar.setInitialDelay(3);
		someTimerVar.start();
		displayPause();
		displayQuit();
	}

	/**
	 * Displays the title
	 */
	public void displayTitle()
	{
		GImage displayTitle = new GImage("title.png", 20, 0);
		displayTitle.setSize(350, 150);
		add(displayTitle);
	}

	/**
	 * Displays the player score - Need to make some sort of score counter which
	 * communicates with matches
	 */
	public void displayScore()
	{
		displayScore = new GLabel("Score: " + board.getScore(), 75, 200);
		
		displayScore.setFont("Times Roman-40");
		displayScore.setColor(Color.ORANGE);
		add(displayScore);
	}

	/**
	 * Displays the time and moves left (These modes are not yet implemented)
	 */
	public void displayMovesTime()
	{
		GLabel movesAndTime = new GLabel("Moves left: 50", 200, 40);

		add(movesAndTime);
		movesAndTime.setFont("Bold-15");
	}

	/**
	 * Displays the pause button - need to make it pause and go to some sort of
	 * pause screen - then add a button to come back to the board
	 */
	public void displayPause()
	{
		GImage pause = new GImage("pause.png", 225, 400);
		add(pause);
		
		pauseButton = new GButton("PAUSE", 50, 400, 150, 50, new ClickAction()
		{
			@Override
			public void onClick(MouseEvent event)
			{
				System.out.println("Pressing Pause");
				// go to a pause screen??
				// switchToScreen();
			}
		});
		pauseButton.setFillColor(buttonColor);
		add(pauseButton);

	}

	/**
	 * Displays the quit button - need to make it go to the MenuScreen
	 */
	public void displayQuit()
	{
		GImage quit = new GImage("boardquit.png", 225, 500);
		add(quit);
		
		quitButton = new GButton("QUIT", 50, 500, 150, 50, new ClickAction()
		{
			@Override
			public void onClick(MouseEvent event)
			{
				System.out.println("Pressing Quit");
				System.out.println("click on quit");
				System.exit(0);
				// go to MenuScreen
				// switchToScreen();
			}
		});
		quitButton.setFillColor(buttonColor);
		add(quitButton);
	}

	/**
	 * This implements the timer count down for TIMED MODE (Not yet implemented)
	 */
	public void actionPerformed(ActionEvent e)
	{
		//TODO make score updates asynchronous
		displayScore.setLabel("Score: " + board.getScore());
		if(secs > 9)
		{
			myTime.setLabel("Time Elapsed: " + mins + ":" + secs);
			secs++;
		}
		else if(secs <= 9)
		{
			myTime.setLabel("Time Elapsed: " + mins + ":0" + secs);
			secs++;
		}
		if(secs > 59)
		{
			secs = 0;
			mins++;
		}
	}

}
