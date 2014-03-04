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

public class Block extends GameObject
{
	protected final float MAX_VELOCITY = 5;
	protected final float acceleration = 0.5f;
	
	protected int size;
	protected float destinationX, destinationY;
	protected Direction direction;
	protected boolean moving;
	protected Grid grid;
	protected BlockTexture type;
	//protected Color color;
	protected Texture tex = Game.TEXTURE;

	public Block(float x, float y, int size, Grid grid, BlockTexture type)
	{
		super(x, y, ObjectId.Block);
		destinationX = x;
		destinationY = y;
		this.size = size;
		this.grid = grid;
		this.type = type;
		//this.color = color;
		this.direction = Direction.CENTER;
		this.moving = false;
	}

	public void tick(LinkedList<GameObject> objects)
	{
		if (moving)
		{		
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

		//collision(objects);
	}

	public void render(Graphics g)
	{
		g.setColor(type.getBaseColor());
		g.fillOval((int) x + 1, (int) y + 1, size - 2, size - 2);
		//g.drawImage(tex.sprites[type.getType()], (int) x, (int) y, size, size, null);
	}

	/*private void collision(LinkedList<GameObject> objects)
	{	
		for (int i = 0; i < objects.size(); i++)
		{
			GameObject tempObject = objects.get(i);
			if (tempObject == this)
				return;
			if (tempObject.getId() == ObjectId.Grid)
			{
				Grid grid = (Grid) tempObject;
				Rectangle gridBounds = grid.getBounds();
				if (!gridBounds.contains(getBoundsBottom()))
				{
					y = grid.getY() + grid.getSize() - size;
					setMovingDown(false);
				}
				if (!gridBounds.contains(getBoundsTop()))
				{
					y = grid.getY();
					setMovingUp(false);
				}
				if (!gridBounds.contains(getBoundsRight()))
				{
					x = grid.getX() + grid.getSize() - size;
					setMovingRight(false);
				}
				if (!gridBounds.contains(getBoundsLeft()))
				{
					x = grid.getX();
					setMovingWest(false);
				}

			}
			if (tempObject.getId() == ObjectId.Block)
			{
				Block block = (Block) tempObject;
				Rectangle blockBounds = block.getBounds();
				if (blockBounds.intersects(getBoundsTop()))
				{
					//System.out.println("Do stuff");
					y = block.getY() + block.getSize();
					setMovingDown(false);
				}
				if (blockBounds.intersects(getBoundsBottom()))
				{
					y = block.getY() - block.getSize();
					setMovingUp(false);
				}
				if (blockBounds.intersects(getBoundsRight()))
				{
					x = block.getX() - block.getSize();
					setMovingRight(false);
				}
				if (blockBounds.intersects(getBoundsLeft()))
				{
					x = block.getX() + block.getSize();
					setMovingWest(false);
				}
			}
		}
	}*/

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

	public Rectangle getBoundsSouth()
	{
		return new Rectangle((int)(x + size / 4), (int)(y + size / 2), size / 2, size / 2);
	}

	public Rectangle getBoundsNorth()
	{
		return new Rectangle((int)(x + size / 4), (int)y, size / 2, size / 2);
	}

	public Rectangle getBoundsEast()
	{
		return new Rectangle((int)(x + size / 2), (int)(y + 5), size / 2, size - 10);
	}

	public Rectangle getBoundsWest()
	{
		return new Rectangle((int)x, (int)(y + 5), size / 2, size - 10);
	}

	public void setMoving(boolean moving)
	{
		this.moving = moving;
		velY = 0;
		velX = 0;
	}

	public boolean isMoving()
	{
		return moving;
	}

	public float getDestinationX()
	{
		return destinationX;
	}

	public void setDestinationX(float destinationX)
	{
		this.destinationX = destinationX;
	}

	public float getDestinationY()
	{
		return destinationY;
	}

	public void setDestinationY(float destinationY)
	{
		this.destinationY = destinationY;
	}

	/*public Color getColor()
	{
		return color;
	}

	public void setColor(Color color)
	{
		this.color = color;
	}*/
	
	public BlockTexture getType()
	{
		return type;
	}
	
	public void setType(BlockTexture type)
	{
		this.type = type;
	}

	public Direction getDirection()
	{
		return direction;
	}

	public void setDirection(Direction direction)
	{
		this.direction = direction;
	}
}