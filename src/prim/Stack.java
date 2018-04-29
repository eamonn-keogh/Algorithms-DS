package prim;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<E> implements Iterable<E>
{
    private Node<E> head;
    private int size;

    public Stack()
    {
        this.head = null;
        this.size = 0;
    }

    public void push(E item)
    {
        head = new Node<E>(item, head);
        size++;
    } 

    public E pop()
    {
        if (this.isEmpty())
            throw new NoSuchElementException();
        
        E item = head.getItem();
        head = head.getNext();
        size--;

        return item;
    }

    public E peek()
    {
        if (this.isEmpty())
            throw new EmptyStackException();

        return head.getItem();
    }

    private boolean isEmpty()
    {
        return head == null;
    }

    public int getSize()
    {
        return this.size;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        for(E item : this)
            sb.append(item);

        return sb.toString();
    }

    public Iterator<E> iterator()
    {
        return new ListIterator<E>(head);
    }
}