
public class MyOrderedList<Type extends Comparable<Type>> {
    private MyArrayList<Type> list;
    public long comparisons;
    public MyOrderedList() {
        list = new MyArrayList<>();
        comparisons = 0;
    }
    public void add(Type item) {
        int index = 0;
        comparisons++;
        while (index < list.size() &&
                item.compareTo(list.get(index)) > 0) {
            comparisons++;
            index++;
        }
        list.insert(item, index);
    }
    public Type remove(Type item) {
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).compareTo(item) == 0) {
                return list.remove(i);
            }
        }
        return null;
    }
    private boolean binarySearch(Type item, int start, int finish) {
        int low = 0;
        int high = list.size()-1;

        while(low <= high) {
            int mid = (low + high) / 2;
            comparisons++;
            if(list.get(mid).compareTo(item) < 0) {
                low = mid + 1;
            }
            else if(list.get(mid).compareTo(item) > 0) {
                high = mid - 1;
            }
            else {
                return true;
            }
        }
        return false;
    }
    public boolean binarySearch(Type item) {
        return binarySearch(item, 0, list.size() -1);
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
