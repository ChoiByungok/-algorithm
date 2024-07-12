package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 15558 점프게임 (Gold5)
 * 행의 길이가 2로 고정된 2차원 배열을 만들어서 해결함
 * 매초마다 첫번째 칸부터 무너진다고 했으니
 * 2중반복문을 사용함 그래서 바깥쪽 반복문은 주어진 N만큼 반복시킴
 * 그리고 안쪽 반복문은 bfs반복문인데 여기서 주어진 이동조건을 고려해서
 * 해당 위치에 갈 수 있으면 방문처리하고 큐에 담아둠
 * 이때 주의해야할 점은 상근이가 이동한 위치가 다음에 무너진 위치면 continue 시켜주면 됨
 * 그렇게 반복문 중간에 주어진 N이상 넘어갔다 그러면 true를 반환시켜주고
 * 모든 반복문을 다 돌았는데 N을 못넘어간거면 탈출하지 못한것이므로 false를 반환시켜주면 됨
 */
public class Solution79 {
    static class SangGeun {
        int x;
        int y;

        public SangGeun(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N;
    static int K;
    static boolean[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] split = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        K = Integer.parseInt(split[1]);
        map = new boolean[2][N];

        for (int i = 0; i < 2; i++) {
            String readLine = bufferedReader.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = readLine.charAt(j) == '1';
            }
        }
        System.out.println(bfs() ? 1 : 0);
    }

    static boolean bfs() {
        Queue<SangGeun> queue = new LinkedList<>();
        queue.add(new SangGeun(0, 0));
        for (int i = 0; i < N; i++) {
            Queue<SangGeun> nextSangGeun = new LinkedList<>();
            while (!queue.isEmpty()) {
                SangGeun sangGeun = queue.poll();
                int x = sangGeun.x;
                int y = sangGeun.y;


                if (y == i - 1) {
                    continue;
                }

                if (y - 1 >= 0 && map[x][y - 1]) {
                    map[x][y - 1] = false;
                    nextSangGeun.add(new SangGeun(x, y - 1));
                }

                if (y + 1 >= N) {
                    return true;
                } else {
                    if (map[x][y + 1]) {
                        map[x][y + 1] = false;
                        nextSangGeun.add(new SangGeun(x, y + 1));
                    }
                }

                if (x == 0) {
                    if (y + K >= N) {
                        return true;
                    } else {
                        if (map[1][y + K]) {
                            map[1][y + K] = false;
                            nextSangGeun.add(new SangGeun(1, y + K));
                        }
                    }
                } else {
                    if (y + K >= N) {
                        return true;
                    } else {
                        if (map[0][y + K]) {
                            map[0][y + K] = false;
                            nextSangGeun.add(new SangGeun(0, y + K));
                        }
                    }
                }
            }
            queue = nextSangGeun;
            map[0][i] = false;
            map[1][i] = false;
        }
        return false;
    }

    static void print() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] ? "□ " : "■ ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
