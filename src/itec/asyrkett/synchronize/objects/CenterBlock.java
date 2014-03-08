package itec.asyrkett.synchronize.objects;

import itec.asyrkett.synchronize.framework.Direction;
import itec.asyrkett.synchronize.framework.GameObject;
import itec.asyrkett.synchronize.framework.ObjectId;
import itec.asyrkett.synchronize.framework.BlockTexture;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;

/**
 * This class represents a block game object that the player can
 * move north, south, east, or west (up, down, left, right).
 */
public class CenterBlock extends Block
{
	//the arrows indicating in which direction the player can move
	private Shape upArrow, downArrow, rightArrow, leftArrow;
	
	/**
	 * Constructs a block to the center position of the given grid
	 * @param grid the grid to which the block belongs
	 * @param texture the texture of the block
	 */
	public CenterBlock(Grid grid, BlockTexture texture)
	{
		super(grid.getX() + grid.getStep() * (grid.getDimension() / 2),
				grid.getY() + grid.getStep() * (grid.getDimension() / 2),
				grid.getStep(), grid, texture);
		setId(ObjectId.CenterBlock);
		adjustArrows();
	}

	public void tick(LinkedList<GameObject> objects)
	{
	}
	
	/**
	 * Renders a block and the arrows indicating the direction
	 * the block can move. If a direction is chosen, the arrow is filled in.
	 */
	public void render(Graphics g)
	{
		super.render(g);
		
		g.setColor(texture.getBaseColor());
		Graphics2D g2d = (Graphics2D) g;
		
		if (grid.getHorizontalTrackBounds().contains(getBounds()))
		{
			if (direction == Direction.NORTH)
				g2d.fill(upArrow);
			else
				g2d.draw(upArrow);
			if (direction == Direction.SOUTH)
				g2d.fill(downArrow);
			else
				g2d.draw(downArrow);
		}
		
		if (grid.getVerticalTrackBounds().contains(getBounds()))
		{	
			if (direction == Direction.WEST)
				g2d.fill(leftArrow);
			else
				g2d.draw(leftArrow);
			if (direction == Direction.EAST)
				g2d.fill(rightArrow);
			else
				g2d.draw(rightArrow);
		}
	}
	
	/**
	 * Creates a block object with the center block's settings
	 * including location, size, grid, texture, and direction
	 * @return the block object with the center block's attributes
	 */
	public Block toBlock()
	{
		Block block = new Block(x, y, size, grid, texture);
		block.setDirection(direction);
		return block;
	}
	
	/**
	 * Sets the x coordinate of the center block and adjusts the arrows' positions
	 * @param x the x coordinate to set
	 */
	public void setX(float x)
	{
		super.setX(x);
		adjustArrows();
	}
	
	/**
	 * Sets the y coordinate of the center block and adjusts the arrows' positions
	 * @param y the y coordinate to set
	 */
	public void setY(float y)
	{
		super.setY(y);
		adjustArrows();
	}

	/**
	 * Creates a shape in the form of an arrow extending from a start point to an end point
	 * @param startPoint the start position of the arrow
	 * @param endPoint the end position of the arrow
	 * @return an arrow extending from the start point to the end point
	 */
	private Shape createArrowShape(Point startPoint, Point endPoint)
	{
	    Polygon arrowPolygon = new Polygon();
	    arrowPolygon.addPoint(-6, 1);
	    arrowPolygon.addPoint(3, 1);
	    arrowPolygon.addPoint(3, 3);
	    arrowPolygon.addPoint(6, 0);
	    arrowPolygon.addPoint(3, -3);
	    arrowPolygon.addPoint(3, -1);
	    arrowPolygon.addPoint(-6, -1);

	    Point midpoint = midpoint(startPoint, endPoint);
	    double rotate = Math.atan2(endPoint.y - startPoint.y, endPoint.x - startPoint.x);

	    AffineTransform transform = new AffineTransform();
	    transform.translate(midpoint.x, midpoint.y);
	    double ptDistance = startPoint.distance(endPoint);
	    double scale = ptDistance / 12; // 12 because it's the length of the arrow polygon.
	    transform.scale(scale, scale);
	    transform.rotate(rotate);

	    return transform.createTransformedShape(arrowPolygon);
	}

	/**
	 * Calculates the midpoint between two other points
	 * @param p1 the first point
	 * @param p2 the second point
	 * @return the midpoint of the segment between two other points
	 */
	private Point midpoint(Point p1, Point p2)
	{
	    return new Point((int)((p1.x + p2.x)/2.0), (int)((p1.y + p2.y)/2.0));
	}
	
	/**
	 * Adjusts the positions of the arrows relative to the block's position
	 */
	private void adjustArrows()
	{	
		upArrow = createArrowShape(new Point((int) (x + size / 2), (int) y),
				new Point((int) (x + size / 2), (int) y - 20));
		downArrow = createArrowShape(new Point((int) (x + size / 2), (int) (y + size)),
				new Point((int) (x + size / 2), (int) (y + size) + 20));
		leftArrow = createArrowShape(new Point((int) x, (int) (y + size / 2)),
				new Point((int) x - 20, (int) (y + size / 2)));
		rightArrow = createArrowShape(new Point((int) (x + size), (int) (y + size / 2)),
				new Point((int) (x + size) + 20, (int) (y + size / 2)));
	}
}