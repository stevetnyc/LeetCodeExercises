import java.util.Arrays;

public class BinarySearchArray {
    public int binSearchRec(int[] a, int key, int low, int high) {
        int testIdx = low + ((high - low) / 2);
        int testVal = a[testIdx];

        if (low > high) {
            return -1;
        }

        if (testVal == key) {
            return testIdx;
        }

        if (testVal > key) {
            return binSearchRec(a, key, low, testIdx - 1);
        } else {
            return binSearchRec(a, key, testIdx + 1, high);
        }
    }

    public int binSearch(int[] a, int key) {
        return binSearchRec(a, key, 0, a.length - 1);
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1, 2, 4, 7, 8, 12, 15, 19, 24};
        int ans = new BinarySearchArray().binSearch(arr, 19);
        System.out.println(ans);

    }
}

