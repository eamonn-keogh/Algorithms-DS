package kruskal;

public class Node<E>
{
    private E item;
    private Node<E> next;

    public Node()
    {
        this.item = null;
        this.next = null;
    }

    public Node(E item)
    {
        this.item = item;
        this.next = null;
    }

    public Node(E item, Node<E> next)
    {
        this.item = item;
        this.next = next;
    }

    public void setE(E item)
    {
        this.item = item;
    }

    public void setNext(Node<E> next)
    {
        this.next = next;
    }

    public E getItem()
    {
        return this.item;
    }

    public Node<E> getNext()
    {
        return this.next;
    }
}