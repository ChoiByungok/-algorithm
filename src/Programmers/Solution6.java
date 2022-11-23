package Programmers;
/**
 * 예산
 */

import java.util.Arrays;
public class Solution6 {
    public int solution(int[] d, int budget) {
        int answer = 0;
        long sum = Arrays.stream(d).sum();
        if(sum <= budget){
            answer = d.length;
            return answer;
        }
        Arrays.sort(d);
        for (int i = d.length-1; i > 0; i--) {
            sum = sum - d[i];
            if(sum <= budget){
                answer = i;
                break;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution6 solution6 = new Solution6();
        int[] arr1 = {1,3,2,5,4};
        int[] arr2 = {2,2,3,3};
        int budget1 = 9;
        int budget2 = 10;

        System.out.println(solution6.solution(arr1,budget1));
    }

}
