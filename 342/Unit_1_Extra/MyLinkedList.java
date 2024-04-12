/**
 * This class implements a linked list data structure.
 * @author Reis Cook
 */
public class MyLinkedList<Type extends Comparable<Type>> {
    private Node first;
    private Node last;
    private Node current;
    private Node previous;
    private int size;

    public MyLinkedList() {
        first = null;
        last = null;
        current = null;
        previous = null;
        size = 0;
    }

    protected class Node {
        Type item;
        Node next;

        Node(Type item) {
            this.item = item;
            this.next = null;
        }

        public String toString() {
            return item.toString();
        }
    }

    public void addBefore(Type item) {
        Node newNode = new Node(item);
        if (first == null) {
            first = newNode;
            last = newNode;
        } else if (current == first) {
            newNode.next = first;
            first = newNode;
        } else {
            newNode.next = current;
            previous.next = newNode;
        }
        previous = newNode;
        size++;
    }

    public void addAfter(Type item) {
        if (current == null) {
            return;
        }
        Node newNode = new Node(item);
        newNode.next = current.next;
        current.next = newNode;
        size++;
    }

    public void addEnd(Type item) {
        Node newNode = new Node(item);
        if (last == null) {
            first = newNode;
        } else {
            last.next = newNode;
        }
        last = newNode;
        size++;
    }

    public Type current() {
        return (current == null) ? null : current.item;
    }

    public Type first() {
        current = first;
        previous = null;
        return current();
    }

    public Type next() {
        if (current != null && current.next != null) {
            previous = current;
            current = current.next;
        } else {
            current = null;
        }
        return current();
    }

    public Type remove() {
        if (current == null) {
            return null;
        }
        Type item = current.item;
        if (current == first) {
            first = current.next;
            if (current == last) {
                last = null;
            }
        } else {
            previous.next = current.next;
            if (current == last) {
                last = previous;
            }
        }
        current = current.next;
        size--;
        return item;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node temp = first;
        while (temp != null) {
            sb.append(temp.item);
            temp = temp.next;
            if (temp != null) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public boolean contains(Type item) {
        Node current = first;
        while (current != null) {
            if (item.compareTo(current.item) == 0) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void swapWithPrevious() {
        if (previous != null && current != null) {
            Node temp = previous.next; // This is the current node
            previous.next = current.next;
            current.next = previous;
            if (previous == first) {
                first = current;
            } else {
                Node tempPrevious = first;
                while (tempPrevious.next != previous) {
                    tempPrevious = tempPrevious.next;
                }
                tempPrevious.next = current;
            }
            Node swapTemp = previous;
            previous = current;
            current = swapTemp;
        }
    }

    public void moveToFront(Node node) {
        if (node == null || node == first) {
            return; // No need to move if the node is null or already the first node
        }
        // Remove the node from its current position
        if (node == last) {
            last = previous;
        }
        previous.next = node.next;
        // Add the node to the front of the list
        node.next = first;
        first = node;
        // Reset the current pointer to the beginning
        current = first;
    }

    public Node getCurrentNode() {
        return current;
    }
    public Node getFirstNode() {
        return first;
    }
    public Node getPreviousNode() {
        return previous;
    }
    public Node getLastNode() {
        return last;
    }
}

