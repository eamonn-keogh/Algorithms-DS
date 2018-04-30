package prim;

public class Main 
{
    public static void main(String[] args) 
    {
        Graph graph = new Graph(args[0]);
        System.out.println(graph);

        PrimTree prim = new PrimTree(graph);
        System.out.println(prim);

    }
}