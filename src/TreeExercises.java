import javax.rmi.CORBA.Util;
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


    public static boolean ivb_rcrs(TreeNode node, long currMin, long currMax) {
        if (node == null) return true;

        if (node.val <= currMin || node.val >= currMax) {
            return false;
        }

        return ivb_rcrs(node.left, currMin, node.val) && ivb_rcrs(node.right, node.val, currMax);
    }


    public static boolean isValidBST(TreeNode root) {

        return ivb_rcrs(root, Long.MIN_VALUE, Long.MAX_VALUE);

    }

    public static int ft_rcrs(TreeNode node) {
        if (node == null) return 0;

        int left = ft_rcrs(node.left);
        int right = ft_rcrs(node.right);

        TreeExercises.tilt += Math.abs(left - right);
        return left + right + node.val;

    }

    public static int tilt = 0;
    public static int findTilt(TreeNode root) {


        ft_rcrs(root);

        return TreeExercises.tilt;

    }

    public static boolean hasCycle(Map<Integer, Set<Integer>> adjList, int currNode,  boolean[] visited, int parent ){
        visited[currNode] = true;
        Set<Integer> nodes = adjList.get(currNode);
        for (int node : nodes) {
            if (visited[node] && node != parent) return true;
            if (!visited[node] && hasCycle(adjList, node, visited, currNode)) return true;
        }
        return false;
    }

    public static boolean validTree(int n, int[][] edges) {
        Map<Integer, Set<Integer>> adjList = new HashMap<>();
        boolean[] visited = new boolean[n];
        Queue<Integer> nodeQueue = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            adjList.put(i, new HashSet<Integer>());
        }

        for (int[] e: edges) {
            adjList.get(e[0]).add(e[1]);
            adjList.get(e[1]).add(e[0]);
        }

        nodeQueue.offer(0);
        visited[0] = true;

        while (!nodeQueue.isEmpty()) {
            int currNode = nodeQueue.poll();
            for (int neighbor: adjList.get(currNode)) {
                if (!visited[neighbor]) {
                    nodeQueue.offer(neighbor);
                    visited[neighbor] = true;
                }
            }

        }



//        for (int i = 0; i < adjList.size(); i++) {
//            if (hasCycle(adjList, i, visited, -1)) return false;
//        }
//
        for (int i = 0; i < adjList.size(); i++) {
            if (!visited[i]) return false;
        }

        return true;
    }



    public static void vt_dfs(TreeNode node, int row, int col, List<int[]> nodeData) {
        if (node == null) return;

        nodeData.add(new int[] {row, col, node.val});
        vt_dfs(node.left, row+1, col - 1, nodeData);
        vt_dfs(node.right, row+1, col + 1, nodeData);
    }

    public static List<List<Integer>> verticalTraversal(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();
        List<int[]> nodeData = new ArrayList<>();

        vt_dfs(root, 0, 0, nodeData);

        nodeData.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] != o2[1]) return Integer.compare(o1[1], o2[1]);
                if (o1[0] != o2[0]) return Integer.compare(o1[0], o2[0]);
                return Integer.compare(o1[2], o2[2]);
            }
        });

        int prevCol = Integer.MIN_VALUE;
        for (int[] node: nodeData) {
            if (node[1] != prevCol) {
                result.add(new ArrayList<Integer>());
                prevCol = node[1];
            }
            result.get(result.size() - 1).add(node[2]);
        }
        return  result;

    }
    public static void main(String[] args) {


        Object[] arr = {3,9,20,null,null,15,7};
        TreeNode root = TreeNode.insertLevel(arr, 0);
        Utils.printList(verticalTraversal(root));

//        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {0, 3}, {1, 4}};
//        int[][] edges = {{0, 1}, {2, 3}};
//        int n = 4;
//        System.out.println(validTree(n, edges));
//        Integer[] arr = {21,7,14,1,1,2,2,3,3};
//        TreeNode root = TreeNode.insertLevel(arr, 0);
//        System.out.println(findTilt(root));
//        Integer[] arr ={2,1,3};
//        Object[] arr = {5,1,4,null,null,3,6};
//        Object[] arr = {5,4,6,null,null,3,7};
//        TreeNode root = TreeNode.insertLevel(arr, 0);
//        System.out.println(isValidBST(root));



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

//        Object[] arr = {3,5,1,6,2,0,8,null,null,7,4};
//        TreeNode root = TreeNode.insertLevel(arr, 0);
//        TreeNode p = TreeNode.find(root, 5);
//        TreeNode q = TreeNode.find(root, 4);
//        System.out.println(lowestCommonAncestor(root, p, q).val);

    }

}

