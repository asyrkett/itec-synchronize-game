package itec.asyrkett.synchronize.objects;

import itec.asyrkett.synchronize.framework.Direction;
import itec.asyrkett.synchronize.framework.GameObject;
import itec.asyrkett.synchronize.framework.ObjectId;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;

public class CenterBlock extends Block
{
	private Shape upArrow, downArrow, rightArrow, leftArrow;
	//private boolean upKeyPressed = false, downKeyPressed = false, rightKeyPressed = false, leftKeyPressed = false;
	
	public CenterBlock(float x, float y, int size, Grid grid, Color color)
	{
		super(x, y, size, grid, color);
		setId(ObjectId.CenterBlock);
		adjustArrows();
	}

	public void tick(LinkedList<GameObject> objects)
	{
	}
	
	public void render(Graphics g)
	{
		super.render(g);

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
		/*Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(1f));
		g2d.setColor(Color.YELLOW);
		g2d.draw(getBoundsBottom());
		g2d.draw(getBoundsTop());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsRight());*/
	}
	
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
	
	public void setX(float x)
	{
		super.setX(x);
		adjustArrows();
	}
	
	public void setY(float y)
	{
		super.setY(y);
		adjustArrows();
	}

	public static Shape createArrowShape(Point startPoint, Point endPoint)
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

	private static Point midpoint(Point p1, Point p2)
	{
	    return new Point((int)((p1.x + p2.x)/2.0), (int)((p1.y + p2.y)/2.0));
	}
	
	public Block toBlock()
	{
		Block block = new Block(x, y, size, grid, color);
		block.setDirection(direction);
		return block;
	}
}