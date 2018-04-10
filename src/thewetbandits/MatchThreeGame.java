package thewetbandits;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import thewetbandits.screens.MovementTestScreen;
import thewetbandits.screens.MainGameplayScreen;

/**
 * @author Jacob Faulk Created Mar 28, 2018
 */

public class MatchThreeGame extends GraphicsApplication
{
	public static final int WINDOW_WIDTH = 1000;
	public static final int WINDOW_HEIGHT = 700;
	private MainGameplayScreen testScreen = new MainGameplayScreen(this, WINDOW_WIDTH, WINDOW_HEIGHT);
	private MenuScreen testMenu = new MenuScreen(this);

	/**
	 * initializes MatchThreeGame according to the specified width and height
	 */
	public void init()
	{
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	/**
	 * sets testScreen as the main screen and draws it
	 */
	public void run()
	{
		// testScreen = new TestGameScreen(this, WINDOW_WIDTH, WINDOW_HEIGHT);
		//switchToScreen(testScreen);
		switchToScreen(testMenu);
	}

	/**
	 * default mousePressed() method. it doesn't do anything yet (as of Mar 31,
	 * 2018)
	 * 
	 * @param e
	 *            the event specifying the details of the click
	 */
	@Override
	public void mousePressed(MouseEvent e)
	{
		super.mouseClicked(e);
	}
}
