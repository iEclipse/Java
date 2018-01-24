
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.Timer;

public class Frame extends javax.swing.JFrame implements ActionListener {

    Timer t = new Timer(15, this);
    Character c = new Character();

    public Frame() {
        setUndecorated(true);
        setBackground(new Color(240, 240, 240, Color.TRANSLUCENT));
        setTitle("Title");
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 600));
        setLocationRelativeTo(null);
        setVisible(true);
        t.start();
        add(c);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            repaint();
        } catch (NullPointerException ex) {
        }
    }
}
