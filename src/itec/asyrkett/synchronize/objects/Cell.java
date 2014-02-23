package itec.asyrkett.synchronize.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import itec.asyrkett.synchronize.framework.GameObject;
import itec.asyrkett.synchronize.framework.ObjectId;

public class Cell extends GameObject {

	private Block block;
	private Grid grid;
	private int row;
	private int column;
	private int size;
	
	public Cell(Grid grid, int row, int col) {
		super(grid.getX() + col * grid.getStep(), grid.getY() + row * grid.getStep(), ObjectId.Cell);
		this.block = null;
		this.grid = grid;
		this.row = row;
		this.column = col;
		this.size = grid.getStep();
	}

	public void tick(LinkedList<GameObject> objects)
	{
	}

	public void render(Graphics g)
	{
		g.setColor(Color.RED);
		g.drawRect((int)x, (int)y, size, size);
	}

	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, size, size);
	}
	
	public boolean isOccupied()
	{
		return (block != null);
	}
	
	public boolean addBlock(Block block)
	{
		if (this.block == null)
		{
			/*block.setX(x);
			block.setY(y);*/
			block.setDestinationX(x);
			block.setDestinationY(y);
			this.block = block;
			return true;
		}
		return false;
	}
	
	public void removeBlock()
	{
		this.block = null;
	}
	
	public Block getBlock()
	{
		return block;
	}

	public String toString()
	{
		return "Cell [x=" + x + ", y=" + y + "]";
	}
	
	public int getRow()
	{
		return this.row;
	}
	
	public int getColumn()
	{
		return this.column;
	}
	
	public int getNumAdjacent()
	{
		Cell[][] cells = grid.getCells();
		int count = 0;
		if (row - 1 >= 0 && cells[row - 1][column].isOccupied())
			count++;
		if (row + 1 < cells.length && cells[row + 1][column].isOccupied())
			count++;
		if (column - 1 >= 0 && cells[row][column - 1].isOccupied())
			count++;
		if (column + 1 < cells.length && cells[row][column + 1].isOccupied())
			count++;
		return count;
	}
}
