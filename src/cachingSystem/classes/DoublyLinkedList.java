package cachingSystem.classes;

import dataStructures.classes.Pair;

import java.util.AbstractList;

public class DoublyLinkedList<K, V> extends AbstractList<Pair<K, V>> {

    private int size = 0;
    private Node<Pair<K, V>> first;
    private Node<Pair<K, V>> last;

    @Override
    public Pair<K, V> get(int index) {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    public void put(K key, V value) {
        removeItem(key);
        Node<Pair<K, V>> node = new Node<>(new Pair<>(key, value));
        if (last != null) {
            last.setNext(node);
            node.setPrevious(last);
        } else {
            first = node;
        }
        last = node;
        size++;
    }

    public Pair<K, V> pop() {
        if (first != null) {
            Pair<K, V> old = first.getValue();
            first = first.getNext();
            if (first != null) {
                first.setPrevious(null);
            }
            size--;
            return old;
        }
        return null;
    }

    public V get(K key) {
        Node<Pair<K, V>> node = internalGet(key);
        return node != null ? node.getValue().getValue() : null;
    }

    public V removeItem(K key) {
        Node<Pair<K, V>> node = internalGet(key);
        if (node != null) {
            if (node.getPrevious() != null) {
                node.getPrevious().setNext(node.getNext());
            } else {
                first = node.getNext();
            }
            if (node.getNext() != null) {
                node.getNext().setPrevious(node.getPrevious());
            } else {
                last = node.getPrevious();
            }
            size--;
            return node.getValue().getValue();
        }
        return null;
    }

    public Pair<K, V> getEldestEntry() {
        if (first == null) {
            return null;
        }

        return first.getValue();
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    private Node<Pair<K, V>> internalGet(K key) {
        if (first == null || key == null) {
            return null;
        }
        Node<Pair<K, V>> node = first;
        for (int i = 0; i < size; i++) {
            if (key.equals(node.getValue().getKey())) {
                return node;
            } else {
                node = node.getNext();
            }
        }
        return null;
    }

    private static class Node<T> {
        private T value;
        private Node<T> previous;
        private Node<T> next;


        Node(T value) {
            this.value = value;
        }


        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node<T> getPrevious() {
            return previous;
        }

        public void setPrevious(Node<T> previous) {
            this.previous = previous;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }
}
