package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 2696 중앙값 구하기 (Gold2)
 * 이 문제는 특이하게 입력값도 10자리가 넘어가면 개행이 된다.
 * 그러기에 따로 카운트 변수를 둬서 10이 넘어가면 입력을 새로 받아야 한다.
 * 입력뿐만 아니라 출력도 10자리가 넘어가면 개행을 해주어야 한다.
 * 문제 내용은 입력받은 값을 계속 정렬해주어 리스트의 크기가 홀수가 되었을때 중앙값을 뽑아내주면 된다.
 * 우선순위 큐를 이용하여 계속 정렬을 해주는것이 관건이라고 생각했지만 우선순위 큐는 중간의 요소를
 * 뺄 수 없다. 그래서 일단은 무식하게 리스트를 계속 정렬해가면서 중앙값을 추출하고 출력형식에 맞게
 * 출력을 해보았는데 시간 초과가 날 줄 알았으나 통과는되었다. 근데 우선순위 큐를 사용한 풀이보다 3배나 느린코드였다.
 */
public class Solution111 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int T = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < T; i++) {
            int M = Integer.parseInt(bufferedReader.readLine());
            List<Integer> sequence = new ArrayList<>();
            StringBuilder builder = new StringBuilder();
            int count = 0;
            if (M > 10) {
                int repeat = (M / 10) + 1;
                int index = 0;
                for (int j = 0; j < repeat; j++) {
                    StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
                    while (tokenizer.hasMoreTokens()) {
                        sequence.add(Integer.parseInt(tokenizer.nextToken()));
                        Collections.sort(sequence);
                        if (index % 2 == 0) {
                            int middle = sequence.size() / 2;
                            count++;
                            if (count % 10 != 0) {
                                builder.append(sequence.get(middle)).append(" ");
                            } else {
                                builder.append(sequence.get(middle)).append("\n");
                            }
                        }
                        index++;
                    }
                }
            } else {
                StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
                for (int j = 0; j < M; j++) {
                    sequence.add(Integer.parseInt(tokenizer.nextToken()));
                    Collections.sort(sequence);
                    if (j % 2 == 0) {
                        int middle = sequence.size() / 2;
                        builder.append(sequence.get(middle)).append(" ");
                        count++;
                    }
                }
            }
            answer.append(count).append("\n").append(builder).append("\n");
        }
        System.out.println(answer);
    }
}
