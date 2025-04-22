public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    static TreeNode insertLevel (int[] arr, int i) {

        TreeNode root = new TreeNode();
        if (i < arr.length) {
            root.val = arr[i];
            root.left = insertLevel(arr, 2 * i + 1);
            root.right = insertLevel(arr, 2 * i + 2);
        }
        if (root.val == 0) return null;
        return root;
    }
}
