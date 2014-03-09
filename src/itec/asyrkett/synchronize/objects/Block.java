package itec.asyrkett.synchronize.objects;

import itec.asyrkett.synchronize.framework.Direction;
import itec.asyrkett.synchronize.framework.GameObject;
import itec.asyrkett.synchronize.framework.ObjectId;
import itec.asyrkett.synchronize.framework.BlockTexture;
import itec.asyrkett.synchronize.framework.Texture;
import itec.asyrkett.synchronize.window.Game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

/**
 * This class is a game object that represents a block
 * that the user matches by color to advance in a level.
 */
public class Block extends GameObject
{
	protected final float MAX_VELOCITY = 5;
	protected final float acceleration = 0.5f;
	
	protected int size; //the side length of the block in pixels
	protected float destinationX, destinationY; //the destination coordinates of the block if moving
	protected Direction direction; //the direction in which the block is moving
	protected boolean moving; //whether or not the block is static or in motion
	protected Grid grid; //the grid to which the block is drawn
	protected BlockTexture texture; //the rendered texture of the block
	protected Texture tex = Game.TEXTURE;

	/**
	 * Creates a block game object at the specified location of the grid
	 * @param x the x coordinate of the block's upper left corner
	 * @param y the y coordinate of the block's upper left corner
	 * @param size the side length of the block
	 * @param grid the grid in which the block is placed
	 * @param texture the texture of the block
	 */
	public Block(float x, float y, int size, Grid grid, BlockTexture texture)
	{
		super(x, y, ObjectId.Block);
		destinationX = x;
		destinationY = y;
		this.size = size;
		this.grid = grid;
		this.texture = texture;
		this.direction = Direction.CENTER;
		this.moving = false;
	}

	/**
	 * Updates the block's location if it is in motion
	 */
	public void tick(LinkedList<GameObject> objects)
	{
		if (!moving)
			return;

		x += velX;
		y += velY;

		if (direction == Direction.EAST)
		{
			velX += acceleration;
			velX = ((velX > MAX_VELOCITY) ? MAX_VELOCITY : velX);
			if (x > destinationX)
			{
				x = destinationX;
				setMoving(false);
			}
		}
		else if (direction == Direction.WEST)
		{
			velX -= acceleration;
			velX = ((velX < -MAX_VELOCITY) ? -MAX_VELOCITY : velX);
			if (x < destinationX)
			{
				x = destinationX;
				setMoving(false);
			}
		}
		else if (direction == Direction.NORTH)
		{
			velY -= acceleration;
			velY = ((velY < -MAX_VELOCITY) ? -MAX_VELOCITY : velY);
			if (y < destinationY)
			{
				y = destinationY;
				setMoving(false);
			}
		}
		else if (direction == Direction.SOUTH)
		{
			velY += acceleration;
			velY = ((velY > MAX_VELOCITY) ? MAX_VELOCITY : velY);
			if (y > destinationY)
			{
				y = destinationY;
				setMoving(false);
			}
		}
	}

	/**
	 * Renders the block's current texture
	 */
	public void render(Graphics g)
	{
		g.setColor(texture.getBaseColor());
		//g.fillOval((int) x + 1, (int) y + 1, size - 2, size - 2);
		g.drawImage(tex.sprites[1][texture.getType()], (int) x, (int) y, size, size, null);
	}

	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, size, size);
	}

	/**
	 * Returns the side length of the block in pixels
	 * @return the side length of the block
	 */
	public int getSize()
	{
		return size;
	}

	/**
	 * Sets the side length of the block
	 * @param size the side length to set
	 */
	public void setSize(int size)
	{
		this.size = size;
	}

	/**
	 * Sets the block to static or in motion
	 * @param moving true if moving, false otherwise
	 */
	public void setMoving(boolean moving)
	{
		this.moving = moving;
		velY = 0;
		velX = 0;
	}

	/**
	 * Returns if the block is moving or is sitting still
	 * @return true if moving, false otherwise
	 */
	public boolean isMoving()
	{
		return moving;
	}

	/**
	 * Returns the x coordinate of where the block should move to
	 * @return the x coordinate of the block's destination
	 */
	public float getDestinationX()
	{
		return destinationX;
	}

	/**
	 * Sets the x coordinate of the location where the block should move
	 * @param destinationX the x coordinate of the block's destination
	 */
	public void setDestinationX(float destinationX)
	{
		this.destinationX = destinationX;
	}

	/**
	 * Returns the y coordinate of where the block should move to
	 * @return the y coordinate of the block's destination
	 */
	public float getDestinationY()
	{
		return destinationY;
	}

	/**
	 * Sets the y coordinate of the location where the block should move
	 * @param destinationY the y coordinate of the block's destination
	 */
	public void setDestinationY(float destinationY)
	{
		this.destinationY = destinationY;
	}
	
	/**
	 * Gets the block's rendered texture
	 * @return the texture the block renders
	 */
	public BlockTexture getTexture()
	{
		return texture;
	}
	
	/**
	 * Sets the block's texture
	 * @param texture the texture to set
	 */
	public void setTexture(BlockTexture texture)
	{
		this.texture = texture;
	}

	/**
	 * Gets the direction the block is to move
	 * @return the direction the block is to move
	 */
	public Direction getDirection()
	{
		return direction;
	}

	/**
	 * Sets the direction the block is to move
	 * @param direction the direction to set
	 */
	public void setDirection(Direction direction)
	{
		this.direction = direction;
	}
}