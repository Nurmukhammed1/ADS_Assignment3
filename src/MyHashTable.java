public class MyHashTable<K, V> {

    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }

    private HashNode<K, V>[] chainArray;
    private int M = 11;
    private int size = 0;
    private double loadFactor = 0.75;

    public MyHashTable() {
        chainArray = new HashNode[M];
    }

    public MyHashTable(int M) {
        this.M = M;
        chainArray = new HashNode[M];
    }

    private int hash(K key) {
        int index = key.hashCode() % M;
        return index;
    }

    public void put(K key, V value) {

        if ((double) size / M > loadFactor) {
            increaseCapacity();
        }

        int index = hash(key);
        HashNode<K, V> node = chainArray[index];
        node.next = chainArray[index];
        chainArray[index] = node;
        size++;
    }

    public void increaseCapacity() {
        HashNode<K, V>[] newArray = chainArray;
        M = M * 2;
        chainArray = new HashNode[M];
        for (int i = 0; i < newArray.length; i++) {
            if (newArray[i] != null) {
                HashNode<K, V> node = newArray[i];
                while (node != null) {
                    put(node.key, node.value);
                    size--;
                    node = node.next;
                }
            }
        }
    }

    public V get(K key) {
        int index = hash(key);
        HashNode<K, V> node = chainArray[index];

        while(node != null) {
            if(node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }

        return null;
    }

    public void remove(K key) {
        int index = hash(key);
        HashNode<K, V> newNode = chainArray[index];
        HashNode<K, V> prev = null;
        while (newNode != null) {
            if(newNode.key.equals(key)) {
                if(prev == null) {
                    chainArray[index] = newNode.next;
                } else {
                    prev.next = newNode.next;
                }
                //should I make newNode = null ???
                size--;
                return;
            }
            prev = newNode;
            newNode = newNode.next;
        }
    }

    public boolean contains(K key) {
        int index = hash(key);
        HashNode<K, V> node = chainArray[index];

        while(node != null) {
            if(node.key.equals(key)) {
                return true;
            }
            node = node.next;
        }

        return false;
    }

    public K getKey(V value) {
        for(int i = 0 ; i < M ; i++) {
            HashNode<K, V> node = chainArray[i];
            while(node != null) {
                if(node.value.equals(value)) {
                    return node.key;
                }
                node = node.next;
            }
        }

        return null;
    }


}
