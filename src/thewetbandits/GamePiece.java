package thewetbandits;

import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GOval;
import acm.graphics.GPoint;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GamePiece extends GCompound
{

	private static final int MOVEMENT_SPEED = 5;
	private static final int MOVEMENT_FREQUENCY = 13;
	private static final Random random = new Random();

	// Store a list of weak references (so the pieces can be garbage collected
	// correctly) of pieces to update
	private static final ArrayList<WeakReference<GamePiece>> pieces = new ArrayList<>();
	private static Timer updateTimer;

	static
	{
		updateTimer = new Timer(MOVEMENT_FREQUENCY, new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// Timers are async so synchronize access to the array list so we don't break
				// everything with CMEs
				synchronized(pieces)
				{
					Iterator<WeakReference<GamePiece>> iterator = pieces.iterator();
					while(iterator.hasNext())
					{
						WeakReference<GamePiece> pieceReference = iterator.next();
						GamePiece piece = pieceReference.get();
						if(piece == null)
						{
							// The piece has been garbage collected, remove it from the list
							iterator.remove();
						}
						else
						{
							if(piece.currentPoint != null)
								piece.updatePose();
						}
					}
				}
			}
		});
		updateTimer.start();
	}

	private Color color;

	private GImage image;
	private GImage imageAnimated;
	private GOval testOval;

	private int x, y, size, r, c;

	private boolean active = false;

	private GPoint currentPoint;

	private ArrayList<GPoint> locations = new ArrayList<>();

	private Runnable animationCallback;

	private static int nextId = 1;
	private int id = nextId++;

	/**
	 * Constructor where color and image are randomly chosen out of a predefined set
	 *
	 * @param x
	 *            the x position of the piece
	 * @param y
	 *            the y position of the piece
	 * @param size
	 *            the width and height of the piece
	 */
	public GamePiece(int x, int y, int size, int r, int c)
	{
		this(x, y, size, getRandomColor(), r, c);
	}

	public GamePiece(int spaceSize, int size, int r, int c)
	{
		this(spaceSize * (r + 1), spaceSize * (c + 1), size, r, c);
	}

	public GamePiece(int x, int y, int size, Color color, int r, int c)
	{
		this.color = color;
		this.x = x;
		this.y = y;
		this.size = size;
		this.initImage();
		this.r = r;
		this.c = c;
		// Add the piece to the list of pieces to update
		synchronized(pieces)
		{
			pieces.add(new WeakReference<>(this));
		}
	}

	private static Color getRandomColor()
	{
		return Color.values()[random.nextInt(Color.values().length)];
	}

	public static boolean arePiecesAnimating()
	{
		for(WeakReference<GamePiece> ref : pieces)
		{
			GamePiece p = ref.get();
			if(p != null)
			{
				if(p.animating())
					return true;
			}
		}
		return false;
	}

	public Color setGemColor(GamePiece[][] b)
	{
		return Color.values()[random.nextInt(Color.values().length)];
	}

	public void reposition(int x, int y, int size)
	{
		this.x = x;
		this.y = y;
		this.size = size;
		this.image.setSize(size, size);
		this.imageAnimated.setSize(size, size);
		this.updateImage();
		this.setLocation(x, y);
	}

	public void updateRowCol(int r, int c)
	{
		this.r = r;
		this.c = c;
	}

	public int getR()
	{
		return r;
	}

	public int getC()
	{
		return c;
	}

	private void initImage()
	{
		this.image = new GImage(this.color.toString().toLowerCase() + "_gem.png");
		this.imageAnimated = new GImage(this.color.toString().toLowerCase() + "_gem_animated.gif");
		this.imageAnimated.setSize(this.size, this.size);
		this.image.setSize(this.size, this.size);
		this.testOval = new GOval(size, size);
		this.testOval.setColor(this.color.getColor());
		updateImage();
		this.setLocation(this.x, this.y);
	}

	private void updateImage()
	{
		remove(this.image);
		remove(this.imageAnimated);
		add(active ? this.imageAnimated : this.image);
	}

	public void clearPiece()
	{
		remove(this.image);
		remove(this.imageAnimated);
		add(testOval);
	}

	public void toggleActive()
	{
		this.active = !this.active;
		this.updateImage();
	}

	public Color getColorType()
	{
		return color;
	}

	public boolean animating()
	{
		return !this.locations.isEmpty() || this.currentPoint != null;
	}

	private void updatePose()
	{
		GPoint point = new GPoint(this.getX(), this.getY());
		double distance = calcDistance(point, this.currentPoint);
		if(distance < 4) {
			this.setLocation(this.currentPoint.getX(), this.currentPoint.getY());
			this.currentPoint = this.getNextPoint();
			this.runCallback();
		} else {
			this.movePolar(MOVEMENT_SPEED, calculateAngle(point, this.currentPoint));
		}
	}

	private void runCallback(){
		if(this.currentPoint == null)
			if(this.animationCallback != null)
				this.animationCallback.run();
	}

	private GPoint getNextPoint(){
		if(!this.locations.isEmpty())
			return this.locations.remove(0);
		else
			return null;
	}

	private double calcDistance(GPoint p1, GPoint p2){
		return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getX() - p2.getX(), 2));
	}

	private double calculateAngle(GPoint current, GPoint desired){
		double dx = desired.getX() - current.getX();
		double dy = desired.getY() - current.getY();
		double angle = -1 * Math.atan2(dy, dx);
		angle = (angle > 0 ? angle : (2 * Math.PI + angle)) * 360 / (2 * Math.PI);
		return angle;
	}

	public void setAnimationCallback(Runnable runnable)
	{
		this.animationCallback = runnable;
	}

	public enum Color
	{
		YELLOW(new java.awt.Color(250, 240, 66)), GREEN(new java.awt.Color(67, 153, 58)), BLUE(
				new java.awt.Color(24, 30, 219)), RED(java.awt.Color.RED);

		private java.awt.Color color;

		private Color(java.awt.Color color)
		{
			this.color = color;
		}

		public java.awt.Color getColor()
		{
			return this.color;
		}
	}

	public void setTargetLocation(int x, int y, boolean queue)
	{
		GPoint target = new GPoint(x, y);
		if(target == this.getLocation())
			return;
		System.out.println("Setting target to "+x+", "+y);
		if(this.currentPoint == null) {
			this.currentPoint = target;
		} else {
			if(queue)
				this.locations.add(target);
			else
				this.currentPoint = target;
		}
	}

	public void setTargetLocation(int x, int y){
		this.setTargetLocation(x, y, false);
	}
}
