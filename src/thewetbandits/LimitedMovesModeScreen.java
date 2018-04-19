package thewetbandits;

import java.awt.Color;

import acm.graphics.GLabel;
import thewetbandits.screens.MainGameplayScreen;

public class LimitedMovesModeScreen extends MainGameplayScreen {

	public LimitedMovesModeScreen(MatchThreeGame app, int width, int height) {
		super(app, width, height);
		run();
	}
	
	public void run() {
		displayTitle();
		displayScore();
		displayMoves();
		scoreTimer.setInitialDelay(3);
		scoreTimer.start();
		displayPause();
		displayQuit();
	}
	
	/**
	 * Displays the time and moves left (These modes are not yet implemented)
	 */
	public void displayMoves()
	{
		GLabel movesAndTime = new GLabel("Moves left: 50", 400, 40);

		add(movesAndTime);
		movesAndTime.setFont("Bold-15");
		movesAndTime.setColor(Color.WHITE);
	}
}
