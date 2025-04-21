class JumpGame {

    static boolean canJump(int[] nums) {
        if (nums.length == 1) return true;
        if (nums[0] == 0) return false;
        boolean canReach = true;
        int i = nums.length - 1;
        while (i > 0) {
            if (nums[i] > 0) i--;
            else {
                canReach = false;
                int getPast = i + 1;
                if (getPast > nums.length - 1) getPast = nums.length - 1;
                i--;
                while (i >= 0 && nums[i] < getPast - i) i--;
                if (i > 0 || (i == 0 && nums[i] >= getPast)) canReach = true;
            }
        }
        return canReach;
    }

    public static void main(String[] args) {

//        int[] nums = {2,3,1,1,4};
//        int[] nums = {3,2,1,0,4};
//        int[] nums = {3,2,2,0,1,0,1,2};
        int[] nums = {2,0,0};
        System.out.println("Can reach: " + canJump(nums));
    }
}
