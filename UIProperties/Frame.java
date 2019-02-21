import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Frame extends JFrame implements ActionListener {
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	UI panel = new UI("Custom UI",300, 400, this);
	ItemUI iPanel = new ItemUI(this);
	JButton showPanel = new JButton("Activate");
	JButton quit = new JButton("Quit");

	public Frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setLayout(null);
		setSize(400, 500);
		this.setLocation((dim.width - this.getWidth()) / 2, (dim.height - this.getHeight()) / 2);

		showPanel.setBounds(0, 0, 90, 20);
		quit.setBounds(0, 20, 90, 20);

		showPanel.addActionListener(this);
		quit.addActionListener(this);

		add(iPanel);
		add(showPanel);
		add(quit);
		add(panel);

		setVisible(true);
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == showPanel) {
			panel.toggle();
			showPanel.setEnabled(false);
		} else if (arg0.getSource() == quit) {
			System.exit(0);
		}
	}
}
