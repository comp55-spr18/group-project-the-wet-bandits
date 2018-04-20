package thewetbandits.screens;

import acm.graphics.GLabel;
import thewetbandits.MatchThreeGame;

import java.awt.*;

public class LimitedMovesModeScreen extends MainGameplayScreen
{

	/**
	 * base constructor that initializes the screen
	 * 
	 * @param app
	 *            the application that this screen runs in
	 */
	public LimitedMovesModeScreen(MatchThreeGame app)
	{
		super(app);
		if(!isInitialized)
			run();
	}

	/**
	 * displays the title, score, number of moves dialogue, and starts the timer for
	 * the score updater
	 */
	public void run()
	{
		displayTitle();
		displayScore();
		displayMoves();
		scoreTimer.setInitialDelay(3);
		scoreTimer.start();
		displayButton();
		isInitialized = true;
	}

	/**
	 * Displays the time and moves left TODO make this one actually work
	 */
	public void displayMoves()
	{
		GLabel moves = new GLabel("Moves left: 50", 400, 40);

		add(moves);
		moves.setFont("Bold-15");
		moves.setColor(Color.WHITE);
	}
}
