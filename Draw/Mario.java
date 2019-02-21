import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.QuadCurve2D;

import javax.swing.JPanel;

// Panel that Controls all Elements
public class Mario extends JPanel {

	// Constructor
	public Mario() {
		setBounds(0, 0, 500, 500);
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

		// Head
		g2d.drawArc(155, 118, 80, 100, 160, 28);
		g2d.drawArc(155, 130, 80, 100, 222, 95);

		// Ear
		g2d.drawArc(218, 182, 35, 35, 233, 230);
		g2d.drawArc(230, 192, 20, 15, 90, 170);

		// Hair
		g2d.drawArc(210, 184, 8, 8, 110, 170);
		g2d.drawArc(215, 190, 8, 8, 135, 200);
		g2d.drawArc(147, 95, 80, 100, 310, 18);
		g2d.drawArc(156, 100, 80, 110, 313, 18);

		// Eyes
		g2d.fillOval(160, 160, 8, 20);
		g2d.fillOval(185, 165, 8, 20);

		// Mustache
		g2d.drawArc(127, 100, 85, 100, 273, 38);
		g2d.drawArc(155, 200, 14, 14, 172, 170);
		g2d.drawArc(169, 199, 14, 14, 190, 170);
		g2d.drawArc(180, 195, 14, 14, 248, 130);
		g2d.drawArc(190, 188, 14, 14, 250, 188);

		// Nose
		g2d.setColor(new Color(238, 238, 238));
		g2d.fillArc(140, 175, 35, 30, 30, 290);
		g2d.setColor(Color.BLACK);
		g2d.drawArc(140, 175, 35, 30, 30, 300);

		// Hat
		g2d.drawArc(100, 148, 140, 140, 30, 100);
		g2d.drawArc(160, 122, 38, 38, 322, 238);

		g2d.dispose();
	}
}
