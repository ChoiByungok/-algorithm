package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 1715 카드 정렬하기
 * 1. while문을 사용하여 더한 값을 계속해서 우선순위 큐에 넣어준다.
 * 2. 큐에 1 갯값만 남으면 더 이상 더할 개수가 없으므로 연산을 끝낸다.
 *
 * 예를들어
 * 4 30 40 50 60 이 들어왔을 떄
 * 처음에 30과 40이 더해진 수 70이 다시 우선순위 큐에 들어가서 50 60 70 이 된 후 50 과 60을 더해서 다시 큐에 넣는걸 반복하는 문제인데
 * 나는 70이란 숫자를 뒤에 숫자 50과 비교하며 풀었기에 틀렸었다.
 * 예제가 2개정도만 더 있었더라면 이해 할 수 있었을텐데 나의 문제 이해능력이 아쉬운 문제였다.
 */
public class Solution47 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        Queue<Long> queue = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            queue.offer(Long.parseLong(bufferedReader.readLine()));
        }

        long answer = 0;
        while (queue.size() > 1) {
            Long num1 = queue.poll();
            Long num2 = queue.poll();
            long sum = num1 + num2;
            answer += sum;
            queue.offer(sum);
        }

        System.out.println(answer);
    }
}
