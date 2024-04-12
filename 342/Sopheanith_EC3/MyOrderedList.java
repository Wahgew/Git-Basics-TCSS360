public class MyOrderedList<Type extends Comparable<Type>>{
    protected MyArrayList<Type> list;
    public long comparisons;
    protected int size;

    public MyOrderedList() {
        list = new MyArrayList<>();
    }
    public void add(Type item) {
        // Adds the new item to the end of the list.
        list.insert(item, list.size());
        // sets current index to last index of the list
        int currentIndex = list.size() - 1;
        comparisons++;
        // loop to compare currentIndex and left neighbor of item
        while (currentIndex > 0 && item.compareTo(list.get(currentIndex - 1)) < 0) {
            comparisons++;
            Type leftNeighbor = list.get(currentIndex - 1);
            list.set(currentIndex - 1, item);
            list.set(currentIndex, leftNeighbor);
            currentIndex--;
        }
        size++; // Increment the size after the loop completes
    }

    public Type remove(Type item) {
        int index = list.indexOf(item);

        if(index != -1) {
            Type removedItem =  list.get(index);
            list.remove(index);
            return  removedItem;
        }
        else{
            return null;
        }

    }
    public Type binarySearch(Type item) {
        int start = 0;
        int finish = list.size() - 1;
        while(start <= finish) {
            int mid = (start + finish) / 2;
            int compare = item.compareTo(list.get(mid));
            comparisons++;

            if(compare == 0) {
                return list.get(mid);
            } else if(compare < 0) {
                finish = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return null;
    }

    public Type get(int index) {
        if (index >= 0 && index < list.size()) {
            return list.get(index);
        } else {
            return null; // Index out of bounds
        }
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
