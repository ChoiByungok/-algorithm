package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 1417 국회의원 선거 (Silver5)
 * 1번 후보가 과반수를 획득하기 위해 최소 몇명을 매수해야 하는지 구하는 문제
 * 우선순위큐를 내림차순으로 만들고 맨 앞에 요소를 빼서 비교한 뒤
 * 빼낸 요소가 1번후보와 같거나 크면 1번후보를 1증가 시키고 요소를 1감소시킨 뒤 다시 큐에 넣는다.
 * 그렇게 반복하다가 1번후보가 맨앞에 요소보다 커지는 순간 반복문을 종료하면 된다.
 */
public class Solution135 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Queue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        int N = Integer.parseInt(bufferedReader.readLine());
        int daSom = Integer.parseInt(bufferedReader.readLine());
        int answer = 0;
        for (int i = 0; i < N - 1; i++) {
            queue.add(Integer.parseInt(bufferedReader.readLine()));
        }

        while (true) {
            if (queue.isEmpty()) {
                break;
            }
            Integer poll = queue.poll();
            if (daSom > poll) {
                break;
            } else {
                daSom++;
                poll--;
                queue.add(poll);
            }
            answer++;
        }
        System.out.println(answer);
    }
}
