package com.pathfinding.visualizer;

import java.awt.Color;
import java.awt.Polygon;
import java.util.Arrays;

public class Sector {
	private int x, y, width, height;
	private double startAngle, endAngle;
	private Color color;
	private Polygon bounds;
	
	public Sector(int x, int y, int width, int height, double startAngle, double endAngle) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.startAngle = startAngle;
		this.endAngle = endAngle;
		this.color = new Color(240, 240, 240);
		
		int lowerRadius = convertXCartesian( (int) ( ( 550 - ( (12 - (width/50)) * (25) ) )) );
		int upperRadius = lowerRadius + 25;
				
		double startTheta = Math.toRadians(startAngle); 
		double midTheta = Math.toRadians((startAngle + endAngle)/2);
		double endTheta = Math.toRadians(endAngle);
		
		double startSinTheta = Math.sin(startTheta);
		double startCosTheta = Math.cos(startTheta);
		double midSinTheta = Math.sin(midTheta);
		double midCosTheta = Math.cos(midTheta);
		double endSinTheta = Math.sin(endTheta);
		double endCosTheta = Math.cos(endTheta);
		
		int [] xpoints = {convertXScreen(lowerRadius*startCosTheta), convertXScreen(upperRadius*startCosTheta), convertXScreen(upperRadius*midCosTheta), 
				convertXScreen(upperRadius*endCosTheta), convertXScreen(lowerRadius*endCosTheta), convertXScreen(lowerRadius*midCosTheta)};
		int [] ypoints = {convertYScreen(lowerRadius*startSinTheta), convertYScreen(upperRadius*startSinTheta), convertYScreen(upperRadius*midSinTheta), 
				convertYScreen(upperRadius*endSinTheta), convertYScreen(lowerRadius*endSinTheta), convertYScreen(lowerRadius*midSinTheta)};
		
		this.bounds = new Polygon(xpoints, ypoints, 6) ;
	}
	
	public int convertXCartesian(double x) {
		return (int) (x - PolarGrid.WIDTH/2);
	}
	public int convertYCartesian(double y) {
		return (int) (-y + PolarGrid.HEIGHT/2);
	}
	public int convertXScreen(double x) {
		return (int) (x + PolarGrid.WIDTH/2);
	}
	public int convertYScreen(double y) {
		return (int) (-y + PolarGrid.HEIGHT/2);
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
	public Polygon getPoly() {
		return this.bounds;
	}
	
	public String toString() {
		return this.x + " " + this.y + " " + this.width + " " + this.height + " " + this.startAngle + " " + this.endAngle + " Polygon Bounds: " + this.bounds.getBounds();
		
	}
	
	
	
	
	
	
	
}
