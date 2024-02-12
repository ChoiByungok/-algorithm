package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 5212 지구 온난화 (Silver2)
 * 현재맵을 탐색하여 50년 후의 예상지도를 그려준다.
 * 50년 후의 예상지도를 그리면서 잘라야 할 위치를 체크해준다.
 * 잘라야할 위치 내에서 반복문을 실행하여 50년후의 지도를 출력해주면 되는 문제
 */
public class Solution127 {
    static boolean[][] map;
    static int R;
    static int C;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] split = bufferedReader.readLine().split(" ");
        R = Integer.parseInt(split[0]);
        C = Integer.parseInt(split[1]);
        map = new boolean[R][C];
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < R; i++) {
            String readLine = bufferedReader.readLine();
            for (int j = 0; j < readLine.length(); j++) {
                char c = readLine.charAt(j);
                if (c == 'X') {
                    map[i][j] = true;
                }
            }
        }
        int firstX = 10;
        int lastX = -1;
        int firstY = 10;
        int lastY = -1;
        boolean[][] fiftyYearsLater = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j]) {
                    if (check(i, j)) {
                        fiftyYearsLater[i][j] = true;
                        firstX = Math.min(firstX, i);
                        firstY = Math.min(firstY, j);
                        lastX = Math.max(lastX, i);
                        lastY = Math.max(lastY, j);
                    }
                }
            }
        }
        for (int i = firstX; i <= lastX; i++) {
            for (int j = firstY; j <= lastY; j++) {
                if (fiftyYearsLater[i][j]) {
                    answer.append("X");
                } else {
                    answer.append(".");
                }
            }
            answer.append("\n");
        }
        System.out.println(answer);
    }

    private static boolean check(int x, int y) {
        int count = 0;
        if (x == 0) {
            count++;
        } else {
            if (!map[x - 1][y]) {
                count++;
            }
        }

        if (x == R - 1) {
            count++;
        } else {
            if (!map[x + 1][y]) {
                count++;
            }
        }

        if (y == 0) {
            count++;
        } else {
            if (!map[x][y - 1]) {
                count++;
            }
        }

        if (y == C - 1) {
            count++;
        } else {
            if (!map[x][y + 1]) {
                count++;
            }
        }

        return count < 3;
    }
}
