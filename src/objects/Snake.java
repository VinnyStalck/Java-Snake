package objects;

import javax.swing.ImageIcon;

public class Snake {
	private int[] xLength = new int[750];
	private int[] yLength = new int[750];

	private boolean isFacingRight = false;
	private boolean isFacingLeft = false;
	private boolean isFacingUp = false;
	private boolean isFacingDown = false;

	private ImageIcon headFacingRight;
	private ImageIcon headFacingLeft;
	private ImageIcon headFacingUp;
	private ImageIcon headFacingDown;
	private ImageIcon tail;

	private int length = 3;

	public Snake() {
		
	}

	public void grow() {
		length++;
	}

	public int[] getXLength() {
		return xLength;
	}

	public void setXLength(int key, int value) {
		this.xLength[key] = value;
	}

	public int[] getYLength() {
		return yLength;
	}

	public void setYLength(int key, int value) {
		this.yLength[key] = value;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public ImageIcon getHeadFacingRight() {
		return headFacingRight;
	}

	public void setHeadFacingRight(ImageIcon headFacingRight) {
		this.headFacingRight = headFacingRight;
	}

	public ImageIcon getHeadFacingLeft() {
		return headFacingLeft;
	}

	public void setHeadFacingLeft(ImageIcon headFacingLeft) {
		this.headFacingLeft = headFacingLeft;
	}

	public ImageIcon getHeadFacingUp() {
		return headFacingUp;
	}

	public void setHeadFacingUp(ImageIcon headFacingUp) {
		this.headFacingUp = headFacingUp;
	}

	public ImageIcon getHeadFacingDown() {
		return headFacingDown;
	}

	public void setHeadFacingDown(ImageIcon headFacingDown) {
		this.headFacingDown = headFacingDown;
	}

	public ImageIcon getTail() {
		return tail;
	}

	public void setTail(ImageIcon tail) {
		this.tail = tail;
	}

	public boolean isFacingRight() {
		return isFacingRight;
	}

	public void setFacingRight() {
		resetFacing();
		isFacingRight = true;
	}

	public boolean isFacingLeft() {
		return isFacingLeft;
	}

	public void setFacingLeft() {
		resetFacing();
		isFacingLeft = true;
	}

	public boolean isFacingUp() {
		return isFacingUp;
	}

	public void setFacingUp() {
		resetFacing();
		isFacingUp = true;
	}

	public boolean isFacingDown() {
		return isFacingDown;
	}

	public void setFacingDown() {
		resetFacing();
		isFacingDown = true;
	}

	private void resetFacing() {
		isFacingRight = false;
		isFacingLeft = false;
		isFacingUp = false;
		isFacingDown = false;
	}
}
