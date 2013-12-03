package project;

import javax.swing.*;

public class Game extends JFrame{
	 public Game() {
		 
		 	add(new Level());

	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setSize(600, 600);
	        setLocationRelativeTo(null);
	        setTitle("Balls");

	        setResizable(false);
	        setVisible(true);
	    }

	    public static void main(String[] args) {
	        new Game();
	    }
}
