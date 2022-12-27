package Programmers2;

import java.util.Arrays;

/**
 * 캐릭터의 좌표
 */
public class Solution2 {
    public int[] solution(String[] keyinput, int[] board) {
        int[] answer = new int[2];
        for (String s : keyinput) {
            switch (s) {
                case "right":
                    if (answer[0] == (board[0] / 2)) {
                        continue;
                    }
                    answer[0]++;
                    break;
                case "left":
                    if (answer[0] == -(board[0] / 2)) {
                        continue;
                    }
                    answer[0]--;
                    break;
                case "up":
                    if (answer[1] == (board[1] / 2)) {
                        continue;
                    }
                    answer[1]++;
                    break;
                default:
                    if (answer[1] == -(board[1] / 2)) {
                        continue;
                    }
                    answer[1]--;
                    break;
            }

        }
        return answer;
    }

    public static void main(String[] args) {
        String[] keyinput = {"left", "right", "up", "right", "right"};
        int[] board = {7, 9};
        System.out.println(Arrays.toString(new Solution2().solution(keyinput,board)));
    }
}
