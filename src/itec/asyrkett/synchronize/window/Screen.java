package itec.asyrkett.synchronize.window;

import itec.asyrkett.synchronize.framework.BufferedImageLoader;
import itec.asyrkett.synchronize.framework.GameMode;
import itec.asyrkett.synchronize.framework.Texture;
import itec.asyrkett.synchronize.objects.Button;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 * This is an abstract class that renders a screen corresponding
 * to the mode of the game
 */
public abstract class Screen
{
	protected BufferedImage background; //the background of the screen
	protected LinkedList<Button> buttons; //a list of buttons on the screen
	protected GameMode gameMode; //the game mode to render
	protected Game game;

	/**
	 * Constructs a screen of the given mode and default background
	 * @param gameMode the type of game mode to render
	 */
	public Screen(Game game, GameMode gameMode)
	{
		this.game = game;
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
		g.setColor(Color.BLACK);
		g.drawImage(background, 0, 0, Game.WIDTH, Game.HEIGHT, null);	
		drawButtons(g);
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

	/**
	 * Returns a list of all the buttons displayed on the screen
	 * @return a list of the buttons on the screen
	 */
	public LinkedList<Button> getButtons()
	{
		return buttons;
	}

	/**
	 * Sets a button of the screen to its hovered state
	 * if the mouse hovers over it
	 * @param e the mouse event passed
	 */
	public void mouseMoved(MouseEvent e)
	{
		setButtonsUnhovered();
		for (int i = 0; i < buttons.size(); i++)
		{
			Button button = buttons.get(i);
			if (button.getBounds().contains(e.getPoint()))
			{
				button.setHovered(true);
				break;
			}
		}
	}

	/**
	 * Sets a button of the screen to its pressed state
	 * if it is pressed by the mouse
	 * @param e the mouse event passed
	 */
	public void mousePressed(MouseEvent e)
	{
		for (int i = 0; i < buttons.size(); i++)
		{
			Button button = buttons.get(i);
			if (button.getBounds().contains(e.getPoint()))
			{
				button.setPressed(true);
				break;
			}
		}
	}

	/**
	 * Completes the specified action when a button
	 * is released by the mouse
	 * @param e the mouse event passed
	 */
	public void mouseReleased(MouseEvent e)
	{
		setButtonsUnpressed();
		for(int i = 0; i < buttons.size(); i++)
		{
			Button button = buttons.get(i);
			if (button.getBounds().contains(e.getPoint()))
			{
				switch(button.getTextureText())
				{
				case Texture.BUTTON_TEXT_PLAY:
					game.setGameMode(GameMode.PLAY);
					break;
				case Texture.BUTTON_TEXT_HELP:
					game.setGameMode(GameMode.HELP);
					break;
				case Texture.BUTTON_TEXT_MENU:
					game.setGameMode(GameMode.MENU);
					break;
				case Texture.BUTTON_TEXT_LEVEL:
					game.setGameMode(GameMode.LEVEL_SELECTION);
					break;
				case Texture.BUTTON_TEXT_OPTIONS:
					game.setGameMode(GameMode.OPTIONS);
					break;
				case Texture.BUTTON_TEXT_CANCEL:
					game.setGameMode(game.getPreviousGameMode());
					break;
				case Texture.BUTTON_TEXT_QUIT:
					System.exit(1);
					break;
				case Texture.BUTTON_TEXT_RESET:
					game.resetLevel();
					break;
				}
				break;
			}
		}
	}
	
	/**
	 * Sets all of the buttons to their unpressed state
	 */
	protected void setButtonsUnpressed()
	{
		for (int i = 0; i < buttons.size(); i++)
			buttons.get(i).setPressed(false);
	}

	/**
	 * Sets all of the buttons to their unhovered state
	 */
	protected void setButtonsUnhovered()
	{
		for (int i = 0; i < buttons.size(); i++)
			buttons.get(i).setHovered(false);
	}
	
	protected void drawButtons(Graphics g)
	{
		for (int i = 0; i < buttons.size(); i++)
			buttons.get(i).render(g);
	}
}
