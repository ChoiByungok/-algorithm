package Programmers;

import java.util.Arrays;

public class Solution18 {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int area = brown + yellow;
        for (int i = 3; i < area; i++) {
            int j = area / i;

            if (area % i == 0 && j >= 3) {
                int col = Math.max(i, j);
                int row = Math.min(i, j);
                int center = (col - 2) * (row - 2);

                if (center == yellow) {
                    answer[0] = col;
                    answer[1] = row;
                    return answer;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int brown = 10;
        int yellow = 2;
        Solution18 solution18 = new Solution18();
        System.out.println(Arrays.toString(solution18.solution(brown,yellow)));
    }
}
