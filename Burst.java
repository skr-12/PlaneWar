package PlaneWar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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
		try {
			BufferedImage image = ImageIO.read(new File("img\\Burst.png"));
			g.drawImage(image, x, y, fireworks[j], fireworks[j], mc);
		} catch(IOException e) {
			e.printStackTrace();
		}
//		g.setColor(Color.YELLOW);
//		g.fillOval(x, y, fireworks[j], fireworks[j]);
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
