import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;


public class Frame extends JFrame{
	public Frame(){
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 500);
		setTitle("Keys");
		this.setLocation((dim.width - this.getWidth()) / 2, (dim.height - this.getHeight()) / 2);
		add(new Panel());
		setVisible(true);
	}
}
