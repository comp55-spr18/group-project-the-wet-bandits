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
	private String music = "music.mp3";
	private Color buttonColor = new Color(0, 0, 125);

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
		music();
	}
	
	public void displayEndlessModeButton() {
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
		timedModeButton = new GButton("Timed", 400, 415, 115, 50, new ClickAction() {
				@Override
				public void onClick(MouseEvent event) {
					game.switchToScreen(new MainGameplayScreen(game, WINDOW_WIDTH, WINDOW_HEIGHT));
				}
		});
		timedModeButton.setColor(Color.WHITE);
		timedModeButton.setFillColor(buttonColor);
		add(timedModeButton);
	}
	
	public void displayLimitedMovesModeButton() {
		limitedMovesModeButton = new GButton("Endless", 400, 515, 115, 50, new ClickAction() {
				@Override
				public void onClick(MouseEvent event) {
					game.switchToScreen(new MainGameplayScreen(game, WINDOW_WIDTH, WINDOW_HEIGHT));
				}
		});
		limitedMovesModeButton.setColor(Color.WHITE);
		limitedMovesModeButton.setFillColor(buttonColor);
		add(limitedMovesModeButton);
	}

	private void music() {
		AudioPlayer audio = AudioPlayer.getInstance();
		audio.playSound("", music, true);
	}

}
