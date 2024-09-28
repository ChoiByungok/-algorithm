package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 27440 1로 만들기 3 (Gold4)
 * 정답률이 낮아서 살짝 쫄았는데 의외로 한번에 통과되었다.
 * 입력값이 10의 18승 이라 Long 형으로 받았고
 * 방문배열을 Long형으로 배열의 길이를 선언할수 없기에
 * Set 자료구조를 사용하여 방문처리를 하였다.
 * 그리고 연산은 그냥 하라는대로 구현하였다
 */
public class Solution57 {
    static class Node {
        long now;
        int step;

        public Node(long now, int step) {
            this.now = now;
            this.step = step;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(bufferedReader.readLine());
        System.out.println(bfs(N));
    }
    static int bfs(long N) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(N, 0));
        Set<Long> set = new HashSet<>();
        set.add(N);
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            long now = poll.now;
            int step = poll.step;
            if (now == 1) {
                return step;
            }

            if (now % 3 == 0) {
                if (!set.contains(now / 3)) {
                    set.add(now / 3);
                    queue.add(new Node(now / 3, step + 1));
                }
            }

            if (now % 2 == 0) {
                if (!set.contains(now / 2)) {
                    set.add(now / 2);
                    queue.add(new Node(now / 2, step + 1));
                }
            }

            if (!set.contains(now - 1)) {
                set.add(now - 1);
                queue.add(new Node(now - 1, step + 1));
            }
        }
        return - 1;
    }
}
