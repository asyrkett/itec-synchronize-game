package itec.asyrkett.synchronize.objects;

import itec.asyrkett.synchronize.framework.GameObject;
import itec.asyrkett.synchronize.framework.ObjectId;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

/**
 * This class is a game object that allows the player to select a level
 * to display from the game
 */
public class LevelSelect extends GameObject
{
	private int level;
	private int width, height;
	private boolean hovered;
	private boolean locked;
	
	public LevelSelect(float x, float y, int width, int height, int level)
	{
		super(x, y, ObjectId.LevelSelect);
		this.level = level;
		this.width = width;
		this.height = height;
		hovered = false;
		if (level == 1)
			locked = false;
		else
			locked = true;
	}
	
	public void update(LinkedList<GameObject> objects)
	{
	}

	public void render(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect((int) x, (int) y, width, height);
		if (locked)
		{
			g.setColor(Color.RED);
			if (hovered)
			{
				g.drawString("Level " + level, (int) x + 5, (int) y + 40);
				g.drawRect((int) x, (int) y, width, height);
			}
			else
			{
				g.setColor(Color.WHITE);
				g.drawString("Level " + level, (int) x + 5, (int) y + 40);
			}
			g.drawLine((int) x, (int) y, (int) x + width, (int) y + height);
			g.drawLine((int) x + width, (int) y, (int) x, (int) y + height);
		}
		else
		{
			if (hovered)
			{
				g.setColor(Color.GREEN);
				g.drawRect((int) x, (int) y, width, height);
			}
			else
				g.setColor(Color.WHITE);
			g.drawString("Level " + level, (int) x + 5, (int) y + 40);
		}
	}

	public Rectangle getBounds()
	{
		return new Rectangle((int) x, (int) y, width, height);
	}

	/**
	 * Gets the level
	 * @return the level
	 */
	public int getLevel()
	{
		return level;
	}

	/**
	 * Sets the level
	 * @param level the level to set
	 */
	public void setLevel(int level)
	{
		this.level = level;
	}

	/**
	 * Gets the width of the enclosing rectangle
	 * @return the width
	 */
	public int getWidth()
	{
		return width;
	}

	/**
	 * Sets the width of the level select box
	 * @param width the width to set
	 */
	public void setWidth(int width)
	{
		this.width = width;
	}

	/**
	 * Gets the height of the enclosing rectangle
	 * @return the height
	 */
	public int getHeight()
	{
		return height;
	}

	/**
	 * Sets the height of the level select box
	 * @param height the height to set
	 */
	public void setHeight(int height)
	{
		this.height = height;
	}

	/**
	 * Returns the hovered state of the level select
	 * @return true if a mouse is hovering over it, false otherwise
	 */
	public boolean isHovered()
	{
		return hovered;
	}

	/**
	 * Sets the state of the level if a mouse is hovering over it
	 * @param hovered the state to set
	 */
	public void setHovered(boolean hovered)
	{
		this.hovered = hovered;
	}

	/**
	 * Returns if the level select is locked or unlocked
	 * @return true if locked, false if unlocked
	 */
	public boolean isLocked()
	{
		return locked;
	}

	/**
	 * Sets the level select's locked state
	 * @param locked the state to set
	 */
	public void setLocked(boolean locked)
	{
		this.locked = locked;
	}
}