package itec.asyrkett.synchronize.window;

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
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

/**
 * The main mechanism of the game
 */
public class Game extends Canvas implements Runnable
{
	public static int WIDTH, HEIGHT; //the game's width and height
	public static Font FONT; //the game's font
	
	public static final int DEFAULT_MARGIN = 32; //the default margin
	public static final int TOTAL_LEVELS = 7; //the total number of levels in the game
	
	private boolean running = false; // whether or not the game is running
	private Thread thread; // the game thread
	private BufferedImage levelImage;
	private GameMode gameMode = GameMode.MENU; //the game starts on the menu screen
	private int level = 1; //the default starting level of the game
	private LinkedList<Screen> screens; //a list of the game's screens
	private Screen currentScreen; //the current screen the game is rendering
	private Handler handler; // handler of the game objects
	private int blockTextureType = Texture.BLOCK_SQUARE;
	
	private static final long serialVersionUID = -2703393471231825194L;
	
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
				update();
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
	private void update()
	{
		if (gameMode == GameMode.PLAY)
			handler.update();
	}
	
	/**
	 * Renders the graphics
	 */
	private void render()
	{
		BufferStrategy bufferStrategy = this.getBufferStrategy();
		if (bufferStrategy == null)
		{
			this.createBufferStrategy(3); //create 3 buffers behind first one
			return;
		}
		
		Graphics g = bufferStrategy.getDrawGraphics();
		//////////////////////////////////////////////////
		//DRAW HERE
		g.setFont(FONT);
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
	 * Advances and loads the game by one level
	 */
	public int nextLevel()
	{
		return setLevel(level + 1);
	}
	
	/**
	 * Sets the level for the game to load and display when in play mode
	 * @param level the level to load
	 */
	public int setLevel(int level)
	{
		if (level > TOTAL_LEVELS)
			this.level = TOTAL_LEVELS;
		else
			this.level = level;
		handler.clearHandler();
		handler.clearBlockColors();
		levelImage = BufferedImageLoader.loadImage("/levels/level" + this.level + ".png");
		loadImageLevel(levelImage);
		return this.level;
	}
	
	/**
	 * Gets the texture type of the blocks to display from the Texture class
	 * (Texture.BLOCK_SQUARE, Texture.BLOCK_CIRCLE, etc.)
	 * @return the block texture type
	 */
	public int getBlockTextureType()
	{
		return blockTextureType;
	}
	
	/**
	 * Initializes game objects
	 */
	private void init()
	{
		WIDTH = getWidth();
		HEIGHT = getHeight();
		try {
			FONT = Font.createFont(Font.PLAIN, this.getClass().getResourceAsStream("/font/NEUROPOL.ttf"));
			FONT = FONT.deriveFont(Font.BOLD, 20);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
	
		//creating the game screens
		screens = new LinkedList<Screen>();
		screens.add(new MenuScreen(this));
		screens.add(new PlayScreen(this));
		screens.add(new LevelSelectionScreen(this, TOTAL_LEVELS));
		screens.add(new HelpScreen(this));
		currentScreen = getScreen(GameMode.MENU);
		
		handler = new Handler(this);
		
		//loading the default starting level
		levelImage = BufferedImageLoader.loadImage("/levels/level" + level + ".png");
		loadImageLevel(levelImage);
		
		//adding key and mouse listeners
		final MouseInput mouseInput = new MouseInput(this);
		this.addMouseListener(mouseInput);
		this.addMouseMotionListener(mouseInput);
		this.addKeyListener(new KeyInput(this));
	}
	
	/**
	 * Loads and renders the given image level to the game's play screen
	 * @param image the level's image to load
	 */
	private void loadImageLevel(BufferedImage image)
	{
		final int width = image.getWidth();
		
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
		Grid grid = new Grid(0, Game.DEFAULT_MARGIN * 2, dimension);
		grid.setX((Game.WIDTH - grid.getSize()) / 2);
		handler.addObject(grid);
		int step = grid.getStep();
		float gridX = grid.getX();
		float gridY = grid.getY();
		
		//populates the grid's cells with blocks and other game objects
		Cell[][] cells = grid.getCells();
		for (int xx = 0; xx < dimension; xx++)
		{
			for (int yy = 0; yy < dimension; yy++)
			{
				Color color = getPixelColor(image, xx, yy);
				if (!color.equals(Color.WHITE))
				{
					int colorTexture = getBlockTexture(color);
					handler.addBlockColor(colorTexture);
					Block block = new Block(gridX + (xx * step), gridY + (yy * step), 
							step, grid, blockTextureType, colorTexture);
					cells[yy][xx].addBlock(block);
					handler.addObject(block);
				}
			}
		}
		
		//add player-controlled block
		handler.addCenterBlock();
	}
	
	/**
	 * Gets the block Texture corresponding to the given color, Texture.BLOCK_BLUE default
	 * @param color the base color of the texture
	 * @return the Texture type of the color (Texture.BLOCK_RED, Texture.BLOCK_ORANGE, etc.)
	 */
	private int getBlockTexture(Color color)
	{
		if (color.equals(Color.RED))
			return Texture.BLOCK_RED;
		else if (color.equals(Color.MAGENTA))
			return Texture.BLOCK_MAGENTA;
		else if (color.equals(new Color(255, 120, 0)))
			return Texture.BLOCK_ORANGE;
		else if (color.equals(Color.YELLOW))
			return Texture.BLOCK_YELLOW;
		else if (color.equals(Color.GREEN))
			return Texture.BLOCK_GREEN;
		else if (color.equals(Color.CYAN))
			return Texture.BLOCK_CYAN;
		else //if (color.equals(Color.BLUE)), default
			return Texture.BLOCK_BLUE;
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
	 * Runs the game
	 * @param args
	 */
	public static void main(String[] args)
	{
		new Window(800, 600, "Synchronize Game Prototype", new Game());
	}
}