package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 13975 파일 합치기3
 * 우선수위 큐를 사용하여 오름차순으로 정렬한 뒤
 * 앞에서 두개의 요소를 제거 한 후 두 값을 더해서 total 이라는 변수에 누적으로 더해주고
 * 다시 큐에 넣어준다 그 행위를 반복하다보면 큐의 사이즈가 1이되는 순간이 있는데
 * 그때 반복문을 탈출시켜준뒤 total 값을 출력시켜주면 되는문제인데
 * 여기서 주의 해야할 것은 역시 자료형을 long 형으로 지정해주어야 한다는 것이다.
 * 요소의 크기는 10000이지만 파일의 갯수가 1000000개를 넘어가기 때문이다.
 */
public class Solution102 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bufferedReader.readLine());
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < T; i++) {
            int K = Integer.parseInt(bufferedReader.readLine());
            Queue<Long> queue = new PriorityQueue<>();
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            long total = 0;
            while (tokenizer.hasMoreTokens()) {
                long parseLong = Long.parseLong(tokenizer.nextToken());
                queue.add(parseLong);
            }
            while (queue.size() != 1) {
                Long c1 = queue.poll();
                Long c2 = queue.poll();

                Long x1 = c1 + c2;
                total += x1;
                queue.add(x1);
            }

            answer.append(total).append("\n");
        }
        System.out.println(answer);
    }
}
