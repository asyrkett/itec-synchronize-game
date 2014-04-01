package itec.asyrkett.synchronize.objects;

import itec.asyrkett.synchronize.framework.BufferedImageLoader;
import itec.asyrkett.synchronize.framework.GameObject;
import itec.asyrkett.synchronize.framework.ObjectId;
import itec.asyrkett.synchronize.framework.Texture;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Button extends GameObject
{
	public static final int DEFAULT_WIDTH = 128;
	public static final int DEFAULT_HEIGHT = 64;
	
	private int textureText;
	private boolean pressed;
	private boolean hovered;

	/**
	 * Constructs a button object with the specified text
	 * @param x the x coordinate of the button's upper left corner
	 * @param y the y coordinate of the button's upper left corner
	 * @param textureText the text of the button from the Texture class (Texture.BUTTON_TEXT_MENU, etc.)
	 */
	public Button(float x, float y, int textureText)
	{
		super(x, y, ObjectId.Button);
		this.textureText = textureText;
		pressed = false;
		hovered = false;
	}
	
	public void update(LinkedList<GameObject> objects)
	{
	}

	public void render(Graphics g)
	{
		BufferedImageLoader.drawImage(g, Texture.getButtonBase(), (int) x, (int) y);
		if (pressed)
		{
			BufferedImageLoader.drawImage(g, Texture.getButtonText(textureText, false), (int) x, (int) y);
			BufferedImageLoader.drawImage(g, Texture.getButtonText(textureText, true), (int) x, (int) y);
		}
		else
			BufferedImageLoader.drawImage(g, Texture.getButtonText(textureText, hovered), (int) x, (int) y);
	}

	public Rectangle getBounds()
	{
		BufferedImage text = Texture.getButtonText(textureText, pressed);
		return new Rectangle((int) x, (int)y, text.getWidth(), text.getHeight());
	}

	/**
	 * Gets the text the button displays from the Texture class (Texture.BUTTON_TEXT_MENU, etc.)
	 * @return the text texture the button displays
	 */
	public int getTextureText()
	{
		return textureText;
	}

	/**
	 * Sets the text the button is to display from the Texture class
	 * @param textureText the text texture to display (Texture.BUTTON_TEXT_MENU, etc.)
	 */
	public void setTextureText(int textureText)
	{
		this.textureText = textureText;
	}

	/**
	 * Returns if the button is pressed by a mouse
	 * @return true if the button is pressed, false otherwise
	 */
	public boolean isPressed()
	{
		return pressed;
	}

	/**
	 * Sets if the button is pressed by a mouse
	 * @param pressed the pressed state to set
	 */
	public void setPressed(boolean pressed)
	{
		this.pressed = pressed;
	}

	/**
	 * Returns if the button is hovered over by a mouse
	 * @return true if hovered over by a mouse, false otherwise
	 */
	public boolean isHovered() {
		return hovered;
	}

	/**
	 * Sets if the button is hovered over by a mouse
	 * @param hovered the hoevered state to set
	 */
	public void setHovered(boolean hovered) {
		this.hovered = hovered;
	}
}
