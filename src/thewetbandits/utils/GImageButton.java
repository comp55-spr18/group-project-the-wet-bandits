package thewetbandits.utils;

import acm.graphics.GCompound;
import acm.graphics.GImage;

import java.awt.event.MouseEvent;

public class GImageButton extends GCompound implements Clickable {

	private static final long serialVersionUID = -4303608991720369295L;

	private ClickAction action;

	private GImage image;

	public GImageButton(String image, int x, int y, ClickAction action) {
		this.image = new GImage(image, 0, 0);
		this.setLocation(x, y);
		this.action = action;
		add(this.image);
	}

	public GImageButton(String image, int x, int y) {
		this(image, x, y, null);
	}

	@Override
	public double getWidth() {
		return image.getWidth();
	}

	@Override
	public double getHeight() {
		return image.getHeight();
	}

	@Override
	public void onClick(MouseEvent event) {
		if (action != null)
			action.onClick(event);
	}
}
