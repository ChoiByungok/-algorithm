package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 13417 카드 문자열
 * 맨처음 입력된 문자는 덱에 넣고
 * 그 이후 들어오는 문자가 현재 덱의 맨 뒤에 알페벳보다 크면 그냥 덱의 맨 뒤에 두고
 * 작으면 덱의 맨 앞의 문자와 비교해 같거나 작으면 덱의 맨 앞에 두고 크면 덱의 맨 뒤에 두면 된다.
 */
public class Solution60 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bufferedReader.readLine());
        StringBuilder answer = new StringBuilder();
        Deque<String> deque = new ArrayDeque<>();
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(bufferedReader.readLine());
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            while (tokenizer.hasMoreTokens()) {
                String s = tokenizer.nextToken();
                if (deque.isEmpty()) {
                    deque.add(s);
                } else {
                    if (s.compareTo(deque.peekLast()) >= 0) {
                        deque.offerLast(s);
                    } else {
                        if (s.compareTo(deque.peekFirst()) <= 0) {
                            deque.offerFirst(s);
                        } else {
                            deque.offerLast(s);
                        }
                    }
                }
            }
            while (!deque.isEmpty()) {
                answer.append(deque.pollFirst());
            }
            answer.append("\n");
        }
        System.out.println(answer);
    }
}
