package thewetbandits;

import java.awt.event.MouseEvent;

import thewetbandits.screens.Screen;
import thewetbandits.MenuScreen;
import thewetbandits.utils.ClickAction;
import thewetbandits.utils.GButton;
import thewetbandits.utils.GParagraph;

public class Context extends Screen {
	private GParagraph tutorial;
	private GButton exit;
	private MatchThreeGame menu;
	

	public Context(MatchThreeGame app) {
		super(app);
		menu = app;
		run();
	}

	public void run() {
		tutorial();
		exit();
	}
	
	
	public void tutorial() {
		tutorial = new GParagraph("-Minimum of 3 jewels in a row to get a match."
				+ "\n-Scoring matches in a row will earn you a score multiplier.", 150, 250);
		tutorial.setFont("Bradley Hand ITC-Bold-22");
		add(tutorial);
	}
	
	public void exit() {
		exit = new GButton("X", 850, 75, 50, 50,  new ClickAction() {
			@Override
			public void onClick(MouseEvent event) {
				System.out.println("click on tutorial");
				menu.switchToScreen(new MenuScreen(menu));
			}});
		add(exit);
	}
}
