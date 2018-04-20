package thewetbandits.screens;

import acm.graphics.GLabel;
import thewetbandits.MatchThreeGame;
import thewetbandits.screens.MainGameplayScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimedGameModeScreen extends MainGameplayScreen {
	
	private MatchThreeGame game;
	private Timer countDownTimer = new Timer(1000, new ActionListener()
	{
		@Override
        public void actionPerformed(ActionEvent ae)
        {
			if(myTime != null) {
				myTime.setLabel(String.format("Time Left: %d:%02d", secs / 60, secs % 60));
			}
			if (secs == -1) {
				myTime.setLabel("Game Over");
				scoreTimer.stop();
				game.switchToScreen(Screens.GAME_OVER);
			}
			secs--;
        }
	});
	
	public TimedGameModeScreen(MatchThreeGame app) {
		super(app);
		game = app;
		run();
	}

	public void run() {
		displayTitle();
		displayScore();
		displayScore.setLabel("");
		this.mins = 0;
		this.secs = 10;
		myTime = new GLabel("", 350, 40);
		add(myTime);
		myTime.setFont("Bold-15");
		myTime.setColor(Color.WHITE);
		displayButton();
	}

	@Override
	public void onShow() {
		countDownTimer.setInitialDelay(3);
		countDownTimer.start();
		scoreTimer.start();
	}
}
