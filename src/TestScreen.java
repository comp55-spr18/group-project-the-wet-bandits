import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GObject;
import acm.graphics.GRect;

/**
 * A test screen testing the {@link Screen} functionality
 * 
 * @author Austin
 *
 */
public class TestScreen extends Screen {
	private GButton rect;

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

		this.add(rect);
	}
}
