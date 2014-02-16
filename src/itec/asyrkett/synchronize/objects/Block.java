package itec.asyrkett.synchronize.objects;

import itec.asyrkett.synchronize.framework.GameObject;
import itec.asyrkett.synchronize.framework.ObjectId;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Block extends GameObject
{
	
	//public static final int SIZE = 32;
	protected int size;

	public Block(float x, float y, int size, ObjectId id)
	{
		super(x, y, id);
		this.size = size;
	}

	public void tick(LinkedList<GameObject> objects)
	{
		
	}

	public void render(Graphics g)
	{
		g.setColor(Color.GREEN);
		g.drawRect((int)x, (int)y, size, size);
	}

	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, size, size);
	}

	public int getSize()
	{
		return size;
	}

	public void setSize(int size)
	{
		this.size = size;
	}
}
