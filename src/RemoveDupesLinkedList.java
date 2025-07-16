import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static java.util.Objects.isNull;

public class RemoveDupesLinkedList {

    public ListNode removeDupes(ListNode head) {

        if (head == null) return head;

        ListNode returnVal = head;

        while  (head != null && head.next != null) {
            while (head.next != null && head.val == head.next.val) {
                head.next = head.next.next;
            }
            head = head.next;
        }

        return returnVal;

    }

    public static void main(String[] args) {
        List<Integer> linkedList = Arrays.asList(1,1,1);
        ListNode head = new ListNode();
        for (int x = linkedList.size() - 1; x >= 0; x--) {
            head = ListNode.insert(head, linkedList.get(x));
        }

        head = new RemoveDupesLinkedList().removeDupes(head);
        while (head.next != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
