public class MTFList<Type extends Comparable<Type>> {
    private MyLinkedList<Type> list;

    public MTFList() {
        list = new MyLinkedList<>();
    }

    public void add(Type item) {
        list.addBefore(item);
        list.first(); // Reset the current pointer to the beginning
    }

    public Type remove(Type item) {
        Type removedItem = null;
        for (Type current = list.first(); current != null; current = list.next()) {
            if (current.compareTo(item) == 0) {
                removedItem = list.remove();
                break;
            }
        }
        return removedItem;
    }

    public Type find(Type item) {
        Type foundItem = null;
        MyLinkedList<Type>.Node previousNode = null;
        for (Type current = list.first(); current != null; current = list.next()) {
            if (current.compareTo(item) == 0) {
                foundItem = current;
                if (previousNode != null) {
                    // Move the found item to the front of the list
                    list.moveToFront(list.getCurrentNode());
                }
                break;
            }
            previousNode = list.getCurrentNode();
        }
        return foundItem;
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public String toString() {
        return list.toString();
    }
}
