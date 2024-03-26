package mwang.online.classic150;

import org.junit.Test;

import java.util.*;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2024/3/26 09:32
 * @description: No36_ValiadSudoku
 */
public class No36_ValiadSudoku {

    @Test
    public void test() {
        final char[][] case1 =
                {{'.', '3', '.', '.', '7', '.', '.', '.', '.'}
                        , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                        , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
                        , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
                        , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
                        , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
                        , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
                        , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
                        , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        assert isValidSudoku(case1);

        char[][] case2 =
                {{'8', '3', '.', '.', '7', '.', '.', '.', '.'}
                        , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                        , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
                        , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
                        , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
                        , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
                        , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
                        , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
                        , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        assert !isValidSudoku(case2);

        char[][] case3 =
                {{'.', '.', '.', '.', '5', '.', '.', '1', '.'},
                        {'.', '4', '.', '3', '.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.', '3', '.', '.', '1'},
                        {'8', '.', '.', '.', '.', '.', '.', '2', '.'},
                        {'.', '.', '2', '.', '7', '.', '.', '.', '.'},
                        {'.', '1', '5', '.', '.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.', '2', '.', '.', '.'},
                        {'.', '2', '.', '9', '.', '.', '.', '.', '.'},
                        {'.', '.', '4', '.', '.', '.', '.', '.', '.'}};
        assert !isValidSudoku(case3);

    }

    public boolean isValidSudoku(char[][] board) {
        int n = board.length;
        // row check
        for (int i = 0; i < n; i++) {
            final char[] checkList = new char[9];
            for (int j = 0; j < n; j++) {
                checkList[j] = board[i][j];
            }
            if (!duplicateCheck(checkList)) return false;
        }
        // column check
        for (int j = 0; j < n; j++) {
            final char[] checkList = new char[9];
            for (int i = 0; i < n; i++) {
                checkList[i] = board[i][j];
            }
            if (!duplicateCheck(checkList)) return false;
        }
        // sub-boxes check
        for (int j = 0; j < n; j += 3) {
            for (int i = 0; i < n; i += 3) {
                final char c1 = board[i][j];
                final char c2 = board[i][j + 1];
                final char c3 = board[i][j + 2];
                final char c4 = board[i + 1][j];
                final char c5 = board[i + 1][j + 1];
                final char c6 = board[i + 1][j + 2];
                final char c7 = board[i + 2][j];
                final char c8 = board[i + 2][j + 1];
                final char c9 = board[i + 2][j + 2];
                if (!duplicateCheck(c1, c2, c3, c4, c5, c6, c7, c8, c9)) return false;
            }
        }
        return true;
    }

    private boolean duplicateCheck(char... chars) {
        final HashSet<Character> set = new HashSet<>();
        for (char c : chars) {
            if (c == '.') continue;
            if (!set.add(c)) return false;
        }
        return true;
    }

}


