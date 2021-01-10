package com.pathfinding.visualizer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JPanel;

/* 
 * Polar grid dimensions
 * Width: 564
 * Height: 569
 * 
 * */

@SuppressWarnings("serial")
public class PolarGrid extends JPanel implements MouseListener{
	
	public static Sector [] sectors = new Sector[176];
		
	public static final int [] ORIGIN = {279, 285};
	public static final int [] circleRadii = {4};
	public static final int [] lineSlopes = {4};
	
	
	public PolarGrid() {
		//create sectors(nodes)
		int x = 1, y = 5;
		int width = 550, height = 550;
		double startAngle = 0, endAngle = 22.5;
		for(int i = 0; i < 176; i++) {			
			if( (i != 0) && (i % 16 == 0) ) {
				x += 25;
				y += 25;
				width -= 50;
				height -= 50;
				startAngle = 0;
				endAngle = 22.5;
			}
			sectors[i] = new Sector(x, y, width , height, startAngle, endAngle);
			System.out.println(i + "th entry, " + sectors[i]);
			startAngle += 22.5;
			endAngle += 22.5;
			
			
		}
		
	}
	
	

	public void paint(Graphics g) {
		//fill circles
		/*
		int x = 1, y = 5;
		int width = 550, height = 550;
		Random r = new Random();
		for(int i = 0; i < 11; i++) {
			g.setColor(new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256)));
			g.fillArc(x, y, width , height, 0, 368);
			x += 25;
			y += 25;
			width -= 50;
			height -= 50;
			
		}
		*/
		
	
		for(int i = 0; i < sectors.length; i++) {
			g.setColor(sectors[i].getColor());
			g.setColor(Color.red);
			if(sectors[i].getEndAngle() == 360) {
				g.fillArc(sectors[i].getX(), sectors[i].getY(), sectors[i].getWidth() , sectors[i].getHeight(), (int)sectors[i].getStartAngle(), (int)sectors[i].getEndAngle());
			}

			
		}
		
		
		
		
		
		g.setColor(Color.black);
		int x = 251, y = 255;
		int width = 50, height = 50;
		
		//circles
		for(int i = 0; i < 11; i++) {
			g.drawOval(x, y, width, height);
			x -= 25;
			y -= 25;
			width += 50;
			height += 50;
			
		}
		//lines
		//y and x axes
		g.drawLine(275, -5, 275, 564); 
		g.drawLine(-5, 279, 559, 279); 
		
		//diagonals
		g.drawLine(-5, -3, 559, 567);
		g.drawLine(559, -7, -5, 562);
		
		g.drawLine(156, -5, 394, 562);
		g.drawLine(394, -5, 156, 562);
		
		g.drawLine(-5, 164, 559, 396);
		g.drawLine(559, 163, -5, 395);
		
		

		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		System.out.println("(" + mx + " , " + my + ")" );

		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		//System.out.println("(" + mx + " , " + my + ")" );
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		//System.out.println("(" + mx + " , " + my + ")" );
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		//System.out.println("(" + mx + " , " + my + ")" );
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		//System.out.println("(" + mx + " , " + my + ")" );
		
	}
	

	
	

}
