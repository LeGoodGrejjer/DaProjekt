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

    private final int DELAY = 140;

    private Timer timer;
    
    private boolean inGame = true;
    private World world;
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
    	
    	PhysicsFixture fixture = new CircleFixture(5f);
    	
    	PhysicsObject physObj = new PhysicsObject(fixture);
    	
    	world.addObject(physObj);
    	
        timer = new Timer(DELAY, this);
        timer.start();
    }


    public void paint(Graphics g) {
        super.paint(g);

        if (inGame) {

            //g.drawImage(apple, apple_x, apple_y, this);

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

            //if ((key == KeyEvent.VK_LEFT))
        }
    }
}
