package itec.asyrkett.synchronize.window;

import itec.asyrkett.synchronize.framework.ObjectId;
import itec.asyrkett.synchronize.objects.Grid;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable
{
	private static final long serialVersionUID = 1L;
	//private static final Random GENERATOR = new Random();
	
	private boolean running = false; // whether or not the game is running
	private Thread thread; // the game thread
	
	public static int WIDTH, HEIGHT;
	public static final int DEFAULT_MARGIN = 32;
	
	Handler handler; // handler of the graphics objects
	
	/**
	 * Initializes game objects
	 */
	private void init()
	{
		WIDTH = getWidth();
		HEIGHT = getHeight();
		
		handler = new Handler();
		handler.createLevel();
	}
	
	public synchronized void start()
	{
		if (running)
		{
			return;
		}
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	// game loop
	public void run()
	{
		init();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0; //updates
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while (running)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1)
			{
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}

	/**
	 * Handles game updates
	 */
	private void tick()
	{
		handler.tick();
	}
	
	/**
	 * Handles game graphics rendering
	 */
	private void render()
	{
		BufferStrategy bufferStrategy = this.getBufferStrategy();
		if (bufferStrategy == null)
		{
			this.createBufferStrategy(3); // create 3 buffers behind first one
			return;
		}
		
		Graphics g = bufferStrategy.getDrawGraphics();
		//////////////////////////////////////////////////
		//DRAW HERE
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		//Create the grid
		int xx = (WIDTH - Grid.getDefaultGridSize(9)) / 2;
		int yy = DEFAULT_MARGIN * 2;
		Grid grid = new Grid(xx, yy, 9, ObjectId.Grid);
		grid.render(g);
		
		//Create horizontal and vertical grid tracks
		
		
		
		handler.render(g);
		
		
		//////////////////////////////////////////////////
		g.dispose();
		bufferStrategy.show();
		
	}
	
	public static void main(String[] args)
	{
		new Window(800, 600, "Synchronize Game Prototype", new Game());
	}
}