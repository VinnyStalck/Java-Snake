import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import objects.Snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
	private ImageIcon titleImage;

	private Timer timer;
	private int delay = 100;

	private int moves = 0;
	private int score = 0;

	Snake snake = new Snake();

	public GamePanel() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);

		timer = new Timer(delay, this);
		timer.start();
	}

	public void paint(Graphics g) {
		if (moves == 0) {
			snake.setXLength(0, 100);
			snake.setXLength(1, 75);
			snake.setXLength(2, 50);
			snake.setYLength(0, 100);
			snake.setYLength(1, 100);
			snake.setYLength(2, 100);
		}
		
		displayTitle(g);

		// Display game panel border
		g.setColor(Color.WHITE);
		g.drawRect(24, 74, 851, 577);

		// Displays game panel background
		g.setColor(Color.DARK_GRAY);
		g.fillRect(25, 75, 850, 575);

		
		snake.setHeadFacingRight(new ImageIcon(assets("snake_head_right")));
		snake.getHeadFacingRight().paintIcon(this, g, snake.getXLength()[0], snake.getYLength()[0]);
		
		for (int i = 0; i < snake.getLength(); i++) {
			if (i==0 && snake.isFacingRight()) {
				snake.setHeadFacingRight(new ImageIcon(assets("snake_head_right")));
				snake.getHeadFacingRight().paintIcon(this, g, snake.getXLength()[i], snake.getYLength()[i]);
			}
			if (i==0 && snake.isFacingLeft()) {
				snake.setHeadFacingLeft(new ImageIcon(assets("snake_head_left")));
				snake.getHeadFacingLeft().paintIcon(this, g, snake.getXLength()[i], snake.getYLength()[i]);
			}
			if (i==0 && snake.isFacingUp()) {
				snake.setHeadFacingUp(new ImageIcon(assets("snake_head_up")));
				snake.getHeadFacingUp().paintIcon(this, g, snake.getXLength()[i], snake.getYLength()[i]);
			}
			if (i==0 && snake.isFacingDown()) {
				snake.setHeadFacingDown(new ImageIcon(assets("snake_head_down")));
				snake.getHeadFacingDown().paintIcon(this, g, snake.getXLength()[i], snake.getYLength()[i]);
			}

			if (i!=0) {
				snake.setTail(new ImageIcon(assets("snake_tail")));
				snake.getTail().paintIcon(this, g, snake.getXLength()[i], snake.getYLength()[i]);
			}
		}
		
		g.dispose();
	}

	/**
	 * Displays title
	 * @param g
	 */
	private void displayTitle(Graphics g) {
		titleImage = new ImageIcon(assets("snake_head"));
		titleImage.paintIcon(this, g, 25, 5);
	}

	private String assets(String assetName) {
		return "src/assets/"+assetName+".png";
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
