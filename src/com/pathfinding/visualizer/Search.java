package com.pathfinding.visualizer;

import java.awt.Color;

public class Search implements Runnable{
	
	private Graph graph;
	private String algorithm;
	private int startingIndex, endIndex;
	private int speed;
	public static boolean isSearching = false;
	
	public Search(String algorithm, int speed) {
		
		Window.polargrid.resetPath();
		this.algorithm = algorithm;
		this.speed = speed;
		for(int i = 0; i < 176; i++) {
			if(PolarGrid.sectors[i].getColor() == Color.green) this.startingIndex = i;
			if(PolarGrid.sectors[i].getColor() == Color.red) this.endIndex = i;
		}
		
		//create graph
		graph = new Graph(176); 
		for(int i = 0; i < 176; i++) { 
			if( i == 15 || i == 31 || i == 47 || i == 63 || i == 79 || i == 95 || i == 111 || i == 127 || i == 143 || i == 159 || i == 175) {
				if(PolarGrid.sectors[i].getColor() != Color.black && PolarGrid.sectors[i - 15].getColor() != Color.black) {
					graph.addEdge(i , i - 15);
					graph.addEdge(i - 15, i);
				}
			}
			else {
				if(PolarGrid.sectors[i].getColor() != Color.black && PolarGrid.sectors[i + 1].getColor() != Color.black) {
					graph.addEdge(i, i + 1);
					graph.addEdge(i + 1, i);
				}

			} 
		}
		
		for(int i = 0; i < 160; i++) {
			if(PolarGrid.sectors[i].getColor() != Color.black && PolarGrid.sectors[i + 16].getColor() != Color.black) {
				graph.addEdge(i , i + 16);
				graph.addEdge(i + 16, i);
			}

		}
		
		Thread t = new Thread(this);
		isSearching = true;
		t.start();
		
		
	}
	
	@Override
	public void run() {
		
		if(this.algorithm.equals("BFS")) {
			graph.BFS(startingIndex, endIndex, speed, Window.polargrid);
		}
        isSearching = false;
        Window.polargrid.setCompleteGrid(true);
		
	}

}
