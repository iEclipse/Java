import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.NumberFormatter;

public class LeechTool extends JPanel implements ActionListener, ChangeListener {
	// Timer for Leech
	Timer t;
	HashMap<Integer, Integer> xpTable = new HashMap<>();

	// Labels
	// =========
	// timeRemainingLabel = Displays Time Passed
	// profitLabel = Displays Current Price of Leech
	// mphLabel = Informs User that Cost is Millions per Hour
	// startLabel = Informs User what the Input Box is for
	// endLabel = Informs User what the Input Box is for
	// avgXPLabel = Displays Average XP per Hour
	JLabel timeRemainingLabel = new JLabel("Elapsed Time:  00:00:00");
	JLabel profitLabel = new JLabel("Leech Price:  0 mesos");
	JLabel mphLabel = new JLabel("m / hr");
	JLabel startLabel = new JLabel("Start LV/XP");
	JLabel endLabel = new JLabel("End LV/XP");
	JLabel avgXPLabel = new JLabel("Average xp / hr:  0");

	// Start Button
	JButton startButton = new JButton("Start");
	// Pause Button
	JButton pauseButton = new JButton("Pause");
	// Stop Button
	JButton stopButton = new JButton("Stop");
	// Calculate Average XP Button
	JButton calcAvgXPButton = new JButton("Calculate Average XP");

	// Limits Input Box to Numbers Only
	NumberFormat format = NumberFormat.getInstance();
	// Format for Leech Price Field, StartXP, and EndXP
	NumberFormatter formatter = new NumberFormatter(format);
	NumberFormatter formatter2 = new NumberFormatter(format);
	NumberFormatter formatter3 = new NumberFormatter(format);

	// Increment/Decrement CH & FM Button in Setup Frame
	JSpinner startLevel = new JSpinner((SpinnerModel) new SpinnerNumberModel(1, 1, 200, 1));
	JSpinner endLevel = new JSpinner((SpinnerModel) new SpinnerNumberModel(200, 1, 200, 1));

	// Input Boxes
	JFormattedTextField leechPriceField;
	JFormattedTextField startXPField;
	JFormattedTextField endXPField;

	// Elapsed Time in Seconds
	int elapsedTime = 0;
	// Leech End Time in Seconds
	int endTime = 0;
	// Current Level for Avg XP Calculation
	int xpIndex;
	// Total XP Gained
	long xpGained;

	public LeechTool() {
		// Initializes Leech Tab
		setLayout(new FlowLayout());

		// Price Input Box
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(0);
		formatter.setMaximum(Integer.MAX_VALUE);
		formatter.setAllowsInvalid(false);
		formatter.setCommitsOnValidEdit(true);
		leechPriceField = new JFormattedTextField(formatter);
		leechPriceField.setValue(50);
		leechPriceField.setPreferredSize(new Dimension(90, 27));
		leechPriceField.setHorizontalAlignment(SwingConstants.RIGHT);

		// Restrict XP Values of XP Boxes to Valid Values
		setXPTable();
		formatter2.setValueClass(Integer.class);
		formatter2.setMinimum(0);
		formatter2.setMaximum(xpTable.get(1));
		formatter2.setAllowsInvalid(false);
		formatter2.setCommitsOnValidEdit(true);
		formatter3.setValueClass(Integer.class);
		formatter3.setMinimum(0);
		formatter3.setMaximum(xpTable.get(200));
		formatter3.setAllowsInvalid(false);
		formatter3.setCommitsOnValidEdit(true);

		// XP Input Boxes
		startLevel.setPreferredSize(new Dimension(40, 27));
		endLevel.setPreferredSize(new Dimension(40, 27));
		startXPField = new JFormattedTextField(formatter2);
		startXPField.setValue(0);
		startXPField.setPreferredSize(new Dimension(86, 27));
		endXPField = new JFormattedTextField(formatter3);
		endXPField.setValue(0);
		endXPField.setPreferredSize(new Dimension(86, 27));

		// Adds Elements to Leech Tab
		add(timeRemainingLabel);
		add(Box.createRigidArea(new Dimension(400, 0)));
		add(profitLabel);
		add(Box.createRigidArea(new Dimension(400, 0)));
		add(startButton);
		add(pauseButton);
		add(stopButton);
		add(leechPriceField);
		add(mphLabel);
		add(Box.createRigidArea(new Dimension(400, 0)));
		add(avgXPLabel);
		add(Box.createRigidArea(new Dimension(400, 0)));
		add(startLabel);
		add(startLevel);
		add(startXPField);
		add(endLabel);
		add(endLevel);
		add(endXPField);
		add(calcAvgXPButton);

		startButton.addActionListener(this);
		pauseButton.addActionListener(this);
		stopButton.addActionListener(this);
		calcAvgXPButton.addActionListener(this);
		startLevel.addChangeListener(this);
		endLevel.addChangeListener(this);

		// Initially Locks Some UI
		pauseButton.setEnabled(false);
		stopButton.setEnabled(false);
		endLevel.setEnabled(false);
		endXPField.setEnabled(false);
		calcAvgXPButton.setEnabled(false);
	}

