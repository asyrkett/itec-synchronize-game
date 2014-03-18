package itec.asyrkett.synchronize.objects;

import itec.asyrkett.synchronize.framework.GameObject;
import itec.asyrkett.synchronize.framework.ObjectId;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class LevelSelect extends GameObject {

	private int level;
	private int width;
	private int height;
	
	public LevelSelect(float x, float y, int width, int height, int level)
	{
		super(x, y, ObjectId.LevelSelect);
		this.level = level;
		this.width = width;
		this.height = height;
	}
	
	public void tick(LinkedList<GameObject> objects)
	{
	}

	public void render(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect((int) x, (int) y, width, height);
		g.setColor(Color.WHITE);
		g.drawString("Level " + level, (int) x + 5, (int) y + 40);
	}

	public Rectangle getBounds()
	{
		return new Rectangle((int) x, (int) y, width, height);
	}

	/**
	 * @return the level
	 */
	public int getLevel()
	{
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(int level)
	{
		this.level = level;
	}

	/**
	 * @return the width
	 */
	public int getWidth()
	{
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width)
	{
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight()
	{
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height)
	{
		this.height = height;
	}
}