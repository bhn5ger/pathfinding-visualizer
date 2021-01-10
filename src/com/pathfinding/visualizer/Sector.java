package com.pathfinding.visualizer;

import java.awt.Color;

public class Sector {
	private int x, y, width, height;
	private double startAngle, endAngle;
	private Color color;
	
	public Sector(int x, int y, int width, int height, double startAngle, double endAngle) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.startAngle = startAngle;
		this.endAngle = endAngle;
		this.color = new Color(240, 240, 240);
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public double getStartAngle() {
		return this.startAngle;
	}

	public double getEndAngle() {
		return this.endAngle;
	}
	public Color getColor() {
		return this.color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	public String toString() {
		return this.x + " " + this.y + " " + this.width + " " + this.height + " " + this.startAngle + " " + this.endAngle;
		
	}
	
	
	
	
	
	
	
}
