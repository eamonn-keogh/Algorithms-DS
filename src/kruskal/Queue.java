package kruskal;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<E> implements Iterable<E>
{
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public Queue()
    {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void enQueue(E item)
    {
        Node<E> newNode = new Node<E>(item, null);

        if (this.isEmpty())
            head = newNode;
        else
            tail.setNext(newNode);

        tail = newNode;
        size++;
    }

    public E deQueue()
    {
        if (this.isEmpty())
            throw new NoSuchElementException();
        
        E item = head.getItem();
        head = head.getNext();
        size--;

        if (this.isEmpty())
            tail = null;

        return item;
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

        for (E item : this)
            sb.append(item);

        return sb.toString();
    }

    public Iterator<E> iterator() 
    {
        return new ListIterator<E>(head);
	}
}