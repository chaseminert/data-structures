public final class ListNode<T> {
    private T data;
    private ListNode<T> next;

    public ListNode() {
        data = null;
        next = null;
    }

    public ListNode(T data) {
        this.data = data;
        this.next = null;
    }

    public ListNode(T data, ListNode<T> next) {
        this.data = data;
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ListNode<T> getNext() {
        return next;
    }

    public void setNext(ListNode<T> nx) {
        next = nx;
    }


}
