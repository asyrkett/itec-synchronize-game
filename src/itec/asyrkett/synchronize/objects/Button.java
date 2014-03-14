package itec.asyrkett.synchronize.objects;

import itec.asyrkett.synchronize.framework.BufferedImageLoader;
import itec.asyrkett.synchronize.framework.GameObject;
import itec.asyrkett.synchronize.framework.ObjectId;
import itec.asyrkett.synchronize.framework.Texture;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Button extends GameObject
{
	private int textureText;
	private boolean clicked;

	/**
	 * Constructs a button object with the specified text
	 * @param x the x coordinate of the button's upper left corner
	 * @param y the y coordinate of the button's uppwer left corner
	 * @param textureText the text of the button from the Texture class (Texture.BUTTON_TEXT_MENU, etc.)
	 */
	public Button(float x, float y, int textureText)
	{
		super(x, y, ObjectId.Button);
		this.textureText = textureText;
		clicked = false;
	}
	
	public void tick(LinkedList<GameObject> objects)
	{
	}

	public void render(Graphics g)
	{
		BufferedImageLoader.drawImage(g, Texture.getButtonBase(), (int) x, (int) y);
		BufferedImageLoader.drawImage(g, Texture.getButtonText(textureText, clicked), (int) x, (int) y);
	}

	public Rectangle getBounds()
	{
		BufferedImage text = Texture.getButtonText(textureText, clicked);
		return new Rectangle((int) x, (int)y, text.getWidth(), text.getHeight());
	}

	/**
	 * @return the textureText
	 */
	public int getTextureText()
	{
		return textureText;
	}

	/**
	 * @param textureText the textureText to set
	 */
	public void setTextureText(int textureText)
	{
		this.textureText = textureText;
	}

	/**
	 * @return the clicked
	 */
	public boolean isClicked()
	{
		return clicked;
	}

	/**
	 * @param clicked the clicked to set
	 */
	public void setClicked(boolean clicked)
	{
		this.clicked = clicked;
	}
	
}
