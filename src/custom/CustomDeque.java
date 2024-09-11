package custom;

import java.util.NoSuchElementException;

public class CustomDeque<T> {
    private int size;

    class CustomNode {
        T data;
        CustomNode next;
        CustomNode prev;

        public CustomNode(T data) {
            this.data = data;
        }
    }

    CustomNode head;
    CustomNode tail;

    public CustomDeque() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    public void addFirst(T o) {
        CustomNode node = new CustomNode(o);
        if (isEmpty()) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
        size++;
    }

    public void addLast(T o) {
        CustomNode node = new CustomNode(o);
        if (isEmpty()) {
            head = node;
        } else {
            node.prev = tail;
            tail.next = node;
        }
        tail = node;
        size++;
    }

    public boolean offerFirst(T o) {
        addFirst(o);
        return true;
    }

    public boolean offerLast(T o) {
        addLast(o);
        return true;
    }

    public T removeFirst() {
        if (isEmpty()) {
            throw new RuntimeException("deque is empty");
        }
        T data = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
            size = 0;
        } else {
            head.prev = null;
            size--;
        }
        return data;
    }

    public T removeLast() {
        if (isEmpty()) {
            throw new RuntimeException("deque is empty");
        }
        T data = tail.data;
        tail = tail.prev;
        if (tail == null) {
            head = null;
            size = 0;
        } else {
            tail.next = null;
            size--;
        }
        return data;
    }

    public T pollFirst() {
        return removeFirst();
    }

    public T pollLast() {
        return removeLast();
    }

    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("deque is empty");
        }
        return head.data;
    }

    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("deque is empty");
        }
        return tail.data;
    }

    public T peekFirst() {
        return getFirst();
    }

    public T peekLast() {
        return getLast();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
