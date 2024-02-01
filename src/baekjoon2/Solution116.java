package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 14502 연구소 (Gold4)
 * 벽은 꼭 3개를 세워야 한다. 바이러스는 상하좌우 4방향으로 이동가능
 * 벽3개를 사용하여 최대한 많은 안전영역을 확보하라
 * 벽을 어디다 세우느냐가 관건이며 이것을 어떤기준으로 찾느냐가 관건
 * 결국 고민하다가 구글링해보니 벽을 칠 수 있는 모든 위치에 벽을 전부 세워보고
 * 그중 가장 많은 영역을 확보할 수 있는 영역을 찾는방법밖에 없었다. 나도 어렴풋이 생각은 해봤는데
 * 시간적으로 가능할지 긴가민가해서 시도해보지는 않았는데 이게 정답이었나봄
 * dfs 를 이용하여 가능한 위치에 모두 벽을 세워보고
 * bfs 를 이용하여 안전영역의 크기를 구한다.
 */
public class Solution116 {
    static int N;
    static int M;
    static int[][] lab;
    static int answer = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = bufferedReader.readLine().split(" ");

        N = Integer.parseInt(nm[0]);
        M = Integer.parseInt(nm[1]);
        lab = new int[N][M];

        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < M; j++) {
                lab[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
        dfs(0);

        System.out.println(answer);
    }

    static void dfs(int wall) {
        if (wall == 3) {
            bfs();
            return;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (lab[i][j] == 0) {
                    lab[i][j] = 1;
                    dfs(wall + 1);
                    lab[i][j] = 0;
                }

            }
        }
    }

    static void bfs() {
        int count = 0;
        int[][] newLab = new int[N][M];
        List<int[]> virus = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                newLab[i][j] = lab[i][j];
                if (lab[i][j] == 2) {
                    virus.add(new int[]{i,j});
                }
            }
        }

        while (!virus.isEmpty()) {
            List<int[]> newVirus = new ArrayList<>();

            for (int[] ints : virus) {
                int x = ints[0];
                int y = ints[1];

                if (x + 1 < N && newLab[x + 1][y] == 0) {
                    newVirus.add(new int[]{x + 1, y});
                    newLab[x+1][y] = 2;
                }

                if (x - 1 >= 0 && newLab[x - 1][y] == 0) {
                    newVirus.add(new int[]{x - 1, y});
                    newLab[x-1][y] = 2;
                }

                if (y + 1 < M && newLab[x][y + 1] == 0) {
                    newVirus.add(new int[]{x, y + 1});
                    newLab[x][y+1] = 2;
                }

                if (y - 1 >= 0 && newLab[x][y - 1] == 0) {
                    newVirus.add(new int[]{x, y - 1});
                    newLab[x][y-1] = 2;
                }
            }
            virus = newVirus;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (newLab[i][j] == 0) {
                    count++;
                }
            }
        }

        answer = Math.max(answer, count);
    }

}
