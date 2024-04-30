package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 18428 감시피하기 (Gold5)
 * dfs 백트래킹을 이용하여 장애물이 존재할 수 있는 모든 위치에 장애물을 3개 설치하였고
 * 그때마다 학생이 선생의 감시를 피할 수 있는지 없는지 확인하였다.
 * 모든 경우의 수를 다 돌고 왔는데도 불가능하면 NO를 출력시켜 주었고
 * 한번이라도 감시를 피할 수 있으면 그 자리에서 YES를 출력시키고 프로그램을 종료시켜주었다.
 */
public class Solution6 {
    static class Teacher {
        int x;
        int y;

        public Teacher(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N;
    static String[][] map;
    static final int MAX = 3;
    static List<Teacher> teachers = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        map = new String[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = tokenizer.nextToken();
                if (map[i][j].equals("T")) {
                    teachers.add(new Teacher(i, j));
                }
            }
        }
        dfs(0);
        System.out.println("NO");
    }

    static void dfs(int count) {
        if (count == MAX) {
            if (watch()) {
                System.out.println("YES");
                System.exit(0);
            }
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j].equals("X")) {
                    map[i][j] = "O";
                    dfs(count + 1);
                    map[i][j] = "X";
                }
            }
        }
    }

    static void print(String[][] map) {
        System.out.println();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    static boolean watch() {
        for (Teacher teacher : teachers) {
            int x = teacher.x;
            int y = teacher.y;

            for (int i = x - 1; i >= 0; i--) {
                if (map[i][y].equals("O")) {
                    break;
                } else if (map[i][y].equals("S")) {
                    return false;
                }
            }

            for (int i = x + 1; i < N; i++) {
                if (map[i][y].equals("O")) {
                    break;
                } else if (map[i][y].equals("S")) {
                    return false;
                }
            }

            for (int i = y - 1; i >= 0; i--) {
                if (map[x][i].equals("O")) {
                    break;
                } else if (map[x][i].equals("S")) {
                    return false;
                }
            }

            for (int i = y + 1; i < N; i++) {
                if (map[x][i].equals("O")) {
                    break;
                } else if (map[x][i].equals("S")) {
                    return false;
                }
            }
        }
        return true;
    }
}
