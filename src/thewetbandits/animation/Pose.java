package thewetbandits.animation;

import acm.graphics.GObject;

public class Pose {

	private int x, y;

	private int width, height;

	public Pose(int x, int y, int width, int sizeY) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = sizeY;
	}

	public static int clamp(int number, int min, int max) {
		if (number < min)
			return min;
		if (number > max)
			return max;
		return number;
	}

	public static double clamp(double number, double min, double max) {
		if (number < min)
			return min;
		if (number > max)
			return max;
		return number;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean reachedSize(GObject object) {
		boolean sizeY = this.height == (int) object.getSize().getHeight();
		boolean sizeX = this.width == (int) object.getSize().getWidth();
		return sizeY && sizeX;
	}

	public boolean reachedPos(GObject object){
		boolean posX = this.x == (int) object.getX();
		boolean posY = this.y == (int) object.getY();
		return posX && posY;
	}

	@Override
	public String toString() {
		return "Pose{" +
				"x=" + x +
				", y=" + y +
				", width=" + width +
				", height=" + height +
				'}';
	}
}
