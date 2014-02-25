package itec.asyrkett.synchronize.window;

import itec.asyrkett.synchronize.framework.GameObject;
import itec.asyrkett.synchronize.framework.ObjectId;
import itec.asyrkett.synchronize.objects.CenterBlock;
import itec.asyrkett.synchronize.objects.Grid;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
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
	
	public void createLevel(int level)
	{
		int xx = (Game.WIDTH - Grid.getDefaultGridSize(Game.DEFAULT_GRID_DIMENSION)) / 2;
		int yy = Game.DEFAULT_MARGIN * 2;
		Grid grid = new Grid(xx, yy, Game.DEFAULT_GRID_DIMENSION);
		addObject(grid);
		
		colorList = Arrays.asList(Color.GREEN, Color.MAGENTA);
		Collections.shuffle(colorList);
		
		int step = grid.getStep();
		int dimension = grid.getDimension();
		CenterBlock centerBlock = new CenterBlock(xx + step * (dimension / 2), yy + step * (dimension / 2), step, grid, colorList.get(0));
		addObject(centerBlock);
	}
	
	public void removeCenterBlock()
	{
		CenterBlock current = (CenterBlock) getObject(ObjectId.CenterBlock);
		removeObject(current);
	}
	
	public void addCenterBlock()
	{
		int xx = (Game.WIDTH - Grid.getDefaultGridSize(Game.DEFAULT_GRID_DIMENSION)) / 2;
		int yy = Game.DEFAULT_MARGIN * 2;
		Grid grid = (Grid) getObject(ObjectId.Grid);
		int step = grid.getStep();
		int dimension = grid.getDimension();
		
		Collections.shuffle(colorList);
		addObject(new CenterBlock(xx + step * (dimension / 2), yy + step * (dimension / 2), step, grid, colorList.get(0)));
	}
}