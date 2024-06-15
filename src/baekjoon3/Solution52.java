package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1987 알파벳 (Gold4)
 * 처음엔 bfs로 접근해봤다가 가차없이 메모리 초과가 발생하였다.
 * 그래서 알고리즘 분류에 쓰여저 있는 백트래킹 기법으로 풀었더니
 * 통과는 되었는데 성능이 최악이었다. 다른사람의 코드보다 평균 3배가 느린 코드가 완성됨
 * 이제 다른 사람들은 어떻게 풀었는지 보고 다시한번 풀어봐야 할거같다.
 * 다른 사람의 풀이를 보니 나처럼 set 자료구조를 사용하지 않고
 * 알파뱃 배열을 하나 생성하여 그 배열을 체크하면서 탐색하는 그런 방식으로 풀었음
 */
public class Solution52 {
 /*   static class Point {
        Set<Character> set;
        int x;
        int y;

        public Point(Set<Character> set, int x, int y) {
            this.set = set;
            this.x = x;
            this.y = y;
        }
    }*/
    static char[][] map;
    static int answer = Integer.MIN_VALUE;
    static int N;
    static int M;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] split = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String readLine = bufferedReader.readLine();
            for (int j = 0; j < readLine.length(); j++) {
                map[i][j] = readLine.charAt(j);
            }
        }
//        bfs();
        Set<Character> set = new HashSet<>();
        set.add(map[0][0]);
        backTracking(0, 0, set);
        System.out.println(answer);
    }

    private static void backTracking(int x, int y, Set<Character> set) {

        if (x + 1 < N && !set.contains(map[x + 1][y])) {
            set.add(map[x + 1][y]);
            backTracking(x + 1, y, set);
            set.remove(map[x + 1][y]);
        }

        if (x - 1 >= 0 && !set.contains(map[x - 1][y])) {
            set.add(map[x - 1][y]);
            backTracking(x - 1, y, set);
            set.remove(map[x - 1][y]);
        }

        if (y + 1 < M && !set.contains(map[x][y + 1])) {
            set.add(map[x][y + 1]);
            backTracking(x, y + 1, set);
            set.remove(map[x][y + 1]);
        }

        if (y - 1 >= 0 && !set.contains(map[x][y - 1])) {
            set.add(map[x][y - 1]);
            backTracking(x, y - 1, set);
            set.remove(map[x][y - 1]);
        }

        answer = Math.max(answer, set.size());
    }

    /*private static void bfs() {
        Queue<Point> queue = new LinkedList<>();
        Set<Character> set = new LinkedHashSet<>();
        set.add(map[0][0]);
        queue.add(new Point(set, 0, 0));
        while (!queue.isEmpty()) {
            Point now = queue.poll();
            Set<Character> characters = now.set;
            int x = now.x;
            int y = now.y;
            boolean deadEnd = true;

            if (x + 1 < N && !characters.contains(map[x + 1][y])) {
                HashSet<Character> next = new LinkedHashSet<>(characters);
                next.add(map[x + 1][y]);
                queue.add(new Point(next, x + 1, y));
                deadEnd = false;
            }

            if (x - 1 >= 0 && !characters.contains(map[x - 1][y])) {
                HashSet<Character> next = new LinkedHashSet<>(characters);
                next.add(map[x - 1][y]);
                queue.add(new Point(next, x - 1, y));
                deadEnd = false;
            }

            if (y + 1 < M && !characters.contains(map[x][y + 1])) {
                HashSet<Character> next = new LinkedHashSet<>(characters);
                next.add(map[x][y + 1]);
                queue.add(new Point(next, x, y + 1));
                deadEnd = false;
            }

            if (y - 1 >= 0 && !characters.contains(map[x][y - 1])) {
                HashSet<Character> next = new LinkedHashSet<>(characters);
                next.add(map[x][y - 1]);
                queue.add(new Point(next, x, y - 1));
                deadEnd = false;
            }

            if (deadEnd) {
                System.out.println(characters);
                answer = Math.max(answer, characters.size());
            }
        }
    }*/
}
