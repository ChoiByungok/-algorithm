package Programmers;

import java.util.Arrays;
/**
 * 배열 회전시키기
 */
public class Solution34 {
    public int[] solution(int[] numbers, String direction) {
        int[] answer = new int[numbers.length];
        if(direction.equals("right")){
            answer[0] = numbers[numbers.length-1];
            System.arraycopy(numbers, 0, answer, 1, numbers.length - 1);
        }else {
            answer[answer.length - 1] = numbers[0];
            System.arraycopy(numbers, 1, answer, 0, numbers.length - 1);
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] numbers = {4, 455, 6, 4, -1, 45, 6};
        System.out.println(Arrays.toString(new Solution34().solution(numbers, "left")));
        String s = "Bcad";

    }
}