	// Updates GUI
	class UpdateTimer extends TimerTask {
		JLabel time;
		JLabel price;

		public UpdateTimer(JLabel label1, JLabel label2) {
			time = label1;
			price = label2;
		}

		// Updates Timer and UI
		public void run() {
			++elapsedTime;
			int hours = (elapsedTime % 86400) / 3600;
			int mins = (elapsedTime % 3600) / 60;
			int secs = elapsedTime % 60;
			double costPerSec = Double.parseDouble(leechPriceField.getText().replaceAll(",", "")) * 1000000 / 3600;
			time.setText("Elapsed Time:  " + String.format("%02d:%02d:%02d", hours, mins, secs));
			price.setText(String.format("Leech Price:  %,.0f mesos", elapsedTime * costPerSec));
		}
	}

	@Override
	// Disables Unnecessary Buttons and Performs Timer Actions
	public void actionPerformed(ActionEvent ae) {

		// Start Button
		if (ae.getSource() == startButton) {
			t = new Timer();
			t.scheduleAtFixedRate(new UpdateTimer(timeRemainingLabel, profitLabel), 1000, 1000);
			startButton.setEnabled(false);
			pauseButton.setEnabled(true);
			stopButton.setEnabled(true);
			leechPriceField.setEnabled(false);
			endLevel.setEnabled(false);
			endXPField.setEnabled(false);
			calcAvgXPButton.setEnabled(false);

			// Pause Button
		} else if (ae.getSource() == pauseButton) {
			t.cancel();
			startButton.setEnabled(true);
			pauseButton.setEnabled(false);
			leechPriceField.setEnabled(true);

			// Stop Button
		} else if (ae.getSource() == stopButton) {
			t.cancel();
			endTime = elapsedTime;
			elapsedTime = 0;
			startButton.setEnabled(true);
			pauseButton.setEnabled(false);
			stopButton.setEnabled(false);
			leechPriceField.setEnabled(true);

			if (endTime > 0) {
				endLevel.setEnabled(true);
				endXPField.setEnabled(true);
				calcAvgXPButton.setEnabled(true);
			}

			// Calculate Average XP Button
		} else if (ae.getSource() == calcAvgXPButton) {
			xpIndex = (int) startLevel.getValue();
			xpGained = 0;
			while (xpIndex < (int) endLevel.getValue())
				xpGained += xpTable.get(xpIndex++);
			xpGained += (int) endXPField.getValue() - (int) startXPField.getValue();
			if (startLevel.getValue() == endLevel.getValue() && (int) startXPField.getValue() > (int) endXPField.getValue())
				avgXPLabel.setText("Error: Starting XP cannot be less than Ending XP.");
			else
				avgXPLabel.setText("Average xp / hr:  " + String.format("%,.0f", (xpGained * (3600.0 / endTime))));
		}
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		// Updates Restrictions on XP Input Boxes
		if (arg0.getSource() == startLevel) {
			startXPField.setValue(0);
			formatter2.setMaximum(xpTable.get(startLevel.getValue()));
			if ((int) startLevel.getValue() > (int) endLevel.getValue()) {
				endLevel.setValue((int) startLevel.getValue());
				formatter3.setMaximum(xpTable.get(endLevel.getValue()));
				endXPField.setValue(0);
			}
		} else if (arg0.getSource() == endLevel) {
			endXPField.setValue(0);
			formatter3.setMaximum(xpTable.get(endLevel.getValue()));
			if ((int) endLevel.getValue() < (int) startLevel.getValue()) {
				startLevel.setValue((int) endLevel.getValue());
				formatter2.setMaximum(xpTable.get(startLevel.getValue()));
				startXPField.setValue(0);
			}
		}
	}

