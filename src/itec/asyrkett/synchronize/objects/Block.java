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
	protected boolean movingRight = false, movingLeft = false, movingUp = false, movingDown = false;
	protected float acceleration = 0.5f;
	protected final float MAX_VELOCITY = 5;
	protected Grid grid;

	public Block(float x, float y, int size, Grid grid, ObjectId id)
	{
		super(x, y, id);
		this.size = size;
		this.grid = grid;
	}

	public void tick(LinkedList<GameObject> objects)
	{
		x += velX;
		y += velY;
		
		if (movingRight)
		{
			velX += acceleration;
			velX = ((velX > MAX_VELOCITY) ? MAX_VELOCITY : velX);
		}
		else if (movingLeft)
		{
			velX -= acceleration;
			velX = ((velX < -MAX_VELOCITY) ? -MAX_VELOCITY : velX);
		}
		else if (movingUp)
		{
			velY -= acceleration;
			velY = ((velY < -MAX_VELOCITY) ? -MAX_VELOCITY : velY);
		}
		else if (movingDown)
		{
			velY += acceleration;
			velY = ((velY > MAX_VELOCITY) ? MAX_VELOCITY : velY);
		}

		collision(objects);
	}

	public void render(Graphics g)
	{
		g.setColor(Color.GREEN);
		g.fillRect((int)x, (int)y, size, size);
	}
	
	private void collision(LinkedList<GameObject> objects)
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
					setMovingLeft(false);
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
					setMovingLeft(false);
				}
			}
		}
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
	
	public Rectangle getBoundsBottom()
	{
		return new Rectangle((int)(x + size / 4), (int)(y + size / 2), size / 2, size / 2);
	}

	public Rectangle getBoundsTop()
	{
		return new Rectangle((int)(x + size / 4), (int)y, size / 2, size / 2);
	}

	public Rectangle getBoundsRight()
	{
		return new Rectangle((int)(x + size / 2), (int)(y + 5), size / 2, size - 10);
	}

	public Rectangle getBoundsLeft()
	{
		return new Rectangle((int)x, (int)(y + 5), size / 2, size - 10);
	}

	public boolean isMovingRight()
	{
		return movingRight;
	}

	public void setMovingRight(boolean movingRight)
	{
		this.movingRight = movingRight;
		this.movingLeft = false;
		this.movingUp = false;
		this.movingDown = false;
		velY = 0;
		velX = 0;
	}

	public boolean isMovingLeft()
	{
		return movingLeft;
	}

	public void setMovingLeft(boolean movingLeft)
	{
		this.movingLeft = movingLeft;
		this.movingRight = false;
		this.movingUp = false;
		this.movingDown = false;
		velY = 0;
		velX = 0;
	}

	public boolean isMovingUp()
	{
		return movingUp;
	}

	public void setMovingUp(boolean movingUp)
	{
		this.movingUp = movingUp;
		this.movingLeft = false;
		this.movingRight = false;
		this.movingDown = false;
		velY = 0;
		velX = 0;
	}

	public boolean isMovingDown()
	{
		return movingDown;
	}

	public void setMovingDown(boolean movingDown)
	{
		this.movingDown = movingDown;
		this.movingLeft = false;
		this.movingRight = false;
		this.movingUp = false;
		velY = 0;
		velX = 0;
	}
	
	public boolean isMoving()
	{
		return (movingUp || movingDown || movingRight || movingLeft);
	}
}
