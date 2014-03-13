package itec.asyrkett.synchronize.objects;

import itec.asyrkett.synchronize.framework.Direction;
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

/**
 * This class represents a square grid object on which
 * blocks are positioned
 */
public class Grid extends GameObject 
{
	private int dimension; //the dimension of the grid, dimension x dimension
	private int size; //the side length of the grid
	private int step; //the length between each cell of the grid
	private boolean tracksVisible; //whether or not the horizontal and vertical tracks are visible
	private Cell[][] cells; //the cells of the grid

	/**
	 * Creates an empty grid of the given dimensions at the given location
	 * with its tracks visible
	 * @param x the x coordinate of the grid's upper left corner
	 * @param y the y coordinate of the grid's upper left corner
	 * @param dimension how many cells long and tall the grid will be
	 */
	public Grid(float x, float y, int dimension)
	{
		super(x, y, ObjectId.Grid);
		this.dimension = dimension;
		this.size = getDefaultGridSize(dimension);
		this.step = getDefaultGridStep(dimension);
		this.tracksVisible = true;
		initCells();
	}

	/**
	 * Initializes the cells of the grid
	 */
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

	/**
	 * Renders the grid, its cells, and its tracks if they are set visible
	 */
	public void render(Graphics g)
	{	
		g.setColor(Color.BLACK);
		g.fillRect((int) x, (int) y, size, size);

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
			g2d.setColor(Color.WHITE);

			//Horizontal track
			g2d.drawRoundRect((int) x, (int) (y + dimension / 2 * step), size, step, 20, 20);

			//Vertical track
			g2d.drawRoundRect((int)(x + dimension / 2 * step), (int) y, step, size, 20, 20);
		}
	}

	/**
	 * Returns the bounds of the horizontal track
	 * spanning across the columns and located in the middle row of the grid
	 * at dimension/2 row
	 * @return the horizontal track bounds
	 */
	public Rectangle getHorizontalTrackBounds()
	{
		return new Rectangle((int)x, (int)(y + dimension / 2 * step), size, step);
	}

	/**
	 * Returns the bounds of the vertical track
	 * spanning across the rows and located in the middle column of the grid
	 * at dimension/2 row
	 * @return the vertical track bounds
	 */
	public Rectangle getVerticalTrackBounds()
	{
		return new Rectangle((int)(x + dimension / 2 * step), (int) y, step, size);
	}

	public Rectangle getBounds()
	{
		return new Rectangle((int) x, (int) y, size, size);
	}

	/**
	 * Returns the grid dimensions, the number of rows
	 * @return the dimension of the grid
	 */
	public int getDimension()
	{
		return dimension;
	}

	/**
	 * Returns the side length of the grid
	 * @return the size of the grid
	 */
	public int getSize()
	{
		return size;
	}

	/**
	 * Gets the step size of the grid, the length between each cell
	 * @return the step size of the grid
	 */
	public int getStep()
	{
		return step;
	}

	/**
	 * Returns if the tracks are visible or not
	 * @return true if tracks are visible, false otherwise
	 */
	public boolean getTracksVisible()
	{
		return tracksVisible;
	}

	/**
	 * Sets the tracks visibility
	 * @param tracksVisible the visibility to set
	 */
	public void setTracksVisible(boolean tracksVisible)
	{
		this.tracksVisible = tracksVisible;
	}

	/**
	 * Calculates the default grid size for the given dimension for a Game object
	 * @param dimension the number of rows of the grid
	 * @return the side length of the grid
	 */
	public static int getDefaultGridSize(int dimension)
	{
		int size = Game.HEIGHT - Game.DEFAULT_MARGIN * 4;
		return size - ((size % dimension != 0) ? (size % dimension) : 0);
	}

	/**
	 * Calculates the default grid step for the given dimension for a Game object
	 * @param dimension the number of rows of the grid
	 * @return the length between each cell of the grid for a default game
	 */
	public static int getDefaultGridStep(int dimension)
	{
		return getDefaultGridSize(dimension) / dimension;
	}

	/**
	 * Returns the a 2d array of the cells of the grid
	 * @return the grid's cells
	 */
	public Cell[][] getCells()
	{
		return cells;
	}

	/**
	 * Returns the row where the block is positioned on the grid
	 * @param block the block to check
	 * @return the row the block is positioned
	 */
	public int getRow(Block block)
	{
		return (int)(block.getY() - y) / step;
	}

	/**
	 * Returns the column where the block is positioned on the grid
	 * @param block the block to check
	 * @return the column the block is positioned
	 */
	public int getColumn(Block block)
	{
		return (int)(block.getX() - x) / step;
	}

	/**
	 * Returns true if all of the grid's cells do not contain a block
	 * @return true if all of the grid's cells do not contain a block, false otherwise
	 */
	public boolean isEmpty()
	{
		for (int row = 0; row < cells.length; row++)
		{
			for (int column = 0; column < cells[row].length; column++)
			{
				if (cells[row][column].getBlock() != null)
					return false;
			}
		}
		return true;
	}

	/**
	 * Checks for a group of cells in the grid that match.
	 * A group matches if they are a group of at least 3 adjacent cells with blocks
	 * of the same texture color
	 * @return the cells that make a match
	 */
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
					List<Cell> verticalSGroup = new ArrayList<Cell>();
					Cell southCell = cell;
					while (southCell != null && southCell.isOccupied() &&
							southCell.getBlock().getTextureColor() == cell.getBlock().getTextureColor())
					{
						verticalSGroup.add(southCell);
						southCell = southCell.getAdjacentCell(Direction.SOUTH);
					}

					List<Cell> horizontalEGroup = new ArrayList<Cell>();
					Cell eastCell = cell;
					while (eastCell != null && eastCell.isOccupied() &&
							eastCell.getBlock().getTextureColor() == cell.getBlock().getTextureColor())
					{
						horizontalEGroup.add(eastCell);
						eastCell = eastCell.getAdjacentCell(Direction.EAST);
					}

					List<Cell> verticalNGroup = new ArrayList<Cell>();
					Cell northCell = cell;
					while (northCell != null && northCell.isOccupied() &&
							northCell.getBlock().getTextureColor() == cell.getBlock().getTextureColor())
					{
						verticalNGroup.add(northCell);
						northCell = northCell.getAdjacentCell(Direction.NORTH);
					}

					List<Cell> horizontalWGroup = new ArrayList<Cell>();
					Cell westCell = cell;
					while (westCell != null && westCell.isOccupied() &&
							westCell.getBlock().getTextureColor	() == cell.getBlock().getTextureColor())
					{
						horizontalWGroup.add(westCell);
						westCell = westCell.getAdjacentCell(Direction.WEST);
					}

					if (verticalNGroup.size() + horizontalWGroup.size() >= 4)
					{
						cellsToRemove.addAll(verticalNGroup);
						cellsToRemove.addAll(horizontalWGroup);
					}
					if (verticalSGroup.size() + horizontalWGroup.size() >= 4)
					{
						cellsToRemove.addAll(verticalSGroup);
						cellsToRemove.addAll(horizontalWGroup);
					}
					if (verticalSGroup.size() + horizontalEGroup.size() >= 4)
					{
						cellsToRemove.addAll(verticalSGroup);
						cellsToRemove.addAll(horizontalEGroup);
					}
					if (verticalNGroup.size() + horizontalEGroup.size() >= 4)
					{
						cellsToRemove.addAll(verticalNGroup);
						cellsToRemove.addAll(horizontalEGroup);
					}
					if (horizontalEGroup.size() >= 3)
						cellsToRemove.addAll(horizontalEGroup);
					if (verticalSGroup.size() >= 3)
						cellsToRemove.addAll(verticalSGroup);
				}
			}
		}
		return cellsToRemove;
	}
}