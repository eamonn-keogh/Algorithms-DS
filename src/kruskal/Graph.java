package kruskal;

import java.io.BufferedReader;
import java.io.FileReader;

public class Graph
{
    private int V;
    private int E;
    private Queue<Edge>[] adjacency;
    private int maxWeight;

    public Graph(String file)
    {
        // open with resources (closes file automatically)
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) 
        {
            String line = reader.readLine();
            String regex = "\\s+";
            String[] parse = line.split(regex);

            this.V = Integer.parseInt(parse[0]);
            this.E = Integer.parseInt(parse[1]);

            System.out.println("Vertices: " + this.getV() + " Edges: " + this.getE() + "\n");

            adjacency = (Queue<Edge>[]) new Queue[V];

            // initialise empty adjaceny list
            for (int i = 0; i < V; i++) 
                adjacency[i] = new Queue<Edge>();

            for (int i = 0; i < E; i++)
            {
                line = reader.readLine();
                parse = line.split(regex);

                int u = Integer.parseInt(parse[0]);
                int v = Integer.parseInt(parse[1]);
                int weight = Integer.parseInt(parse[2]);
                maxWeight += weight;

                adjacency[u].enQueue(new Edge(u, v, weight));
            }

        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    public int getV()
    {
        return this.V;
    }

    public int getE()
    {
        return this.E;
    }

    public int getWeight()
    {
        return this.maxWeight;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("Adjacency List: " + maxWeight + " weighting" + "\n");

        for (int i = 1; i < adjacency.length; i++) 
                sb.append(toChar(i) + " -> " + adjacency[i] + "\n");

        return sb.toString();
    }

    public Iterable<Edge> adj(int vertex)
    {
        return adjacency[vertex];
    }

    // returns all the edges from the graph as iterable
    public Iterable<Edge> edges()
    {
        Queue<Edge> list = new Queue<Edge>();

        for (int i = 0; i < this.getV(); i++) 
        {
            int selfLoops = 0;
            for (Edge e : adj(i))
            {
                if (e.other(i) > i)
                    list.enQueue(e);
                else if (e.other(i) == i)
                {
                    if (selfLoops % 2 == 0)
                        list.enQueue(e);
                    selfLoops++;
                }
            }
        }
        return list;
    }

    private char toChar(int item)
    {
        return (char) (item + 64);
    }
}