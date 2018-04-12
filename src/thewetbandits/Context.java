package thewetbandits;

import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import thewetbandits.screens.Screen;
import thewetbandits.MenuScreen;
import thewetbandits.utils.AudioPlayer;
import thewetbandits.utils.ClickAction;
import thewetbandits.utils.GButton;
import thewetbandits.utils.GParagraph;

	/**
	 * 
	 * @author John Thao
	 *
	 *		Context displays the set of instructions for the game, it
	 *		also consist of the back button to return to the MenuScreen again.		
	 *
	 */

public class Context extends Screen {
	public static final int WINDOW_WIDTH = 1000;
	public static final int WINDOW_HEIGHT = 700;
	private GParagraph tutorial;
	private GButton exit;
	private MatchThreeGame menu;
	private Color buttonColor = new Color(0,0, 125);
	AudioPlayer audio = AudioPlayer.getInstance();
	private String music = "music.mp3";

	public Context(MatchThreeGame app) {
		super(app);
		menu = app;
		run();
	}

	public void run() {
		GImage background = new GImage("background.gif" ,0, 0);
		background.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		audio.stopSound("", music);
		add(background);
		tutorial();
		exit();
	}
	
	
	public void tutorial() {
		tutorial = new GParagraph("-Minimum of 3 jewels in a row to get a match."
				+ "\n-Scoring matches in a row will earn you a score multiplier.", 150, 250);
		tutorial.setFont("Bradley Hand ITC-Bold-22");
		tutorial.setColor(Color.WHITE);
		add(tutorial);
	}
	
	public void exit() {
		exit = new GButton("X", 850, 75, 50, 50,  new ClickAction() {
			@Override
			public void onClick(MouseEvent event) {
				System.out.println("click on tutorial");
				menu.switchToScreen(new MenuScreen(menu));
			}});
		exit.setColor(Color.WHITE);
		exit.setFillColor(buttonColor);
		add(exit);
	}
}
