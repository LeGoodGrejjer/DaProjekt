package project;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.*;

public class Game extends JFrame implements MouseListener, MouseMotionListener{
	
	private ArrayList<Vector2> pointsArr = new ArrayList<Vector2>();
	
	 public Game() {
		
		 Level level = new Level();
		 	add(level);

	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
	        setLocationRelativeTo(null);
	        setTitle("Balls");

	        setResizable(false);
	        setPreferredSize(new Dimension(600, 600));
	        pack();
	        
	        
	        level.addMouseMotionListener(new MouseDraw());
	        level.addMouseListener(new MouseDraw());
	      
	        
	        //setSize(600, 600);
	       
	        
	        setVisible(true);
	    }

	    public static void main(String[] args) {
	        new Game();
	    }

		@Override
		public void mouseDragged(MouseEvent e) {
			pointsArr.add(new Vector2(e.getX(), e.getY()));
			
			
			PhysicsFixture fixture = new CircleFixture(25f);
	    	physObj = new PhysicsObject(fixture, new Vector2(100f, 300f));
	    	physObj.name = "Player";
	    	
	    	world.addObject(physObj);
		}
		

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
}
