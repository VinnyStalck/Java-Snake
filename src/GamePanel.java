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

	private static final int backgroundXPos = 25;
	private static final int backgroundYPos = 75;
	private static final int backgroundWidth = 850;
	private static final int backgroundHeight = 575;

	private static final int tileSize = 25;

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
		g.drawRect(backgroundXPos-1, backgroundYPos-1, backgroundWidth+1, backgroundHeight+2);

		// Displays game panel background
		g.setColor(Color.DARK_GRAY);
		g.fillRect(backgroundXPos, backgroundYPos, backgroundWidth, backgroundHeight);

		
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
		titleImage.paintIcon(this, g, tileSize, 5);
	}

	private String assets(String assetName) {
		return "src/assets/"+assetName+".png";
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.restart();
		if (snake.isFacingRight()) {
			// Next position of the snake becomes the previous one
			for (int i = snake.getLength()-1; i >= 0; i--) {
				snake.setYLength(i+1, snake.getYLength()[i]);
			}
			for (int i = snake.getLength(); i >= 0; i--) {
				if (i==0) {
					snake.setXLength(i, snake.getXLength()[i]+backgroundXPos);
				} else {
					snake.setXLength(i, snake.getXLength()[i-1]);
				}

				// Moves snake to the other side of the screen
				if (snake.getXLength()[i] > backgroundWidth) {
					snake.setXLength(i, backgroundXPos);
				}
			}
			repaint();
		}
		if (snake.isFacingLeft()) {
			// Next position of the snake becomes the previous one
			for (int i = snake.getLength()-1; i >= 0; i--) {
				snake.setYLength(i+1, snake.getYLength()[i]);
			}
			for (int i = snake.getLength(); i >= 0; i--) {
				if (i==0) {
					snake.setXLength(i, snake.getXLength()[i]-backgroundXPos);
				} else {
					snake.setXLength(i, snake.getXLength()[i-1]);
				}

				// Moves snake to the other side of the screen
				if (snake.getXLength()[i] < backgroundXPos) {
					snake.setXLength(i, backgroundWidth);
				}
			}
			repaint();
		}
		if (snake.isFacingUp()) {
			// Next position of the snake becomes the previous one
			for (int i = snake.getLength()-1; i >= 0; i--) {
				snake.setXLength(i+1, snake.getXLength()[i]);
			}
			for (int i = snake.getLength(); i >= 0; i--) {
				if (i==0) {
					snake.setYLength(i, snake.getYLength()[i]-backgroundXPos);
				} else {
					snake.setYLength(i, snake.getYLength()[i-1]);
				}

				// Moves snake to the other side of the screen
				if (snake.getYLength()[i] < backgroundYPos) {
					snake.setYLength(i, backgroundYPos+backgroundHeight-tileSize);
				}
			}
			repaint();
		}
		if (snake.isFacingDown()) {
			// Next position of the snake becomes the previous one
			for (int i = snake.getLength()-1; i >= 0; i--) {
				snake.setXLength(i+1, snake.getXLength()[i]);
			}
			for (int i = snake.getLength(); i >= 0; i--) {
				if (i==0) {
					snake.setYLength(i, snake.getYLength()[i]+backgroundXPos);
				} else {
					snake.setYLength(i, snake.getYLength()[i-1]);
				}

				// Moves snake to the other side of the screen
				if (snake.getYLength()[i] > backgroundYPos+backgroundHeight-tileSize) {
					snake.setYLength(i, backgroundYPos);
				}
			}
			repaint();
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			moves++;

			if (!snake.isFacingLeft()) {
				snake.setFacingRight();
			} else {
				snake.setFacingLeft();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			moves++;

			if (!snake.isFacingRight()) {
				snake.setFacingLeft();
			} else {
				snake.setFacingRight();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			moves++;

			if (!snake.isFacingDown()) {
				snake.setFacingUp();
			} else {
				snake.setFacingDown();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			moves++;

			if (!snake.isFacingUp()) {
				snake.setFacingDown();
			} else {
				snake.setFacingUp();
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
