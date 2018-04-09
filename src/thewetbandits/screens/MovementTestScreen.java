package thewetbandits.screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.Timer;

import test.BetterPiece;
import thewetbandits.MatchThreeGame;
import thewetbandits.utils.ClickAction;
import thewetbandits.utils.GButton;

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
				if(state) {
					piece.setTargetLocation(60, 60);
				} else {
					piece.setTargetLocation(120, 150);
				}
				state = !state;
			}
		});
		
		this.updateTimer = new Timer(30, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MovementTestScreen.this.piece.updateLocation();
			}
		});
		
		this.updateTimer.start();
		
		this.addComponents();
	}
	
	private void addComponents() {
		add(this.piece);
		add(this.button);
	}

}
