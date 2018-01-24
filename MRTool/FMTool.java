import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.text.NumberFormatter;

public class FMTool extends JPanel implements KeyListener, ActionListener {
	// Frame
	JFrame fmSetup = new JFrame();

	// LABELS
	// ==========
	// setupChannel = Setup Frame
	// setupRoom = Setup Frame
	// fmLocation = FM Tab
	// fmTimer = FM Tab
	// mesoLabel = FM Tab
	// tradeTaxLabel = FM Tab
	// merchantTaxLabel = FM Tab
	// arrowCountLabel = FM Tab
	// infoLabel = FM Tab
	JLabel setupChannel = new JLabel("CH:");
	JLabel setupRoom = new JLabel("Room:");
	JLabel fmLocation = new JLabel("<html><font color=#0000FF>Location: N/A |</font>");
	JLabel fmTimer = new JLabel("<html><font color=#FF0000>00:00:00</font></html>");
	JLabel mesoLabel = new JLabel("Meso Amount:  ");
	JLabel tradeTaxLabel = new JLabel("Trade Tax:  0");
	JLabel merchantTaxLabel = new JLabel("Merchant Tax:  0");
	JLabel arrowCountLabel = new JLabel("Arrows:  0 + Mesos:  0");
	JLabel infoLabel = new JLabel("<html><font color=#FF0000>No Tax Arrows = 99,999  |  Meso Cap = 2,147,483,647</font></html>");

	// Increment/Decrement CH & FM Button in Setup Frame
	JSpinner channel = new JSpinner((SpinnerModel) new SpinnerNumberModel(1, 1, 20, 1));
	JSpinner fm = new JSpinner((SpinnerModel) new SpinnerNumberModel(1, 1, 22, 1));

	// Button for Starting Shop Count Down
	JButton fmStartTimerButton = new JButton("Start Timer");

	// Timer for 24Hr Shop Count Down
	Timer t = new Timer();

	// Button for Opening Setup Frame
	JButton setupButton = new JButton("Setup");

	// Formats Numbers with Commas e.g. 4000 -> 4,000
	NumberFormat format = NumberFormat.getInstance();
	NumberFormatter formatter = new NumberFormatter(format);
	JFormattedTextField amountField;

	// Constructor
	public FMTool() {
		setLayout(new FlowLayout());
		fmSetup.setLayout(new FlowLayout());

		// Initialize Setup Frame
		fmSetup.setSize(new Dimension(180, 100));
		fmSetup.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		fmSetup.setLocationRelativeTo(null);
		fmSetup.setResizable(false);
		fmSetup.add(setupChannel);
		fmSetup.add(channel);
		fmSetup.add(setupRoom);
		fmSetup.add(fm);
		fmSetup.add(fmStartTimerButton);
		setupButton.addActionListener(this);
		fmStartTimerButton.addActionListener(this);

		// Initialize FM Tab
		formatter.setAllowsInvalid(false);
		formatter.setCommitsOnValidEdit(true);
		amountField = new JFormattedTextField(formatter);
		amountField.setValue(0);
		amountField.setPreferredSize(new Dimension(140, 27));
		amountField.setHorizontalAlignment(SwingConstants.RIGHT);
		amountField.addKeyListener(this);
		add(fmLocation);
		add(fmTimer);
		add(setupButton);
		add(Box.createRigidArea(new Dimension(400, 0)));
		add(mesoLabel);
		add(amountField);
		add(Box.createRigidArea(new Dimension(400, 0)));
		add(tradeTaxLabel);
		add(Box.createRigidArea(new Dimension(400, 0)));
		add(merchantTaxLabel);
		add(Box.createRigidArea(new Dimension(400, 0)));
		add(arrowCountLabel);
		add(Box.createRigidArea(new Dimension(400, 0)));
		add(infoLabel);

		// Loads Timer from Saved File and Continues Count Down
		startFMTimer();
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Limits Number of Characters in Meso Amount Input Box
		if (amountField.getText().length() >= 20)
			amountField.setText(amountField.getText().substring(0, 20));
		if (e.getKeyChar() == '\n') {
			// Updates Tax Values on 'Enter' Press
			double amount = Double.parseDouble(amountField.getText().replaceAll(",", ""));
			double tradeTax = getTradeTax(amount);
			double merchantTax = getMerchantTax(amount);
			tradeTaxLabel.setText(String.format("Trade Tax:  %,.0f", tradeTax));
			merchantTaxLabel.setText(String.format("Merchant Tax:  %,.0f", merchantTax));
			arrowCountLabel.setText(String.format("Arrows:  %,.0f + Mesos:  %,.0f", (amount / 99999), (amount % 99999)));
		}
	}

	// Gets Trade Tax Amount
	public double getTradeTax(double amount) {
		if (amount > 99999)
			return amount * .008;
		else if (amount > 999999)
			return amount * .018;
		else if (amount > 4999999)
			return amount * .03;
		else if (amount > 9999999)
			return amount * .04;
		else if (amount > 24999999)
			return amount * .05;
		else if (amount > 99999999)
			return amount * .06;
		else
			return 0;
	}

	// Gets Merchant Tax Amount
	public double getMerchantTax(double amount) {
		return getTradeTax(amount) / 2;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Opens Setup Frame when 'Setup' is Pressed in FM Tab
		if (e.getSource() == setupButton) {
			fmSetup.setLocation(GUI.jf.getX() + GUI.jf.getWidth(), GUI.jf.getY());
			fmSetup.setVisible(true);
			// Starts the Timer when 'Start Timer' is Pressed in Setup Frame
		} else if (e.getSource() == fmStartTimerButton) {
			GUI.fmStartTime = System.currentTimeMillis();
			startFMTimer();
		}
	}

	// Updates Merchant Location Information and Starts Timer
	public void startFMTimer() {
		fmLocation.setText("<html><font color=#0000FF>Location:  CH" + channel.getValue() + " FM" + fm.getValue() + " </font>|");
		t.cancel();
		t = new Timer();
		t.scheduleAtFixedRate(new UpdateTimer(fmTimer), 0, 1000);
		fmSetup.setVisible(false);
		try {
			GUI.saveData();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	// Performs UI Update for Merchant Count Down Every Second
	class UpdateTimer extends TimerTask {
		JLabel msg;

		public UpdateTimer(JLabel label) {
			msg = label;
		}

		public void run() {
			long currentTime = System.currentTimeMillis();
			long difference = TimeUnit.DAYS.toMillis(1) - (currentTime - GUI.fmStartTime);
			int tSeconds = (int) (difference / 1000);
			int tMinutes = tSeconds / 60;
			int tHours = tMinutes / 60;
			if (difference > 0)
				fmTimer.setText("" + String.format("<html><font color=#0000FF>%02d:%02d:%02d", tHours % 24, tMinutes % 60, tSeconds % 60) + "</font>");
			else {
				// If Time Reaches Zero Stop Count Down
				t.cancel();
				fmLocation.setText("<html><font color=#0000FF>Location: N/A |</font>");
				fmTimer.setText("<html><font color=#FF0000>00:00:00</font></html>");
			}
		}
	}
}
