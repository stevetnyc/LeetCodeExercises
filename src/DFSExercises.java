
import java.util.ArrayList;
import java.util.List;

public class DFSExercises {


    static boolean dfs(char[][] board, String word, int r, int c, int idx) {
        if (idx == word.length()) return true;

        if (r < 0 || c < 0 || r >= board.length || c >= board[r].length || board[r][c] != word.charAt(idx)){
            return false;
        }


        return (
                    dfs(board, word, r - 1, c, idx+1) ||
                            dfs(board, word, r + 1, c, idx+1) ||
                            dfs(board, word, r, c - 1, idx+1) ||
                            dfs(board, word, r, c + 1, idx+1)
                );


    }

    static boolean exist(char[][] board, String word) {

        boolean found = false;

        for (int r = 0; r < board.length;  r++) {
            for (int c = 0; c< board[r].length; c++) {
                if (board[r][c] == word.charAt(0)) {
                    found = dfs(board, word, r, c, 0);
                    if (found) return found;
                }
            }
        }

        return found;
    }

    public static void p_rcrs(int[] nums, List<List<Integer>> result, List<Integer> currList, boolean[] seen, int idx) {
        if (idx >= nums.length) {
            result.add(new ArrayList<>(currList));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!seen[i]) {
                currList.add(nums[i]);
                seen[i] = true;
                p_rcrs(nums, result, currList, seen, idx + 1);
                currList.remove(currList.size() - 1);
                seen[i] = false;
            }
        }
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currList = new ArrayList<>();
        boolean[] seen = new boolean[nums.length];

        p_rcrs(nums, result, currList, seen, 0);

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        Utils.printList(permute(nums));

//        char[][] arr = {
//                {'B', 'L', 'C', 'H'},
//                {'D', 'E', 'L', 'T'},
//                {'D', 'A', 'K', 'A'}
//        };
//        char[][] arr = {
//                {'A','B','C','E'},
//                {'S', 'F', 'C', 'S'},
//                {'A', 'D', 'E', 'E'}
//        };
//
//        String word = "SEE";
//
//        System.out.println(exist(arr, word));
    }
}
