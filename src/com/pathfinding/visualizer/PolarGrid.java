package com.pathfinding.visualizer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
	public Graph graph = new Graph(176);
		
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
		//create graph
		for(int i = 0; i < 176; i++) {
			if( i == 15 || i == 31 || i == 47 || i == 63 || i == 79 || i == 95 || i == 111 || i == 127 || i == 143 || i == 159 || i == 175) {
				graph.addEdge(i , i - 15);
				graph.addEdge(i - 15, i);
			}
			else {
				graph.addEdge(i, i + 1);
				graph.addEdge(i + 1, i);
			} 
		}
		
		for(int i = 0; i < 160; i++) {
				graph.addEdge(i , i + 16);
				graph.addEdge(i + 16, i);

		}
		
	
	}

	public void paint(Graphics g) {
		g.setColor(new Color(240,240,240));
		g.fillRect(0,0,564,569);
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
			g.fillArc(sectors[i].getX(), sectors[i].getY(), sectors[i].getWidth() , sectors[i].getHeight(), (int)sectors[i].getStartAngle(), 23);
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
		x -= 25;
		y -= 25;
		width += 50;
		height += 50;
		for(int i = 0; i < 2; i++) {
			g.drawOval(x, y, width, height);
			x -= 50;
			y -= 50;
			width += 100;
			height += 100;
			
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
		
		Font f = new Font("TimesRoman", 0, 17);
		g.setFont(f);
		g.drawString("¹⁄₈π", 532, 153);
		g.drawString("¹⁄₄π", 513, 16);
		g.drawString("³⁄₈π", 360, 11);
		g.drawString("⁵⁄₈π", 173, 14);
		g.drawString("³⁄₄π", 36, 18);
		g.drawString("⁷⁄₈π", -1, 155);
		
		g.drawString("9⁄₈π", 1, 425);
		g.drawString("5⁄₄π", 28, 544);
		g.drawString("11⁄₈π", 170, 564);
		g.drawString("13⁄₈π", 347, 564);
		g.drawString("7⁄₄π", 500, 552);
		g.drawString("15⁄₈π", 520, 425);
		
		
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
