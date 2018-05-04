package prim;

public class PrimTree 
{
    private Edge[] edgeTo;
    private int[] distTo;
    private boolean[] marked;
    private Heap<Integer> pq;

    public PrimTree(Graph g)
    {
        edgeTo = new Edge[g.getV()];
        distTo = new int[g.getV()];
        marked = new boolean[g.getV()];
        pq = new Heap<Integer>(g.getV());

        for (int i = 0; i < g.getV(); i++) {
            distTo[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i <g.getV(); i++) {
            if (!marked[i])
                prim(g, i);
        }

    }

    private void prim(Graph g, int v)
    {
        distTo[v] = 0;
        pq.insert(v, distTo[v]);

        while (!pq.isEmpty())
        {
            int m = pq.remove();
            System.out.println(m);
            scan(g, m); 
        }
    }

    private void scan(Graph g, int v)
    {
        marked[v] = true;

        for (Edge e : g.adj(v))
        {
            int u = e.other(v);
            if (marked[u]) continue;
            if (e.getWeight() < distTo[u])
            {
                distTo[u] = e.getWeight();
                edgeTo[u] = e;

                if (pq.contains(u))
                    pq.decreaseKey(u, distTo[u]);
                else
                    pq.insert(u, distTo[u]);
            }
        }
    }

    // Returns the edges of the minimum spanning tree
    public Iterable<Edge> edges()
    {
        Queue<Edge> mst = new Queue<Edge>();

        for (int i = 0; i < edgeTo.length; i++) {
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
}