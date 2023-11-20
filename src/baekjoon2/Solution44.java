package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 2823 유턴 싫어
 * 기존에 풀때는 유턴의 기준을 현재 위치에서 갈 수 있는 방향이 한 방향 밖에 없는데 그 길이 바로 직전에서 온 길이면
 * 유턴이라고 정의하고 문제를 풀었었다. 그렇게 하기 위해 직전의 길을 저장하면서 넓이 우선 탐색으로 풀었었는데
 * 예제는 다 맞는데 36%에서 틀렸다
 * 그래서 결국 다른 사람의 풀이를 본 결과 다른 사람은 이전의 길을 저장할 필요 없이 그냥 현재 길에서 갈 수 있는 방향이
 * 한 방향 밖에 없을때 막다른 길이라고 정의를 했던 것이다.
 * 너무 문제를 어렵게 생각하고 있었던거 같다.
 */
public class Solution44 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] rc = bufferedReader.readLine().split(" ");
        int R = Integer.parseInt(rc[0]);
        int C = Integer.parseInt(rc[1]);
        boolean[][] map = new boolean[R][C];
        boolean deadEnd = false;

        for (int i = 0; i < R; i++) {
            String readLine = bufferedReader.readLine();
            for (int j = 0; j < readLine.length(); j++) {
                if (readLine.charAt(j) == '.') {
                    map[i][j] = true;
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int road = 0;
                if (map[i][j]) {
                    if (i + 1 < R && map[i + 1][j]) {
                        road++;
                    }
                    if (i - 1 >= 0 && map[i - 1][j]) {
                        road++;
                    }
                    if (j + 1 < C && map[i][j + 1]) {
                        road++;
                    }
                    if (j - 1 >= 0 && map[i][j - 1]) {
                        road++;
                    }
                    if (road < 2) {
                        deadEnd = true;
                        break;
                    }
                }
            }
            if (deadEnd) {
                break;
            }
        }
        System.out.println(deadEnd ? 1 : 0);
    }
}
