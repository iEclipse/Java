package GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Frame extends javax.swing.JFrame implements ActionListener {

    Timer t = new Timer(15, this);
    public FPS fps;

    public Frame() {
        setTitle("Title");
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 600));
        setLocationRelativeTo(null);
        setVisible(true);
        t.start();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            resizeGUI();
            repaint();
        } catch (NullPointerException ex) {
        }
    }

    public void startFPS() {
        add(new FPS(getSize()));
    }

    public void resizeGUI() {
        pack();
        setResizable(false);
    }
}
