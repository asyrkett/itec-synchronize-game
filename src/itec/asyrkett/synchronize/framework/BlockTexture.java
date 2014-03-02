package itec.asyrkett.synchronize.framework;

import java.awt.Color;

public enum BlockTexture
{
	PURPLE(0, new Color(154, 34, 169)),
	RED(1, Color.RED),
	CYAN(2, Color.CYAN),
	GREEN(3, Color.GREEN),
	ORANGE(4, Color.ORANGE),
	YELLOW(5, Color.YELLOW);
	
	private int type;
	private Color baseColor;
	
	private BlockTexture(int type, Color baseColor)
	{
		this.type = type;
		this.baseColor = baseColor;
	}
	
	public int getType()
	{
		return type;
	}
	
	public Color getBaseColor()
	{
		return baseColor;
	}
}