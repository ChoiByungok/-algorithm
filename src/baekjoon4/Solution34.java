package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 14248 점프점프 (Silver2)
 * 간단한 bfs 문제 방문배열을 하나 설정하여 bfs 탐색이 끝난 후
 * 방문배열의 몇개의 노드를 방문했는지 세서 그 수를 출력하면 된다.
 */
public class Solution34 {
    static boolean[] visited;
    static int N;
    static int[] stoneBridge;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        visited = new boolean[N];
        stoneBridge = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int now = Integer.parseInt(bufferedReader.readLine()) - 1;
        bfs(now);
        System.out.println(check());
    }

    static void bfs(int now) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(now);
        visited[now] = true;
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            int jump = stoneBridge[poll];
            int next = poll + jump;
            int prev = poll - jump;

            if (prev >= 0 && !visited[prev]) {
                visited[prev] = true;
                queue.add(prev);
            }

            if (next < N && !visited[next]) {
                visited[next] = true;
                queue.add(next);
            }
        }
    }

    static int check() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                count++;
            }
        }
        return count;
    }
}
