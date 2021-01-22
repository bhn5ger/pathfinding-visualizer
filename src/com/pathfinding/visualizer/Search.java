package com.pathfinding.visualizer;

import java.awt.Color;

public class Search implements Runnable{
	
	private Graph graph;
	private String algorithm;
	public static boolean isSearching = false;
	
	public Search(String algorithm, int speed) {
		
		Window.polargrid.resetPath();
		this.algorithm = algorithm;
		
		int startingIndex = 0; 
		int endIndex = 0;
		for(int i = 0; i < 176; i++) {
			if(PolarGrid.sectors[i].getColor() == Color.green) startingIndex = i;
			if(PolarGrid.sectors[i].getColor() == Color.red) endIndex = i;
		}
		
		//create graph
		this.graph = new Graph(176, startingIndex, endIndex, speed, Window.polargrid); 
		for(int i = 0; i < 176; i++) { 
			if( i == 15 || i == 31 || i == 47 || i == 63 || i == 79 || i == 95 || i == 111 || i == 127 || i == 143 || i == 159 || i == 175) {
				if(PolarGrid.sectors[i].getColor() != Color.black && PolarGrid.sectors[i - 15].getColor() != Color.black) {
					this.graph.addEdge(i , i - 15);
					this.graph.addEdge(i - 15, i);
				}
			}
			else {
				if(PolarGrid.sectors[i].getColor() != Color.black && PolarGrid.sectors[i + 1].getColor() != Color.black) {
					this.graph.addEdge(i, i + 1);
					this.graph.addEdge(i + 1, i);
				}

			} 
		}
		
		for(int i = 0; i < 160; i++) {
			if(PolarGrid.sectors[i].getColor() != Color.black && PolarGrid.sectors[i + 16].getColor() != Color.black) {
				this.graph.addEdge(i , i + 16);
				this.graph.addEdge(i + 16, i);
			}

		}
		
		this.graph.setAdjMatrix(this.graph.convert(this.graph.getAdj(), 176));
		
		Thread t = new Thread(this);
		isSearching = true;
		t.start();
		
		
	}
	
	@Override
	public void run() {
		
		if(algorithm.equals("BFS")) {
			graph.printBFS();
		}
		if(algorithm.equals("DFS")) {
			graph.DFS();
		}
		if(algorithm.equals("Dijkstra's")) {
		    graph.dijkstra(); 
		}
        isSearching = false;
        Window.polargrid.setCompleteGrid(true);
		
	}

}
