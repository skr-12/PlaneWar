import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.List;

//bullet增加功能，子弹可以穿墙

public class Bullet {
	int x_location=150,y_location=420;
	private int width = 10;
	private int height = 10;
	private static final int X_PD = 25;
	private static final int Y_PD = 25;
	boolean U = false,D = false,L = false,R = false;
	boolean live = true;
	public MainConsole mc;
	Rectangle rpd = new Rectangle(x_location,y_location,width,height);
	
	public Bullet(int x,int y,MainConsole mc) {
		this.x_location = x;
		this.y_location = y;
		this.mc = mc;
	}
	
	public void paint(Graphics g) {
//		if(!live) {
//			mc.pd.remove(this);
//			return;
//		}
		g.setColor(Color.RED);
		g.fillOval(x_location - width/2, y_location - height/2, width, height);
		move();
	}
	
	public void move() {		
		y_location = y_location - Y_PD;
		if(y_location<0 || y_location>500) {
			live = false;
			mc.pd.remove(this);
		}
	}
	
	public boolean isLive() {
		return live;
	}
	
	public void setLive(boolean live) {
		this.live = live;
	} 
	
	public Rectangle getRectange() {
		return  rpd;
	}
	
	public boolean zhuangJi(EnemyPlane a) {
		if(this.rpd.intersects(a.rd)) {
			a.setLive(false);
			this.live = false;
			return true;
		}	
		return false;
	}
	
	public boolean zhuangJi1(List<EnemyPlane> a1) {
		for(int i=0;i<a1.size();i++) {
			if(zhuangJi(a1.get(i)))
				return true;
		}
		return false;
	}
}
