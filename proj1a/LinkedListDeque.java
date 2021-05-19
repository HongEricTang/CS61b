import java.util.List;

public class LinkedListDeque<T> {
    private class ListNode{
        public T item;
        public ListNode next;
        public ListNode previous;

        public ListNode(T item, ListNode next, ListNode previous){
            this.item = item;
            this.next = next;
            this.previous = previous;
        }
    }

    private int size;
    private ListNode head;
    private ListNode tail;

    public LinkedListDeque(){
        head = new ListNode(null, null, null);
        tail = new ListNode(null, null, null);
        head.next = tail;
        tail.previous = head;
        size = 0;
    }

    public LinkedListDeque(LinkedListDeque other){
        this.head = new ListNode(null, null, null);
        this.tail = new ListNode(null, null, null);
        this.size = other.size;

        ListNode at1 = head;
        ListNode at2 = other.head.next;

        while (at2.next != other.tail) {
            T i = at2.item;
            at1.next = new ListNode(i, null, at1);
            at1 = at1.next;
            at2 = at2.next;
        }

        at1.next = tail;
        tail.previous = at1;

    }

    public void addFirst(T item){
        ListNode first = head.next;
        ListNode newFirst = new ListNode(item, first, head);
        head.next = newFirst;
        first.previous = newFirst;
        size += 1;
    }

    public void addLast(T item){
        ListNode last = tail.previous;
        ListNode newLast = new ListNode(item, tail, last);
        last.next = newLast;
        tail.previous = newLast;
        size += 1;
    }

    public boolean isEmpty(){
        return (size == 0);
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        ListNode at = head.next;
        while (at != tail){
            System.out.println(at.item);
            at = at.next;
        }
    }

    public T removeFirst(){
        ListNode first = head.next;
        ListNode second = first.next;
        head.next = second;
        second.previous = head;
        size -= 1;

        return first.item;
    }

    public T removeLast(){
        ListNode last = tail.previous;
        ListNode secondLast = last.previous;
        tail.previous = secondLast;
        secondLast.next = tail;
        size -= 1;

        return last.item;
    }

    public T get(int index){
        ListNode tmp = head.next;
        int at = 0;
        while (at != index){
            tmp = tmp.next;
            at += 1;
        }

        return tmp.item;
    }

    private T getRecursive(ListNode current, int index){
        if (index == 0){
            return current.item;
        }
        return getRecursive(current.next, index - 1);
    }
    public T getRecursive(int index){
        return getRecursive(head.next, index);
    }
}
