package mwang.online.hot100;

/**
 * 79. 单词搜索
 * 思路：递归回溯剪枝
 */
public class No79_WordSearch {

    public static void main(String[] args) {
        char[][] board = {{'C', 'A', 'A'},
                {'A', 'A', 'A'},
                {'B', 'C', 'D'}};
        String word = "AAB";
        System.out.println(exist(board, word));
    }

    public static boolean exist(char[][] board, String word) {
        // 用来标记走过的路
        int m = board.length;
        int n = board[0].length;
        int[][] map = new int[board.length][n];
        // 先找到第一个字符
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boolean res = dfs(board, map, word, 0, i, j);
                if (res) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean dfs(char[][] board, int[][] map, String word, int index, int i, int j) {
        // 递归终止条件
        if (index == word.length()) {
            return true;
        }
        // 超出范围
        if (i < 0 || i > board.length - 1 || j < 0 || j > board[0].length - 1) {
            return false;
        }
        // 当前位置匹配, 尝试用上下左右来匹配后续位置
        if (map[i][j] == 0 && board[i][j] == word.charAt(index)) {
            // 标记当前位置已经走过
            map[i][j] = 1;
            boolean hasNext = dfs(board, map, word, index + 1, i - 1, j) ||
                    dfs(board, map, word, index + 1, i + 1, j) ||
                    dfs(board, map, word, index + 1, i, j - 1) ||
                    dfs(board, map, word, index + 1, i, j + 1);
            if (!hasNext) {
                // 如果上下左右都不匹配后续位置，则撤销当前位置
                map[i][j] = 0;
            } else {
                 return true;
            }
        }
        return false;
    }
}
