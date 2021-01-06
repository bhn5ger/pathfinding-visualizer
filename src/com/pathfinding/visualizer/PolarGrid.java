package com.pathfinding.visualizer;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class PolarGrid extends JPanel{
	/* 
	 * Polar grid dimensions
	 * Width: 564
	 * Height: 569
	 * 
	 * */
	public void paint(Graphics g) {
		int x = 255, y = 260;
		int width = 50, height = 50;
		for(int i = 0; i < 15; i++) {//circles
			g.drawOval(x, y, width, height);
			x -= 25;
			y -= 25;
			width += 50;
			height += 50;
			
		}
		g.drawLine(280, 0, 280, 569); //y-axis
		g.drawLine(0, 284, 564, 284); //x-axis
		//diagonals
		g.drawLine(0, 2, 564, 572);
		g.drawLine(564, -2, 0, 567);
		
		g.drawLine(161, 0, 399, 567);
		g.drawLine(399, 0, 161, 567);
		
		g.drawLine(0, 169, 564, 401);
		g.drawLine(564, 168, 0, 400);

	}

	
	

}
