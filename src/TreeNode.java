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

    static TreeNode insertLevel (Object[] arr, int i) {

        TreeNode root = new TreeNode();
        if (i < arr.length && arr[i] != null ) {
            root.val = (int)arr[i];
            root.left = insertLevel(arr, 2 * i + 1);
            root.right = insertLevel(arr, 2 * i + 2);
            return root;
        }
        return null;
    }

    public static TreeNode find (TreeNode root, int match) {

        if (root == null) return null;
        if (root.val == match) return root;

        TreeNode left = find(root.left, match);
        TreeNode right = find(root.right, match);

        return (left != null) ? left : right;

    }
}
