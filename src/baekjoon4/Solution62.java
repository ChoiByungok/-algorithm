package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1655 가운데를 말해요 (Gold2)
 * 리스트에 값을 넣고 정렬한 뒤 리스트 사이즈가 짝수면 중간에 있는 값중 작은것을 출력하였고
 * 홀수면 가운데 값을 출력하였다. 역시나 바로 시간초과가 발생하였다.
 * 해당 문제는 우선순위 큐를 이용하여 해결해야 한다.
 * 하지만 큐는 리스트의 맨 뒤에서 입력이 이루어지고 맨 앞에서 출력이 이루어지는 자료구조라 중간부분을 출력할 수 없다. 어떻게 해야할까
 * 결국 다른사람의 풀이를 보고 제출하였다.
 * 오름차순으로 정렬되는 우선순위 큐랑 내림차순으로 정렬되는 우선순위 큐 2개를 이용하여 해결해야 한다.
 * 1. 두 큐의 사이즈가 같으면 내림차순 큐에 입력값을 넣는다.
 * 2. 다르면 오름차순 큐에 입력값을 넣는다.
 * 3. 두 큐가 비어있지 않았는데 내림차순큐의 top 값이 오름차순 큐의 top 값보다 크면 서로 top 의 요소를 바꿔준다.
 * 4. 모든 작업을 끝냈을 때 내림차순 큐의 top 값이 중간값이 되게 된다.
 * 큐의 성질도 알고있고 우선순위큐도 잘 알고 있다고 생각했는데 우선순위 큐 2개를 이용하여 이렇게 문제를 해결해야겠다는
 * 응용력이 부족하여 이 문제를 스스로 해결하지 못했다. 어떻게하면 응용력을 늘릴수있을까 참 어렵다.
 */
public class Solution62 {
    public static void main(String[] args) throws IOException {
        StringBuilder answer = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        Queue<Integer> Min = new PriorityQueue<>();
        Queue<Integer> Max = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(bufferedReader.readLine());

            if (Max.size() == Min.size()) {
                Max.add(num);
            } else {
                Min.add(num);
            }

            if (!Max.isEmpty() && !Min.isEmpty()) {
                if (Max.peek() > Min.peek()) {
                    Integer max = Max.poll();
                    Integer min = Min.poll();
                    Max.add(min);
                    Min.add(max);
                }
            }
            answer.append(Max.peek()).append("\n");
        }
        System.out.println(answer);
    }
}
