package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 5397 키로거
 * 리스트로 풀려다가 인덱스 범위 벗어나는 에러 나옴
 * 그래서 검색하니까 스택 2개를 놓고 '<' 나올때 스택1과 '>' 나올때 스택2을 따로두고 푸는 방법이 있었음
 * 제출 하고 보니까 자바의 list.listIterator() 로 푸는 방법도 있었음 새로운걸 하나 알게 됨
 */
public class Solution43 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bufferedReader.readLine());
        StringBuilder answer = new StringBuilder();
 /*       for (int i = 0; i < T; i++) {
            String readLine = bufferedReader.readLine();
            Stack<Character> left = new Stack<>();
            Stack<Character> right = new Stack<>();
            for (int j = 0; j < readLine.length(); j++) {
                char c = readLine.charAt(j);
                if (c == '<') {
                    if (!left.isEmpty()) {
                        right.push(left.pop());
                    }
                } else if (c == '>') {
                    if (!right.isEmpty()) {
                        left.push(right.pop());
                    }
                } else if (c == '-') {
                    if (!left.isEmpty()) {
                        left.pop();
                    }
                } else {
                    left.push(c);
                }
            }
            while (!left.isEmpty()) {
                right.push(left.pop());
            }
            while (!right.isEmpty()) {
                answer.append(right.pop());
            }
            answer.append("\n");
        }*/
        for (int i = 0; i < T; i++) {
            String readLine = bufferedReader.readLine();
            List<Character> list = new LinkedList<>();
            ListIterator<Character> iterator = list.listIterator();
            for (int j = 0; j < readLine.length(); j++) {
                char c = readLine.charAt(j);

                if (c == '<') {
                    if (iterator.hasPrevious()) {
                        iterator.previous();
                    }
                } else if (c == '>') {
                    if (iterator.hasNext()) {
                        iterator.next();
                    }
                } else if (c == '-') {
                    if (iterator.hasPrevious()) {
                        iterator.previous();
                        iterator.remove();
                    }
                } else {
                    iterator.add(c);
                }
            }
            for (Character character : list) {
                answer.append(character);
            }
            answer.append("\n");
        }
        System.out.println(answer);
    }
}
