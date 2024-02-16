package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 2638 치즈 (Gold3)
 * 이전에 풀었던 치즈(2636)문제랑 비슷한데 이번에는 2면이상의 외부공기와 닿아 있어야 치즈가 녹는다.
 * 이 문제야말로 외부공기와 내부공기를 미리 구분해놔야 할거같은데 어떻게 해야 할지 생각을 해봐야 할거같다.
 * 우선 나는 2중반복문을 돌다가 치즈를 만났을 때 해당 치즈가 공기와 2면이상 접촉하는 치즈들만 구분을 하였다.
 * 해당 치즈들은 2면이상 공기와 접촉하지만 그 공기가 내부공기 인지 외부공기인지는 구분되어 있지않는다.
 * 그 후 공기들의 좌표를 저장하여 해당 좌표들이 0,0 으로 갈 수 있으면 외부공기이고 아니면 내부공기라고 판단하였다.
 * 그렇게 공기들의 좌표들이 0,0 으로 갈 수 있는게 2개 이상 나오면 녹는 치즈라 판단하고 알고리즘을 진행하였으나 메모리초과가 발생하였다.
 * 메모리초과의 이유를 몰랏던 나는 그냥 야예 코드를 갈아엎기로 하였다.
 * 우선 테두리에는 치즈가 존재하지 않는다고 했으니
 * 0,0 좌표부터 치즈인 곳을 제외하고 전부 탐색을 진행한다.
 * 그러면 치즈로 둘러쌓인 내부공기는 탐색이 불가능 하므로 여기서 외부공기랑 내부공기를 구분할 수 있다.
 * 나같은 경우는 int형 2차원 배열을 만들어 외부공기는 2 치즈는 1 내부공기는 0으로 구분하였다.
 * 그 이후에는 이제 치즈의 상하좌우를 탐색하여 외부공기가 2칸 이상 있는지만 확인하면 된다.
 * 2칸이상이면 그 자리의 치즈를 없애주고 이것을 모든 치즈가 사라질때까지 반복한 후 몇번 반복하였는지 출력해주면 된다.
 */
public class Solution131 {
    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N;
    static int M;
    static boolean[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        int answer = 0;
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        map = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = tokenizer.nextToken().equals("1");
            }
        }

        while (count() != 0) {
            answer++;
            int[][] newMap = getOutSideAir();
            for (int i = 0; i < newMap.length; i++) {
                for (int j = 0; j < newMap[i].length; j++) {
                    int count = 0;
                    if (newMap[i][j] == 1) {
                        if (newMap[i - 1][j] == 2) {
                            count++;
                        }
                        if (newMap[i + 1][j] == 2) {
                            count++;
                        }
                        if (newMap[i][j - 1] == 2) {
                            count++;
                        }
                        if (newMap[i][j + 1] == 2) {
                            count++;
                        }
                    }
                    if (count >= 2) {
                        map[i][j] = false;
                    }
                }
            }
            print();
        }
        System.out.println(answer);
    }

    private static int[][] getOutSideAir() {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0));
        int[][] newMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                newMap[i][j] = map[i][j] ? 1 : 0;
            }
        }
        boolean[][] visited = new boolean[N][M];
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            Point poll = queue.poll();
            int x = poll.x;
            int y = poll.y;

            newMap[x][y] = 2;

            if (x + 1 < N && !map[x + 1][y] && !visited[x + 1][y]) {
                queue.add(new Point(x + 1, y));
                visited[x + 1][y] = true;
            }

            if (x - 1 >= 0 && !map[x - 1][y] && !visited[x - 1][y]) {
                queue.add(new Point(x - 1, y));
                visited[x - 1][y] = true;
            }

            if (y + 1 < M && !map[x][y + 1] && !visited[x][y + 1]) {
                queue.add(new Point(x, y + 1));
                visited[x][y + 1] = true;
            }

            if (y - 1 >= 0 && !map[x][y - 1] && !visited[x][y - 1]) {
                queue.add(new Point(x, y - 1));
                visited[x][y - 1] = true;
            }
        }
        return newMap;
    }

    static int count () {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }
    static void print() {
        System.out.println();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j] ? "■ " : "□ ");
            }
            System.out.println();
        }
    }
}
