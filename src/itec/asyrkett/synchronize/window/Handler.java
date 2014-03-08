package itec.asyrkett.synchronize.window;

import itec.asyrkett.synchronize.framework.GameObject;
import itec.asyrkett.synchronize.framework.ObjectId;
import itec.asyrkett.synchronize.framework.BlockTexture;
import itec.asyrkett.synchronize.objects.CenterBlock;
import itec.asyrkett.synchronize.objects.Grid;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * This class handles all of the game objects
 */
public class Handler
{
	public LinkedList<GameObject> objects = new LinkedList<GameObject>(); //the objects the handler holds in the level
	public List<BlockTexture> textureList = new ArrayList<BlockTexture>(); //the textures of the current level
	
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
	public void tick()
	{
		for (int i = 0; i < objects.size(); i++)
		{
			objects.get(i).tick(objects);
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
		Collections.shuffle(textureList);
		addObject(new CenterBlock(grid, textureList.get(0)));
	}
	
	/**
	 * Adds a texture to the current texture list for the game's level
	 * @param texture the texture to add
	 */
	public void addTexture(BlockTexture texture)
	{
		if (!textureList.contains(texture))
			textureList.add(texture);
	}
	
	/**
	 * Removes the specified texture from the handler
	 * @param texture the texture to remove
	 */
	public void removeTexture(BlockTexture texture)
	{
		textureList.remove(texture);
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
	 * Removes all textures from the handler
	 */
	public void clearTextures()
	{
		for (int i = 0; i < textureList.size(); i++)
		{
			textureList.remove(i);
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
}