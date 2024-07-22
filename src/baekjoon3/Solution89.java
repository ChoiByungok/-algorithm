package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2617 구슬 찾기 (Gold4)
 * 플로이드 워셜 알고리즘을 사용해서 정확히 무게를 비교할 수 있는 노드와 아닌 노드를 구분하는 방법이 있었는데 다 잊어버림
 * 다른 사람의 코드를 살짝 참고함
 * boolean 형 배열로 해결하려고 했는데 이 문제는 3가지의 상태가 필요하기 때문에 int형 배열로 해결하였음
 * 현재 구슬이 다른 구슬보다 무거우면 1 가벼우면 -1 모르면 0 이렇게 총 3가지의 상태가 필요함
 * 플로이드 와셜 알고리즘을 이용하여 간접적으로 연결되어있는 구슬이 현재 구슬보다 무거운지 가벼운지 판별함
 * 그렇게 판별된 배열을 이용하여 1 과 -1 갯수를 각각 세어줌
 * 그렇게 1이 나 -1 이 중간값보다 같거나 크면 해당 구슬은 중간값이 될 수 없음
 * 그 구슬의 갯수를 출력해주면 되는거임
 */
public class Solution89 {
    static int N;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int answer = 0;
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        int median = (N + 1) / 2;
        int M = Integer.parseInt(tokenizer.nextToken());
        map = new int[N][N];
        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int A = Integer.parseInt(tokenizer.nextToken()) - 1;
            int B = Integer.parseInt(tokenizer.nextToken()) - 1;
            map[A][B] = 1;
            map[B][A] = -1;
        }

        for (int mid = 0; mid < N; mid++) {
            for (int start = 0; start < N; start++) {
                for (int end = 0; end < N; end++) {
                    if (start == end || start == mid || mid == end) {
                        continue;
                    }

                    if (map[start][end] == 0) {
                        if (map[start][mid] != 0 && map[start][mid] == map[mid][end]) {
                            map[start][end] = map[start][mid];
                        }
                    }
                }
            }
        }
        for (int i = 0; i < N; i++) {
            int heavy = 0;
            int light = 0;
            for (int j = 0; j < N; j++) {
                if (map[i][j] == -1) {
                    light++;
                }
                if (map[i][j] == 1) {
                    heavy++;
                }
            }
            if (light >= median || heavy >= median) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    static void print() {
        System.out.println();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
