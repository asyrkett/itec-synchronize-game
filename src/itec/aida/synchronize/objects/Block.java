package itec.aida.synchronize.objects;

import itec.aida.synchronize.framework.GameObject;
import itec.aida.synchronize.framework.ObjectId;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Block extends GameObject
{
	
	public static final int SIZE = 32;

	public Block(float x, float y, ObjectId id)
	{
		super(x, y, id);
		// TODO Auto-generated constructor stub
	}

	public void tick(LinkedList<GameObject> object)
	{
		// TODO Auto-generated method stub
		
	}

	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.drawRect((int)x, (int)y, SIZE, SIZE);
		
	}

	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, SIZE, SIZE);
	}

}
