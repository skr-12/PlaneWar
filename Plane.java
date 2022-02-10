package PlaneWar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Plane {
	int x_location;
	int y_location;
	public static final int WIDTH = 30;
	public static final int HEIGHT = 30;
	private static final int x_weiyi=15;
	private static final int y_weiyi=15;
	boolean U = false,D = false,L = false,R = false;
	boolean live = true;
	MainConsole mc;

	public Plane(int x,int y,MainConsole mc) {
		this.x_location = x;
		this.y_location = y;
		this.mc = mc;
	}
	
	public void paint(Graphics g) {
    	if(!live) {return;}
		g.setColor(Color.BLACK);	
		g.fillRect(x_location, y_location, WIDTH, HEIGHT);
		this.move();
		shot();
	}
	
	public void move() {
		if(U && !D && !L && !R) {
			y_location = y_location - y_weiyi;
		}
		else if(!U && D && !L && !R) {
			y_location = y_location + y_weiyi;
		}
		else if(!U && !D && L && !R) {
			x_location = x_location - x_weiyi;
		}
		else if(!U && !D && !L && R) {
			x_location = x_location + x_weiyi;
		}
		
		if(x_location < 0) {
			x_location = 0;
		}
		if(y_location < 0) {
			y_location = 20;
		}
		if(x_location + Plane.WIDTH > 300) {
			x_location = 300 - Plane.WIDTH;
		}
		if(y_location + Plane.HEIGHT > 500) {
			y_location = 500 - Plane.HEIGHT;
		}	
	}
	
	public Bullet shot() {
		if(!live) {
			return null;
		}
//希望通过控制炮弹装填的速度来使炮弹不那么密集，过20次循环装一次		
//		int xunhuan = 0;
//		while(true) {
//			xunhuan++;
//		if(xunhuan % 10 == 0) {
		int x = this.x_location + Plane.WIDTH/2 - Bullet.WIDTH/2;
		int y = this.y_location + Plane.HEIGHT/2 - Bullet.HEIGHT/2;
		Bullet m = new Bullet(x,y,mc);
		mc.pd.add(m);	
		return m;
//			}
//		}
	}
	
	public void keyPressed(KeyEvent e) {
		int k = e.getKeyCode();
		if(k == KeyEvent.VK_UP) {
			U = true;
		}
		else if(k == KeyEvent.VK_DOWN) {
			D = true;
		}
		else if(k == KeyEvent.VK_LEFT) {
			L = true;
		}
		else if(k == KeyEvent.VK_RIGHT) {
			R = true;
		}
	}

	public void keyReleased(KeyEvent e) {
		int k = e.getKeyCode();
		if(k == KeyEvent.VK_UP) {
			U = false;
		}
		else if(k == KeyEvent.VK_DOWN) {
			D = false;
		}
		else if(k == KeyEvent.VK_LEFT) {
			L = false;
		}
		else if(k == KeyEvent.VK_RIGHT) {
			R = false;
		}

	}
	
	public boolean isLive() {
		return live;
	}
	
	public void setLive(boolean live) {
		this.live = live;
	} 
	
	public Rectangle getRectange() {
		Rectangle rp = new Rectangle(x_location,y_location,WIDTH,HEIGHT);
		return  rp;
	}
	
	public boolean knock(EnemyPlane a) {
		if( this.getRectange().intersects(a.getRectange())) {
			a.setLive(false);
			this.live = false;
			return true;
		}
		return false;
	}
	
	public boolean knocks(List<EnemyPlane> dj) {
		for(int i=0;i<dj.size();i++) {
			if(knock(dj.get(i)))
				return true;
		}
		return false;
	}
}
