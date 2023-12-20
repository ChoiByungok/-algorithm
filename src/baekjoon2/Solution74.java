package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 16928 뱀과 사다리 게임
 * 내 풀이같은 경우는 사다리와 뱀의 정보를 리스트에 저장하고 루프를 돌려서 확인해서
 * 풀이시간이 오래걸렸지만 이 정보를 맵에 저장하면 시간을 단축시킬 수 있을거같다
 */
public class Solution74 {
    static class Player {
        int now;
        int step;

        public Player(int now, int step) {
            this.now = now;
            this.step = step;
        }
    }
    static class LadderSnake {
        int start;
        int end;

        public LadderSnake(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = bufferedReader.readLine().split(" ");
        int answer = 0;
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        LadderSnake[] ladderSnakes = new LadderSnake[n + m];
        boolean[] visited = new boolean[101];
        for (int i = 0; i < n + m; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            ladderSnakes[i] = new LadderSnake(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
        }
        Arrays.sort(ladderSnakes, new Comparator<LadderSnake>() {
            @Override
            public int compare(LadderSnake o1, LadderSnake o2) {
                return o1.start - o2.start;
            }
        });

        Queue<Player> queue = new LinkedList<>();
        queue.add(new Player(1, 0));
        while (!queue.isEmpty()) {
            Player player = queue.poll();
            int now = player.now;
            int step = player.step;

            for (LadderSnake ladderSnake : ladderSnakes) {
                if (now == ladderSnake.start) {
                    now = ladderSnake.end;
                }
            }
            visited[now] = true;

            if (now == 100) {
                answer = step;
                break;
            }

            for (int i = 1; i <= 6; i++) {
                if (now + i <= 100) {
                    if (!visited[now + i]) {
                        queue.add(new Player(now + i, step + 1));
                    }
                }
            }
        }
        System.out.println(answer);
    }
}
