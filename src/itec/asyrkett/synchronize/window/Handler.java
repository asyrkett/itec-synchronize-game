package itec.asyrkett.synchronize.window;

import itec.asyrkett.synchronize.framework.GameMode;
import itec.asyrkett.synchronize.framework.GameObject;
import itec.asyrkett.synchronize.framework.ObjectId;
import itec.asyrkett.synchronize.objects.Block;
import itec.asyrkett.synchronize.objects.CenterBlock;
import itec.asyrkett.synchronize.objects.Grid;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

/**
 * This class handles all of the game objects
 */
public class Handler
{
	public static final Random RANDOM_GENERATOR = new Random();
	
	private LinkedList<GameObject> objects = new LinkedList<GameObject>(); //the objects the handler holds in the current level
	private LinkedList<Integer> blockColors = new LinkedList<Integer>(); //the block colors in the current level
	private Game game;
	
	/**
	 * Constructs a handler for the given game
	 * @param game the game in which to place the objects
	 */
	public Handler(Game game)
	{
		this.game = game;
	}
	
	/**
	 * Ticks the updates for all the objects in the handler
	 */
	public void update()
	{
		for (int i = 0; i < objects.size(); i++)
		{
			objects.get(i).update(objects);
		}
	}
	
	/**
	 * Renders all the objects in the handler
	 * @param g the graphics to on which to draw the objects
	 */
	public void render(Graphics g)
	{
		for (int i = 0; i < objects.size(); i++)
		{
			objects.get(i).render(g);
		}
	}
	
	/**
	 * Adds the specified object to the handler
	 * @param object the object to add
	 */
	public void addObject(GameObject object)
	{
		this.objects.add(object);
	}
	
	/**
	 * Removes the specified object from the handler
	 * @param object the object to remove
	 */
	public void removeObject(GameObject object)
	{
		this.objects.remove(object);
	}
	
	/**
	 * Gets the first object in the handler with the specified id
	 * @param id the id of the object to find
	 * @return the first object with the given id, otherwise null
	 */
	public GameObject getObject(ObjectId id)
	{
		for (GameObject tempObject : objects)
		{
			if (tempObject.getId() == id)
			{
				return tempObject;
			}
		}
		return null;
	}
	
	/**
	 * Removes the current center block from the handler
	 */
	public void removeCenterBlock()
	{
		CenterBlock current = (CenterBlock) getObject(ObjectId.CenterBlock);
		if (current != null)
			removeObject(current);
	}
	
	/**
	 * Adds a new center block to the handler
	 */
	public void addCenterBlock()
	{
		Grid grid = (Grid) getObject(ObjectId.Grid);
		PlayScreen playScreen = (PlayScreen) game.getScreen(GameMode.PLAY);
		addObject(new CenterBlock(grid, game.getBlockTextureType(), playScreen.getNextBlockColor()));
		playScreen.setNextBlockColor(getRandomColor());
	}
	
	public int getRandomColor()
	{
		return blockColors.get(RANDOM_GENERATOR.nextInt(blockColors.size()));
	}

	/**
	 * Adds the specified block color to the handler
	 * @param textureColor the color of a block from the Texture class (Texture.BLOCK_RED, Texture.BLOCK_MAGENTA, etc.)
	 */
	public void addBlockColor(int textureColor)
	{
		if (!blockColors.contains(textureColor))
			blockColors.add(textureColor);
	}
	
	/**
	 * Removes all objects from the handler
	 */
	public void clearHandler()
	{
		for (int i = 0; i < objects.size(); i++)
		{
			objects.remove(i);
			i--;
		}
	}
	
	/**
	 * Removes all block colors form the handler
	 */
	public void clearBlockColors()
	{
		for (int i = 0; i < blockColors.size(); i++)
		{
			blockColors.remove(i);
			i--;
		}
	}
	
	/**
	 * Gets the game the handler renders to
	 * @return the game
	 */
	public Game getGame()
	{
		return game;
	}
	
	public void resetBlockTextures()
	{
		for (int i = 0; i < objects.size(); i++)
		{
			GameObject object = objects.get(i);
			if (object.getId() == ObjectId.Block || object.getId() == ObjectId.CenterBlock)
			{
				Block block = (Block) object;
				block.setTextureType(game.getBlockTextureType());
			}
		}
	}
}