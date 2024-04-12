public class MyArrayList<Type  extends Comparable<Type>>{
    private Type[] list;
    private int capacity;
    private int size;
    public long comparisons;
    public MyArrayList() {
        this.capacity = 16;
        this.list =  (Type[]) new Comparable[capacity];
        this.size = 0;
        this.comparisons = 0;
    }

    public void insert(Type item, int index) {
        if(size == capacity) {
            resize();
        }

        if (index < 0 || index > size) {
            return;
        }
        for (int i = size; i > index; i--) {
            list[i] = list[i - 1];
        }
        list[index] = item;
        size++;
    }
    public Type remove(int index) {
        if(index < 0 || index >= size) {
            return null;
        }
        Type removedItem = list[index];
        for(int i = index; i < size-1; i++) {
            list[i] = list[i+1];
        }
        size--;
        return removedItem;

    }
    public boolean contains(Type item) {
        boolean found = false;
        for (int i = 0; i < size; i++) {
            comparisons++;  // Increment comparisons outside the loop
            if (list[i].compareTo(item) == 0) {
                found = true;
                break;
            }
        }
        return found;
    }

    public int indexOf(Type item) {
        int foundIndex = -1;
        for (int i = 0; i < size; i++) {
            if (list[i].compareTo(item) == 0) {
                foundIndex = i;
                break;
            }
        }
        return foundIndex;
    }

    public void sort() {
        bubbleSort();
    }
    private void bubbleSort() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (list[j].compareTo(list[j + 1]) > 0) {
                    // Swap list[j] and list[j+1]
                    Type temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                }
            }
        }
    }
    public Type get(int index) {

        if (index < 0 || index >= this.size)
            return null;

        return this.list[index];
    }
    public void set(int index, Type item) {
        if(!(index < 0 || index >= capacity)) {
            list[index] = item;
        }
    }
    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    @Override
    public String toString() {
        if (list == null) {
            return "[]";
        }
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                result.append(", ");
            }
            result.append(list[i]);
        }
        result.append("]");
        return result.toString();
    }

    protected void resize() {
        capacity *= 2;
        Comparable[] newList = new Comparable[capacity];
        for (int i = 0; i < size; i++) {
            newList[i] = list[i];
        }
        list = (Type[]) newList;
    }
}