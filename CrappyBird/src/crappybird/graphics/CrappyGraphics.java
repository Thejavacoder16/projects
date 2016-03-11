package crappybird.graphics;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import crappybird.graphics.textures.Texture;

public class CrappyGraphics {

	public static Texture bird_texture = new Texture("bird.png");
	public static Texture pipe = new Texture("pipe.png");
	public static Texture pipe_flipped = new Texture("pipe2.png");
	public static Texture background = new Texture("backdrop.png");
	public static Texture gameover = new Texture("gameover.png");
	public static Texture pressspace = new Texture("pressspace.png");
	public static Texture crap = new Texture("crap.png");
	public static Texture escape = new Texture("escape.png");
	public static Texture n0 = new Texture("n0.png");
	public static Texture n1 = new Texture("n1.png");
	public static Texture n2 = new Texture("n2.png");
	public static Texture n3 = new Texture("n3.png");
	public static Texture n4 = new Texture("n4.png");
	public static Texture n5 = new Texture("n5.png");
	public static Texture n6 = new Texture("n6.png");
	public static Texture n7 = new Texture("n7.png");
	public static Texture n8 = new Texture("n8.png");
	public static Texture n9 = new Texture("n9.png");
	
	public boolean spacebar = false;
	public int ee = 0;
	
	public double xOffset = 0;
	private JFrame frame;
	private int[] pixels;
	private BufferedImage image;
	private Graphics graphics;
	
	public CrappyGraphics(){
		frame = new JFrame("Crappy Bird");
		frame.setSize(240, 640);
		frame.setDefaultCloseOperation(3);
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		frame.addKeyListener(new KeyListener(){

			@Override
			public void keyTyped(KeyEvent e) {
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
					System.exit(0);
				}
				if(e.getKeyCode() == KeyEvent.VK_SPACE){
					spacebar = true;
				}
				if(e.getKeyCode() == KeyEvent.VK_E){
					ee++;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_SPACE){
					spacebar = false;
				}
			}
			
		});

		frame.setVisible(true);
		
		graphics = frame.getGraphics();

		image = new BufferedImage(240, 640, BufferedImage.TYPE_INT_RGB);
		pixels =((DataBufferInt)image.getRaster().getDataBuffer()).getData();
		for(int i=0;i<pixels.length;i++){
			pixels[i] = 0xFFFFFFFF;
		}
	}
	
	public int[] getPixels(){
		return pixels;
	}
	
	public void setPixels(int[] pixels){
		this.pixels = pixels;
	}
	
	public BufferedImage getImage(){
		return image;
	}
	
	public void render(){
		graphics.drawImage(image, 0, 0, null);
	}
	
	public void drawTextureExact(Texture texture, int xp, int yp){
		for(int y=0;y<texture.getHeight();y++){
			for(int x=0;x<texture.getWidth();x++){
				if((xp+x) < 0 || (xp+x) >=240 || (yp+y) < 0 || (yp+y) >= 640)break;
				if(texture.getPixels()[x+(y*texture.getWidth())] != 0xFFFF00DC){
					pixels[(xp+x)+(240*(yp+y))] = texture.getPixels()[y*texture.getWidth()+x];
				}
			}
		}
	}
	
	public void drawTexture(Texture texture, int xp, int yp){
		int xa = (int) (xp - xOffset);
		for(int y=0;y<texture.getHeight();y++){
			for(int x=0;x<texture.getWidth();x++){
				if((xa+x) < 0 || (xa+x) >=240 || (yp+y) < 0 || (yp+y) >= 640)break;
				if(texture.getPixels()[x+(y*texture.getWidth())] != 0xFFFF00DC){
					pixels[(xa+x)+(240*(yp+y))] = texture.getPixels()[y*texture.getWidth()+x];
				}
			}
		}
	}
	
	public void drawTextureCentered(Texture texture, int yp){
		int w = texture.getWidth();
		int x = (640/2)-(w/2);
		drawTextureExact(texture, x, yp);
	}
	
	public void drawScore(int score){
		char[] nums = String.valueOf(score).toCharArray();
		int width = nums.length*25;
		int[] pixels = new int[50*width];
		for(int i=0;i<nums.length;i++){
			Texture texture = CrappyGraphics.n0;
			
			switch(Integer.parseInt(String.valueOf(nums[i]))){
			case 0:{
				texture = CrappyGraphics.n0;
				break;
			}
			case 1:{
				texture = CrappyGraphics.n1;
				break;
			}
			case 2:{
				texture = CrappyGraphics.n2;
				break;
			}
			case 3:{
				texture = CrappyGraphics.n3;
				break;
			}
			case 4:{
				texture = CrappyGraphics.n4;
				break;
			}
			case 5:{
				texture = CrappyGraphics.n5;
				break;
			}
			case 6:{
				texture = CrappyGraphics.n6;
				break;
			}
			case 7:{
				texture = CrappyGraphics.n7;
				break;
			}
			case 8:{
				texture = CrappyGraphics.n8;
				break;
			}
			case 9:{
				texture = CrappyGraphics.n9;
				break;
			}
			}
			
			int xa = 25*i;
			drawTextureExact(texture, 100+xa, 40);
		}
	}
	
}
