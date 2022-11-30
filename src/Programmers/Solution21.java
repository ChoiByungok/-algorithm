package Programmers;

import java.util.*;

/**
 * 모의고사
 */
public class Solution21 {
    public int[] solution(int[] answers) {
        int[][] pattern = {{1, 2, 3, 4, 5},
                           {2, 1, 2, 3, 2, 4, 2, 5},
                           {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}};

        int[] answer = new int[3];
        int max = -1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < answers.length; j++) {
                if(pattern[i][j % pattern[i].length] == answers[j]){
                    answer[i]++;
                }
            }
        }

        for (int j : answer) {
            if (j > max) {
                max = j;
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < answer.length; i++) {
            if(answer[i] == max){
                list.add(i+1);
            }
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        int[] answer = {1,2,3,4,5};
        System.out.println(Arrays.toString(new Solution21().solution(answer)));
    }
}
