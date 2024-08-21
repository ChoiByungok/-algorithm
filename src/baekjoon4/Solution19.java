package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 14442 벽 부수고 이동하기 2 (Gold3)
 * 2차원 배열을 이용하여 풀었는데 예제도 다 통과 되길래 제출했더니 1퍼센트에서 틀렸다고 나왔다.
 * 그래서 질문게시판에있는 모든 반례들을 다 넣어봤는데도 정답이 잘 출력되었다.
 * 그래서 뭐가 문제인지 챗 GPT를 통해 물어봤더니 이 문제는 3차원 배열을 이용하여 풀어야 되는 문제였다.
 * 2차원배열로는 왜 한계가 있는지 물어봤더니
 * 예를 들어 특정위치 x,y 에 도달했을 때 벽을 부수고 온 경우와 벽을 부수고 오지 않은 경우가 있을 수 있는데
 * 2차원 배열로 방문처리를 해버리면 이미 x,y에 도착한 어떤 노드가 존재한다면 이 좌표를 방문하지 않기때문에
 * 최적의 경로를 탐색하지 못할수가 있다고 한다.
 * 그래서 벽을 부수는 모든 경우의수를 담을 3차원 배열을 선언하여 벽을 부술때마다 말 그대로 차원을 이동시켜 줘야 한다.
 * 그나마 어느정도 이해는 가서 다행이다. 이런 비슷한 문제를 풀어보면서 감을 잡아야 할거같다.
 */
public class Solution19 {
    static class Node {
        int x;
        int y;
        int breakWall;
        int step;

        public Node(int x, int y, int breakWall, int step) {
            this.x = x;
            this.y = y;
            this.breakWall = breakWall;
            this.step = step;
        }
    }
    static int N;
    static int M;
    static int K;
    static char[][] map;
    static boolean[][][] visited;
    static int[] dirX = new int[] {1, -1 ,0, 0};
    static int[] dirY = new int[] {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] split = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        K = Integer.parseInt(split[2]);
        map = new char[N][M];
        visited = new boolean[N][M][K + 1];

        for (int i = 0; i < N; i++) {
            String readLine = bufferedReader.readLine();
            for (int j = 0; j < M; j++) {
                char c = readLine.charAt(j);
                map[i][j] = c;
            }
        }

        System.out.println(bfs());
    }


    static int bfs() {
        int answer = -1;
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, 0, 1));
        visited[0][0][0] = true;
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            int x = poll.x;
            int y = poll.y;
            int step = poll.step;
            int breakWall = poll.breakWall;

            if (x == N - 1 && y == M - 1) {
                return step;
            }

            for (int i = 0; i < 4; i++) {
                int nx = dirX[i] + x;
                int ny = dirY[i] + y;
                if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                    if (map[nx][ny] == '0' && !visited[nx][ny][breakWall]) {
                        visited[nx][ny][breakWall] = true;
                        queue.add(new Node(nx, ny, breakWall, step + 1));
                    }

                    if (map[nx][ny] == '1' && breakWall < K && !visited[nx][ny][breakWall + 1]) {
                        visited[nx][ny][breakWall + 1] = true;
                        queue.add(new Node(nx, ny, breakWall + 1, step + 1));
                    }
                }
            }
        }

        return answer;
    }
}
