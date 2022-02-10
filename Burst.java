package PlaneWar;

import java.awt.Color;
import java.awt.Graphics;

public class Burst {
	int x,y;
	int WIDTH,HEIGHT;
	int[] fireworks = {6,16,26,36,26,6,0};
	boolean live = true;
	int j = 0;
	public MainConsole mc;
	
	public Burst(int x,int y,MainConsole mc) {
		this.x = x;
		this.y = y;
		this.mc = mc;
	}
	
	public void paint(Graphics g) {
		if(!live) {
			mc.bz.remove(this);
		}
		if(j == fireworks.length) {
			this.setLive(false);
		}
		g.setColor(Color.YELLOW);
		g.fillOval(x, y, fireworks[j], fireworks[j]);
		if(j<6) {
		j++;
		}
	}
	
	public boolean isLive() {
		return live;
	}
	
	public void setLive(boolean live) {
		this.live = live;
	}
}
