package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 5430 AC (Gold5)
 * 덱 자료구조의 특성을 잘 이용했으면 간단하게 풀었을 문제였을텐데
 * 나는 멍청하게 알고리즘 분류가 덱인걸 알고도
 * R 연산이 주어졌을때 무식하게 배열안의 요소를 뒤집었다. 사실상 덱을 쓴 이유가 없었던것
 * boolean형 변수를 하나 두어서 R연산이 나올때 그것을 반전시켜주고
 * boolean변수가 true 이면 요소를 앞에서 빼고 false면 요소를 뒤에서 빼면된다.
 * 덱 자료구조의 특성을 잘 이용했다면 배열을 뒤집을 필요가 없었던것
 * 그외에 어려웠던것은 입력받은 문자열 파싱과 다시 문자열을 조립하여 출력하는것이었다.
 */
public class Solution67 {
    public static void main(String[] args) throws IOException {
        StringBuilder answer = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < T; i++) {
            boolean error = false;
            boolean reverse = false;
            Deque<Integer> deque = new ArrayDeque<>();
            String command = bufferedReader.readLine();
            int n = Integer.parseInt(bufferedReader.readLine());
            String[] split = bufferedReader.readLine().split("\\D");
            for (int j = 1; j < split.length; j++) {
                deque.addLast(Integer.parseInt(split[j]));
            }

            for (int j = 0; j < command.length(); j++) {
                char c = command.charAt(j);
                if (c == 'R') {
                   reverse = !reverse;
                } else {
                    if (deque.size() == 0) {
                        error = true;
                        break;
                    }
                    if (reverse) {
                        deque.pollLast();
                    } else {
                        deque.pollFirst();
                    }
                }
            }

            if (error) {
                answer.append("error").append("\n");
            } else {
                answer.append("[");
                while (!deque.isEmpty()) {
                    if (reverse) {
                        if (deque.size() > 1) {
                            answer.append(deque.pollLast()).append(",");
                        } else {
                            answer.append(deque.pollLast());
                        }
                    } else {
                        if (deque.size() > 1) {
                            answer.append(deque.pollFirst()).append(",");
                        } else {
                            answer.append(deque.pollFirst());
                        }
                    }
                }
                answer.append("]").append("\n");
            }
        }
        System.out.println(answer);
    }
}
