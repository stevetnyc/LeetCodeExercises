

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

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

    static int maxElement(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++){
            if (arr[i] > max) max = arr[i];
        }
        return max;
    }
    static int minEatingSpeed(int[] piles, int h) {

        Arrays.sort(piles);
        int left = 1;
        int right = piles[piles.length - 1];
        long timeTaken = 0;
        while (left <= right) {
            int mid = (left + right)/2;
            timeTaken = 0;
            for (int i = 0; i < piles.length; i++) {
                if (piles[i] % mid == 0) {
                    timeTaken += piles[i]/mid;
                } else {
                    timeTaken += piles[i]/mid + 1;
                }
            }

            if (timeTaken > h) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
    static int search (int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;


        while (left < right) {

            if (nums[left] == target) return left;
            if (nums[right] == target) return right;

            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else {

                if (nums[left] < nums[mid]) {
                    if (nums[left] <= target && nums[mid] > target) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                } else {
                    if (nums[right] >= target && nums[mid] < target) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
            }

        }
        if (nums[left] == target) return left;
        return -1;
    }

        public static void main(String[] args) {
//        int[] arr = new int[] {1, 2, 4, 7, 8, 12, 15, 19, 24};
//        int[] arr = new int[] {1, 2, 4, 7, 8, 12, 15, 19, 24};
//        int[] arr =? new int[] {4,5,6,7,0,1,2};
        int[] arr = new int[] {5,1,2,3,4};
        //805,306,368
        //1,000,000,000
//        int[] arr = new int[] {805306368,805306368,805306368};
//        int t = 1000000000;
        int t = 1;
        int ans = search(arr, t);
        System.out.println(ans);

    }
}

