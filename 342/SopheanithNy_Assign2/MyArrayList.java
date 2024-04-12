public class MyArrayList<Type extends Comparable<Type>> {
    private Type[] list;
    private int capacity;
    private int size;
    protected long comparisons;
    public MyArrayList() {
        this.capacity = 16;
        list = (Type[]) new Comparable[capacity];
        this.comparisons = 0;
        this.size = 0;
    }
    public void insert(Type item, int index) {
        if (size == capacity) {
            resize();
        }
        if (index < 0 || index > size) {
            return;
        }
        for (int i = size - 1; i >= index; i--) {
            list[i + 1] = list[i];
        }
        list[index] = item;
        size++;
    }
    public Type remove(int index) {
        if (index < 0 || index > size) {
            return null;
        } else {
            Type element = get(index);
            for (int i = index; i < size; i++) {
                list[i] = list[i+1];
            }
            size--;
            return element;
        }
    }
    public boolean contains(Type item) {
        for (int i = 0; i < size; i++) {
            comparisons++;
            if (list[i].compareTo(item) == 0) {
                return true;
            }
        }
        return false;
    }
    public int indexOf(Type item) {
        for (int i = 0; i < size; i++) {
            comparisons++;
            if (list[i].compareTo(item) == 0) {
                return i;
            }
        }
        return -1;
    }
    public void sort() {
        quickSort(0, size - 1);
    }

    private void quickSort(int low, int high) {
        if (low < high) {
            int pivotIndex = position(low, high);

            quickSort(low, pivotIndex - 1);
            quickSort(pivotIndex + 1, high);
        }
    }
    private int position(int low, int high) {
        Type pivot = (Type) list[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (((Type) list[j]).compareTo(pivot) <= 0) {
                i++;
                swap(i, j);
            }
            comparisons++;
        }
        swap(i + 1, high);
        return i + 1;
    }
    private void swap(int i, int j) {
        Object temp = list[i];
        list[i] = list[j];
        list[j] = (Type) temp;
    }
    public Type get(int index) {
        if (index < 0 || index > size) {
            return null;
        }
        return list[index];
    }

    public void set(int index, Type item) {
        if (index < 0 || index > size) {
            return;
        }
        list[index] = item;

    }
    public int size() {
        return size;
    }
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        String print = "[";
        for (int i = 0; i < size; i++) {
            print += list[i];
            if (i != size - 1) {
                print += ", ";
            }
        }
        print += "]";
        return print;
    }
    protected void resize() {
        if(size < capacity){
            return;
        }
        int doubleCap = capacity * 2;
        Type[] newList = (Type[]) new Comparable[doubleCap];
        for(int i = 0; i < size; i++) {
            newList[i] = list[i];
        }
        list = newList;
        capacity = doubleCap;
    }

}
