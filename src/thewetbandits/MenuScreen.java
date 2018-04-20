package thewetbandits;

import acm.graphics.GImage;
import thewetbandits.screens.Screen;
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
	private GButton muteButton;
	private MatchThreeGame game;
	private String music = "default.wav";
	private URL url;
	private AudioInputStream audioIn;
	private Clip clip;
	private Color buttonColor = new Color(255, 154, 0);
	private GImage background = new GImage("background.gif", 0, 0);
	private GImage displayTitle = new GImage("logo.png", 200, 50);
	private GImage playButton = new GImage("play.png", 450, 315);
	private GImage quitButton = new GImage("quit.png", 450, 415);
	private GImage instructionButton = new GImage("question.png", 900, 615);

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
		// TODO 4/18/18: Re-add the godawful loud annoying music
		// music();
		displayTitle();
		buttons();
		//showSetting();
		displayMuteButton();
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
		GImageButton playButton = new GImageButton("play.png", 450, 315, new ClickAction() {
			@Override
			public void onClick(MouseEvent event) {
				game.switchToScreen(new ModeSelectScreen(game));
			}
		});
		add(playButton);
		GImageButton quitButton = new GImageButton("quit.png", 450, 415, new ClickAction() {
			@Override
			public void onClick(MouseEvent event) {
				System.exit(0);
			}
		});
		add(quitButton);
		GImageButton instructionButton = new GImageButton("question.png", 900, 615, new ClickAction() {
			@Override
			public void onClick(MouseEvent event) {
				game.switchToScreen(new Context(game));
			}
		});
		add(instructionButton);
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
