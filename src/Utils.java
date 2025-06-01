import java.sql.SQLSyntaxErrorException;
import java.util.*;

public class Utils {

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void printArr(int[][] arr) {
        for (int x = 0; x < arr.length; x++) {
            System.out.print("[");
            for (int y = 0; y < arr[0].length; y++) {
                System.out.print(arr[x][y]);
                if (y < arr[0].length - 1) {
                    System.out.print(",");
                }
            }
            System.out.println("]");
        }
    }

    static void printArr(int[] arr) {
        System.out.print("[");
        for (int x = 0; x < arr.length; x++) {
            System.out.print(arr[x]);
            if (x < arr.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }

    static void printArr(double[] arr) {
        System.out.print("[");
        for (int x = 0; x < arr.length; x++) {
            System.out.print(arr[x]);
            if (x < arr.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }

    static void printArr(List<Integer> arr) {
        System.out.print("[");
        for (int i: arr) {
            System.out.print(i + ",");
        }
        System.out.println("]");
    }

    static void printHeap(PriorityQueue<?> minHeap) {

        System.out.print("[");
        for (int i = 0; i < minHeap.size(); i++) {
            System.out.print(minHeap.poll());
            if (i < minHeap.size() - 1) System.out.print(", ");
        }
        System.out.println("]");

    }

    public static void printList(List<?> list) {
        System.out.print("[");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i < list.size() - 1) System.out.print(", ");
        }

        System.out.println("]");


    }

    public static void printList(ListNode head) {
        System.out.print("[");
        ListNode currNode = head;
        while (currNode != null) {
            System.out.print(currNode.val);
            if (currNode.next != null) System.out.print(", ");
            currNode = currNode.next;
        }
        System.out.println("]");

    }

//    public static void printNode (Node node) {
//        Queue<Node> queue = new ArrayDeque<>();
//        Set<Node> visited = new HashSet<>();
//        queue.add(node);
//        visited.add(node);
//
//        System.out.print("[");
//
//        while (!queue.isEmpty()) {
//            int levelSize = queue.size();
//            System.out.print("[");
//
//            for (int i = 0; i < levelSize; i++) {
//                Node currNode = queue.poll();
//                for (Node neighbor : currNode.neighbors) {
//                    System.out.print(neighbor.val);
//                    if (i < levelSize - 1) System.out.print(", ");
//                    if (!visited.contains(neighbor)) queue.add(neighbor);
//                }
//            }
//            System.out.print("]");
//        }
//
//
//
//        System.out.println("]");
//    }
}
