package thewetbandits.screens;

import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GLabel;
import thewetbandits.MatchThreeGame;
import thewetbandits.utils.ClickAction;
import thewetbandits.utils.GImageButton;

public class GameOverScreen extends Screen{
	
	private MatchThreeGame game;
	private GLabel finalScore;
	private GImageButton mainMenu;

	public GameOverScreen(MatchThreeGame app) {
		super(app);
		game = app;
		run();
	}
	
	private void run () {
		add(Screens.GAMEPLAY_ENDLESS_SCREEN.boardBG);
		displayFinalScore();
		displayMainMenuButton();
	}
	
	private void displayFinalScore() {
		finalScore = new GLabel("Your final score was " + Screens.GAMEPLAY_TIMED_SCREEN.displayedScore, Screens.GAMEPLAY_ENDLESS_SCREEN.noMovesImage.getX(), Screens.GAMEPLAY_ENDLESS_SCREEN.noMovesImage.getY());
		add(finalScore);
		finalScore.setFont("Bold-48");
		finalScore.setColor(Color.WHITE);
	}
	
	private void displayMainMenuButton() {
		mainMenu = new GImageButton("quit.png", (int) finalScore.getX(), (int) finalScore.getY() + 200, new ClickAction() 
		{
			@Override
			public void onClick(MouseEvent event) {
				game.switchToScreen(Screens.MENU_SCREEN);
			}
		});
		add(mainMenu);
	}

}
