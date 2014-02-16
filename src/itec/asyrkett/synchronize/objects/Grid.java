package itec.asyrkett.synchronize.objects;

import itec.asyrkett.synchronize.framework.GameObject;
import itec.asyrkett.synchronize.framework.ObjectId;
import itec.asyrkett.synchronize.window.Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Grid extends GameObject 
{
	private int dimension;
	private int size;
	private int step;
	private boolean tracksVisible;
	
	public Grid(float x, float y, int dimension, ObjectId id)
	{
		super(x, y, id);
		this.dimension = dimension;
		this.size = getDefaultGridSize(dimension);
		this.step = getDefaultGridStep(dimension);
		this.tracksVisible = true;
	}

	@Override
	public void tick(LinkedList<GameObject> objects)
	{

	}
	
	public void render(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillRect((int) x, (int) y, size, size);
		
		g.setColor(Color.RED);
		for (int xx = (int) x; xx < (int) (x + size); xx += step)
		{
			for (int yy = (int) y; yy < (int) (y + size); yy += step)
			{
				g.drawRect(xx, yy, step, step);
			}
		}
		
		if (tracksVisible)
		{
			Graphics2D g2d = (Graphics2D) g;
			//g2d.setStroke(new BasicStroke(2F));
			g2d.setColor(Color.BLUE);
			
			int horizontalTrackX = (int) x;
			int horizontalTrackY = (int) y + (dimension / 2) * step;
			g2d.drawRoundRect(horizontalTrackX, horizontalTrackY, size, step, 20, 20);
			
			int verticalTrackX = (int) x + (dimension / 2) * step;
			int verticalTrackY = (int) y;
			g2d.drawRoundRect(verticalTrackX, verticalTrackY, step, size, 20, 20);
		}
	}

	@Override
	public Rectangle getBounds()
	{
		return new Rectangle((int) x, (int) y, size, size);
	}
	
	public int getDimension()
	{
		return dimension;
	}

	public void setDimension(int dimension)
	{
		this.dimension = dimension;
	}

	public int getSize()
	{
		return size;
	}

	public void setSize(int size)
	{
		this.size = size;
	}

	public int getStep()
	{
		return step;
	}

	public void setStep(int step)
	{
		this.step = step;
	}
	
	public boolean getTracksVisible()
	{
		return tracksVisible;
	}
	
	public void setTracksVisible(boolean tracksVisible)
	{
		this.tracksVisible = tracksVisible;
	}

	public static int getDefaultGridSize(int dimension)
	{
		int size = Game.HEIGHT - Game.DEFAULT_MARGIN * 4;
		return size - ((size % dimension != 0) ? (size % dimension) : 0);
	}
	
	public static int getDefaultGridStep(int dimension)
	{
		return getDefaultGridSize(dimension) / dimension;
	}
}
