package itec.asyrkett.synchronize.framework;

import itec.asyrkett.synchronize.objects.CenterBlock;
import itec.asyrkett.synchronize.objects.Grid;
import itec.asyrkett.synchronize.window.Handler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter
{
	private Handler handler;
	
	public KeyInput(Handler handler)
	{
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e)
	{
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_ESCAPE)
		{
			System.exit(1);
		}
		
		if (handler.getObject(ObjectId.CenterBlock) != null && handler.getObject(ObjectId.Grid)!= null )
		{
			CenterBlock centerBlock = (CenterBlock) handler.getObject(ObjectId.CenterBlock);
			Grid grid = (Grid) handler.getObject(ObjectId.Grid);
			
			if (grid.getHorizontalTrackBounds().contains(centerBlock.getBounds()))
			{
				if (keyCode == KeyEvent.VK_RIGHT && centerBlock.getX() != grid.getX() + grid.getSize() - centerBlock.getSize())
				{
					centerBlock.setUpKeyPressed(false);
					centerBlock.setDownKeyPressed(false);
					centerBlock.setX(centerBlock.getX() + grid.getStep());
				}
				else if (keyCode == KeyEvent.VK_LEFT && centerBlock.getX() != grid.getX())
				{
					centerBlock.setUpKeyPressed(false);
					centerBlock.setDownKeyPressed(false);
					centerBlock.setX(centerBlock.getX() - grid.getStep());
				}
				else if (keyCode == KeyEvent.VK_UP)
				{
					centerBlock.setUpKeyPressed(true);
				}
				else if (keyCode == KeyEvent.VK_DOWN)
				{
					centerBlock.setDownKeyPressed(true);
				}
				else if (keyCode == KeyEvent.VK_SPACE)
				{
					if (centerBlock.isUpKeyPressed())
					{
						centerBlock.setMovingUp(true);
						handler.addCenterBlock();
					}
					else if (centerBlock.isDownKeyPressed())
					{
						centerBlock.setMovingDown(true);
						handler.addCenterBlock();
					}
				}
			}
			if (grid.getVerticalTrackBounds().contains(centerBlock.getBounds()))
			{
				if (keyCode == KeyEvent.VK_UP && centerBlock.getY() != grid.getY())
				{
					centerBlock.setRightKeyPressed(false);
					//centerBlock.setLeftKeyPressed(false);
					centerBlock.setY(centerBlock.getY() - grid.getStep());
				}
				else if (keyCode == KeyEvent.VK_DOWN && centerBlock.getY() != (grid.getY() + grid.getSize() - centerBlock.getSize()))
				{
					centerBlock.setLeftKeyPressed(false);
					//centerBlock.setRightKeyPressed(false);
					centerBlock.setY(centerBlock.getY() + grid.getStep());
				}
				else if (keyCode == KeyEvent.VK_RIGHT)
				{
					centerBlock.setRightKeyPressed(true);
				}
				else if (keyCode == KeyEvent.VK_LEFT)
				{
					centerBlock.setLeftKeyPressed(true);
				}
				else if (keyCode == KeyEvent.VK_SPACE)
				{
					if (centerBlock.isRightKeyPressed())
					{
						//if ()
						//{
							
						//}
						//else
						//{
							centerBlock.setMovingRight(true);
							handler.addCenterBlock();
						//}
					}
					else if (centerBlock.isLeftKeyPressed())
					{
						centerBlock.setMovingLeft(true);
						handler.addCenterBlock();
					}
				}
			}
		}
	}
}
