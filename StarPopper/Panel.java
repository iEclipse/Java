import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Panel extends JPanel implements MouseMotionListener, ActionListener {
	Timer t; // Repaint Timer
	Timer t2; // Star Generator
	ArrayList<Star> stars; // All Stars
	HashMap<String, Integer> allPos; // All Star Positions
	int[] mousePos; // Mouse Position
	int counter; // Counts How Many Stars Exploded
	Random rn; // RNG

	// Constructor
	public Panel() {
		allPos = new HashMap<String, Integer>();
		rn = new Random();
		setBackground(Color.BLACK);
		stars = new ArrayList<Star>();
		t = new Timer(1, this);
		t2 = new Timer(10, this);

		// Generates Random Starting Position
		mousePos = new int[] { rn.nextInt(485), rn.nextInt(460) };

		counter = 0;
		addMouseMotionListener(this);

		t.start();
		t2.start();
	}

	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseMoved(MouseEvent arg0) {
		// Stars follow Mouse if Inside Window
		mousePos[0] = arg0.getX();
		mousePos[1] = arg0.getY();
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g);
		int[] tempPos = new int[2]; // Next Star Position

		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_LCD_CONTRAST, 100);
		g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

		// For Each Star
		for (int i = 0; i < stars.size(); i++) {

			// Save Previous Star Position
			tempPos = stars.get(i).pos;

			// Update Position based on Mouse Position
			if (mousePos[0] > stars.get(i).pos[0])
				tempPos[0]++;
			if (mousePos[0] < stars.get(i).pos[0])
				tempPos[0]--;
			if (mousePos[1] > stars.get(i).pos[1])
				tempPos[1]++;
			if (mousePos[1] < stars.get(i).pos[1])
				tempPos[1]--;

			// If a Star Doesn't Collide with Another Star, Update Position
			if (!allPos.containsKey(tempPos[0] + "," + tempPos[1])) {
				stars.get(i).pos[0] = tempPos[0];
				stars.get(i).pos[1] = tempPos[1];
				// Otherwise
			} else {
				// Delete One of the Stars and Combine their Size
				stars.get(i).size += allPos.get(tempPos[0] + "," + tempPos[1]);
			}

			// Put the Star in Collision Checking List
			allPos.put(stars.get(i).pos[0] + "," + stars.get(i).pos[1], stars.get(i).size);

			// Paints the Stars
			g2d.setColor(Color.WHITE);
			g2d.fillOval(tempPos[0] - stars.get(i).size / 2, tempPos[1] - stars.get(i).size / 2, stars.get(i).size, stars.get(i).size);

			// If the Stars Get Too Big, Pop Them
			if (stars.get(i).size > 100) {
				stars.remove(stars.get(i--));
				counter++;

				// Randomize New Mouse Position & Change Background Color
				if (counter % 25 == 0) {
					mousePos[0] = rn.nextInt(485);
					mousePos[1] = rn.nextInt(460);
				}
				setBackground(new Color(rn.nextInt(150), rn.nextInt(150), rn.nextInt(150)));
			}
		}
		
		// Displays Score
		Font f = new Font("Calibri", Font.PLAIN, 30);
		g2d.setFont(f);
		g2d.drawString("Popped Stars: " + counter, 20, 40);
		g2d.drawString("Active Stars: " + stars.size(), 20, 70);
		
		// Clears Star Checking List
		allPos.clear();
	}

	public void actionPerformed(ActionEvent arg0) {
		// Repaints the GUI
		if (arg0.getSource() == t)
			repaint();

		// Generates A New Star
		else if (arg0.getSource() == t2)
			stars.add(new Star());
	}
}
