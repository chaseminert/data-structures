public final class MyLinkedList<T> {
    public ListNode<T> head;
    int size = 0;
    public MyLinkedList() {
        head = new ListNode<T>();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(final T o) {
        ListNode<T> current = head;
        for (int i = 0; i < size; i++) {
            current = current.getNext();
            if (current.getData().equals(o)) {
                return true;
            }
        }
        return false;
    }

    public <T1> T1[] toArray(final T1[] a) {
        return null;
    }

    public void add(final T elem) {
        ListNode<T> last = getLastNode();
        ListNode<T> newNode = new ListNode<T>(elem);
        last.setNext(newNode);
        size++;
    }

    public void remove(T elem) {
        ListNode<T> current = head;
        for (int i = 0; i < size; i++) {
            ListNode<T> next = current.getNext();
            if (next.getData().equals(elem)) {
                current.setNext(next.getNext());
                size--;
                return;
            }
            current = current.getNext();
        }
    }

    public void clear() {
        head.setNext(new ListNode<T>());
    }

    public void printLinkedList() {
        ListNode<T> current = head;
        for (int i = 0; i < size; i++) {
            current = current.getNext();
            System.out.printf("%d: %s%n", i, current.getData().toString());
        }
    }

    public T get(final int index) {
        return getNode(index).getData();
    }

    private ListNode<T> getNode(final int index) {
        ListNode<T> current = head;
        for (int i = 0; i <= index; i++) {
            current = current.getNext();
        }
        return current;
    }

    private ListNode<T> getLastNode() {
        return getNode(size - 1);
    }

    public void set(final int index, final T element) {
        ListNode<T> current = head;
        for (int i = 0; i <= index; i++) {
            current = current.getNext();
        }
        current.setData(element);

    }

    public void add(final int index, final T element) {
        ListNode<T> newNode = new ListNode<T>(element);
        ListNode<T> rightBeforeAdd = getNode(index-1);
        ListNode<T> rightAfterAdd = rightBeforeAdd.getNext();
        rightBeforeAdd.setNext(newNode);
        newNode.setNext(rightAfterAdd);
        size++;

    }

    public void remove(final int index) {
        ListNode<T> rightBeforeRemove = getNode(index-1);
        rightBeforeRemove.setNext(rightBeforeRemove.getNext().getNext());
        size--;
    }

    public int indexOf(final T obj) {
        return 0;
    }

    public int lastIndexOf(final Object o) {
        return 0;
    }

}
