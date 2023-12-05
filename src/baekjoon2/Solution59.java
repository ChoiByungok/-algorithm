package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 18115 카드놓기
 * 1 2 3 번의 연산이 있고 해당 연산을 끝냈을 때 카드의 순서가 1 2 3 ... N 으로 되어 있다고 한다
 * 1번연산 : 제일 위의 카드 1장을 바닥에 내려놓는다.
 * 2번연산 : 위에서 두번째 카드를 바닥에 내려놓는다.
 * 3번연산 : 제일 아래 있는 카드를 바닥에 내려놓는다.
 * 연산을 하기 전에 카드의 순서를 출력하는 문제인데 역으로 추적해야 하는건지 생각보다 감이 잡히지 않는다.
 * 2번 예제 정답 1 5 2 3 4 에서 2 3 3 2 1 의 연산을 하면 1 2 3 4 5가 되니깐
 * 1은 1번연산 2는 2번연산 3은 3번연산 4는 3번연산 5는 2번연산을 했다고 생각하면 된다.
 * 연산도 주어진 연산 그대로 하면 안되고 역으로 생각해야 하는데
 * 나는 이렇게 생각하며 풀었다.
 * 뒤집은 1번연산 : 제일 아래카드를 제일 뒤로
 * 뒤집은 2번연산 : 제일 아래카드를 뒤에서 2번째로
 * 뒤집은 3번연산 : 제일 아래카드를 제일 앞으로
 * 해당 연산을 진행하니깐 2번예제 기준 덱에 4 3 2 5 1 로 들어가 있었고
 * 덱 뒤에서 부터 뽑아내며 출력을 하였다.
 */
public class Solution59 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        StringBuilder answer = new StringBuilder();
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        ArrayDeque<Integer> answerDeque = new ArrayDeque<>();
        Stack<String> stack = new Stack<>();
        for (int i = 1; i <= N; i++) {
            deque.add(i);
        }
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            stack.push(tokenizer.nextToken());
        }
        while (!stack.isEmpty()) {
            String A = stack.pop();
            switch (A) {
                case "1" :
                    answerDeque.offerLast(deque.pollFirst());
                    break;
                case "2" :

                    Integer integer1 = answerDeque.pollLast();
                    answerDeque.offerLast(deque.pollFirst());
                    answerDeque.offerLast(integer1);
                    break;
                case "3" :
                    answerDeque.offerFirst(deque.pollFirst());
                    break;
            }
        }
        while (!answerDeque.isEmpty()) {
            answer.append(answerDeque.pollLast()).append(" ");
        }

        System.out.println(answer);
    }
}
