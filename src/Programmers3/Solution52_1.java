package Programmers3;

/**
 * 큰 수 만들기(정답풀이) Lv.2
 * 챗지피티의 풀이...
 * 접근 방법: 그리디 알고리즘
 * 큰 자릿수를 우선으로 선택하면서 수를 만들어 나갑니다.
 * 스택을 활용하여 작은 수를 제거하고, 큰 수가 앞쪽에 오도록 배열합니다.
 * 최종적으로 스택에 남은 수가 가장 큰 수를 형성하게 됩니다.
 */
public class Solution52_1 {
    public String solution(String number, int k) {
        StringBuilder stack = new StringBuilder();

        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);

            while (k > 0 && stack.length() > 0 && stack.charAt(stack.length() - 1) < c) {
                stack.deleteCharAt(stack.length() - 1);
                k--;
            }
            stack.append(c);
            System.out.println("stack = " + stack);
        }

        // 필요한 만큼 뒷자리 제거
        return stack.substring(0, stack.length() - k);
    }

    public static void main(String[] args) {
        Solution52_1 solution52_1 = new Solution52_1();
        System.out.println(solution52_1.solution("1924", 2));
        System.out.println(solution52_1.solution("1231234", 3));
        System.out.println(solution52_1.solution("4177252841", 4));
    }
}
