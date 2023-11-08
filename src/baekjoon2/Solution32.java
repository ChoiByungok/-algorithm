package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 11660 구간 합 구하기 5
 */
public class Solution32 {
    public static void main(String[] args) throws IOException {
        StringBuilder answer = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        
        int[][] map = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            for (int j = 0; j < split.length; j++) {
                map[i][j + 1] = map[i][j] + Integer.parseInt(split[j]);
            }
        }

        for (int i = 0; i < m; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            int sum = 0;
            int x1 = Integer.parseInt(split[0]);
            int y1 = Integer.parseInt(split[1]);
            int x2 = Integer.parseInt(split[2]);
            int y2 = Integer.parseInt(split[3]);

            for (int j = x1; j <= x2; j++) {
                sum += (map[j][y2] - map[j][y1 - 1]);
            }

            if (i != m - 1) {
                answer.append(sum).append("\n");
            } else {
                answer.append(sum);
            }
        }
        System.out.println(answer);
    }
}
