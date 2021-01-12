package com.pathfinding.visualizer;

import java.awt.Color;

public class Search implements Runnable{
	
	public Graph graph;
	
	public Search() {
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
		t.start();
		
		
	}
	
	@Override
	public void run() {
        System.out.println("Following is Breadth First Traversal "+
                "(starting from vertex 0)");
        graph.BFS(170, Window.polargrid);
		
	}

}
