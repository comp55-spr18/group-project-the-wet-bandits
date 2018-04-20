package thewetbandits.screens;

import acm.graphics.GImage;
import thewetbandits.MatchThreeGame;
import thewetbandits.screens.Screen;
import thewetbandits.screens.Screens;
import thewetbandits.utils.ClickAction;
import thewetbandits.utils.GImageButton;

import java.awt.event.MouseEvent;

public class ModeSelectScreen extends Screen
{
	public static final int WINDOW_WIDTH = 1000;
	public static final int WINDOW_HEIGHT = 700;

	private MatchThreeGame game;

	/**
	 * initializes the screen and adds it to the application
	 * 
	 * @param app
	 *            the application that this screen runs in
	 */
	public ModeSelectScreen(MatchThreeGame app)
	{
		super(app);
		game = app;
		run();
	}

	/**
	 * initializes the background and displays the buttons
	 */
	private void run()
	{
		GImage background = new GImage("boardBG.PNG", 0, 0);
		background.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		add(background);
		displayMode();
	}

	/**
	 * Creates all the game mode buttons
	 */
	public void displayMode()
	{

		/**
		 * Displays the endlessMode button, clicking on it enables player to play
		 * forever.
		 */
		GImageButton endlessMode = new GImageButton("endless.png", 400, 200, new ClickAction()
		{
			@Override
			public void onClick(MouseEvent event)
			{
				game.switchToScreen(Screens.GAMEPLAY_ENDLESS_SCREEN);
			}
		});
		add(endlessMode);

		/**
		 * Displays the timedMode button, clicking on it enables the player to play for
		 * a set time amount before the game ends.
		 */
		GImageButton timedMode = new GImageButton("timed.png", 400, 300, new ClickAction()
		{
			@Override
			public void onClick(MouseEvent event)
			{
				game.switchToScreen(Screens.GAMEPLAY_TIMED_SCREEN);
			}
		});
		add(timedMode);

		/**
		 * Displays the limitedMode button, clicking on it enables the player to play
		 * for a certain amount of moves.
		 */
		GImageButton limitedMode = new GImageButton("limited.png", 400, 400, new ClickAction()
		{
			@Override
			public void onClick(MouseEvent event)
			{
				game.switchToScreen(Screens.GAMEPLAY_LIMITED_SCREEN);
			}
		});
		add(limitedMode);

		/**
		 * Displays the backButton, clicking on it would take the user back to the
		 * menuScreen.
		 */
		GImageButton backButton = new GImageButton("x.png", 850, 100, new ClickAction()
		{
			@Override
			public void onClick(MouseEvent event)
			{
				game.switchToScreen(Screens.MENU_SCREEN);
			}
		});
		add(backButton);
	}
}