	// XP Til Next Level (200-201 Doesn't Exist, but Data is Added)
	public void setXPTable() {
		xpTable.put(1, 15);
		xpTable.put(2, 34);
		xpTable.put(3, 57);
		xpTable.put(4, 92);
		xpTable.put(5, 135);
		xpTable.put(6, 372);
		xpTable.put(7, 560);
		xpTable.put(8, 840);
		xpTable.put(9, 1242);
		xpTable.put(10, 1144);

		xpTable.put(11, 1573);
		xpTable.put(12, 2144);
		xpTable.put(13, 2800);
		xpTable.put(14, 3640);
		xpTable.put(15, 4700);
		xpTable.put(16, 5893);
		xpTable.put(17, 7360);
		xpTable.put(18, 9144);
		xpTable.put(19, 11120);
		xpTable.put(20, 13477);

		xpTable.put(21, 16268);
		xpTable.put(22, 19320);
		xpTable.put(23, 22880);
		xpTable.put(24, 27008);
		xpTable.put(25, 31477);
		xpTable.put(26, 36600);
		xpTable.put(27, 42444);
		xpTable.put(28, 48720);
		xpTable.put(29, 55813);
		xpTable.put(30, 63800);

		xpTable.put(31, 86784);
		xpTable.put(32, 98208);
		xpTable.put(33, 110932);
		xpTable.put(34, 124432);
		xpTable.put(35, 139372);
		xpTable.put(36, 155865);
		xpTable.put(37, 173280);
		xpTable.put(38, 192400);
		xpTable.put(39, 213345);
		xpTable.put(40, 235372);

		xpTable.put(41, 259392);
		xpTable.put(42, 285532);
		xpTable.put(43, 312928);
		xpTable.put(44, 342624);
		xpTable.put(45, 374760);
		xpTable.put(46, 408336);
		xpTable.put(47, 445544);
		xpTable.put(48, 483532);
		xpTable.put(49, 524160);
		xpTable.put(50, 567772);

		xpTable.put(51, 598886);
		xpTable.put(52, 631704);
		xpTable.put(53, 666321);
		xpTable.put(54, 702836);
		xpTable.put(55, 741351);
		xpTable.put(56, 781976);
		xpTable.put(57, 824828);
		xpTable.put(58, 870028);
		xpTable.put(59, 917625);
		xpTable.put(60, 967995);

		xpTable.put(61, 1021041);
		xpTable.put(62, 1076994);
		xpTable.put(63, 1136013);
		xpTable.put(64, 1198266);
		xpTable.put(65, 1263930);
		xpTable.put(66, 1333194);
		xpTable.put(67, 1406252);
		xpTable.put(68, 1483314);
		xpTable.put(69, 1564600);
		xpTable.put(70, 1650340);

		xpTable.put(71, 1740778);
		xpTable.put(72, 1836173);
		xpTable.put(73, 1936794);
		xpTable.put(74, 2042930);
		xpTable.put(75, 2154882);
		xpTable.put(76, 2272970);
		xpTable.put(77, 2397528);
		xpTable.put(78, 2528912);
		xpTable.put(79, 2667496);
		xpTable.put(80, 2813674);

		xpTable.put(81, 2967863);
		xpTable.put(82, 3130502);
		xpTable.put(83, 3302053);
		xpTable.put(84, 3483005);
		xpTable.put(85, 3673873);
		xpTable.put(86, 3875201);
		xpTable.put(87, 4087562);
		xpTable.put(88, 4311559);
		xpTable.put(89, 4547832);
		xpTable.put(90, 4797053);

		xpTable.put(91, 5059931);
		xpTable.put(92, 5337215);
		xpTable.put(93, 5629694);
		xpTable.put(94, 5938202);
		xpTable.put(95, 6263614);
		xpTable.put(96, 6606860);
		xpTable.put(97, 6968915);
		xpTable.put(98, 7350811);
		xpTable.put(99, 7753635);
		xpTable.put(100, 8178534);

		xpTable.put(101, 8626718);
		xpTable.put(102, 9099462);
		xpTable.put(103, 9598112);
		xpTable.put(104, 10124088);
		xpTable.put(105, 10678888);
		xpTable.put(106, 11264090);
		xpTable.put(107, 11881362);
		xpTable.put(108, 12532461);
		xpTable.put(109, 13219239);
		xpTable.put(110, 13943653);

		xpTable.put(111, 14707765);
		xpTable.put(112, 15513750);
		xpTable.put(113, 16363902);
		xpTable.put(114, 17260644);
		xpTable.put(115, 18206527);
		xpTable.put(116, 19204245);
		xpTable.put(117, 20256637);
		xpTable.put(118, 21366700);
		xpTable.put(119, 22537594);
		xpTable.put(120, 23772654);

		xpTable.put(121, 25075395);
		xpTable.put(122, 26449526);
		xpTable.put(123, 27898960);
		xpTable.put(124, 29427822);
		xpTable.put(125, 31040466);
		xpTable.put(126, 32741483);
		xpTable.put(127, 34535716);
		xpTable.put(128, 36428273);
		xpTable.put(129, 38424542);
		xpTable.put(130, 40530206);

		xpTable.put(131, 42751262);
		xpTable.put(132, 45094030);
		xpTable.put(133, 47565183);
		xpTable.put(134, 50171755);
		xpTable.put(135, 52921167);
		xpTable.put(136, 55821246);
		xpTable.put(137, 58880250);
		xpTable.put(138, 62106888);
		xpTable.put(139, 65510344);
		xpTable.put(140, 69100311);

		xpTable.put(141, 72887008);
		xpTable.put(142, 76881216);
		xpTable.put(143, 81094306);
		xpTable.put(144, 85594273);
		xpTable.put(145, 90225770);
		xpTable.put(146, 95170142);
		xpTable.put(147, 100385466);
		xpTable.put(148, 105886589);
		xpTable.put(149, 111689174);
		xpTable.put(150, 117809740);

		xpTable.put(151, 124265714);
		xpTable.put(152, 131075474);
		xpTable.put(153, 138258410);
		xpTable.put(154, 145834970);
		xpTable.put(155, 153826726);
		xpTable.put(156, 162256430);
		xpTable.put(157, 171148082);
		xpTable.put(158, 180526997);
		xpTable.put(159, 190419876);
		xpTable.put(160, 200854885);

		xpTable.put(161, 211861732);
		xpTable.put(162, 223471711);
		xpTable.put(163, 235718806);
		xpTable.put(164, 248635353);
		xpTable.put(165, 262260570);
		xpTable.put(166, 276632449);
		xpTable.put(167, 291791906);
		xpTable.put(168, 307782102);
		xpTable.put(169, 324648562);
		xpTable.put(170, 342439302);

		xpTable.put(171, 361204976);
		xpTable.put(172, 380999008);
		xpTable.put(173, 401877754);
		xpTable.put(174, 423900654);
		xpTable.put(175, 447130410);
		xpTable.put(176, 471633156);
		xpTable.put(177, 497478653);
		xpTable.put(178, 524740482);
		xpTable.put(179, 553496261);
		xpTable.put(180, 583827855);

		xpTable.put(181, 615821622);
		xpTable.put(182, 649568646);
		xpTable.put(183, 685165008);
		xpTable.put(184, 722712050);
		xpTable.put(185, 762316670);
		xpTable.put(186, 804091623);
		xpTable.put(187, 848155844);
		xpTable.put(188, 894634784);
		xpTable.put(189, 943660770);
		xpTable.put(190, 995373379);

		xpTable.put(191, 1049919840);
		xpTable.put(192, 1107455447);
		xpTable.put(193, 1168144006);
		xpTable.put(194, 1232158297);
		xpTable.put(195, 1299680571);
		xpTable.put(196, 1370903066);
		xpTable.put(197, 1446028554);
		xpTable.put(198, 1525246918);
		xpTable.put(199, 1608855764);
		xpTable.put(200, 1697021059);
	}
}
