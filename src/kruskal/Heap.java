package kruskal;

import java.util.ArrayList;

// Minimum Heap
public class Heap<E extends Comparable<E>>
{
    // used arraylist as work around instead of generic array[]
    private ArrayList<E> pq;
    private int size;

    public Heap()
    {
        this.pq = new ArrayList<E>();
        this.size = 0;
    }

    public void insert(E item)
    {
        // inserts items at the bottom on the heap
        pq.add(item);
        
        // gets the size of the arraylist n - 1
        size = pq.size() - 1;

        // heapify arraylist
        siftUp(size);
    }

    public E remove()
    {
        // retrieve the value from top of heap
        E item = pq.get(0);

        // swap the top key with the bottom key
        swap(0, size--);

        // heapify based on key 0
        siftDown(0);

        // clear the loitering key, shrinks the heap by assigning null
        pq.set(size + 1, null);

        return item;
    }

    // returns the item at the head of heap
    public E peek()
    {
        return this.pq.get(0);
    }

    private void siftUp(int key)
    {
        while (key > 0 && greater(key / 2, key))
        {
            swap(key, key / 2);
            key = key / 2;
        }
    }

    private void siftDown(int key)
    {
        while (2 * key <= size) 
        {
            int j = 2 * key;

            if (j < size && greater(j, j + 1))
                j++;
            if (!greater(key, j))
                break;

            swap(key, j);
            key = j;
        }
    }

    private boolean greater(int i, int j)
    {
        return pq.get(i).compareTo(pq.get(j)) > 0;
    }

    private void swap(int i, int j)
    {
        E item = pq.get(i);
        pq.set(i, pq.get(j));
        pq.set(j, item);
    }

    public int getSize()
    {
        return this.pq.size();
    }

    public boolean isEmpty()
    {
        return this.size == 0;
    }
    
    public String toString()
    {
        return this.toString();
        // StringBuilder sb = new StringBuilder();
        
        // sb.append("[ ");
        // for (int i = 0; i < pq.size(); i++)
        // { 
        //     if (i == pq.size() - 1)
        //         sb.append(pq.get(i));
        //     else
        //         sb.append(pq.get(i) + ", ");
        // }
        // sb.append(" ]");
        
        // return sb.toString();
    }
}