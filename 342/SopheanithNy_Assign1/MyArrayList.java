public class MyArrayList<Type> {
    private Type[] list;
    private int capacity;
    private int size;
    public MyArrayList() {
        this.capacity = 16;
        list = (Type[]) new Object[capacity];
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
        if (item == null) {
            for (int i = 0; i < size; i++) {
                if (list[i] == null) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (item.equals(list[i])) {
                    return true;
                }
            }
        }
        return false;
    }
    public int indexOf(Type item) {
        if (item == null) {
            return -1;
        }
        for (int i = 0; i < size; i++) {
            if (item.equals(get(i))) {
                return i;
            }
        }
        return -1;
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
        Type[] newList = (Type[]) new Object[doubleCap];
        for(int i = 0; i < size; i++) {
            newList[i] = list[i];
        }
        list = newList;
        capacity = doubleCap;
    }

}
