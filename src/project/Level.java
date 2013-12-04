package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Level extends JPanel implements ActionListener {

    private final int DELAY = (int)(1f/60f);

    private Timer timer;
    
    private boolean inGame = true;
    private World world;
    
    PhysicsObject physObj;
    static Vector2 translation = Vector2.zero();
    //private Image ball;
    public Level() {
        addKeyListener(new TAdapter());

        setBackground(Color.black);

        //ImageIcon iid = new ImageIcon(this.getClass().getResource("dot.png"));
        //ball = iid.getImage();

        setFocusable(true);
        initGame();
    }


    public void initGame() {
    	//initializera
    	world = new World();
    	
    	
    	
    	
    	PhysicsFixture fixture = new CircleFixture(25f);
    	physObj = new PhysicsObject(fixture, new Vector2(100f, 300f));
    	physObj.name = "Player";
    	
    	world.addObject(physObj);
    	
    	fixture = new CircleFixture(40f);
    	
    	PhysicsObject physObj2 = new PhysicsObject(fixture, new Vector2(320f, 320f));
    	physObj2.name = "Övre";
    	
    	world.addObject(physObj2);
    	
    	fixture = new CircleFixture(30f);
    	
    	physObj2 = new PhysicsObject(fixture, new Vector2(30f, 30f));
    	
    	physObj2.name = "Nedre";
    	world.addObject(physObj2);
    	
    	world.addObject(new PhysicsObject(new PolygonFixture(new Vector2[]{new Vector2(-40, -25), new Vector2(40, -5), new Vector2(0, 40), new Vector2(-50, 60)}),
    			new Vector2(200f, 200f)));
    	
    	world.addObject(new PhysicsObject(new PolygonFixture(new Vector2[]{new Vector2(60, 25), new Vector2(40, 5), new Vector2(0, 40), new Vector2(50, 60)}),
    			new Vector2(400f, 400f)));
    	
        timer = new Timer(DELAY, this);
        timer.start();
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (inGame) {

            world.debugDraw(g);

            Toolkit.getDefaultToolkit().sync();
            g.dispose();

        } else {
            //gameOver(g);
        }
    }


//    public void gameOver(Graphics g) {
//        String msg = "Game Over";
//        Font small = new Font("Helvetica", Font.BOLD, 14);
//        FontMetrics metr = this.getFontMetrics(small);
//
//        g.setColor(Color.white);
//        g.setFont(small);
//        g.drawString(msg, (WIDTH - metr.stringWidth(msg)) / 2,
//                     HEIGHT / 2);
//    }

    public void update()
    {
    	physObj.addForce(translation);
    	world.update();
    }
    public void actionPerformed(ActionEvent e) {

        if (inGame) {
            update();
        }
        repaint();
    }


    private class TAdapter extends KeyAdapter {

        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();
            
            if(key == KeyEvent.VK_LEFT)
            {
            	translation.x = -1f/600f;
            }
            if(key == KeyEvent.VK_RIGHT)
            {
            	translation.x = 1f/600f;
            }
            if(key == KeyEvent.VK_UP)
            {
            	translation.y = -1f/600f;
            }
            if(key == KeyEvent.VK_DOWN)
            {
            	translation.y = 1f/600f;
            }

        }
        public void keyReleased(KeyEvent e) {
        	
        	int key = e.getKeyCode();
        	
        	if(key == KeyEvent.VK_LEFT)
            {
            	translation.x = 0;
            }
            if(key == KeyEvent.VK_RIGHT)
            {
            	translation.x = 0;
            }
            if(key == KeyEvent.VK_UP)
            {
            	translation.y = 0;
            }
            if(key == KeyEvent.VK_DOWN)
            {
            	translation.y = 0;
            }
            	
        }
    }
}
