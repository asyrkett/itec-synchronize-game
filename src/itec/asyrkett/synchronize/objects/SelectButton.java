package itec.asyrkett.synchronize.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import itec.asyrkett.synchronize.framework.BufferedImageLoader;
import itec.asyrkett.synchronize.framework.GameObject;
import itec.asyrkett.synchronize.framework.ObjectId;
import itec.asyrkett.synchronize.framework.Texture;

public class SelectButton extends Button
{

	public static final int DEFAULT_SIZE = 64;
	private boolean selected;
	
	public SelectButton(float x, float y, boolean selected)
	{
		super(x, y, -1);
		this.selected = selected;
		this.id = ObjectId.SelectButton;
	}
	
	@Override
	public void update(LinkedList<GameObject> objects)
	{
	}

	@Override
	public void render(Graphics g)
	{
		BufferedImageLoader.drawImage(g, Texture.getRadioButton(selected), (int) x, (int) y);
	}

	@Override
	public Rectangle getBounds()
	{
		return new Rectangle((int) x, (int) y, DEFAULT_SIZE, DEFAULT_SIZE);
	}

	/**
	 * @return the selected
	 */
	public boolean isSelected()
	{
		return selected;
	}

	/**
	 * @param selected the selected to set
	 */
	public void setSelected(boolean selected)
	{
		this.selected = selected;
	}
	
	
}