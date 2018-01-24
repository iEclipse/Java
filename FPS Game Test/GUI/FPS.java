package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.Timer;

public class FPS extends javax.swing.JPanel implements ActionListener, MouseListener {

    Timer t = new Timer(15, this); //Repaint
    Timer t2 = new Timer(100, this); //Accelration of Gravity
    Timer t3 = new Timer(800, this); //Hitmarker Reset
    int x, y, mX, mY, vX, vY, hitX, hitY, clicked, total;
    double accuracy;
    boolean hit, showHitmarker;
    Toolkit tk = Toolkit.getDefaultToolkit();
    Image cursorImage;
    Cursor cursor;
    JLabel hits = new JLabel("Hits: 0/0");
    JLabel hitAccuracy = new JLabel("Accuracy: 0%");

    public FPS(Dimension size) {
        x = 0;
        y = 0;
        vX = 5;
        vY = 5;
        clicked = 0;
        total = 0;

        setSize(size);
        setLayout(null);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        addMouseListener(this);
        t.start();
        t2.start();

        cursorImage = tk.getImage(getClass().getResource("Resources/Default.png"));
        cursor = tk.createCustomCursor(cursorImage, new Point(15, 16), "D");
        setCursor(cursor);

        hits.setBounds(5, 5, 100, 15);
        hitAccuracy.setBounds(5, 20, 100, 15);
        hits.setForeground(Color.WHITE);
        hitAccuracy.setForeground(Color.WHITE);
        add(hits);
        add(hitAccuracy);
    }

    private void drawHitMarker(Graphics g) {
        if (showHitmarker) {
            if (hit)
                g.setColor(Color.RED);
            else
                g.setColor(Color.GREEN);
            g.fillOval(hitX - 5, hitY - 5, 10, 10);
        }
        t3.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillOval(x, y, 50, 50);
        drawHitMarker(g);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        repaint();
        if (ae.getSource() == t) {
            if (x < 0 || x > getWidth() - 60)
                vX = -vX;
            if (y < 0 || y > getHeight() - 80)
                vY = -vY;
            x += vX;
            y += vY;
        } else if (ae.getSource() == t2 && y < getHeight() / 2) {
            if (vY < 5)
                vY += 1;
        } else if (ae.getSource() == t3) {
            showHitmarker = false;
            cursorImage = tk.getImage(getClass().getResource("Resources/Default.png"));
            cursor = tk.createCustomCursor(cursorImage, new Point(15, 16), "TS");
            setCursor(cursor);
            t3.stop();
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        showHitmarker = true;
        hitX = me.getX();
        hitY = me.getY();
        if (me.getX() > x - 20 && me.getX() < x + 70 && me.getY() > y - 20 && me.getY() < y + 70) {
            cursorImage = tk.getImage(getClass().getResource("Resources/TargetSighted.png"));
            cursor = tk.createCustomCursor(cursorImage, new Point(15, 16), "TS");
            setCursor(cursor);
            hit = true;
            vY -= 8;
            clicked++;
        } else
            hit = false;
        total++;
        hits.setText("Hits: " + clicked + " / " + total);
        accuracy = (double) clicked / total;
        hitAccuracy.setText("Accuracy: " + (int) (accuracy * 100) + "%");
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
}
