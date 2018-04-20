package thewetbandits.screens;

import acm.graphics.GLabel;
import thewetbandits.MatchThreeGame;
import thewetbandits.screens.MainGameplayScreen;

import java.awt.*;

public class LimitedMovesModeScreen extends MainGameplayScreen {

	public LimitedMovesModeScreen(MatchThreeGame app) {
		super(app);
		run();
	}
	
	public void run() {
		displayTitle();
		displayScore();
		displayMoves();
		scoreTimer.setInitialDelay(3);
		scoreTimer.start();
		displayButton();
	}
	
	/**
	 * Displays the time and moves left (These modes are not yet implemented)
	 */
	public void displayMoves()
	{
		GLabel moves = new GLabel("Moves left: 50", 400, 40);

		add(moves);
		moves.setFont("Bold-15");
		moves.setColor(Color.WHITE);
	}
}
