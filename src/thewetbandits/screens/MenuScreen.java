package thewetbandits.screens;
import acm.program.GraphicsProgram;
import thewetbandits.GraphicsApplication;
import thewetbandits.MenuButton;
import acm.graphics.GRect;
import acm.graphics.GLabel;
import java.awt.Color;

/**
 * 
 * @author John Thao
 * March 29, 2018
 *
 */

public class MenuScreen extends GraphicsApplication {
	private MenuButton button;
	public static final int WINDOW_WIDTH = 1000;
	public static final int WINDOW_HEIGHT = 700;	
	
	
	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		requestFocus();
	}
	
	public void run() {
		displayTitle();
		showPlayButton();
		showQuitButton();
		showTutorialButton();
	}
	
	/**
	 * Display title: "Three's A Company
	 */
	private void displayTitle() {
		GLabel displayTitle = new GLabel("Three's A Company", 175, 150);

		add(displayTitle);
		displayTitle.setColor(Color.RED);
		displayTitle.setFont("Bradley Hand ITC-Italic-75");
	}

	/**
	 * Displays the playButton
	 */
	public void showPlayButton() {
		GRect playButton = new GRect(435, 315, 115, 50);
		GLabel displayPlay = new GLabel("PLAY", 450, 350);
		
		add(playButton);
		add(displayPlay);
		displayPlay.setFont("Bradley Hand ITC-Bold-30");
		
	}
	
	/**
	 * Displays the quitButton
	 */
	public void showQuitButton() {
		GRect quitButton = new GRect(435, 440, 115, 50);
		GLabel displayQuit = new GLabel("QUIT", 450, 475);
		
		add(quitButton);
		add(displayQuit);
		displayQuit.setFont("Bradley Hand ITC-Bold-30");
	}
	
	/**
	 * Display the tutorialButton
	 */
	public void showTutorialButton() {
		GRect tutorialButton = new GRect(885, 615, 50, 50);
		GLabel displayTutorial = new GLabel("?", 900, 650);
		
		add(tutorialButton);
		add(displayTutorial);
		displayTutorial.setColor(Color.RED);
		displayTutorial.setFont("Arial-Bold-30");
	}
}
