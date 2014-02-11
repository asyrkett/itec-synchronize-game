package itec.aida.synchronize.window;

import itec.aida.synchronize.framework.GameObject;
import itec.aida.synchronize.framework.ObjectId;
import itec.aida.synchronize.objects.Block;

import java.awt.Graphics;
import java.util.LinkedList;


public class Handler
{
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private GameObject tempObject;
	
	public void tick()
	{
		for (int i = 0; i < object.size(); i++)
		{
			tempObject = object.get(i);
			tempObject.tick(object);
		}
	}
	
	public void render(Graphics g)
	{
		for (int i = 0; i < object.size(); i++)
		{
			tempObject = object.get(i);
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject object)
	{
		this.object.add(object);
	}
	
	public void removeObject(GameObject object)
	{
		this.object.remove(object);
	}
	
	public void createLevel()
	{
		for (int xx = 0; xx < Game.WIDTH; xx += Block.SIZE)
		{
			for (int yy = 0; yy < Game.HEIGHT; yy += Block.SIZE)
			{
				addObject(new Block(xx, yy, ObjectId.Block));
			}
		}
	}
}