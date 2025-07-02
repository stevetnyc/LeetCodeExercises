import java.util.*;

public class StringExercises {


    public static String convert(String s, int numRows) {

        if (numRows == 1) return s;
        String[] matrix = new String[numRows];
        int row = 0;
        int col = 0;
        int idx = 0;
        String dir = "d";

        for (int i = 0; i < numRows; i++){
            matrix[i] = "";
        }


        while (idx < s.length()) {
            matrix[row] += s.charAt(idx);
            idx++;
            switch (dir) {
                case "d":
                    if (row < numRows - 1) {
                        row++;
                    } else {
                        dir = "u";
                        row--;
                    }
                    break;
                case "u":
                    if (row > 0) {
                        row--;
                    } else {
                        dir = "d";
                        row++;
                    }
                    break;
            }


        }
        String result = "";
        for (int i = 0; i < numRows; i++) {
            result += matrix[i];
        }
        return  result;
    }

    public static void gp_rcrs(int n, String currString, List<String> result, int openCount, int closedCount) {
        if (currString.length() == n * 2) {
            result.add(currString);
            return;
        }
        if (openCount < n) {
            gp_rcrs(n, currString +"(", result, openCount + 1, closedCount);
        }
        if (closedCount < openCount) {
            gp_rcrs(n, currString +")", result, openCount, closedCount + 1);
        }

    }
    public static List<String> generateParenthesis(int n) {
        // n= 3: ["((()))","(()())","(())()","()(())","()()()"]

        List<String> result = new ArrayList<>();
        gp_rcrs(n,"", result, 0, 0);
        return result;
    }

    public static String multiply(String num1, String num2) {
        // num1 = 12; num2 = 16
        if ("0".equals(num1) || "0".equals(num2)) return "0";
        String result = "";

        int[] productArr = new int[num1.length() + num2.length()];

        for (int i = num1.length() - 1; i >= 0; i--) {
            int digit1 = num1.charAt(i) - '0';
            for (int j = num2.length() - 1; j >= 0; j--) {
                int digit2 = num2.charAt(j) - '0';

                productArr[i+j+1] += digit1 * digit2;
            }
        }

        for (int i = productArr.length - 1; i > 0; i--) {
            productArr[i - 1] += productArr[i] / 10;
            productArr[i] = productArr[i] % 10;
        }

        int start = productArr[0] == 0 ? 1 : 0;

        for (int i = start; i < productArr.length; i++) {
            result += productArr[i];
        }

        return result;

    }

