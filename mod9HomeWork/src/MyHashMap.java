//import java.util.Arrays;
public class MyHashMap<K,V> {
    NodeHashMap<K,V>[] table;
    int threshold;
    static final int MAXIMUM_CAPACITY = 1 << 30;
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private int modCount;
    float loadFactor;
    private int size;


    void afterNodeInsertion(boolean evict) { }
    void afterNodeRemoval(NodeHashMap<K,V> p) { }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    public V put(K key, V value) {

        return putVal(hash(key), key, value, false, true);
    }

    public V get(Object key) {
        NodeHashMap<K,V> e;
        return (e = getNode(hash(key), key)) == null ? null : e.value;
    }
    public int size() { return size; }
    public void clear() {
        NodeHashMap<K,V>[] tab;
        modCount++;
        if ((tab = table) != null && size > 0) {
            size = 0;
            for (int i = 0; i < tab.length; ++i)
                tab[i] = null;
        }
    }
    public V remove(Object key) {
        NodeHashMap<K,V> e;
        return (e = removeNode(hash(key), key, null, false, true)) == null ?
                null : e.value;
    }

    final NodeHashMap<K,V>[] resize() {
        NodeHashMap<K,V>[] oldTab = table;
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        int oldThr = threshold;
        int newCap, newThr = 0;
        if (oldCap > 0) {
            if (oldCap >= MAXIMUM_CAPACITY) {
                threshold = Integer.MAX_VALUE;
                return oldTab;
            }
            else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                    oldCap >= DEFAULT_INITIAL_CAPACITY)
                newThr = oldThr << 1;
        }
        else if (oldThr > 0)
            newCap = oldThr;
        else {
            newCap = DEFAULT_INITIAL_CAPACITY;
            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
        if (newThr == 0) {
            float ft = (float)newCap * loadFactor;
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
                    (int)ft : Integer.MAX_VALUE);
        }
        threshold = newThr;

        NodeHashMap<K,V>[] newTab = (NodeHashMap<K,V>[])new NodeHashMap[newCap];
        table = newTab;
        if (oldTab != null) {
            for (int j = 0; j < oldCap; ++j) {
                NodeHashMap<K,V> e;
                if ((e = oldTab[j]) != null) {
                    oldTab[j] = null;
                    if (e.next == null)
                        newTab[e.hash & (newCap - 1)] = e;
                    else {
                        NodeHashMap<K,V> loHead = null, loTail = null;
                        NodeHashMap<K,V> hiHead = null, hiTail = null;
                        NodeHashMap<K,V> next;
                        do {
                            next = e.next;
                            if ((e.hash & oldCap) == 0) {
                                if (loTail == null)
                                    loHead = e;
                                else
                                    loTail.next = e;
                                loTail = e;
                            }
                            else {
                                if (hiTail == null)
                                    hiHead = e;
                                else
                                    hiTail.next = e;
                                hiTail = e;
                            }
                        } while ((e = next) != null);
                        if (loTail != null) {
                            loTail.next = null;
                            newTab[j] = loHead;
                        }
                        if (hiTail != null) {
                            hiTail.next = null;
                            newTab[j + oldCap] = hiHead;
                        }
                    }
                }
            }
        }
        return newTab;
    }
    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        NodeHashMap<K,V>[] tab; NodeHashMap<K,V> p; int n, i;
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;
        if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = new NodeHashMap<>(hash, key, value, null);
        ++modCount;
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);
        return null;
    }

    final NodeHashMap<K,V> removeNode(int hash, Object key, Object value,
                                       boolean matchValue, boolean movable) {
        NodeHashMap<K,V>[] tab; NodeHashMap<K,V> p; int n, index;
        if ((tab = table) != null && (n = tab.length) > 0 &&
                (p = tab[index = (n - 1) & hash]) != null) {
            NodeHashMap<K,V> node = null, e; K k; V v;
            if (p.hash == hash &&
                    ((k = p.key) == key || (key != null && key.equals(k))))
                node = p;

            if (node != null && (!matchValue || (v = node.value) == value ||
                    (value != null && value.equals(v)))) {

                 if (node == p)
                    tab[index] = node.next;
                else
                    p.next = node.next;
                ++modCount;
                --size;
                afterNodeRemoval(node);
                return node;
            }
        }
        return null;
    }

    final NodeHashMap<K,V> getNode(int hash, Object key) {
        NodeHashMap<K,V>[] tab; NodeHashMap<K,V> first, e; int n; K k;
        if ((tab = table) != null && (n = tab.length) > 0 &&
                (first = tab[(n - 1) & hash]) != null) {
            if (first.hash == hash &&
                    ((k = first.key) == key || (key != null && key.equals(k))))
                return first;
            if ((e = first.next) != null) {
                do {
                    if (e.hash == hash &&
                            ((k = e.key) == key || (key != null && key.equals(k))))
                        return e;
                } while ((e = e.next) != null);
            }
        }
        return null;
    }

//    @Override
//    public String toString() {
//        return "MyHashMap{" +
//                "table=" +Arrays.toString(table) +
//                '}';
//    }

//    public static void main(String[] args) {
//        MyHashMap<String,String> myHashMap = new MyHashMap<>();
//
//        myHashMap.put("Sasha","Vishlo");
//        myHashMap.put("Sahsa","dsf");
//        myHashMap.put("Sasha","jsdjjd");
//        System.out.println(myHashMap);
//        System.out.println(myHashMap.size);
//        System.out.println(myHashMap.get("Sahsa"));
//    }


}
