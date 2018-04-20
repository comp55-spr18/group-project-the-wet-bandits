package thewetbandits.screens;

import acm.graphics.GImage;
import thewetbandits.MatchThreeGame;
import thewetbandits.screens.Screen;
import thewetbandits.screens.Screens;
import thewetbandits.utils.ClickAction;
import thewetbandits.utils.GButton;
import thewetbandits.utils.GImageButton;

import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;

/**
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
	private GButton settingButton;
	private MatchThreeGame game;
	private String music = "default.wav";
	private URL url;
	private AudioInputStream audioIn;
	private Clip clip;
	private Color buttonColor = new Color(255, 154, 0);
	private GImage background = new GImage("boardBG.PNG", 0, 0);
	private GImage displayTitle = new GImage("logo.png", 200, 50);

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
		//showSetting();
	}

	/**
	 * Display title: "Three's A Company
	 */
	public void displayTitle()
	{
		displayTitle.setSize(650, 150);
		add(displayTitle);
	}

	/**
	 * Displays the playButton, clicking on it would take you to the game
	 */
	public void buttons()
	{
		GImageButton playButton = new GImageButton("play.png", 450, 315, new ClickAction()
		{
			@Override
			public void onClick(MouseEvent event)
			{
				game.switchToScreen(Screens.MODE_SELECT_SCREEN);
			}
		});
		add(playButton);
		GImageButton quitButton = new GImageButton("quit.png", 450, 415, new ClickAction()
		{
			@Override
			public void onClick(MouseEvent event)
			{
				System.exit(0);
			}
		});
		add(quitButton);
		GImageButton instructionButton = new GImageButton("question.png", 900, 615, new ClickAction()
		{
			@Override
			public void onClick(MouseEvent event)
			{
				game.switchToScreen(Screens.TUTORIAL_SCREEN);
			}
		});
		add(instructionButton);
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

	public void showSetting()
	{
		settingButton = new GButton("SETTING", 450, 375, 115, 50, new ClickAction()
		{
			@Override
			public void onClick(MouseEvent event)
			{
				// game.switchToScreen(new MainGameplayScreen(game, WINDOW_WIDTH,
				// WINDOW_HEIGHT));
			}
		});
		settingButton.setColor(Color.WHITE);
		settingButton.setFillColor(buttonColor);
		add(settingButton);
	}

	/**
	 * Plays music, defaulting a song right now.
	 */
	public void music()
	{
		try
		{
			// TODO make volume lower
			url = this.getClass().getClassLoader().getResource(music);
			audioIn = AudioSystem.getAudioInputStream(url);
			clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
		}catch(LineUnavailableException | UnsupportedAudioFileException | IOException e)
		{
			e.printStackTrace();
		}
	}

}
