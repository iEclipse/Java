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

import javax.swing.JPanel;
import javax.swing.Timer;

public class Panel extends JPanel implements KeyListener, ActionListener {
	Timer t; // Repaint Timer
	Timer t2; // Key Spawner Timer
	ArrayList<Key> spawned = new ArrayList<Key>(); // Spawned Keys
	ArrayList<Key> hit = new ArrayList<Key>(); // Keys Correctly Timed
	ArrayList<Key> miss = new ArrayList<Key>(); // Keys Missed
	Collision c = new Collision(); // Collision Checker
	Random rn = new Random(); // RNG
	int count, total; // Number of Hit Tiles, Total Number of Hit/Missed Tiles

	// Constructor
	public Panel() {
		setFocusable(true);
		t = new Timer(16, this);
		t2 = new Timer(1000, this);
		setBackground(Color.GRAY);
		count = 0;
		total = 0;
		t.start();
		t2.start();
		addKeyListener(this);
	}

	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	// Checks to See if Tile is Hit
	public void keyTyped(KeyEvent arg0) {

		// For All Spawned Tiles
		for (int i = 0; i < spawned.size(); ++i) {

			// If ASDFJKL; Matches the Correct Lanes and are Timed Properly
			if ((arg0.getKeyChar() == 'a' && spawned.get(i).pos[0] == 0 && c.isCollided(new double[] { spawned.get(i).pos[0], spawned.get(i).pos[1], spawned.get(i).pos[0] + 30, spawned.get(i).pos[1] + 10 }, new double[] { 0, 420, 600, 440 })) || (arg0.getKeyChar() == 's' && spawned.get(i).pos[0] == 1 && c.isCollided(new double[] { spawned.get(i).pos[0], spawned.get(i).pos[1], spawned.get(i).pos[0] + 30, spawned.get(i).pos[1] + 10 }, new double[] { 0, 420, 600, 440 }))
					|| (arg0.getKeyChar() == 'd' && spawned.get(i).pos[0] == 2 && c.isCollided(new double[] { spawned.get(i).pos[0], spawned.get(i).pos[1], spawned.get(i).pos[0] + 30, spawned.get(i).pos[1] + 10 }, new double[] { 0, 420, 600, 440 })) || (arg0.getKeyChar() == 'f' && spawned.get(i).pos[0] == 3 && c.isCollided(new double[] { spawned.get(i).pos[0], spawned.get(i).pos[1], spawned.get(i).pos[0] + 30, spawned.get(i).pos[1] + 10 }, new double[] { 0, 420, 600, 440 }))
					|| (arg0.getKeyChar() == 'j' && spawned.get(i).pos[0] == 4 && c.isCollided(new double[] { spawned.get(i).pos[0], spawned.get(i).pos[1], spawned.get(i).pos[0] + 30, spawned.get(i).pos[1] + 10 }, new double[] { 0, 420, 600, 440 })) || (arg0.getKeyChar() == 'k' && spawned.get(i).pos[0] == 5 && c.isCollided(new double[] { spawned.get(i).pos[0], spawned.get(i).pos[1], spawned.get(i).pos[0] + 30, spawned.get(i).pos[1] + 10 }, new double[] { 0, 420, 600, 440 }))
					|| (arg0.getKeyChar() == 'l' && spawned.get(i).pos[0] == 6 && c.isCollided(new double[] { spawned.get(i).pos[0], spawned.get(i).pos[1], spawned.get(i).pos[0] + 30, spawned.get(i).pos[1] + 10 }, new double[] { 0, 420, 600, 440 })) || (arg0.getKeyChar() == ';' && spawned.get(i).pos[0] == 7 && c.isCollided(new double[] { spawned.get(i).pos[0], spawned.get(i).pos[1], spawned.get(i).pos[0] + 30, spawned.get(i).pos[1] + 10 }, new double[] { 0, 420, 600, 440 }))) {
				hit.add(spawned.remove(i--)); // Remove from Spawned, Add to Hit List
				count++;
				total++;
				break;

				// If ASDFJL; Matches a Tile on a Lane, but is Not Timed Properly
			} else if ((arg0.getKeyChar() == 'a' && spawned.get(i).pos[0] == 0) || (arg0.getKeyChar() == 's' && spawned.get(i).pos[0] == 1) || (arg0.getKeyChar() == 'd' && spawned.get(i).pos[0] == 2) || (arg0.getKeyChar() == 'f' && spawned.get(i).pos[0] == 3) || (arg0.getKeyChar() == 'j' && spawned.get(i).pos[0] == 4) || (arg0.getKeyChar() == 'k' && spawned.get(i).pos[0] == 5) || (arg0.getKeyChar() == 'l' && spawned.get(i).pos[0] == 6) || (arg0.getKeyChar() == ';' && spawned.get(i).pos[0] == 7)) {
				miss.add(spawned.remove(i)); // Remove from Spawned, Add to Miss List
				total++;
				break;
			}
		}
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == t) // Repaint Timer
			repaint();
		else if (arg0.getSource() == t2) { // Tile Generator
			spawned.add(new Key());
			t2.setDelay(150 + rn.nextInt(1350)); // Uses a Different Delay for Each Tile Spawned
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(new Font("default", Font.BOLD, 16));
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

		// Draws Orange Hit Bar
		g2d.setColor(new Color(40, 40, 40));
		for (int i = 0; i < 8; i++)
			g2d.fillRect(95 + i * 50, 0, 40, 500);
		g2d.setColor(Color.ORANGE);
		g2d.fillRect(0, 420, 600, 20);

		// Displays Letters on Each Lane
		g2d.setColor(Color.WHITE);
		g2d.drawString("A", 110, 400);
		g2d.drawString("S", 160, 400);
		g2d.drawString("D", 210, 400);
		g2d.drawString("F", 260, 400);
		g2d.drawString("J", 310, 400);
		g2d.drawString("K", 360, 400);
		g2d.drawString("L", 410, 400);
		g2d.drawString(";", 460, 400);

		// Draws White Spawned Tiles
		for (int i = 0; i < spawned.size(); ++i) {
			spawned.get(i).pos[1] += 5;
			g2d.fillRect(100 + (int) spawned.get(i).pos[0] * 50, (int) spawned.get(i).pos[1], 30, 10);
			if (spawned.get(i).pos[1] >= getHeight() - 10) {
				spawned.remove(i--);
				total++;
			}
		}

		// Draws Hit Tiles
		g2d.setColor(Color.GREEN);
		for (int i = 0; i < hit.size(); ++i) {
			hit.get(i).pos[1] += 5;
			g2d.fillRect(100 + (int) hit.get(i).pos[0] * 50, (int) hit.get(i).pos[1], 30, 10);
			if (hit.get(i).pos[1] >= getHeight())
				hit.remove(i--);
		}

		// Draws Missed Tiles
		g2d.setColor(Color.RED);
		for (int i = 0; i < miss.size(); ++i) {
			miss.get(i).pos[1] += 5;
			g2d.fillRect(100 + (int) miss.get(i).pos[0] * 50, (int) miss.get(i).pos[1], 30, 10);
			if (miss.get(i).pos[1] >= getHeight())
				miss.remove(i--);
		}

		// Displays Accuracy
		g2d.setColor(Color.GREEN);
		g2d.drawString("Accuracy: " + count + " / " + total, 10, 20);
		
		g2d.dispose();
	}
}
