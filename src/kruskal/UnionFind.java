package kruskal;

public class UnionFind
{
    private int[] id;
    private int[] rank;
    private int size;

    public UnionFind(int n)
    {
        this.size = n + 1;
        id = new int[size];
        rank = new int[size];

        for (int i = 0; i < n; i++)
        {
            id[i] = i;
            rank[i] = 0;
        }
    }

    private int find(int i)
    {
        while (i != id[i])
        {
            // path compression
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    public boolean isConnected(int i, int j)
    {
        return find(i) == find(j);
    }

    public void union(int set1, int set2)
    {
        int i = find(set1);
        int j = find(set2);
        
        if (i == j)
            return;
        
        if (rank[i] < rank[j])
            id[i] = j;
        else if (rank[i] > rank[j])
            id[j] = i;
        else 
        {
            id[i] = j;
            rank[j]++;
        }
        this.size--;
    }

    public int getSize()
    {
        return this.size;
    }
}