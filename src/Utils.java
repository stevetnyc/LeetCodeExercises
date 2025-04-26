import java.util.List;
import java.util.PriorityQueue;

public class Utils {

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
}
