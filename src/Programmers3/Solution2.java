package Programmers3;

import java.util.Arrays;

/**
 * 로또의 최고 순위와 최저 순위
 * 1	6개 번호가 모두 일치
 * 2	5개 번호가 일치
 * 3	4개 번호가 일치
 * 4	3개 번호가 일치
 * 5	2개 번호가 일치
 * 6(낙첨)	그 외
 */
public class Solution2 {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        for (int lotto : lottos) {
            if (lotto == 0) {
                answer[0]++;
                continue;
            }
            for (int win_num : win_nums) {
                if (lotto == win_num) {
                    answer[1]++;
                    break;
                }
            }
        }
        answer[0] += answer[1];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = 7 - answer[i];
            if (answer[i] == 7) {
                answer[i] = 6;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] lottos1 = {44, 1, 0, 0, 31, 25};
        int[] win_nums1 = {31, 10, 45, 1, 6, 19};

        int[] lottos2 = {0, 0, 0, 0, 0, 0};
        int[] win_nums2 = {38, 19, 20, 40, 15, 25};

        int[] lottos3 = {45, 4, 35, 20, 3, 9};
        int[] win_nums3 = {20, 9, 3, 45, 4, 35};

        System.out.println(Arrays.toString(new Solution2().solution(lottos2, win_nums2)));
    }
}
