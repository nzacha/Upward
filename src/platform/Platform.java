package platform;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import entity.Mob;

public class Platform {
	@SuppressWarnings("unused")
	private Dimension size;
	private Rectangle bounds;

	public Platform(Dimension size, Point location) {
		this.size = size;
		this.bounds = new Rectangle(location.x, location.y, (int) size.getWidth(), (int) size.getHeight());
	}

	public void move(Dimension speed) {
		bounds.x += speed.width;
		bounds.y += speed.height;
	}

	public void draw(Graphics g, int offset) {
		g.setColor(Color.black);
		g.fillRect(bounds.x, bounds.y - offset, bounds.width, bounds.height);
	}

	public boolean collides(Mob entity, Point newLocation) {
		if (bounds.intersects(entity.getBounds()) || bounds.intersects(new Rectangle(newLocation.x, newLocation.y, entity.getBounds().width, entity.getBounds().height)))
			return true;
		return false;
	}

	public boolean grounds(Mob entity) {
		if (bounds.intersects(entity.getLowestBounds()))
			return true;
		return false;
	}

	public Rectangle getBounds() {
		return bounds;
	}
}
