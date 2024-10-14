package Programmers3;

/**
 * 피로도 Lv.2
 * 예전에 백준 알고리즘을 풀기 전에 도전해봤다가 어떻게 접근해야 할지 몰라서 포기했던 문제
 * 백준에서 폐관수련을 하고나서는 이 문제는 백트래킹을 이용한 완전탐색 문제라는 것을 알았고
 * 조금 고민은 했지만 바로 풀수있었다.
 */
public class Solution31 {
    static boolean[] visited;
    static int MAX = Integer.MIN_VALUE;
    static int number;
    public int solution(int k, int[][] dungeons) {
        number = dungeons.length;
        visited = new boolean[number];
        for (int i = 0; i < number; i++) {
            visited[i] = true;
            int min = dungeons[i][0];
            int fatigue = dungeons[i][1];
            if (k >= min) {
                backTracking(k - fatigue, dungeons, 1);
            }
            visited[i] = false;
        }
        return MAX;
    }

    static void backTracking(int k, int[][] dungeons, int step) {
        for (int i = 0; i < number; i++) {
            if (!visited[i]) {
                int min = dungeons[i][0];
                int fatigue = dungeons[i][1];
                if (k >= min) {
                    visited[i] = true;
                    backTracking(k - fatigue, dungeons, step + 1);
                    visited[i] = false;
                }
            }
        }
        MAX = Math.max(MAX, step);
    }

    public static void main(String[] args) {
        Solution31 solution31 = new Solution31();
        System.out.println(solution31.solution(80, new int[][] {{80, 20}, {50, 40}, {30, 10}}));
    }
}
