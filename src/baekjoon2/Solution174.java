package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1213 팰린드롬 만들기 (Silver3)
 * 실버3 난이도라고 하는데 체감상 골드보다 훨씬어려웠던 문제
 * 어떻게 조건을 세워야 할지 고민하다가 해결못하고 결국 구글링함
 * 방법만 대충보고 내방식대로 로직을 짜봄
 * 1.우선 알파벳별 갯수를 카운트를 하여 맵에 저장함
 * 2.키값으로 정렬함
 * 3.카운트가 2이상이면 스택에하나 넣고 큐에 하나 넣는다. 그리고 카운트를 2줄인다.
 * 4.카운트가 2이상인 요소가 없을때까지 반복한다.
 * 5.큐와 스택에서 차례대로 문자를 꺼내 문자열을 조립한뒤 뒤집어서 비교한다.
 * 예를 들어 AABBCCD 라는 문자열이 있으면
 * 맵에 카운트 저장하고 정렬하면 {A=2, B=2 C=2, D=1} 가 된다.
 * 리스트의 앞에서부터 카운트가 2이상인 요소들을 먼저 큐와 스택에 넣는다. 넣고 나서는 내부 반복문을 탈출시켜준다.
 * queue[A] stack[A] A=0, B=2, C=2, D=1
 * queue[A, B] stack[A, B] A=0, B=0, C=2, D=1
 * queue[A, B, C] stack[A, B, C] A=0, B=0, C=0, D=1
 * 까지 반복을 하면 이제 카운트가 2이상인 요소가 없기에 반복문을 탈출한다.
 * D=1 인 알파벳을 큐에 넣으면
 * 최종적으로 queue[A, B, C, D] stack[A, B, C] 이렇게 들어가게 된다.
 * 그리고 이걸 꺼내서 문자열로 조립하면 ABCDCBA가 되는것이다.
 * 그리고 이 문자열을 뒤집어서도 같은지 같지 않은지 비교하여 문제를 풀면된다.
 */
public class Solution174 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String readLine = bufferedReader.readLine();
        Map<Character, Integer> map = new HashMap<>();
        StringBuilder answer = new StringBuilder();
        boolean possible = true;

        for (int i = 0; i < readLine.length(); i++) {
            char c = readLine.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByKey());
        Queue<Character> queue = new LinkedList<>();
        Stack<Character> stack = new Stack<>();

        while (check(list)) {
            for (Map.Entry<Character, Integer> entry : list) {
                if (entry.getValue() >= 2) {
                    map.put(entry.getKey(), map.get(entry.getKey()) - 2);
                    queue.offer(entry.getKey());
                    stack.push(entry.getKey());
                    break;
                }
            }
        }
        for (Map.Entry<Character, Integer> entry : list) {
            if (entry.getValue() == 1) {
                queue.add(entry.getKey());
            }
        }

        while (!queue.isEmpty()) {
            answer.append(queue.poll());
        }
        while (!stack.isEmpty()) {
            answer.append(stack.pop());
        }
        StringBuilder original = new StringBuilder(answer);
        StringBuilder reverse = new StringBuilder(answer.reverse());
        if (!original.toString().equals(reverse.toString())) {
            possible = false;
        }

        System.out.println(possible ? answer : "I'm Sorry Hansoo");
    }

    static boolean check(List<Map.Entry<Character, Integer>> list) {
        for (Map.Entry<Character, Integer> entry : list) {
            if (entry.getValue() >= 2) {
                return true;
            }
        }

        return false;
    }
}
