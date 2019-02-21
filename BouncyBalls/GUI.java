import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class GUI extends JFrame {
	// Constructor
	public GUI() {
		ColorPanel p = new ColorPanel();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		// Frame Settings
		setBounds(dim.width / 2 - 250, dim.height / 2 - 250, 500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Bouncy Balls");

		// Frame Elements
		add(p);

		// Show Frame
		setVisible(true);
	}
}
