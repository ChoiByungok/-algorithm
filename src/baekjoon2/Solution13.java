package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 7562 나이트의 이동
 */
public class Solution13 {
    static class Knight {
        int x;
        int y;
        int step;

        public Knight(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }

    static boolean[][] chessBoard;
    static int endX;
    static int endY;
    static StringBuilder answer = new StringBuilder();
    static int size;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < T; i++) {
            size = Integer.parseInt(bufferedReader.readLine());
            chessBoard = new boolean[size][size];

            String[] start = bufferedReader.readLine().split(" ");
            int startX = Integer.parseInt(start[0]);
            int startY = Integer.parseInt(start[1]);

            String[] end = bufferedReader.readLine().split(" ");
            endX = Integer.parseInt(end[0]);
            endY = Integer.parseInt(end[1]);

            bfs(startX, startY);
        }
        System.out.println(answer);
    }

    private static void bfs(int startX, int startY) {
        Queue<Knight> queue = new LinkedList<>();
        queue.add(new Knight(startX, startY, 0));

        while (!queue.isEmpty()) {
            Knight now = queue.poll();
            int x = now.x;
            int y = now.y;
            int step = now.step;

            if (x == endX && y == endY) {
                answer.append(step).append("\n");
                break;
            }

            if (x-2 >= 0 && y-1 >= 0 && !chessBoard[x-2][y-1]) {
                chessBoard[x-2][y-1] = true;
                queue.add(new Knight(x-2, y-1, step+1));
            }
            if (x-2 >= 0 && y+1 < size && !chessBoard[x-2][y+1]) {
                chessBoard[x-2][y+1] = true;
                queue.add(new Knight(x-2, y+1, step+1));
            }
            if (x-1 >= 0 && y-2 >= 0 && !chessBoard[x-1][y-2]) {
                chessBoard[x-1][y-2] = true;
                queue.add(new Knight(x-1, y-2, step+1));
            }
            if (x-1 >= 0 && y+2 < size && !chessBoard[x-1][y+2]) {
                chessBoard[x-1][y+2] = true;
                queue.add(new Knight(x-1, y+2, step+1));
            }
            if (x+1 < size && y-2 >= 0 && !chessBoard[x+1][y-2]) {
                chessBoard[x+1][y-2] = true;
                queue.add(new Knight(x+1, y-2, step+1));
            }
            if (x+1 < size && y+2 < size && !chessBoard[x+1][y+2]) {
                chessBoard[x+1][y+2] = true;
                queue.add(new Knight(x+1, y+2, step+1));
            }
            if (x+2 < size && y-1 >= 0 && !chessBoard[x+2][y-1]) {
                chessBoard[x+2][y-1] = true;
                queue.add(new Knight(x+2, y-1, step+1));
            }
            if (x+2 < size && y+1 < size && !chessBoard[x+2][y+1]) {
                chessBoard[x+2][y+1] = true;
                queue.add(new Knight(x+2, y+1, step+1));
            }
        }
    }
}
