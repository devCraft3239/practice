package code.linkedList;

import code.util.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.LinkedList;

/*
* 1->2->3->4->5->6->7
* 7->6->5->4->3->2->1
* 7->2->3->4->5->6->1
* 7->2->5->4->3->6->1
*/
public class ReverseLinkedListRecurTillMid {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        System.out.println(list);
        System.out.println(recursiveRemoveTillMid(list));
    }

    public static List<Integer> recursiveRemoveTillMid(LinkedList linkedList){
        List<Integer> list =  new ArrayList<Integer>((Collection<? extends Integer>) linkedList);
        int l = 0, h =list.size()-1;
        while (l<h){
            Utils.swap(list, l,h);
            l+=2;
            h-=2;
        }
        return list.stream().collect(Collectors.toCollection(LinkedList::new));
    }

}
