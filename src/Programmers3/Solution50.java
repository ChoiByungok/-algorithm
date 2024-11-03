package Programmers3;

/**
 * [PCCE 기출문제] 9번 / 이웃한 칸 Lv.1
 * 문제를 잘못이해하여 bfs 탐색을 진행하였다 알고보니 상하좌우 인접한 칸만 한번씩 확인해보면 되는거였다.
 */
public class Solution50 {
    public int solution(String[][] board, int h, int w) {
        int answer = 0;
        int N = board.length;
        int M = board[0].length;
        int[][] dir = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for (int i = 0; i < 4; i++) {
            int nx = h + dir[i][0];
            int ny = w + dir[i][1];
            if (nx >= 0 && nx < N && ny >= 0 && ny < M && board[h][w].equals(board[nx][ny])) {
                answer++;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution50 solution50 = new Solution50();
        System.out.println(solution50.solution(
                new String[][] {{"blue", "red", "orange", "red"},
                        {"red", "red", "blue", "orange"},
                        {"blue", "orange", "red", "red"},
                        {"orange", "orange", "red", "blue"}},
                1, 1));

        System.out.println(solution50.solution(
                new String[][] {{"yellow", "green", "blue"},
                        {"blue", "green", "yellow"},
                        {"yellow", "blue", "blue"}},
                1, 1));
    }
}
