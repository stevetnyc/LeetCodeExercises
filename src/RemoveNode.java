import java.util.Stack;

public class RemoveNode {
    /*
    Given the head of a linked list, remove the nth node
    from the end of the list and return its head.
     */

    static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head.next == null) return null;
        ListNode fastNode = head;
        ListNode slowNode = head;

        //move fast n nodes ahead
        for (int i = 0; i < n; i++) {
            fastNode = fastNode.next;
        }

        // if we hit the end of the list return head
        if (fastNode == null) return head.next;

        // move both pointers until fast hits the end
        while (fastNode.next != null) {
            fastNode = fastNode.next;
            slowNode = slowNode.next;
        }

        //use slow pointer to remove node
        slowNode.next = slowNode.next.next;
        return head;
    }

    static ListNode removeNthFromEnd_Stack(ListNode head, int n) {
        if (head.next == null) return null;
        Stack<ListNode> nodeStack = new Stack<>();
        ListNode currNode = head;
        while (currNode.next != null) {
            nodeStack.add(currNode);
            currNode = currNode.next;
        }
        nodeStack.add(currNode);

        int i = n;
        while (i > 0) {
            currNode = nodeStack.pop();
            i--;
        }
        // need to get next ref from prior node
        if (nodeStack.size() > 0) {
            ListNode priorNode = nodeStack.pop();
            priorNode.next  = currNode.next;
            return head;
        } else {
            // we're removing the head so return next
            return currNode.next;
        }

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
    public static void main(String[] args) {
        ListNode head = new ListNode(1);

//        head.next = new ListNode(2);
//        head.next.next = new ListNode(3);
//        head.next.next.next = new ListNode(4);
//        head.next.next.next.next = new ListNode(5);

        System.out.println("Original List");
        printList(head);

        ListNode newList = removeNthFromEnd(head, 1);
        System.out.println("New  List");
        printList(newList);
    }

}
