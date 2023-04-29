package org.example;

public class MyHashMap<K, V> {
    private NodeHash<K, V>[] nodes;
    private int size;

    public MyHashMap() {
        nodes = new NodeHash[16];
    }

    public void put(K key, V value) {
        int index = key.hashCode() % nodes.length;
        NodeHash<K, V> node = nodes[index];
        while (node != null) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
            node = node.next;
        }
        NodeHash<K, V> newNode = new NodeHash<>(key, value);
        newNode.next = nodes[index];
        nodes[index] = newNode;
        size++;
    }

    public void remove(K key) {
        int index = key.hashCode() % nodes.length;
        NodeHash<K, V> node = nodes[index];
        if (node == null) {
            return;
        }
        if (node.key.equals(key)) {
            nodes[index] = node.next;
            size--;
            return;
        }
        while (node.next != null) {
            if (node.next.key.equals(key)) {
                node.next = node.next.next;
                size--;
                return;
            }
            node = node.next;
        }
    }

    public void clear() {
        nodes = new NodeHash[16];
        size = 0;
    }

    public int size() {
        return size;
    }

    public V get(K key) {
        int index = key.hashCode() % nodes.length;
        NodeHash<K, V> node = nodes[index];
        while (node != null) {
            if (node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    private static class NodeHash<K, V> {
        private K key;
        private V value;
        private NodeHash<K, V> next;

        public NodeHash(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
