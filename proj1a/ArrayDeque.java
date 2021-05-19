public class ArrayDeque<T> {
    private int totalSize;
    private T[] items;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque(){
        totalSize = 8;
        items = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
    }

    public ArrayDeque(ArrayDeque other){
        totalSize = other.totalSize;
        items = (T[]) other.items;
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
    }

    private void upsize(){
        T[] a = (T[]) new Object[totalSize*2];
        System.arraycopy(items, nextFirst, a, 0, totalSize - nextFirst);
        System.arraycopy(items, 0, a, totalSize - nextFirst, nextFirst);
        items = a;
        nextFirst = 0;
        nextLast = totalSize - 1;
        totalSize *= 2;
    }

    private void downsize(){
        T[] a = (T[]) new Object[totalSize/2];
        System.arraycopy(items, nextFirst, a, 0, totalSize - nextFirst);
        System.arraycopy(items, 0, a, totalSize - nextFirst, nextFirst);
        items = a;
        nextFirst = 0;
        nextLast = totalSize - 1;
        totalSize /= 2;
    }

    public void addFirst(T item){
        if ((nextLast + 1)%totalSize == nextFirst){
            upsize();
        }
        items[nextFirst] = item;
        nextFirst = (nextFirst - 1 + totalSize)%totalSize;
    }

    public T removeFirst(){
        int firstIndex = (nextFirst + 1)%totalSize;
        T first = items[firstIndex];
        items[firstIndex] = null;
        nextFirst = firstIndex;
        if (size() < totalSize/2){
            downsize();
        }
        return first;
    }

    public void addLast(T item){
        if ((nextLast + 1)%totalSize == nextFirst) {
            upsize();
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1)%totalSize;
    }

    public T removeLast(){
        int lastIndex = (nextLast - 1 + totalSize)%totalSize;
        T last = items[lastIndex];
        items[nextLast] = null;
        nextLast = lastIndex;
        if (size() < totalSize/2){
            downsize();
        }
        return last;
    }

    public int size(){
        if (nextFirst > nextLast){
            return nextLast + totalSize - 1 - nextFirst;
        }
        return nextLast - 1 - nextFirst;
    }

    public T get(int index){
        return items[(nextFirst + 1 + index)%totalSize];
    }

    public boolean isEmpty(){
        int currSize = size();
        return (currSize == 0);
    }

    public void printDeque(){
        int at = (nextFirst + 1)%totalSize;
        while (at != nextLast){
            System.out.println(items[at]);
            at = (at + 1)%totalSize;
        }
    }
}
