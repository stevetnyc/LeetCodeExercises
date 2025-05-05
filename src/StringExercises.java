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

    public static void gp_rcrs(String s, int n, int openCount, int closeCount, List<String> result){
        if (s.length() == n * 2) {
            result.add(s);
            return;
        }

        if (openCount < n) {
            gp_rcrs(s + "(", n, openCount + 1, closeCount, result);
        }
        if (closeCount < openCount) {
            gp_rcrs(s + ")", n, openCount, closeCount + 1, result);
        }
    }
    public static List<String> generateParenthesis(int n) {
        // n= 3: ["((()))","(()())","(())()","()(())","()()()"]

        List<String> result = new ArrayList<>();
        gp_rcrs("", n, 0, 0, result);
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
//        for (int i = 0; i < s.length(); i++) {
//            for (int j = 1; j < s.length(); j++) {
//                if (isPal(s, i, j) && j - i + 1 > maxLen) {
//                    maxLen = j - i + 1;
//                    start = i;
//                }
//            }
//
//        }
        return s.substring(start, start + maxLen);
    }

    public static void main(String[] args) {


        String s = "xxxmadamimadamzaaax";
        System.out.println(longestPalindrome(s));
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
