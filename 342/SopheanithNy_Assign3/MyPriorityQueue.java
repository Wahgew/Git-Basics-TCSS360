public class MyPriorityQueue<Type extends Comparable<Type>> {
    protected MyArrayList<Type> heap;
    public MyPriorityQueue() {
        this.heap = new MyArrayList<>();
    }
    public void insert(Type item) {
        heap.insert(item, heap.size());
        bubbleUp();
    }
    public Type removeMin() {
        Type min = heap.get(0);
        heap.set(0, heap.get(heap.size()-1));
        heap.remove(heap.size() - 1);
        sinkDown();
        return min;
    }
    public Type min() {
        return heap.get(0);
    }
    public int size() {
        return heap.size();
    }
    public boolean isEmpty() {
        return heap.isEmpty();
    }
    public String toString() {
        return heap.toString();
    }
    protected void bubbleUp() {
        int index = heap.size() -1;
        while (index > 0) {
            int parent = (index -1) /2;
            if (heap.get(index).compareTo(heap.get(parent)) < 0) {
                heap.swap(index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }
    protected void sinkDown() {
        int index = 0;
        while (left(index) < heap.size()) {
            int smallest = index;
            if (left(index) < heap.size() &&
                    heap.get(left(index)).compareTo(heap.get(smallest)) < 0) {
                smallest = left(index);
            }
            if (right(index) < heap.size() &&
                    heap.get(right(index)).compareTo(heap.get(smallest)) < 0) {
                smallest = right(index);
            }
            if (smallest != index) {
                heap.swap(index, smallest);
                index = smallest;
            } else {
                break;
            }
        }
    }
    protected int parent(int index) {
        return (index - 1)/2;
    }
    protected int right(int index) {
        return 2 * index  + 2;
    }
    protected int left(int index) {
        return 2 * index + 1;
    }
}
