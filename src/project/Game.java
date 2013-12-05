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
	      
	        
	        //setSize(600, 600);
	       
	        
	        setVisible(true);
	    }

	    public static void main(String[] args) {
	        new Game();
	    }
}
