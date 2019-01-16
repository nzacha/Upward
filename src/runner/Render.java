package runner;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import component.Component;

@SuppressWarnings("serial")
public class Render extends JPanel {
	@SuppressWarnings("unused")
	private final int WIDTH, HEIGHT;
	private Component component;

	public Render(int width, int height, Component component) {
		setPreferredSize(new Dimension(width, height));
		WIDTH = width;
		HEIGHT = height;
		updateComponent(component);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		component.draw(g);
	}

	public void updateComponent(Component newComponent) {
		if (component != null) {
			removeKeyListener(component);
			removeMouseListener(component);
		}
		addKeyListener(newComponent);
		addMouseListener(newComponent);
		setFocusable(true);
		requestFocusInWindow();
		component = newComponent;
	}

	public Dimension getOffset() {
		return new Dimension(0, 0);
	}
}
