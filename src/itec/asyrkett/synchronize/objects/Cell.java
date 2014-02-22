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
	
	public Cell(float x, float y, Grid grid) {
		super(x, y, ObjectId.Cell);
		this.block = null;
		this.grid = grid;
	}

	public void tick(LinkedList<GameObject> objects)
	{
	}

	public void render(Graphics g)
	{
		g.setColor(Color.RED);
		g.drawRect((int)x, (int)y, grid.getStep(), grid.getStep());
	}

	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, grid.getStep(), grid.getStep());
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
}
