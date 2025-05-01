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

    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        int numRows = 4;

        System.out.println(convert(s, numRows));


    }
}
