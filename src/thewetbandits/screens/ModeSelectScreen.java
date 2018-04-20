package thewetbandits.screens;


import acm.graphics.GImage;
import thewetbandits.MatchThreeGame;
import thewetbandits.screens.Screen;
import thewetbandits.screens.Screens;
import thewetbandits.utils.ClickAction;
import thewetbandits.utils.GImageButton;

import java.awt.event.MouseEvent;


public class ModeSelectScreen extends Screen {
	public static final int WINDOW_WIDTH = 1000;
	public static final int WINDOW_HEIGHT = 700;

	private MatchThreeGame game;

	public ModeSelectScreen(MatchThreeGame app) {
		super(app);
		game = app;
		run();
	}

	private void run() {
		GImage background = new GImage("boardBG.PNG" ,0, 0);
		background.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		add(background);
		displayMode();
	}
	
	/**
	 *  Creates all the game mode buttons
	 */
	public void displayMode() {
		GImageButton endlessMode = new GImageButton("endless.png", 400, 200, new ClickAction() {
			@Override
			public void onClick(MouseEvent event) {
				game.switchToScreen(Screens.GAMEPLAY_ENDLESS_SCREEN);
			}
		});
		add(endlessMode);
		GImageButton timedMode = new GImageButton("timed.png", 400, 300, new ClickAction() {
			@Override
			public void onClick(MouseEvent event) {
				game.switchToScreen(Screens.GAMEPLAY_TIMED_SCREEN);
			}
		});
		add(timedMode);
		GImageButton limitedMode = new GImageButton("limited.png", 400, 400, new ClickAction() {
			@Override
			public void onClick(MouseEvent event) {
				game.switchToScreen(Screens.GAMEPLAY_LIMITED_SCREEN);
			}
		});
		add(limitedMode);
	}
}

