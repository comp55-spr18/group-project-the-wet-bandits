package thewetbandits;
import thewetbandits.MatchThreeGame;
import thewetbandits.MenuScreen;
import thewetbandits.Context;
import thewetbandits.screens.Screen;
import thewetbandits.screens.MainGameplayScreen;
import thewetbandits.utils.ClickAction;
import thewetbandits.utils.GButton;

import java.awt.event.MouseEvent;
import acm.graphics.*;
import java.awt.Color;


public class MenuScreen extends Screen {
	public static final int WINDOW_WIDTH = 1000;
	public static final int WINDOW_HEIGHT = 700;
	private GButton playButton;
	private GButton quitButton;
	private GButton tutorialButton;
	private MatchThreeGame game;
	private Color buttonColor = new Color(0, 0, 125);
	
	public MenuScreen(MatchThreeGame app) {
		super(app);
		game = app;
		run();
	}
	
	public void run() {
		GImage background = new GImage("background.gif" ,0, 0);
		background.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		add(background);
		
		displayTitle();
		showPlayButton();
		showQuitButton();
		showTutorialButton();
	}
	
	/**
	 * Display title: "Three's A Company
	 */
	public void displayTitle() {
		GLabel displayTitle = new GLabel("Three's A Company", 175, 150);

		add(displayTitle);
		displayTitle.setColor(Color.BLUE);
		displayTitle.setFont("Bradley Hand ITC-Italic-75");
	}

	/**
	 * Displays the playButton, clicking on it would take you to the game
	 */
	public void showPlayButton() {
		playButton = new GButton("PLAY", 450, 315, 115, 50, new ClickAction() {
				@Override
				public void onClick(MouseEvent event) {
					game.switchToScreen(new MainGameplayScreen(game, WINDOW_WIDTH, WINDOW_HEIGHT));
				}});
		playButton.setColor(Color.WHITE);
		playButton.setFillColor(buttonColor);
		add(playButton);
	};
	
	/**
	 * Displays the quitButton, clicking on it would exit out of the game and close it
	 */
	public void showQuitButton() {
		quitButton = new GButton("QUIT", 450, 440, 115, 50, new ClickAction() {
				@Override
				public void onClick(MouseEvent event) {
					System.out.println("click on quit");
					System.exit(0);
				}});
		quitButton.setColor(Color.WHITE);
		quitButton.setFillColor(buttonColor);
		add(quitButton);
	};
	
	/**
	 * Display the tutorialButton, clicking on it would bring you to the instructions
	 */
	public void showTutorialButton() {
		tutorialButton = new GButton("?", 900, 615, 50, 50, new ClickAction() {
			@Override
			public void onClick(MouseEvent event) {
				System.out.println("click on tutorial");
				game.switchToScreen(new Context(game));
			}});
		tutorialButton.setColor(Color.WHITE);
		tutorialButton.setFillColor(buttonColor);
		add(tutorialButton);
	}
}
