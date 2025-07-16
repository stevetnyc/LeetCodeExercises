import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
//
//
//
//    public static Node listToNode (List<List<Integer>> list) {
//        Set<Integer> visitedIdx = new HashSet<>();
//        List<Node> nodeArr = new ArrayList<>();
//
//        for (int i = 0; i < list.size(); i++) {
//            Node currNode = new Node();
//            if (!visitedIdx.contains(i)) {
//                currNode.val = i + 1;
//                visitedIdx.add(i);
//            } else {
//                currNode = nodeArr.get(i);
//            }
//
//            for (Integer idx: list.get(i)) {
//                if (!visitedIdx.contains(idx - 1)) {
//                    Node neighbor = new Node(idx + 1);
//                    nodeArr.add(idx - 1, neighbor);
//                    visitedIdx.add(i - 1);
//                }
//                currNode.neighbors.add(nodeArr.get(idx - 1));
//            }
//
//        }
//        return nodeArr.get(0);
//
//    }
}
