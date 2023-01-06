package Programmers2;

import java.util.Arrays;
import java.util.List;

/**
 * 연속된 수의 합
 */
public class Solution11 {
    public int[] solution(int num, int total) {
        int[] answer = new int[num];
        if(num % 2 == 1){
            int value = total / num - num / 2;
            for (int i = 0; i < answer.length; i++) {
                answer[i] = value;
                value++;
            }
        }else {
            int value = (total / num) - ((num / 2) - 1);
            for (int i = 0; i < answer.length; i++) {
                answer[i] = value;
                value++;
            }

        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution11().solution(4,14)));
    }
}
