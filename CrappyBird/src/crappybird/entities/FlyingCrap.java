package crappybird.entities;

public class FlyingCrap {

	private int x, y;
	private double nx, ny;
	
	public FlyingCrap(int x, int y, double nx, double ny){
		this.x=x;
		this.y=y;
		this.nx=nx;
		this.ny=ny;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public double getNX(){
		return nx;
	}
	
	public double getNY(){
		return ny;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public void setNX(double nx){
		this.nx = nx;
	}
	
	public void setNY(double ny){
		this.ny = ny;
	}
	
}
