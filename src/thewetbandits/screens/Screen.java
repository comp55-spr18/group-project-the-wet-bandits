package thewetbandits.screens;

import acm.graphics.GObject;
import thewetbandits.MatchThreeGame;
import thewetbandits.utils.Clickable;
import thewetbandits.utils.Displayable;
import thewetbandits.utils.GraphicsPane;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * A class extending the functionality of {@link GraphicsPane} by automatically
 * handling the addition and removal of objects to the screen when it becomes
 * active or inactive
 * 
 * @author Austin
 *
 */
public abstract class Screen extends GraphicsPane
{

	protected final MatchThreeGame application;
	private final ArrayList<GObject> objects = new ArrayList<>();
	private final ArrayList<Displayable> displayables = new ArrayList<>();

	/**
	 * default constructor for screen
	 * 
	 * @param app
	 *            the {@link MatchThreeGame} that the screen runs in
	 */
	public Screen(MatchThreeGame app)
	{
		this.application = app;
	}

	/**
	 * Adds a {@link GObject} to the screen to be displayed
	 * 
	 * @param obj
	 *            The object to add
	 */
	protected void add(GObject obj)
	{
		this.objects.add(obj);
		if(this.application.getCurrentPane() == this)
		{
			this.application.add(obj);
		}
	}

	/**
	 * Adds a {@link Displayable} to the screen to be displayed when the screen is
	 * 
	 * @param displayable
	 *            The displayable to display
	 */
	protected void add(Displayable displayable)
	{
		this.displayables.add(displayable);
		if(this.application.getCurrentPane() == this)
		{
			displayable.showContents();
		}
	}

	/**
	 * Removes the object from the screen
	 * 
	 * @param displayable
	 *            The object to remove
	 */
	protected void remove(Displayable displayable)
	{
		displayable.hideContents();
		this.displayables.remove(displayable);
	}

	/**
	 * Removes the object from the screen
	 * 
	 * @param obj
	 *            The object to remove
	 */
	protected void remove(GObject obj)
	{
		this.application.remove(obj);
		this.objects.remove(obj);
	}

	/**
	 * Clears all of the items currently on the screen
	 */
	protected void clear()
	{
		for(Displayable d : this.displayables)
		{
			d.hideContents();
		}
		for(GObject obj : this.objects)
		{
			this.application.remove(obj);
		}

		this.displayables.clear();
		this.objects.clear();
	}

	/**
	 * shows all contents on the screen
	 */
	@Override
	public void showContents()
	{
		for(Displayable d : this.displayables)
		{
			d.showContents();
		}
		for(GObject obj : this.objects)
		{
			this.application.add(obj);
			System.out.println("Showing " + obj.getClass());
		}
		this.onShow();
	}

	/**
	 * removes all contents from the screen
	 */
	@Override
	public void hideContents()
	{
		for(Displayable d : this.displayables)
		{
			d.hideContents();
		}
		for(GObject obj : this.objects)
		{
			this.application.remove(obj);
		}
		this.onHide();
	}

	/**
	 * Calls the onClick() method of an object that is clicked upon
	 */
	@Override
	public void mouseReleased(MouseEvent event)
	{
		GObject obj = this.application.getElementAt(event.getX(), event.getY());
		System.out.println("Clicked on " + obj);
		if(obj instanceof Clickable)
		{
			((Clickable) obj).onClick(event);
		}
	}

	public void onShow()
	{

	}

	public void onHide()
	{

	}

}
