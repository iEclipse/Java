import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Panel extends JPanel implements MouseListener, ActionListener {
	Timer t;
	Timer t2;
	ArrayList<Character> player;
	ArrayList<Character> opponent;
	ArrayList<JButton> spawner;
	Tower pTower;
	Tower oTower;
	int power;
	Collision c;

	public Panel() {
		setLayout(null);
		setSize(600, 400);
		c = new Collision();
		t = new Timer(1000 / 60, this);
		t2 = new Timer(10, this);
		player = new ArrayList<Character>();
		opponent = new ArrayList<Character>();
		spawner = new ArrayList<JButton>();

		pTower = new Tower(true, 2000);
		oTower = new Tower(false, 2000);
		power = 0;

		for (int i = 0; i < 3; i++) {
			JButton button = new JButton("" + (i + 1) * 100);
			button.setBounds(10 + 70 * i, 300, 70, 50);
			spawner.add(button);
			button.addActionListener(this);
			add(button);
		}

		for (int i = 0; i < 3; i++) {
			JButton button = new JButton("" + (3 - i) * 100);
			button.setBounds(505 - 70 * i, 300, 70, 50);
			spawner.add(button);
			button.addActionListener(this);
			add(button);
		}

		addMouseListener(this);
		t.start();
		t2.start();
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_LCD_CONTRAST, 100);
		g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

		g2d.setColor(new Color(0, 0, 100, (int) (pTower.hp / pTower.maxHP * 255)));
		g2d.fillRect(10, 230, 30, 60);
		g2d.drawString("" + pTower.hp, 12, 220);
		g2d.setColor(new Color(100, 0, 100, (int) (oTower.hp / oTower.maxHP * 255)));
		g2d.fillRect(545, 230, 30, 60);
		g2d.drawString("" + oTower.hp, 547, 220);

		for (int i = 0; i < player.size(); i++) {
			g2d.setColor(new Color(0, 0, 255, (int) (player.get(i).hp / player.get(i).maxHP * 255)));
			g2d.drawString("" + player.get(i).hp, (int)player.get(i).pos[0] + 8, (int)player.get(i).pos[1] + 20);
			g2d.fillArc((int)player.get(i).pos[0], (int)player.get(i).pos[1] + 30, 30, 30, 0, 360);
		}

		for (int i = 0; i < opponent.size(); i++) {
			g2d.setColor(new Color(255, 0, 255, (int) (opponent.get(i).hp / opponent.get(i).maxHP * 255)));
			g2d.drawString("" + opponent.get(i).hp, (int)opponent.get(i).pos[0] + 8, (int)opponent.get(i).pos[1] + 20);
			g2d.fillArc((int)opponent.get(i).pos[0], (int)opponent.get(i).pos[1] + 30, 30, 30, 0, 360);
		}

		g2d.setColor(Color.BLACK);
		g2d.drawString("Power: " + power, 10, 20);
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

	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void actionPerformed(ActionEvent arg0) {
		boolean move;

		if (arg0.getSource() == t) {
			for (int i = 0; i < player.size(); i++) {
				move = true;
				for (int j = 0; j < opponent.size(); j++) {
					if (c.isCollided(player.get(i).pos, opponent.get(j).pos, 7, 7)) {
						move = false;
						if (!player.get(i).cd) {
							opponent.get(j).hp -= player.get(i).att;
							player.get(i).cd = true;
							player.get(i).startCD();
						}
						if (opponent.get(j).hp <= 0)
							opponent.remove(j--);
						break;
					}
				}
				if (player.get(i).pos[0] <= 545 && player.get(i).pos[0] + player.get(i).spd[0] >= 545 && oTower.hp > 0) {
					player.get(i).pos[0] = 545;
					move = false;
					player.get(i).cd = true;
					player.get(i).startCD();
					oTower.hp -= player.get(i).att;
				}
				if (move)
					player.get(i).pos[0] += player.get(i).spd[0];
			}
			for (int i = 0; i < opponent.size(); i++) {
				move = true;
				for (int j = 0; j < player.size(); j++) {
					if (c.isCollided(player.get(j).pos, opponent.get(i).pos, 7, 7)) {
						move = false;
						if (!opponent.get(i).cd) {
							player.get(j).hp -= opponent.get(i).att;
							opponent.get(i).cd = true;
							opponent.get(i).startCD();
						}
						if (player.get(j).hp <= 0)
							player.remove(j--);
						break;
					}
				}
				if (opponent.get(i).pos[0] >= 10 && opponent.get(i).pos[0] + opponent.get(i).spd[0] <= 10 && pTower.hp > 0) {
					opponent.get(i).pos[0] = 10;
					move = false;
					opponent.get(i).cd = true;
					opponent.get(i).startCD();
					pTower.hp -= opponent.get(i).att;
					break;
				}
				if (move)
					opponent.get(i).pos[0] += opponent.get(i).spd[0];
			}
			if (pTower.hp < 0)
				pTower.hp = 0;
			else if (oTower.hp < 0)
				oTower.hp = 0;

			repaint();
		} else if (arg0.getSource() == t2 && power < 1000) {
			power++;
		} else if (arg0.getSource() == spawner.get(0) && power >= 100) {
			player.add(new Character(1, 5, 500, new double[] { 1, 0 }, true));
			power -= 100;
		} else if (arg0.getSource() == spawner.get(1) && power >= 200) {
			player.add(new Character(30, 10, 300, new double[] { 1.5, 0 }, true));
			power -= 200;
		} else if (arg0.getSource() == spawner.get(2) && power >= 300) {
			player.add(new Character(10, 15, 500, new double[] { 2, 0 }, true));
			power -= 300;
		} else if (arg0.getSource() == spawner.get(3)) {
			opponent.add(new Character(1, 5, 500, new double[] { 1.5, 0 }, false));
			power += 300;
		} else if (arg0.getSource() == spawner.get(4)) {
			opponent.add(new Character(100, 1, 700, new double[] { 1, 0 }, false));
			power += 200;
		} else if (arg0.getSource() == spawner.get(5)) {
			opponent.add(new Character(5, 3, 200, new double[] { 2, 0 }, false));
			power += 100;
		}
		if (power > 1000)
			power = 1000;
	}

	public void clear() {
		player.clear();
		opponent.clear();
	}
}
