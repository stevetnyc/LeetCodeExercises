import java.util.*;

public class DPExercises {

    static int numDecodings(String s) {

        int possVals = 0;

        int len = s.length();
        if (s.charAt(0) == '0') return 0;

        int[] dp = new int[len + 1];

        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < len + 1; i++) {
            int digit = Integer.valueOf(s.substring(i - 1, i));
            if (digit != 0) {
                dp[i] += dp[i - 1];
            }

            digit = Integer.valueOf(s.substring(i - 2, i));
            if (digit >= 10 && digit <= 26) {
                dp[i] += dp[i - 2];
            }
        }

      return dp[len];
    }

    public static long uniquePaths(int m, int n) {
        long[][] dp = new long[m][n];

        for (int i = 0; i < m ; i++) dp[i][0] = 1;
        for (int j = 0; j < n ; j++) dp[0][j] = 1;

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m-1][n-1];

    }

    public int lengthOfLIS(int[] nums) {
//        nums = [8,2,4,3,6,12]

        int result = 0;
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
        }

        for (int i = 1; i < nums.length; i++) {
           for (int j = 0; j < i; j++) {
               if (nums[i] > nums[j]) {
                       dp[i] = Math.max(dp[i], dp[j] + 1);
               }
           }
        }



        return result;
    }

    public static void main(String[] args) {

//        String code = "06";
//        System.out.println(numDecodings(code));
        int m = 9;
        int n = 9;
        System.out.println(uniquePaths(m, n));
    }
}
