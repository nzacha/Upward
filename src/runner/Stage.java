package runner;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import entity.Mob;
import entity.Player;
import platform.MovingPlatform;
import platform.Platform;

public class Stage {
	private ArrayList<Platform> platforms = new ArrayList<Platform>();
	private String name;
	private int gravity = 1;
	private int offset = 0;

	public Stage() {
		platforms.add(new Platform(new Dimension(800, 50), new Point(0, 570)));
	}

	public void reset() {
		platforms.clear();
		platforms.add(new Platform(new Dimension(800, 50), new Point(0, 570)));
		offset = 0;
	}

	public void update(Player entity) {
		for (Platform platform : platforms) {
			if (platform instanceof MovingPlatform)
				((MovingPlatform) platform).update(this, entity);
		}
		int height = entity.getHeight();
		if (entity.getHighest() > 200)
			offset = -entity.getHighest() + 200;
		else
			offset = 0;
		int highest = 0;
		for (Platform platform : platforms) {
			if (platform.getBounds().y - 570 < highest)
				highest = platform.getBounds().y - 570;
		}
		while (Math.abs(highest) <= (entity.getHighest() + 300)) {
			highest -= 150;
			int x = (int) (Math.random() * 500) + 50;
			platforms.add(new Platform(new Dimension(200, 20), new Point(x, (highest + 570))));
		}
	}

	public boolean isCollision(Mob entity, Point newLocation) {
		for (Platform platform : platforms)
			if (platform.collides(entity, newLocation))
				return true;
		return false;
	}

	public boolean isGrounded(Mob entity) {
		for (Platform platform : platforms)
			if (platform.grounds(entity))
				return true;
		return false;
	}

	public Dimension calculateSpeedOf(Mob entity) {
		Dimension speed = entity.getSpeed();
		if (!isGrounded(entity)) {
			speed.height += gravity;
			for (Platform platform : platforms) {
				if (platform.collides(entity, new Point(entity.getLocation().x + speed.width, entity.getLocation().y + speed.height))) {
					if (speed.height > 0) {
						int dist = platform.getBounds().y - entity.getLocation().y - entity.getBounds().height;
						if (dist < speed.height)
							speed.height = dist;
					} else {
						int dist = entity.getLocation().y - platform.getBounds().y - platform.getBounds().height;
						if (dist > speed.height)
							speed.height = -dist;
					}
					if (speed.width > 0) {
						int dist = platform.getBounds().x - entity.getLocation().x - entity.getBounds().width;
						if (dist < speed.width)
							speed.width = dist;
					} else {
						int dist = entity.getLocation().x - platform.getBounds().x - platform.getBounds().width;
						if (dist > speed.width)
							speed.width = -dist;
					}
				}
			}
		}
		return speed;
	}

	public void draw(Graphics g) {
		for (Platform platform : platforms)
			platform.draw(g, offset);
	}

	public String getName() {
		return name;
	}

	public int getOffset() {
		return offset;
	}
}
