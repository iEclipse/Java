import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GUI extends JFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;

	Pane p = new Pane(); // Panel that contains all UI
	JLabel scoreUI, bestUI, pause; // Text on screen
	Collision co = new Collision(); // Collision Detection
	Timer t = new Timer(15, this); // 60FPS Refresh Rate
	Timer t2 = new Timer(300, this); // Box Spawn Timer
	Timer t3 = new Timer(15000, this); // Initial Power Up Timer
	Timer t4, t5, t6; // Shrink, Speed, Invulnerability Timers
	ArrayList<double[]> boxes = new ArrayList<double[]>(); // Contains All Boxes
	ArrayList<double[]> vel = new ArrayList<double[]>(); // Contains the Boxes Velocities
	Stack<Integer> pressedKeys = new Stack<Integer>(); // Contains All Pressed Keystrokes
	ArrayList<double[]> powerUps = new ArrayList<double[]>(); // Contains All Power Ups on the Map
	ArrayList<Integer> powerUps2 = new ArrayList<Integer>(); // Contains All Power Ups the Player Has
	Random rn = new Random(); // RNG

	double[] player;
	int[] rgb = new int[] { 255, 100, 100 }; // Initial Player Color
	int score = 0, best = 0; // Player Score and High Score
	int boxSpd = 1; // Initial Box Speed
	int moveSpd = 2; // Player Move Speed
	boolean invuln = false;

	/* GUI INITIALIZATION */
	public GUI() {

		// GUI Settings
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		addKeyListener(this);
		setSize(600, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		add(p);

		// Sets Player Position (Size is Determined by Using Distance Formula: X2 - X1, Y2 - Y1)
		player = new double[] { getWidth() / 2 - 20, getHeight() / 2 - 25, getWidth() / 2 + 5, getHeight() / 2 };

		// Starts Timers for Game [FPS, Box Spawner, Power Up Timer]
		t.start();
		t2.start();
		t3.start();
	}

	// Resets All Game Settings, Except High Score
	void reset() {
		t2 = new Timer(300, this);
		t3 = new Timer(15000, this);
		t2.restart();
		t3.restart();
		t4 = null;
		t5 = null;
		t6 = null;
		boxes.clear();
		vel.clear();
		powerUps.clear();
		powerUps2.clear();
		player = new double[] { getWidth() / 2 - 20, getHeight() / 2 - 25, getWidth() / 2 + 5, getHeight() / 2 };
		rgb = new int[] { 255, 100, 100 };
		score = 0;
		boxSpd = 1;
		moveSpd = 2;
		invuln = false;
	}

	class Pane extends JPanel {

		private static final long serialVersionUID = 1L;

		/* PANEL INITIALIZATION */
		public Pane() {
			setSize(600, 600);
			setBackground(Color.BLACK);

			// Create Text Labels
			scoreUI = new JLabel("Score: " + score);
			bestUI = new JLabel("|  Best: " + best);
			pause = new JLabel("| PAUSED");

			// Set Colors for Labels and Hide the Pause Label
			scoreUI.setForeground(Color.WHITE);
			bestUI.setForeground(Color.WHITE);
			pause.setForeground(Color.RED);
			pause.setVisible(false);

			// Add the Labels to the Panel
			add(scoreUI);
			add(bestUI);
			add(pause);
		}

		// Draw Frame
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			drawPlayer(g);
			drawBoxes(g);
			drawPowerUps(g);
			drawPowerUpUI(g);
		}
	}

	// Draws the Player
	void drawPlayer(Graphics g) {
		if (invuln)
			drawEntity(player, new Color(255, 255, 255, 150), g);
		else
			drawEntity(player, new Color(255, 0, 0, 100), g);
	}

	// Draws Each Box
	void drawBoxes(Graphics g) {
		for (int i = 0; i < boxes.size(); ++i)
			drawEntity(boxes.get(i), new Color(rgb[0], rgb[1], rgb[2], 100), g);
	}

	// General Drawing Method (Helper for Boxes and Player)
	void drawEntity(double[] coords, Color c, Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Path2D.Double path = new Path2D.Double();

		// Create the Path
		path.moveTo(coords[0], coords[1]);
		path.lineTo(coords[0], coords[3]);
		path.lineTo(coords[2], coords[3]);
		path.lineTo(coords[2], coords[1]);
		path.closePath();

		// Draw the Path
		g2d.setColor(c);
		g2d.draw(path);
		g2d.fill(path);
	}

	// Draws the Power Ups
	void drawPowerUps(Graphics g) {
		for (int i = 0; i < powerUps.size(); ++i)

			// Bomb [Yellow]
			if (powerUps.get(i)[4] == 0)
				drawEntity(powerUps.get(i), new Color(255, 255, 0, 100), g);

			// Shrink [Blue]
			else if (powerUps.get(i)[4] == 1)
				drawEntity(powerUps.get(i), new Color(0, 255, 255, 100), g);

			// Speed [Green]
			else if (powerUps.get(i)[4] == 2)
				drawEntity(powerUps.get(i), new Color(0, 255, 0, 100), g);

			// Invulnerability [White]
			else if (powerUps.get(i)[4] == 3)
				drawEntity(powerUps.get(i), new Color(255, 255, 255, 100), g);
	}

	// Draws the Player's Obtained Power Ups
	void drawPowerUpUI(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		for (int i = 0; i < powerUps2.size(); ++i) {

			Path2D.Double path = new Path2D.Double();

			// Bomb [Yellow]
			if (powerUps2.get(i) == 0)
				g2d.setColor(new Color(255, 255, 0, 100));

			// Shrink [Blue]
			else if (powerUps2.get(i) == 1)
				g2d.setColor(new Color(0, 255, 255, 100));

			// Speed [Green]
			else if (powerUps2.get(i) == 2)
				g2d.setColor(new Color(0, 255, 0, 100));

			// Invulnerability [White]
			else if (powerUps2.get(i) == 3)
				g2d.setColor(new Color(255, 255, 255, 100));

			// Create the Path
			path.moveTo(i * 15 + 10, 550);
			path.lineTo(i * 15 + 10, 560);
			path.lineTo(i * 15 + 20, 560);
			path.lineTo(i * 15 + 20, 550);
			path.closePath();

			// Draw the Path
			g2d.fill(path);
		}
	}

	// Perform Update Every Time Frame is Refreshed
	public void update() {
		move(); // Move Player
		boxesMove(); // Move Boxes
		checkPowerUpCollision(); // Check if Player Obtained Power Up
		checkBoxCollision(); // Check If Player Crashed Into a Box
	}

	// Moves the Player Based on Keyboard Input, Also Handles Warping
	public void move() {

		// Gets the Player's Dimensions
		double pWidth = (player[2] - player[0]);
		double pHeight = (player[3] - player[1]);

		// Grab the Most Recent Key Pressed
		if (!pressedKeys.isEmpty()) {
			switch (pressedKeys.peek()) {

			// Left Arrow
			case 37:
				player[0] -= moveSpd;
				player[2] -= moveSpd;
				break;

			// Up Arrow
			case 38:
				player[1] -= moveSpd;
				player[3] -= moveSpd;
				break;

			// Right Arrow
			case 39:
				player[0] += moveSpd;
				player[2] += moveSpd;
				break;

			// Down Arrow
			case 40:
				player[1] += moveSpd;
				player[3] += moveSpd;
			}

			// Warp from Left to Right Edge
			if (player[0] < -pWidth) {
				player[0] = getWidth();
				player[2] = getWidth() + pWidth;
			}

			// Warp from Top to Bottom Edge
			else if (player[1] < -pHeight) {
				player[1] = getHeight() - pHeight;
				player[3] = getHeight();
			}

			// Warp from Right to Left Edge
			else if (player[2] > getWidth() + pWidth) {
				player[0] = -pWidth;
				player[2] = 0;
			}

			// Warp from Bottom to Top Edge
			else if (player[3] > getHeight()) {
				player[1] = -pHeight;
				player[3] = 0;
			}
		}
	}

	// Moves Boxes
	void boxesMove() {
		for (int i = 0; i < boxes.size(); ++i) {
			boxes.get(i)[0] += vel.get(i)[0];
			boxes.get(i)[1] += vel.get(i)[1];
			boxes.get(i)[2] += vel.get(i)[0];
			boxes.get(i)[3] += vel.get(i)[1];
		}
	}

	// Checks If Player Obtained a Power Up
	void checkPowerUpCollision() {
		for (int i = 0; i < powerUps.size(); ++i) {
			if (co.isCollided(player, powerUps.get(i))) {
				if (powerUps2.size() <= 10)
					powerUps2.add((int) powerUps.get(i)[4]);
				powerUps.remove(i--);
			}
		}
	}

	// Checks if Player Crashed into a Box
	void checkBoxCollision() {

		// If the Player Crashes into a Box, Reset the Game and Update High Score
		for (int i = 0; i < boxes.size(); ++i) {
			if (!invuln && co.isCollided(player, boxes.get(i))) {
				if (best < score) {
					best = score;
					bestUI.setText("| Best: " + best);
				}
				reset();

				// If a Box Passes through an Edge, Remove It
			} else if (boxes.get(i)[0] > getWidth() || boxes.get(i)[1] > getHeight() || boxes.get(i)[2] < 0 || boxes.get(i)[3] < 0) {
				boxes.remove(i);
				vel.remove(i--);
			}
		}
	}

	// Timer Actions
	public void actionPerformed(ActionEvent arg0) {

		// Update Every Frame
		if (arg0.getSource() == t) {
			update();
			repaint();

			// Spawn Boxes and Manage Score
		} else if (arg0.getSource() == t2) {

			// If the Spawn Timer Delay is Greater than 0
			if (t2.getDelay() > 0) {

				// Increase the Score
				score += boxSpd;

				// Otherwise Reduce the Spawn Timer Delay One MilliSecond at a Time
				if (t2.getDelay() > 5)
					t2.setDelay(t2.getDelay() - 1);

				// Until it Reaches Zero
				else if (rn.nextInt(3) == 0) // Make it Harder to Reach 0
					t2.setDelay(t2.getDelay() - 1);

				// If Spawn Timer Delay Reaches 0, Increase Difficulty, Give Bonus Points, Change Box Color, and then Reset the Delay
			} else {
				score += boxSpd * 50;
				++boxSpd;
				t2.setDelay(300);
				getRandomColor();
			}

			// Spawn Another Box and Update the Score UI
			spawnBox();
			scoreUI.setText("Score: " + score);

			// Spawn a Power Up
		} else if (arg0.getSource() == t3) {
			spawnPowerUp();

			// Shrink the Player and Start the Countdown to Revert it
		} else if (arg0.getSource() == t4) {
			player[0] = player[0] - 6;
			player[1] = player[1] - 6;
			player[2] = player[2] + 6;
			player[3] = player[3] + 6;
			t4.stop();

			// Increase the Player Speed and Start the Countdown to Revert it
		} else if (arg0.getSource() == t5) {
			t5.stop();
			moveSpd = 2;

			// Make the Player Invulnerable and Start the Countdown to Revert it
		} else if (arg0.getSource() == t6) {
			invuln = false;
			t6.stop();
		}
	}

	// Generates a Random Color (Avoids Dark Colors)
	public void getRandomColor() {
		rgb[0] = rn.nextInt(156) + 100;
		rgb[1] = rn.nextInt(156) + 100;
		rgb[2] = rn.nextInt(156) + 100;
	}

	// Spawns a Box on a Random Edge or Corner
	void spawnBox() {
		int x = rn.nextInt(getWidth() - 10), y = rn.nextInt(getHeight() - 35);
		switch (rn.nextInt(4)) {

		// Left Wall
		case 0:
			boxes.add(new double[] { 0, y, 5, y + 5 });
			vel.add(new double[] { rn.nextDouble() * boxSpd, rn.nextDouble() * boxSpd });
			break;

		// Top Wall
		case 1:
			boxes.add(new double[] { x, 0, x + 5, 5 });
			vel.add(new double[] { rn.nextDouble() * boxSpd, rn.nextDouble() * boxSpd });
			break;

		// Right Wall
		case 2:
			boxes.add(new double[] { getWidth() - 10, y, getWidth() - 5, y + 5 });
			vel.add(new double[] { -rn.nextDouble() * boxSpd, -rn.nextDouble() * boxSpd });
			break;

		// Bottom Wall
		case 3:
			boxes.add(new double[] { x, getHeight() - 35, x + 5, getHeight() - 30 });
			vel.add(new double[] { -rn.nextDouble() * boxSpd, -rn.nextDouble() * boxSpd });
			break;
		}
	}

	// Spawns a Random Power Up and Randomizes Timer for Next Power Up Spawn
	void spawnPowerUp() {
		int x = rn.nextInt(getWidth() - 65) + 25;
		int y = rn.nextInt(getHeight() - 90) + 25;
		powerUps.add(new double[] { x, y, x + 10, y + 10, rn.nextInt(4) });
		t3.setDelay(rn.nextInt(10000) + 20000);
	}

	// Log the Pressed Keys into a Stack (Ignores Space)
	public void keyPressed(KeyEvent arg0) {
		if (!pressedKeys.contains(arg0.getKeyCode()) && arg0.getKeyCode() != 17) {
			pressedKeys.push(arg0.getKeyCode());
		}
	}

	// Removes Pressed Keys from the Stack (Ignores Space)
	public void keyReleased(KeyEvent arg0) {
		if (pressedKeys.contains(arg0.getKeyCode()) && arg0.getKeyCode() != 17)
			pressedKeys.remove(pressedKeys.indexOf(arg0.getKeyCode()));

		// If the Key Removed was CTRL, Activate the First Power Up Owned (If Any)
		if (arg0.getKeyCode() == 17 && !powerUps2.isEmpty()) {
			activate(powerUps2.get(0));
			powerUps2.remove(0);
		}
	}

	// If Space is Pressed, Pause/Resume the Game
	public void keyTyped(KeyEvent arg0) {
		if (arg0.getKeyChar() == ' ')
			pause();
	}

	// Activate the Power Up Used
	public void activate(int powerup) {

		// Bomb
		if (powerup == 0) {
			boxes.clear();

			// Shrink
		} else if (powerup == 1) {
			if (player[2] - player[0] == 25) {
				player[0] = player[0] + 6;
				player[1] = player[1] + 6;
				player[2] = player[2] - 6;
				player[3] = player[3] - 6;
			}
			t4 = new Timer(7000, this);
			t4.start();

			// Speed
		} else if (powerup == 2) {
			moveSpd = 5;
			t5 = new Timer(5000, this);
			t5.start();

			// Invulnerability
		} else if (powerup == 3) {
			invuln = true;
			t6 = new Timer(3000, this);
			t6.start();
		}
	}

	// Pause/Resume the Game (by Manipulating the Timers) and Set Visiblity of "Pause" Text on UI
	public void pause() {
		if (t.isRunning()) {
			t.stop();
			t2.stop();
			t3.stop();

			// Shrink
			if (t4 != null)
				t4.stop();

			// Speed
			if (t5 != null)
				t5.stop();

			// Invulnerability
			if (t6 != null)
				t6.stop();

			pause.setVisible(true);

		} else {
			t.start();
			t2.start();
			t3.start();

			// Shrink
			if (t4 != null)
				t4.start();

			// Speed
			if (t5 != null)
				t5.stop();

			// Invulnerability
			if (t6 != null)
				t6.stop();

			pause.setVisible(false);
		}
	}
}
