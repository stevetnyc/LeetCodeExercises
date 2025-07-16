import java.util.*;


public class BFSExercises {

    static Set<Utils.Pair> checkNeighbors(int[][] grid, int x, int y) {
         Set<Utils.Pair> result = new HashSet<>();

         if (x > 0) {
             if (grid[x - 1][y] == 1) {
                 result.add(new Utils.Pair(x - 1, y));
             }
         }
         if (x < grid.length - 1) {
             if (grid[x + 1][y] == 1) {
                 result.add(new Utils.Pair(x + 1, y));
             }
         }
        if (y > 0) {
            if (grid[x][y - 1] == 1) {
                result.add(new Utils.Pair(x, y - 1));
            }
        }
        if (y < grid[x].length - 1) {
            if (grid[x][y + 1] == 1) {
                result.add(new Utils.Pair(x, y + 1));
            }
        }
         return result;
    }

    static int orangesRotting(int[][] grid) {
        // 0 = empty | 1 = fresh | 2 = rotten

        int minutes = 0;
        Queue<Utils.Pair> rottens = new ArrayDeque<>();
        Set<Utils.Pair> visited = new HashSet<>();

        // find and queue iniital rotten
        for (int i = 0; i < grid.length; i++) {
            for (int b = 0; b < grid[i].length; b++) {
                if (grid[i][b] == 2) {
                    Set<Utils.Pair> freshNeighbors = checkNeighbors(grid, i, b);
                    rottens.addAll(freshNeighbors);
                }
            }
        }

        //every pass through the queue is 1 minute
        int totalRottens = rottens.size();
        if (totalRottens == 0) return 0;
        while (totalRottens > 0) {
            minutes += 1;
            totalRottens = rottens.size();
            for (int i = 0; i < totalRottens; i++) {
                Utils.Pair currPair = rottens.poll();
                if (!visited.contains(currPair)) {
                    visited.add(currPair);
                    grid[currPair.x][currPair.y] = 2;
                    Set<Utils.Pair> freshNeighbors = checkNeighbors(grid, currPair.x, currPair.y);
                    rottens.addAll(freshNeighbors);
                }
            }
        }

        // check grid i any fresh remain
        for (int i = 0; i < grid.length; i++) {
            for (int b = 0; b < grid[i].length; b++) {
                if (grid[i][b] == 1) return -1;
            }
        }
        return minutes;
    }

    static void printArray(int[][] arr) {
        for (int i = 0; i <arr.length; i++) {
            for (int b = 0; b < arr[i].length; b++) {
                System.out.println("arr["+i+"][" + b + "] = " + arr[i][b]);
            }
        }
    }
    static int[][] updateMatrix_nestedloops(int[][] mat) {
         int[][] result = new int[mat.length][mat[0].length];

         int shortestDistance = Integer.MAX_VALUE;
         for (int r = 0; r < mat.length; r++) {
             for (int c = 0; c < mat[r].length; c++) {
                 if (mat[r][c] == 0) {
                     result[r][c] = 0;
                 } else {
                     int nr = r;
                     int tmpDistance = 0;

                     while (nr >= 0 && mat[nr][c] != 0) {
                         nr--;
                         tmpDistance++;
                     }

                     if (tmpDistance > 0 && nr >= 0 && mat[nr][c] == 0)  shortestDistance = Math.min(shortestDistance, tmpDistance);
                     tmpDistance = 0;
                     nr = r;

                     while (shortestDistance != 1 && nr < mat.length && mat[nr][c] != 0) {
                         nr++;
                         tmpDistance++;
                     }
                     if (tmpDistance > 0 && nr < mat.length && mat[nr][c] == 0)  shortestDistance = Math.min(shortestDistance, tmpDistance);
                     tmpDistance = 0;

                     int nc = c;
                     while (shortestDistance != 1 && nc >= 0 && mat[r][nc] != 0) {
                         nc--;
                         tmpDistance++;
                     }
                     if (tmpDistance > 0 && nc >= 0 && mat[r][nc] == 0)  shortestDistance = Math.min(shortestDistance, tmpDistance);
                     tmpDistance = 0;
                     nc = c;
                     while (shortestDistance != 1 && nc < mat[r].length && mat[r][nc] != 0) {
                         nc++;
                         tmpDistance++;
                     }
                     if (tmpDistance > 0 && nc < mat[r].length && mat[r][nc] == 0)  shortestDistance = Math.min(shortestDistance, tmpDistance);

                     if (shortestDistance > 0 && shortestDistance < Integer.MAX_VALUE) {
                         result[r][c] = shortestDistance;
                     } else {
                         result[r][c] = -1;
                     }
                 }
                 shortestDistance = Integer.MAX_VALUE;
             }
         }
         return result;

    }

    static int[][] updateMatrix(int[][] mat) {
         Queue<Utils.Pair> cells = new ArrayDeque<>();
         int[][] result = new int[mat.length][mat[0].length];

         //initialize result
        for (int r = 0; r < result.length; r++) {
            for (int c = 0; c < result[r].length; c++ ){
                result[r][c] = -1;
            }
        }

        for (int r = 0; r < result.length; r++) {
            for (int c = 0; c < result[r].length; c++) {
                if (mat[r][c] == 0) {
                    cells.add(new Utils.Pair(r, c));
                    result[r][c] = 0;
                }
            }
        }

        int distance = 1;
        while (cells.size() > 0) {
            int cellsCount = cells.size();
            for (int i = 0; i < cellsCount; i++) {
                Utils.Pair currPair = cells.poll();
                int x = currPair.x;
                int y = currPair.y;

                if (x > 0 && result[x - 1][y] == -1) {
                    cells.add(new Utils.Pair(x - 1, y));
                    result[x-1][y] = distance;
                }
                if (x < result.length - 1 && result[x + 1][y] == -1) {
                    cells.add(new Utils.Pair(x + 1, y));
                    result[x+1][y] = distance;
                }
                if (y > 0 && result[x][y - 1] == -1) {
                    cells.add(new Utils.Pair(x,  y - 1));
                    result[x][y-1] = distance;
                }
                if (y < result[x].length - 1 && result[x][y + 1] == -1) {
                    cells.add(new Utils.Pair(x,  y + 1));
                    result[x][y+1] = distance;
                }
            }
            distance++;
        }

        return result;

    }
    public static void main(String[] args) {

//        int[][] arr = {{2,1,1},{1,1,0},{0,1,1}};

//        int[][] arr = {{2,1,1},{0,1,1},{1,0,1}};
//        int[][] arr = {{1,2,2}};
        int[][] arr = {{0,0,0},{0,1,0},{0,0,0}};

//        int[][] arr = {{0,0,0},{0,1,0},{1,1,1}};
//        System.out.println(updateMatrix(arr));

        printArray(updateMatrix(arr));
    }
}
