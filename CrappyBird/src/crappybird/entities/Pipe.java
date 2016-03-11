package crappybird.entities;

import java.awt.Rectangle;

public class Pipe {

	private int x,y;
	private boolean flipped = false;
	
	public Pipe(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Pipe(int x, int y, boolean flipped){
		this.x = x;
		this.y = y;
		this.flipped = flipped;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public boolean isFlipped(){
		return flipped;
	}
	
	public boolean intersects(Rectangle r){
		if(flipped){
			return r.intersects(new Rectangle(x,y-640,64, 640));
		} else {
			return r.intersects(new Rectangle(x,y,64,640));
		}
	}
	
}
