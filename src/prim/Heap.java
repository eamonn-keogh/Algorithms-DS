package prim;

import java.util.ArrayList;
import java.util.NoSuchElementException;

// Minimum Heap
public class Heap<E extends Comparable<E>>
{
    private int n;
    private int[] pq;
    private int[] hPos;
    private ArrayList<E> keys;

    public Heap(int maxN)
    {
        n = 0;
        pq = new int[maxN + 1];
        hPos = new int[maxN + 1];
        keys = new ArrayList<E>(maxN + 1);


        for (int i = 0; i <= maxN; i++)
            hPos[i] = -1;
    }

    public void insert(int i, E key)
    {
        n++;
        hPos[i] = n;
        pq[n] = i;
        keys.add(key);

        siftUp(n);
    }

    public int remove()
    {
        if (n == 0)
            throw new NoSuchElementException();

        int min = pq[1];

        swap(1, n--);

        siftDown(1);

        hPos[min] = -1;
        keys.set(min, null);
        pq[n + 1] = -1;

        return min;
    }

    public boolean isEmpty()
    {
        return n == 0;
    }

    public boolean contains(int i)
    {
        return hPos[i] != -1;
    }

    public void decreaseKey(int i, E key)
    {
        keys.add(key);
        siftUp(hPos[i]);
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
        while (2 * key <= n) 
        {
            int j = 2 * key;

            if (j < n && greater(j, j + 1))
                j++;
            if (!greater(key, j))
                break;

            swap(key, j);
            key = j;
        }
    }

    private boolean greater(int i, int j)
    {
        return pq[i] > pq[j];
    }

    private void swap(int i, int j)
    {
        int temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
        // E item = pq.get(i);
        // pq.set(i, pq.get(j));
        // pq.set(j, item);
    }
}