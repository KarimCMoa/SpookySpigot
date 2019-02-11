package protocolsupport.utils.recyclable;

import io.netty.util.Recycler;
import io.netty.util.Recycler.Handle;
import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RecyclableSingletonList<E>
        extends AbstractCollection<E>
        implements RecyclableCollection<E>
{
    private static final Recycler<RecyclableSingletonList> RECYCLER = new Recycler()
    {
        protected RecyclableSingletonList newObject(Recycler.Handle handle)
        {
            return new RecyclableSingletonList(handle);
        }
    };
    private final Recycler.Handle handle;
    protected E singleValue;

    public static <T> RecyclableSingletonList<T> create(T singleValue)
    {
        RecyclableSingletonList<T> list = (RecyclableSingletonList)RECYCLER.get();
        list.singleValue = singleValue;
        return list;
    }

    private RecyclableSingletonList(Recycler.Handle handle)
    {
        this.handle = handle;
    }

    public void recycle()
    {
        this.singleValue = null;
        RECYCLER.recycle(this, this.handle);
    }

    public int size()
    {
        return 1;
    }

    public boolean isEmpty()
    {
        return false;
    }

    public boolean contains(Object o)
    {
        return this.singleValue.equals(o);
    }

    private final RecyclableSingletonList<E>.ResetableIterator iterator = new ResetableIterator();

    public Iterator<E> iterator()
    {
        this.iterator.reset();
        return this.iterator;
    }

    protected class ResetableIterator
            implements Iterator<E>
    {
        private boolean hasNext = true;

        protected ResetableIterator() {}

        public boolean hasNext()
        {
            return this.hasNext;
        }

        public E next()
        {
            if (this.hasNext)
            {
                this.hasNext = false;
                return (E)RecyclableSingletonList.this.singleValue;
            }
            throw new NoSuchElementException();
        }

        public void reset()
        {
            this.hasNext = true;
        }
    }
}
