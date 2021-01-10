package com.pathfinding.visualizer;

public class Search implements Runnable{
	
	public Search() {
		Thread t = new Thread(this);
		t.start();
		
		
	}
	
	@Override
	public void run() {
        System.out.println("Following is Breadth First Traversal "+
                "(starting from vertex 0)");
        Window.polargrid.graph.BFS(170, Window.polargrid);
		
	}

}
