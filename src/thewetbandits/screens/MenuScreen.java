package thewetbandits.screens;

import acm.graphics.GImage;
import thewetbandits.MatchThreeGame;
import thewetbandits.screens.Screen;
import thewetbandits.screens.Screens;
import thewetbandits.utils.ClickAction;
import thewetbandits.utils.GImageButton;

import javax.sound.sampled.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;

/**
 * 
 * 
 * @author John Thao with help from Austin Whyte
 *
 *         MenuScreen displays the very beginning screen, it consist of the
 *         following title, play button, quit button, and a instruction button.
 * 
 */

public class MenuScreen extends Screen
{
	public static final int WINDOW_WIDTH = 1000;
	public static final int WINDOW_HEIGHT = 700;
	private MatchThreeGame game;
	private String music = "default.wav";
	private URL url;
	private AudioInputStream audioIn;
	protected Clip clip;
	private GImage background = new GImage("boardBG.PNG", 0, 0);
	private GImage displayTitle = new GImage("logo1.png", 200, 50);

	public MenuScreen(MatchThreeGame app)
	{
		super(app);
		game = app;
		run();
	}

	public void run()
	{
		background.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		add(background);
		music();
		displayTitle();
		buttons();
	}

	/**
	 * Display title: "Three's A Company
	 */
	public void displayTitle()
	{
		add(displayTitle);
	}

	/**
	 * Displays all the buttons and its actions
	 */
	public void buttons()
	{
		
		/**
		 *  Displays the playButton, clicking on it would take you to the game
		 */
		
		GImageButton playButton = new GImageButton("play.png", 450, 315, new ClickAction()
		{
			@Override
			public void onClick(MouseEvent event)
			{
				game.switchToScreen(Screens.MODE_SELECT_SCREEN);
			}
		});
		add(playButton);
		
		/**
		 *  Displays the quitButton, click on it exits out of the game
		 */
		GImageButton quitButton = new GImageButton("quit.png", 450, 415, new ClickAction()
		{
			@Override
			public void onClick(MouseEvent event)
			{
				System.exit(0);
			}
		});
		add(quitButton);
		
		/**
		 *  Displays the instructionButton, clicking on it takes you to a different
		 *  screen that display instructions for the game
		 */
		
		GImageButton instructionButton = new GImageButton("question.png", 900, 615, new ClickAction()
		{
			@Override
			public void onClick(MouseEvent event)
			{
				game.switchToScreen(Screens.TUTORIAL_SCREEN);
			}
		});
		add(instructionButton);
		
		/**
		 *  Displays the muteButton, it can be toggle on/off
		 */
		GImageButton muteButton = new GImageButton("mute.png", 900, 515, new ClickAction()
		{
			@Override
			public void onClick(MouseEvent event)
			{
				if(clip.isActive())
					clip.stop();
				else
					clip.start();
			}
		});
		add(muteButton);
	}

	/**
	 * Plays music
	 */
	public void music()
	{
		try
		{
			url = this.getClass().getClassLoader().getResource(music);
			audioIn = AudioSystem.getAudioInputStream(url);
			clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
			clip.loop(Integer.MAX_VALUE);
		}catch(LineUnavailableException | UnsupportedAudioFileException | IOException e)
		{
			e.printStackTrace();
		}
	}

}
