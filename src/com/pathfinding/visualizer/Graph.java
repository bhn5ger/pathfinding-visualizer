package com.pathfinding.visualizer;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
 
// This class represents a directed graph using adjacency list
// representation
public class Graph
{
	private static final int NO_PARENT = -1; 
	private boolean pathFound = false;
	private int checks = 0;
	private int pathlen = 0;
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
    void DFS(int s, int d) 
    { 
        boolean[] isVisited = new boolean[V]; 
        ArrayList<Integer> pathList = new ArrayList<>(); 

        // add source to path[] 
        pathList.add(s); 

        // Call recursive utility 
        dfsUtil(s, d, isVisited, pathList); 
    } 

    // A recursive function to print 
    // all paths from 'u' to 'd'. 
    // isVisited[] keeps track of 
    // vertices in current path. 
    // localPathList<> stores actual 
    // vertices in the current path 
    void dfsUtil(Integer u, Integer d, boolean[] isVisited, List<Integer> localPathList) 
    { 
    	if(pathFound == false) {
            if (u.equals(d)) { 
                System.out.println(localPathList);
                for(int i = 0; i < localPathList.size(); i++) {
                	 Window.setPathlen(pathlen);
                     pathlen++;
                     if(localPathList.get(i) != startingIndex && localPathList.get(i) != endIndex)PolarGrid.sectors[localPathList.get(i)].setColor(Color.yellow);
                     polargrid.repaint();
                     try {
             			Thread.sleep(speed);
             		} catch (InterruptedException e) {
             			// TODO Auto-generated catch block
             			e.printStackTrace();
             		}
                }
                pathFound = true;
                // if match found then no need to traverse more till depth 
                return; 
            } 

            // Mark the current node
            isVisited[u] = true; 
            //if(u == d) break;
            Window.setChecks(checks);
            checks++;
            if(u != startingIndex && u != endIndex)PolarGrid.sectors[u].setColor(new Color(0,255,255));
            polargrid.repaint();
            try {
       			Thread.sleep(speed);
       		} catch (InterruptedException e) {
       			// TODO Auto-generated catch block
       			e.printStackTrace();
       		}


            // Recur for all the vertices 
            // adjacent to current vertex 
            for (Integer i : adj[u]) { 
                if (!isVisited[i]) { 
                    // store current node 
                    // in path[] 
                    localPathList.add(i); 
                    dfsUtil(i, d, isVisited, localPathList); 

                    // remove current node 
                    // in path[] 
                    localPathList.remove(i); 
                } 
            } 
    		
    	}else return;

    }
    

    // Function that implements Dijkstra's 
    // single source shortest path 
    // algorithm for a graph represented  
    // using adjacency matrix 
    // representation 
    void dijkstra(int[][] adjacencyMatrix, 
                                        int startVertex) 
    { 
        int nVertices = adjacencyMatrix[0].length; 

        // shortestDistances[i] will hold the 
        // shortest distance from src to i 
        int[] shortestDistances = new int[nVertices]; 

        // added[i] will true if vertex i is 
        // included / in shortest path tree 
        // or shortest distance from src to  
        // i is finalized 
        boolean[] added = new boolean[nVertices]; 

        // Initialize all distances as  
        // INFINITE and added[] as false 
        for (int vertexIndex = 0; vertexIndex < nVertices;  
                                            vertexIndex++) 
        { 
            shortestDistances[vertexIndex] = Integer.MAX_VALUE; 
            added[vertexIndex] = false; 
        } 
          
        // Distance of source vertex from 
        // itself is always 0 
        shortestDistances[startVertex] = 0; 

        // Parent array to store shortest 
        // path tree 
        int[] parents = new int[nVertices]; 

        // The starting vertex does not  
        // have a parent 
        parents[startVertex] = NO_PARENT; 

        // Find shortest path for all  
        // vertices 
        for (int i = 1; i < nVertices; i++) 
        { 

            // Pick the minimum distance vertex 
            // from the set of vertices not yet 
            // processed. nearestVertex is  
            // always equal to startNode in  
            // first iteration. 
            int nearestVertex = -1; 
            int shortestDistance = Integer.MAX_VALUE; 
            for (int vertexIndex = 0; 
                     vertexIndex < nVertices;  
                     vertexIndex++) 
            { 
                if (!added[vertexIndex] && 
                    shortestDistances[vertexIndex] <  
                    shortestDistance)  
                { 
                    nearestVertex = vertexIndex; 
                    shortestDistance = shortestDistances[vertexIndex]; 
                } 
            } 

            // Mark the picked vertex as 
            // processed 
            if(nearestVertex == -1) break;
            if(nearestVertex == endIndex) {
                System.out.print(shortestDistances[endIndex] + "\t\t"); 
                printPath(endIndex, parents); 
            	break;
            } 
            added[nearestVertex] = true; 
            Window.setChecks(checks);
            checks++;
            if(nearestVertex != startingIndex && nearestVertex != endIndex)PolarGrid.sectors[nearestVertex].setColor(new Color(0,255,255));
            polargrid.repaint();
            try {
    			Thread.sleep(speed);
    		} catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}

            // Update dist value of the 
            // adjacent vertices of the 
            // picked vertex. 
            for (int vertexIndex = 0; 
                     vertexIndex < nVertices;  
                     vertexIndex++)  
            { 
            	int edgeDistance = adjacencyMatrix[nearestVertex][vertexIndex]; 
                  
                if (edgeDistance > 0
                    && ((shortestDistance + edgeDistance) <  
                        shortestDistances[vertexIndex]))  
                { 
                    parents[vertexIndex] = nearestVertex; 
                    shortestDistances[vertexIndex] = shortestDistance +  
                                                       edgeDistance; 
                } 
            } 
        } 

        
    } 
 

    // Function to print shortest path 
    // from source to currentVertex 
    // using parents array 
    void printPath(int currentVertex, 
                                  int[] parents) 
    { 
          
        // Base case : Source node has 
        // been processed 
        if (currentVertex == NO_PARENT) 
        { 
            return; 
        } 
        printPath(parents[currentVertex], parents); 
        System.out.print(currentVertex + " "); 
        Window.setPathlen(pathlen);
        pathlen++;
        if(currentVertex != startingIndex && currentVertex != endIndex)PolarGrid.sectors[currentVertex].setColor(Color.yellow);
        polargrid.repaint();
        try {
			Thread.sleep(speed);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    } 
    
    
  //Function to convert adjacency
  //list to adjacency matrix
   public int[][] convert(LinkedList<Integer> adj[],
                       int V)
  {
     
    // Initialize a matrix
    int [][]matrix = new int[V][V];

    for(int i = 0; i < V; i++) 
    {
        for(int j : adj[i]) {
        	matrix[i][j] = 1;
        }
            
    }
    return matrix;
  }
   
   
   public LinkedList <Integer> [] getAdj() {
	   return this.adj;
   }
    

 

}






