import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

public class GUI {
	// File for R/W Data
	static File f = new File("Settings.ini");
	// MapleRoyals Username
	static String user = "N/A";
	// Start Time for Merchant Timer
	public static long fmStartTime = 0;
	// Main Frame of Program
	static JFrame jf = new JFrame();

	// Tabs for GUI
	static JTabbedPane jtp = new JTabbedPane();

	public static void main(String[] args) throws IOException {

		// Initialize Main Frame
		jf.setSize(new Dimension(420, 250));
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLocationRelativeTo(null);
		jf.setResizable(false);

		// Initial Setup for New User
		if (!f.exists()) {
			// Gets MapleRoyals Username and Stores to Settings.ini
			user = JOptionPane.showInputDialog(jf, "MapleRoyals Username: ", "First Time Setup", JOptionPane.PLAIN_MESSAGE);
			if (user == null || user.replace(" ", "").length() == 0)
				System.exit(0);
			saveData();
		}

		// Loads Program Data
		loadData();

		// Adds Tools to TabPane for Main Frame
		jtp.add("Main", new MainPanel());
		jtp.add("Vote", new VoteTool());
		jtp.add("Leech", new LeechTool());
		jtp.add("Bossing", new BossingTool());
		jtp.add("FM", new FMTool());

		jf.add(jtp);
		jf.setVisible(true);
	}

	// Saves Program Data to File
	public static void saveData() throws IOException {
		FileWriter fw = new FileWriter(f);
		fw.write("user=" + user + "\r\n");
		fw.write("merchantOpenTime=" + fmStartTime);
		fw.close();
	}

	// Loads Program Data from File
	public static void loadData() throws IOException {
		// Loads Data from File
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		try {
			String[] fileContents = new String[2];

			// Loads Username
			String[] loadUser = br.readLine().replace(" ", "").split("=");
			fileContents[0] = loadUser[1];

			// Loads Merchant Start Time
			String[] loadFMTime = br.readLine().replace(" ", "").split("=");
			fileContents[1] = loadFMTime[1];

			// Assigns Data to Variables
			if (fileContents.length == 2) {
				user = fileContents[0];
				fmStartTime = Long.parseLong(fileContents[1]);
			}
		} catch (Exception e) {
			System.out.println("Error: Settings.ini has been tampered with.");
			System.exit(0);
		}
		// Closes Files
		br.close();
		fr.close();

		// Sets Title
		jf.setTitle("MapleRoyals Tool - User: " + GUI.user);

		// Sets New Timers for Vote & FM
		if (jtp.getTabCount() > 4) {
			jtp.setComponentAt(1, new VoteTool());
			jtp.setComponentAt(4, new FMTool());
		}
	}
}
