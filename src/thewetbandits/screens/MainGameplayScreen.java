package thewetbandits.screens;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.Timer;

import acm.graphics.GObject;
import acm.graphics.GLabel;
import test.BetterBoard;
import test.BetterPiece;
import thewetbandits.Board;
import thewetbandits.MatchThreeGame;
import thewetbandits.utils.ClickAction;
import thewetbandits.utils.GButton;

/**
 * @author Jacob Faulk Created Mar 30, 2018
 *
 * John Thao - extra add-on buttons and context
 */
public class MainGameplayScreen extends Screen implements ActionListener
{
	private static final int BOARD_SIZE = 10;
	public Board board;
	private int width;
	private int height;
	
	private GButton pauseButton;
	private GButton quitButton;
	private int secs = 0;
	private int mins = 5;
	private GLabel myTime;
	Timer someTimerVar = new Timer(1000, this);

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
		board = new Board(width < height ? width : height, BOARD_SIZE, app);
		//board.setLocation(300, 25);
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
		run();
	}

	/**
	 * Adds the board to itself and draws it
	 */
	private void addComponents()
	{
		this.add(board);//new BetterBoard(30, 30, 50, 8));
	}
	
	
	/**
	 * Calls all the displays/buttons (Also start the timer for the Timed Game Mode, not yet implemented)
	 */
	public void run() {
		myTime = new GLabel("Time left: ", 350, 40);
		add(myTime);
		myTime.setFont("Bold-15");
		someTimerVar.setInitialDelay(3);
		someTimerVar.start();
		
		displayTitle();
		displayScore();
		displayMovesTime();
		displayPause();
		displayQuit();
	}
	
	/**
	 * Displays the title
	 */
	
	public void displayTitle() {
		GLabel displayTitle = new GLabel("Three's A Company", 675, 50);

		add(displayTitle);
		displayTitle.setColor(Color.RED);
		displayTitle.setFont("Bradley Hand ITC-Bold-30");
	}
	
	/**
	 * Displays the player score
	 *    - Need to make some sort of score counter which communicates with matches
	 */
	
	public void displayScore() {
		GLabel displayScore = new GLabel("Score: ", 675, 200);

		add(displayScore);
		displayScore.setFont("Times Roman-40");
	}
	
	
	/**
	 * Displays the time and moves left (These modes are not yet implemented)
	 */
	
	public void displayMovesTime() {
		GLabel movesAndTime = new GLabel("Moves left: 50", 200, 40);
		
		add(movesAndTime);
		movesAndTime.setFont("Bold-15");
	}
	
	/**
	 * Displays the pause button
	 *    - need to make it pause and go to some sort of pause screen
	 *    - then add a button to come back to the board
	 */
	
	public void displayPause() {
		pauseButton = new GButton("PAUSE", 750, 400, 150, 50, new ClickAction() {
				@Override
				public void onClick(MouseEvent event) {
					System.out.println("Pressing Pause");
					//go to a pause screen??
					//switchToScreen();
				}});
		add(pauseButton);
		
	}
	
	/**
	 * Displays the quit button
	 *   - need to make it go to the MenuScreen
	 */
	
	public void displayQuit() {
		quitButton = new GButton("QUIT", 750, 500, 150, 50, new ClickAction() {
				@Override
				public void onClick(MouseEvent event) {
					System.out.println("Pressing Quit");
					//go to MenuScreen
					//switchToScreen();
				}});
		add(quitButton);
	}

	/**
	 * This implements the timer count down for TIMED MODE (Not yet implemented)
	 */
	public void actionPerformed(ActionEvent e) {
		if(secs > 9) {
			myTime.setLabel("Time: " + mins + ":" + secs);
			secs--;
		} else if (secs <= 9) {
			myTime.setLabel("Time: " + mins + ":0" + secs);
			secs--;
		}
		if(secs < 0) {
			secs = 59;
			mins--;
		}
	}
	
	
}
