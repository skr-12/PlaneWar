import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;
import java.util.Random;

public class EnemyPlane {
	int x,y;
	public static final int Y_DJ=15;
	public static final int WIDTH = 15;
	public static final int HEIGHT = 20;
	MainConsole mc;
	public Random r;
	boolean live = true;
	enum size{big,midile,sumall};
	
	public EnemyPlane(int x,int y,MainConsole mc) {
		this.x = x;
		this.y = y;
		this.mc = mc;
	}
	
	public void paint(Graphics g) {
		if(!live) {
			mc.dj.remove(this);
			return;
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
	
	public Rectangle getRectange() {
		Rectangle rd = new Rectangle(x,y,WIDTH,HEIGHT);
		return  rd;
	}
	public boolean knock(Bullet a) {
		if(this.getRectange().intersects(a.getRectange())) {
			a.setLive(false);
			this.live = false;
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
