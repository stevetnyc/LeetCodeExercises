import java.util.ArrayList;
import java.util.List;

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

    public static void main(String[] args) {


        String num1 = "15", num2 = "100";
        System.out.println(multiply(num1, num2));

//        int n = 3;
//
//        Utils.printList(generateParenthesis(n));

//        String s = "PAYPALISHIRING";
//        int numRows = 4;
//
//        System.out.println(convert(s, numRows));


    }
}
