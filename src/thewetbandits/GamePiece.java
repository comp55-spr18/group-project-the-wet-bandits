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

	private static final long serialVersionUID = -7593716498021184989L;
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
	// TODO remove this? I'm not sure if it does anything
	private int id = nextId++;

	/**
	 * * Constructor where color and image are randomly chosen out of a predefined
	 * set
	 *
	 * @param x
	 *            the x position of the piece
	 * @param y
	 *            the y position of the piece
	 * @param size
	 *            the width and height of the piece
	 * @param r
	 *            the row where the piece exists in the board
	 * @param c
	 *            the column where the piece exists in the board
	 */
	public GamePiece(int x, int y, int size, int r, int c)
	{
		this(x, y, size, getRandomColor(), r, c);
	}

	/**
	 * Constructor where x and y are calculated instead of passed
	 * 
	 * @param spaceSize
	 *            the size of the space in between pieces + the size of the piece
	 *            itself
	 * @param size
	 *            the size of the piece
	 * @param r
	 *            the row where the piece exists in the board
	 * @param c
	 *            the column where the piece exists in the board
	 */
	public GamePiece(int spaceSize, int size, int r, int c)
	{
		this(spaceSize * (r + 1), spaceSize * (c + 1), size, r, c);
	}

	/**
	 * Constructor where color is passed as a parameter instead of randomized
	 * 
	 * @param x
	 *            the x position of the piece
	 * @param y
	 *            the y position of the piece
	 * @param size
	 *            the width and height of the piece
	 * @param color
	 *            the color of the piece
	 * @param r
	 *            the row where the piece exists in the board
	 * @param c
	 *            the column where the piece exists in the board
	 */
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

	/**
	 * Gets a random color out of all the possible colors
	 * 
	 * @return a random color out of all the colors in the enum
	 */
	private static Color getRandomColor()
	{
		return Color.values()[random.nextInt(Color.values().length)];
	}

	/**
	 * Checks whether at least one piece is in an animation
	 * 
	 * @return whether at least one piece is in the process of animating
	 */
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

	// TODO this looks like a getter. Do we have to change the name?
	public Color setGemColor(GamePiece[][] b)
	{
		return Color.values()[random.nextInt(Color.values().length)];
	}

	/**
	 * Resizes and moves the piece
	 * 
	 * @param x
	 *            the x of the target location
	 * @param y
	 *            the y of the target location
	 * @param size
	 *            the size to which the piece will be set
	 */
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

	/**
	 * setter for both row and column
	 * 
	 * @param r
	 *            the new row
	 * @param c
	 *            the new column
	 */
	public void updateRowCol(int r, int c)
	{
		this.r = r;
		this.c = c;
	}

	/**
	 * getter for row
	 * 
	 * @return the row of the piece
	 */
	public int getR()
	{
		return r;
	}

	/**
	 * getter for column
	 * 
	 * @return the column of the piece
	 */
	public int getC()
	{
		return c;
	}

	/**
	 * initializes the image of the piece and sets the size and location of both the
	 * static and animated images
	 */
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

	/**
	 * updates the image of the piece to either be animating or static depending on
	 * the active state of the piece
	 */
	private void updateImage()
	{
		remove(this.image);
		remove(this.imageAnimated);
		add(active ? this.imageAnimated : this.image);
	}

	/**
	 * clears all images from the piece's visuals
	 */
	public void clearPiece()
	{
		remove(this.image);
		remove(this.imageAnimated);
		add(testOval);
	}

	/**
	 * Toggles the active state of the piece. If static, it will start animating,
	 * and vice-versa
	 */
	public void toggleActive()
	{
		this.active = !this.active;
		this.updateImage();
	}

	/**
	 * getter for the Color of the piece (note that this is the enum color, not the
	 * actual RGB color
	 * 
	 * @return the enum color of the piece
	 */
	public Color getColorType()
	{
		return color;
	}

	// TODO document this. I don't know what this method does
	public boolean animating()
	{
		return !this.locations.isEmpty() || this.currentPoint != null;
	}

	// TODO document this. I don't know what this method does
	private void updatePose()
	{
		GPoint point = new GPoint(this.getX(), this.getY());
		double distance = calcDistance(point, this.currentPoint);
		if(distance < 4)
		{
			this.setLocation(this.currentPoint.getX(), this.currentPoint.getY());
			this.currentPoint = this.getNextPoint();
			this.runCallback();
		}
		else
		{
			this.movePolar(MOVEMENT_SPEED, calculateAngle(point, this.currentPoint));
		}
	}

	// TODO document this. I don't know what this method does
	private void runCallback()
	{
		if(this.currentPoint == null)
			if(this.animationCallback != null)
				this.animationCallback.run();
	}

	// TODO document this. I don't know what this method does
	private GPoint getNextPoint()
	{
		if(!this.locations.isEmpty())
			return this.locations.remove(0);
		else
			return null;
	}

	/**
	 * Calculates the distance between two points
	 * 
	 * @param p1
	 *            the first point
	 * @param p2
	 *            the second point
	 * @return the distance between the two points
	 */
	private double calcDistance(GPoint p1, GPoint p2)
	{
		return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getX() - p2.getX(), 2));
	}

	// TODO document this. I don't know how to document this
	private double calculateAngle(GPoint current, GPoint desired)
	{
		double dx = desired.getX() - current.getX();
		double dy = desired.getY() - current.getY();
		double angle = -1 * Math.atan2(dy, dx);
		angle = (angle > 0 ? angle : (2 * Math.PI + angle)) * 360 / (2 * Math.PI);
		return angle;
	}

	// TODO document this. I don't know what this method does
	public void setAnimationCallback(Runnable runnable)
	{
		this.animationCallback = runnable;
	}

	public enum Color
	{
		YELLOW(new java.awt.Color(250, 240, 66)), GREEN(new java.awt.Color(67, 153, 58)), BLUE(
				new java.awt.Color(24, 30, 219)), RED(java.awt.Color.RED), ORANGE(
						java.awt.Color.ORANGE), PINK(java.awt.Color.PINK), WHITE(java.awt.Color.WHITE);

		private java.awt.Color color;

		/**
		 * Constructor that is set based on the actual RGB color
		 * 
		 * @param color
		 *            the RGB color of the piece
		 */
		private Color(java.awt.Color color)
		{
			this.color = color;
		}

		/**
		 * returns the RGB color
		 * 
		 * @return the RGB value of the selected color
		 */
		public java.awt.Color getColor()
		{
			return this.color;
		}
	}

	// TODO document this. I don't know what this method does
	public void setTargetLocation(int x, int y, boolean queue)
	{
		GPoint target = new GPoint(x, y);
		if(target == this.getLocation())
			return;
		System.out.println("Setting target to " + x + ", " + y);
		if(this.currentPoint == null)
		{
			this.currentPoint = target;
		}
		else
		{
			if(queue)
				this.locations.add(target);
			else
				this.currentPoint = target;
		}
	}

	// TODO document this. I don't know what this method does
	public void setTargetLocation(int x, int y)
	{
		this.setTargetLocation(x, y, false);
	}
}
