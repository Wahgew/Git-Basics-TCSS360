public class MyHashTable<Key extends Comparable<Key>, Value> {
    protected Integer capacity;
    protected Key[] keyBuckets;
    protected Value[] valueBuckets;
    protected Integer size;
    public MyArrayList<Key> keys;
    public Integer comparisons;
    public Integer maxProbe;

    public MyHashTable(Integer capacity) {
        this.capacity = capacity;
        keyBuckets = (Key[]) new String[capacity]; //comparable
        valueBuckets = (Value[]) new Object[capacity]; //object
        keys = new MyArrayList<>();
        size = 0;
        comparisons = 0;
        maxProbe = 0;
    }

    private Integer hash(Key key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    public Value get(Key key) {
        //comparisons++;
        int index = hash(key);
        int probeCount = 0;
        while (keyBuckets[index] != null) {
            //comparisons++;
            probeCount++;
            if (keyBuckets[index].compareTo(key) == 0) {
                maxProbe = Math.max(maxProbe, probeCount);
                return valueBuckets[index];
            }
            index = (index + 1) % capacity;
        }
        maxProbe = Math.max(maxProbe, probeCount);
        return null;
    }
    public void put(Key key, Value value) {
        comparisons++;
        int index = hash(key);
        int count = 0;
        for (int i = index; i < capacity; i = (i+ 1) % capacity ) {
            if (keyBuckets [i] == null) {
                valueBuckets[i] = value;
                keyBuckets[i] = key;
                size++;
                count++;
                if (count > maxProbe) {
                    maxProbe = count;
                }
                return;
            } else if (keyBuckets[i].compareTo(key) == 0) {
                valueBuckets[i] = value;
                count++;
                if (count > maxProbe) {
                    maxProbe = count;
                }
                return;
            }
            count++;
            comparisons++;
        }
    }

    public Integer size() {
        return size;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (size == 0) {
            return "[]";
        } else {
            sb.append("[");
            for (int i = 0; i < capacity; i++) {
                if (valueBuckets[i] != null) {
                    sb.append(keyBuckets[i] + ":" + valueBuckets[i] + ", ");
                }
            }
        }
        sb.replace(sb.length() - 2, sb.length(), "");
        sb.append("]");
        return sb.toString();
    }

}
