public class ArrayDeque<T> {
    private static final int INIT_SIZE = 8;
    private static final int FACTOR = 2;
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;   

    public ArrayDeque() {
        items = (T[]) new Object[INIT_SIZE];
        size = 0;
        nextFirst = items.length / 2;
        nextLast = nextFirst + 1;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int leftSize = items.length - nextLast;
        int rightSize = items.length - leftSize;
        System.arraycopy(items, (nextFirst + 1) % items.length, a, a.length / 4, leftSize);
        nextFirst = minusOne(a.length / 4);
        System.arraycopy(items, 0, a, nextFirst + leftSize + 1, rightSize);
        nextLast = nextFirst + items.length + 1;
        items = a;
    }

    private int minusOne(int index) {
        if (index == 0) {
            return items.length - 1;
        }
        return index - 1;
    }

    private int plusOne(int index) {
        if (index == items.length - 1) {
            return 0;
        }
        return index + 1;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size += 1;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (isEmpty()) {
            return;
        }
        int start = (nextFirst + 1) % items.length;
        int end = (nextLast - 1 + items.length) % items.length;
        while (start != end) {
            System.out.print(items[start].toString() + ' ');
            start = plusOne(start);
        }
        System.out.println(items[start].toString());
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        nextFirst = plusOne(nextFirst);
        T data = items[nextFirst];
        items[nextFirst] = null;
        size -= 1;
        return data;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        nextLast =  minusOne(nextLast);
        T data = items[nextLast];
        items[nextLast] = null;
        size -= 1;
        return data;
    }

    public T get(int index) {
        if (isEmpty() || index < 0 || index > size) {
            return null;
        }
        int t = plusOne(nextFirst) + index;
        t %= items.length;
        return items[t];
    }
}
