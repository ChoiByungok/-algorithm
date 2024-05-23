package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 17070 파이프 옯기기 1 (Gold5)
 * 그래프탐색 문제인줄 알고 bfs로 접근했다가 68퍼센트에서 시간초과 맞음
 * 처음에는 파이프가 걸치고 있는 모든 좌표를 저장했었는데
 * 생각해보니 그럴필요가 없어서 마지막 지점만 저장을했고
 * 파이프의 어느 한 부위가 (N, N) 에 걸치면 끝난다고 했는데
 * 그냥 마지막 지점이 (N, N) 이면 되는거라 리스트 순회돌려서 확인할 필요도 없었음
 * 그래서 그냥 큐에 담을 수 있는지 없는지 조건만 파악한 다음 파이프의 마지막 좌표만 저장하고 큐에 담음
 * 그러니깐 간신히 통과되긴했는데 이 문제는 그래프 탐색이지만 넓이우선 탐색이 아닌 dp로 접근하는 문제 였는데
 * 어떻게 dp로 접근하는지는 다음에 다른사람의 풀이를 봐야할듯 싶음
 */
public class Solution29 {
    static class Pipe {
        int[] point;
        int dir;

        public Pipe(int[] point, int dir) {
            this.point = point;
            this.dir = dir;
        }
    }
    static int N;
    static boolean[][] map;
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        map = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = tokenizer.nextToken().equals("1");
            }
        }
        if (map[N - 1][N - 1]) {
            System.out.println(answer);
        } else {
            bfs();
            System.out.println(answer);
        }
    }

    static void bfs() {
        Queue<Pipe> queue = new LinkedList<>();
        queue.add(new Pipe(new int[] {0, 1}, 0));

        while (!queue.isEmpty()) {
            Pipe poll = queue.poll();
            int[] point = poll.point;
            int dir = poll.dir;
            int x = point[0];
            int y = point[1];
            if (x == N - 1 && y == N - 1) {
                answer++;
                continue;
            }

            switch (dir) {
                case 0: // → 0, 1 가능
                    if (y + 1 < N && !map[x][y + 1]) {
                        List<int[]> list = new ArrayList<>();
                        queue.add(new Pipe(new int[] {x, y + 1}, 0));
                    }
                    if (x + 1 < N && y + 1 < N && !map[x + 1][y] && !map[x][y + 1] && !map[x + 1][y + 1]) {
                        queue.add(new Pipe(new int[] {x + 1, y + 1}, 1));
                    }
                    break;
                case 1: // ↘ 0, 1, 2, 전부가능
                    if (y + 1 < N && !map[x][y + 1]) {
                        queue.add(new Pipe(new int[] {x, y + 1}, 0));
                    }
                    if (x + 1 < N && y + 1 < N && !map[x + 1][y] && !map[x][y + 1] && !map[x + 1][y + 1]) {
                        queue.add(new Pipe(new int[] {x + 1, y + 1}, 1));
                    }
                    if (x + 1 < N && !map[x + 1][y]) {
                        queue.add(new Pipe(new int[] {x + 1, y}, 2));
                    }
                    break;
                case 2: // ↓ 1, 2 가능
                    if (x + 1 < N && y + 1 < N && !map[x + 1][y] && !map[x][y + 1] && !map[x + 1][y + 1]) {
                        queue.add(new Pipe(new int[] {x + 1, y + 1}, 1));
                    }
                    if (x + 1 < N && !map[x + 1][y]) {
                        queue.add(new Pipe(new int[] {x + 1, y}, 2));
                    }
                    break;
            }
        }
    }
}
