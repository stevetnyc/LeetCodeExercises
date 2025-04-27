import java.util.*;

public class TreeExercises {
    static int diameter = 0;

    static int findHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = findHeight(node.left);
        int right = findHeight(node.right);

        diameter = Math.max(diameter, left + right);
        return 1 + Math.max(left, right) ;

    }
    static int diameterOfBinaryTree(TreeNode root) {

        findHeight(root);

        return diameter;

    }

    static List<Integer> rightSideView(TreeNode root) {

        List<Integer> result = new ArrayList<>();

        if (root == null) return result;

        Queue<TreeNode> nodeQueue = new ArrayDeque<>();

        nodeQueue.add(root);


        int level = 1;
        while (nodeQueue.size() > 0) {
            int levelSize = nodeQueue.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode currNode = nodeQueue.poll();
                if (i == levelSize - 1) result.add(currNode.val);
                if (currNode.left != null) nodeQueue.add(currNode.left);
                if (currNode.right != null) nodeQueue.add(currNode.right);
            }
        }

        return result;
    }

    static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        Deque<TreeNode> nodeQueue = new ArrayDeque<>();

        if (root == null) return result;

        nodeQueue.add(root);
        int level = 0;
        List<Integer> tmpResult = new ArrayList<>();
        while(nodeQueue.size() > 0) {
            int levelSize = nodeQueue.size();
            tmpResult = new ArrayList<>();
            level++;

            for (int i = 0; i < levelSize; i++){
                TreeNode currNode = nodeQueue.poll();

                if ((level) % 2 == 0 || tmpResult.size() == 0) {
                    tmpResult.add(0, currNode.val);
                } else {
                    tmpResult.add(tmpResult.size(), currNode.val);
                }

                if (currNode.left != null) nodeQueue.add(currNode.left);
                if (currNode.right != null) nodeQueue.add(currNode.right);
            }
            result.add(tmpResult);

        }
        return result;
    }

    public static int goodNodes_rcrs(TreeNode root, int max) {
        if (root == null) return 0;

        int count = 0;
        if (root.val >= max) {
            count++;
        }
        max = Math.max(max, root.val);

        int left = goodNodes_rcrs(root.left, max);
        int right = goodNodes_rcrs(root.right, max);

        return left + right + count;
    }

    public static int goodNodes(TreeNode root) {

        return goodNodes_rcrs(root, 0);
    }


    public static TreeNode lca_rcrs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        if (root == p || root == q) return root;

        TreeNode left = lca_rcrs(root.left, p, q);
        TreeNode right = lca_rcrs(root.right, p, q);

        if (left != null && right != null) return root;

        return (left != null) ? left : right;

    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        return lca_rcrs(root, p, q);

    }

    public static void main(String[] args) {

//        int[] arr = {1,2,3,4,0,0,0,5};
//        int[] arr = {1,2};
////        int[] arr = {3,9,20,0,0,15,7};
////        TreeNode root = TreeNode.insertLevel(arr, 0);
//
////        System.out.println(diameterOfBinaryTree(root));
//
//        List<List<Integer>> result = zigzagLevelOrder(root);

//        int[] arr = {3,1,4,3,-999,1,5};
////        int[] arr = {9,-999,3,6};
//        TreeNode root = TreeNode.insertLevel(arr, 0);
//        System.out.println(goodNodes(root));

        Object[] arr = {3,5,1,6,2,0,8,null,null,7,4};
        TreeNode root = TreeNode.insertLevel(arr, 0);
        TreeNode p = TreeNode.find(root, 5);
        TreeNode q = TreeNode.find(root, 4);
        System.out.println(lowestCommonAncestor(root, p, q).val);

    }

}
