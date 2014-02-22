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
	private Cell[][] cells;
	
	public Grid(float x, float y, int dimension)
	{
		super(x, y, ObjectId.Grid);
		this.dimension = dimension;
		this.size = getDefaultGridSize(dimension);
		this.step = getDefaultGridStep(dimension);
		this.tracksVisible = true;
		initCells();
	}
	
	private void initCells()
	{
		cells = new Cell[dimension][dimension];
		for (int xx = 0; xx < dimension; xx++)
		{
			for (int yy = 0; yy < dimension; yy++)
			{
				cells[xx][yy] = new Cell(x + xx * step, y + yy * step, this);
			}
		}
		System.out.println(x + " " + y);
	}
	
	public void tick(LinkedList<GameObject> objects)
	{

	}
	
	public void render(Graphics g)
	{
		/*g.setColor(Color.WHITE);
		g.fillRect((int) x, (int) y, size, size);*/
		
		for (int xx = 0; xx < cells.length; xx++)
		{
			for (int yy = 0; yy < cells[xx].length; yy++)
			{
				cells[xx][yy].render(g);
			}
		}
		/*g.setColor(Color.RED);
		for (int xx = (int) x; xx < (int) (x + size); xx += step)
		{
			for (int yy = (int) y; yy < (int) (y + size); yy += step)
			{
				g.drawRect(xx, yy, step, step);
			}
		}*/
		
		if (tracksVisible)
		{
			Graphics2D g2d = (Graphics2D) g;
			//g2d.setStroke(new BasicStroke(2F));
			g2d.setColor(Color.BLUE);
			
			//Horizontal track
			g2d.drawRoundRect((int) x, (int) (y + dimension / 2 * step), size, step, 20, 20);

			//Vertical track
			g2d.drawRoundRect((int)(x + dimension / 2 * step), (int) y, step, size, 20, 20);
		}
	}

	public Rectangle getHorizontalTrackBounds()
	{
		return new Rectangle((int)x, (int)(y + dimension / 2 * step), size, step);
	}
	
	public Rectangle getVerticalTrackBounds()
	{
		return new Rectangle((int)(x + dimension / 2 * step), (int) y, step, size);
	}
	
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
	
	public Cell[][] getCells()
	{
		return cells;
	}
	
	/*public void addBlock(Block block)
	{
		cells[getRow(block)][getColumn(block)] = false;
	}
	
	public void removeBlock(Block block)
	{
		cells[getRow(block)][getColumn(block)] = false;
	}
	
	public int getRow(Block block)
	{
		return (int)(block.getY() - y) / step;
	}
	
	public int getColumn(Block block)
	{
		return (int)(block.getX() - x) / step;
	}
	
	public boolean cellOccupied(Block block)
	{
		return cellOccupied(getRow(block), getColumn(block));
	}
	
	public boolean cellOccupied(int row, int column)
	{
		if (row <= dimension && column <= dimension)
		{
			return cells[row][column];
		}
		return false;
	}
	
	public void printCells()
	{
		for (int xx = 0; xx < cells.length; xx++)
		{
			for (int yy = 0; yy < cells[xx].length; yy++)
			{
				System.out.print(cells[xx][yy] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}*/
}
