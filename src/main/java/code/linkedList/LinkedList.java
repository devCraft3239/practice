package code.linkedList;


class ListNode<T>{
    T data;
    ListNode<T> next;

    ListNode(T data){
        this.data =  data;
    }

    ListNode(T data, ListNode<T> next){
        this.data =  data;
        this.next = next;
    }
}

public class LinkedList<T> {
    ListNode<T> head;

    LinkedList(ListNode head){
        this.head =  head;
    }

    public static ListNode reverse(LinkedList list){
        ListNode curr = list.head, prev = null, next;
        while (curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static void display(LinkedList list){
        ListNode curr = list.head;
        while (curr.next != null){
            System.out.print(curr.data+"-->");
            curr = curr.next;
        }
        System.out.println(curr.data);
    }
}
