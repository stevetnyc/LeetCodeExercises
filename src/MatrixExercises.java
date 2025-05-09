import java.util.*;
//import java.util.HashSet;

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

    public static boolean isValidSudoku(char[][] board) {
        boolean result = true;
        int[][] rowMap = new int[9][10];
        int[][] colMap = new int[9][10];
        int[][] subMap = new int[9][10];
        Set<Character> charSet = new HashSet<>();
        charSet.add('1');
        charSet.add('2');
        charSet.add('3');
        charSet.add('4');
        charSet.add('5');
        charSet.add('6');
        charSet.add('7');
        charSet.add('8');
        charSet.add('9');


        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c ++) {
                int subMat = 0;

                if (r < 3 ) {
                    if (c < 3) {
                        subMat = 0;
                    } else if (c < 6) {
                        subMat = 1;
                    } else {
                        subMat = 2;
                    }
                } else if (r < 6) {
                    if (c < 3) {
                        subMat = 3;
                    } else if (c < 6) {
                        subMat = 4;
                    } else {
                        subMat = 5;
                    }
                } else {
                    if (c < 3) {
                        subMat = 6;
                    } else if (c < 6) {
                        subMat = 7;
                    } else {
                        subMat = 8;
                    }
                }

                if (board[r][c] != '.') {
                    if (charSet.contains(board[r][c])) {
                        int currNum = Integer.valueOf(board[r][c]) - '0';
                        if (rowMap[r][currNum] > 0 || colMap[c][currNum] > 0 || subMap[subMat][currNum] > 0) {
                            return false;
                        } else {
                            rowMap[r][currNum] = 1;
                            colMap[c][currNum] = 1;
                            subMap[subMat][currNum] = 1;
                        }
                    } else {
                        return false;
                    }
                }
            }
        }

        return result;

    }


    public static int shortestPathBinaryMatrix(int[][] grid) {
//        {0,0,0}
//        {1,1,0}
//        {1,1,0}
        if (grid[0][0] != 0) {
            return -1;
        }

        Queue<int[]> cellQueue = new ArrayDeque<>();
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        visited[0][0] = true;
        cellQueue.offer(new int[] {0, 0});

        for (int currPath = 1; !cellQueue.isEmpty(); currPath++) {
            for (int l = cellQueue.size(); l > 0; l--) {
                int[] currCell = cellQueue.poll();
                int r = currCell[0];
                int c = currCell[1];
                if (r == grid.length - 1 && c == grid[0].length - 1) {
                    return currPath;
                }

                for (int i = r - 1; i <= r + 1; i++) {
                    for (int j = c - 1; j <= c + 1; j++) {
                        if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && !visited[i][j] && grid[i][j] == 0) {
                            visited[i][j] = true;
                            cellQueue.offer(new int[]{i, j});
                        }
                    }
                }
            }

        }

        return -1;

    }

    public static void main(String[] args) {

        int[][] grid = {{0,0,0},{1,1,0},{1,1,0}};
        System.out.println(shortestPathBinaryMatrix(grid));

//        char[][] board =
//                {{'8','3','.','.','7','.','.','.','.'}
//                        ,{'6','.','.','1','9','5','.','.','.'}
//                        ,{'.','9','8','.','.','.','.','6','.'}
//                        ,{'8','.','.','.','6','.','.','.','3'}
//                        ,{'4','.','.','8','.','3','.','.','1'}
//                        ,{'7','.','.','.','2','.','.','.','6'}
//                        ,{'.','6','.','.','.','.','2','8','.'}
//                        ,{'.','.','.','4','1','9','.','.','5'}
//                        ,{'.','.','.','.','8','.','.','7','9'}};
//
//        System.out.println(isValidSudoku(board));

//        int[][] arr = {{1,2,3},{4,5,6},{7,8,9}};
//        int[][] arr = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
//        Utils.printArr(spiralOrder(arr));
//        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
//        int[][] matrix = {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
//        rotate(matrix);


    }

}
