package Programmers3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * n^2 배열 자르기
 * 2차원 배열의 좌표가 n값을 left로 나눴을때 몫이 행의 좌표가 되고 나머지가 열의 좌표가 되는것을 이용했다.
 * 그 두 값중 큰 값이 배열의 값이 된다.
 */
public class Solution15 {
    public int[] solution(int n, long left, long right) {
/*        int[] answer = new int[(int)((right - left) + 1)];
        int [][] twoDArray = new int[n][n];
        int index = 0;
        int idx = 0;
        for (int i = 0; i < twoDArray.length; i++) {
            int k = i;
            for (int j = 0; j < twoDArray[i].length; j++) {
                if (k < j) {
                    k++;
                }
                twoDArray[i][j] = k + 1;
                if (index >= left && index <= right) {
                    answer[idx++] = twoDArray[i][j];
                }
                index++;
            }
            if (index == right) {
                break;
            }
        }
        System.out.println("twoDArray = " + Arrays.deepToString(twoDArray));
        return answer;*/
        List<Integer> answer = new ArrayList<>();
        for (long i = left; i <= right ; i++) {
            answer.add((int) (Math.max(i / n, i % n)) + 1);
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        int n1 = 3;
        long left1 = 2;
        long right1 = 5;

        int n2 = 4;
        long left2 = 7;
        long right2 = 14;

        System.out.println(Arrays.toString(new Solution15().solution(n2, left2, right2)));
    }
}
