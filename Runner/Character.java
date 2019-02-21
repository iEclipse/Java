import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.Timer;

//Visual Elements for Runner
public class Character extends JPanel implements ActionListener, KeyListener {
	Pillar p = new Pillar(); // Pillar Object
	double[] pos; // Character Position
	int[] character; // Character Size
	double vel; // Y Velocity of Character
	Timer t; // Repaint Timer
	Timer t2; // Pillar Generation Timer
	Timer t3; // Speed Increase Timer
	HashMap<int[], String> hit; // List of all Hit Pillars
	int counter; // Counter for Hit Pillars
	int pillarSpeed; // Pillar Speed

	// Constructor
	public Character() {

		// Panel Settings
		setSize(500, 500);
		addKeyListener(this);
		setFocusable(true);
		startGame();
	}

	public void startGame() {
		// Initial Game Setup
		hit = new HashMap<int[], String>();
		pos = new double[] { 20, 80 };
		character = new int[] { 30, 30 };
		counter = 0;
		vel = -10;
		pillarSpeed = 5;

		// Timers
		t = new Timer(17, this);
		t2 = new Timer(600, this);
		t3 = new Timer(5000, this);
		t.start();
		t2.start();
		t3.start();
	}

	// Draws Graphics
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_LCD_CONTRAST, 100);
		g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

		// Draws Pillars
		for (int i = 0; i < Pillar.pillars.size(); i++) {
			if (hit.containsKey(Pillar.pillars.get(i)))
				g2d.setColor(Color.GREEN);// Hit Pillar Color
			else
				g2d.setColor(Color.GRAY);// Untouched Pillar Color
			g2d.fillRect(Pillar.pillars.get(i)[0], Pillar.pillars.get(i)[1], Pillar.pillars.get(i)[2], 465 - Pillar.pillars.get(i)[1]);
		}

		// Draw Character
		g2d.setColor(Color.BLACK);
		g2d.fillOval((int) pos[0], (int) pos[1], character[0], character[1]);

		// Displays Score
		Font f = new Font("Calibri", Font.PLAIN, 30);
		g2d.setFont(f);
		g2d.drawString("Score: " + counter, 20, 50);
		
		g2d.dispose();
	}

	public void actionPerformed(ActionEvent arg0) {
		//
		if (arg0.getSource() == t) {

			// Moves All Pillars
			for (int i = 0; i < Pillar.pillars.size(); i++)
				Pillar.pillars.get(i)[0] -= pillarSpeed;

			// Gravity
			vel ++;

			// Collision Checker
			for (int i = 0; i < Pillar.pillars.size(); i++)
				if (pos[0] + character[0] - 3 >= Pillar.pillars.get(i)[0] && pos[0] <= Pillar.pillars.get(i)[0] + Pillar.pillars.get(i)[2]) {

					if (pos[1] + vel >= Pillar.pillars.get(i)[1] - character[1])
						// Set Y Velocity to 0 if Character Lands on Top of Pillar
						if (pos[1] <= Pillar.pillars.get(i)[1] - character[1]) {
							pos[1] = Pillar.pillars.get(i)[1] - character[1];
							vel = 0;
							// Increment Counter if Pillar Hasn't Been Touched Before
							if (!hit.containsKey(Pillar.pillars.get(i))) {
								hit.put(Pillar.pillars.get(i), "");
								counter++;
							}
						} else {
							// Stop if Character Collides with Side of a Pillar
							t.stop();
							t2.stop();
							t3.stop();
						}
				} else if (pos[1] >= 455 - character[1]) {
					// Stop if Character Falls off the Map
					t.stop();
					t2.stop();
					t3.stop();
				}

			// Sets Character's New Position
			pos[1] += vel;

			// Destroys Useless Pillars
			while (!Pillar.pillars.isEmpty() && Pillar.pillars.get(0)[0] + Pillar.pillars.get(0)[2] < 0) {
				hit.remove(Pillar.pillars.get(0));
				Pillar.pillars.remove(Pillar.pillars.get(0));
			}
			repaint();

			// Generates New Pillars
		} else if (arg0.getSource() == t2)
			p.genPillar();

		// Speeds Up Pillar Generation
		else if (arg0.getSource() == t3) {
			pillarSpeed++;
			t2.setDelay((int) (t2.getDelay() * .93));
		}
	}

	public void keyPressed(KeyEvent arg0) {
		// Jump if {Space} is Pressed
		if (arg0.getKeyCode() == 32)
			vel = -20;

		// Restarts Game if {Enter} is Pressed
		else if (arg0.getKeyCode() == 10) {
			if (!t.isRunning()) {
				Pillar.pillars.clear();
				startGame();
			}
		}
	}

	public void keyReleased(KeyEvent arg0) {
	}

	public void keyTyped(KeyEvent arg0) {
	}
}
