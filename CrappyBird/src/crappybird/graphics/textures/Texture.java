package crappybird.graphics.textures;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Texture {

	private int[] pixels;
	private int width, height;
	private BufferedImage image;
	
	public Texture(String file){
		try {
			image = ImageIO.read(Texture.class.getResource(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		width = image.getWidth();
		height = image.getHeight();
		pixels = new int[width*height];
		image.getRGB(0, 0, width, height, pixels, 0, width);
	}
	
	public Texture(int[] tpixels, int width){
		this.width = width;
		this.height = tpixels.length/width;
		pixels = new int[width*height];
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pixels =((DataBufferInt)image.getRaster().getDataBuffer()).getData();
			pixels = tpixels;
	}
	
	public int[] getPixels(){
		return pixels;
	}
	
	public void setPixels(int[] pixels){
		this.pixels = pixels;
	}
	
	public void setWidth(int width){
		this.width = width;
	}
	
	public void setHeight(int height){
		this.height = height;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
}
