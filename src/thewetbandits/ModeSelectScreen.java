package thewetbandits;


import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import thewetbandits.screens.MainGameplayScreen;
import thewetbandits.screens.Screen;


public class ModeSelectScreen extends Screen {
	public static final int WINDOW_WIDTH = 1000;
	public static final int WINDOW_HEIGHT = 700;

	private MatchThreeGame game;
	private GImage endlessMode = new GImage("endless.png", 400, 200);
	private GImage timedMode = new GImage("timed.png", 400, 300);
	private GImage limitedMode = new GImage("limited.png", 400, 400);

	public ModeSelectScreen(MatchThreeGame app) {
		super(app);
		game = app;
		run();
	}

	private void run() {
		GImage background = new GImage("background.gif" ,0, 0);
		background.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		add(background);
		displayMode();
	}
	
	/**
	 *  Creates all the game mode buttons
	 */
	public void displayMode() {
		add(endlessMode);
		add(timedMode);
		add(limitedMode);
	}
	
	@Override
	public void mouseReleased(MouseEvent event) {
	   super.mouseReleased(event);
	   
	   /**
	    *  Coordinates X and Y for endless mode
	    */
	   if(event.getX() >= 400 && event.getX() <= 610) {
		   if(event.getY() >= 200 && event.getY() <= 280) {
			   System.out.println("Release on Endless");
			   game.switchToScreen(new MainGameplayScreen(game, WINDOW_WIDTH, WINDOW_HEIGHT));
		   }
	   }
	   
	   /**
	    *  Coordinates X and Y for timed mode
	    */
	   if(event.getX() >= 400 && event.getX() <= 610) {
		   if(event.getY() >= 300 && event.getY() <= 380) {
			   System.out.println("Release on Timed");
			   game.switchToScreen(new TimedGameModeScreen(game, WINDOW_WIDTH, WINDOW_HEIGHT));
		   }
	   }

	   /**
	    *  Coordinates X and Y for limited mode
	    */
	   if(event.getX() >= 400 && event.getX() <= 610) {
		   if(event.getY() >= 400 && event.getY() <= 480) {
			   System.out.println("Release on Limited");
			   game.switchToScreen(new LimitedMovesModeScreen(game, WINDOW_WIDTH, WINDOW_HEIGHT));
		   }
	   }
	}
}

