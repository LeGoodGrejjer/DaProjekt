package project;

import java.awt.Dimension;

import javax.swing.*;

public class Game extends JFrame{
	 public Game() {
		 
		 	add(new Level());

	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
	        setLocationRelativeTo(null);
	        setTitle("Balls");

	        setResizable(false);
	        setPreferredSize(new Dimension(600, 600));
	        pack();
	        //setSize(600, 600);
	        setVisible(true);
	    }
	 // hahahahahahahaha
	    public static void main(String[] args) {
	        new Game();
	    }
}
