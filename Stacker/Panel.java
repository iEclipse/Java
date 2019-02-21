import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JPanel;
import javax.swing.Timer;

//Controls
// Left, Right = Movement Keys
// Up = Jump
// Down = Crouch (Shield if on a Platform)
//
// Combinations
// Left(Hold) > Down(Repeat) or Right(Hold) > Down(Repeat) = Mini Hop
// Down(Hold) > Up > Left(Hold) or Down(Hold) > Up > Right(Hold) = Glide Further

public class Panel extends JPanel implements KeyListener, ActionListener {
	Stack<Integer> pressedKeys; // List of Actively Pressed Keys
	Stack<Platform> platforms; // List of Active Platforms
	ArrayList<Hazard> hazards = new ArrayList<Hazard>(); // List of Hazards
	Timer t; // Repaint Timer
	Timer t2; // Freeze Platforms Timer
	Timer t3; // Hazard Generator
	Timer t4; // Fade Timer
	Player p; // Player
	Random rn; // Randomizer
	boolean fade; // Fade if Collide with Hazard
	boolean frozen; // Platforms are All Frozen
	boolean grounded; // Character is on Ground
	boolean crouch; // Character is Crouching
	boolean shield; // Shield Activated
	int greenPlatforms; // Number of Touched Platforms
	int set; // Set of 5 Activated Platforms
	int camera; // Scrolling Camera Height
	int camSpd; // Camera Speed
	int points; // Displayed Height (Uses Height for Comparison)

	// Constructor
	public Panel() {
		rn = new Random();
		setBackground(Color.DARK_GRAY);
		pressedKeys = new Stack<Integer>();
		platforms = new Stack<Platform>();
		t = new Timer(16, this);
		t3 = new Timer(1000, this);
		p = new Player();
		grounded = false;
		crouch = false;
		greenPlatforms = 0;
		set = 0;
		camera = p.pos[1];
		camSpd = 0;

		setFocusable(true);
		addKeyListener(this);
		genPlatforms();
		t.start();
		t3.start();
	}

	public void genPlatforms() {
		for (int i = 0; i < 5; i++)
			platforms.add(new Platform(400 - (250 * set) - 50 * i));
	}

	public void keyPressed(KeyEvent arg0) {

		// Add Unique Keys Pressed to List
		if (!pressedKeys.contains(arg0.getKeyCode()))
			pressedKeys.push(arg0.getKeyCode());

		if (arg0.getKeyCode() != 40)
			shield = false;

		// Perform Action of Newest Key Pressed
		switch (pressedKeys.peek()) {

		// Ctrl
		case 17:
			// Fade through Platform
			if (grounded && p.pos[1] != 460 - (int) (p.size[1] / 2)) {
				grounded = false;
				p.pos[1]++;
			}
			pressedKeys.pop();
			break;

		// Left
		case 37:
			// Move
			if (!crouch)
				p.vel[0] = -5;
			else
				p.vel[0] = -10;
			break;

		// Up
		case 38:
			// If on Ground, Jump
			if (grounded) {
				grounded = false;
				p.vel[1] = -17;
			}
			break;

		// Right
		case 39:
			// Move
			if (!crouch)
				p.vel[0] = 5;
			else
				p.vel[0] = 10;
			break;

		// Down
		case 40:
			// Activate Shield if Crouching on Ground
			if (grounded && !shield)
				shield = true;

			// Crouch
			if (!crouch) {
				grounded = false;
				crouch = true;
				p.size[1] /= 2;
			}
			break;
		}
	}

	public void keyReleased(KeyEvent arg0) {
		switch (arg0.getKeyCode()) {
		case 17:
			// Return when CTRL is pressed, because it is removed immediately from pressedKey list in keyPressed()
			return;

			// Left
		case 37:
			// If Right Key is Still Held Down, Move Right
			if (pressedKeys.contains(39))
				p.vel[0] = 5;
			// Otherwise Stop Moving
			else
				p.vel[0] = 0;
			break;

		// Up
		case 38:
			break;

		// Right
		case 39:
			// If Left Key is Still Held Down, Move Left
			if (pressedKeys.contains(37))
				p.vel[0] = -5;
			// Otherwise Stop Moving
			else
				p.vel[0] = 0;
			break;

		// Down
		case 40:
			// Get Up if Crouching
			if (shield) {
				shield = false;
			}
			if (crouch) {
				crouch = false;
				p.pos[1] -= p.size[1] / 2;
				p.size[1] *= 2;
			}
			break;
		}

		// Remove Key from Pressed List
		pressedKeys.remove(pressedKeys.indexOf(arg0.getKeyCode()));
	}

	public void keyTyped(KeyEvent arg0) {
	}

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

		// Draws Platforms
		for (int i = 0; i < platforms.size(); i++) {

			// Freezes Platforms
			if (!frozen)
				platforms.get(i).movePlatform();

			// Draws Untouched Platforms
			if (!platforms.get(i).touched) {

				// Frozen Platforms
				if (frozen && platforms.get(i).type != 1)
					g2d.setColor(Color.BLUE);
				// Normal Platform
				else if (platforms.get(i).type == 0)
					g2d.setColor(Color.GRAY);

				// Freeze Buff Platform
				else if (platforms.get(i).type == 1)
					g2d.setColor(Color.CYAN);

				// Touched Platforms
			} else {
				g2d.setColor(Color.GREEN);
			}

			g2d.fillRect(platforms.get(i).pos[0], 460 + platforms.get(i).pos[1] - camera, 50, 8);
		}

