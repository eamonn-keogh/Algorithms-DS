package kruskal;

public class Main 
{
    public static void main(String[] args) 
    {
        Graph graph = new Graph(args[0]);
        System.out.println(graph);

        KruskalTree kruskal = new KruskalTree(graph);
        System.out.println(kruskal);        
    }
}