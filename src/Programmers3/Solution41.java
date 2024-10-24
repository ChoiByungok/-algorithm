package Programmers3;

import java.util.Arrays;

/**
 * 행렬의 곱셈 Lv.2
 * 우선 행렬은 교환 법칙이 성립되지 않기에 AB != BA 이다.
 * 그래서 어떤 행렬이 앞에 나가느냐에 따라 정답이 달라진다.
 * 2X3 행렬과 3X2 행렬을 곱하면 2X2행렬이 만들어지지만
 * 3X2 행렬과 2X3 행렬을 곱하면 3X3행렬이 만들어진다.
 * 그리고 두 행렬이 곱해지기 위해선 앞 행렬의 열의 길이와 뒤 행렬의 행의 길이가 같아야한다.
 * 이 법칙을 준수하여 구현해보자.
 * 막상 구현을 해보라고 그러면 굉장히 헷갈린다.
 */
public class Solution41 {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int N = arr1.length;
        int M = arr2[0].length;
        int[][] answer = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < arr1[0].length; k++) {
                    answer[i][j] += arr1[i][k] * arr2[k][j];
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution41 solution41 = new Solution41();
        System.out.println(Arrays.deepToString(solution41.solution(
                new int[][]{{1, 4}, {3, 2}, {4, 1}},
                new int[][]{{3, 3}, {3, 3}})));

        System.out.println(Arrays.deepToString(solution41.solution(
                new int[][]{{3, 3}, {3, 3}},
                new int[][]{{1, 4}, {3, 2}, {4, 1}})));


        System.out.println(Arrays.deepToString(solution41.solution(
                new int[][]{{2, 3, 2}, {4, 2, 4}, {3, 1, 4}},
                new int[][]{{5, 4, 3}, {2, 4, 1}, {3, 1, 1}})));
    }
}
