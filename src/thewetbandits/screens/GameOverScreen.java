package thewetbandits.screens;

import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GLabel;
import thewetbandits.MatchThreeGame;
import thewetbandits.utils.ClickAction;
import thewetbandits.utils.GImageButton;

public class GameOverScreen extends Screen
{

	private MatchThreeGame game;
	private GLabel finalScore;
	private GImageButton mainMenu;

	/**
	 * base constructor that initializes the screen
	 * 
	 * @param app
	 *            the MatchThreeGame that this Screen will be added to
	 */
	public GameOverScreen(MatchThreeGame app)
	{
		super(app);
		game = app;
		run();
	}

	/**
	 * adds the background and displays the score and buttons
	 */
	private void run()
	{
		add(Screens.GAMEPLAY_ENDLESS_SCREEN.boardBG);
		displayFinalScore();
		displayMainMenuButton();
	}

	/**
	 * displays the user's final score
	 */
	private void displayFinalScore()
	{
		finalScore = new GLabel("Your final score was " + Screens.GAMEPLAY_TIMED_SCREEN.displayedScore,
				Screens.GAMEPLAY_ENDLESS_SCREEN.noMovesImage.getX(),
				Screens.GAMEPLAY_ENDLESS_SCREEN.noMovesImage.getY());
		add(finalScore);
		finalScore.setFont("Bold-48");
		finalScore.setColor(Color.WHITE);
	}

	/**
	 * displayed the quit button
	 */
	private void displayMainMenuButton()
	{
		mainMenu = new GImageButton("quit.png", (int) finalScore.getX(), (int) finalScore.getY() + 200,
				new ClickAction()
				{
					@Override
					public void onClick(MouseEvent event)
					{
						System.exit(0);
						// game.switchToScreen(Screens.MENU_SCREEN);
					}
				});
		add(mainMenu);
	}

}
