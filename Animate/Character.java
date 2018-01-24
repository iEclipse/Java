
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Character extends JPanel implements ActionListener {

    ArrayList<ImageIcon> alert = new ArrayList<>();
    ArrayList<ImageIcon> fly = new ArrayList<>();
    ArrayList<ImageIcon> heal = new ArrayList<>();
    ArrayList<ImageIcon> jump = new ArrayList<>();
    ArrayList<ImageIcon> ladder = new ArrayList<>();
    ArrayList<ImageIcon> prone = new ArrayList<>();
    ArrayList<ImageIcon> proneStab = new ArrayList<>();
    ArrayList<ImageIcon> rope = new ArrayList<>();
    ArrayList<ImageIcon> shoot1 = new ArrayList<>();
    ArrayList<ImageIcon> shoot2 = new ArrayList<>();
    ArrayList<ImageIcon> shootF = new ArrayList<>();
    ArrayList<ImageIcon> sit = new ArrayList<>();
    ArrayList<ImageIcon> stabO1 = new ArrayList<>();
    ArrayList<ImageIcon> stabO2 = new ArrayList<>();
    ArrayList<ImageIcon> stabOF = new ArrayList<>();
    ArrayList<ImageIcon> stabT1 = new ArrayList<>();
    ArrayList<ImageIcon> stabT2 = new ArrayList<>();
    ArrayList<ImageIcon> stabTF = new ArrayList<>();
    ArrayList<ImageIcon> stand1 = new ArrayList<>();
    ArrayList<ImageIcon> stand2 = new ArrayList<>();
    ArrayList<ImageIcon> swingO1 = new ArrayList<>();
    ArrayList<ImageIcon> swingO2 = new ArrayList<>();
    ArrayList<ImageIcon> swingO3 = new ArrayList<>();
    ArrayList<ImageIcon> swingOF = new ArrayList<>();
    ArrayList<ImageIcon> swingP1 = new ArrayList<>();
    ArrayList<ImageIcon> swingP2 = new ArrayList<>();
    ArrayList<ImageIcon> swingPF = new ArrayList<>();
    ArrayList<ImageIcon> swingT1 = new ArrayList<>();
    ArrayList<ImageIcon> swingT2 = new ArrayList<>();
    ArrayList<ImageIcon> swingT3 = new ArrayList<>();
    ArrayList<ImageIcon> swingTF = new ArrayList<>();
    ArrayList<ImageIcon> walk1 = new ArrayList<>();
    ArrayList<ImageIcon> walk2 = new ArrayList<>();
    ArrayList<ImageIcon> current;

    JLabel sprite = new JLabel();
    Timer t;
    int frameIndex;
    int[] delay;
    boolean flipped = false;

    public Character() {        
        setLayout(null);
        setBackground(new Color(240,240,240, Color.TRANSLUCENT));
        setBounds(0, 0, 90, 90);

        for (int i = 0; i < 5; i++)
            alert.add(new ImageIcon(getClass().getResource("/Animation/alert_" + i + ".png")));
        for (int i = 0; i < 2; i++)
            fly.add(new ImageIcon(getClass().getResource("/Animation/fly_" + i + ".png")));
        for (int i = 0; i < 3; i++)
            heal.add(new ImageIcon(getClass().getResource("/Animation/heal_" + i + ".png")));
        for (int i = 0; i < 1; i++)
            jump.add(new ImageIcon(getClass().getResource("/Animation/jump_" + i + ".png")));
        for (int i = 0; i < 2; i++)
            ladder.add(new ImageIcon(getClass().getResource("/Animation/ladder_" + i + ".png")));
        for (int i = 0; i < 1; i++)
            prone.add(new ImageIcon(getClass().getResource("/Animation/prone_" + i + ".png")));
        for (int i = 0; i < 2; i++)
            proneStab.add(new ImageIcon(getClass().getResource("/Animation/proneStab_" + i + ".png")));
        for (int i = 0; i < 2; i++)
            rope.add(new ImageIcon(getClass().getResource("/Animation/rope_" + i + ".png")));
        for (int i = 0; i < 3; i++)
            shoot1.add(new ImageIcon(getClass().getResource("/Animation/shoot1_" + i + ".png")));
        for (int i = 0; i < 5; i++)
            shoot2.add(new ImageIcon(getClass().getResource("/Animation/shoot2_" + i + ".png")));
        for (int i = 0; i < 3; i++)
            shootF.add(new ImageIcon(getClass().getResource("/Animation/shootF_" + i + ".png")));
        for (int i = 0; i < 1; i++)
            sit.add(new ImageIcon(getClass().getResource("/Animation/sit_" + i + ".png")));
        for (int i = 0; i < 2; i++)
            stabO1.add(new ImageIcon(getClass().getResource("/Animation/stabO1_" + i + ".png")));
        for (int i = 0; i < 2; i++)
            stabO2.add(new ImageIcon(getClass().getResource("/Animation/stabO2_" + i + ".png")));
        for (int i = 0; i < 3; i++)
            stabOF.add(new ImageIcon(getClass().getResource("/Animation/stabOF_" + i + ".png")));
        for (int i = 0; i < 3; i++)
            stabT1.add(new ImageIcon(getClass().getResource("/Animation/stabT1_" + i + ".png")));
        for (int i = 0; i < 3; i++)
            stabT2.add(new ImageIcon(getClass().getResource("/Animation/stabT2_" + i + ".png")));
        for (int i = 0; i < 4; i++)
            stabTF.add(new ImageIcon(getClass().getResource("/Animation/stabTF_" + i + ".png")));
        for (int i = 0; i < 5; i++)
            stand1.add(new ImageIcon(getClass().getResource("/Animation/stand1_" + i + ".png")));
        for (int i = 0; i < 5; i++)
            stand2.add(new ImageIcon(getClass().getResource("/Animation/stand2_" + i + ".png")));
        for (int i = 0; i < 3; i++)
            swingO1.add(new ImageIcon(getClass().getResource("/Animation/swingO1_" + i + ".png")));
        for (int i = 0; i < 3; i++)
            swingO2.add(new ImageIcon(getClass().getResource("/Animation/swingO2_" + i + ".png")));
        for (int i = 0; i < 3; i++)
            swingO3.add(new ImageIcon(getClass().getResource("/Animation/swingO3_" + i + ".png")));
        for (int i = 0; i < 4; i++)
            swingOF.add(new ImageIcon(getClass().getResource("/Animation/swingOF_" + i + ".png")));
        for (int i = 0; i < 3; i++)
            swingP1.add(new ImageIcon(getClass().getResource("/Animation/swingP1_" + i + ".png")));
        for (int i = 0; i < 3; i++)
            swingP2.add(new ImageIcon(getClass().getResource("/Animation/swingP2_" + i + ".png")));
        for (int i = 0; i < 4; i++)
            swingPF.add(new ImageIcon(getClass().getResource("/Animation/swingPF_" + i + ".png")));
        for (int i = 0; i < 3; i++)
            swingT1.add(new ImageIcon(getClass().getResource("/Animation/swingT1_" + i + ".png")));
        for (int i = 0; i < 3; i++)
            swingT2.add(new ImageIcon(getClass().getResource("/Animation/swingT2_" + i + ".png")));
        for (int i = 0; i < 3; i++)
            swingT3.add(new ImageIcon(getClass().getResource("/Animation/swingT3_" + i + ".png")));
        for (int i = 0; i < 4; i++)
            swingTF.add(new ImageIcon(getClass().getResource("/Animation/swingTF_" + i + ".png")));
        for (int i = 0; i < 4; i++)
            walk1.add(new ImageIcon(getClass().getResource("/Animation/walk1_" + i + ".png")));
        for (int i = 0; i < 4; i++)
            walk2.add(new ImageIcon(getClass().getResource("/Animation/walk2_" + i + ".png")));

        sprite.setBounds(0, 0, 90, 90);
        add(sprite);
    }

    public void animate(String type) {
        switch (type) {
            case "alert":
                current = alert;
                delay = new int[]{500, 500, 500, 500, 500};
                break;
            case "fly":
                current = fly;
                delay = new int[]{300, 300};
                break;
            case "heal":
                current = heal;
                delay = new int[]{300, 150, 350};
                break;
            case "jump":
                current = jump;
                delay = new int[]{200};
                break;
            case "ladder":
                current = ladder;
                delay = new int[]{250, 250};
                break;
            case "prone":
                current = prone;
                delay = new int[]{100};
                break;
            case "proneStab":
                current = proneStab;
                delay = new int[]{300, 400};
                break;
            case "rope":
                current = rope;
                delay = new int[]{250, 250};
                break;
            case "shoot1":
                current = shoot1;
                delay = new int[]{300, 150, 350};
                break;
            case "shoot2":
                current = shoot2;
                delay = new int[]{160, 160, 250, 100, 150};
                break;
            case "shootF":
                current = shootF;
                delay = new int[]{300, 150, 250};
                break;
            case "sit":
                current = sit;
                delay = new int[]{100};
                break;
            case "stabO1":
                current = stabO1;
                delay = new int[]{350, 450};
                break;
            case "stabO2":
                current = stabO2;
                delay = new int[]{350, 450};
                break;
            case "stabOF":
                current = stabOF;
                delay = new int[]{250, 150, 300};
                break;
            case "stabT1":
                current = stabT1;
                delay = new int[]{300, 100, 350};
                break;
            case "stabT2":
                current = stabT2;
                delay = new int[]{300, 100, 350};
                break;
            case "stabTF":
                current = stabTF;
                delay = new int[]{100, 200, 200, 200};
                break;
            case "stand1":
                current = stand1;
                delay = new int[]{500, 500, 500, 500, 500};
                break;
            case "stand2":
                current = stand2;
                delay = new int[]{500, 500, 500, 500, 500};
                break;
            case "swingO1":
                current = swingO1;
                delay = new int[]{300, 150, 300};
                break;
            case "swingO2":
                current = swingO2;
                delay = new int[]{300, 150, 350};
                break;
            case "swingO3":
                current = swingO3;
                delay = new int[]{300, 150, 350};
                break;
            case "swingOF":
                current = swingOF;
                delay = new int[]{200, 100, 100};
                break;
            case "swingP1":
                current = swingP1;
                delay = new int[]{300, 150, 350};
                break;
            case "swingP2":
                current = swingP2;
                delay = new int[]{300, 150, 350};
                break;
            case "swingPF":
                current = swingPF;
                delay = new int[]{100, 200, 200};
                break;
            case "swingT1":
                current = swingT1;
                delay = new int[]{300, 150, 350};
                break;
            case "swingT2":
                current = swingT2;
                delay = new int[]{300, 150, 350};
                break;
            case "swingT3":
                current = swingT3;
                delay = new int[]{300, 150, 350};
                break;
            case "swingTF":
                current = swingTF;
                delay = new int[]{200, 150, 150, 200};
                break;
            case "walk1":
                current = walk1;
                delay = new int[]{180, 180, 180, 180};
            case "walk2":
                current = walk2;
                delay = new int[]{180, 180, 180, 180};
                break;
            default:
                System.out.println("Animation not found.");
        }

        try {
            t.stop();
        } catch (NullPointerException e) {
        }
        t = new Timer(delay[frameIndex], this);
        t.setInitialDelay(0);
        t.start();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (frameIndex < current.size())
            sprite.setIcon(current.get(frameIndex++));
        else {
            frameIndex = 0;
            sprite.setIcon(current.get(frameIndex++));
            flipped = !flipped;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if (flipped) {
            g2d.scale(-1, 1);
            g2d.translate(-getWidth(), 0);
        }
    }
}
