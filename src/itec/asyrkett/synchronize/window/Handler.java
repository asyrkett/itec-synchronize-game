package itec.asyrkett.synchronize.window;

import itec.asyrkett.synchronize.framework.GameObject;
import itec.asyrkett.synchronize.framework.ObjectId;
import itec.asyrkett.synchronize.objects.CenterBlock;
import itec.asyrkett.synchronize.objects.Grid;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class Handler
{
	public LinkedList<GameObject> objects = new LinkedList<GameObject>();
	public List<Color> colorList = new ArrayList<Color>();
	
	public void tick()
	{
		for (int i = 0; i < objects.size(); i++)
		{
			objects.get(i).tick(objects);
		}
	}
	
	public void render(Graphics g)
	{
		for (int i = 0; i < objects.size(); i++)
		{
			objects.get(i).render(g);
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
		for (GameObject tempObject : objects)
		{
			if (tempObject.getId() == id)
			{
				return tempObject;
			}
		}
		return null;
	}
	
	/*public void createLevel(int level)
	{
		int xx = (Game.WIDTH - Grid.getDefaultGridSize(Game.DEFAULT_GRID_DIMENSION)) / 2;
		int yy = Game.DEFAULT_MARGIN * 2;
		Grid grid = new Grid(xx, yy, Game.DEFAULT_GRID_DIMENSION);
		addObject(grid);
		
		colorList = Arrays.asList(Color.GREEN, Color.MAGENTA, Color.CYAN);
		Collections.shuffle(colorList);
		
		CenterBlock centerBlock = new CenterBlock(grid, colorList.get(0));
		addObject(centerBlock);
	}*/
	
	public void removeCenterBlock()
	{
		CenterBlock current = (CenterBlock) getObject(ObjectId.CenterBlock);
		removeObject(current);
	}
	
	public void addCenterBlock()
	{
		Grid grid = (Grid) getObject(ObjectId.Grid);	
		Collections.shuffle(colorList);
		addObject(new CenterBlock(grid, colorList.get(0)));
	}
	
	public void addColor(Color color)
	{
		if (!colorList.contains(color))
			colorList.add(color);
	}
	
	public void removeColor(Color color)
	{
		colorList.remove(color);
	}
}