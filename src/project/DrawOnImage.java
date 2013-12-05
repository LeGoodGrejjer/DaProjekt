package project;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 *  Note: Normally the ButtonPanel and DrawingArea would not be static classes.
 *  This was done for the convenience of posting the code in one class and to
 *  highlight the differences between the two approaches. All the differences
 *  are found in the DrawingArea class.
 */
public class DrawOnImage
{
	private DrawingArea drae = new DrawingArea();
	
	public DrawingArea getDrae() {
		return drae;
	}

	public void setDrae(DrawingArea drae) {
		this.drae = drae;
	}

	public DrawOnImage(){
		drae = new DrawingArea();
	}

	private static void createAndShowGUI()
	{
		DrawingArea drawingArea = new DrawingArea();
		ButtonPanel buttonPanel = new ButtonPanel( drawingArea );

		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("Draw On Image");
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.getContentPane().add(drawingArea);
		frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		frame.setSize(400, 400);
		frame.setLocationRelativeTo( null );
		frame.setVisible(true);
	}

	static class ButtonPanel extends JPanel implements ActionListener
	{
		private DrawingArea drawingArea;

		public ButtonPanel(DrawingArea drawingArea)
		{
			this.drawingArea = drawingArea;

			add( createButton("	", Color.BLACK) );
			add( createButton("	", Color.RED) );
			add( createButton("	", Color.GREEN) );
			add( createButton("	", Color.BLUE) );
			add( createButton("	", Color.ORANGE) );
			add( createButton("	", Color.YELLOW) );
			add( createButton("Clear Drawing", null) );
		}

		private JButton createButton(String text, Color background)
		{
			JButton button = new JButton( text );
			button.setBackground( background );
			button.addActionListener( this );

			return button;
		}

		public void actionPerformed(ActionEvent e)
		{
			JButton button = (JButton)e.getSource();

			if ("Clear Drawing".equals(e.getActionCommand()))
				drawingArea.clear();
			else
				drawingArea.setForeground( button.getBackground() );
		}
	}

	static class DrawingArea extends JPanel
	{
		BufferedImage image;
		Graphics2D g2d;
		Point startPoint = null;
		Point endPoint = null;

		public DrawingArea()
		{
			setBackground(Color.WHITE);

			MyMouseListener ml = new MyMouseListener();
			addMouseListener(ml);
			addMouseMotionListener(ml);
		}

		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);

			//  Custom code to support painting from the BufferedImage

			if (image == null)
			{
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
				g.drawRect(x, y, width, height);
			}
		}

		private void createEmptyImage()
		{
			image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
			g2d = (Graphics2D)image.getGraphics();
			g2d.setColor(Color.BLACK);
			g2d.drawString("Add a rectangle by doing mouse press, drag and release!", 40, 15);
		}

		public void addRectangle(int x, int y, int width, int height, Color color)
		{
			g2d.setColor( color );
			g2d.drawRect(x, y, width, height);
			repaint();
		}

		public void clear()
		{
			createEmptyImage();
			repaint();
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
			}

			public void mouseDragged(MouseEvent e)
			{
				//  Repaint only the area affected by the mouse dragging

				endPoint = e.getPoint();
				xMin = Math.min(xMin, endPoint.x);
				xMax = Math.max(xMax, endPoint.x);
				yMin = Math.min(yMin, endPoint.y);
				yMax = Math.max(yMax, endPoint.y);
				repaint(xMin, yMin, xMax - xMin + 1, yMax - yMin + 1);
			}

			public void mouseReleased(MouseEvent e)
			{
				//  Custom code to paint the Rectangle on the BufferedImage

				int x = Math.min(startPoint.x, endPoint.x);
				int y = Math.min(startPoint.y, endPoint.y);
				int width = Math.abs(startPoint.x - endPoint.x);
				int height = Math.abs(startPoint.y - endPoint.y);

				if (width != 0 || height != 0)
				{
//					g2d.setColor( e.getComponent().getForeground() );
//					g2d.drawRect(x, y, width, height);
					addRectangle(x, y, width, height, e.getComponent().getForeground());
				}

				startPoint = null;
//				repaint();
			}
		}
	}

}