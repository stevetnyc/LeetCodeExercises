import javax.rmi.CORBA.Util;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class LinkedListExercises {


    static boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (slow == fast) return true;

        }
        return false;

    }

    static boolean isPalindrome(ListNode head) {

        if (head == null) return false;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode curr = slow;
        ListNode prev = null;

        //reverse 2nd half of the list
        while (curr != null) {
            ListNode nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr =  nextNode;
        }

        ListNode left = head;
        ListNode right = prev;

        while (prev != null){
            if (prev.val != head.val) return false;
            head = head.next;
            prev = prev.next;
        }


        return true;
    }

    static ListNode reorderList(ListNode head) {

        if (head.next != null && head.next.next != null) return head;
        //find mid-point
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        //reverse the 2nd half of the list
        ListNode curr = slow;
        ListNode prev = null;

        while (curr != null) {
            ListNode nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }

        //merge both lists
        ListNode l1 = head;
        ListNode l2 = prev;
        ListNode dummy = new ListNode(-1);
        curr = dummy;

        while (l2.next != null) {
            curr.next = l1;
            l1 = l1.next;
            curr = curr.next;

            curr.next = l2;
            l2 = l2.next;
            curr = curr.next;
        }

        // handle the case where the list had even number of nodes
        if (l1.val != l2.val) {
            ListNode tmp = curr.next;
            curr.next = l1;
            curr.next.next = tmp;
        }
        return dummy.next;
    }

    static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;


        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode tail = dummy;
        ListNode first = head;

        while (first != null && first.next != null) {
            ListNode second = first.next;

            tail.next = second;
            first.next = second.next;
            second.next = first;

            tail = first;
            first = first.next;

        }
        return dummy.next;
    }

    static void printList (ListNode head) {
        if (head == null) {
            System.out.println("Empty List!!!");
        } else {
            ListNode currNode = head;
            int i = 0;
            System.out.println("Curr Node = " + i + " | Value = " + currNode.val);
            while (currNode.next != null) {
                currNode = currNode.next;
                i++;
                System.out.println("Curr Node = " + i + " | Value = " + currNode.val);
            }
        }
    }

    static ListNode mergeKLists(ListNode[] lists) {

        if (lists.length == 0) return null;

        PriorityQueue<Integer> vals = new PriorityQueue<>();
        for (ListNode ln: lists) {
            ListNode currNode = ln;

            while (currNode != null) {
                vals.add(currNode.val);
                currNode = currNode.next;
            }
        }

        if (vals.size() == 0) return null;

        ListNode rtnList = new ListNode(vals.poll());
        ListNode currNode = rtnList;
        while (vals.size() > 0) {
            currNode.next = new ListNode(vals.poll());
            currNode = currNode.next;
        }

        return rtnList;
    }

    static ListNode[] initArr() {

        ListNode[] arrList = new ListNode[3];

        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(5);

        arrList[0] = head;

        head = new ListNode(1);
        head.next = new ListNode(3);
        head.next.next = new ListNode(4);

        arrList[1] = head;

        head = new ListNode(2);
        head.next = new ListNode(6);

        arrList[2] = head;

        return arrList;
    }

    public static ListNode rotateRight(ListNode head, int k) {
//        Input: head = [1,2,3,4,5], k = 2
//        Output: [4,5,1,2,3]


        if (head == null || head.next == null) return head;

        ListNode oldTail = head;
        ListNode newTail = head;
        ListNode currNode = head;
        int length = 0;

        //find length
        while (currNode != null) {
            length++;
            currNode = currNode.next;
        }

        k = k % length;
        if (k == 0) return  head;

        for (int i = 1; i <= k; i++) {
            oldTail = oldTail.next;
        }


        while (oldTail.next != null) {
            oldTail = oldTail.next;
            newTail = newTail.next;
        }

        ListNode newHead = newTail.next;
        newTail.next = null;
        oldTail.next = head;

        return newHead;
    }
    public static void main(String[] args) {

//        ListNode[] arrList = initArr();

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
//        head.next.next.next.next.next = new ListNode(6);
        int k = 2;
        Utils.printList(rotateRight(head, k));

//        printList(mergeKLists(arrList));

//        System.out.println(isPalindrome(head));
    }
}
