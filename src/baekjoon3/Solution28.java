package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 5014 스타트링크 (Silver1)
 * 방향이 2가지인 bfs 문제
 * 조건에 따라 움직여주면서 현재층이 목적층이면 몇번만에 도착했는지 반환해주고
 * 도달하지 못하고 while문을 빠져나왔다면 use the stairs 을 출력해주면 된다.
 */
public class Solution28 {
    static class Elevator {
        int floor;
        int step;

        public Elevator(int floor, int step) {
            this.floor = floor;
            this.step = step;
        }
    }
    static int F, S, G, U, D;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        F = Integer.parseInt(tokenizer.nextToken());
        S = Integer.parseInt(tokenizer.nextToken());
        G = Integer.parseInt(tokenizer.nextToken());
        U = Integer.parseInt(tokenizer.nextToken());
        D = Integer.parseInt(tokenizer.nextToken());
        visited = new boolean[F + 1];
        visited[0] = true;

        int answer = bfs();
        System.out.println(answer == -1 ? "use the stairs" : answer);
    }

    static int bfs() {
        Queue<Elevator> queue = new LinkedList<>();
        queue.add(new Elevator(S, 0));
        while (!queue.isEmpty()) {
            Elevator now = queue.poll();
            int floor = now.floor;
            int step = now.step;

            if (floor == G) {
                return step;
            }

            if (floor + U <= F && !visited[floor + U]) {
                visited[floor + U] = true;
                queue.add(new Elevator(floor + U, step + 1));
            }

            if (floor - D >= 1 && !visited[floor - D]) {
                visited[floor - D] = true;
                queue.add(new Elevator(floor - D, step + 1));
            }
        }


        return -1;
    }
}
