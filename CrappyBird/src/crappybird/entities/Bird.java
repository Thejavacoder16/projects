package crappybird.entities;


public class Bird {

	private double y;
	private double ny = 0;
	
	public Bird(double y){
		this.y = y;
	}
	
	
	public double getY(){
		return y;
	}
	
	
	public void setY(double y){
		this.y = y;
	}
	
	
	public double getNY(){
		return ny;
	}
	
	
	public void setNY(double ny){
		this.ny = ny;
	}
}
