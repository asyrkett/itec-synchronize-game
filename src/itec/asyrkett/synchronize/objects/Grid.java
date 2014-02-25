package itec.asyrkett.synchronize.objects;

import itec.asyrkett.synchronize.framework.GameObject;
import itec.asyrkett.synchronize.framework.ObjectId;
import itec.asyrkett.synchronize.window.Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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
		for (int row = 0; row < dimension; row++)
		{
			for (int col = 0; col < dimension; col++)
			{
				cells[row][col] = new Cell(this, row, col);
			}
		}
	}
	
	public void tick(LinkedList<GameObject> objects)
	{

	}
	
	public void render(Graphics g)
	{	
		for (int xx = 0; xx < cells.length; xx++)
		{
			for (int yy = 0; yy < cells[xx].length; yy++)
			{
				cells[xx][yy].render(g);
			}
		}
		
		if (tracksVisible)
		{
			Graphics2D g2d = (Graphics2D) g;
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
	
	public int getRow(Block block)
	{
		return (int)(block.getY() - y) / step;
	}
	
	public int getColumn(Block block)
	{
		return (int)(block.getX() - x) / step;
	}
	
	public boolean isEmpty()
	{
		boolean empty = true;
		for (int row = 0; row < cells.length; row++)
		{
			for (int column = 0; column < cells[row].length; column++)
			{
				if (cells[row][column].getBlock() != null)
					return false;
			}
		}
		return empty;
	}
	
	public Set<Cell> checkForMatch()
	{
		Set<Cell> cellsToRemove = new HashSet<Cell>();
		for (int row = 0; row < cells.length; row++)
		{
			for (int column = 0; column < cells[row].length; column++)
			{
				Cell cell = cells[row][column];
				if (cell.isOccupied())
				{
					List<Cell> verticalGroup = new ArrayList<Cell>();
					Cell southCell = cell;
					while (southCell != null && southCell.isOccupied() && southCell.getBlock().getColor() == cell.getBlock().getColor())
					{
						verticalGroup.add(southCell);
						southCell = southCell.getSouthCell();
					}
					
					List<Cell> horizontalGroup = new ArrayList<Cell>();
					Cell eastCell = cell;
					while (eastCell != null && eastCell.isOccupied() && eastCell.getBlock().getColor() == cell.getBlock().getColor())
					{
						horizontalGroup.add(eastCell);
						eastCell = eastCell.getEastCell();
					}
					
					if (horizontalGroup.size() >= 3)
						cellsToRemove.addAll(horizontalGroup);
					if (verticalGroup.size() >= 3)
						cellsToRemove.addAll(verticalGroup);
				}
			}
		}
		return cellsToRemove;
	}
}