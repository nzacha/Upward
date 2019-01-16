package platform;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

public class Strategy {
	private ArrayList<Point> points = new ArrayList<Point>();
	private int speed, selected = 0, offset = 1;

	public Strategy(int speed, Point... points) {
		this.speed = speed;
		for (Point point : points)
			this.points.add(point);
	}

	public Dimension getSpeed(Platform platform) {
		Point point = points.get(selected);
		int wid = point.x - platform.getBounds().x;
		int hei = point.y - platform.getBounds().y;
		int distance = (int) Math.sqrt(hei * hei + wid * wid);
		if (distance <= speed) {
			selected += offset;
			if (selected == 0 || selected == points.size() - 1) {
				offset *= -1;
			}
			return new Dimension(wid, hei);
		}
		double y = Math.abs(speed * 1.0 / (hei + wid));
		double x = y * wid;
		y *= hei;
		return new Dimension((int) x, (int) y);
	}
}
