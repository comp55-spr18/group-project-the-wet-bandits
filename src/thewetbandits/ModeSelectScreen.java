package thewetbandits;

import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import thewetbandits.screens.MainGameplayScreen;
import thewetbandits.screens.Screen;
import thewetbandits.utils.AudioPlayer;
import thewetbandits.utils.ClickAction;
import thewetbandits.utils.GButton;

public class ModeSelectScreen extends Screen {
	public static final int WINDOW_WIDTH = 1000;
	public static final int WINDOW_HEIGHT = 700;

	private MatchThreeGame game;
	private GButton endlessModeButton;
	private GButton timedModeButton;
	private GButton limitedMovesModeButton;
	private Color buttonColor = new Color(255, 154, 0);

	public ModeSelectScreen(MatchThreeGame app) {
		super(app);
		game = app;
		run();
	}

	private void run() {
		GImage background = new GImage("background.gif" ,0, 0);
		background.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		add(background);
		displayEndlessModeButton();
		displayTimedModeButton();
		displayLimitedMovesModeButton();
	}
	
	public void displayEndlessModeButton() {
		GImage endlessMode = new GImage("endless.png", 100, 200);
		add(endlessMode);
		
		endlessModeButton = new GButton("Endless", 400, 315, 115, 50, new ClickAction() {
				@Override
				public void onClick(MouseEvent event) {
					game.switchToScreen(new MainGameplayScreen(game, WINDOW_WIDTH, WINDOW_HEIGHT));
				}
		});
		endlessModeButton.setColor(Color.WHITE);
		endlessModeButton.setFillColor(buttonColor);
		add(endlessModeButton);
	}
	
	public void displayTimedModeButton() {
		GImage timedMode = new GImage("timed.png", 100, 300);
		add(timedMode);
		
		timedModeButton = new GButton("Timed", 400, 415, 115, 50, new ClickAction() {
				@Override
				public void onClick(MouseEvent event) {
					game.switchToScreen(new TimedGameModeScreen(game, WINDOW_WIDTH, WINDOW_HEIGHT));
				}
		});
		timedModeButton.setColor(Color.WHITE);
		timedModeButton.setFillColor(buttonColor);
		add(timedModeButton);
	}
	
	public void displayLimitedMovesModeButton() {
		GImage limitedMode = new GImage("limited.png", 100, 400);
		add(limitedMode);
		
		limitedMovesModeButton = new GButton("Limited Moves", 400, 515, 115, 50, new ClickAction() {
				@Override
				public void onClick(MouseEvent event) {
					game.switchToScreen(new LimitedMovesModeScreen(game, WINDOW_WIDTH, WINDOW_HEIGHT));
				}
		});
		limitedMovesModeButton.setColor(Color.WHITE);
		limitedMovesModeButton.setFillColor(buttonColor);
		add(limitedMovesModeButton);
	}
}

