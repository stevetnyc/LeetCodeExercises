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

    public static int subarraySum(int[] nums, int k) {
        //nums = [1,1,1], k = 2
        int result = 0;

        Map<Integer, Integer> sumsCount = new HashMap<>();
        sumsCount.put(0, 1);

        int runningTot = 0;

        for (int i = 0; i < nums.length; i++){

            runningTot += nums[i];

            int target = runningTot - k;
            if (sumsCount.containsKey(target)) {
                result += sumsCount.get(target);
            }
            sumsCount.put(runningTot, sumsCount.getOrDefault(runningTot,0) + 1);
        }

        return result;
    }

    public static int computeDepthSum(List<Object> nestedList, int depth){
        int sum = 0;
        for (Object obj1: nestedList) {
            if (obj1 instanceof Integer) {
                sum += (int)obj1 * depth;
            } else {
               sum += computeDepthSum((List)obj1, depth + 1);
            }
        }

        return sum;
    }
    public static int depthSum(List<Object> nestedList) {
        return computeDepthSum(nestedList, 1);

    }

    public static void subsets_rcrs(int[] nums,int currIdx, List<List<Integer>> result, List<Integer> currList) {
        if (currIdx >= nums.length) {
            result.add(currList);
            return;
        }

        subsets_rcrs(nums, currIdx + 1, result, new ArrayList<>(currList));
        currList.add(nums[currIdx]);
        subsets_rcrs(nums, currIdx + 1, result, currList);
//        currList.remove(currList.size() - 1);



        return;
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currList = new ArrayList<>();

        subsets_rcrs(nums, 0, result, currList);

        return result;
    }

    public static boolean checkSubarraySum(int[] nums, int k) {
//        nums = [23,2,4,6,7], k = 6
        //nums = [23,2,6,4,7], k = 6


        HashMap<Integer, Integer> remainderIdx = new HashMap<>();
        remainderIdx.put (0, -1);
        int total = 0;
        for (int i = 0; i < nums.length; i++ ) {
            total += nums[i];
            int rem = total % k;

            if (remainderIdx.containsKey(rem) && i - remainderIdx.get(rem) >= 2) {
                return true;
            }
            remainderIdx.putIfAbsent(rem, i);
        }

        return false;

    }
    public static void main(String[] args) {
//            int[] fruits = {3,3,3,1,2,1,1,2,3,3,4};

//            System.out.println(totalFruit(fruits));

//        int [] nums = {-1,0,1,2,-1,-4};
//        System.out.println(threeSum(nums));

//        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
//        int[][] intervals = {{1,5},{2,3},{3,4},{4,6}};
//        System.out.println(eraseOverlapIntervals(intervals));

//        int[] nums = {1,1,1};
//        int[] nums = {1,2,3};
//        int k = 3;
//        int[] nums = {1,2,1,2,1,3};
//        int[] nums = {3, 4, 7, 2, -3, 1, 4, 2};
//        int k = 7;
//{{1,1},2,{1,1}}
//        List<Object> nestedList = new ArrayList<>();
//        nestedList.add(new ArrayList<Integer>(){{add(1); add(1);}});
//        nestedList.add(2);
//        nestedList.add(new ArrayList<Integer>(){{add(1); add(1);}});
//        System.out.println(depthSum(nestedList));


//        int[] nums = {1,2,3};
//        List<List<Integer>> result = subsets(nums);
//
// c       for (List<Integer> l: result) Utils.printList(l);

//        int[] nums = {23,2,6,4,7};
        int[] nums = {23,2,6,4,7};
        int k = 13;
        System.out.println(checkSubarraySum(nums, k));
    }
}
