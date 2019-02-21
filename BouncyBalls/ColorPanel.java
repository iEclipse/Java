import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

// Panel that Controls all Elements
public class ColorPanel extends JPanel implements MouseListener, ActionListener {
	Random rn = new Random();
	JLabel text = new JLabel(); // Displays Current RGB Value
	int r = 0, g = 0, b = 0, r2 = 0, g2 = 0, b2 = 0; // RGB = Previous Values, RGB2 = Current Values
	Timer t; // Repaint Timer
	ArrayList<double[]> positions = new ArrayList<double[]>(); // Positions of Balls
	ArrayList<double[]> velocities = new ArrayList<double[]>(); // Velocities of Balls

	// Constructor
	public ColorPanel() {
		setBounds(0, 0, 500, 500);
		addMouseListener(this);
		Timer t = new Timer(17, this);
		t.start();
	}

	// Obtains the Positions for the Next Frame
	public void getNewPos() {
		for (int i = 0; i < positions.size(); i++) {

			// Bounce X
			if (positions.get(i)[0] <= 0 || positions.get(i)[0] >= 475)
				velocities.get(i)[0] = -velocities.get(i)[0];
			// Bounce Y
			if (positions.get(i)[1] <= 0 || positions.get(i)[1] >= 450) {
				velocities.get(i)[1] = -velocities.get(i)[1];
			}

			// Gravity
			velocities.get(i)[1] += .5;

			// Bounce Physics (No Friction)
			positions.get(i)[0] += velocities.get(i)[0];
			positions.get(i)[1] += velocities.get(i)[1];

			// Prints Position & Velocity of Balls
			// System.out.println(positions.get(i)[0] + ", " + positions.get(i)[1] + " | " + velocities.get(i)[0] + ", " + velocities.get(i)[1]);

			// Set Boundary Position
			if (positions.get(i)[0] < 0)
				positions.get(i)[0] = 0;
			if (positions.get(i)[1] > 475)
				positions.get(i)[1] = 475;
			if (positions.get(i)[0] < 0)
				positions.get(i)[0] = 0;
			if (positions.get(i)[1] > 450) {
				// If No Bounce, Reset Bounce
				if (velocities.get(i)[1] <= 2)
					velocities.get(i)[1] = -20;
				positions.get(i)[1] = 450;
			}

			// System.out.println(positions.get(i)[0] + ", " +
			// positions.get(i)[1]);
		}
	}

	public void mouseClicked(MouseEvent arg0) {
	}

	// Renders Graphics
	public void paintComponent(Graphics gr) {
		Graphics2D g2d = (Graphics2D) gr;
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_LCD_CONTRAST, 100);
		g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

		// Fills Frame w/ Background Color
		g2d.setColor(new Color(r2, g2, b2));
		g2d.fillRect(0, 0, 500, 500);
		g2d.setColor(new Color(255 - r2, 255 - g2, 255 - b2));

		// Text that Displays Current RGB Values
		Font f = new Font("Calibri", Font.PLAIN, 30);
		g2d.setFont(f);
		g2d.drawString("RGB: " + r2 + ", " + g2 + ", " + b2, 20, 50);

		// Draws Balls
		for (int i = 0; i < positions.size(); i++) {
			g2d.fillOval((int) positions.get(i)[0], (int) positions.get(i)[1], 10, 10);
		}
		g2d.dispose();
	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent arg0) {
	}

	// On Mouse Release
	public void mouseReleased(MouseEvent arg0) {

		// Generates new Ball
		positions.add(new double[] { arg0.getX(), arg0.getY() });
		velocities.add(new double[] { rn.nextInt(2) == 0 ? rn.nextDouble() * 10 : rn.nextDouble() * -10, rn.nextDouble() });

		// Stores Previous RGB Values
		r2 = r;
		g2 = g;
		b2 = b;

		// Generates New Random RGB Values
		r = rn.nextInt(256);
		g = rn.nextInt(256);
		b = rn.nextInt(256);

		// Prints RGB Value Change to Console
		System.out.println(+r2 + ", " + g2 + ", " + b2 + " -> " + r + ", " + g + ", " + b);
	}

	public void actionPerformed(ActionEvent arg0) {
		// Increments RGB Values Until RGB = RGB2
		if (r < r2)
			r2--;
		else if (r > r2)
			r2++;
		if (g < g2)
			g2--;
		else if (g > g2)
			g2++;
		if (b < b2)
			b2--;
		else if (b > b2)
			b2++;

		// Gets Position of All Balls
		getNewPos();

		// Draws Animation
		repaint();
	}
}
