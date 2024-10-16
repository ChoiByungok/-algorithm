package Programmers3;

import java.util.Stack;

/**
 * 택배상자 Lv.2
 * 자료구조를 사용한 구현문제
 * 문제설명을 읽어보니깐 스택을 사용해야 할거같아서 스택을 사용하여 풀음
 * 근데 조금 문제에 끼워맞추기위하여 뭔가 억지로 푼 느낌이 있음
 * 그래서 제출했을때 틀렸을것이라고 예상했지만 의외로 통과됨
 * 풀어도 찝집하다는게 이런느낌
 */
public class Solution33 {
    public int solution(int[] order) {
        int answer = 0;
        int N = order.length;
        int pointer = 0;
        Stack<Integer> belt = new Stack<>();
        for (int i = 1; i <= N; i++) {
            if (order[pointer] == i) {
                answer++;
                pointer++;
            } else {
                while (!belt.isEmpty() && belt.peek() == order[pointer]) {
                    belt.pop();
                    pointer++;
                    answer++;
                }
                belt.add(i);
            }
        }
        while (!belt.isEmpty() && belt.peek() == order[pointer]) {
            belt.pop();
            pointer++;
            answer++;
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution33 solution33 = new Solution33();
        System.out.println(solution33.solution(new int[] {4, 3, 1, 2, 5}));
        System.out.println(solution33.solution(new int[] {5, 4, 3, 2, 1}));
    }
}
