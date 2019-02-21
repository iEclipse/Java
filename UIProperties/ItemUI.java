import java.awt.Graphics;

public class ItemUI extends UI {
	public ItemUI(Frame parent) {
		super("Item", 300, 500, parent);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 5; j++)
				g.fillRect(5*i, 5*j, 5,5);
	}
}
