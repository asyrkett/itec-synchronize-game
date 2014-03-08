package itec.asyrkett.synchronize.window;

import itec.asyrkett.synchronize.framework.BlockTexture;
import itec.asyrkett.synchronize.framework.BufferedImageLoader;
import itec.asyrkett.synchronize.framework.GameMode;
import itec.asyrkett.synchronize.framework.KeyInput;
import itec.asyrkett.synchronize.framework.MouseInput;
import itec.asyrkett.synchronize.framework.Texture;
import itec.asyrkett.synchronize.objects.Block;
import itec.asyrkett.synchronize.objects.Cell;
import itec.asyrkett.synchronize.objects.Grid;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;

/**
 * The main mechanism of the game
 */
public class Game extends Canvas implements Runnable
{
	private static final long serialVersionUID = -2703393471231825194L;
	
	private boolean running = false; // whether or not the game is running
	private Thread thread; // the game thread
	private BufferedImage levelImage;
	private GameMode gameMode = GameMode.MENU; //the game starts on the menu screen
	private int level = 1; //the default starting level of the game
	
	public static int WIDTH, HEIGHT; //the game's width and height
	public static final int DEFAULT_MARGIN = 32; //the default margin
	public static final int DEFAULT_GRID_DIMENSION = 9; //the default dimension of the grid, 9x9 cells
	public static Texture TEXTURE;
	public static final Random GENERATOR = new Random(); //random number generator
	public static final int TOTAL_LEVELS = 3; //the total number of levels in the game
	
	private LinkedList<Screen> screens; //a list of the game's screens
	private Screen currentScreen; //the current screen the game is rendering
	private Handler handler; // handler of the game objects
	
	/**
	 * Initializes game objects
	 */
	private void init()
	{
		WIDTH = getWidth();
		HEIGHT = getHeight();
		TEXTURE = new Texture();
	
		//creating the game screens
		screens = new LinkedList<Screen>();
		screens.add(new MenuScreen());
		screens.add(new PlayScreen());
		screens.add(new LevelSelectionScreen(TOTAL_LEVELS));
		currentScreen = getScreen(GameMode.MENU);
		
		handler = new Handler(this);
		
		//loading the default starting level
		levelImage = BufferedImageLoader.loadImage("/levels/level" + level + ".png");
		loadImageLevel(levelImage);
		
		//adding key and mouse listeners
		this.addKeyListener(new KeyInput(this));
		this.addMouseListener(new MouseInput(this));
	}
	
	/**
	 * Starts the game as a new thread
	 */
	public synchronized void start()
	{
		if (running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	/**
	 * The game loop
	 */
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
		if (gameMode == GameMode.PLAY)
			handler.tick();
	}
	
	/**
	 * Renders the graphics
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
		
		currentScreen.render(g);
		if (gameMode == GameMode.PLAY)
			handler.render(g);
		
		//////////////////////////////////////////////////
		g.dispose();
		bufferStrategy.show();
	}
	
	/**
	 * Searches and returns the screen that has the given game mode
	 * @param gameMode the game mode of the screen to search for
	 * @return the first screen in the game's screens with the given mode, otherwise null
	 */
	public Screen getScreen(GameMode gameMode)
	{
		for (Screen screen : screens)
		{
			if (screen.getGameMode() == gameMode)
				return screen;
		}
		return null;
	}
	
	/**
	 * Loads and renders the given image level to the game's play screen
	 * @param image the level's image to load
	 */
	private void loadImageLevel(BufferedImage image)
	{
		int width = image.getWidth();
		
		//get the dimension of the grid to create
		int dimension = 0;
		for (int xx = 0; xx <= 0; xx++)
		{
			for (int yy = 0; yy < width; yy++)
			{
				Color color = getPixelColor(image, xx, yy);
				if (!color.equals(Color.BLACK))
					dimension++;
				else
					break;
			}
		}
		
		//create the grid
		Grid grid = new Grid((Game.WIDTH - Grid.getDefaultGridSize(dimension)) / 2,
				Game.DEFAULT_MARGIN * 2,
				dimension);
		handler.addObject(grid);
		int step = grid.getStep();
		float gridX = grid.getX();
		float gridY = grid.getY();
		
		//populates the grid's cells with blocks and other game objects
		Cell[][] cells = grid.getCells();
		BlockTexture[] types = BlockTexture.values();
		for (int xx = 0; xx < dimension; xx++)
		{
			for (int yy = 0; yy < dimension; yy++)
			{
				Color color = getPixelColor(image, xx, yy);
				BlockTexture type = null;
				if (!color.equals(Color.WHITE))
				{
					for (BlockTexture texture : types)
					{
						if (texture.getBaseColor().equals(color))
						{
							type = texture;
							handler.addTexture(texture);
							break;
						}
					}
					Block block = new Block(gridX + (xx * step), gridY + (yy * step), step, grid, type);
					cells[yy][xx].addBlock(block);
					handler.addObject(block);
				}
			}
		}
		
		//add player-controlled block
		handler.addCenterBlock();
	}
	
	/**
	 * Returns a Color object of the image's color at the given location
	 * @param image the image to check
	 * @param x the x location of the pixel
	 * @param y the y location of the pixel
	 * @return a Color object of the pixel of the image at the location (x, y)
	 */
	private Color getPixelColor(BufferedImage image, int x, int y)
	{
		int pixel = image.getRGB(x, y);
		int red = (pixel >> 16) & 0xff;
		int green = (pixel >> 8) & 0xff;
		int blue = (pixel) & 0xff;
		return new Color(red, green, blue);
	}
	
	/**
	 * Returns the current mode of the game
	 * @return the current game mode
	 */
	public GameMode getGameMode()
	{
		return gameMode;
	}
	
	/**
	 * Sets the mode of the game and sets the corresponding screen
	 * @param gameMode the game mode to set
	 */
	public void setGameMode(GameMode gameMode)
	{
		this.gameMode = gameMode;
		currentScreen = getScreen(gameMode);
	}
	
	/**
	 * Gets the game object handler
	 * @return the game's object handler
	 */
	public Handler getHandler()
	{
		return handler;
	}
	
	/**
	 * Gets the current level
	 * @return the current level
	 */
	public int getLevel()
	{
		return level;
	}
	
	/**
	 * Resets the current level of the game to its original form
	 */
	public void resetLevel()
	{
		handler.clearHandler();
		loadImageLevel(levelImage);
	}
	
	/**
	 * Advances and loads the game one level
	 * @return the next level number
	 */
	public int nextLevel()
	{
		level++;
		handler.clearHandler();
		handler.clearTextures();
		levelImage = BufferedImageLoader.loadImage("/levels/level" + level + ".png");
		loadImageLevel(levelImage);
		return level;
	}
	
	/**
	 * Runs the game
	 * @param args
	 */
	public static void main(String[] args)
	{
		new Window(800, 600, "Synchronize Game Prototype", new Game());
	}
}