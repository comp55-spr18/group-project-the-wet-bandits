package thewetbandits.screens;

import test.BetterPiece;
import thewetbandits.MatchThreeGame;
import thewetbandits.animation.Pose;
import thewetbandits.utils.ClickAction;
import thewetbandits.utils.GButton;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class MovementTestScreen extends Screen {

	private boolean state = true;
	
	private BetterPiece piece;
	private GButton button;
	
	private Timer updateTimer;
	
	public MovementTestScreen(MatchThreeGame app) {
		super(app);
		piece = new BetterPiece(60, 60, 30, 0 ,0);
		button = new GButton("Click me", 30, 30, 20, 20, new ClickAction() {
			@Override
			public void onClick(MouseEvent event) {
				if(piece.animating())
					return;
				MovementTestScreen.this.remove(button);
				if(state) {
					piece.setTargetLocation(60, 60);
					piece.setPose(new Pose(60, 60, 40, 40));
					piece.setPose(new Pose(120, 60, 40, 20));
					piece.setPose(new Pose(300, 300, 120, 120, 1000));
					piece.setAnimationCallback(new Runnable() {
						@Override
						public void run() {
							MovementTestScreen.this.add(button);
						}
					});

				} else {
					MovementTestScreen.this.remove(button);
					piece.setTargetLocation(120, 150);
					piece.setPose(new Pose(120, 150, 20, 20));
					piece.setPose(new Pose(60, 60, 20, 20, 3000));
					piece.setAnimationCallback(new Runnable() {
						@Override
						public void run() {
							MovementTestScreen.this.add(button);
						}
					});
				}
				state = !state;
			}
		});
		this.addComponents();
	}
	
	private void addComponents() {
		add(this.piece);
		add(this.button);
	}

}
