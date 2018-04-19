package thewetbandits.utils;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface Interfaceable extends Displayable {
	void mousePressed(MouseEvent e);
	void mouseReleased(MouseEvent e);
	void mouseClicked(MouseEvent e);
	void mouseDragged(MouseEvent e);
	void mouseMoved(MouseEvent e);
	void keyPressed(KeyEvent e);
	void keyReleased(KeyEvent e);
	void keyTyped(KeyEvent e);
}
