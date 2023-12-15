package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 24511 queuestack
 * 예문을 읽고 이해를 못해서 질문게시판의 도움을 얻음
 * 처음에는 단순하게 클래스를 하나 만들어서 해당 자료구조가 큐면 들어온 숫자랑 기존의 숫자랑 바꿔치기 해서
 * 다음 요소로 넘겨주고 스택이면 그냥 숫자를 넘겨주고 그것을 반복해서 마지막에 나온숫자가 pop된 요소이므로
 * 그렇게 문제를 제출했었는데 시간초과가 나왔다. 지문을 다시 읽어보니 자료구조의 갯수도 10만개이고 들어올 숫자도 10만개이므로
 * 최악의 경우 100억번 연산을 하게 된다.
 * 그래서 해당 문제를 다시 되짚어 보니
 * 자료구조가 스택형태일때는 변하는게 없으니 스킵하고 큐일때만 변하는 모습을 볼 수 있다.
 * 다시보니 deque형태의 구조를 띄는것이었다. 그래서 deque로 푸니 풀렸다.
 */
public class Solution69 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        StringBuilder answer = new StringBuilder();
        Deque<Integer> deque = new ArrayDeque<>();
        StringTokenizer tokenizer1 = new StringTokenizer(bufferedReader.readLine());
        StringTokenizer tokenizer2 = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            if (tokenizer1.nextToken().equals("0")) {
                deque.add(Integer.parseInt(tokenizer2.nextToken()));
            } else {
                tokenizer2.nextToken();
            }
        }

        int M = Integer.parseInt(bufferedReader.readLine());
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(tokenizer.nextToken());
            deque.offerFirst(num);
            answer.append(deque.pollLast()).append(" ");
        }
        System.out.println(answer);
    }
}
