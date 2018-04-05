package thewetbandits;
import thewetbandits.GraphicsApplication;
import thewetbandits.MatchThreeGame;
import thewetbandits.MenuButton;
import java.awt.event.MouseEvent;
import acm.graphics.*;
import java.awt.Color;


public class MenuButton extends GraphicsApplication {
	public static final int WINDOW_WIDTH = 1000;
	public static final int WINDOW_HEIGHT = 700;
	int locX;
	int locY;

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

	public void mousePressed(MouseEvent e) {
		locX = e.getX();
		locY = e.getY();
		
		// X and Y for Play button
		if(locX >= 435 && locX <= 550) {
			if(locY >= 315 && locY <= 365){
				System.out.println("Clicking on play.");
			}
		}
		
		// X and Y for Quit button
		if(locX >= 435 && locX <= 550) {
			if(locY >= 440 && locY <= 490){
				System.out.println("Clicking on quit.");
			}
		}
		
		// X and Y for Tutorial button
		if(locX >= 885 && locX <= 935) {
			if(locY >= 615 && locY <= 665){
				System.out.println("Clicking on tutorial.");
			}
		}
	}

	/**
	 * Display title: "Three's A Company
	 */
	public void displayTitle() {
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
