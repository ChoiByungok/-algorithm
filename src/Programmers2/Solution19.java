package Programmers2;
/**
 * 가장 가까운 같은 글자
 */

import java.util.Arrays;

public class Solution19 {

    public int[] solution(String s) {
        int[] answer = new int[s.length()];
        Arrays.fill(answer, -1);
        if (answer.length == 1) {
            return answer;
        }
        for (int i = 1; i < s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if(s.charAt(i) == s.charAt(j)) {
                    answer[i] = i - j;
                }
            }
        }


        return answer;
    }

    public static void main(String[] args) {
        String s = "foobar";
        System.out.println(Arrays.toString(new Solution19().solution(s)));
    }
}
