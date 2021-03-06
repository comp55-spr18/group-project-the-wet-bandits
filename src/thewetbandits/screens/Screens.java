package thewetbandits.screens;

import thewetbandits.MatchThreeGame;

public class Screens {

	public static MainGameplayScreen GAMEPLAY_ENDLESS_SCREEN;
	public static LimitedMovesModeScreen GAMEPLAY_LIMITED_SCREEN;
	public static TimedGameModeScreen GAMEPLAY_TIMED_SCREEN;

	public static InstructionsScreen TUTORIAL_SCREEN;
	public static ModeSelectScreen MODE_SELECT_SCREEN;
	public static MenuScreen MENU_SCREEN;
	public static GameOverScreen GAME_OVER;

	/**
	 * Calls constructor for all screens in the application
	 *
	 * @param app the application in which all screens will reside
	 */
	public static void initialize(MatchThreeGame app) {
		GAMEPLAY_ENDLESS_SCREEN = new MainGameplayScreen(app);
		MODE_SELECT_SCREEN = new ModeSelectScreen(app);
		GAMEPLAY_LIMITED_SCREEN = new LimitedMovesModeScreen(app);
		GAMEPLAY_TIMED_SCREEN = new TimedGameModeScreen(app);
		TUTORIAL_SCREEN = new InstructionsScreen(app);
		MENU_SCREEN = new MenuScreen(app);
		GAME_OVER = new GameOverScreen(app);
	}
}
