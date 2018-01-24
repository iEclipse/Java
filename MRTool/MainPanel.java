import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainPanel extends JPanel implements MouseListener {

	// Button for Changing Username
	JButton changeUserButton = new JButton("Change User");

	// Labels for Quick Links
	JLabel quickLinksLabel = new JLabel("<html>Quick Links<br>=========================================</html>");
	JLabel hPageLabel = new JLabel("<html><a href=\"\">Home Page</a></html>");
	JLabel forumsLabel = new JLabel("<html><a href=\"\">Forums</a></html>");
	JLabel libraryLabel = new JLabel("<html><a href=\"\">Library</a></html>");
	JLabel guidesLabel = new JLabel("<html><a href=\"\">Guides</a></html>");

	public MainPanel() {
		setLayout(new FlowLayout(FlowLayout.LEFT));

		// Sets Cursor on Hover
		hPageLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		forumsLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		libraryLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		guidesLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// Adds Components to Main Panel
		add(changeUserButton);
		add(Box.createRigidArea(new Dimension(400, 0)));
		add(quickLinksLabel);
		add(Box.createRigidArea(new Dimension(400, 0)));
		add(hPageLabel);
		add(Box.createRigidArea(new Dimension(400, 0)));
		add(forumsLabel);
		add(Box.createRigidArea(new Dimension(400, 0)));
		add(libraryLabel);
		add(Box.createRigidArea(new Dimension(400, 0)));
		add(guidesLabel);

		changeUserButton.addMouseListener(this);
		hPageLabel.addMouseListener(this);
		forumsLabel.addMouseListener(this);
		libraryLabel.addMouseListener(this);
		guidesLabel.addMouseListener(this);
	}

	@Override
	// Opens Default Web Browser to Selected Links
	public void mouseClicked(MouseEvent arg0) {
		Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
		if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
			try {
				if (arg0.getSource() == hPageLabel) {
					desktop.browse(new URI("https://mapleroyals.com/?page=index"));
				} else if (arg0.getSource() == forumsLabel) {
					desktop.browse(new URI("https://mapleroyals.com/forum/"));
				} else if (arg0.getSource() == libraryLabel) {
					desktop.browse(new URI("https://mapleroyals.com/library/"));
				} else if (arg0.getSource() == guidesLabel) {
					desktop.browse(new URI("https://mapleroyals.com/forum/threads/%C2%BB-guide-directory-%C2%AB.29219/"));
				}
			} catch (Exception e) {
				System.out.println("Error: Invalid URL");
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	// Change Username Function
	@Override
	public void mouseReleased(MouseEvent arg0) {
		String newUser;
		if (arg0.getSource() == changeUserButton) {
			newUser = JOptionPane.showInputDialog(this, "MapleRoyals Username: ", "Change Username", JOptionPane.PLAIN_MESSAGE);
			if (newUser != null) {
				while (newUser.equals("")) {
					newUser = JOptionPane.showInputDialog(this, "MapleRoyals Username: ", "Change Username", JOptionPane.PLAIN_MESSAGE);
					if (newUser == null)
						return;
				}
				GUI.user = newUser;
				GUI.fmStartTime = 0;
				try {
					GUI.saveData();
					GUI.loadData();
				} catch (IOException e) {
					System.out.println("Error: Can't Save New Username to File");
					System.exit(0);
				}
			}
		}
	}
}
