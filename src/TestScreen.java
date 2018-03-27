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
	private boolean test = false;

	public TestScreen(MainApplication app) {
		super(app);
		this.addComponents();
	}

	private void addComponents() {
		rect = new GButton("WOW", 200, 200, 200, 200);
		rect.setFillColor(Color.RED);

		this.add(rect);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = this.application.getElementAt(e.getX(), e.getY());
		if (obj == rect) {
			if (test)
				this.application.switchToSome();
			if (!test) {
				test = true;
				GRect rect = new GRect(0, 0, 10, 10);
				rect.setFillColor(Color.GREEN);
				this.add(rect);
			}
		}
	}
}
