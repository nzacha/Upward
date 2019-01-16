package component;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import entity.Player;
import runner.Stage;

public class GameComponent extends Component {
	public static final int KEY_A = 0, KEY_D = 1, KEY_SPACE = 2;
	private boolean[] keys = new boolean[3];
	private Stage stage;
	private Player player;

	public GameComponent(Stage stage, Player player) {
		this.stage = stage;
		this.player = player;
	}

	@Override
	public void update() {
		stage.update(player);
		player.update(stage, keys);
		if (player.getLowestBounds().y > stage.getOffset() + 600)
			reset();
	}

	@Override
	public void draw(Graphics g) {
		stage.draw(g);
		player.draw(g, stage.getOffset());
	}

	@Override
	public void reset() {
		stage.reset();
		player.reset();
	}

	@Override
	public void keyPressed(KeyEvent kp) {
		int key = kp.getKeyCode();
		if (key == KeyEvent.VK_A) {
			keys[KEY_A] = true;
		} else if (key == KeyEvent.VK_D) {
			keys[KEY_D] = true;
		} else if (key == KeyEvent.VK_SPACE) {
			keys[KEY_SPACE] = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent kr) {
		int key = kr.getKeyCode();
		if (key == KeyEvent.VK_A) {
			keys[KEY_A] = false;
		} else if (key == KeyEvent.VK_D) {
			keys[KEY_D] = false;
		} else if (key == KeyEvent.VK_SPACE) {
			keys[KEY_SPACE] = false;
		}
	}
}
