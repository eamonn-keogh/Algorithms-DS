// Simple weighted graph representation 
// Uses an Adjacency Linked Lists, suitable for sparse graphs

package prim;

import java.io.*;

class Heap
{
    private int[] h;	   // heap array
    private int[] hPos;	   // hPos[h[k]] == k
    private int[] dist;    // dist[v] = priority of v

    private int N;         // heap size
   
    // The heap constructor gets passed from the Graph:
    //    1. maximum heap size
    //    2. reference to the dist[] array
    //    3. reference to the hPos[] array
    public Heap(int maxSize, int[] _dist, int[] _hPos) 
    {
        N = 0;
        h = new int[maxSize + 1];
        dist = _dist;
        hPos = _hPos;
    }


    public boolean isEmpty() 
    {
        return N == 0;
    }


    public void siftUp( int k) 
    {
        int v = h[k];

        // code yourself
        // must use hPos[] and dist[] arrays
		//Smallest value into distance 0 so it can be compared.
        dist[0] = 0;

        //While distance value at the current element.
        //Is less than the distance value at k / 2.
        //Keep dividing going up the list to insert the element
        //At the correct place.
        while(dist[v] < dist[h[k / 2]]) 
        {
            //If not found, then siftUp the value to next value.
            h[k] = h[k / 2];
            hPos[h[k]] = k;

            //Then divide the element by 2 to go up.
            k = k / 2;
        }

        //Insert Vertex into the correct place on to the heap array.
        h[k] = v;
        hPos[v] = k;
    }


    public void siftDown( int k) 
    {
        int v, j;
       
        v = h[k];  
        
        // code yourself 
        // must use hPos[] and dist[] arrays
		
		j = 2 * k;

        while(j <= N) {

            if((j + 1 <= N) && dist[h[j]] > dist[h[j + 1]]) 
            {
                j++; //Next element.
            }

            //If the distance of vertex element we are currently on is
            //Greater than the vertex we are sifting down then abort.
            if(dist[h[j]] >= dist[v]) 
            {
                break;

            } 
            else 
            { //If it's not then continue down the list.
                h[k] = h[j];
                k = j;
                j = k * 2;
            }
        }

        //When the right position is found put it into the array.
        h[k] = v;
        hPos[v] = k;
    }


    public void insert( int x) 
    {
        h[++N] = x;
        siftUp( N);
    }

    public int remove()
    {   
        int v = h[1];
        hPos[v] = 0; // v is no longer in heap
        h[N+1] = 0;  // put null node into empty spot
        
        h[1] = h[N--];
        siftDown(1);
        
        return v;
    }

} // end heap class

class Graph 
{
    class Node 
    {
        public int vert;
        public int wgt;
        public Node next;
    }
    
    // V = number of vertices
    // E = number of edges
    // adj[] is the adjacency lists array
    private int V, E;
    private Node[] adj;
    private Node z;
    private int[] mst;
    
    // used for traversing graph
    private int[] visited;
    private int id;
    
    
    // default constructor
    public Graph(String graphFile)  throws IOException
    {
        int u, v;
        int e, wgt;
        Node t;

        FileReader fr = new FileReader(graphFile);
		BufferedReader reader = new BufferedReader(fr);
	           
        String splits = " +";  // multiple whitespace as delimiter
		String line = reader.readLine();        
        String[] parts = line.split(splits);
        System.out.println("Vertices: " + parts[0] + " Edges: " + parts[1] + "\n");
        
        V = Integer.parseInt(parts[0]);
        E = Integer.parseInt(parts[1]);
        
        // create sentinel node
        z = new Node(); 
        z.next = z;
        
        // create adjacency lists, initialised to sentinel node z       
        adj = new Node[V+1];        
        for(v = 1; v <= V; ++v)
            adj[v] = z;               
        
       // read the edges
        for(e = 1; e <= E; ++e)
        {
            line = reader.readLine();
            parts = line.split(splits);
            u = Integer.parseInt(parts[0]);
            v = Integer.parseInt(parts[1]); 
            wgt = Integer.parseInt(parts[2]);
                        
            // write code to put edge into adjacency matrix
			// Putting edge into adjacency matrix into the linked list.
            t = new Node();
            t.wgt = wgt;
            t.vert = v;
            t.next = adj[u];
            adj[u] = t;

            t = new Node();
            t.wgt = wgt;
            t.vert = u;
            t.next = adj[v];
            adj[v] = t;			
            
        }	       
    }
   
    // convert vertex into char for pretty printing
    private char toChar(int u)
    {  
        return (char)(u + 64);
    }
    
    // method to display the graph representation
    public void display() 
    {
        int v;
        Node n;
        
        System.out.println("Adjacency List: ");
        for(v=1; v<=V; ++v)
        {
            System.out.print(toChar(v) + " -> " );
            for(n = adj[v]; n != z; n = n.next) 
                System.out.print("[" + toChar(n.vert) + ", " + n.wgt + "] ");   
            System.out.println(""); 
        }
        
    }


    
	public void MST_Prim(int s)
	{
        int v, u;
        int wgt, wgt_sum = 0;
        int[]  dist, parent, hPos;
        Node t;

        //code here
		dist = new int[V + 1];      // Distance from node to node.
        parent = new int[V + 1];    // Parent node.
        hPos = new int[V + 1];      // Current heap position.


        for(v = 0; v <= V; v++) 
        {

            dist[v] = Integer.MAX_VALUE; // dist is infinity.

            parent[v] = 0; // null vertex.

            hPos[v] = 0; // not on heap
        }

        // Creating a new empty priority heap.
        // V is the max size of the heap array.
        Heap pq =  new Heap(V, dist, hPos);

        // Insert first element into the heap, s is used a the root of the MST.
        pq.insert(s);
        
        dist[s] = 0;
        
        while (!pq.isEmpty()) 
        {
            v = pq.remove(); 	// Adding V to the MST.
            dist[v] = -dist[v]; // Marking V as in the MST.

            Node n;
            int w;

            // Examine each neighbour u of v.
            for (n = adj[v]; n != z; n = n.next) 
            {
                u = n.vert;
                w = n.wgt;

                if (w < dist[u]) 
                {
                    if (dist[u] != Integer.MAX_VALUE) 
                    {
                        wgt_sum -= dist[u];
                    }

                    dist[u] = w;
                    parent[u] = v;
                    wgt_sum += w;

                    if (hPos[u] == 0) 
                    {
                        pq.insert(u);

                    } 
                    else 
                    {
                        pq.siftUp(hPos[u]);
                    }
                }
            }
        }

        System.out.print("\n\nWeight of MST = " + wgt_sum + "\n");
        
        mst = parent; 
    }                    		
    
    public void showMST()
    {
            System.out.print("\n\nMinimum Spanning tree is:\n");
            for(int v = 1; v <= V; ++v)
                System.out.println(toChar(v) + " -> " + toChar(mst[v]));
            System.out.println("");
    }
} // end graph class

public class PrimLists 
{
    public static void main(String[] args) throws IOException
    {
        int s = 1;
        String fname = "myGraph.txt";               

        Graph g = new Graph(fname);
       
        g.display();

        g.MST_Prim(s);

        g.showMST();
                  
    }
}
