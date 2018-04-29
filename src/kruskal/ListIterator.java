package kruskal;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListIterator<E> implements Iterator<E> 
{
    private Node<E> current;

    public ListIterator(Node<E> head)
    {
        this.current = head;
    }

    public boolean hasNext()
    {
        return current != null;
    }

    public E next()
    {
        if (!hasNext())
            throw new NoSuchElementException();
        
        E item = current.getItem();
        current = current.getNext();
        return item;
    }
}