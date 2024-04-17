package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 15686 치킨 배달 (Gold5)
 * 역시 이문제 또한 백트래킹을 이용하여 해결하려고 했다.
 * 치킨집의 갯수를 세고 그 숫자와 같은 boolean형 배열을 선언한 뒤
 * 최소 치킨집 갯수에 도달하면 그때 최소 치킨 거리를 재고 값을 비교하려고 했는데
 * 시간초과가 발생하였다. 예를들어 치킨집이 3개있고 3개롤 남겨놓아야 한다고 했을때
 * 123 132 213 231 312 321 이런식으로 순서가 나오겠지만
 * 사실은 순서에 상관없이 모두 거리는 같기때문에 필요없는 연산이 5번이나 더 진행하게 되는것이다.
 * 어떻게 해야 중복연산을 없앨 수 있을까 고민하다가 결국 다른사람의 풀이에서 해답을 찾아내었다.
 * start 라는 변수를 주고 다음 재귀호출을 할때 +1 씩 해서 넘겨주면
 * 반복문을 start 부터 보기때문에 이미 계산을 한 치킨집은 다시 계산을 하지 않게 되는것이다.
 * start 변수를 둠으로써 123 의 경우만 계산하고 나머지는 하지 않게 된다.
 * 그렇기 때문에 시간초과를 벗어날 수 있었다.
 */
public class Solution193 {
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
    static List<Point> chicken = new ArrayList<>();
    static List<Point> house = new ArrayList<>();
    static boolean[] visited;
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        for (int i = 1; i <= N; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 1; j <= N; j++) {
                int num = Integer.parseInt(tokenizer.nextToken());
                if (num == 1) {
                    house.add(new Point(i, j));
                } else if (num == 2) {
                    chicken.add(new Point(i, j));
                }
            }
        }
        visited = new boolean[chicken.size()];
        permutation(0, 0);
        System.out.println(answer);
    }

    static void permutation(int depth, int start) {
        if (depth == M) {
            int total = 0;
            for (Point house : house) {
                int min = Integer.MAX_VALUE;
                for (int j = 0; j < chicken.size(); j++) {
                    if (visited[j]) {
                        Point open = chicken.get(j);
                        int dir = Math.abs(house.x - open.x) + Math.abs(house.y - open.y);
                        min = Math.min(min, dir);
                    }
                }
                total += min;
            }
            answer = Math.min(answer, total);
            return;
        }

        for (int i = start; i < chicken.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                permutation(depth + 1, i + 1);
                visited[i] = false;
            }
        }
    }

    static void permutation(int depth) {
        if (depth == M) {
            int total = 0;
            for (Point house : house) {
                int min = Integer.MAX_VALUE;
                for (int j = 0; j < chicken.size(); j++) {
                    if (visited[j]) {
                        Point open = chicken.get(j);
                        int dir = Math.abs(house.x - open.x) + Math.abs(house.y - open.y);
                        min = Math.min(min, dir);
                    }
                }
                total += min;
            }
            answer = Math.min(answer, total);
            return;
        }

        for (int i = 0; i < chicken.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                permutation(depth + 1);
                visited[i] = false;
            }
        }
    }

}
