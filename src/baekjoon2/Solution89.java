package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 23757 아이들과 선물 상자
 * 내림차순 우선순의 큐를 사용하여 선물상자의 value 를 저장한다.
 * 아이들을 순서대로 입력 받은 뒤 선물상자의 가장 큰 값과 비교하여 그 값이 같거나 작으면 로직을 수행하고
 * 아니면 반복문을 종료시킨다.
 */
public class Solution89 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(nm[0]);
        int M = Integer.parseInt(nm[1]);
        boolean satisfaction = true;
        Queue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());

        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            queue.offer(Integer.parseInt(tokenizer.nextToken()));
        }

        tokenizer = new StringTokenizer(bufferedReader.readLine());

        for (int i = 0; i < M; i++) {
            int children = Integer.parseInt(tokenizer.nextToken());
            if (queue.peek() < children) {
                satisfaction = false;
                break;
            }
            queue.offer(queue.poll() - children);
        }

        System.out.println(satisfaction ? 1 : 0);
    }
}
