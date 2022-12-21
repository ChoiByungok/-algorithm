package Programmers;

import java.util.Arrays;

/**
 * 가까운 수
 */
public class Solution39 {
    public int solution(int[] array, int n) {
        int answer = 0;
        int close = 101;
        int[] closeArray = new int[array.length];
        Arrays.sort(array);
        for (int i = 0; i < array.length; i++) {
            closeArray[i] = Math.abs(array[i] - n);
            if(close > Math.abs(array[i] - n)){
                close = Math.abs(array[i] - n);
            }
        }
        for (int i = 0; i < closeArray.length; i++) {
            if(close == closeArray[i]){
                answer = array[i];
                break;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] array = {3, 10, 28};
        int n = 28;

        System.out.println(new Solution39().solution(array,n));
    }
}
