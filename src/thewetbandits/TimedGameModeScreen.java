package thewetbandits;

import acm.graphics.GLabel;
import thewetbandits.screens.MainGameplayScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimedGameModeScreen extends MainGameplayScreen {
	
	private Timer countDownTimer = new Timer(1000, new ActionListener()
	{
		@Override
        public void actionPerformed(ActionEvent ae)
        {
			if(secs > 9)
			{
				myTime.setLabel("Time Left: " + mins + ":" + secs);
				secs--;
			}
			else {
				myTime.setLabel("Time Left: " + mins + ":0" + secs);
				secs--;
			}
			if(secs < 0)
			{
				secs = 59;
				mins--;
			}
			if (mins == 0 && secs == 0) {
				myTime.setLabel("Game Over");
				scoreTimer.stop();
			}
        }
	});
	
	public TimedGameModeScreen(MatchThreeGame app) {
		super(app);
		run();
	}

	public void run() {
		displayTitle();
		displayScore();
		displayScore.setLabel("");
		this.mins = 3;
		myTime = new GLabel("", 350, 40);
		add(myTime);
		myTime.setFont("Bold-15");
		myTime.setColor(Color.WHITE);
	//	countDownTimer.setInitialDelay(3);
	//	countDownTimer.start();
		displayButton();
	}

	@Override
	public void onShow() {
		clockTimer.setInitialDelay(3);
		clockTimer.start();
		scoreTimer.start();
	}
}
