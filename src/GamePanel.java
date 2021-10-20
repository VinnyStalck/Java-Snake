import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;

public class GamePanel extends JPanel {
	private ImageIcon titleImage;

	public GamePanel() {

	}

	public void paint(Graphics g) {
		displayTitle(g);

		// Display game panel border
		g.setColor(Color.WHITE);
		g.drawRect(24, 74, 851, 577);

		// Displays game panel background
		g.setColor(Color.DARK_GRAY);
		g.fillRect(25, 75, 850, 575);

		g.dispose();
	}

	/**
	 * Displays title
	 * @param g
	 */
	private void displayTitle(Graphics g) {
		titleImage = new ImageIcon("src/assets/snake_head.png");
		titleImage.paintIcon(this, g, 25, 5);
	}
}
