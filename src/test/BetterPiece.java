package test;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.Timer;

import acm.graphics.GCompound;
import acm.graphics.GImage;
import thewetbandits.screens.MovementTestScreen;

public class BetterPiece extends GCompound
{

	private static final int MOVEMENT_SPEED = 5;
	private static final int MOVEMENT_FREQUENCY = 13;
	private static final Random random = new Random();
	
	// Store a list of weak references (so the pieces can be garbage collected correctly) of pieces to update
	private static ArrayList<WeakReference<BetterPiece>> pieces = new ArrayList<>();
	private static Timer updateTimer;
	
	static {
		updateTimer = new Timer(MOVEMENT_FREQUENCY, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Timers are async so synchronize access to the array list so we don't break everything with CMEs
				synchronized(pieces) {
					Iterator<WeakReference<BetterPiece>> iterator = pieces.iterator();
					while(iterator.hasNext()) {
						WeakReference<BetterPiece> pieceReference =  iterator.next();
						BetterPiece piece = pieceReference.get();
						if(piece == null) {
							// The piece has been garbage collected, remove it from the list
							iterator.remove();
						} else {
							// The piece is still in memory, tell it to update its location
							piece.updateLocation();
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

	private int x, y, size;
	
	private int targetX, targetY;

	private boolean active = false;

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
	public BetterPiece(int x, int y, int size)
	{
		this(x, y, size, getRandomColor());
	}

	public BetterPiece(int x, int y, int size, Color color)
	{
		this.color = color;
		this.x = x;
		this.y = y;
		this.size = size;
		this.initImage();
		this.targetX = this.x;
		this.targetY = this.y;
		
		// Add the piece to the list of pieces to update
		synchronized(pieces) {
			pieces.add(new WeakReference<>(this));
		}
	}

	private static Color getRandomColor()
	{
		return Color.values()[random.nextInt(Color.values().length)];
	}
	
	public Color setGemColor(BetterPiece[][] b)
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

	private void initImage()
	{
		this.image = new GImage(this.color.toString().toLowerCase() + "_gem.png");
		this.imageAnimated = new GImage(this.color.toString().toLowerCase() + "_gem_animated.gif");
		this.imageAnimated.setSize(this.size, this.size);
		this.image.setSize(this.size, this.size);
		updateImage();
		this.setLocation(this.x, this.y);
	}

	private void updateImage()
	{
		remove(this.image);
		remove(this.imageAnimated);
		add(active ? this.imageAnimated : this.image);
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
	
	public void updateLocation() 
	{
		double deltaX = this.targetX - this.getX();
		double deltaY = this.targetY - this.getY();
		
		if(deltaX < -MOVEMENT_SPEED)
			deltaX = -MOVEMENT_SPEED;
		if(deltaX > MOVEMENT_SPEED)
			deltaX = MOVEMENT_SPEED;
		if(deltaY < -MOVEMENT_SPEED)
			deltaY = -MOVEMENT_SPEED;
		if(deltaY > MOVEMENT_SPEED)
			deltaY = MOVEMENT_SPEED;
		
		this.move(deltaX, deltaY);
		
		this.x = (int) this.getX();
		this.y = (int) this.getY();
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

	public void setTargetLocation(int x, int y) {
		this.targetX = x;
		this.targetY = y;
	}
}
