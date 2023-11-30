package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 2075 N번쨰 큰 수
 * 우선순위 큐를 큰 숫자가 앞으로 가게 즉 내림차순으로 정렬 될 수 있도록 함수형 인터페이스를 오버라이딩 함
 * 그리고 입력받은 수 만큼 반복문을 돌며 앞에 숫자를 제거해 나가면서 풀면 됨
 */
public class Solution54 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        Queue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            while (tokenizer.hasMoreTokens()) {
                queue.offer(Integer.parseInt(tokenizer.nextToken()));
            }
        }

        for (int i = 0; i < N - 1; i++) {
            queue.poll();
        }
        System.out.println(queue.poll());
    }
}
