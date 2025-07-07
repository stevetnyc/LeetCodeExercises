
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

    public static List<List<Integer>> fourSum(int[] nums, int target) {
//        nums = [1,0,-1,0,-2,2]
//        [-2, -1, 0, 0, 1, 2]
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    long total = (long) nums[i] + nums[left] + nums[right] + nums[j];
                    if (total < target) {
                        left++;
                    } else if (total > target) {
                        right--;
                    } else {
                        result.add(new ArrayList<Integer>(Arrays.asList(nums[i], nums[left], nums[right], nums[j])));
                        right--;
                        left++;
                        while (left < right && nums[left] == nums[left - 1]) left++;
                        while (left < right && nums[right] == nums[right + 1]) right--;
                    }
                }
            }
        }

        return result;

    }

    public static int[][] merge(int[][] intervals) {
//        Input: intervals = [[1,3],[2,6],,[8,10],[15,18]]
//        Output: [[1,6],[8,10],[15,18]]

        List<Integer[]> resultList = new ArrayList<>();

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        for (int i = 0; i < intervals.length; i++) {
            if (resultList.size() == 0 || intervals[i][0] >= resultList.get(resultList.size() - 1)[1]) {
                Integer[] tmpArr = new Integer[2];
                tmpArr[0] = intervals[i][0];
                tmpArr[1] = intervals[i][1];
                resultList.add(tmpArr);
            } else {
                Integer[] tmpArr = new Integer[2];
                tmpArr[0] = resultList.get(resultList.size() - 1)[0];
                tmpArr[1] = Math.max(intervals[i][1], resultList.get(resultList.size() - 1)[1]);
                resultList.remove(resultList.size() - 1);
                resultList.add(tmpArr);
            }
        }

        int[][] result = new int[resultList.size()][2];
        for (int i = 0; i < resultList.size(); i++) {
            result[i][0] = resultList.get(i)[0];
            result[i][1] = resultList.get(i)[1];
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
        Map<Integer, Integer> sumsMap = new HashMap<>();
        sumsMap.put(0, 1);

        int runningTotal = 0;

        for (int i = 0; i < nums.length; i++ ){
            runningTotal += nums[i];
            int target = runningTotal - k;
            if (sumsMap.containsKey(target)){
                result += sumsMap.get(target);
            }
            sumsMap.put(runningTotal, sumsMap.getOrDefault(runningTotal, 0) + 1);
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

    public static double[] medianSlidingWindow(int[] nums, int k) {

        // [1,3,-1,-3,5,3,6,7] | k = 3

        double[] result = new double[nums.length - k + 1];
        if (k ==1) {
            for (int i = 0; i < nums.length; i++) {
                result[i] = nums[i];
            }
           return result;
        }
        int resIdx = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        boolean oddK = false;


        if (k % 2 == 1) {
            oddK = true;
        }

        maxHeap.add(nums[0]);

        for (int i = 1; i < nums.length; i++){

            if (maxHeap.isEmpty() || nums[i] <= maxHeap.peek()) {
                maxHeap.add(nums[i]);
            } else {
                minHeap.add(nums[i]);

            }

            if (i < k - 1) continue;

            while (maxHeap.size() > minHeap.size() + 1) {
                minHeap.add(maxHeap.poll());
            }
            while (maxHeap.size() < minHeap.size()) {
                maxHeap.add(minHeap.poll());
            }

            double median = 0;
            if (oddK) {
                median = maxHeap.peek();
            } else {
                median = ((double)maxHeap.peek() + (double)minHeap.peek())/2;
            }
            result[resIdx++] = median;

            int removeNum = nums[0];
            if (i >= k) removeNum = nums[i - k + 1];
            if (maxHeap.contains(removeNum)) {
                maxHeap.remove(removeNum);
            } else {
                minHeap.remove(removeNum);
            }

        }
        return result;

    }

    public static int maxArea_old(int[] height) {
//        Input: height = [1,8,6,2,5,4,8,3,7]
//        Output: 49

        int max = 0;
        int start = 0;
        int end = height.length - 1;

        while (start < end) {
            int min = Math.min(height[start], height[end]);
            max = Math.max(max, (end - start) * min);
            if (height[start] < height[end]) {
                start++;
            } else {
                end--;
            }
        }

//        for (int start = 0; start < height.length - 1; start++) {
//            for (int end = start + 1; end < height.length ; end++) {
//                int min = Math.min(height[start], height[end]);
//                max = Math.max(max, (end - start) * min);
//            }
//        }
        return max;
    }
    public static int[] searchRange(int[] nums, int target) {
//        nums = [5,7,7,8,8,10], target = 8


        int start = 0;
        int end = nums.length - 1;
        int[] result = new int[2];

        if (nums.length == 1) {
            if (nums[0] == target) {
                result[0] = 0;
                result[1] = 0;
            } else {
                result[0] = -1;
                result[1] = -1;
            }
            return result;
        }

        while (start <= end) {
            int mid = (end + start) / 2;
            if (nums[mid] == target) {
                int left = mid;
                int right = mid;
                while (left > 0 && nums[left - 1] == target) left--;
                while (right < nums.length - 1 && nums[right + 1] == target) right++;

                result[0] = left;
                result[1] = right;
                return result;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }

        }
        result[0] = -1;
        result[1] = -1;
        return result;
    }

//    private List<List<Integer>> result = new ArrayList<>();
//    private List<Integer> currCandidates = new ArrayList<>();

    public static void cs_rcrs(int[] candidates, int target, List<List<Integer>> result,  List<Integer> currCandidates, int currTotal, int startIdx) {
        if (currTotal == 0) {
            result.add(new ArrayList<> (currCandidates));
            return;
        }
        if (currTotal < 0) return;

        for (int i = startIdx; i < candidates.length; i++) {
            currCandidates.add(candidates[i]);
            cs_rcrs(candidates, target, result, currCandidates, currTotal - candidates[i], i);
            currCandidates.remove(currCandidates.size() - 1);
        }

//        currCandidates.add(candidates[startIdx]);
//        cs_rcrs(candidates, target,startIdx + 1, currTotal);
//        currCandidates.remove(currCandidates.size() - 1);
//        cs_rcrs(candidates, target, startIdx, currTotal - candidates[startIdx]);

    }
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
//        List<Integer> currCandidates = new ArrayList<>();

        Arrays.sort(candidates);
        cs_rcrs(candidates, target, result, new ArrayList<>(), target, 0);
        return result;

    }


    public static int jump(int[] nums) {
//        nums = [2,3,1,1,4]

        if (nums.length <= 2) return nums.length - 1;
        int result = 0;
        int maxReach = 0;
        int lastJumpMaxReach = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            maxReach = Math.max(maxReach, i + nums[i]);

            if (maxReach >= nums.length - 1) {
                result++;
                break;
            }
            if (i == lastJumpMaxReach) {
                lastJumpMaxReach = maxReach;
               result++;
            }

        }
        return result;
    }

    public static int maxProfit(int[] prices) {
//        prices = [7,1,5,3,6,4]
        int totalProfit  = 0;
        for (int i = 1; i < prices.length; i++) {
            int dailyProfit = Math.max(0, prices[i] - prices[i - 1]);
            totalProfit += dailyProfit;
        }

        return totalProfit;
    }

    public static double getMaxExpectedProfit(int N, int[] V, int C, double S) {
        int valueCollected = 0;
        int visits = 0;
        double[][] valueInRoom = new double[N][N];
        double[] profit = new double[N];

        for (int i = 0; i < N; i++) {
            valueInRoom[i][i] = V[i];

            for (int j = i + 1; j < N; j++) {
                valueInRoom[i][j] = (valueInRoom[i][j - 1] * (1 - S)) + V[j];
            }
        };

        profit[N-1] = Math.max(0, V[N - 1] - C);

        for (int i = N - 2; i >=0 ; i--) {
            double maxProfit = Math.max(0, V[i] - C + V[i + 1]);
            for (int j = i + 1; j < N; j++) {
                double currProfit = (j < N - 1) ? profit[j+1] : 0;
                maxProfit = Math.max(maxProfit, valueInRoom[i][j] - C + currProfit);
            }
            profit[i] = maxProfit;
        }

        return profit[0];
    }

    static int countSubarrays(int[] a) {
        int[] prefix = new int[a.length + 1];
        int result = 0;

        for (int i = 1; i <= a.length; i++) {
            prefix[i] = prefix[i-1] + a[i - 1];
        }

        for (int i = 1; i < a.length + 1; i++) {
            for (int j = i; j < a.length + 1; j++) {
                int currSum = prefix[j] - prefix[i - 1];
                int currCount = j - i + 1;
                int remainSum = prefix[a.length] - currSum;
                int remainCount = (a.length - currCount == 0) ? 1 : a.length - currCount;
                if (currSum/currCount > remainSum/remainCount) {
                    result++;
                }
            }
        }

        return result;

    }

    public static int majorityElement(int[] nums) {
//        Map<Integer, Integer> counts = new HashMap<>();


        int currCandidate = 0;
        int currCount = 0;
        for (int i = 0; i < nums.length; i++) {
           if (currCount == 0) {
               currCandidate = nums[i];
               currCount++;
           } else {
               if (nums[i] == currCandidate) {
                   currCount++;
               } else {
                   currCount--;
               }
           }
        }

        return currCandidate;
    }
    public static void nextPermutation(int[] nums) {

        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            for (int j = nums.length - 1; j > 0; j--) {
                if (nums[j] > nums[i]) {
                    int tmp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = tmp;
                    break;
                }
            }
        }

        int start = i + 1;
        int end = nums.length - 1;
        while (start < end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
    }

    public static int maxArea(int[] height) {
        int start = 0, end = height.length - 1;
        int result = 0;
        while (start < end) {
            int currHeight = Math.min(height[start], height[end]);
            result = Math.max(result, currHeight * (end - start));
            if (height[start] < height[end]) {
                start++;
            } else {
                end--;
            }
        }
        return result;
    }

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int minDiff = Integer.MAX_VALUE;
        int result = 0;

        for (int i = 0; i < nums.length; i++) {
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int currSum = nums[i] + nums[left] + nums[right];
                if (currSum == target) return currSum;

                int currDiff = Math.abs(currSum - target);
                if (currDiff < minDiff) {
                    minDiff = currDiff;
                    result = currSum;
                }
                if (currSum > target) {
                    right--;
                } else {
                    left++;
                }
            }

        }

        return result;
    }

    public static int maxSubArray(int[] nums) {
        int result = nums[0];
        int currMax = nums[0];


        for (int i = 1; i < nums.length; i++) {
            currMax = Math.max(currMax, 0) + nums[i];
            result = Math.max(result, currMax);
        }

        return result;

    }

    public static int longestConsecutive(int[] nums) {

//        Arrays.sort(nums);
        int currSeq = 1;
        int maxSeq = 0;
        Set<Integer> seqs = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            seqs.add(nums[i]);
        }

        for (int num: seqs) {
            int curr = num;
            if (!seqs.contains(num - 1)) {
                while (seqs.contains(curr + 1)) {
                    currSeq++;
                    curr++;
                }
                maxSeq = Math.max(currSeq, maxSeq);
            }


        }
        return maxSeq;
    }

    public static int power2Combinations(int[] nums) {
        int count = 0;

        Map<Integer, Integer> counts = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            counts.put(nums[i], counts.getOrDefault(nums[i], 0) + 1);
            for (int twos = 0; twos < 21; twos ++) {
                int secondNum = (1 << twos) - nums[i];
                if (counts.containsKey(secondNum)) count += counts.get(secondNum);
            }
        }
        return count;
    }

    public static boolean canReorderDoubled(int[] arr) {

        Map<Integer, Integer> frequency = new HashMap<>();

        for (int num: arr) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }

        if (frequency.containsKey(0) && frequency.get(0) % 2 == 1) return false;

        List<Integer> keys = new ArrayList<>(frequency.keySet());
        keys.sort(Comparator.comparingInt(Math::abs));

        for (int key: keys) {
            if (frequency.getOrDefault(key * 2, 0) < frequency.get(key)) return false;

            frequency.put(key * 2, frequency.getOrDefault(key * 2, 0) - frequency.get(key));
        }
        return true;
    }

    public static int threeSumMulti(int[] arr, int target) {
        //Taken from https://algo.monster/liteproblems/923

        final int MOD = 1_000_000_007; // 1e9 + 7 is represented as 1000000007

        int[] count = new int[101]; // Array to store count of each number, considering constraint 0 <= arr[i] <= 100
        // Populate the count array with the frequency of each value in arr
        for (int num : arr) {
            ++count[num];
        }
        long ans = 0; // To store the result, using long to avoid integer overflow before taking the modulus

        // Iterate through all elements in arr to find triplets
        for (int j = 0; j < arr.length; ++j) {
            int second = arr[j]; // The second element in the triplet
            --count[second]; // Decrement count since this number is being used in the current triplet

            // Iterate from the start of the array to the current index 'j'
            for (int i = 0; i < j; ++i) {
                int first = arr[i]; // The first element in the triplet
                int third = target - first - second; // Calculate the third element

                // Check if third element is within range and add the count to the answer
                if (third >= 0 && third <= 100) {
                    ans = (ans + count[third]) % MOD; // Use the modulo to avoid overflow and get the correct result
                }
            }
        }
        // Cast and return the final answer as an integer
        return (int) ans;
    }

    public static int numberOfSubarrays(int[] nums, int k) {
        int result = 0;

        int currOdds = 0;
        int[] prefixOdds = new int[nums.length + 1];
        prefixOdds[0]=1;

        for (int i = 0; i < nums.length; i++) {
            currOdds += nums[i] & 1;
            if (currOdds - k >= 0) {
                result += prefixOdds[currOdds - k];
            }
            prefixOdds[currOdds]++;
        }

        return result;

    }

    public static int[] occurrencesOfElement(int[] nums, int[] queries, int x) {
        int[] result = new int[queries.length];
        List<Integer> occurences = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == x) occurences.add(i);
        }

        for (int i = 0; i < queries.length; i++) {
            if (queries[i] <= occurences.size()) {
                result[i] = occurences.get(queries[i] - 1);
            } else {
                result[i] = -1;
            }
        }

        return result;
    }

    public static int[] queryResults(int limit, int[][] queries) {
        Map<Integer, Integer> ballColor = new HashMap<>();
        Map<Integer, Integer> colorCounts = new HashMap<>();
        int[] result = new  int[queries.length];
        int numColors = 0;

        for (int i = 0; i < queries.length; i++) {
            int ball = queries[i][0];
            int color = queries[i][1];
            int oldColor = ballColor.getOrDefault(ball, 0);

            colorCounts.put(color, colorCounts.getOrDefault(color, 0) + 1);

            // did ball change color?
            // if so, decrement the old count
            if (oldColor > 0) {
                if (oldColor != color) {
                    int count = colorCounts.get(oldColor) - 1;
                    if (count <= 0) {
                        colorCounts.remove(oldColor);
                    } else {
                        colorCounts.put(oldColor, count);
                    }
                } else {
                    //color didn't change os undo increment
                    colorCounts.put(color, colorCounts.get(color) - 1);
                }
            }

            ballColor.put(ball, color);

            result[i] = colorCounts.size();

        }
        return result;

    }


    public static int[] sumNeighbors(int[] arr) {
        int[] result = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            int sum = arr[i];
            if (i > 0) {
                sum += arr[i - 1];
            }
            if (i < arr.length -1) {
                sum += arr[i +1];
            }
            result[i] = sum;
        }
        return result;
    }

    public static void main(String[] args) {

        int[] arr = {4,0,1,-2,3};
        Utils.printArr(sumNeighbors(arr));

//        int[][] queries = {{0,1},{0,4},{0,4},{0,1},{1,2}};
//        int limit = 1;
//        Utils.printArr(queryResults(limit, queries));
//        int[] nums = {1,3,1,7};
//        int[] queries = {1,3,2,4};
//        int x = 1;
//        Utils.printArr(occurrencesOfElement(nums, queries, x));
//        int[] arr = {1,1,2,1,1};
//        int k = 3;
//
//        System.out.println(numberOfSubarrays(arr, k));
//        int[] nums = {4,-2,2,-4};
//        System.out.println(canReorderDoubled(nums));


//        System.out.println(power2Combinations(nums));
//        System.out.println(longestConsecutive(nums));
//        int nums[] = {-2,1,-3,4,-1,2,1,-5,4};
//        System.out.println(maxSubArray(nums));

//        int [] nums = {-1,2,1,-4};
//        int[] nums = {-4,2,2,3,3,3};
//        int target = 1;
//        System.out.println(threeSumClosest(nums, target));
//        int[] height = {1,8,6,2,5,4,8,3,7};
//        System.out.println(maxArea(height));
//        int[] nums = {1, 3, 5, 4, 2};
//        nextPermutation(nums);
//        Utils.printArr(nums);
//        int[] nums = {3,2,1,1,1,2,3,1};
//        System.out.println(majorityElement(nums));
//        int[] nums = {3, 4, -1, 2, 1};
//        int k = 5;

//        System.out.println(subarraySum(nums, k));
//        int[] a = {6, 3, 5};
//        System.out.println(countSubarrays(a));
//        int[] V = {10, 2, 8, 6, 4};
//        int N = V.length;
//        int C = 5;
//        double S = 0.5;
//
//        System.out.println(getMaxExpectedProfit(N, V, C, S));

//        int[] prices = {1,2,3,4,6};
//        System.out.println(maxProfit(prices));

//        int[] nums = {2,3,0,1,4};
//        int[] nums = {1,2,3};
////        int[] nums = {1,2,1,1,1};
//        System.out.println(jump(nums));

//        int[] candidates = {2,3,6,7};
//        int target = 7;
//
//        ArrayExercises ae = new ArrayExercises();
//
//        Utils.printList(combinationSum(candidates, target));

//        int[] nums = {5,7,7,8,8,10};
//        int[] nums = {1, 4};
//        int target = 4;
//        Utils.printArr(searchRange(nums, target));

//        int[] height = {1,8,6,2,5,4,8,3,7};
//        int[] height = {1,1};
//        System.out.println(maxArea(height));


//        int[] nums = {2,2,2,2,2};
//        int[] nums = {1,0,-1,0,-2,2};
//        int target = 8;
//        Utils.printList(fourSum(nums, target));


//        int[] fruits = {3,3,3,1,2,1,1,2,3,3,4};

//            System.out.println(totalFruit(fruits));

//        int [] nums = {-1,0,1,2,-1,-4};
//        System.out.println(threeSum(nums));

//        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
//        int[][] intervals = {{1,5},{2,3},{3,4},{4,6}};
//        System.out.println(eraseOverlapIntervals(intervals));
//        Utils.printArr(merge(intervals));

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
//        int[] nums = {23,2,6,4,7};
//        int k = 13;
//        System.out.println(checkSubarraySum(nums, k));

//        int[] nums = {1,3,-1,-3,5,3,6,7};
        //        int[] nums = {1,4,2,3};
//        int[] nums = {1, 2, 3, 4};
//        int[] nums = {2147483647,1,2,3,4,5,6,7,2147483647};
//        int k = 2;
//
//        Utils.printArr(medianSlidingWindow(nums,k));

    }
}
