import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLayeredPane;

public class UI extends JLayeredPane implements MouseMotionListener, MouseListener, ActionListener {
	JButton closeButton;
	Frame parent;
	String text;
	int width, height;
	boolean moved;
	Point p;

	public UI(String text, int width, int height, Frame parent) {
		this.text = text;
		setLayout(null);
		setVisible(false);
		this.parent = parent;
		this.width = width;
		this.height = height;
		setSize(width, height);
		setBorder(BorderFactory.createLineBorder(Color.blue));
		moveToFront(this);

		closeButton = new JButton("X");
		closeButton.setFocusPainted(false);
		closeButton.setMargin(new Insets(0, 0, 0, 0));
		closeButton.setBounds(width - 20, 0, 20, 20);
		closeButton.addActionListener(this);

		add(closeButton);
		addMouseMotionListener(this);
		addMouseListener(this);
	}

	public void toggle() {
		if (!moved)
			setLocation(parent.getWidth() / 2 - width / 2, parent.getHeight() / 2 - height / 2);
		parent.showPanel.setEnabled(!parent.showPanel.isEnabled());
		setVisible(!isVisible());
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
		g2d.setColor(Color.gray);
		g.fillRect(0, 0, width, 20);
		g2d.setColor(Color.white);
		g.fillRect(0, 20, width, height);
		g2d.drawString(text, 5, 14);
		System.out.println("X");
	}

	public void mouseDragged(MouseEvent arg0) {
		try {
			if (p.y < 20)
				setLocation(parent.getMousePosition().x - p.x, parent.getMousePosition().y - p.y);
		} catch (Exception e) {
			System.out.println("Tried to drag mouse past window.");
		}
	}

	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		p = arg0.getPoint();
	}

	public void mouseReleased(MouseEvent arg0) {

	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == closeButton)
			toggle();
	}
}
