package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 14235 크리스마스 선물
 * 차례대로 거점지가 입력 되는데 0 이면 아이를 만난거고 그 외의 숫자는 거점지다
 * 거점지에서는 선물을 충전하는데 다음에 아이를 만날때 산타가 가지고 있는 선물중 가장 가치가 높은 선물을 줘야 하기에
 * 우선순위 큐를 내림차순으로 구현해놓는다.
 */
public class Solution61 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        StringBuilder answer = new StringBuilder();
        Queue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < N; i++) {
            String readLine = bufferedReader.readLine();
            if (readLine.equals("0")) {
                if (queue.isEmpty()) {
                    answer.append(-1).append("\n");
                } else {
                    answer.append(queue.poll()).append("\n");
                }
            } else {
                StringTokenizer tokenizer = new StringTokenizer(readLine);
                int a = Integer.parseInt(tokenizer.nextToken());
                for (int j = 0; j < a; j++) {
                    queue.add(Integer.parseInt(tokenizer.nextToken()));
                }
            }
        }
        System.out.println(answer);
    }
}
