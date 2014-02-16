package itec.asyrkett.synchronize.framework;

import itec.asyrkett.synchronize.objects.CenterBlock;
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
		
		if (handler.getObject(ObjectId.CenterBlock) != null)
		{
			CenterBlock centerBlock = (CenterBlock) handler.getObject(ObjectId.CenterBlock);
			if (keyCode == KeyEvent.VK_UP && !centerBlock.isMovingUp())
			{
				centerBlock.setMovingUp(true);
			}
			else if (keyCode == KeyEvent.VK_DOWN && !centerBlock.isMovingDown())
			{
				centerBlock.setMovingDown(true);
			}
			else if (keyCode == KeyEvent.VK_RIGHT && !centerBlock.isMovingRight())
			{
				centerBlock.setMovingRight(true);
			}
			else if (keyCode == KeyEvent.VK_LEFT && !centerBlock.isMovingLeft())
			{
				centerBlock.setMovingLeft(true);
			}
		}
	}
	
	public void keyReleased(KeyEvent e)
	{
		/*int keyCode = e.getKeyCode();
		
		if (handler.getObject(ObjectId.CenterBlock) != null)
		{
			CenterBlock centerBlock = (CenterBlock) handler.getObject(ObjectId.CenterBlock);
			if (keyCode == KeyEvent.VK_UP)
			{
				centerBlock.setVelY(0);
			}
			else if (keyCode == KeyEvent.VK_DOWN)
			{
				centerBlock.setVelY(0);
			}
			else if (keyCode == KeyEvent.VK_RIGHT)
			{
				centerBlock.setVelX(0);
			}
			else if (keyCode == KeyEvent.VK_LEFT)
			{
				centerBlock.setVelX(0);
			}
		}*/
	}
}