    public static List<List<String>> groupAnagrams(String[] strs) {
//        Input: strs = ["eat","tea","tan","ate","nat","bat"]
//        Output: [["bat"],["nat","tan"],["ate","eat","tea"]]


        Map<String, List<String>> foundMap = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] str1Arr = strs[i].toCharArray();
            Arrays.sort(str1Arr);
            String newStr = new String(str1Arr);
            foundMap.putIfAbsent(newStr, new ArrayList<>());
            foundMap.get(newStr).add(strs[i]);
        }


        return new ArrayList<>(foundMap.values());

    }

    public static boolean isPal(String s, int low, int high) {

        while (low < high) {
            if (s.charAt(low) != s.charAt(high)) return false;
            low++;
            high--;
        }

        return true;
    }

    public static String longestPalindrome(String s) {
        //  s = "xxxmadamimadamzaaax"
        int maxLen = 0;
        int start = 0;

        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j <= 1; j++) {
                int low = i;
                int high = i + j;
                while (low >= 0 && high < s.length() && s.charAt(low) == s.charAt(high)) {
                    if (high - low + 1 > maxLen) {
                        start = low;
                        maxLen = high - low + 1;
                    }
                    low--;
                    high++;
                }

            }

        }

        return s.substring(start, start + maxLen);
    }

    public static int minDistance(String word1, String word2) {

        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i2 = 1; i2 <= len2; i2++ ) {
            dp[0][i2] = i2;
        }

        for (int i1 = 1; i1 <= len1; i1++) {
            dp[i1][0] = i1;

            for (int i2 = 1; i2 <= len2; i2 ++) {
                if (word1.charAt(i1- 1) == word2.charAt(i2 - 1)) {
                    dp[i1][i2] = dp[i1 - 1][i2 - 1];
                } else {
                    int insertion = dp[i1][i2 -1];
                    int deletion = dp[i1 - 1][i2];
                    int replacement = dp[i1 - 1][i2 - 1];
                    dp[i1][i2] = Math.min(insertion, Math.min(deletion, replacement)) + 1;
                }
            }
        }
        return dp[len1][len2];
    }


    public static boolean isInterleave(String s1, String s2, String s3) {
//        s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
        int len1 = s1.length(), len2 = s2.length();
        if (s3.length() != len1 + len2) return false;
        boolean dp[][] = new boolean[len1 + 1][len2 + 1];

        dp[0][0] = true;

        for (int j = 1; j <= len2; j++) {
            dp[0][j] = (s2.charAt(j - 1) == s3.charAt(j - 1)) && dp[0][j - 1];
        }
        for (int i = 1; i <= len1; i++) {
            dp[i][0] = (s1.charAt(i - 1) == s3.charAt(i - 1)) && dp[i - 1][0];
        }

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                int k = i + j ;
                dp[i][j] = ((s1.charAt(i - 1) == s3.charAt(k - 1) && dp[i-1][j]) ||
                        (s2.charAt(j - 1) == s3.charAt(k - 1) && dp[i][j-1]));
            }
        }

        return dp[len1][len2];

    }



    public static boolean wordBreak(String s, List<String> wordDict) {
        boolean[] tracker = new boolean[s.length() + 1];
        tracker[0] = true;

        for (int j = 1; j <= s.length(); j++) {
            for (int i = 0; i < j; i++) {
                if (tracker[i] && wordDict.contains(s.substring(i, j))) {
                    tracker[j] = true;
                    break;
                }
            }
        }

        return tracker[s.length()];
    }

    public static boolean exist_rcrs(char[][] board, String word, int r, int c, int idx, boolean[][]visited) {
//        board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
        if (idx == word.length()) return true;
        if (r >= board.length || c >= board[0].length || r < 0 || c < 0 || visited[r][c] ||
                word.charAt(idx) != board[r][c]) return false;

        visited[r][c] = true;
        return exist_rcrs(board, word, r + 1, c, idx + 1, visited) ||
                    exist_rcrs(board, word, r, c + 1, idx + 1, visited) ||
                    exist_rcrs(board, word, r - 1, c, idx + 1, visited) ||
                    exist_rcrs(board, word, r, c - 1, idx + 1, visited);
    }

    public static boolean exist(char[][] board, String word) {

        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0) && exist_rcrs(board,word, i, j, 0, visited)) return true;
            }
        }
        return false;
    }

    public static int calculate(String s) {
        int result = 0;
        Set<Character> nums = new HashSet<>();

        nums.add('0');
        nums.add('1');
        nums.add('2');
        nums.add('3');
        nums.add('4');
        nums.add('5');
        nums.add('6');
        nums.add('7');
        nums.add('8');
        nums.add('9');

        int len = s.length();
        String currNum = "";

        Stack<Integer> opsQueue = new Stack<>();
        int sign = 1;

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (nums.contains(c)) {
                int start = i;
                while (start < len && nums.contains(s.charAt(start))) {
                    currNum += s.charAt(start);
                    start++;
                }
                int num = Integer.parseInt(currNum);
                result += sign * num;
                currNum = "";
                i = start - 1;
            } else if (c == '+') {
                sign = 1;
            } else if (c == '-') {
                sign = -1;
            } else if (c == '(') {
                opsQueue.push(result);
                opsQueue.push(sign);
                result = 0;
                sign = 1;
            } else if (c == ')') {
                result = opsQueue.pop() * result + opsQueue.pop();
            }
        }
        return  result;

    }

    public static int patternMatches(String pattern, String source) {
        Set<Character> vowels = new HashSet<>();

        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');

        int l = 0;
        int r = pattern.length();
        int count = 0;

        while (r <= source.length()) {
            String sub = source.substring(l, r);
            boolean match = true;
            for (int i = 0; i < sub.length(); i++) {
                Character c = sub.charAt(i);
                if (pattern.charAt(i) == '0' && !vowels.contains(c)) {
                    match = false;
                    break;
                }
                if (pattern.charAt(i) == '1' && vowels.contains(c)) {
                    match = false;
                    break;
                }
            }
            if (match) count++;
            l++;
            r++;
        }

        return count;
    }

    public static int longestPalindrome(String[] words) {
        Map<String, Integer> counts = new HashMap<>();
        int result = 0;
        int singleCenter = 0;
        for (String word: words) {
            counts.put(word, counts.getOrDefault(word, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry: counts.entrySet()){
            String word = entry.getKey();
            String rev = new StringBuilder(word).reverse().toString();

            int count = entry.getValue();

            if (word.charAt(0) == word.charAt(1)) {
                singleCenter += count % 2;
            } else {
                result += Math.min(count, counts.getOrDefault(rev, 0)) * 2;
            }
        }
        result += singleCenter > 0 ? 2 : 0;
        return result;

    }

    public static void main(String[] args) {

        String[] words = {"lc","cl","gg"};
        System.out.println(longestPalindrome(words));
//        String pattern = "010";
//        String source = "amazing";
//        System.out.println(patternMatches(pattern, source));

//        String  s = "10 + 4 - 3";
//        String s = "(1+(4+5+2)-3)+(6+8)";
//        System.out.println(calculate(s));

//        board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"

//        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
//        String word = "ABCCED";
//        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
//        String word = "SEE";
//        System.out.println(exist(board, word));
//        String s = "leetscode";
//        String[] arr = {"leet", "code", "leets"};
//        List<String> wordDict = new ArrayList<>(Arrays.asList(arr));
//        System.out.println(wordBreak(s, wordDict));
//        String string1 = "aabcc";
//        String string2 = "dbbca";
//        String string3 = "aadbbcbcac";
//
//        System.out.println(isInterleave(string1, string2, string3));


//        String word1 = "intention";
//        String word2 = "execution";
//
//        System.out.println(minDistance(word1, word2));

//        int n = 3;
//        Utils.printList(generateParenthesis(n));

//        String s = "xxxmadamimadamzaaax";
//        System.out.println(longestPalindrome(s));
//        String[] strs = {"eat","tea","tan","ate","nat","bat"};
//        Utils.printList(groupAnagrams(strs));
//        String num1 = "15", num2 = "100";
//        System.out.println(multiply(num1, num2));

//        int n = 3;
//
//        Utils.printList(generateParenthesis(n));

//        String s = "PAYPALISHIRING";
//        int numRows = 4;
//
//        System.out.println(convert(s, numRows));


    }
}
