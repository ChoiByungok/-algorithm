package Programmers;
/**
 * 이진 변환 반복하기
 */

import java.util.Arrays;

public class Solution17 {
    public int[] solution(String s) {

        int[] answer = new int[2];
        while (!s.equals("1")){
            answer[0]++;
            int length = s.length();
            s = s.replace("0","");
            int length1 = s.length();
            answer[1] += (length - length1);
            s = Integer.toBinaryString(length1);
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution17 solution17 = new Solution17();
        String s = "110010101001";
        System.out.println(Arrays.toString(solution17.solution(s)));
    }
}
