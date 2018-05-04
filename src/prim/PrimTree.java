package prim;

public class PrimTree 
{
    private Edge[] edgeTo;
    private double[] distTo;
    private boolean[] visited;
    private Heap<Double> pq;

    public PrimTree(Graph g)
    {
        this.edgeTo = new Edge[g.getV()];
        this.distTo = new double[g.getV()];
        this.visited = new boolean[g.getV()];
        this.pq = new Heap<Double>(g.getV());

        // Initialise distances to infinity for each vertex 
        for (int i = 0; i < g.getV(); i++) 
            distTo[i] = Double.POSITIVE_INFINITY;
        
        // Visit each node and add to mst
        for (int i = 0; i < g.getV(); i++)
        {
            if (!visited[i])
                prim(g, i);
        }
    }

    private void prim(Graph g, int vertex)
    {
        distTo[vertex] = 0.0f;

        pq.insert(vertex, distTo[vertex]);

        while (!pq.isEmpty())
        {
            int u = pq.remove();
            scan(g, u);
        }
    }

    private void scan(Graph g, int u)
    {
        visited[u] = true;

        for (Edge e : g.adj(u)) 
        {
            int v = e.other(u);

            if (e.getWeight() < distTo[v])
            {
                distTo[v] = e.getWeight();
                edgeTo[v] = e;

                if (pq.contains(v))
                    pq.decreaseKey(v, distTo[v]);
                else
                    pq.insert(v, distTo[v]);
            }
        }
    }

    // Returns the edges of the minimum spanning tree
    public Iterable<Edge> edges()
    {
        Queue<Edge> mst = new Queue<Edge>();
        for (int i = 0; i < edgeTo.length; i++) 
        {
            Edge e = edgeTo[i];

            if (e != null)
                mst.enQueue(e);
            
        }
        return mst;
    }

    public int getWeight()
    {
        int weight = 0;

        for (Edge e : edges())
            weight += e.getWeight();
        
        return weight;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("Minimum Spanning Tree: " + this.getWeight() + " weighting" + "\n");
        for (Edge e : this.edges())
            sb.append(e + "\n");

        return sb.toString();
    }

    private char toChar(int item)
    {
        return (char) (item + 64);
    }
}