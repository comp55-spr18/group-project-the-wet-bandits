package thewetbandits.screens;

import thewetbandits.*;

public class Screens {

	public static MainGameplayScreen GAMEPLAY_ENDLESS_SCREEN;
	public static LimitedMovesModeScreen GAMEPLAY_LIMITED_SCREEN;
	public static TimedGameModeScreen GAMEPLAY_TIMED_SCREEN;

	public static Context TUTORIAL_SCREEN;
	public static ModeSelectScreen MODE_SELECT_SCREEN;
	public static MenuScreen MENU_SCREEN;


	public static void initialize(MatchThreeGame app){
		GAMEPLAY_ENDLESS_SCREEN = new MainGameplayScreen(app);
		MODE_SELECT_SCREEN = new ModeSelectScreen(app);
		GAMEPLAY_LIMITED_SCREEN = new LimitedMovesModeScreen(app);
		GAMEPLAY_TIMED_SCREEN = new TimedGameModeScreen(app);
		TUTORIAL_SCREEN = new Context(app);
		MENU_SCREEN = new MenuScreen(app);
	}
}
