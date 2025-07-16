import java.util.Arrays;

public class RotatedArraySearch {
    // Divide array
    // one half should be sorted
    // check for key in sorted array,
    //      if there --> binary search
    //      if not, split unsorted half and repeat


    int searchArray(int[] arr, int key, int low, int high) {

        if (low > high) return -1;

        int mid = (low + high) / 2;
        int midVal = arr[mid];

        if (midVal == key) {
            return mid;
        }

        //cover edge where arr is 2 elements
        if (low == mid) {
            if (arr[high] == key) {
                return high;
            } else {
                return -1;
            }
        }

        if (arr[low] < arr[mid]) {
            if (arr[low] <= key && arr[mid] >= key) {
                return searchArray(arr, key, low, mid);
            } else {
                return searchArray(arr, key, mid + 1, high);
            }
        } else {
            if (arr[mid] <= key && arr[high] >= key){
                return searchArray(arr, key, mid, high);
            } else {
                return searchArray(arr, key, low, mid - 1);
            }

        }

    }

    public int search (int[] nums, int target) {
        return searchArray(nums, target, 0, nums.length - 1);

    }

    public static void main(String[] args) {

        int[] nums = {4,5,6,7,0,1,2};
//        int[] nums = {1, 3};
        int target = 0;

        System.out.println(new RotatedArraySearch().search(nums, target));


    }
}
