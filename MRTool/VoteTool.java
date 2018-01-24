import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class VoteTool extends JPanel implements ActionListener {
	// Label for Timer Update
	JLabel timeRemainingLabel = new JLabel();

	// Vote Button
	JButton voteButton = new JButton("Vote");

	public VoteTool() {
		setLayout(new FlowLayout());
		// Timer for Updating UI
		Timer t = new Timer();
		t.scheduleAtFixedRate(new UpdateTimer(timeRemainingLabel, voteButton), 0, 60000);

		// Initially Disable Vote Button
		voteButton.setEnabled(false);
		voteButton.addActionListener(this);

		// Add Elements to Vote Tab
		add(Box.createRigidArea(new Dimension(400, 55)));
		add(voteButton);
		add(Box.createRigidArea(new Dimension(400, 0)));
		add(timeRemainingLabel);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// Sends You to GTop100 Voting Page to Vote
		if (ae.getSource() == voteButton) {
			Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
			if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
				try {
					desktop.browse(new URI("http://www.gtop100.com/topsites/MapleStory/sitedetails/MapleRoyals-The-Nostalgic-MapleStory-Server-79510?vote=1&pingUsername=" + GUI.user));
				} catch (Exception e) {
					timeRemainingLabel.setText("<html<font color=#FF0000>><center>There was a problem connecting to GTop100.</font></center></html>");
				}
			}
		}
	}

	// Updates GUI
	class UpdateTimer extends TimerTask {
		JLabel msg;
		JButton vote;

		public UpdateTimer(JLabel label, JButton voteButton) {
			msg = label;
			vote = voteButton;
		}

		public void run() {
			try {
				// Sends Username to Server and Clicks Vote Button
				Document doc = Jsoup.connect("https://mapleroyals.com/?page=vote").method(Method.POST).data("name", GUI.user).post();

				// Able to Vote
				if (doc.getElementById("main").ownText().equals("")) {
					msg.setText("<html><font color=#00AA00><center>You can now vote!</font></center></html>");
					vote.setEnabled(true);
				} else {

					// 24Hours Not Yet Passed
					vote.setEnabled(false);
					int minsRemaining = Integer.parseInt(doc.getElementById("tu").ownText());
					msg.setText("Time Remaining: " + minsRemaining / 60 + " hrs " + minsRemaining % 60 + " mins");
				}
			} catch (NullPointerException | NumberFormatException e1) {
				// Problem Finding Account
				msg.setText("<html><font color=#FF0000><center>Either the account doesn't exist or all <br>characters are under LV15.</font></center></html>");
			} catch (IOException e2) {
				// Connection to Server Error
				msg.setText("<html><font color=#FF0000><center>Cannot connect to the server.<br>Check your internet connection, firewall,<br>and the MapleRoyals homepage.</font></center></html>");
			} finally {
			}
		}
	}
}
