package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 16918 봄버맨 (Silver1)
 * 문제 예시에서 1초후 2초후 3초후 4초후 까지 진행 한 뒤
 * 5초후 부터는 1초후 모습이랑 같길래 1 2 3 4 1 2 3 4 계속 반복되는줄알고
 * 미리 1 2 3 4 초 후 의 맵의 모습을 저장 한 뒤
 * 입력받은 N 값에서 (N - 1) % 4 + 1 연산으로 풀으려고 했는데 16퍼센트에서 계속 틀렸다고 나옴
 * 알고보니 모든 예시가 그렇게 되는건아니었음
 * 그렇게 시간 낭비하다가 그냥 하라는대로 구현하니깐 바로풀림
 */
public class Solution143 {
    static int R;
    static int C;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] rcn = bufferedReader.readLine().split(" ");
        StringBuilder answer = new StringBuilder();
        int second = 1;
        R = Integer.parseInt(rcn[0]);
        C = Integer.parseInt(rcn[1]);
        int N = Integer.parseInt(rcn[2]);
        map = new int[R][C];

        for (int i = 0; i < R; i++) {
            String readLine = bufferedReader.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = readLine.charAt(j) == 'O' ? 2 : 0;
            }
        }

        while (second != N) {
            second++;
            List<int[]> bombList = new ArrayList<>();
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    int bomb = map[i][j];
                    switch (bomb) {
                        case 0:
                            map[i][j] = 3;
                            break;
                        case 1:
                            bombList.add(new int[] {i, j});
                            break;
                        case 2:
                        case 3:
                            map[i][j]--;
                            break;
                    }
                }
            }
            for (int[] ints : bombList) {
                int x = ints[0];
                int y = ints[1];
                map[x][y] = 0;
                if (x + 1 < R) {
                    map[x + 1][y] = 0;
                }
                if (x - 1 >= 0) {
                    map[x - 1][y] = 0;
                }
                if (y + 1 < C) {
                    map[x][y + 1] = 0;
                }
                if (y - 1 >= 0) {
                    map[x][y - 1] = 0;
                }
            }
        }
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                answer.append(map[i][j] != 0 ? "O" : ".");
            }
            answer.append("\n");
        }
        System.out.println(answer);
    }
}
