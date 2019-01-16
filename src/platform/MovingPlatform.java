package platform;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import entity.Mob;
import runner.Stage;

public class MovingPlatform extends Platform {
	private Strategy strategy;
	private boolean canDrag = false;

	public MovingPlatform(Dimension size, Point location, Strategy strategy, Boolean canDrag) {
		super(size, location);
		this.strategy = strategy;
		this.canDrag = canDrag;
	}

	@Override
	public void draw(Graphics g, int offset) {
		if (canDrag)
			g.setColor(Color.darkGray);
		else
			g.setColor(Color.lightGray);
		g.fillRect(getBounds().x, getBounds().y - offset, getBounds().width, getBounds().height);
	}

	public void update(Stage stage, Mob entity) {
		Dimension speed = strategy.getSpeed(this);
		Rectangle newBounds = new Rectangle(getBounds());
		newBounds.x += speed.width;
		newBounds.y += speed.height;
		if (newBounds.intersects(entity.getBounds())) {
			entity.move(stage, speed);
		}
		move(speed);
		if (canDrag)
			if (collides(entity, new Point(entity.getLocation().x, entity.getLocation().y + entity.getSpeed().height)))
				entity.move(stage, speed);
	}
}
