package Programmers3;

import java.util.Arrays;

/**
 * 정사각형으로 만들기
 */
public class Solution6 {
    public int[][] solution(int[][] arr) {
        int row = arr.length;
        int column = arr[0].length;
        if (row > column) {
            int[][] answer = new int[row][row];
            for (int i = 0; i < arr.length; i++) {
                System.arraycopy(arr[i], 0, answer[i], 0, arr[i].length);
            }
            return answer;
        } else if (row == column) {
            return arr;
        } else {
            int[][] answer = new int[column][column];
            for (int i = 0; i < arr.length; i++) {
                System.arraycopy(arr[i], 0, answer[i], 0, arr[i].length);
            }
            return answer;
        }
    }

    public static void main(String[] args) {
        int[][] arr1 = {{572, 22, 37}, {287, 726, 384}, {85, 137, 292}, {487, 13, 876}};
        int[][] arr2 = {{57, 192, 534, 2}, {9, 345, 192, 999}};
        int[][] arr3 = {{1, 2}, {3, 4}};

        System.out.println(Arrays.deepToString(new Solution6().solution(arr2)));
    }
}
