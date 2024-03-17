package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 26215 눈 치우기 (Silver3)
 * 다른사람들은 어떻게 풀었는지 모르겟지만 나는 보자마자 우선순위 큐를 써서 풀어야 겠다고 생각했음
 * 눈을 치울 곳이 2곳이상 있으면 한번에 두곳을 같이 치우는게 무조건 이득임
 * 그 중에서 가장 눈이 많이 쌓인 두곳을 먼저 치우는게 가장 시간적으로 빠름
 * 그러기에 우선순위 큐를 사용함
 */
public class Solution162 {
    public static void main(String[] args) throws IOException {
        int answer = 0;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Queue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        int N = Integer.parseInt(bufferedReader.readLine());
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            queue.add(Integer.parseInt(tokenizer.nextToken()));
        }

        if (N == 1) {
            Integer poll = queue.poll();
            if (poll > 1440) {
                answer = -1;
            } else {
                answer = poll;
            }
        } else {
            while (queue.peek() != 0) {
                answer++;
                if (answer > 1440) {
                    answer = -1;
                    break;
                }
                Integer poll1 = queue.poll();
                Integer poll2 = queue.poll();
                poll1--;
                if (poll2 != 0) {
                    poll2--;
                }
                queue.add(poll1);
                queue.add(poll2);
            }
        }

        System.out.println(answer);
    }
}
