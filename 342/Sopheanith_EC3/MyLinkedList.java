public class MyLinkedList<Type extends Comparable<Type>> {
    protected class Node{
        protected Type item;
        protected Node next;
        public Node(Type item) {
            this.item = item;
            this.next = null;
        }
        @Override
        public String toString() {
            return item.toString();
        }
    }
    protected Node first;
    protected Node current;
    protected Node previous;
    protected int size;
    public long comparisons;
    // Constructor
    public MyLinkedList() {
        this.first = null;
        this.current = null;
        this.previous = null;
        this.size = 0;
        this.comparisons = 0;
    }
    public void addBefore(Type item) {
        Node newNode = new Node(item);
        if (current == null) {
            if (first == null) {
                first = newNode;
            } else {
                previous.next = newNode;
            }
        } else {
            newNode.next = current;

            if (current == first) {
                first = newNode;
            } else {
                previous.next = newNode;
            }
        }
        previous = newNode;
        size++;
    }
    public void addAfter(Type item) {
        if(current != null) {
            Node newNode = new Node(item);
            newNode.next = current.next;
            current.next = newNode;
            size++;
        }
    }
    public Type current() {
        if (current != null) {
            return current.item;
        } else {
            return null;
        }
    }
    public Type first() {
        if (first != null) {
            current = first;
            previous = null;
            return current.item;
        }


        return null;
    }
    public Type next() {
        if (current != null) {
            previous = current;
            current = current.next;
            if (current != null) {
                return current.item;
            } else {
                // If current is null, you might want to handle this case appropriately.
                // For now, I'm returning null.
                return null;
            }
        } else {
            return null;
        }
    }

    public Type remove() {
        if (current == null) {
            return null;
        }
        Type removedItem = current.item;

        if (current == first) {
            first = current.next;
            current = first;
            previous = null;
        } else {
            previous.next = current.next;
            current = current.next;
        }
        size--;
        return removedItem;
    }
    public boolean contains(Type item) {
        Node tempCurrent = first; // temp to not affect current
        boolean found = false;
        while(tempCurrent != null) {
            comparisons++;
            if (tempCurrent.item.compareTo(item) == 0) {
                found = true;
                break;
            }
            tempCurrent  = tempCurrent.next;

        }
        return found;
    }
    public void sort() {
        bubbleSort();
    }
    private void bubbleSort() {
        int n = size;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            Node current = first;
            Node next = first.next;
            for (int j = 0; j < n - i - 1; j++) {
                if (current.item.compareTo(next.item) > 0) {
                    Type temp = current.item;
                    current.item = next.item;
                    next.item = temp;
                    swapped = true;
                }
                current = next;
                next = next.next;
            }
            if (!swapped) {
                break;
            }
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
        StringBuilder result = new StringBuilder("[");
        Node tempCurrent = first;

        while(tempCurrent != null) {
            result.append(tempCurrent.item);
            if(tempCurrent.next != null) {
                result.append(", ");
            }
            tempCurrent = tempCurrent.next;
        }
        result.append("]");
        return result.toString();
    }
}