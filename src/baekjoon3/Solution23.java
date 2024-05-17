package baekjoon3;

import java.io.*;
import java.util.*;

/**
 * 2573 빙산 (Gold4)
 * 단계별로 접근하니깐 생각보다 수월하게 풀렸던 문제
 * 우선 메인 반복문을 돌려야 하는데 입력으로 받은 빙산지도의 값들이 모두 0이 되면 반복문을 빠져나가게끔 했다.
 * 그리고 정답으로 제출 할 메인카운트를 1증가시키고 빙산을 녹인다. 빙산의 상하좌우를 보고 그 카운트만큼 빙산의 숫자를 줄이면 된다.
 * 그 이후 새로 정립된 빙산지도를 이용하여 방문배열을 만들고 bfs탐색을 진행하는데
 * bfs메서드를 호출할때마다 카운트를 증가시킨다. 그 카운트가 2번이상 호출되었다는것은 빙산이 2조각이상으로 쪼개졌다는 뜻이고
 * 이때 메인반복문을 탈출시켜주면 된다. 해당 조건으로 탈출되었으면 정상적으로 메인카운트를 출력해주면 되고
 * 그렇지 않고 빙산이 다 녹음으로써 메인반복문을 빠져나갔으니 0을 출력시켜주면 된다.
 */
public class Solution23 {
    static int N;
    static int M;
    static int[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int answer = 0;
        boolean twoOrMorePieces = false;
        String[] split = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        label:
        while (check()) {
            answer++;
            melting();
            visited = makeVisitedMap();
            int iceberg = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] > 0 && visited[i][j]) {
                        iceberg++;
                        visited[i][j] = false;
                        bfs(i, j);
                    }
                }
                if (iceberg >= 2) {
                    twoOrMorePieces = true;
                    break label;
                }
            }
        }

        System.out.println(twoOrMorePieces ? answer : 0);
    }

    static void melting() {
        int[][] countMap = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int count = 0;
                if (map[i][j] > 0) {
                    if (map[i - 1][j] == 0) {
                        count++;
                    }

                    if (map[i + 1][j] == 0) {
                        count++;
                    }

                    if (map[i][j - 1] == 0) {
                        count++;
                    }

                    if (map[i][j + 1] == 0) {
                        count++;
                    }
                }
                countMap[i][j] = count;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0) {
                    if (map[i][j] < countMap[i][j]) {
                        map[i][j] = 0;
                    } else {
                        map[i][j] -= countMap[i][j];
                    }
                }
            }
        }
    }

    static void bfs(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {i, j});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];

            if (map[x - 1][y] > 0 && visited[x - 1][y]) {
                visited[x - 1][y] = false;
                queue.add(new int[] {x - 1, y});
            }

            if (map[x + 1][y] > 0 && visited[x + 1][y]) {
                visited[x + 1][y] = false;
                queue.add(new int[] {x + 1, y});
            }

            if (map[x][y - 1] > 0 && visited[x][y - 1]) {
                visited[x][y - 1] = false;
                queue.add(new int[] {x, y - 1});
            }

            if (map[x][y + 1] > 0 && visited[x][y + 1]) {
                visited[x][y + 1] = false;
                queue.add(new int[] {x, y + 1});
            }
        }
    }

    static boolean[][] makeVisitedMap() {
        boolean[][] visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = map[i][j] > 0;
            }
        }
        return visited;
    }
    static boolean check() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
