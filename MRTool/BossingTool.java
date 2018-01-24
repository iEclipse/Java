import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BossingTool extends JPanel {
	public BossingTool() {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel userLabel = new JLabel("<html><u>NOT CODED</u><br>- Bossing Squad Member List<br>- Split Loot Calculator<br>- Member Item Loot Request Tracking<br>- Krex/HT Cooldown Tracker</html>");
		add(userLabel);
	}
}
