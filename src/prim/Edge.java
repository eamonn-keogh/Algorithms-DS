package prim;

public class Edge implements Comparable<Edge>
{
    private int u;
    private int v;
    private int weight;

    public Edge()
    {
        this.u = 0;
        this.v = 0;
        this.weight = 0;
    }

    public Edge(int u, int v, int weight)
    {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    public int getWeight()
    {
        return this.weight;
    }

    public int either()
    {
        return this.v;
    }

    public int other(int vertex)
    {
        if (vertex == v)
            return u;
        else 
            return v; 
    }

    // comparison between two edges weight
    public int compareTo(Edge that)
    {
        return Integer.compare(this.weight, that.weight);
    }

    private char toChar(int item)
    {
        return (char) (item + 64);
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("[" + toChar(this.v) + ", " + this.weight + "] ");
        
        return sb.toString();
    }

   
}