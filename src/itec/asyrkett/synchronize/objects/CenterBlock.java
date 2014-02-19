package itec.asyrkett.synchronize.objects;

import itec.asyrkett.synchronize.framework.GameObject;
import itec.asyrkett.synchronize.framework.ObjectId;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;

public class CenterBlock extends Block
{
	private boolean movingRight = false, movingLeft = false, movingUp = false, movingDown = false;
	private float acceleration = 0.5f;
	private final float MAX_VELOCITY = 5;
	private Grid grid;
	private Shape upArrow, downArrow, rightArrow, leftArrow;
	private boolean upKeyPressed = false, downKeyPressed = false, rightKeyPressed = false, leftKeyPressed = false;

	public CenterBlock(float x, float y, int size, Grid grid, ObjectId id)
	{
		super(x, y, size, id);
		this.grid = grid;
		adjustArrows();
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

	private void collision(LinkedList<GameObject> objects)
	{
		for (int i = 0; i < objects.size(); i++)
		{
			GameObject tempObject = objects.get(i);

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
		}
	}

	public void render(Graphics g)
	{
		super.render(g);

		Graphics2D g2d = (Graphics2D) g;
		if (grid.getHorizontalTrackBounds().contains(getBounds()))
		{
			rightKeyPressed = false;
			leftKeyPressed = false;
			if (upKeyPressed)
			{
				g2d.fill(upArrow);
			}
			else
			{
				g2d.draw(upArrow);
			}
			if (downKeyPressed)
			{
				g2d.fill(downArrow);
			}
			else
			{
				g2d.draw(downArrow);
			}
		}
		if (grid.getVerticalTrackBounds().contains(getBounds()))
		{
			upKeyPressed = false;
			downKeyPressed = false;
			if (leftKeyPressed)
			{
				g2d.fill(leftArrow);
			}
			else
			{
				g2d.draw(leftArrow);
			}
			if (rightKeyPressed)
			{
				g2d.fill(rightArrow);
			}
			else
			{
				g2d.draw(rightArrow);
			}
		}
		/*Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(1f));
		g2d.setColor(Color.YELLOW);
		g2d.draw(getBoundsBottom());
		g2d.draw(getBoundsTop());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsRight());*/
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
	
	private void adjustArrows()
	{
		int topArrowX = (int) (x + size / 2);
		int topArrowY = (int) y;
		int bottomArrowX = (int) (x + size / 2);
		int bottomArrowY = (int) (y + size);
		
		upArrow = createArrowShape(new Point(topArrowX, topArrowY), new Point(topArrowX, topArrowY - 20));
		downArrow = createArrowShape(new Point(bottomArrowX, bottomArrowY), new Point(bottomArrowX, bottomArrowY + 20));
		
		int leftArrowX = (int) x;
		int leftArrowY = (int) (y + size / 2);
		int rightArrowX = (int) (x + size);
		int rightArrowY = (int) (y + size / 2);
		
		leftArrow = createArrowShape(new Point(leftArrowX, leftArrowY), new Point(leftArrowX - 20, leftArrowY));
		rightArrow = createArrowShape(new Point(rightArrowX, rightArrowY), new Point(rightArrowX + 20, rightArrowY));
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
	
	public boolean isUpKeyPressed()
	{
		return upKeyPressed;
	}

	public void setUpKeyPressed(boolean upKeyPressed)
	{
		this.upKeyPressed = upKeyPressed;
		rightKeyPressed = false;
		leftKeyPressed = false;
		downKeyPressed = false;
	}

	public boolean isDownKeyPressed()
	{
		return downKeyPressed;
	}

	public void setDownKeyPressed(boolean downKeyPressed)
	{
		this.downKeyPressed = downKeyPressed;
		rightKeyPressed = false;
		leftKeyPressed = false;
		upKeyPressed = false;
	}

	public boolean isRightKeyPressed()
	{
		return rightKeyPressed;
	}

	public void setRightKeyPressed(boolean rightKeyPressed)
	{
		this.rightKeyPressed = rightKeyPressed;
		leftKeyPressed = false;
		upKeyPressed = false;
		downKeyPressed = false;
	}

	public boolean isLeftKeyPressed()
	{
		return leftKeyPressed;
	}

	public void setLeftKeyPressed(boolean leftKeyPressed)
	{
		this.leftKeyPressed = leftKeyPressed;
		rightKeyPressed = false;
		upKeyPressed = false;
		downKeyPressed = false;
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
}