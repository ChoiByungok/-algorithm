package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 15903 카드 합체 놀이
 * 우선순위 큐를 사용해서 오름차순 정렬한 뒤
 * M번 반복하는 동안 제일 작은 수 두개를 꺼내서 더한 뒤 그 값을 두번 넣어주면 된다.
 * 여기서 주의 할 점은 반복이 끝나고 모든 요소의 합을 출력할 때 그 값이 int형 범위를 넘어설수도 있기때문에
 * long형으로 타입을 지정해 줘야 한다는 것이다.
 */
public class Solution65 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(nm[0]);
        int M = Integer.parseInt(nm[1]);
        long answer = 0;
        Queue<Long> queue = new PriorityQueue<>();
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        while (tokenizer.hasMoreTokens()) {
            queue.add(Long.parseLong(tokenizer.nextToken()));
        }
        while (M-- > 0) {
            long sum = queue.poll() + queue.poll();
            queue.add(sum);
            queue.add(sum);
        }

        while (!queue.isEmpty()) {
            answer += queue.poll();
        }

        System.out.println(answer);
    }
}
