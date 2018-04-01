package thewetbandits.screens;
import java.awt.Color;
import java.awt.event.MouseEvent;

//import thewetbandits.GamePiece;
import thewetbandits.MatchThreeGame;
import thewetbandits.utils.ClickAction;
import thewetbandits.utils.GButton;

/**
 * A test screen testing the {@link Screen} functionality
 * 
 * @author Austin
 *
 */
public class TestScreen extends Screen {
	private GButton rect;

//	private GamePiece piece;

	public TestScreen(MatchThreeGame app) {
		super(app);
		this.addComponents();
	}

	private void addComponents() {
		rect = new GButton("WOW", 200, 200, 200, 200);
		rect.setFillColor(Color.RED);

		rect.setAction(new ClickAction() {
			@Override
			public void onClick(MouseEvent event) {
				System.out.println("You clicked the thing!");
			}
		});
//		piece = new GamePiece(10,10,20,Color.BLUE);
//		this.add(piece);
		this.add(rect);
	}
}
