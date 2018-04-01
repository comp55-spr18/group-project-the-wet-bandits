package thewetbandits;

import java.awt.Color;
import java.io.File;
import java.util.Random;

import acm.graphics.GCompound;
import acm.graphics.GImage;

public class GamePiece extends GCompound {

	private static final Random random = new Random();

	private Color color;

	private GImage image;
	private GImage imageAnimated;

	private int x, y, size;
	
	private boolean active = false;

	public GamePiece(int x, int y, int size) {
		this(x, y, size, getRandomColor());
	}

	public GamePiece(int x, int y, int size, Color color) {
		this.color = color;
		this.x = x;
		this.y = y;
		this.size = size;
		this.initImage();
	}

	private static Color getRandomColor() {
		return Color.values()[random.nextInt(Color.values().length)];
	}

	/**
	 * Initializes the image in the compound
	 */
	private void initImage() {
		this.image = new GImage(this.color.toString().toLowerCase() + "_gem.png");
		this.imageAnimated = new GImage(this.color.toString().toLowerCase()+"_gem_animated.gif");
		this.imageAnimated.setLocation(this.x, this.y);
		this.imageAnimated.setSize(this.size, this.size);
		this.image.setLocation(this.x, this.y);
		this.image.setSize(this.size, this.size);
		updateImage();
	}
	
	/**
	 * Update the compound to display the animated or static image
	 */
	private void updateImage() {
		remove(this.image);
		remove(this.imageAnimated);
		add(active ? this.imageAnimated : this.image);
	}
	
	/**
	 * Toggles the active state of the image
	 */
	public void toggleActive() {
		this.active = !this.active;
		this.updateImage();
	}

	/**
	 * An enum representing the color of the piece
	 * @author Austin
	 *
	 * Created; Mar 31, 2018
	 */
	public enum Color {
		YELLOW(new java.awt.Color(250, 240, 66)), GREEN(new java.awt.Color(67, 153, 58)), BLUE(
				new java.awt.Color(24, 30, 219)), RED(java.awt.Color.RED);

		private java.awt.Color color;

		private Color(java.awt.Color color) {
			this.color = color;
		}

		/**
		 * Gets the object's raw color
		 * @return The object's raw color
		 */
		public java.awt.Color getColor() {
			return this.color;
		}
	}
}
