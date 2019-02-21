import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class GUI extends JFrame {
	Panel p; // Panel that Contains all UI
	int playerHP, pMaxHP; // Player's HP and MaxHP
	int cpuHP, oMaxHP; // cpu's HP and MaxHP
	int pCharge, cpuCharge; // Player & cpu's Damage Charge

	/* CREATES THE GUI */
	GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 240);
		setLocationRelativeTo(null);
		setResizable(false);

		p = new Panel();
		add(p);
		setVisible(true);
	}

	/* CREATES THE PANEL */
	class Panel extends JPanel implements ActionListener {

		// Buttons
		JButton attack = new JButton("Attack");
		JButton charge = new JButton("Charge");
		JButton block = new JButton("Block");
		JButton playAgain = new JButton("Play Another Game?");

		// Text Box
		JTextArea display = new JTextArea(10, 40);
		JScrollPane scroll = new JScrollPane(display);

		// Initializes the Panel
		Panel() {
			attack.addActionListener(this);
			charge.addActionListener(this);
			block.addActionListener(this);
			playAgain.addActionListener(this);

			display.setEditable(false);
			scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

			add(attack);
			add(charge);
			add(block);
			add(playAgain);
			add(scroll);

			// Starts the Game
			startGame();
		}

		// Sets all Initial Values then Starts the Game
		void startGame() {
			playerHP = 25;
			pMaxHP = playerHP;
			cpuHP = 25;
			oMaxHP = cpuHP;
			pCharge = 0;
			cpuCharge = 0;

			// Hide and Show the Correct Buttons when Starting Game
			playAgain.setVisible(false);
			attack.setEnabled(false);
			attack.setVisible(true);
			charge.setVisible(true);
			block.setVisible(true);

			// Adds a Comment to the Text Box
			display.setText(display.getText() + "New Game Started.\n\n");
		}

		// Draws the GUI
		protected void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			super.paintComponent(g2d);

			// Draws the Green HP Bars
			g2d.setColor(Color.GREEN);
			g2d.fillRect(10, 200 - (int) (190 * (double) playerHP / pMaxHP), 50, (int) (190 * (double) playerHP / pMaxHP));
			g2d.fillRect(535, 200 - (int) (190 * (double) cpuHP / oMaxHP), 50, (int) (190 * (double) cpuHP / oMaxHP));

			// Draws the Gray Missing HP Portion
			g2d.setColor(Color.GRAY);
			g2d.fillRect(10, 10, 50, 190 - (int) (190 * (double) playerHP / pMaxHP));
			g2d.fillRect(535, 10, 50, 190 - (int) (190 * (double) cpuHP / oMaxHP));
		}

		// Button Actions
		public void actionPerformed(ActionEvent arg0) {
			int cpuAction = getCPUAction(); // Grabs a Pre-determined CPU Action
			boolean blocked = false; // Changes if the Player Click the Block Button

			// Play Again Button
			if (arg0.getSource() == playAgain) {
				startGame();
				repaint();
				return;

				// Attack Button
			} else if (arg0.getSource() == attack) {

				// Do Damage if the CPU isn't Blocking
				if (cpuAction != 2) {
					cpuHP -= pCharge;
					display.setText(display.getText() + "Player has dealt " + pCharge + " damage to the CPU.\n");

					// Otherwise Just Make a Comment
				} else
					display.setText(display.getText() + "CPU has blocked the Player's attack.\n");

				// Resets Charge After Attempting to Attack and Disables the Attack Button
				pCharge = 0;
				attack.setEnabled(false);

				// Charge Button
			} else if (arg0.getSource() == charge) {

				// Enables the Attack Button when at least 1 Charge is Reached
				if (!attack.isEnabled())
					attack.setEnabled(true);

				// Make Comment on Charge Amount
				display.setText(display.getText() + "Player's charge has increased to " + ++pCharge + " for the next attack.\n");

				// Block Button
			} else if (arg0.getSource() == block) {
				blocked = true;

				// Make Comment on Player Blocking Nothing
				if (cpuAction != 0)
					display.setText(display.getText() + "Player blocked nothing.\n");
			}

			// Do CPU's Turn
			cpuTurn(blocked, cpuAction);

			// Extra New Line in Text Box to Separate Text
			display.setText(display.getText() + "\n");

			// Check if Someone Won
			checkWin();

			// Updates GUI
			repaint();
		}

		// Randomizes a CPU Action
		int getCPUAction() {
			Random rn = new Random();

			// Only Choose Charge or Block if Charge = 0
			if (cpuCharge == 0)
				return rn.nextInt(2) + 1;

			// Otherwise Pick Any Option
			return rn.nextInt(3);
		}

		// Performs the CPU's Predetermined Action
		void cpuTurn(boolean playerBlocked, int option) {

			// Option was Determined in getCPUAction
			switch (option) {

			// Attack
			case 0:

				// If the Player isn't Blocking, Do Damage
				if (!playerBlocked) {
					playerHP -= cpuCharge;
					display.setText(display.getText() + "CPU has dealt " + cpuCharge + " damage to the Player.\n");

					// Otherwise Comment that the Attack was Blocked
				} else
					display.setText(display.getText() + "Player has blocked the CPU's attack.\n");

				// Reset CPU's Charge
				cpuCharge = 0;
				break;

			// Charge
			case 1:
				display.setText(display.getText() + "CPU's charge has increased to " + ++cpuCharge + " for the next attack.\n");
				break;

			// Block (This only Happens when CPU Blocks Nothing, other Situations are Handled during Player's Turn)
			case 2:
				display.setText(display.getText() + "CPU blocked nothing.\n");
				break;
			}
		}

		// Check if Someone Won
		void checkWin() {

			// If Player Died
			if (playerHP <= 0)

				// Check if CPU also Died
				if (cpuHP <= 0)
					display.setText(display.getText() + "No one won.\n");
				else
					display.setText(display.getText() + "CPU has defeated the Player.\n");

			// Otherwise Check if only the CPU Died
			else if (cpuHP <= 0)
				display.setText(display.getText() + "Player has defeated the CPU.\n");

			// No Matter Who Won, Hide All the Buttons Except the Play Again Button
			if (playerHP <= 0 || cpuHP <= 0) {
				attack.setVisible(false);
				charge.setVisible(false);
				block.setVisible(false);
				playAgain.setVisible(true);
			}
		}
	}
}
