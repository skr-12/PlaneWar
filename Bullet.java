package PlaneWar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

public class Bullet {
	int x_location,y_location;
	public static int WIDTH = 20;
	public static int HEIGHT = 20;
	private static final int Y_PD = 50;
	boolean U = false,D = false,L = false,R = false;
	boolean live = true;
	public MainConsole mc;
	int BigLife = 5;
	int MediumLife = 3;
	int SmallLife = 1;
	
	public Bullet(int x,int y,MainConsole mc) {
		this.x_location = x;
		this.y_location = y;
		this.mc = mc;
	}
	
	public void paint(Graphics g) {
		if(!live) {
			mc.pd.remove(this);
			return;
		}
		try {
			BufferedImage image = ImageIO.read(new File("img\\Bullet.jpeg"));
			g.drawImage(image, x_location, y_location, WIDTH, HEIGHT, mc);
		} catch(IOException e) {
			e.printStackTrace();
		}
		move();
	}
	
	public void move() {
		y_location = y_location - Y_PD;
		if(y_location<0 || y_location>500) {
			this.live = false;
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
		Rectangle rpd = new Rectangle(x_location,y_location,WIDTH,HEIGHT);
		return  rpd;
	}
	
	public boolean knock(EnemyPlane a) {
		if(this.getRectange().intersects(a.getRectange())) {
//			BigLife--;
//			MediumLife--;
//			SmallLife--;
//			if(BigLife == 0 || MediumLife == 0 || SmallLife == 0) {
//			a.setLive(false);
//			}
			this.live = false;
			Burst u = new Burst(x_location,y_location,mc);
			mc.bz.add(u);
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
