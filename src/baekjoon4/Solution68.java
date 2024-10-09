package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 23843 콘센트 (Gold5)
 * 충전하는데 각각 걸리는 시간이 다른 전자기기들과 여러개의 충전소가 있을때
 * 최소시간으로 모든 전자기기를 충전하는데 걸리는 시간을 출력해야한다.
 * 이것을 우선순위 큐를 이용하여 해결해야 하는데
 * 전자기기의 수만큼 큐에 0을 담는다. 큐는 오름차순 우선순위큐이다.
 * 그리고 전자기기의 충전시간을 내림차순으로 정렬한 뒤
 * 큐에서 요소를 하나빼고 내림차순된 전자기기의 시간을 더한뒤 다시 큐에 담아주는 행위를 반복한다.
 * 그러면 큐에서 가장 큰수가 충전하는데 걸리는 최소시간인것이다.
 */
public class Solution68 {
    static int[] consents;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        consents = new int[N];
        Queue<Integer> queue = new PriorityQueue<>();
        tokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < M; i++) {
            queue.add(0);
        }
        for (int i = 0; i < N; i++) {
            consents[i] = Integer.parseInt(tokenizer.nextToken());
        }
        Arrays.sort(consents);
        for (int i = N - 1; i >= 0 ; i--) {
            queue.add(queue.poll() + consents[i]);
        }

        while (queue.size() > 1) {
            queue.poll();
        }
        System.out.println(queue.poll());
    }

}
