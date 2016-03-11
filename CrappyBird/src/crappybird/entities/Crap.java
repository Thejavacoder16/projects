package crappybird.entities;

public class Crap {

	private int x;
	private int y;
	
	private boolean dead = false;
	
	private double ny;
	
	public Crap(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public double getNY(){
		return ny;
	}
	
	public void setNY(double ny){
		this.ny = ny;
	}
	
	public boolean isDead(){
		return dead;
	}
	
	public void setDead(boolean d){
		this.dead = d;
	}
	
}
