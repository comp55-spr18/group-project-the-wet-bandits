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
	private MatchThreeGame game;

	private GImageButton mute = new GImageButton("mute.png", 135, 400, new ClickAction()
	{
		@Override
		public void onClick(MouseEvent event)
		{
			if(Screens.MENU_SCREEN.clip.isActive())
			{
				Screens.MENU_SCREEN.clip.stop();
			}
			else
			{
				Screens.MENU_SCREEN.clip.start();
			}
		}
	});
	private GImageButton quit = new GImageButton("boardquit.png", 100, 500, new ClickAction()
	{
		@Override
		public void onClick(MouseEvent event)
		{
			game.switchToScreen(Screens.MENU_SCREEN);
		}
	});

	private GImage noMovesImage = new GImage("no_moves.png");
	protected int secs = 0;
	protected int mins;
	protected GLabel time;
	protected GLabel displayScore;
	protected Timer scoreTimer = new Timer(1, this);
	protected Timer clockTimer = new Timer(1000, new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(time != null)
				time.setLabel(String.format("Time Elapsed: %d:%02d", secs / 60, secs % 60));
			secs++;
		}
	});

	private int score;
	private int displayedScore;

	protected boolean isInitialized = false;

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
		game = app;
		GImage boardBG = new GImage("boardBG.png", 0, 0);
		boardBG.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		add(boardBG);

		this.score = 0;
		this.displayedScore = 0;

		board = new Board(WINDOW_HEIGHT, BOARD_SIZE, app);
		board.setLocation(300, 25);
		while(board.numberOfMatches() > 0 || board.numberOfPossibleMoves() <= 0)
			board.shuffleBoard();
		this.addComponents();
		this.noMovesImage.setSize(this.noMovesImage.getWidth() / 2, this.noMovesImage.getHeight() / 2);
		double imgX = WINDOW_WIDTH / 2 - this.noMovesImage.getWidth() / 2;
		double imgY = WINDOW_HEIGHT / 2 - this.noMovesImage.getHeight() / 2;
		this.noMovesImage.setLocation(imgX, imgY);
		if(!isInitialized)
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
		time = new GLabel("Time Elapsed: ", 500, 75);
		add(time);
		time.setFont("Bold-25");
		time.setColor(Color.WHITE);
		isInitialized = true;
	}

	/**
	 * shows the image that indicates to the player that there are no more moves to
	 * be made
	 */
	public void showNoMoves()
	{
		this.add(this.noMovesImage);
	}

	/**
	 * hides the image that indicates to the player that there are no more moves to
	 * be made
	 */
	public void hideNoMoves()
	{
		this.remove(this.noMovesImage);
	}

	/**
	 * starts the score and clock timers
	 */
	@Override
	public void onShow()
	{
		clockTimer.setInitialDelay(3);
		scoreTimer.start();
		clockTimer.start();
	}

	/**
	 * pauses the clock timer
	 */
	@Override
	public void onHide()
	{
		clockTimer.stop();
	}

	/**
	 * Displays the title
	 */
	public void displayTitle()
	{
		GImage displayTitle = new GImage("title1.png", 5, 0);
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
		add(mute);
		add(quit);
	}

	/**
	 * Animates the score
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
	}

}
