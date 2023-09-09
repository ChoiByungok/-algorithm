package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 11866 요세푸스 문제0
 */
public class Solution42 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder josephus = new StringBuilder();

        String[] NK = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(NK[0]);
        int K = Integer.parseInt(NK[1]);

        Queue<Integer> queue = IntStream.rangeClosed(1, N).boxed().collect(Collectors.toCollection(LinkedList::new));
        josephus.append("<");

        while (queue.size() > 1) {
            for (int i = 0; i < K - 1; i++) {
                queue.add(queue.poll());
            }
            josephus.append(queue.poll()).append(", ");
        }
        josephus.append(queue.poll()).append(">");
        System.out.println(josephus);
    }
}
