package prim;

public class PrimTree 
{
    private Edge[] edgeto;
    private int[] distTo;
    private boolean[] visited;
    private Heap<Integer> pq;

    public PrimTree(Graph g)
    {
        this.edgeto = new Edge[g.getV()];
        this.distTo = new int[g.getV()];
        this.visited = new boolean[g.getV()];
        this.pq = new Heap<Integer>();

        // Initialise distances to infinity for each vertex 
        for (int i = 0; i < g.getV(); i++) 
            distTo[i] = Integer.MAX_VALUE;
        
        // Visit each node and add to mst
        for (int i = 0; i < g.getV(); i++)
        {
            if (!visited[i])
            {
                prim(g, i);
            }
        }
    }

    private void prim(Graph g, int vertex)
    {
        distTo[vertex] = 0;
        pq.insert(distTo[vertex]);

        while (!pq.isEmpty())
        {
            int v = pq.remove();
            System.out.println(v);
        }
    }

    // Returns the edges of the minimum spanning tree
    public Iterable<Edge> edges()
    {
        Queue<Edge> mst = new Queue<Edge>();
        for (int i = 0; i < edgeto.length; i++) 
        {
            Edge e = edgeto[i];

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
}