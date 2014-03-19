package itec.asyrkett.synchronize.objects;

import itec.asyrkett.synchronize.framework.Direction;
import itec.asyrkett.synchronize.framework.GameObject;
import itec.asyrkett.synchronize.framework.ObjectId;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

/**
 * This class represents the cell of a grid game object.
 */
public class Cell extends GameObject {

	private Block block; //the block a cell contains
	private Grid grid; //the grid the cell belongs to
	private int row; //the row of the grid where the cell is located
	private int column; //the column of the grid where the cell is located
	private int size; //the size of the column
	
	/**
	 * Constructs a cell at the specified row and column of a grid
	 * @param grid the grid the cell belongs to
	 * @param row the row of the grid to place the cell, starts at 0
	 * @param col the column of the grid to place the cell, starts at 0
	 */
	public Cell(Grid grid, int row, int col) {
		super(grid.getX() + col * grid.getStep(), grid.getY() + row * grid.getStep(), ObjectId.Cell);
		this.block = null;
		this.grid = grid;
		this.row = row;
		this.column = col;
		this.size = grid.getStep();
	}

	public void update(LinkedList<GameObject> objects)
	{
	}

	public void render(Graphics g)
	{
		/*g.setColor(Color.RED);
		g.drawRect((int)x, (int)y, size, size);*/
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, size, size);
	}
	
	/**
	 * Returns if the cell is occupied by a block
	 * @return true if occupied by a block, false otherwise
	 */
	public boolean isOccupied()
	{
		return (block != null);
	}
	
	/**
	 * Adds a block to the cell if the cell does not already have a block assigned
	 * @param block the block to add
	 * @return true if the block is successfully added, false otherwise
	 */
	public boolean addBlock(Block block)
	{
		if (this.block == null)
		{
			block.setDestinationX(x);
			block.setDestinationY(y);
			this.block = block;
			return true;
		}
		return false;
	}
	
	/**
	 * Removes the assigned block from the cell
	 * @return the block to be removed
	 */
	public Block removeBlock()
	{
		Block block = this.block;
		this.block = null;
		return block;
	}
	
	/**
	 * Returns the block assigned to the cell
	 * @return the block of the cell
	 */
	public Block getBlock()
	{
		return block;
	}
	
	/**
	 * Gets the row of the grid where the cell is located
	 * @return the row of the cell's location in the grid
	 */
	public int getRow()
	{
		return this.row;
	}
	
	/**
	 * Gets the column of the grid where the cell is located
	 * @return the column of the cell's location in the grid
	 */
	public int getColumn()
	{
		return this.column;
	}
	
	/**
	 * Gets the cell adjacent to this cell in the specified direction
	 * @param direction the direction to find the cell
	 * @return the cell adjacent to this cell in the specified direction
	 */
	public Cell getAdjacentCell(Direction direction)
	{
		if (direction == Direction.NORTH && row - 1 >= 0)
			return grid.getCells()[row - 1][column];
		else if (direction == Direction.SOUTH && row + 1 < grid.getCells().length)
			return grid.getCells()[row + 1][column];
		else if (direction == Direction.WEST && column - 1 >= 0)
			return grid.getCells()[row][column - 1];
		else if (direction == Direction.EAST && column + 1 < grid.getCells().length)
			return grid.getCells()[row][column + 1];
		else if (direction == Direction.CENTER)
			return this;
		return null;
	}
	
	public boolean isInGridTracks()
	{
		return (row == grid.getDimension() / 2 || column == grid.getDimension() / 2);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Cell [block=" + block + ", row=" + row
				+ ", column=" + column + ", size=" + size + "]";
	}
}
