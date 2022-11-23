package Programmers;
/**
 * 최솟값 만들기
 */

import java.util.*;

public class Solution16 {
    public int solution(int []A, int []B)
    {
        int answer = 0;
        Integer[] b = new Integer[B.length];
        Arrays.sort(A);
        for (int i = 0; i < B.length; i++) {
            b[i] = B[i];
        }
        Arrays.sort(b,Collections.reverseOrder());

        for (int i = 0; i < A.length; i++) {
            answer += (A[i] * b[i]);
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution16 solution16 = new Solution16();
        int[] A = {1,4,2};
        int[] B = {5,4,4};
        System.out.println(solution16.solution(A,B));
    }
}
