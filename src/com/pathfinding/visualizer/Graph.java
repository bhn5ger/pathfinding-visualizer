package com.pathfinding.visualizer;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
	private double weightedPathlen = 0;
	private int startingIndex, endIndex, speed;
	private PolarGrid polargrid;
    private int V;   // No. of vertices
    private LinkedList<Integer> adj[]; //Adjacency Lists
    private double [][] adjMatrix = new double[176][176];
 
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
 
    // function to print the shortest distance and path
    // between source vertex and destination vertex
    void printBFS()
    {
        // predecessor[i] array stores predecessor of
        // i and distance array stores distance of i
        // from s
        int pred[] = new int[V];
        int dist[] = new int[V];

        if (BFS(adj, startingIndex, endIndex, V, pred, dist) == false) {
            System.out.println("Given source and destination" + "are not connected");
            return;
        }

        // LinkedList to store path
        LinkedList<Integer> path = new LinkedList<Integer>();
        int crawl = endIndex;
        path.add(crawl);
        while (pred[crawl] != -1) {
            path.add(pred[crawl]);
            crawl = pred[crawl];
        }

        // Print distance
        System.out.println("Shortest path length is: " + dist[endIndex]);

        // Print path
        System.out.println("Path is ::");
        for (int i = path.size() - 1; i >= 0; i--) {
            //System.out.print(path.get(i) + " ");
            System.out.print(path.get(i) + " "); 
            if((i-1) != -1)System.out.println(path.get(i-1));
            if((i-1) != -1)paintPath(path.get(i), path.get(i-1));
            else paintPath(path.get(i), 12134);
        }
    }

    // a modified version of BFS that stores predecessor
    // of each vertex in array pred
    // and its distance from source in array dist
    boolean BFS(LinkedList <Integer> adj [], int src, int dest, int v, int pred[], int dist[])
    {
        // a queue to maintain queue of vertices whose
        // adjacency list is to be scanned as per normal
        // BFS algorithm using LinkedList of Integer type
        LinkedList<Integer> queue = new LinkedList<Integer>();

        // boolean array visited[] which stores the
        // information whether ith vertex is reached
        // at least once in the Breadth first search
        boolean visited[] = new boolean[v];

        // initially all vertices are unvisited
        // so v[i] for all i is false
        // and as no path is yet constructed
        // dist[i] for all i set to infinity
        for (int i = 0; i < v; i++) {
            visited[i] = false;
            dist[i] = Integer.MAX_VALUE;
            pred[i] = -1;
        }

        // now source is first to be visited and
        // distance from source to itself should be 0
        visited[src] = true;
        dist[src] = 0;
        queue.add(src);

        // bfs Algorithm
        while (!queue.isEmpty()) {
            int u = queue.remove();
            Iterator<Integer> i = adj[u].listIterator();
            while (i.hasNext())
            {
           	 int n = i.next();
                if (visited[n] == false) {
                    visited[n] = true;
                    paintChecks(n);
                    dist[n] = dist[u] + 1;
                    pred[n] = u;
                    queue.add(n);

                    // stopping condition (when we find
                    // our destination)
                    if (n == dest)
                        return true;
                }
            }
           

            
        }
        return false;
    }
    
    
    
    void DFS() 
    { 
        boolean[] isVisited = new boolean[V]; 
        ArrayList<Integer> pathList = new ArrayList<>(); 

        // add source to path[] 
        pathList.add(startingIndex); 

        // Call recursive utility 
        dfsUtil(startingIndex, endIndex, isVisited, pathList); 
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
                    System.out.print(localPathList.get(i) + " "); 
                    if((i+1) < localPathList.size())System.out.println(localPathList.get(i+1));
                	if((i+1) < localPathList.size())paintPath(localPathList.get(i), localPathList.get(i+1));
                	else paintPath(localPathList.get(i), 12134);
                }
                pathFound = true;
                // if match found then no need to traverse more till depth 
                return; 
            } 

            // Mark the current node
            isVisited[u] = true; 
            //if(u == d) break;
            paintChecks(u);


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
    void dijkstra() 
    { 
        int nVertices = adjMatrix[0].length; 

        // shortestDistances[i] will hold the 
        // shortest distance from src to i 
        double[] shortestDistances = new double[nVertices]; 

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
        shortestDistances[startingIndex] = 0; 

        // Parent array to store shortest 
        // path tree 
        int[] parents = new int[nVertices]; 

        // The starting vertex does not  
        // have a parent 
        parents[startingIndex] = NO_PARENT; 

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
            double shortestDistance = Integer.MAX_VALUE; 
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
            paintChecks(nearestVertex);

            // Update dist value of the 
            // adjacent vertices of the 
            // picked vertex. 
            for (int vertexIndex = 0; 
                     vertexIndex < nVertices;  
                     vertexIndex++)  
            { 
            	double edgeDistance = adjMatrix[nearestVertex][vertexIndex]; 
                  
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
    void printPath(int currentVertex, int[] parents) 
    { 
          
        // Base case : Source node has 
        // been processed 
        if (currentVertex == NO_PARENT) 
        { 
            return; 
        } 
        printPath(parents[currentVertex], parents); 
        System.out.print(parents[currentVertex] + " "); 
        System.out.println(currentVertex);
        paintPath(parents[currentVertex], currentVertex);
    } 
    
    
   public void paintChecks(int x) {
       Window.setChecks(checks);
       checks++;
       if(x != startingIndex && x != endIndex)PolarGrid.sectors[x].setColor(new Color(0,255,255));
       polargrid.repaint();
       try {
			Thread.sleep(speed);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }
   public void paintPath(int x, int y) {
	   Window.setPathlen(pathlen);
       pathlen++;
       if(x != -1 && y != 12134)weightedPathlen += adjMatrix[x][y];
       Window.setWeightedPathlen(round(weightedPathlen, 2));
       if(y != startingIndex && y != endIndex && y!= 12134)PolarGrid.sectors[y].setColor(Color.yellow);
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
   public double[][] convert(LinkedList<Integer> adj[], int V)
  {
     
    // Initialize a matrix
    double [][]matrix = new double[V][V];
    int weightFactor = 11;

    for(int i = 0; i < V; i++) 
    {
        for(int j : adj[i]) {
        	
        	if((j != i + 16) && (j != i - 16)) {
        		matrix[i][j] = weightFactor * (Math.PI/8);
        	}else {
        		matrix[i][j] = 1;
        	}
        	
        }
        if( i == 15 || i == 31 || i == 47 || i == 63 || i == 79 || i == 95 || i == 111 || i == 127 || i == 143 || i == 159 || i == 175) {
        	weightFactor--;
        }
            
    }
    return matrix;
  }
   public double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = BigDecimal.valueOf(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
   
   
   public LinkedList <Integer> [] getAdj() {
	   return this.adj;
   }
   public void setAdjMatrix(double [][] adj) {
	   this.adjMatrix = adj;
   }
   public double [][] getAdjMatrix(){
	   return this.adjMatrix;
   }
    

 

}






