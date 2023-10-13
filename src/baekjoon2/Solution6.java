package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 1966 프린터 큐
 */
public class Solution6 {
    static class Print {
        int index;
        int priority;

        public Print(int index, int priority) {
            this.index = index;
            this.priority = priority;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Queue<Print> queue = new LinkedList<>();
        StringBuilder answer = new StringBuilder();
        int T = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < T; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            int n = Integer.parseInt(split[0]);
            int m = Integer.parseInt(split[1]);
            int count = 0;

            String[] priorities = bufferedReader.readLine().split(" ");
            for (int j = 0; j < priorities.length; j++) {
                queue.add(new Print(j, Integer.parseInt(priorities[j])));
            }

            while (!queue.isEmpty()) {
                int index = queue.peek().index;
                int priority = queue.peek().priority;
                boolean flag = false;

                int cnt = 0;
                for (Print print : queue) {
                    if (priority < print.priority) {
                        for (int j = 0; j < cnt; j++) {
                            queue.add(queue.poll());
                        }
                        flag = true;
                        break;
                    }
                    cnt++;
                }
                if (flag) {
                    continue;
                }
                queue.poll();
                count++;
                if (index == m) {
                    break;
                }
            }
            answer.append(count).append("\n");
            queue.clear();
        }

        System.out.println(answer);
    }
}
