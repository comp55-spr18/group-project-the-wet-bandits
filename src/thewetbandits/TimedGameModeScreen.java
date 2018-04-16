package thewetbandits;

import java.awt.event.ActionEvent;
import javax.swing.*;
import acm.graphics.GLabel;
import thewetbandits.screens.MainGameplayScreen;
import thewetbandits.utils.GButton;

public class TimedGameModeScreen extends MainGameplayScreen {
	public TimedGameModeScreen(MatchThreeGame app, int width, int height) {
		super(app, width, height);
		run();
	}
	
	public void run() {
		displayTitle();
		displayScore();
		displayScore.setLabel("");
		this.mins = 3;
		myTime = new GLabel("", 350, 40);
		add(myTime);
		someTimerVar.setInitialDelay(3);
		someTimerVar.start();
		displayPause();
		displayQuit();
	}
	
	public void actionPerformed(ActionEvent e) {
		displayScore.setLabel("Score: " + board.getScore());
		if(secs > 9)
		{
			myTime.setLabel("Time Left: " + mins + ":" + secs);
			secs--;
		}
		else if(secs <= 9)
		{
			myTime.setLabel("Time Left: " + mins + ":0" + secs);
			secs--;
		}
		if(secs < 0)
		{
			secs = 59;
			mins--;
		}
		if (mins == 0 && secs == 00) {
			myTime.setLabel("Game Over");
			someTimerVar.stop();
		}
	}
}
