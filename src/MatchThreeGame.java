import java.awt.event.MouseEvent;


/**
 * @author JacobFaulk
 * Created Mar 28, 2018
 */

public class MatchThreeGame extends GraphicsApplication
{
	public static final int WINDOW_WIDTH = 1000;
	public static final int WINDOW_HEIGHT = 700;
	private TestGameScreen testScreen;
	
	public void init()
	{
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	public void run()
	{
		System.out.println("Running class MatchThreeGame\nWe are only getting the bare bones down first.\nMostly backend stuff.\n");
		testScreen = new TestGameScreen(this);
		switchToScreen(testScreen);
	}

	public void switchToSome()
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		//System.out.println("MatchThreeGame clicked");
		super.mouseClicked(e);
	}
	
	

}
