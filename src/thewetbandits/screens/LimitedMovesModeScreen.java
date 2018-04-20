package thewetbandits.screens;

import acm.graphics.GLabel;
import thewetbandits.MatchThreeGame;

import java.awt.*;

public class LimitedMovesModeScreen extends MainGameplayScreen
{

	private int movesLeft;
	private GLabel moves;

	public LimitedMovesModeScreen(MatchThreeGame app)
	{
		super(app);
		movesLeft = 50;
		if(!isInitialized)
		{
			run();
		}
		this.updateMoves();
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
	 * Displays the time and moves left
	 */
	public void displayMoves()
	{
		moves = new GLabel("Moves left: " + this.movesLeft, 500, 75);

		add(moves);
		moves.setFont("Bold-25");
		moves.setColor(Color.WHITE);
	}

	public void subtractAndUpdate()
	{
		this.movesLeft--;
		this.updateMoves();
		if(this.movesLeft == 0)
		{
			this.displayEnd();
		}
	}

	public void updateMoves()
	{
		this.moves.setLabel("Moves left: " + this.movesLeft);
	}

	private void displayEnd()
	{
		GameOverScreen.score = this.board.getScore();
		this.application.switchToScreen(Screens.GAME_OVER);
	}
}
