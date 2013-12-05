package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.MouseInputAdapter;


public class Level extends JPanel implements ActionListener {

    private final int DELAY = (int)(1f/60f);

    private Timer timer;
    
    private boolean inGame = true;
    private World world;
    private BufferedImage image;
    private static Graphics2D g2d;
    private static Point startPoint = null;
    private static Point endPoint = null;
    private static Point lastPoint = new Point(0, 0);
    private static boolean ritarBoolean = false;
    private static boolean isDrawing = false;
    
    PhysicsObject physObj;
    static Vector2 translation = Vector2.zero();
    //private Image ball;
    public Level() {
        addKeyListener(new TAdapter());

        setBackground(Color.white);

        //ImageIcon iid = new ImageIcon(this.getClass().getResource("dot.png"));
        //ball = iid.getImage();

        setFocusable(true);
        initGame();
    }

    public void initGame() {
    	//initializera
    	world = new World();
    	
    	MyMouseListener musse = new MyMouseListener();
    	
    	addMouseMotionListener(musse);
    	addMouseListener(musse);
    	
    	PhysicsFixture fixture = new CircleFixture(25f);
    	physObj = new PhysicsObject(fixture, new Vector2(70f, 100f));
    	physObj.name = "Player";
    	physObj.isRigid = true;
    	
    	world.addObject(physObj);
    	
    	PhysicsObject physObj2 = new PhysicsObject(fixture, new Vector2(500f, 100f));
    	physObj2.name = "Player2";
    	
    	physObj2.isRigid = true;
    	
    	world.addObject(physObj2);
//    	fixture = new CircleFixture(40f);
//    	
//    	PhysicsObject physObj2 = new PhysicsObject(fixture, new Vector2(320f, 320f));
//    	physObj2.name = "�vre";
//    	
//    	world.addObject(physObj2);
//    	
//    	fixture = new CircleFixture(30f);
//    	
//    	physObj2 = new PhysicsObject(fixture, new Vector2(30f, 30f));
//    	
//    	physObj2.name = "Nedre";
//    	world.addObject(physObj2);
    	world.addObject(new PhysicsObject(new PolygonFixture(new Vector2[]{new Vector2(45, 225), new Vector2(100, 300), new Vector2(75, 350), new Vector2(10, 250)}),
    			new Vector2(0, 0)));
    	
    	world.addObject(new PhysicsObject(new PolygonFixture(new Vector2[]{new Vector2(100, 300), new Vector2(200, 340), new Vector2(200, 400), new Vector2(75, 350)}),
    			new Vector2(0, 0)));
    	
    	world.addObject(new PhysicsObject(new PolygonFixture(new Vector2[]{new Vector2(200, 340), new Vector2(330, 350), new Vector2(350, 400), new Vector2(200, 400)}),
    			new Vector2(0, 0)));
    	
    	world.addObject(new PhysicsObject(new PolygonFixture(new Vector2[]{new Vector2(330, 350), new Vector2(420, 320), new Vector2(450, 350), new Vector2(350, 400)}),
    			new Vector2(0, 0)));
    	
    	world.addObject(new PhysicsObject(new PolygonFixture(new Vector2[]{new Vector2(420, 320), new Vector2(520, 220), new Vector2(575, 230), new Vector2(450, 350)}),
    			new Vector2(0, 0)));
    	
    	
        timer = new Timer(DELAY, this);
        timer.start();
    }

    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (inGame) {

            world.debugDraw(g);

            
            

        } else {
            //gameOver(g);
        }
        
        if(ritarBoolean){
        	
        	if (image == null)
    		{
        		System.out.println("asdsa");
    			createEmptyImage();
    		}

    		g.drawImage(image, 0, 0, null);

    		//  Paint the Rectangle as the mouse is being dragged

    		if (startPoint != null && endPoint != null)
    		{
    			int x = Math.min(startPoint.x, endPoint.x);
    			int y = Math.min(startPoint.y, endPoint.y);
    			int width = Math.abs(startPoint.x - endPoint.x);
    			int height = Math.abs(startPoint.y - endPoint.y);
    			//g.drawRect(x, y, width, height);
    		}
        }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
        //g2d.dispose();
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

