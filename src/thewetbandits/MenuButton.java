package thewetbandits;
import java.awt.event.MouseEvent;

public class MenuButton {
	private String text;
	private int locX;
	private int locY;
	
	public MenuButton(String text, int locX, int locY) {
		this.text = text;
		this.locX = locX;
		this.locY = locY;
	}
	
	public void mousePressed(MouseEvent e) {
		System.out.println("You clicked.");
	}
}
