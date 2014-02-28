package itec.asyrkett.synchronize.objects;

import itec.asyrkett.synchronize.framework.GameObject;
import itec.asyrkett.synchronize.framework.ObjectId;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

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
			block.setDestinationX(x);
			block.setDestinationY(y);
			this.block = block;
			return true;
		}
		return false;
	}
	
	public Block removeBlock()
	{
		Block block = this.block;
		this.block = null;
		return block;
	}
	
	public Block getBlock()
	{
		return block;
	}

	public String toString()
	{
		return "" + isOccupied() + (isOccupied() ? "" + block.getColor() : "");
		//return "Cell [x=" + x + ", y=" + y + ", isOccupied=" + isOccupied() + "]";
	}
	
	public int getRow()
	{
		return this.row;
	}
	
	public int getColumn()
	{
		return this.column;
	}
	
	public Cell getNorthCell() {
		if (row - 1 < 0)
			return null;
		return grid.getCells()[row - 1][column];
	}
	
	public Cell getSouthCell() {
		if (row + 1 >= grid.getCells().length)
			return null;
		return grid.getCells()[row + 1][column];
	}
	
	public Cell getWestCell() {
		if (column - 1 < 0)
			return null;
		return grid.getCells()[row][column - 1];
	}
	
	public Cell getEastCell() {
		if (column + 1 >= grid.getCells().length)
			return null;
		return grid.getCells()[row][column + 1];
	}
	
	public Block getNorthBlock() {
		if (row - 1 < 0)
			return null;
		return getNorthCell().block;
	}
	
	public Block getSouthBlock() {
		if (row + 1 >= grid.getCells().length)
			return null;
		return getSouthCell().block;
	}
	
	public Block getEastBlock() {
		if (column + 1 >= grid.getCells().length)
			return null;
		return getEastCell().block;
	}
	
	public Block getWestBlock() {
		if (column - 1 < 0)
			return null;
		return getWestCell().block;
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
	
	/*public int countGroup(Set<Block> groupSet)
	{
		if (this.block == null)
			return 0;
		
		Color color = this.block.getColor();
		List<Block> list = new ArrayList<Block>();
		if (getNorthBlock() != null && getNorthBlock().getColor() == color)
			list.add(getNorthBlock());
		if (getSouthBlock() != null && getSouthBlock().getColor() == color)
			list.add(getSouthBlock());
		if (getEastBlock() != null && getEastBlock().getColor() == color)
			list.add(getEastBlock());
		if (getWestBlock() != null && getWestBlock().getColor() == color)
			list.add(getWestBlock());
		
		if (list.size() == 0 || groupSet.containsAll(list))
		{
			return groupSet.size();
		}
		
		
		
		return 0;
	}*/
}