	private void createEmptyImage()
	{
		image = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
		g2d = (Graphics2D)image.getGraphics();
		g2d.setBackground(Color.white);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Adgfgfhfd a rectangle by doing mouse press, drag and release!", 40, 15);
	}

	public void addRectangle(int x, int y, int width, int height, Color color)
	{
		g2d.setColor( color );
		g2d.drawRect(x, y, width, height);
		//repaint();
	}

	public void clear()
	{
		createEmptyImage();
		//repaint();
	}

	class MyMouseListener extends MouseInputAdapter
	{
		private int xMin;
		private int xMax;
		private int yMin;
		private int yMax;

		public void mousePressed(MouseEvent e)
		{
			startPoint = e.getPoint();
			endPoint = startPoint;
			xMin = startPoint.x;
			xMax = startPoint.x;
			yMin = startPoint.y;
			yMax = startPoint.y;
			isDrawing = true;

			Ellipse2D.Double circle = new Ellipse2D.Double((int)e.getPoint().getX(), (int)e.getPoint().getY(), 20, 20);
			g2d.fill(circle);
			repaint(xMin, yMin, xMax - xMin + 1, yMax - yMin + 1);
			lastPoint = e.getPoint();
			
		}

		public void mouseDragged(MouseEvent e)
		{
			ritarBoolean = true;
			//  Repaint only the area affected by the mouse dragging
			
			endPoint = e.getPoint();
			xMin = Math.min(xMin, endPoint.x);
			xMax = Math.max(xMax, endPoint.x);
			yMin = Math.min(yMin, endPoint.y);
			yMax = Math.max(yMax, endPoint.y);
			//repaint(xMin, yMin, xMax - xMin + 1, yMax - yMin + 1);
			
			Ellipse2D.Double circle = new Ellipse2D.Double((int)e.getPoint().getX(), (int)e.getPoint().getY(), 20, 20);
			g2d.fill(circle);
			repaint(xMin, yMin, xMax - xMin + 1, yMax - yMin + 1);
			
			double hyp = Math.sqrt(Math.pow(lastPoint.x-e.getX(), 2)+Math.pow(lastPoint.y-e.getY(), 2));
			
			System.out.println(hyp);
			if(hyp >= 5 && (lastPoint.x != 0 & lastPoint.y != 0) && isDrawing){
				for(int n = 0; n<hyp/5; n++){
					Ellipse2D.Double circle2 = new Ellipse2D.Double(lastPoint.x-5*(lastPoint.x-e.getX())/hyp*(n+1), lastPoint.y-5*(lastPoint.y-e.getY())/hyp*(n+1), 20, 20);
					g2d.fill(circle2);
					repaint(xMin, yMin, xMax - xMin + 1, yMax - yMin + 1);
					System.out.println("lagger till");
				}
			}
			
			lastPoint = e.getPoint();
			
		}

		public void mouseReleased(MouseEvent e)
		{
			isDrawing = false;
			/*
			//  Custom code to paint the Rectangle on the BufferedImage
			int x = Math.min(startPoint.x, endPoint.x);
			int y = Math.min(startPoint.y, endPoint.y);
			int width = Math.abs(startPoint.x - endPoint.x);
			int height = Math.abs(startPoint.y - endPoint.y);

			if (width != 0 || height != 0)
			{
//				g2d.setColor( e.getComponent().getForeground() );
//				g2d.drawRect(x, y, width, height);
				addRectangle(x, y, width, height, Color.black);
			}
			System.out.println(x);
			//g2d.drawRect(x, y, 40, 40);
			//image. = (BufferedImage)g2d.;
			startPoint = null;
			//ritarBoolean = false;
//			repaint();
 */
		}
	}
}
