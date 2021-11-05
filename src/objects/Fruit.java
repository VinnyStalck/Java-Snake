package objects;

import javax.swing.ImageIcon;

public class Fruit {
	private Tile tile;
	private ImageIcon icon;

	public Fruit(Grid grid) {
		place(grid);
	}

	/**
	 * Places the fruit in a random tile on a grid
	 * @param grid The grid to place the fruit on
	 */
	public void place(Grid grid) {
		this.tile = grid.getRandomTile();
	}
	
	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}

	public ImageIcon getIcon() {
		return icon;
	}

	public void setIcon(ImageIcon image) {
		this.icon = image;
	}
}
