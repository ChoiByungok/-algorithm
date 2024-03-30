package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 10157 자리배정 (Silver4)
 * 얼마전에 풀었던 달팽이(1913) 과 비슷한문제
 * 그 문제는 정사각형의 이차원배열이 주어지고 정 가운데 좌표부터 시계방향으로 확산해 나가는 거였다면
 * 해당문제는 정사각형도 아니며 0,0 좌표부터 반시계방향으로 크게 돌며 가운데로 줄어드는 문제
 * 그 문제는 회전하기 위한 규칙을 찾아야 했는데
 * 해당문제는 boolean 형 2차원 배열을 만들어 0,0 부터 돌면서
 * 해당 좌표가 배열의 범위를 벗어나거나 이미 방문한 위치라면 반시계방향으로 돌리면 되는문제라 더 간단했다.
 * 근데 이제 문제의 예시가 우리가 일반적으로 인지하고 있는 2차원 배열의 모습과 반대였기 때문에
 * 그걸 신경쓰는 거랑 x y 좌표가 반대라는것이 헷갈렸던 문제
 */
public class Solution175 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] split = bufferedReader.readLine().split(" ");
        int R = Integer.parseInt(split[0]);
        int C = Integer.parseInt(split[1]);
        int K = Integer.parseInt(bufferedReader.readLine());
        boolean[][] visited = new boolean[C][R];
        int x = 0;
        int y = 0;
        int dir = 3;
        int max = R * C;

        if (K <= max) {
            for (int i = 1; i < K; i++) {
                visited[x][y] = true;
                switch (dir) {
                    case 1:
                        if (x - 1 >= 0 && !visited[x - 1][y]) {
                            x--;
                        } else {
                            y--;
                            dir = 4;
                        }
                        break;
                    case 2:
                        if (y + 1 < R && !visited[x][y + 1]) {
                            y++;
                        } else {
                            x--;
                            dir = 1;
                        }
                        break;
                    case 3:
                        if (x + 1 < C && !visited[x + 1][y]) {
                            x++;
                        } else {
                            y++;
                            dir = 2;
                        }
                        break;
                    case 4:
                        if (y - 1 >= 0 && !visited[x][y - 1]) {
                            y--;
                        } else {
                            x++;
                            dir = 3;
                        }
                        break;
                }

            }
            System.out.println((y + 1) + " " + (x + 1));
        } else {
            System.out.println(0);
        }
    }
}
