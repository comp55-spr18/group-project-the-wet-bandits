import acm.program.GraphicsProgram;
import acm.graphics.GRect;
import acm.graphics.GLabel;

/**
 * 
 * @author John Thao
 * March 29, 2018
 *
 */

public class MenuScreen extends GraphicsProgram {
	//private MenuButton button;
	public static final int WINDOW_WIDTH = 1000;
	public static final int WINDOW_HEIGHT = 700;
	GRect playButton = new GRect(1, 2, 3, 4);
	GRect quitButton = new GRect(5, 6, 7, 8);
	GRect tutorialButton = new GRect(9, 10, 11, 12);
	
	
	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}
	
	public void run() {
		displayTitle();
		showPlayButton();
		showQuitButton();
		showTutorialButton();
	}
	
	/**
	 * Display title: "Three's A Company
	 * 
	 */
	private void displayTitle() {
		//GRect titleOutline = new GRect(200, 200, 100, 100);
		GLabel displayTitle = new GLabel("Three's A Company", 450, 150);
		
		//add(titleOutline);
		add(displayTitle);
	}

	/**
	 * Displays the playButton
	 */
	public GRect showPlayButton() {
		System.out.println("Play button.");
		return playButton;
	}
	
	/**
	 * Displays the quitButton
	 */
	public GRect showQuitButton() {
		System.out.println("Quit button.");
		return quitButton;
	}
	
	/**
	 * Display the tutorialButton
	 */
	public GRect showTutorialButton() {
		System.out.println("Tutorial button.");
		return tutorialButton;
	}
}