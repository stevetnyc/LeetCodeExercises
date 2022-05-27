import java.util.Arrays;

public class RotatedArraySearch {
    // Divide array
    // one half should be sorted
    // check for key in sorted array,
    //      if there --> binary search
    //      if not, split unsorted half and repeat


    int searchArray(int[] arr, int searchVal, int low, int high) {
       if (low > high) return -1;

        int mid = (low + high) / 2;

        int midVal = arr[mid];

        if (midVal == searchVal) {
            return mid;
        } else if (midVal > searchVal) {
            return searchArray(arr, searchVal, low, mid);
        } else {
            return searchArray(arr, searchVal, mid + 1, high );
        }

    }

    int findPivot(int[] arr, int low, int high) {

        if (arr[low] < arr[high]) return -1;
        if (high < low) return -1;
        if (low == high) return low;

        int mid = (low + high) / 2;

        if (mid < high && arr[mid] > arr[mid + 1]) {
            return mid;
        } else if (mid < low && arr[mid] < arr[mid - 1] ) {
            return mid - 1;
        } else if (arr[low] >= arr[mid] ){
            return findPivot(arr, low, mid - 1 );
        } else {
            return findPivot(arr, mid + 1, high);
        }

    }

    public int search (int[] nums, int target) {
        int pivot = findPivot(nums, 0, nums.length - 1);

        if (pivot == -1) {
            return searchArray(nums, target, 0, nums.length - 1);
        }

        if (nums[pivot] == target) {
            return pivot;
        }

        if (nums[0] < target) {
            return searchArray(nums, target, 0, pivot);
        }

        return searchArray(nums, target, pivot + 1, nums.length - 1);
    }

    public static void main(String[] args) {

        int[] nums = {4,5,6,7,0,1,2};
//        int[] nums = {1, 3};
        int target = 3;

        System.out.println(new RotatedArraySearch().search(nums, target));


    }
}
