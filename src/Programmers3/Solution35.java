package Programmers3;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 타겟 넘버 Lv.2
 * 처음 문제를 보고는 dfs 백트래킹으로 풀어보려고 했는데
 * 재귀를 사용한 dfs 는 역시 아직 쉽지않음
 * 결국 익숙한 bfs 로 해결하였다.
 */
public class Solution35 {
    public int solution(int[] numbers, int target) {
        return bfs(numbers, target);
    }
    private static int bfs(int[] numbers, int target) {
        int answer = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {numbers[0], 1});
        queue.add(new int[] {-numbers[0], 1});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int sum = poll[0];
            int depth = poll[1];
            if (depth == numbers.length) {
                if (sum == target) {
                    answer++;
                }
                continue;
            }
            queue.add(new int[] {sum + numbers[depth], depth + 1});
            queue.add(new int[] {sum - numbers[depth], depth + 1});
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution35 solution35 = new Solution35();
        System.out.println(solution35.solution(new int[] {1, 1, 1, 1, 1}, 3));
        System.out.println(solution35.solution(new int[] {4, 1, 2, 1}, 4));
    }
}
