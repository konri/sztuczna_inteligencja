package graph;

import java.util.Random;

public class Node {
	private int x;
	private int y;
	private Random rand = new Random();

	/*
	 * Constructor which randomly generate coordinates of Node.
	 */
	public Node() {
		x = rand.nextInt(400) + 1;
		y = rand.nextInt(400) + 1;

	}

	/*
	 * Constructor which accepts coordinates from x and y This is use, when user
	 * add node by yourself.
	 */
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/*
	 * Get node's x coordinate
	 */
	public int getX() {
		return x;
	}

	/*
	 * Get node's y coordinate
	 */
	public int getY() {
		return y;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "[" + getX() + ";" + getY() + "] ";
	}

}