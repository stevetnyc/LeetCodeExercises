public class GreedyExercises {

    public static boolean canJump(int[] nums) {
        //        nums = [3,2,1,0,4]

        int maxIdx = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > maxIdx) return false;
            maxIdx = Math.max(maxIdx, i + nums[i]);
        }
        return true;
    }

    public static int canCompleteCircuit(int[] gas, int[] cost) {


        int start = 0;
        int currGas = 0;
        int totalGas = 0;
        int totalCost = 0;
        for (int i = 0; i < gas.length; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
            if (currGas + gas[i] < cost[i]) {
                start = i + 1;
                currGas = 0;
            } else {
                currGas += gas[i] - cost[i];
            }
        }

        if (totalCost > totalGas) start = -1;
        return start;
    }

    public static void main(String[] args) {

        //gas = [1,2,3,4,5], cost = [3,4,5,1,2]
//        int[] gas = {5,1,2,3,4};
//        int[] cost = {4,4,1,5,1};
//        int[] gas = {3,1,1};
//        int[] cost = {1,2,2};
//
//        System.out.println(canCompleteCircuit(gas, cost));

//        int[] nums = {2,3,1,1,4};
        int[] nums = {3,2,1,0,4};

        System.out.println(canJump(nums));
    }
}
