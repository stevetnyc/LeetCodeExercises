import java.util.ArrayList;
//import java.util.HashSet;
import java.util.List;
//import java.util.Set;

public class MatrixExercises {


    public static List<Integer> spiralOrder(int[][] matrix) {
//        Set<Integer[][]> visited = new HashSet<>();
        String currDir = "r";
        int currR = 0;
        int currC = 0;
        int minR = 0;
        int minC = 0;
        int rowsCount = matrix.length;
        int colsCount = matrix[0].length;
        int maxR = rowsCount - 1;
        int maxC = colsCount - 1;
        int size = rowsCount * colsCount;
        List<Integer> result = new ArrayList<>();

        while (result.size() < size) {
            result.add(matrix[currR][currC]);
            switch (currDir) {
                case "r":
                    if (currC < maxC) {
                        currC++;
                    } else {
                        currDir = "d";
                        currR++;
                        minR++;
                    }
                    break;
                case "d":
                    if (currR < maxR) {
                        currR++;
                    } else {
                        currDir = "l";
                        currC--;
                        maxC--;
                    }
                    break;
                case "l":
                    if (currC > minC) {
                        currC--;
                    } else {
                        currDir = "u";
                        currR--;
                        maxR--;
                    }
                    break;
                case "u":
                    if (currR > minR) {
                        currR--;
                    } else {
                        currDir = "r";
                        currC++;
                        minC++;
                    }
                    break;
            }
        }
        return result;

    }


    public static void rotate(int[][] matrix) {
//        Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
//        Output: [[7,4,1],[8,5,2],[9,6,3]]

        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = i; j < matrix.length; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length/2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix[i].length - 1 - j];
                matrix[i][matrix[i].length - 1 - j] = tmp;
            }
        }
        Utils.printArr(matrix);
    }

    public static void main(String[] args) {

//        int[][] arr = {{1,2,3},{4,5,6},{7,8,9}};
//        int[][] arr = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
//        Utils.printArr(spiralOrder(arr));
//        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] matrix = {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
        rotate(matrix);

    }

}