		// Draws Hazards
		g2d.setColor(Color.ORANGE);
		for (int i = 0; i < hazards.size(); i++) {
			hazards.get(i).moveHazard(p.pos, p.size);
			g2d.fillRect(hazards.get(i).pos[0], 460 + hazards.get(i).pos[1] - camera, hazards.get(i).size[0], hazards.get(i).size[1]);
			if (hazards.get(i).pos[1] > 460 - hazards.get(i).size[1])
				hazards.remove(i--);
		}

		// Draws Character Faded
		if (fade) {
			g2d.setColor(Color.PINK);
			g2d.fillRect((int) (p.pos[0] - p.size[0] / 2.0), 460 + (int) (p.pos[1] - p.size[1] / 2) - camera, p.size[0], p.size[1]);
		}
		// Draws Shield
		else if (shield) {
			g2d.setColor(Color.ORANGE);
			g2d.fillArc(p.pos[0] - p.size[0] / 2 - 2, 460 + p.pos[1] - p.size[0] / 2 - camera - 2, p.size[0] + 4, p.size[0] + 4, 0, 360);
		}
		// Draws Normal Character
		else {
			g2d.setColor(Color.WHITE);
			g2d.fillRect((int) (p.pos[0] - p.size[0] / 2.0), 460 + (int) (p.pos[1] - p.size[1] / 2) - camera, p.size[0], p.size[1]);
		}

		// Displays Score
		Font f = new Font("Calibri", Font.PLAIN, 30);
		g2d.setFont(f);
		if (greenPlatforms * 50 > points)
			points += 5;
		g2d.drawString("Points: " + points, 20, 40);
		g2d.dispose();
	}

	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getSource() == t) {

			// Hazard Collision
			for (int i = 0; i < hazards.size(); i++) {
				if (!shield && p.pos[0] - p.size[0] / 2 < hazards.get(i).pos[0] && p.pos[0] + p.size[0] / 2 > hazards.get(i).pos[0] && p.pos[1] - p.size[1] / 2 < hazards.get(i).pos[1] && p.pos[1] + p.size[1] / 2 > hazards.get(i).pos[1]) {
					fade = true;
					grounded = false;
					t4 = new Timer(1000, this);
					t4.start();
				}
			}

			// Platform Collision
			if (!fade)
				for (int i = 0; i < platforms.size(); i++)
					if (!grounded && p.pos[0] + p.size[0] / 2 > platforms.get(i).pos[0] && p.pos[0] - p.size[0] / 2 < platforms.get(i).pos[0] + 50 && p.pos[1] <= platforms.get(i).pos[1] - p.size[1] / 2 && p.pos[1] + p.vel[1] >= platforms.get(i).pos[1] - p.size[1] / 2 - 2) {
						p.pos[1] = platforms.get(i).pos[1] - p.size[1] / 2;
						p.vel[1] = 0;
						grounded = true;

						// Change Platform to Touched
						if (!platforms.get(i).touched) {
							platforms.get(i).touched = true;

							// Freeze Platforms
							if (platforms.get(i).type == 1){
								frozen = true;
								t2 = new Timer(5000, this);
								t2.start();
							}

							greenPlatforms++;
							// Generate New Platforms if All are Touched
							if (greenPlatforms % 5 == 0) {
								set++;
								genPlatforms();
							}
						}
					}

			// Applies Position
			if (p.vel[0] != 0 && p.vel[1] != 0)
				p.pos[0] += p.vel[0];
			p.pos[1] += p.vel[1];

			// Left & Right Bounding Box
			if (p.pos[0] < p.size[0] / 2)
				p.pos[0] = p.size[0] / 2;
			else if (p.pos[0] > 484 - p.size[0] / 2)
				p.pos[0] = 484 - p.size[0] / 2;

			// If in Air, Gravity Applies
			if (p.pos[1] <= 460 - (int) (p.size[1]) / 2 && !grounded) {
				p.vel[1] ++;
			} else {

				// Lock Floor Position
				if (!grounded) {
					p.pos[1] = 461 - (int) (p.size[1] / 2);
					p.vel[1] = 0;
					grounded = true;
				}
			}

			// Camera Pans Down
			if (camera < p.pos[1] + 12)
				camSpd++;

			// Camera Pans Up
			else if (grounded && Math.abs(camera - p.pos[1]) > 120)
				camSpd--;

			// No Camera Movement
			else
				camSpd = 0;
			camera += camSpd;

			// Camera Locked on Ground
			if (camera > 460)
				camera = 460;

			repaint();
		}

		// Freeze Timer
		else if (arg0.getSource() == t2) {
			frozen = false;
			t2.stop();

			// Generate New Hazard
		} else if (arg0.getSource() == t3) {
			hazards.add(new Hazard(camera));
			t3.setDelay(rn.nextInt(5000) + 500);

			// Remove Fade
		} else if (arg0.getSource() == t4) {
			fade = false;
			t4.stop();
		}
	}
}
