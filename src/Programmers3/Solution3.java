package Programmers3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 배열 만들기 2
 */
public class Solution3 {
    public int[] solution(int l, int r) {
        List<Integer> answer = new ArrayList<>();
        for(int i = l; i<=r; i++) {
            String str = i + "";
            int count = 0;
            for(int j = 0; j < str.length(); j++) {
                if(str.charAt(j) == 48 || str.charAt(j) == 53) {
                    count ++;
                }
            }
            if(count == str.length()) {
                answer.add(i);
            }
        }
        int[] empty = {-1};

        return answer.size() != 0 ? answer.stream().mapToInt(i -> i).toArray() : empty;
    }

    public static void main(String[] args) {
        int l1 = 5;
        int r1 = 555;

        int l2 = 10;
        int r2 = 20;
        System.out.println(Arrays.toString(new Solution3().solution(l1, r1)));
    }
}
