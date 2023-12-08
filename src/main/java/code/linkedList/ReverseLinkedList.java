package code.linkedList;

import java.util.List;

public class ReverseLinkedList {
    public static void main(String[] args) {
        ListNode<Integer> head = new ListNode<>(1,
                new ListNode(2,
                        new ListNode(3,
                                new ListNode(4,
                                        new ListNode(5, null)))));
        LinkedList<Integer> list =  new LinkedList(head);
        list.display(list);
        list.head = list.reverse(list);
        list.display(list);
    }
}
