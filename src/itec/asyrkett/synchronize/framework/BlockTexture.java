package itec.asyrkett.synchronize.framework;

import java.awt.Color;

public enum BlockTexture
{
	RED(0, Color.RED), //new Color(154, 34, 169)
	MAGENTA(1, Color.MAGENTA),
	ORANGE(2, Color.ORANGE),
	YELLOW(3, Color.YELLOW),
	GREEN(4, Color.GREEN),
	CYAN(5, Color.CYAN),
	BLUE(6, Color.BLUE);
	
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