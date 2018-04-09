package thewetbandits;
import thewetbandits.MatchThreeGame;
import thewetbandits.MenuScreen;
import thewetbandits.Context;
import thewetbandits.screens.Screen;
import thewetbandits.screens.TestGameScreen;
import thewetbandits.utils.ClickAction;
import thewetbandits.utils.GButton;

import java.awt.event.MouseEvent;
import acm.graphics.*;
import java.awt.Color;


public class MenuScreen extends Screen {
	public static final int WINDOW_WIDTH = 700;
	public static final int WINDOW_HEIGHT = 700;
	private GButton playButton;
	private GButton quitButton;
	private GButton tutorialButton;
	private MatchThreeGame game;
	
	public MenuScreen(MatchThreeGame app) {
		super(app);
		game = app;
		run();
	}
	
	public void run() {
		displayTitle();
		showPlayButton();
		showQuitButton();
		showTutorialButton();
	}
	
	/**
	 * Display title: "Three's A Company
	 */
	public void displayTitle() {
		GLabel displayTitle = new GLabel("Three's A Company", 40, 150);

		add(displayTitle);
		displayTitle.setColor(Color.RED);
		displayTitle.setFont("Bradley Hand ITC-Italic-75");
	}

	/**
	 * Displays the playButton
	 */
	public void showPlayButton() {
		playButton = new GButton("PLAY", 300, 315, 115, 50, new ClickAction() {
				@Override
				public void onClick(MouseEvent event) {
					game.switchToScreen(new TestGameScreen(game, WINDOW_WIDTH, WINDOW_HEIGHT));
				}});
		add(playButton);
	};
	
	/**
	 * Displays the quitButton
	 */
	public void showQuitButton() {
		quitButton = new GButton("QUIT", 300, 440, 115, 50, new ClickAction() {
				@Override
				public void onClick(MouseEvent event) {
					System.out.println("click on quit");
					System.exit(0);
				}});
		add(quitButton);
	};
	
	/**
	 * Display the tutorialButton
	 */
	public void showTutorialButton() {
		tutorialButton = new GButton("?", 600, 615, 50, 50, new ClickAction() {
			@Override
			public void onClick(MouseEvent event) {
				System.out.println("click on tutorial");
				game.switchToScreen(new Context(game));
			}});
		add(tutorialButton);
	}
}

