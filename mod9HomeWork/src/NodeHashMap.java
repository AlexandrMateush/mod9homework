
public class NodeHashMap<K,V> {
    int hash;
    K key;
    V value;
    NodeHashMap<K,V> next;

    NodeHashMap(int hash, K key, V value, NodeHashMap<K,V> next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public final K getKey() { return key; }
    public final V getValue()  { return value; }
    public final String toString() { return key + "=" + value; }



    public final V setValue(V newValue) {
        V oldValue = value;
        value = newValue;
        return oldValue;
    }


}
