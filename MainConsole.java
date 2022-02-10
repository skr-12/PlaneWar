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
		
		for(int i=0;i<pd.size();i++) {
			Bullet m = pd.get(i);
			m.paint(g);
			m.zhuangJi1(dj);
		}
		System.out.println(pd.size());
		
		for(int i=0;i<dj.size();i++) {
			EnemyPlane k = dj.get(i);
			k.paint(g);
//			k.zhuangJi(p);
		}
	}
	
	class ThreadPlane implements Runnable {
		public void run() {
			while(true) {
				repaint();
				try {  
                    Thread.sleep(20);  
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
