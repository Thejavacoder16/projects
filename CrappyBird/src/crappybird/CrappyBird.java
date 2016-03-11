package crappybird;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import crappybird.entities.Bird;
import crappybird.entities.Crap;
import crappybird.entities.FlyingCrap;
import crappybird.entities.Pipe;
import crappybird.graphics.CrappyGraphics;

public class CrappyBird {

	Bird bird;
	CrappyGraphics cg;
	Thread game_loop;
	Boolean running = true;
	boolean playing = false;
	boolean gameover = false;
	boolean easteregg = false;
	Random random = new Random();
	
	ArrayList<Pipe> pipes = new ArrayList<Pipe>();
	ArrayList<Pipe> pipes_flipped = new ArrayList<Pipe>();
	ArrayList<Crap> craps = new ArrayList<Crap>();
	ArrayList<FlyingCrap> flyingcraps = new ArrayList<FlyingCrap>();
	public int score = 0;
	
	public static void main(String args[]){
		new CrappyBird();
	}
	
	public CrappyBird(){
		cg = new CrappyGraphics();
		init();
	}
	
	public void init(){
		//defaults
		gameover = false;
		playing = false;
		running = true;
		
		genPipes();
		
		bird = new Bird(320);
		game_loop = new Thread(){
			public void run(){
				long lastTime = System.nanoTime();
				final double ns = 1000000000.0 / 100.0;
				double delta = 0;
				int lutime = (int) System.currentTimeMillis();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
				}
				while(running){
					long now = System.nanoTime();
					int nutime = (int) System.currentTimeMillis();
					
					if(nutime - lutime >= 1000){
						lutime = nutime;
					}
					
					delta += (now - lastTime) / ns;
					lastTime = now;
					while(delta >= 1){
						tick();
						delta--;
					}
					render();
				}
			}
		};
		game_loop.start();
	}
	
	public void genPipes(){
		pipes = new ArrayList<Pipe>();
		pipes_flipped = new ArrayList<Pipe>();
		for(int i=0;i<2000;i++){
			int r = random.nextInt(5);
			
			
			switch(r){
			case 0:{
				pipes.add(new Pipe(400+(i*200), 440));
				pipes_flipped.add(new Pipe(400+(i*200), 280, true));
				break;
			}
			case 1:{
				pipes.add(new Pipe(400+(i*200), 280));
				pipes_flipped.add(new Pipe(400+(i*200), 120, true));
				break;
			}
			case 2:{
				pipes.add(new Pipe(400+(i*200), 320));
				pipes_flipped.add(new Pipe(400+(i*200), 160, true));
				break;
			}
			case 3:{
				pipes.add(new Pipe(400+(i*200), 480));
				pipes_flipped.add(new Pipe(400+(i*200), 320, true));
				break;
			}
			case 4:{
				pipes.add(new Pipe(400+(i*200), 380));
				pipes_flipped.add(new Pipe(400+(i*200), 220, true));
				break;
			}
			}
		}
		craps = new ArrayList<Crap>();
	}
	
	public void tick(){
		if(playing){
			cg.xOffset+=1;
			if(bird.getNY() < 11){
				bird.setNY(bird.getNY()+.1);
			}
			ArrayList<Crap> dead = new ArrayList<Crap>();
			for(Crap c:craps){
				if(c.getY() >= 641){
					dead.add(c);	
				} else {
				if(c.getNY() < 11){
					c.setNY(c.getNY()+.1);
				}
				c.setY((int) (c.getY()+c.getNY()));
				}
			}
			for(Crap c:dead){
				craps.remove(c);
			}
			dead.clear();
			
			//FlyingCraps
			ArrayList<FlyingCrap> dead2 = new ArrayList<FlyingCrap>();
			for(FlyingCrap c2:flyingcraps){
				if(c2.getY() >= 641){
					dead2.add(c2);	
				} else {
					c2.setX((int)(c2.getX()+c2.getNX()));
					c2.setY((int) (c2.getY()+c2.getNY()));
				}
			}
			for(Crap c:dead){
				craps.remove(c);
			}
			dead.clear();
			//End of flyingcraps
			
			
			if(cg.spacebar){
				bird.setNY(-4);
				cg.spacebar = false;
				if(easteregg){
					flyingcraps.add(new FlyingCrap((int)cg.xOffset+120, (int)bird.getY(), 0, 0));
					flyingcraps.add(new FlyingCrap((int)cg.xOffset+120, (int)bird.getY(), 0, -1));
					flyingcraps.add(new FlyingCrap((int)cg.xOffset+120, (int)bird.getY(), 2, 0));
					flyingcraps.add(new FlyingCrap((int)cg.xOffset+120, (int)bird.getY(), 2, 1));
					flyingcraps.add(new FlyingCrap((int)cg.xOffset+120, (int)bird.getY(), 0, 1));
					flyingcraps.add(new FlyingCrap((int)cg.xOffset+120, (int)bird.getY(), 1, 1));
					flyingcraps.add(new FlyingCrap((int)cg.xOffset+120, (int)bird.getY(), 1, -1));
					flyingcraps.add(new FlyingCrap((int)cg.xOffset+120, (int)bird.getY(), 2, -1));
				} else {
					craps.add(new Crap((int)cg.xOffset+104, (int)bird.getY()));
				}
			}
			bird.setY(bird.getY()+bird.getNY());
			
			if(bird.getY()>640){
				game_over();
			}
			if(bird.getY()<0){
				bird.setY(0);
			}
			
			for(Pipe p : pipes){
				if(p.intersects(new Rectangle((int)cg.xOffset+104,(int)bird.getY(),32,32))){
					game_over();
				}
			}
			for(Pipe p : pipes_flipped){
				if(p.intersects(new Rectangle((int)cg.xOffset+104,(int)bird.getY(),32,32))){
					game_over();
				}
			}
			score = (int) (((cg.xOffset+(104+32)-200))/200);
			if(score < 0) score = 0;
		} else {
			if(cg.ee >= 20){
				easteregg= true;
			}
		}
		if(cg.spacebar&&!playing){
			playing = true;
			gameover = false;
			if(cg.ee < 20){
				cg.ee = 0;
			}
		}
	}
	
	public void game_over(){
		gameover = true;
		render();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		bird = new Bird(320);

		craps = new ArrayList<Crap>();
		flyingcraps = new ArrayList<FlyingCrap>();
		cg.xOffset = 0;
		gameover = false;
		playing = false;
		genPipes();
	}
	
	public void render(){
		if(gameover){
			cg.drawTextureExact(CrappyGraphics.background, 0, 0);
			cg.drawTextureExact(CrappyGraphics.gameover, 20, 290);
		} else {
		cg.drawTextureExact(CrappyGraphics.background, 0, 0);
		for(Pipe p:pipes){
			cg.drawTexture(CrappyGraphics.pipe, p.getX(), p.getY());
		}
		for(Pipe p:pipes_flipped){
			cg.drawTexture(CrappyGraphics.pipe_flipped, p.getX(), p.getY()-640);
		}
		for(Crap c:craps){
			cg.drawTexture(CrappyGraphics.crap, c.getX(), c.getY());
		}
		for(FlyingCrap fc:flyingcraps){
			cg.drawTexture(CrappyGraphics.crap, fc.getX(), fc.getY());
		}
		cg.drawTextureExact(CrappyGraphics.bird_texture, 104, (int)bird.getY());
		if(!playing){
			cg.drawTextureExact(CrappyGraphics.pressspace, 20, 540);
		}
		}
		cg.drawTextureExact(CrappyGraphics.escape, 0, 0);
		cg.drawScore(score);
		cg.render();
	}
	
}
