package thewetbandits;

import thewetbandits.MatchThreeGame;
import thewetbandits.MenuScreen;
import thewetbandits.Context;
import thewetbandits.screens.Screen;
import thewetbandits.utils.ClickAction;
import thewetbandits.utils.GButton;

import acm.graphics.GImage;
import javax.sound.sampled.*;
import java.awt.Color;
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
	private GButton playButton;
	private GButton quitButton;
	private GButton tutorialButton;
	private GButton settingButton;
	private GButton muteButton;
	private MatchThreeGame game;
	private String music = "default.wav";
	private URL url;
	private AudioInputStream audioIn;
	private Clip clip;
	private Color buttonColor = new Color(255, 154, 0);
	

	public MenuScreen(MatchThreeGame app)
	{
		super(app);
		game = app;
		run();
	}

	public void run()
	{
		GImage background = new GImage("background.gif", 0, 0);
		background.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		add(background);
		// TODO 4/18/18: Re-add the godawful loud annoying music
		// music();
		displayTitle();
		showPlayButton();
		//showSetting();
		showQuitButton();
		showTutorialButton();
		displayMuteButton();
	}

	/**
	 * Display title: "Three's A Company
	 */
	public void displayTitle()
	{
		GImage displayTitle = new GImage("logo.png", 200, 50);
		displayTitle.setSize(650, 150);
		add(displayTitle);
	}

	/**
	 * Displays the playButton, clicking on it would take you to the game
	 */
	public void showPlayButton()
	{
		GImage playPhoto = new GImage("play.png", 325, 315);
		add(playPhoto);

		playButton = new GButton("PLAY", 450, 315, 115, 50, new ClickAction()
		{
			@Override
			public void onClick(MouseEvent event)
			{
				game.switchToScreen(new ModeSelectScreen(game));
			}
		});
		playButton.setColor(Color.WHITE);
		playButton.setFillColor(buttonColor);
		add(playButton);
	}

	public void showSetting()
	{
		settingButton = new GButton("SETTING", 450, 375, 115, 50, new ClickAction()
		{
			@Override
			public void onClick(MouseEvent event)
			{
				//game.switchToScreen(new MainGameplayScreen(game, WINDOW_WIDTH, WINDOW_HEIGHT));
			}
		});
		settingButton.setColor(Color.WHITE);
		settingButton.setFillColor(buttonColor);
		add(settingButton);
	}

	/**
	 * Displays the quitButton, clicking on it would exit out of the game and close
	 * it
	 */
	public void showQuitButton()
	{

		GImage quitPhoto = new GImage("quit.png", 325, 415);
		add(quitPhoto);
		quitButton = new GButton("QUIT", 450, 440, 115, 50, new ClickAction()
		{
			@Override
			public void onClick(MouseEvent event)
			{
				System.out.println("click on quit");
				System.exit(0);
			}
		});
		quitButton.setColor(Color.WHITE);
		quitButton.setFillColor(buttonColor);
		add(quitButton);
	}

	/**
	 * Display the tutorialButton, clicking on it would bring you to the
	 * instructions
	 */
	public void showTutorialButton()
	{
		GImage tutorialPhoto = new GImage("question.png", 825, 615);
		add(tutorialPhoto);

		tutorialButton = new GButton("?", 900, 615, 50, 50, new ClickAction()
		{
			@Override
			public void onClick(MouseEvent event)
			{
				System.out.println("click on tutorial");
				game.switchToScreen(new Context(game));
			}
		});
		tutorialButton.setColor(Color.WHITE);
		tutorialButton.setFillColor(buttonColor);
		add(tutorialButton);
	}

	public void displayMuteButton()
	{
		muteButton = new GButton("Mute", 900, 515, 50, 50, new ClickAction()
		{
			@Override
			public void onClick(MouseEvent event)
			{
				clip.stop();
			}
		});
		muteButton.setColor(Color.WHITE);
		muteButton.setFillColor(buttonColor);
		add(muteButton);
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
