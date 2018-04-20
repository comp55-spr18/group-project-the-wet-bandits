package thewetbandits.screens;

import acm.graphics.GImage;
import thewetbandits.MatchThreeGame;
import thewetbandits.utils.ClickAction;
import thewetbandits.utils.GImageButton;
import thewetbandits.utils.GParagraph;

import java.awt.event.MouseEvent;

	/**
	 * 
	 * @author John Thao
	 *
	 *		Context displays the set of instructions for the game, it
	 *		also consist of the back button to return to the MenuScreen again.		
	 *
	 */

public class InstructionsScreen extends Screen {
	public static final int WINDOW_WIDTH = 1000;
	public static final int WINDOW_HEIGHT = 700;
	private GImage stepone = new GImage("stepone.png", 50, 200);
	private GImage steptwo = new GImage("steptwo.png", 50, 250);
	private MatchThreeGame menu;

	public InstructionsScreen(MatchThreeGame app) {
		super(app);
		menu = app;
		run();
	}

	public void run() {
		GImage background = new GImage("boardBG.png" ,0, 0);
		background.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		add(background);
		tutorial();
		exit();
	}
	
	public void tutorial() {
		add(stepone);
		add(steptwo);
	}
	
	public void exit() {
		GImageButton exitButton = new GImageButton("x.png", 850, 100, new ClickAction() {
			@Override
			public void onClick(MouseEvent event) {
				menu.switchToScreen(Screens.MENU_SCREEN);
			}
		});
		add(exitButton);
	}
}
