package component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Button {
	private Rectangle bounds;
	private String text;

	public Button(String text, Rectangle bounds) {
		this.text = text;
		this.bounds = bounds;
	}

	public void draw(Graphics g) {
		g.setColor(Color.lightGray);
		g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
		g.setColor(Color.black);
		g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
		g.drawString(text, bounds.x + bounds.width / 2 - text.length() * 4, bounds.y + bounds.height / 2);
	}

	public void activate() {

	}

	public boolean contains(Point click) {
		if (bounds.contains(click))
			return true;
		return false;
	}
}
