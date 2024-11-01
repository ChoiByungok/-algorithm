package Programmers3;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 야근지수 Lv.3
 * n번 반복하여 모든 요소의 야근지수 값을 최소화 시켜야 한다.
 * 4 3 3 이라는 배열이 주어졌을 때 n값이 4라면
 * 0 3 3 으로 만든다면 야근지수가 18 이되지만 2 2 2로 만든다면 12가 된다.
 * 즉 배열안의 모든요소들의 값들을 최대한 균등하게 맞춰주어야 한다는 소리이다.
 * 나는 이 문제를 내림차순 우선순위 큐를 사용하여 해결하였다.
 * n번씩 반복하면서 큐 맨 앞에 나오는 가장큰 수 를 하나 줄이고 다시 큐에 넣는다
 * 그 후 큐에 있는 모든 요소들을 전부 제곱하면 되는것이다.
 */
public class Solution48 {
    public long solution(int n, int[] works) {
        long answer = 0;
        Queue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        for (int work : works) {
            queue.add(work);
        }
        while (n-- > 0) {
            Integer poll = queue.poll();
            if (poll == 0) {
                break;
            }
            queue.add(--poll);
        }
        while (!queue.isEmpty()) {
            answer += (long) Math.pow(queue.poll(), 2);
        }
        return answer;
    }
    public static void main(String[] args) {
        Solution48 solution48 = new Solution48();
        System.out.println(solution48.solution(4, new int[] {4, 3, 3}));
        System.out.println(solution48.solution(1, new int[] {2, 1, 2}));
        System.out.println(solution48.solution(3, new int[] {1, 1}));
    }
}
