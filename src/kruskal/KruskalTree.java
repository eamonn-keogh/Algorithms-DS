package kruskal;

public class KruskalTree
{
    private Queue<Edge> mst;
    private Heap<Edge> pq;
    private int weight;

    public KruskalTree(Graph g)
    {
        mst = new Queue<Edge>();
        pq = new Heap<Edge>();
        this.weight = 0;
        
        // loops thorough each iterable edge from graph
        for (Edge e : g.edges())
            pq.insert(e); // puts each edge in heap 
                
        UnionFind uf = new UnionFind(g.getV());

        while (mst.getSize() < g.getV() - 1)
        {
            Edge e = pq.remove();
            int u = e.either();
            int v = e.other(u);

            if (!uf.isConnected(u, v))
            {
                uf.union(u, v);
                mst.enQueue(e);
                weight += e.getWeight();
            }
        }
    }

    public Iterable<Edge> edges()
    {
        return mst;
    }

    public int getWeight()
    {
        return this.weight;
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