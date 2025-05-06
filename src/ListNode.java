public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) {this.val = val;}
    ListNode(int val, ListNode next) {this.val = val; this.next= next;}

    static ListNode insert(ListNode head, int val) {
        ListNode temp = new ListNode(val, head);
        head = temp;
        return head;
    }


}
