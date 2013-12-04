package project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.*;

public class Game extends JFrame{
	
	private ArrayList<Vector2> pointsArr = new ArrayList<Vector2>();
	private Level level = new Level();
	
	 public Game() {
		
		 
		 	add(level);

	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
	        setLocationRelativeTo(null);
	        setTitle("Balls");

	        setResizable(false);
	        setPreferredSize(new Dimension(600, 600));
	        pack();
	        
	        
	        level.addMouseMotionListener(new MouseMotionListener() {
				
				@Override
				public void mouseMoved(MouseEvent arg0) {
					// TODO Auto-generated method stub
				}
				
				@Override
				public void mouseDragged(MouseEvent arg0) {
					// TODO Auto-generated method stub
					System.out.println("("+arg0.getY()+", "+arg0.getX()+")");
					
					JLabel lab = new JLabel("O");
					lab.setBounds(arg0.getX(), arg0.getY(), 5, 5);
					lab.setBackground(Color.red);
					level.add(lab);
					lab.setVisible(true);
				}
			});
	      
	        
	        //setSize(600, 600);
	       
	        
	        setVisible(true);
	    }

	    public static void main(String[] args) {
	        new Game();
	    }
}
