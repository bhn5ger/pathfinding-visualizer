package com.pathfinding.visualizer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import javax.swing.JPanel;

/* 
 * Polar grid dimensions
 * Width: 564
 * Height: 569
 * 
 * sectors.length and 176 are used interchangeably
 * */

@SuppressWarnings("serial")
public class PolarGrid extends JPanel implements MouseListener, MouseMotionListener{
	
	public static Sector [] sectors = new Sector[176];
	public static final Point ORIGIN = new Point(276, 279);
	public static final int WIDTH = 552;
	public static final int HEIGHT = 558;
	private Color mouseColor = Color.black;
	private boolean toolboxClicked = false;
	private boolean completeGrid = false;
	
	
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
			startAngle += 22.5;
			endAngle += 22.5;
		}
	
	}

	public void paint(Graphics g) {
		g.setColor(new Color(240,240,240));
		g.fillRect(0,0,564,569);
		
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
			if(i == 1) {
				g.drawOval(x, y, width, height);
			}
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
		g.drawString("π⁄₈", 532, 153);
		g.drawString("π⁄₄", 513, 16);
		g.drawString("3π⁄₈", 360, 11);
		g.drawString("5π⁄₈", 173, 14);
		g.drawString("3π⁄₄", 36, 18);
		g.drawString("7π⁄₈", -1, 155);
		
		g.drawString("9π⁄₈", 1, 425);
		g.drawString("5π⁄₄", 28, 544);
		g.drawString("11π⁄₈", 170, 564);
		g.drawString("13π⁄₈", 347, 564);
		g.drawString("7π⁄₄", 500, 552);
		g.drawString("15π⁄₈", 520, 425);
		

		
		
	}
	
	public void reset() {
		for(int i = 0; i < 176; i++) {
			sectors[i].setColor(new Color(240,240,240));
		}
		repaint();
		
	}
	
	public boolean hasStartAndFinish() {
		boolean hasStart = false;
		boolean hasFinish = false;
		for(int i = 0; i < 176; i++) {
			if(sectors[i].getColor() == Color.green) hasStart = true;
			if(sectors[i].getColor() == Color.red) hasFinish = true;
		}
		boolean hasStartAndFinish = hasStart && hasFinish;
		return hasStartAndFinish;
	}
	
	public void generateMap() {
		reset();
		
		Random r = new Random();
		for(int i = 0; i < sectors.length; i++) {
			if(r.nextInt(2) == 0) sectors[i].setColor(Color.black);
		}
		sectors[r.nextInt(176)].setColor(Color.green);
		sectors[r.nextInt(176)].setColor(Color.red);
		
	}
	


	@Override
	public void mousePressed(MouseEvent e) {
		if(completeGrid == false) {
			
			if(toolboxClicked == true && Search.isSearching == false) {
				int mx = e.getX();
				int my = e.getY();
				Point mouseOver = new Point(mx, my);
				Color white = new Color(240,240,240);
				if(mouseColor == Color.green || mouseColor == Color.red) {
					for(int i = 0; i < 176; i++) {
						if(PolarGrid.sectors[i].getColor() == mouseColor) {
							PolarGrid.sectors[i].setColor(new Color(240,240,240));
							repaint();
						}
					}
					for(int i = 0; i < 176; i++) {
						if(PolarGrid.sectors[i].getPoly().contains(mouseOver) && PolarGrid.sectors[i].getColor().getRed() == white.getRed() && 
								PolarGrid.sectors[i].getColor().getGreen() == white.getGreen() && PolarGrid.sectors[i].getColor().getBlue() == white.getBlue()) {
							PolarGrid.sectors[i].setColor(mouseColor);
							repaint();
						}
					}
				}
				else if(mouseColor.getRed() == white.getRed() && mouseColor.getGreen() == white.getGreen() && mouseColor.getBlue() == white.getBlue()) {
					for(int i = 0; i < 176; i++) {
						if(PolarGrid.sectors[i].getPoly().contains(mouseOver)) {
							PolarGrid.sectors[i].setColor(mouseColor);
							repaint();
						}
					}
				}
				else if(mouseColor == Color.black) {
					for(int i = 0; i < 176; i++) {
						if(PolarGrid.sectors[i].getPoly().contains(mouseOver) && PolarGrid.sectors[i].getColor().getRed() == white.getRed() && 
								PolarGrid.sectors[i].getColor().getGreen() == white.getGreen() && PolarGrid.sectors[i].getColor().getBlue() == white.getBlue()) {
							PolarGrid.sectors[i].setColor(mouseColor);
							repaint();
						}
					}
				}

			}
			
			
			
		}

	
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(completeGrid == false) {
			
			if(toolboxClicked == true && Search.isSearching == false) {
				int mx = e.getX();
				int my = e.getY();
				Point mouseOver = new Point(mx, my);
				Color white = new Color(240,240,240);
				if(mouseColor == Color.green || mouseColor == Color.red) {
					for(int i = 0; i < 176; i++) {
						if(PolarGrid.sectors[i].getColor() == mouseColor) {
							PolarGrid.sectors[i].setColor(new Color(240,240,240));
							repaint();
						}
					}
					for(int i = 0; i < 176; i++) {
						if(PolarGrid.sectors[i].getPoly().contains(mouseOver) && PolarGrid.sectors[i].getColor().getRed() == white.getRed() && 
								PolarGrid.sectors[i].getColor().getGreen() == white.getGreen() && PolarGrid.sectors[i].getColor().getBlue() == white.getBlue()) {
							PolarGrid.sectors[i].setColor(mouseColor);
							repaint();
						}
					}
				}
				else if(mouseColor.getRed() == white.getRed() && mouseColor.getGreen() == white.getGreen() && mouseColor.getBlue() == white.getBlue()) {
					for(int i = 0; i < 176; i++) {
						if(PolarGrid.sectors[i].getPoly().contains(mouseOver)) {
							PolarGrid.sectors[i].setColor(mouseColor);
							repaint();
						}
					}
				}
				else if(mouseColor == Color.black) {
					for(int i = 0; i < 176; i++) {
						if(PolarGrid.sectors[i].getPoly().contains(mouseOver) && PolarGrid.sectors[i].getColor().getRed() == white.getRed() && 
								PolarGrid.sectors[i].getColor().getGreen() == white.getGreen() && PolarGrid.sectors[i].getColor().getBlue() == white.getBlue()) {
							PolarGrid.sectors[i].setColor(mouseColor);
							repaint();
						}
					}
				}
			}
			
			
			
			
		}

	
	}
	public void resetPath() {
		Color white = new Color(240,240,240);
		Color blue = new Color(0,255,255);
		for(int i = 0; i < sectors.length; i++) {
			if( (sectors[i].getColor().getRed() == blue.getRed() && sectors[i].getColor().getGreen() == blue.getGreen() && sectors[i].getColor().getBlue() == blue.getBlue()) || (sectors[i].getColor() == Color.yellow) ) 
				sectors[i].setColor(white);		
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {}
	
	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	public void setMouseColor(Color c) {
		this.mouseColor = c;
	}
	public void setToolboxClicked(boolean b) {
		this.toolboxClicked = b;
	}
	public void setCompleteGrid(boolean b) {
		this.completeGrid = b;
	}
	

}
