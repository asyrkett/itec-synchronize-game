package itec.asyrkett.synchronize.window;

import itec.asyrkett.synchronize.framework.BufferedImageLoader;
import itec.asyrkett.synchronize.framework.GameMode;
import itec.asyrkett.synchronize.objects.Button;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 * This is an abstract class that renders a certain mode
 * of the game
 */
public abstract class Screen
{
	protected BufferedImage background; //the background of the screen
	protected LinkedList<Button> buttons; //a list of buttons on the screen
	protected GameMode gameMode; //the game mode to render
	
	/**
	 * Constructs a screen of the given mode and default background
	 * @param gameMode the type of game mode to render
	 */
	public Screen(GameMode gameMode)
	{
		this.gameMode = gameMode;
		background = BufferedImageLoader.loadImage("/background.png");
		buttons = new LinkedList<Button>();
	}
	
	/**
	 * The graphics to render to the game
	 * @param g the graphics on which to draw
	 */
	public void render(Graphics g)
	{
		for (int i = 0; i < buttons.size(); i++)
			buttons.get(i).render(g);
	}
	
	/**
	 * Gets the game mode of the screen
	 * @return the game mode
	 */
	public GameMode getGameMode()
	{
		return gameMode;
	}
	
	/**
	 * Adds the specified button to the screen
	 * @param button the button to add to the screen
	 */
	public void addButton(Button button)
	{
		buttons.add(button);
	}
	
	/**
	 * Gets the button with the specified text from the Screen if it exists
	 * @param textureText the button text from the Texture class (Texture.BUTTON_TEXT_MENU, etc.)
	 * @return the button with the specified text from the Screen, null otherwise
	 */
	public Button getButton(int textureText)
	{
		for (int i = 0; i < buttons.size(); i++)
		{
			if (buttons.get(i).getTextureText() == textureText)
				return buttons.get(i);
		}
		return null;
	}
	
	public void setButtonsUnclicked()
	{
		for (int i = 0; i < buttons.size(); i++)
			buttons.get(i).setClicked(false);
	}
	
	public LinkedList<Button> getButtons()
	{
		return buttons;
	}
}
