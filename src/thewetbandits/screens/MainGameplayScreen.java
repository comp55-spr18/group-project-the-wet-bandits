package thewetbandits.screens;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import thewetbandits.Board;
import thewetbandits.MatchThreeGame;
import thewetbandits.utils.ClickAction;
import thewetbandits.utils.GImageButton;

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
	private static final int BOARD_SIZE = 8;

	protected Board board;
	private String gameMode;

	private GImageButton pause = new GImageButton("pause.png", 100, 400);
	private GImageButton quit = new GImageButton("boardquit.png", 100, 500, new ClickAction() {
		@Override
		public void onClick(MouseEvent event) {
			System.exit(0);
		}
	});
	protected int secs = 0;
	protected int mins;
	protected GLabel myTime;
	protected GLabel displayScore;
	protected Timer scoreTimer = new Timer(1, this);
	protected Timer clockTimer = new Timer(1000, new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			myTime.setLabel(String.format("Time Elapsed: %d:%02d", secs / 60, secs % 60));
			secs++;
		}
	});

	private int frameNum = 0;
	private int score;
	private int displayedScore;

	/**
	 * Constructor that specifies the MatchThreeGame and the dimensions of that
	 * MatchThreeGame
	 * 
	 * @param app
	 *            the GraphicsApplication that this Screen will be added to
	 */
	public MainGameplayScreen(MatchThreeGame app)
	{
		super(app);
		GImage boardBG = new GImage("boardBG.png", 0, 0);
		boardBG.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		add(boardBG);

		this.score = 0;
		this.displayedScore = 0;
		this.gameMode = "";// TODO this will have to be dealt with at some point

		board = new Board(WINDOW_HEIGHT, BOARD_SIZE, app);
		board.setLocation(300, 25);
		while(board.numberOfMatches() > 0 || board.numberOfPossibleMoves() <= 0)
			board.shuffleBoard();
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
		displayButton();
		this.mins = 0;
		myTime = new GLabel("Time Elapsed: ", 350, 40);
		add(myTime);
		myTime.setFont("Bold-15");
		myTime.setColor(Color.WHITE);
	}

	@Override
	public void onShow() {
		clockTimer.setInitialDelay(3);
		scoreTimer.start();
		clockTimer.start();
	}

	/**
	 * Displays the title
	 */
	public void displayTitle()
	{
		GImage displayTitle = new GImage("title.png", 5, 0);
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
	 * Displays the pause button - need to make it pause and go to some sort of
	 * pause screen - then add a button to come back to the board
	 */
	public void displayButton()
	{
		add(pause);
		add(quit);
	}

	/**
	 * This implements the timer count down for TIMED MODE (Not yet implemented)
	 */
	public void actionPerformed(ActionEvent e)
	{
		this.score = board.getScore();
		if(displayedScore < score)
		{
			if(score - displayedScore < 200)
				displayedScore++;
			else if(score - displayedScore < 1000)
				displayedScore += 5;
			else if(score - displayedScore < 5000)
				displayedScore += 10;
			else if(score - displayedScore < 50000)
				displayedScore += 100;
			else
				displayedScore += 1000;
		}
		if(displayedScore > score)
			displayedScore = score;
		displayScore.setLabel("Score: " + displayedScore);
		frameNum++;
	}

}
