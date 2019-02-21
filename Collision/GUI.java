import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JFrame {
	double[] a;
	double[] b;
	double[] c;
	double[] d;
	Pane p = new Pane();
	Collision co = new Collision();

	public GUI() {
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		a = new double[] { 0,0, 120, 120 };
		b = new double[] { 121, 121, 150, 150 };
		c = new double[] { 130, 130 };
		d = new double[] { 170, 170 };

		int[] vel = new int[] { 10, 10 };
		System.out.println(co.isCollided(a, b));
		System.out.println(co.isCollided(c, d, 30, 30));

		add(p);
		setVisible(true);
	}

	class Pane extends JPanel {
		public Pane() {
			setSize(600, 600);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;

			Path2D.Double path = new Path2D.Double();
			path.moveTo(a[0], a[1]);
			path.lineTo(a[0], a[3]);
			path.lineTo(a[2], a[3]);
			path.lineTo(a[2], a[1]);
			path.closePath();
			g2d.setColor(new Color(255, 0, 0, 100));
			g2d.draw(path);
			g2d.fill(path);

			g2d.drawOval((int) c[0] - 30, (int) c[1] - 30, 60, 60);
			g2d.fillOval((int) c[0] - 30, (int) c[1] - 30, 60, 60);

			Path2D.Double path2 = new Path2D.Double();
			path2.moveTo(b[0], b[1]);
			path2.lineTo(b[0], b[3]);
			path2.lineTo(b[2], b[3]);
			path2.lineTo(b[2], b[1]);
			path2.closePath();
			g2d.setColor(new Color(0, 0, 255, 100));
			g2d.draw(path2);
			g2d.fill(path2);

			g2d.drawOval((int) d[0] - 30, (int) d[1] - 30, 60, 60);
			g2d.fillOval((int) d[0] - 30, (int) d[1] - 30, 60, 60);
			g2d.dispose();
		}
	}

}
