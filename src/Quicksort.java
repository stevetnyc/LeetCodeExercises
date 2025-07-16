import java.lang.reflect.Array;
import java.util.Arrays;

class Quicksort {

    static void swap (int[] arr, int index1, int index2) {
        int temp = arr[index2];
        arr[index2] = arr[index1];
        arr[index1] = temp;
    }

    static void quicksort_lomuto(int[] arr, int start, int end){
        //scheme: 0 = lomuto | 1 = hoare
        int pivotIdx = 0;

        if (start >= end) {
            return;
        }

        pivotIdx = partition_lomuto(arr, start, end);

        quicksort_lomuto(arr, start, pivotIdx - 1);
        quicksort_lomuto(arr, pivotIdx + 1, end);
    }

    static int partition_lomuto(int[] arr, int start, int end) {
        int pivot = arr[end];
        int pivotIdx = start;

        for (int i = start; i < end; i++) {
            if (arr[i] <= pivot) {
                swap(arr, i, pivotIdx);
                pivotIdx++;
            }
        }
        swap(arr, pivotIdx, end);
        return pivotIdx;
    }

    static void quicksort_hoare(int[] arr, int start, int end){
        //scheme: 0 = lomuto | 1 = hoare
        int pivotIdx = 0;

        if (start >= end) {
            return;
        }

        pivotIdx = partition_hoare(arr, start, end);

        quicksort_hoare(arr, start, pivotIdx);
        quicksort_hoare(arr, pivotIdx + 1, end);
    }

    static int partition_hoare(int[] arr, int start, int end) {
        int low = start - 1;
        int high = end + 1;
        int pivot = arr[start];

        while (true) {
            do {
                low++;
            } while (arr[low] < pivot);

            do {
                high--;
            } while (arr[high] > pivot);

            if (low >= high) {
                return high;
            }
            swap (arr, low, high);

        }
    }

    public static void main(String[] args) {
        int[] arr = {4, 16, 39, 25, 45, 12, 92, 8, 63, -2};

        quicksort_lomuto(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));

        quicksort_hoare(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));

    }

}
