import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class GUI extends JFrame implements ActionListener, MouseListener, MouseMotionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	Random rn = new Random();
	Heroes h = new Heroes();
	Timer t = new Timer(50, this);
	JLabel icon;
	String type;
	int index;
	int repeatAmount;
	Point p;
	Clip c;
	AudioInputStream ais;

	public GUI() {
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setUndecorated(true);
		// setBackground(new Color(1.0f, 1.0f, 1.0f, 0f));
		setLocationRelativeTo(null);

		t.addActionListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);

		icon = new JLabel(new ImageIcon(h.images.get(rn.nextInt(h.heroList.size())).getPath()));
		icon.setBorder(BorderFactory.createLineBorder(new Color(47, 76, 130), 5));
		getContentPane().setBackground(Color.white);
		add(icon);
		pack();
		setVisible(true);
	}

	public void selectHero(String type) {
		this.type = type;
		t.setDelay(50);
		t.start();
	}

	public void randomizeIcon() {
		int newIndex = index;
		while (index == newIndex)
			switch (type) {
			case "Offense":
				newIndex = rn.nextInt(h.offSize);
				break;
			case "Defense":
				newIndex = rn.nextInt(h.defSize) + h.offSize;
				break;
			case "Tank":
				newIndex = rn.nextInt(h.tankSize) + h.offSize + h.defSize;
				break;
			case "Support":
				newIndex = rn.nextInt(h.supSize) + h.offSize + h.defSize + h.tankSize;
				break;
			case "All":
				newIndex = rn.nextInt(h.heroList.size());
				break;
			}
		// try {
		// ais = AudioSystem.getAudioInputStream(new File("AUDIO/Tick.wav"));
		// c = AudioSystem.getClip();
		// c.open(ais);
		// } catch (UnsupportedAudioFileException | IOException |
		// LineUnavailableException e) {
		// e.printStackTrace();
		// System.out.println("Error: Problem Loading Audio File.");
		// }
		icon.setIcon(new ImageIcon(h.images.get(index).getPath()));
		index = newIndex;
		// c.start();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		p = e.getPoint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		p = null;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Point cur = e.getLocationOnScreen();
		setLocation(cur.x - p.x, cur.y - p.y);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println(e.getKeyCode());
		//27 for Esc Key
		if (e.getKeyCode() == 19)
			System.exit(0);
		else if (e.getKeyCode() == 119)
			selectHero("All");
		else if (e.getKeyCode() == 120)
			selectHero("Offense");
		else if (e.getKeyCode() == 121)
			selectHero("Defense");
		else if (e.getKeyCode() == 122)
			selectHero("Tank");
		else if (e.getKeyCode() == 123)
			selectHero("Support");
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == t) {
			if (t.getDelay() < 180) {
				t.setDelay(t.getDelay() + 2);
			} else if (t.getDelay() < 230)
				t.setDelay(t.getDelay() + 1);
			else
				t.stop();
			randomizeIcon();
		}
	}
}
