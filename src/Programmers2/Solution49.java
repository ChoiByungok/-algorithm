package Programmers2;

import java.util.Arrays;
import java.util.Stack;

/**
 * 뒤에 있는 큰 수 찾기
 * 내가 푼거아님 남들이 푼거 보고 간신히 이해했지만 이런 유형으로 나오면 또 틀릴듯
 */
public class Solution49 {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (int i = 1; i < numbers.length; i++) {
            System.out.println("numbers[stack.peek()] = " + numbers[stack.peek()]);
            System.out.println("numbers[i] = " + numbers[i]);
            while (!stack.isEmpty() && numbers[stack.peek()] < numbers[i]) {
                answer[stack.pop()] = numbers[i];
            }
            stack.push(i);
            System.out.println("answer = " + Arrays.toString(answer));
            System.out.println("stack = " + stack);
        }
        while (!stack.isEmpty()) {
            answer[stack.pop()] = -1;
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] numbers1 = {2, 3, 3, 5};
        int[] numbers2 = {9, 1, 5, 3, 6, 2};
        System.out.println(Arrays.toString(new Solution49().solution(numbers2)));
    }
}
