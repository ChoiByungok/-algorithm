package Programmers;

import java.util.Arrays;

/**
 * H-Index
 */
public class Solution30 {
    public int solution(int[] citations) {
        int answer = 0;
        Arrays.sort(citations);
        for (int i = 0; i < citations.length; i++) {
            int h = citations.length - i;
            if(citations[i] >= h){
                answer = h;
                break;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] citations = {6, 5, 5, 5, 3, 2, 1, 0};
        System.out.println(new Solution30().solution(citations));
    }
}
