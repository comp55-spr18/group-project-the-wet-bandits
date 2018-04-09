package thewetbandits;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import thewetbandits.screens.MovementTestScreen;
import thewetbandits.screens.TestGameScreen;

/**
 * @author Jacob Faulk Created Mar 28, 2018
 */

public class MatchThreeGame extends GraphicsApplication
{
	public static final int WINDOW_WIDTH = 700;
	public static final int WINDOW_HEIGHT = 700;
	private TestGameScreen testScreen = new TestGameScreen(this, WINDOW_WIDTH, WINDOW_HEIGHT);

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
		switchToScreen(testScreen);
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
