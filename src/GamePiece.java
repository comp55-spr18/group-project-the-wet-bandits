import java.awt.Color;
import java.awt.Graphics;



/*
 * 
 * @author Miguel
 */

public class GamePiece 
{
	private int initialXPos, initialYPos, width, length;
	
	public void paint (Graphics g)
	{
		//Starting colors for the gamepiece will be blue, red, green, and yellow.
		g.setColor(Color.BLUE);
		g.fillOval(10, 20, 100, 100);
	}

}
