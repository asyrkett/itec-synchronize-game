package itec.asyrkett.synchronize.framework;

import java.awt.image.BufferedImage;

/**
 * This class represents a sprite sheet filled with
 * different textures for game objects
 */
public class SpriteSheet
{
	private BufferedImage image;
	private int rowHeight;
	private int colWidth;
	
	/**
	 * Constructs a sprite sheet for the specified image
	 * @param image the sprite sheet
	 * @param rowHeight the number of pixels between each row of the sprite sheet
	 * @param colWidth the number of pixels between each column of the sprite sheet
	 */
	public SpriteSheet(BufferedImage image, int rowHeight, int colWidth)
	{
		this.image = image;
		this.rowHeight = rowHeight;
		this.colWidth = colWidth;
	}
	
	/**
	 * Gets the sprite from the specified row and column of the sprite sheet
	 * @param row the row to grab from the sprite sheet
	 * @param column the column to grab from the sprite sheet
	 * @param width the width of the sprite to grab
	 * @param height the height of the sprite to grab
	 * @return the image of the sprite
	 */
	public BufferedImage grabImage(int row, int column, int width, int height)
	{
		return image.getSubimage(column * colWidth, row * rowHeight, width, height);
	}

	/**
	 * @return the image
	 */
	public BufferedImage getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
	}

	/**
	 * @return the rowHeight
	 */
	public int getRowHeight() {
		return rowHeight;
	}

	/**
	 * @param rowHeight the rowHeight to set
	 */
	public void setRowHeight(int rowHeight) {
		this.rowHeight = rowHeight;
	}

	/**
	 * @return the colWidth
	 */
	public int getColWidth() {
		return colWidth;
	}

	/**
	 * @param colWidth the colWidth to set
	 */
	public void setColWidth(int colWidth) {
		this.colWidth = colWidth;
	}
}