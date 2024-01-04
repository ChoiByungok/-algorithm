package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 19638 센티와 마법의 뿅망치
 * 우선순위 큐를 내림차순으로 구현한 후 사용한다.
 * 그러면 큐의 top 에는 거인국의 가장 큰 사람의 키가 나오게 되는데
 * top 의 값이 h 보다 작거나 1 이 되면 더이상 반복문을 돌 필요가 없다.
 * 왜냐하면 거인국에서 가장 큰 사람이 h 보다 작다는 것은 모든 거인들은 나보다 작다는 뜻이고
 * 또한 1보다는 작아질 수 없기 때문이다.
 * 그리하여 반복문을 전부 돈 뒤 큐의 top 값이 h보다 같거나 크면 NO와 거인국의 가장 큰 사람을 출력해주면 되고
 * 아니면 YES 와 몇번의 뿅망치를 사용했는지 출력해주면 된다.
 */
public class Solution88 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] nht = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(nht[0]);
        int h = Integer.parseInt(nht[1]);
        int t = Integer.parseInt(nht[2]);
        int count = 0;
        StringBuilder answer = new StringBuilder();
        Queue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);

        for (int i = 0; i < n; i++) {
            queue.add(Integer.parseInt(bufferedReader.readLine()));
        }

        for (int i = 0; i < t; i++) {
            if (queue.peek() < h || queue.peek() == 1) {
                break;
            }
            queue.add(queue.poll() / 2);
            count++;
        }

        if (queue.peek() >= h) {
            answer.append("NO").append("\n").append(queue.peek());
        } else {
            answer.append("YES").append("\n").append(count);
        }

        System.out.println(answer);
    }
}
