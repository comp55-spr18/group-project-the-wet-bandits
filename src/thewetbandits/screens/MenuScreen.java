package thewetbandits.screens;
import thewetbandits.GraphicsApplication;
import thewetbandits.MatchThreeGame;
import thewetbandits.MenuButton;
import java.awt.event.MouseEvent;
import acm.graphics.*;
import java.awt.Color;

/**
 * 
 * @author John Thao
 * March 29, 2018
 *
 */

public class MenuScreen extends Screen {
	private MenuButton button;
	
	public MenuScreen(MatchThreeGame app) {
		super(app);
	}

	public MenuButton playButton() {
		
		return button;
	}
	
	public MenuButton quitButton() {
		
		return button;
	}
	
	public MenuButton tutorialButton() {
		
		return button;
	}

}

