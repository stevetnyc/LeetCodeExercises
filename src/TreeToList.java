

import java.util.List;

class Node {
    int val;
    Node left;
    Node right;

    public Node(int val)
    {
        this.val = val;
        left = right = null;
    }
}
class TreeToList {
    Node root;

    // head --> Pointer to head node of created doubly linked list
    Node head;

    // Initialize previously visited node as NULL. This is
    // static so that the same value is accessible in all recursive
    // calls
    Node prev = null;

    void convert(Node root) {
        // Base case
        if (root == null) {
            System.out.println("Root is null");
            return;
        }

        System.out.println("Root: " + root.val);
        // Recursively convert left subtree
        System.out.println("Converting left");
        convert(root.left);

        // Now convert this node
        if (prev == null)
            head = root;
        else
        {
            root.left = prev;
            prev.right = root;
        }
        prev = root;
        System.out.println("Head: " + head.val);
        System.out.println("Prev: " + prev.val);
        // Finally convert right subtree
        System.out.println("Converting right");
        convert(root.right);
        System.out.println("Returned from right");
    }

    void printList(Node node)
    {
        while (node != null)
        {
            System.out.print(node.val + " ");
            node = node.right;
        }
    }
    public static void main(String[] args) {

        int[] vals = new int[]{5, 15, 3, 8, 11, 7};
        TreeToList t2l = new TreeToList();
        t2l.root = new Node(10);
        t2l.root.left = new Node(5);
        t2l.root.right = new Node(15);
        t2l.root.left.left = new Node(3);
        t2l.root.left.right = new Node(8);
        t2l. root.right.left = new Node(11);
        t2l.root.left.right.left = new Node(7);

//        t2l.root = new Node(10);
//        t2l.root.left = new Node(12);
//        t2l.root.right = new Node(15);
//        t2l.root.left.left = new Node(25);
//        t2l.root.left.right = new Node(30);
//        t2l.root.right.left = new Node(36);


//        printList(root);
        t2l.convert(t2l.root);
        t2l.printList(t2l.head);
    }

}
