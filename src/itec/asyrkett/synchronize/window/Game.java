package itec.asyrkett.synchronize.window;

import itec.asyrkett.synchronize.framework.KeyInput;
import itec.asyrkett.synchronize.objects.Block;
import itec.asyrkett.synchronize.objects.Cell;
import itec.asyrkett.synchronize.objects.Grid;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Game extends Canvas implements Runnable
{
	private static final long serialVersionUID = 1L;
	//private static final Random GENERATOR = new Random();
	
	private boolean running = false; // whether or not the game is running
	private Thread thread; // the game thread
	private BufferedImage level = null;
	
	public static int WIDTH, HEIGHT;
	public static final int DEFAULT_MARGIN = 32;
	public static final int DEFAULT_GRID_DIMENSION = 9;
	public static final Random GENERATOR = new Random();
	
	Handler handler; // handler of the graphics objects
	
	/**
	 * Initializes game objects
	 */
	private void init()
	{
		WIDTH = getWidth();
		HEIGHT = getHeight();
		
		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/level01.png"); // loading the level
		
		handler = new Handler();
		//handler.createLevel(1);
		
		loadImageLevel(level);
		
		//KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
	    //manager.addKeyEventDispatcher(new KeyInput(handler));
		this.addKeyListener(new KeyInput(handler));
	}
	
	public synchronized void start()
	{
		if (running)
			return;
		
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
		
		handler.render(g);
		
		
		//////////////////////////////////////////////////
		g.dispose();
		bufferStrategy.show();
		
	}
	
	private void loadImageLevel(BufferedImage image)
	{
		int width = image.getWidth();
		int dimension = 0;
		for (int xx = 0; xx <= 0; xx++)
		{
			for (int yy = 0; yy < width; yy++)
			{
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				if (!(red == 0 && green == 0 && blue == 0)) { //not black
					dimension++;
				}
				else
					break;
			}
		}
		
		Grid grid = new Grid((Game.WIDTH - Grid.getDefaultGridSize(dimension)) / 2,
				Game.DEFAULT_MARGIN * 2,
				dimension);
		handler.addObject(grid);
		int step = grid.getStep();
		float gridX = grid.getX();
		float gridY = grid.getY();
		
		Cell[][] cells = grid.getCells();
		for (int xx = 0; xx < dimension; xx++)
		{
			for (int yy = 0; yy < dimension; yy++)
			{
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if (!(red == 255 && green == 255 && blue == 255)) //not white
				{
					Color color = new Color(red, green, blue);
					handler.addColor(color);
					Block block = new Block(gridX + (xx * step), gridY + (yy * step), step, grid, color);
					cells[yy][xx].addBlock(block);
					handler.addObject(block);
				}
			}
		}
		handler.addCenterBlock();
	}
	
	/*private Color getPixelColor(BufferedImage image, int x, int y)
	{
		int pixel = image.getRGB(x, y);
		int red = (pixel >> 16) & 0xff;
		int green = (pixel >> 8) & 0xff;
		int blue = (pixel) & 0xff;
		return new Color(red, green, blue);
	}*/
	
	public static void main(String[] args)
	{
		new Window(800, 600, "Synchronize Game Prototype", new Game());
	}
}