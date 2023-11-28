package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1018 체스판 다시 칠하기
 */
public class Solution52 {
    static char[][] board;
    static String[] b_start = {"BWBWBWBW", "WBWBWBWB", "BWBWBWBW", "WBWBWBWB", "BWBWBWBW", "WBWBWBWB", "BWBWBWBW", "WBWBWBWB"};
    static String[] w_start = {"WBWBWBWB", "BWBWBWBW", "WBWBWBWB", "BWBWBWBW", "WBWBWBWB", "BWBWBWBW", "WBWBWBWB", "BWBWBWBW"};
    static int answer = 32;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] split = bufferedReader.readLine().split(" ");
        int row = Integer.parseInt(split[0]);
        int column = Integer.parseInt(split[1]);
        board = new char[row][column];

        for (int i = 0; i < row; i++) {
            String readLine = bufferedReader.readLine();
            for (int j = 0; j < column; j++) {
                board[i][j] = readLine.charAt(j);
            }
        }

        int searchRow = row - 7;
        int searchColumn = column - 7;

        for (int i = 0; i < searchRow; i++) {
            for (int j = 0; j < searchColumn; j++) {
                search(i, j);
            }
        }

        System.out.println(answer);
    }

    private static void search(int i, int j) {
        int row = i + 8;
        int column = j + 8;

        int x = 0;
        int y = 0;
        int wCount = 0;
        int bCount = 0;

        // 시작이 W일 경우
        for (int k = i; k < row; k++) {
            for (int l = j; l < column; l++) {
                if (board[k][l] != w_start[x].charAt(y)) {
                    wCount++;
                }
                y++;
            }
            y = 0;
            x++;
        }

        x = 0;

        //시작이 B일 경우
        for (int k = i; k < row; k++) {
            for (int l = j; l < column; l++) {
                if (board[k][l] != b_start[x].charAt(y)) {
                    bCount++;
                }
                y++;
            }
            y = 0;
            x++;
        }

        answer = Math.min(answer, Math.min(wCount, bCount));
    }
}
