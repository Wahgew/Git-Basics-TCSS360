public class MyLinkedList<Type extends Comparable<Type>> {

    protected class Node {  //inner class
        protected Type item;
        protected Node next;
        public Node(Type it, Node next) {
            this.item = it;
        }
        public String toString() {
            return item.toString();
        }
    }
    protected Node first;
    protected Node current;
    protected Node previous;
    protected int size;
    protected long comparisons;

    public MyLinkedList() {
        this.first = null;
        this.current = null;
        this.previous = null;
        this.size = 0;
    }
    public void addBefore(Type item) {
        Node newNode = new Node(item, current);
        if (current == null) {
            if (first == null) {
                first = newNode;
            } else {
                previous.next = newNode;
            }
            previous = newNode;
        } else {
            newNode.next = current;
            if (previous != null) {
                previous.next = newNode;
            } else {
                first = newNode;
            }
            previous = newNode;
        }
        size++;
    }
    public void addAfter(Type item) {
        if (current == null) {
            return;
        }
        Node newNode = new Node(item, current);
        newNode.next = current;
        newNode.next = current.next;
        current.next = newNode;
        size++;
    }
    public Type current() {
        if (current == null) {
            return null;
        }
        return current.item;
    }
    public Type first() {
        current = first;
        if (current == null) {
            return null;
        }
        return current.item;
    }
    public Type next() {
        previous = current;
        current = current.next;
        if (current == null) {
            return null;
        }
        return current.item;
    }
    public Type remove() {
        if (current == null) {
            return null;
        } else {
            Node remove = current;
            if (current == first) {
                first = current.next;
            } else {
                previous.next = current.next;
            }
            current = current.next;
            size--;
            return remove.item;
        }
    }
    public boolean contains(Type item) {
        Node curr = first;
        while (curr != null) {
            comparisons++;
            if (curr.item.compareTo(item) == 0) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }
    public void sort() {
        first = mergeSort(first);
    }
    private Node mergeSort(Node start) {
        if (start == null || start.next == null) {
            return start;
        }
        Node middle = getMiddle(start);
        Node nextOfMiddle = middle.next;
        middle.next = null;
        Node left = mergeSort(start);
        Node right = mergeSort(nextOfMiddle);
        return merge(left, right);
    }
    private Node merge(Node left, Node right) {
        Node result = null;
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        if (left.item.compareTo(right.item) <= 0) {
            result = left;
            result.next = merge(left.next, right);
        } else {
            result = right;
            result.next = merge(left, right.next);
        }
        return result;
    }
    private Node getMiddle(Node head) {
        if (head == null) {
            return head;
        }
        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
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
        StringBuilder result = new StringBuilder();
        Node current = first;
        result.append("[");
        while (current != null) {
            result.append(current.toString());
            current = current.next;
            if (current != null) {
                result.append(", ");
            }
        }
        result.append("]");
        return result.toString();
    }
}
