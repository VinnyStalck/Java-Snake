package objects;

import java.util.Random;

public class Grid {
	private Tile[][] grid;

	/**
	 * Creates a grid.
	 * @param spaceSize The size of each space in the grid
	 * @param x X position
	 * @param y Y position
	 * @param width 
	 * @param height 
	 */
	public Grid(int spaceSize, int x, int y, int width, int height) {
		int xSpaces = width/spaceSize;
		int ySpaces = height/spaceSize;

		grid = new Tile[xSpaces][ySpaces];

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = new Tile((spaceSize*i)+x, (spaceSize*j)+y);
			}
		}
	}

	public Tile[][] getGrid() {
		return grid;
	}

	public Tile getRandomTile() {
		Random random = new Random();

		int xPos = random.nextInt(grid.length);
		int yPos = random.nextInt(grid[xPos].length);

		return grid[xPos][yPos];
	}
	
}
