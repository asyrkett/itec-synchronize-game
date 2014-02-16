package itec.asyrkett.synchronize.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import itec.asyrkett.synchronize.framework.GameObject;
import itec.asyrkett.synchronize.framework.ObjectId;
import itec.asyrkett.synchronize.window.Game;

public class Grid extends GameObject 
{

	private int dimension;
	private int size;
	private int step;
	
	public Grid(float x, float y, int dimension, ObjectId id) {
		super(x, y, id);
		this.dimension = dimension;
		this.size = getDefaultGridSize(dimension);
		this.step = getDefaultGridStep(dimension);
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillRect((int) x, (int) y, size, size);
		
		g.setColor(Color.RED);
		for (int xx = (int) x; xx < (int) (x + size); xx += step)
		{
			for (int yy = (int) y; yy < (int) (y + size); yy += step)
			{
				g.drawRect(xx, yy, step, step);
			}
		}
	}

	@Override
	public Rectangle getBounds()
	{
		return new Rectangle((int) x, (int) y, size, size);
	}
	
	public int getDimension()
	{
		return dimension;
	}

	public void setDimension(int dimension)
	{
		this.dimension = dimension;
	}

	public int getSize()
	{
		return size;
	}

	public void setSize(int size)
	{
		this.size = size;
	}

	public int getStep()
	{
		return step;
	}

	public void setStep(int step)
	{
		this.step = step;
	}

	public static int getDefaultGridSize(int dimension)
	{
		int size = Game.HEIGHT - Game.DEFAULT_MARGIN * 4;
		if (size % dimension != 0)
		{
			size -= (size % dimension);
		}
		return size;
	}
	
	public static int getDefaultGridStep(int dimension)
	{
		return getDefaultGridSize(dimension) / dimension;
	}
}
