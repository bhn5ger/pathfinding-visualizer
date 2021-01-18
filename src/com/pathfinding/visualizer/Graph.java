package com.pathfinding.visualizer;

import java.awt.Color;
import java.util.Iterator;
import java.util.LinkedList;
 
// This class represents a directed graph using adjacency list
// representation
public class Graph
{
	private int checks = 0;
	private int startingIndex, endIndex, speed;
	private PolarGrid polargrid;
    private int V;   // No. of vertices
    private LinkedList<Integer> adj[]; //Adjacency Lists
 
    // Constructor
    Graph(int v, int startingIndex, int endIndex, int speed, PolarGrid polargrid)
    {
    	this.startingIndex = startingIndex;
    	this.endIndex = endIndex;
    	this.speed = speed;
    	this.polargrid = polargrid;
    	
        V = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
    }
 
    // Function to add an edge into the graph
    void addEdge(int v,int w)
    {
        adj[v].add(w);
    }
 
    // prints BFS traversal from a given source s
    void BFS(int s)
    {
    	
        // Mark all the vertices as not visited(By default
        // set as false)
        boolean visited[] = new boolean[V];
 
        // Create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<Integer>();
 
        // Mark the current node as visited and enqueue it
        visited[s]=true;
        queue.add(s);
 
        while (queue.size() != 0)
        {
            // Dequeue a vertex from queue and print it
            s = queue.poll();
            System.out.print(s+" ");
            Window.setChecks(checks);
            checks++;
            if(s != startingIndex && s != endIndex)PolarGrid.sectors[s].setColor(new Color(0,255,255));
            polargrid.repaint();
            try {
    			Thread.sleep(speed);
    		} catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
 
            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            Iterator<Integer> i = adj[s].listIterator();
            while (i.hasNext())
            {
            	 
                int n = i.next();
                if (!visited[n])
                {
                    visited[n] = true;
                    queue.add(n);
                    

                }
            }
            
        }



        
    }
    // A function used by DFS
    void DFSUtil(int v, boolean visited[])
    {
        // Mark the current node as visited and print it
        visited[v] = true;
        System.out.print(v + " ");
        Window.setChecks(checks);
        checks++;
        if(v != startingIndex && v != endIndex)PolarGrid.sectors[v].setColor(new Color(0,255,255));
        polargrid.repaint();
        try {
			Thread.sleep(speed);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
        // Recur for all the vertices adjacent to this
        // vertex
        Iterator<Integer> i = adj[v].listIterator();
        while (i.hasNext()) 
        {
            int n = i.next();
            if (!visited[n])
                DFSUtil(n, visited);
        }
    }
 
    // The function to do DFS traversal.
    // It uses recursive
    // DFSUtil()
    void DFS(int v)
    {
        // Mark all the vertices as 
        // not visited(set as
        // false by default in java)
        boolean visited[] = new boolean[V];
 
        // Call the recursive helper 
        // function to print DFS
        // traversal
        DFSUtil(v, visited);
    }

 

}






