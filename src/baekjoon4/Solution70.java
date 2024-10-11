package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 10866 덱 (Silver4)
 */
public class Solution70 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        Deque<Integer> deque = new ArrayDeque<>();
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            String command = tokenizer.nextToken();
            switch (command) {
                case "push_front" :
                    deque.offerFirst(Integer.parseInt(tokenizer.nextToken()));
                    break;
                case "push_back" :
                    deque.offerLast(Integer.parseInt(tokenizer.nextToken()));
                    break;
                case "pop_front" :
                    if (deque.isEmpty()) {
                        answer.append(-1).append("\n");
                    } else {
                        answer.append(deque.pollFirst()).append("\n");
                    }
                    break;
                case "pop_back" :
                    if (deque.isEmpty()) {
                        answer.append(-1).append("\n");
                    } else {
                        answer.append(deque.pollLast()).append("\n");
                    }
                    break;
                case "size" :
                    answer.append(deque.size()).append("\n");
                    break;
                case "empty" :
                    answer.append(deque.isEmpty() ? 1 : 0).append("\n");
                    break;
                case "front" :
                    if (deque.isEmpty()) {
                        answer.append(-1).append("\n");
                    } else {
                        answer.append(deque.peekFirst()).append("\n");
                    }
                    break;
                case "back" :
                    if (deque.isEmpty()) {
                        answer.append(-1).append("\n");
                    } else {
                        answer.append(deque.peekLast()).append("\n");
                    }
                    break;
            }
        }
        System.out.println(answer);
    }
}
