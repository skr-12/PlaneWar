package PlaneWar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;
import java.util.Random;

public class EnemyPlane {
	int x,y;
	public static final int Y_DJ=15;
//	public static final 
	int WIDTH = 15;
//	public static final 
	int HEIGHT = 20;
	MainConsole mc;
	public Random r;
	boolean live = true;
	enum EnemyPlaneSize{big,medium,small};
	EnemyPlaneSize size = EnemyPlaneSize.small;
	int BigLife = 5;
	int MediumLife = 3;
	int SmallLife = 1;
	
	public EnemyPlane(int x,int y,MainConsole mc) {
		this.x = x;
		this.y = y;
		this.mc = mc;
	}
			
	public EnemyPlane(int x,int y,MainConsole mc,EnemyPlaneSize size) {
		this.x = x;
		this.y = y;
		this.mc = mc;
		this.size = size;
		
	}
	
	public void paint(Graphics g) {
		if(!live) {
			mc.dj.remove(this);
			return;
		}
		if(size == EnemyPlaneSize.small) {
			this.WIDTH = 15;
			this.HEIGHT = 20;
		}
		
		if(size == EnemyPlaneSize.medium) {
			this.WIDTH = 20;
			this.HEIGHT = 30;
		}
		
		if(size == EnemyPlaneSize.big) {
			this.WIDTH = 50;
			this.HEIGHT = 60;
		}
		g.setColor(Color.GREEN);
		g.fillOval(x, y, WIDTH, HEIGHT);
		move();
	}
	
	public void move() {
		y = y + Y_DJ;
	}
	
	public boolean isLive() {
		return live;
	}
	
	public void setLive(boolean live) {
		this.live = live;
	}
	
	public void setLife1(int life) {
		this.BigLife = life;	
	}
	
	public int getLife1() {
		return BigLife;
	}
	
	public void setLife2(int life) {	
		this.MediumLife = life;
	}
	
	public int getLife2() {
		return MediumLife;
	}
	
	public void setLife3(int life) {	
		this.SmallLife = life;
	}
	
	public int getLife3() {
		return SmallLife;
	}
	
	public Rectangle getRectange() {
		Rectangle rd = new Rectangle(x,y,WIDTH,HEIGHT);
		return  rd;
	}
	public boolean knock(Bullet a) {
		if(this.getRectange().intersects(a.getRectange())) {		
			BigLife--;
			MediumLife--;
			SmallLife--;
			a.setLive(false);
			if(BigLife == 0 || MediumLife == 0 || SmallLife == 0) {
			this.live = false;
			}
			return true;
		}
		return false;
	}
	
	public boolean knocks(List<Bullet> pd) {
		for(int i=0;i<pd.size();i++) {
			if(knock(pd.get(i)))
				return true;
		}
		return false;
	}

}
