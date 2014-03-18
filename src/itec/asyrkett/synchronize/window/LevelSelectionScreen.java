package itec.asyrkett.synchronize.window;

import itec.asyrkett.synchronize.framework.GameMode;
import itec.asyrkett.synchronize.framework.Texture;
import itec.asyrkett.synchronize.objects.Button;
import itec.asyrkett.synchronize.objects.LevelSelect;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

/**
 * This class represents the screen to render
 * when the game is in level selection mode
 */
public class LevelSelectionScreen extends Screen
{
	public static final int MAX_COLUMNS = 4;
	public static final int MAX_ROWS = 4;
	public static final int DEFAULT_BLOCK_WIDTH = 128;
	public static final int DEFAULT_BLOCK_HEIGHT = 64;
	public static final int PADDING_HORIZONTAL = (Game.WIDTH - MAX_COLUMNS * DEFAULT_BLOCK_WIDTH) / (MAX_COLUMNS + 1);
	public static final int PADDING_VERTICAL = (Game.HEIGHT - MAX_ROWS * DEFAULT_BLOCK_HEIGHT) / (MAX_ROWS + 1);
	
	private int numLevels; //the number of levels to display
	private LinkedList<LevelSelect> levelList;
	
	/**
	 * Constructs a level selection screen with the given number of levels
	 * @param numLevels the number of levels to display
	 */
	public LevelSelectionScreen(int numLevels)
	{
		super(GameMode.LEVEL_SELECTION);
		this.numLevels = numLevels;
		initLevelList();
		addButton(new Button((Game.WIDTH - Button.DEFAULT_WIDTH) / 2, 520, Texture.BUTTON_TEXT_MENU));
	}
	
	private void initLevelList()
	{
		this.levelList = new LinkedList<LevelSelect>();
		final int totalRows = (int) Math.ceil(1.0 * numLevels / MAX_COLUMNS);
		int levelNum = 1;
		int xCoor = 50;
		int yCoor = 50;
		
		for (int row = 1; row <= totalRows; row++)
		{
			for (int col = 1; col <= MAX_COLUMNS && levelNum <= numLevels; col++)
			{
				LevelSelect levelSelect = new LevelSelect(xCoor, yCoor, 128, 64, levelNum);
				levelList.add(levelSelect);
				levelNum++;
				xCoor += (128 + PADDING_HORIZONTAL);
			}
			xCoor = 50;
			yCoor += (64 + PADDING_VERTICAL);
		}
	}
	
	/**
	 * Renders a menu button and level selection tiles
	 */
	public void render(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.drawImage(background, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		
		super.render(g);
		
		//levelList.get(0).render(g);
		for (int i = 0; i < levelList.size(); i++)
			levelList.get(i).render(g);
	}
	
	/**
	 * Returns the number of levels the screen displays
	 * @return the number of levels
	 */
	public int getNumLevels()
	{
		return numLevels;
	}

	/**
	 * @return the levelList
	 */
	public LinkedList<LevelSelect> getLevelList() {
		return levelList;
	}

	/**
	 * @param levelList the levelList to set
	 */
	public void setLevelList(LinkedList<LevelSelect> levelList) {
		this.levelList = levelList;
	}
}