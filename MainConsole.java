package PlaneWar;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import PlaneWar.EnemyPlane.EnemyPlaneSize;

public class MainConsole extends Frame {
	public static final int WIDTH = 300;
	public static final int HEIGHT = 500;
	Plane p = new Plane(150,450,this);
	List<Bullet> pd = new ArrayList<Bullet>();
	List<EnemyPlane> dj = new ArrayList<EnemyPlane>();
	List<Burst> bz = new ArrayList<Burst>();
	MainConsole mc;
	Random random = new Random();
	private Image iBuffer = null;
	private Graphics gBuffer;
//	DiJi dj = new DiJi(150,50);
//	PaoDan pd = new PaoDan();
	public MainConsole() {
//		for(int i=0;i<8;i++) {
//			dj.add(new EnemyPlane(30*(2+i),50,this,EnemyPlaneSize.big));
//		}	
		this.setTitle("飞机大战");
		this.setSize(WIDTH, HEIGHT);
		this.setLocation(200, 200);
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				System.exit(0);
			}
		});
		
		this.addKeyListener(new Keyboard());
		Thread t = new Thread(new ThreadPlane());
		t.start();
		Thread t1 = new Thread(new Big());
		t1.start();
		Thread t2 = new Thread(new Medium());
		t2.start();
		Thread t3 = new Thread(new Small());
		t3.start();
		
//		this.addMouseListener(new MouseAdapter() {
//			if(state == 0) 			
//		});
	}
	
	public void paint(Graphics g) {
		p.paint(g);
		p.knocks(dj);
		for(int i=0;i<pd.size();i++) {
			Bullet m = pd.get(i);
			m.paint(g);
			m.knocks(dj);
		}
	
		
		for(int i=0;i<dj.size();i++) {
			EnemyPlane k = dj.get(i);
			k.paint(g);
			k.knocks(pd);
			if(p.knocks(dj)) {
				Dialog d = new Dialog(mc,"gameover",false);
				d.setBounds(300, 300, 100, 100);
				d.setVisible(true);
				Button t = new Button("结束");
				Button f = new Button("开始");
				d.add(t);
				d.add(f);
//				setVisible(false);
//				System.exit(0);
			}
		}
		
		for(int i=0;i<bz.size();i++) {
			Burst z = bz.get(i);
			z.paint(g);
		}
	}
	
	
	public void update(Graphics g) {

		if(iBuffer == null) {
			iBuffer = this.createImage(WIDTH,HEIGHT);	
		}
		Graphics gBuffer = iBuffer.getGraphics();
		gBuffer.setColor(getBackground());
		gBuffer.fillRect(0, 0, WIDTH, HEIGHT);
		
		try {
			BufferedImage image = ImageIO.read(new File("img\\background.jpg"));
			g.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		paint(gBuffer);
		g.drawImage(iBuffer,0,0,null);
//		try {
//			BufferedImage image = ImageIO.read(new File("img\\background.jpg"));
//			g.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
//		} catch(IOException e) {
//			e.printStackTrace();
//		}
	}	
	
	class ThreadPlane implements Runnable {	
		public void run() {
			while(true) {
				repaint();
				try {  
                    Thread.sleep(200);  
                } catch (InterruptedException e) {  
                    e.printStackTrace();
                }
			}			
		}
	}
	
	class Big implements Runnable {
		int BigLife = 5;
		Random r = new Random();
		boolean panduan = true;
		public void run() {
			while(panduan) {
				
//				for(int i =0;i<3;i++) {
					if(p.knocks(dj)) {
//						BigLife--;
//						if(BigLife==0) {
						panduan = false;
//						}
//					}
				}
				
				dj.add(new EnemyPlane(r.nextInt(260)+60, 50, MainConsole.this,EnemyPlane.EnemyPlaneSize.big));
//				repaint();
				try {  
                    Thread.sleep(5000);  
                } catch (InterruptedException e) {  
                    e.printStackTrace();
                }
			}
		}
	}
	
	class Medium implements Runnable {
		Random r = new Random();
		boolean panduan = true;
		public void run() {
			while(panduan) {
				if(p.knocks(dj)) { 
					panduan = false;				
					
				}
				dj.add(new EnemyPlane(r.nextInt(300)+60, 50, MainConsole.this,EnemyPlane.EnemyPlaneSize.medium));
//				repaint();
				try {  
                    Thread.sleep(3000);  
                } catch (InterruptedException e) {  
                    e.printStackTrace();
                }
			}
		}
	}
	
	class Small implements Runnable {
		Random r = new Random();
		boolean panduan = true;
		public void run() {
			while(panduan) {
				if(p.knocks(dj)) { 
//					MediumLife--;
//					SmallLife--;
					panduan = false;	
					
				}
				dj.add(new EnemyPlane(r.nextInt(300)+60, 50, MainConsole.this,EnemyPlane.EnemyPlaneSize.small));
//				repaint();
				try {  
                    Thread.sleep(100);  
                } catch (InterruptedException e) {  
                    e.printStackTrace();
                }
			}
		}
	}
	
	
	
	class Keyboard extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			p.keyPressed(e);
		}
		
		public void keyReleased(KeyEvent e) {
			p.keyReleased(e);
		}
	}
	
	public static void main(String[] args) {
		MainConsole mc = new MainConsole();
	}

}
