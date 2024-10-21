package Programmers3;

import java.util.Arrays;

/**
 * 정수 삼각형 Lv.3
 * 누적합을 이용한 다이나믹 프로그래밍이다.
 * 내가 생각하는 다이나믹 프로그래밍 중에서 그나마 제일 쉽다고 생각하는 유형
 * 그래도 많이 헷갈려서 오래걸림
 */
public class Solution38 {
    public int solution(int[][] triangle) {
        int N = triangle.length;
        int[][] temp = new int[N][0];
        for (int i = 0; i < N; i++) {
            temp[i] = new int[triangle[i].length];
        }
        temp[0][0] = triangle[0][0];
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < temp[i].length; j++) {
                temp[i + 1][j] = Math.max(temp[i + 1][j], temp[i][j] + triangle[i + 1][j]);
                temp[i + 1][j + 1] = Math.max(temp[i + 1][j + 1], temp[i][j] + triangle[i + 1][j + 1]);
            }
        }
        Arrays.sort(temp[N - 1]);
        return temp[N - 1][N - 1];
    }

    public static void main(String[] args) {
        Solution38 solution38 = new Solution38();
        System.out.println(solution38.solution(new int[][] {
                {7},
                {3, 8},
                {8, 1, 0},
                {2, 7, 4, 4},
                {4, 5, 2, 6, 5}}));
    }
}
