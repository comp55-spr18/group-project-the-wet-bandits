package thewetbandits.screens;

import acm.graphics.GLabel;
import thewetbandits.MatchThreeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimedGameModeScreen extends MainGameplayScreen {

	private MatchThreeGame game;
	private Timer countDownTimer = new Timer(1000, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent ae) {
			if (time != null) {
				time.setLabel(String.format("Time Left: %d:%02d", secs / 60, secs % 60));
			}
			if (secs < 0) {
				time.setLabel("Game Over");
				scoreTimer.stop();
				GameOverScreen.score = board.getScore();
				game.switchToScreen(Screens.GAME_OVER);
			}
			secs--;
		}
	});

	/**
	 * initializes this screen
	 *
	 * @param app the application that this screen runs in
	 */
	public TimedGameModeScreen(MatchThreeGame app) {
		super(app);
		game = app;
		if (!isInitialized)
			run();
	}

	/**
	 * displays title, score, time, and buttons
	 */
	public void run() {
		displayTitle();
		displayScore();
		displayScore.setLabel("");
		secs = 180;
		time = new GLabel("", 500, 75);
		add(time);
		time.setFont("Bold-25");
		time.setColor(Color.WHITE);
		displayButton();
		isInitialized = true;
	}

	/**
	 * starts the countdown timer and score timer
	 */
	@Override
	public void onShow() {
		countDownTimer.setInitialDelay(3);
		countDownTimer.start();
		scoreTimer.start();
	}

	/**
	 * stops the countdown timer
	 */
	@Override
	public void onHide() {
		countDownTimer.stop();
	}
}
