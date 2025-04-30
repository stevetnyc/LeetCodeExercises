import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public static int up_rcrs(int m, int n, int currX, int currY, int currCount) {
        if (currX == m - 1 && currY == n - 1) return 1;

        if (currX >= m || currY >= n) return 0;


    }
    public static int uniquePaths(int m, int n) {




    }


    public static void main(String[] args) {

        String code = "06";
        System.out.println(numDecodings(code));
    }
}
