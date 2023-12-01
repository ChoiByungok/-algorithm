package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1021 회전하는 큐
 * 1부터 처음 입력받은 숫자까지 순차적으로 덱에 집어넣음
 * 그다음 뽑아내려는 원소랑 같은 값이 나올 때 까지 2번 3번 연산을 반복해야 하는데
 * 여기서 문제점은 입력받은 숫자와 지금 덱에서 맨 앞에 있는 숫자와 비교를 하여 2번 3번 연산을 해야 하는데
 * 어떤 기준으로 2번 3번 연산을 구분해야 할지 모르겠음
 * Deque 인터페이스 로 풀려니깐 입력받은 숫자가 덱의 몇번째 인덱스인지 알 방법이 없어서
 * LinkedList 로 풀었는데 항상 List 인터페이스로 선언하고 구현체를 LinkedList 로 인스턴스를 생성해 오다가
 * 이번에는 LinkedList 로 선언을 해보니깐 Deque 인터페이스와 같이 peekFirst() 등등 이러한 메소드가 있다라는 사실을 처음 알게 됨
 */
public class Solution55 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(nm[0]);
        int M = Integer.parseInt(nm[1]);
        int answer = 0;
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            list.add(i);
        }

        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        while (tokenizer.hasMoreTokens()) {
            int num = Integer.parseInt(tokenizer.nextToken());
            int mid = list.size() / 2;
            int index = list.indexOf(num);
            if (num == list.peekFirst()) {
                list.pollFirst();
            } else {
                if (index > mid) {
                    while (num != list.peekFirst()) {
                        list.offerFirst(list.pollLast());
                        answer++;
                    }
                } else {
                    while (num != list.peekFirst()) {
                        list.offerLast(list.pollFirst());
                        answer++;
                    }
                }
                list.pollFirst();
            }
        }
        System.out.println(answer);
    }
}
