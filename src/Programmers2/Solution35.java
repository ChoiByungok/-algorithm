package Programmers2;

import java.util.HashSet;
import java.util.Set;

/**
 * 연속 부분 수열 합의 개수
 */
public class Solution35 {
    public int solution(int[] elements) {
        Set<Integer> answer = new HashSet<>();
        for (int i = 0; i < elements.length; i++) {
            int length = i + 1;
            for (int j = 0; j < elements.length; j++) {
                int sum = 0;
                for (int k = 0; k < length; k++) {
                    sum += elements[(j + k) % elements.length];
                }
                answer.add(sum);
            }
        }
        return answer.size();
    }

    public static void main(String[] args) {
        int[] elements = {7, 9, 1, 1, 4};
        System.out.println(new Solution35().solution(elements));
    }
}
