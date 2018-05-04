package prim;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class HeapIterator implements Iterator<Integer> 
{
    private Heap<Integer> current;

    public HeapIterator(int size)
    {
        current = new Heap<Integer>(size);
    }

    public boolean hasNext()
    {
        return !current.isEmpty();
    }

    public Integer next()
    {
        if (!hasNext())
            throw new NoSuchElementException();
        
        return current.remove();
    }
}