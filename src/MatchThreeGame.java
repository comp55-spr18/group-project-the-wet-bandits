import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * @author JacobFaulk
 * Created Mar 28, 2018
 */

public class MatchThreeGame extends GraphicsApplication
{
	public static final int WINDOW_WIDTH = 1000;
	public static final int WINDOW_HEIGHT = 700;
	private TestScreen testScreen;

	
	public void init()
	{
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	public void run()
	{
		System.out.println("Running class MatchThreeGame");
		//testScreen = new TestScreen(this);
	}

}
