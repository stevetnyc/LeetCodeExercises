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

    public static void main(String[] args) {

//        char[][] arr = {
//                {'B', 'L', 'C', 'H'},
//                {'D', 'E', 'L', 'T'},
//                {'D', 'A', 'K', 'A'}
//        };
        char[][] arr = {
                {'A','B','C','E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };

        String word = "SEE";

        System.out.println(exist(arr, word));
    }
}
