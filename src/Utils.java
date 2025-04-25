import java.util.List;

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
}
