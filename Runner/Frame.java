import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Frame extends JFrame {
	public Frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(dim.width / 2 - 250, dim.height / 2 - 250, 500, 500);
		setTitle("Runner");
		setSize(500, 500);
		add(new Character());
		setVisible(true);
	}
}
