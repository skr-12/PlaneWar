import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainConsole extends Frame {	
	Plane p = new Plane(150,450,this);
	List<Bullet> pd = new ArrayList<Bullet>();
	List<EnemyPlane> dj = new ArrayList<EnemyPlane>();
	List<Burst> bz = new ArrayList<Burst>();
	Random random = new Random();
//	DiJi dj = new DiJi(150,50);
//	PaoDan pd = new PaoDan();
	public MainConsole() {
		for(int i=0;i<8;i++) {
			dj.add(new EnemyPlane(30*(2+i),50,this));
		}	
		this.setTitle("·É»ú´óÕ½");
		this.setSize(300, 500);
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
		}
		
		for(int i=0;i<bz.size();i++) {
			Burst z = bz.get(i);
			z.paint(g);
		}
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
		public void run() {
	//		while(size == big) {
				repaint();
				try {  
                    Thread.sleep(500);  
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
