package entity;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import component.GameComponent;
import runner.Stage;

public class Player extends Mob {
	@SuppressWarnings("unused")
	private int jump = 20, speed = 20, momentumX = 0;

	public Player(String name, int lifePoints, Point location) {
		super(name, lifePoints, location);
	}

	public void jump(Stage stage) {
		if (stage.isGrounded(this))
			updateSpeedTo(new Dimension(getSpeed().width, -jump));
	}

	@Override
	public void draw(Graphics g, int offset) {
		super.draw(g, offset);
		g.setColor(Color.black);
		g.drawRect(getBounds().x, getBounds().y - offset, getBounds().width, getBounds().height);
	}

	public void update(Stage stage, boolean[] keys) {
		super.update(stage);
		if (keys[GameComponent.KEY_A])
			move(stage, new Dimension(-speed / 2, 0));
		if (keys[GameComponent.KEY_D])
			move(stage, new Dimension(speed / 2, 0));
		if (keys[GameComponent.KEY_SPACE])
			jump(stage);
	}
}
