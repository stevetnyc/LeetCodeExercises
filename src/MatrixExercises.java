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



    public static void main(String[] args) {

//        int[][] arr = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] arr = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        Utils.printArr(spiralOrder(arr));

    }

}
