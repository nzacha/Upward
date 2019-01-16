package entity;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import runner.Stage;

public class Mob {
	private int lifePoints, height = 0, highest = 0;
	private String name;
	private Point location;
	private Dimension size = new Dimension(20, 40), speed = new Dimension(0, 0);

	public Mob(String name, int lifePoints, Point location) {
		this.name = name;
		this.lifePoints = lifePoints;
		this.location = location;
	}

	public void reset() {
		height = 0;
		highest = 0;
		location = new Point(100, 530);
	}

	public void move(Stage stage, Dimension offset) {
		Point newLocation = new Point((int) (getLocation().x + offset.getWidth()), (int) (getLocation().y + offset.getHeight()));
		if (!stage.isCollision(this, newLocation)) {
			height += (newLocation.y - location.y);
			if (height * -1 > highest)
				highest = -1 * height;
			location = newLocation;
		}
	}

	public void draw(Graphics g, int offset) {
		g.setColor(Color.cyan);
		g.fillRect(location.x, location.y - offset, size.width, size.height);
	}

	public void update(Stage stage) {
		speed = stage.calculateSpeedOf(this);
		move(stage, speed);
	}

	public void updateSpeed(Dimension offset) {
		speed.width += offset.width;
		speed.height += offset.height;
	}

	public void updateSpeedTo(Dimension newSpeed) {
		speed = newSpeed;
	}

	public int getHighest() {
		return highest;
	}

	public int getHeight() {
		return height;
	}

	public int getLifePoints() {
		return lifePoints;
	}

	public Point getLocation() {
		return location;
	}

	public Dimension getSpeed() {
		return speed;
	}

	public Rectangle getLowestBounds() {
		return new Rectangle(location.x, location.y + size.height, size.width, 1);
	}

	public Rectangle getBounds() {
		return new Rectangle(location.x, location.y, size.width, size.height);
	}

	public Rectangle getNextBounds() {
		Point nextPoint = location;
		nextPoint.y += (int) speed.getHeight();
		nextPoint.x += (int) speed.getWidth();
		return new Rectangle(nextPoint.x, nextPoint.y, size.width, size.height);
	}

	public String getName() {
		return name;
	}
}
