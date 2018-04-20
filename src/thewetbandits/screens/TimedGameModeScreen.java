package thewetbandits.screens;

import acm.graphics.GLabel;
import thewetbandits.MatchThreeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimedGameModeScreen extends MainGameplayScreen
{

	private Timer countdownTimer = new Timer(1001, new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent ae)
		{
			if(secs > 9)
			{
				myTime.setLabel("Time Left: " + mins + ":" + secs);
				secs--;
			}
			else
			{
				myTime.setLabel("Time Left: " + mins + ":0" + secs);
				secs--;
			}
			if(secs < 0)
			{
				secs = 59;
				mins--;
			}
			if(mins == 0 && secs == 0)
			{
				myTime.setLabel("Game Over");
				scoreTimer.stop();
			}
		}
	});

	/**
	 * initializes this screen
	 * 
	 * @param app
	 *            the application that this screen runs in
	 */
	public TimedGameModeScreen(MatchThreeGame app)
	{
		super(app);
		if(!isInitialized)
			run();
	}

	/**
	 * displays title, score, time, and buttons
	 */
	public void run()
	{
		displayTitle();
		displayScore();
		displayScore.setLabel("");
		this.mins = 3;
		myTime = new GLabel("", 500, 75);
		add(myTime);
		myTime.setFont("Bold-25");
		myTime.setColor(Color.WHITE);
		displayButton();
		isInitialized = true;
	}

	/**
	 * starts the countdown timer and score timer
	 */
	@Override
	public void onShow()
	{
		countdownTimer.setInitialDelay(3);
		countdownTimer.start();
		scoreTimer.start();
	}
	
	/**
	 * stops the countdown timer
	 */
	@Override
	public void onHide()
	{
		countdownTimer.stop();
	}
}
