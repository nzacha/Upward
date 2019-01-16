package component;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public abstract class Component implements MouseListener, KeyListener {
	private ArrayList<Button> buttons = new ArrayList<Button>();
	private boolean isEnabled = false;

	public abstract void draw(Graphics g);

	public abstract void update();

	public abstract void reset();

	public void removeButtons() {
		buttons.clear();
	}

	public void addButton(Button button) {
		buttons.add(button);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent mw) {
		for (Button button : buttons)
			if (button.contains(mw.getPoint())) {
				button.activate();
			}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean enabled) {
		isEnabled = enabled;
	}
}
