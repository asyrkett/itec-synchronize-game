package itec.aida.synchronize.framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class GameObject
{
	protected float x, y; // the x and y coordinates of the game object
	protected ObjectId id; // the id/type of object
	protected float velX = 0, velY = 0; // the x and y velocity
	
	/**
	 * Constructs a game object at x, y coordinates and of type id
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param id the id of the object
	 */
	public GameObject(float x, float y, ObjectId id)
	{
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick(LinkedList<GameObject> object);
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();

	/**
	 * Gets the x coordinate
	 * @return the x coordinate
	 */
	public float getX()
	{
		return x;
	}

	/**
	 * Sets the x coordinate
	 * @param x the x coordinate to set
	 */
	public void setX(float x)
	{
		this.x = x;
	}

	/**
	 * Gets the y coordinate
	 * @return the y coordinate
	 */
	public float getY()
	{
		return y;
	}

	/**
	 * Sets the y coordinate
	 * @param y the y coordinate to set
	 */
	public void setY(float y)
	{
		this.y = y;
	}

	/**
	 * Gets the x velocity
	 * @return the x velocity
	 */
	public float getVelX()
	{
		return velX;
	}

	/**
	 * Sets the x velocity
	 * @param velX the x velocity to set
	 */
	public void setVelX(float velX)
	{
		this.velX = velX;
	}

	/**
	 * Gets the y velocity
	 * @return the y velocity
	 */
	public float getVelY()
	{
		return velY;
	}

	/**
	 * Sets the y velocity of the object
	 * @param velY the y velocity to set
	 */
	public void setVelY(float velY)
	{
		this.velY = velY;
	}

	/**
	 * Gets the object id
	 * @return the object id
	 */
	public ObjectId getId()
	{
		return id;
	}
}