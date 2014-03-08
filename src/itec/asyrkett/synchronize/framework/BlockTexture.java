package itec.asyrkett.synchronize.framework;

import java.awt.Color;

/**
 * This class enum represents the possible textures
 * for a block and their base color
 */
public enum BlockTexture
{
	RED(0, Color.RED), //ff0000, RGB: 255, 0, 0
	MAGENTA(1, Color.MAGENTA), //ff00ff, RGB: 255, 0, 255
	ORANGE(2, new Color(0xFF, 0x78, 0x00)), //RGB: 255, 120, 0
	YELLOW(3, Color.YELLOW), //ff00ff, RGB: 255, 0, 255
	GREEN(4, Color.GREEN), //00ff00, RGB: 0, 255, 0
	CYAN(5, Color.CYAN), //00ffff, RGB: 0, 255, 255
	BLUE(6, Color.BLUE); //0000ff, RGB: 0, 0, 255
	
	private int type;
	private Color baseColor;
	
	/**
	 * Constructs a block texture of the given type and base color
	 * @param type
	 * @param baseColor
	 */
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