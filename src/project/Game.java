package project;

import java.awt.Dimension;

import javax.swing.*;

public class Game extends JFrame{
	 public Game() {
		
		 Level level = new Level();
		 	add(level);

	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
	        setLocationRelativeTo(null);
	        setTitle("Balls");

	        setResizable(false);
	        setPreferredSize(new Dimension(600, 600));
	        pack();
	        
	        level.addMouseListener(new MouseDraw());
	      
	        
	        //setSize(600, 600);
	       
	        
	        setVisible(true);
	    }

	    public static void main(String[] args) {
	        new Game();
	    }
}
