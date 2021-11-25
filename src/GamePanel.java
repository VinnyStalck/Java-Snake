import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import objects.Fruit;
import objects.Grid;
import objects.Snake;

public class GamePanel extends JPanel implements KeyListener, ActionListener {

	private static final int tileSize = 25;
	private static final int xTiles = 34;
	private static final int yTiles = 23;

	private static final int gameWidth = tileSize * xTiles;
	private static final int gameHeight = tileSize * yTiles;
	private static final int gameXPos = ((App.frameWidth - gameWidth) / 2) - 8;
	private static final int gameYPos = ((App.frameHeight - gameHeight) / 2);

	private Timer timer;
	private int delay = 100;

	private int moves = 0;
	private int score = 0;

	Grid grid = new Grid(tileSize, gameXPos, gameYPos, gameWidth, gameHeight);
	Snake snake = new Snake();
	Fruit fruit = new Fruit(grid);

	public GamePanel() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);

		timer = new Timer(delay, this);
		timer.start();
	}

	public void paint(Graphics g) {
		displayTitle(g);
		
		displayGame(g, 0);

		if (moves == 0) {
			snake.setXLength(0, gameXPos + tileSize * 4);
			snake.setXLength(1, gameXPos + tileSize * 3);
			snake.setXLength(2, gameXPos + tileSize * 2);
			snake.setYLength(0, gameYPos + tileSize * 4);
			snake.setYLength(1, gameYPos + tileSize * 4);
			snake.setYLength(2, gameYPos + tileSize * 4);

			snake.setHeadFacingRight(new ImageIcon(assets("snake_head_right")));
			snake.getHeadFacingRight().paintIcon(this, g, snake.getXLength()[0], snake.getYLength()[0]);
		}

		// Display score and length
		g.setColor(Color.BLACK);
		g.setFont(new Font("areal", Font.PLAIN, 14));
		g.drawString("Score: "+score, 780, 30);

		g.setColor(Color.BLACK);
		g.setFont(new Font("areal", Font.PLAIN, 14));
		g.drawString("Length: "+snake.getLength(), 780, 50);

		// Display head in initial position
		for (int i = 0; i < snake.getLength(); i++) {
			if (i == 0 && snake.isFacingRight()) {
				snake.setHeadFacingRight(new ImageIcon(assets("snake_head_right")));
				snake.getHeadFacingRight().paintIcon(this, g, snake.getXLength()[i], snake.getYLength()[i]);
			}
			if (i == 0 && snake.isFacingLeft()) {
				snake.setHeadFacingLeft(new ImageIcon(assets("snake_head_left")));
				snake.getHeadFacingLeft().paintIcon(this, g, snake.getXLength()[i], snake.getYLength()[i]);
			}
			if (i == 0 && snake.isFacingUp()) {
				snake.setHeadFacingUp(new ImageIcon(assets("snake_head_up")));
				snake.getHeadFacingUp().paintIcon(this, g, snake.getXLength()[i], snake.getYLength()[i]);
			}
			if (i == 0 && snake.isFacingDown()) {
				snake.setHeadFacingDown(new ImageIcon(assets("snake_head_down")));
				snake.getHeadFacingDown().paintIcon(this, g, snake.getXLength()[i], snake.getYLength()[i]);
			}

			if (i != 0) {
				snake.setTail(new ImageIcon(assets("snake_tail")));
				snake.getTail().paintIcon(this, g, snake.getXLength()[i], snake.getYLength()[i]);
			}

		}
		displayFruit(g);

		for (int i = 1; i < snake.getLength(); i++) {
			if (snake.getXLength()[i] == snake.getXLength()[0] && snake.getYLength()[i] == snake.getYLength()[0]) {
					snake.resetFacing();

					g.setColor(Color.RED);
					g.setFont(new Font("areal", Font.BOLD, 40));
					g.drawString("Game Over! Score: "+score, 250, 300);
					
					g.setColor(Color.WHITE);
					g.setFont(new Font("areal", Font.BOLD, 40));
					g.drawString("Press Enter to restart", 250, 340);
			}
		}

		g.dispose();
	}

	/**
	 * Displays game panel border and background
	 * @param g
	 * @param border Border thickness
	 */
	private void displayGame(Graphics g, int border) {
		if (border > 0) {
			// Display game panel border
			g.setColor(Color.WHITE);
			g.drawRect(gameXPos - border, gameYPos - border, gameWidth + border, gameHeight + border);
		}

		// Displays game panel background
		g.setColor(Color.DARK_GRAY);
		g.fillRect(gameXPos, gameYPos, gameWidth, gameHeight);
	}

	/**
	 * Displays fruit
	 * @param g
	 */
	private void displayFruit(Graphics g) {
		fruit.setIcon(new ImageIcon(assets("fruit")));
		// If the head of the snake is on the same tile of the fruit
		if (fruit.getTile().getXPos() == snake.getXLength()[0]
				&& fruit.getTile().getYPos() == snake.getYLength()[0]) {
			score++;
			snake.grow();
			fruit.place(grid);
		}
		fruit.getIcon().paintIcon(this, g, fruit.getTile().getXPos(), fruit.getTile().getYPos());
	}

	/**
	 * Displays title
	 * @param g
	 */
	private void displayTitle(Graphics g) {
		int titleYPos = 10, titleHeight = 50;
		g.setColor(Color.GREEN);
		g.fillRect(gameXPos, titleYPos, gameWidth, titleHeight);

		//titleImage = new ImageIcon(assets("snake_head"));
		//titleImage.paintIcon(this, g, gameXPos, 5);
	}

	private String assets(String assetName) {
		return "src/assets/" + assetName + ".png";
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.restart();
		if (snake.isFacingRight()) {
			// Next position of the snake becomes the previous one
			for (int i = snake.getLength() - 1; i >= 0; i--) {
				snake.setYLength(i + 1, snake.getYLength()[i]);
			}
			for (int i = snake.getLength(); i >= 0; i--) {
				if (i == 0) {
					snake.setXLength(i, snake.getXLength()[i] + tileSize);
				} else {
					snake.setXLength(i, snake.getXLength()[i - 1]);
				}

				// Moves snake to the other side of the screen
				if (snake.getXLength()[i] > gameWidth) {
					snake.setXLength(i, gameXPos);
				}
			}
			repaint();
		}
		if (snake.isFacingLeft()) {
			// Next position of the snake becomes the previous one
			for (int i = snake.getLength() - 1; i >= 0; i--) {
				snake.setYLength(i + 1, snake.getYLength()[i]);
			}
			for (int i = snake.getLength(); i >= 0; i--) {
				if (i == 0) {
					snake.setXLength(i, snake.getXLength()[i] - tileSize);
				} else {
					snake.setXLength(i, snake.getXLength()[i - 1]);
				}

				// Moves snake to the other side of the screen
				if (snake.getXLength()[i] < gameXPos) {
					snake.setXLength(i, gameXPos + gameWidth - tileSize);
				}
			}
			repaint();
		}
		if (snake.isFacingUp()) {
			// Next position of the snake becomes the previous one
			for (int i = snake.getLength() - 1; i >= 0; i--) {
				snake.setXLength(i + 1, snake.getXLength()[i]);
			}
			for (int i = snake.getLength(); i >= 0; i--) {
				if (i == 0) {
					snake.setYLength(i, snake.getYLength()[i] - tileSize);
				} else {
					snake.setYLength(i, snake.getYLength()[i - 1]);
				}

				// Moves snake to the other side of the screen
				if (snake.getYLength()[i] < gameYPos) {
					snake.setYLength(i, gameYPos + gameHeight - tileSize);
				}
			}
			repaint();
		}
		if (snake.isFacingDown()) {
			// Next position of the snake becomes the previous one
			for (int i = snake.getLength() - 1; i >= 0; i--) {
				snake.setXLength(i + 1, snake.getXLength()[i]);
			}
			for (int i = snake.getLength(); i >= 0; i--) {
				if (i == 0) {
					snake.setYLength(i, snake.getYLength()[i] + tileSize);
				} else {
					snake.setYLength(i, snake.getYLength()[i - 1]);
				}

				// Moves snake to the other side of the screen
				if (snake.getYLength()[i] > gameYPos + gameHeight - tileSize) {
					snake.setYLength(i, gameYPos);
				}
			}
			repaint();
		}

	}

	@Override
	public void keyTyped(KeyEvent e) { }

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			moves = 0;
			score = 0;
			snake.setLength(3);
			repaint();
		}

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
