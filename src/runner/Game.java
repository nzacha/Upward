package runner;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import component.Component;
import component.GameComponent;
import entity.Player;

public class Game {
	private ArrayList<Component> components = new ArrayList<Component>();
	private Component gameComponent;
	private final int WIDTH = 800, HEIGHT = 600;
	private JFrame frame;
	private Dimension screen;
	private Render render;
	private Stage stage;
	private Player player;

	public Game() {
		initiateComponents();
		setupWindow();
		setupScheduler();
	}

	private void initiateComponents() {
		stage = new Stage();
		player = new Player("Player 1", 4, new Point(100, 530));
		components.add(gameComponent = new GameComponent(stage, player));
		gameComponent.setEnabled(true);
	}

	private void setupWindow() {
		screen = Toolkit.getDefaultToolkit().getScreenSize();
		frame = new JFrame("Upward");
		frame.add(render = new Render(WIDTH, HEIGHT, gameComponent));
		frame.validate();
		frame.pack();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frame.dispose();
				System.exit(0);
			}
		});
		frame.setLocation((screen.width - WIDTH) / 2, (screen.height - HEIGHT) / 2);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	private void setupScheduler() {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				for (Component component : components)
					if (component.isEnabled())
						component.update();
				render.repaint();
			}
		}, 0, 15);
	}

	public static void main(String[] args) {
		new Game();
	}
}
