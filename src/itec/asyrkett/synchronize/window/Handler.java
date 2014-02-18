package itec.asyrkett.synchronize.window;

import itec.asyrkett.synchronize.framework.GameObject;
import itec.asyrkett.synchronize.framework.ObjectId;
import itec.asyrkett.synchronize.objects.CenterBlock;
import itec.asyrkett.synchronize.objects.Grid;

import java.awt.Graphics;
import java.util.LinkedList;


public class Handler
{
	public LinkedList<GameObject> objects = new LinkedList<GameObject>();
	
	private GameObject tempObject;
	
	public void tick()
	{
		for (int i = 0; i < objects.size(); i++)
		{
			tempObject = objects.get(i);
			tempObject.tick(objects);
		}
	}
	
	public void render(Graphics g)
	{
		for (int i = 0; i < objects.size(); i++)
		{
			tempObject = objects.get(i);
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject object)
	{
		this.objects.add(object);
	}
	
	public void removeObject(GameObject object)
	{
		this.objects.remove(object);
	}
	
	public GameObject getObject(ObjectId id)
	{
		for (int i = 0; i < objects.size(); i++)
		{
			tempObject = objects.get(i);
			if (tempObject.getId() == id)
			{
				return tempObject;
			}
		}
		return null;
	}
	
	public void createLevel()
	{
		int xx = (Game.WIDTH - Grid.getDefaultGridSize(9)) / 2;
		int yy = Game.DEFAULT_MARGIN * 2;
		Grid grid = new Grid(xx, yy, 9, ObjectId.Grid);
		addObject(grid);
		
		int step = grid.getStep();
		int dimension = grid.getDimension();
		CenterBlock centerBlock = new CenterBlock(xx + step * (dimension / 2), yy + step * (dimension / 2), step, grid, ObjectId.CenterBlock);
		addObject(centerBlock);
		
		/*for (int xx = 0; xx < Game.WIDTH; xx += Block.SIZE)
		{
			for (int yy = 0; yy < Game.HEIGHT; yy += Block.SIZE)
			{
				addObject(new Block(xx, yy, ObjectId.Block));
			}
		}*/
	}
}