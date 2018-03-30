import java.awt.Color;
import java.util.Random;

/**
 * @author JacobFaulk Created Mar 30, 2018
 */
public class TestGameScreen extends Screen
{
	private GamePiece[][] testBoard;
	private Random rand = new Random();

	public TestGameScreen(MatchThreeGame app)
	{
		super(app);
		this.addComponents();
	}

	private void addComponents()
	{
		testBoard = new GamePiece[8][8];
		Color tempColor = Color.BLACK;
		for(int r = 0; r < testBoard.length; r++)
		{
			for(int c = 0; c < testBoard[0].length; c++)
			{
				switch(rand.nextInt(4))
				{
				case 0:
					tempColor = Color.RED;
					break;
				case 1:
					tempColor = Color.BLUE;
					break;
				case 2:
					tempColor = Color.GREEN;
					break;
				default:
					tempColor = Color.YELLOW;
				}
				testBoard[r][c] = new GamePiece(30 * (r + 1), 30 * (c + 1), 30, tempColor);
				this.add(testBoard[r][c]);
			}
		}
	}
}
