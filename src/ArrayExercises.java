import java.util.*;

public class ArrayExercises {


    public static int totalFruit(int[] fruits) {
        // fruits = [1,2,3,2,2]

        HashMap<Integer, Integer> fruitCounts = new HashMap<>();
        int fruitPicked = 0;
        int maxFruitPicked = 0;
        int start = 0;

        for (int end = 0; end < fruits.length; end++) {
            if (fruitCounts.get(fruits[end]) != null) {
                fruitCounts.put(fruits[end], fruitCounts.get(fruits[end]) + 1);
            } else {
                fruitCounts.put(fruits[end], 1);
            }
            fruitPicked++;


            while (fruitCounts.size()> 2) {
                fruitCounts.put(fruits[start], fruitCounts.get(fruits[start]) - 1);

                if (fruitCounts.get(fruits[start]) == 0) fruitCounts.remove(fruits[start]);
                start++;
                fruitPicked--;
            }
            maxFruitPicked = Math.max(maxFruitPicked, fruitPicked);
        }

        return maxFruitPicked;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        //nums = [-1,0,1,2,-1,-4]
        //[-4,-1,-1,0,1,2]

        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {

            if (i > 0 && nums[i] == nums[i-1]) continue;
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int total = nums[i] + nums[left] + nums[right];
                if (total < 0) {
                    left++;
                } else if (total > 0) {
                    right--;
                } else {
                    result.add(new ArrayList<Integer>(Arrays.asList(nums[i], nums[left], nums[right])));
                    while (left < right && nums[left] == nums[left +1]) left++;
                    while (right >= left && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;

                }
            }
        }

        return result;

    }

    public static int[][] merge(int[][] intervals) {
//        Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
//        Output: [[1,6],[8,10],[15,18]]

//
        List<Integer[]> tmpResults = new ArrayList<>();

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        for (int i = 0; i < intervals.length; i++) {
            Integer[] tmpInt = new  Integer[2];
            if (tmpResults.size() == 0 || intervals[i][0] >= tmpResults.get(tmpResults.size() - 1)[1]) {
                tmpInt[0] = intervals[i][0];
                tmpInt[1] = intervals[i][1];
                tmpResults.add(tmpInt);
            } else {
                tmpInt[0] = tmpResults.get(tmpResults.size() - 1)[0];
                tmpInt[1] = Math.max(tmpResults.get(tmpResults.size() - 1)[1], intervals[i][1]);
                tmpResults.remove(tmpResults.size() - 1);
                tmpResults.add(tmpInt);
            }
        }

        int[][] result = new int[tmpResults.size()][2];
        int idx = 0;
        for (Integer[] res: tmpResults) {
            result[idx][0] = res[0];
            result[idx][1] = res[1];
            idx++;
        }

        return result;

    }

    public static int eraseOverlapIntervals(int[][] intervals) {

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        int result = 0;
        int[] prevInt = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < prevInt[1]) {
                result++;
            } else {
                prevInt = intervals[i];
            }

        }

        return  result;
    }
    public static void main(String[] args) {
//            int[] fruits = {3,3,3,1,2,1,1,2,3,3,4};

//            System.out.println(totalFruit(fruits));

//        int [] nums = {-1,0,1,2,-1,-4};
//        System.out.println(threeSum(nums));

//        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        int[][] intervals = {{1,5},{2,3},{3,4},{4,6}};
        System.out.println(eraseOverlapIntervals(intervals));

    }
}
