public class TransposeList<Type extends Comparable<Type>> {
    private MyLinkedList<Type> list;

    public TransposeList() {
        list = new MyLinkedList<>();
    }

    public void add(Type item) {
        list.addEnd(item); // Add the item at the end of the list
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
        for (Type current = list.first(); current != null; current = list.next()) {
            if (current.compareTo(item) == 0) {
                foundItem = current;
                if (list.getPreviousNode() != null) {
                    list.swapWithPrevious();
                }
                break;
            }
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
