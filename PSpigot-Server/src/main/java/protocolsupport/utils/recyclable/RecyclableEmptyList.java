package protocolsupport.utils.recyclable;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RecyclableEmptyList<E>
        implements RecyclableCollection<E>
{
    private static final RecyclableEmptyList instance = new RecyclableEmptyList();

    public static <T> RecyclableEmptyList<T> get()
    {
        return instance;
    }

    public void recycle() {}

    public int size()
    {
        return 0;
    }

    public boolean isEmpty()
    {
        return true;
    }

    public boolean contains(Object o)
    {
        return false;
    }

    private static final Object[] EMPTY_ARRAY = new Object[0];

    public Object[] toArray()
    {
        return EMPTY_ARRAY;
    }

    public <T> T[] toArray(T[] a)
    {
        if (a.length > 0) {
            a[0] = null;
        }
        return a;
    }

    public boolean add(E e)
    {
        throw new UnsupportedOperationException();
    }

    public boolean remove(Object o)
    {
        throw new UnsupportedOperationException();
    }

    public boolean containsAll(Collection<?> c)
    {
        return false;
    }

    public boolean addAll(Collection<? extends E> c)
    {
        throw new UnsupportedOperationException();
    }

    public boolean removeAll(Collection<?> c)
    {
        throw new UnsupportedOperationException();
    }

    public boolean retainAll(Collection<?> c)
    {
        throw new UnsupportedOperationException();
    }

    public void clear()
    {
        throw new UnsupportedOperationException();
    }

    public Iterator<E> iterator()
    {
        return (Iterator<E>) EmptyIterator.EMPTY_ITERATOR;
    }

    private static class EmptyIterator<E>
            implements Iterator<E>
    {
        static final EmptyIterator<Object> EMPTY_ITERATOR = new EmptyIterator();

        public boolean hasNext()
        {
            return false;
        }

        public E next()
        {
            throw new NoSuchElementException();
        }

        public void remove()
        {
            throw new IllegalStateException();
        }
    }
}
