package itec.asyrkett.synchronize.window;

import itec.asyrkett.synchronize.framework.BufferedImageLoader;
import itec.asyrkett.synchronize.framework.GameState;
import itec.asyrkett.synchronize.framework.KeyInput;
import itec.asyrkett.synchronize.framework.MouseInput;
import itec.asyrkett.synchronize.framework.BlockTexture;
import itec.asyrkett.synchronize.framework.Texture;
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
	private static final long serialVersionUID = -2703393471231825194L;
	
	private boolean running = false; // whether or not the game is running
	private Thread thread; // the game thread
	private BufferedImage levelImage = null;
	private GameState state = GameState.MENU;
	private int level = 1;
	
	public static int WIDTH, HEIGHT;
	public static final int DEFAULT_MARGIN = 32;
	public static final int DEFAULT_GRID_DIMENSION = 9;
	public static Texture TEXTURE;
	public static final Random GENERATOR = new Random();
	public static final int TOTAL_LEVELS = 3;
	
	private BufferedImageLoader loader = new BufferedImageLoader();
	private Handler handler; // handler of the graphics objects
	private Menu menu;
	private LevelSelection levelSelection;
	private GameBackground gameBackground;
	//private SpriteSheet spriteSheet;
	
	/**
	 * Initializes game objects
	 */
	private void init()
	{
		WIDTH = getWidth();
		HEIGHT = getHeight();
		
		TEXTURE = new Texture();
		
		levelImage = loader.loadImage("/levels/level" + level + ".png"); // loading the level
	
		menu = new Menu();
		gameBackground = new GameBackground();
		levelSelection = new LevelSelection(TOTAL_LEVELS);
		handler = new Handler(this);
		//handler.createLevel(1);
		
		loadImageLevel(levelImage);
		
		//KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
	    //manager.addKeyEventDispatcher(new KeyInput(handler));
		this.addKeyListener(new KeyInput(this));
		this.addMouseListener(new MouseInput(this));
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
				//System.out.println("FPS: " + frames + " TICKS: " + updates);
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
		if (state == GameState.GAME)
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
		
		if (state == GameState.GAME)
		{ 
			//g.setColor(Color.BLACK);
			//g.fillRect(0, 0, WIDTH, HEIGHT);
			gameBackground.render(g);
			handler.render(g);
		}
		else if (state == GameState.MENU)
		{
			menu.render(g);
		}
		else if (state == GameState.LEVEL)
		{
			levelSelection.render(g);
		}
		
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
				Color color = getPixelColor(image, xx, yy);
				if (!color.equals(Color.BLACK)) { //not black
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
		BlockTexture[] types = BlockTexture.values();
		for (int xx = 0; xx < dimension; xx++)
		{
			for (int yy = 0; yy < dimension; yy++)
			{
				Color color = getPixelColor(image, xx, yy);
				BlockTexture type = null;
				if (!color.equals(Color.WHITE)) //not white
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
		handler.addCenterBlock();
	}
	
	private Color getPixelColor(BufferedImage image, int x, int y)
	{
		int pixel = image.getRGB(x, y);
		int red = (pixel >> 16) & 0xff;
		int green = (pixel >> 8) & 0xff;
		int blue = (pixel) & 0xff;
		return new Color(red, green, blue);
	}
	
	public GameState getState()
	{
		return state;
	}
	
	public void setState(GameState state)
	{
		this.state = state;
	}
	
	public Menu getMenu()
	{
		return menu;
	}
	
	public GameBackground getGameBackground()
	{
		return gameBackground;
	}
	
	public LevelSelection getLevelSelection()
	{
		return levelSelection;
	}
	
	public Handler getHandler()
	{
		return handler;
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public void resetLevel()
	{
		handler.clearHandler();
		loadImageLevel(levelImage);
	}
	
	public int nextLevel()
	{
		level++;
		handler.clearHandler();
		handler.clearTextures();
		levelImage = loader.loadImage("/levels/level" + level + ".png");
		loadImageLevel(levelImage);
		return level;
	}
	
	public static void main(String[] args)
	{
		new Window(800, 600, "Synchronize Game Prototype", new Game());
	}
}