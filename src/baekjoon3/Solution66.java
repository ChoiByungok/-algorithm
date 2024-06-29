package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 9934 완전 이진 트리 (Silver1)
 * 1-6-4-3-5-2-7 의 중위 순회 결과를 보고 각 노드별 레벨을 구분하여 레벨별로 출력하는 문제
 * 루트노드인 3은 레벨 1이고 6 2 는 레벨 2이다. 그리고 1 4 5 7 은 레벨이 3이기 때문에
 * 3
 * 6 2
 * 1 4 5 7
 * 이렇게 출력해야 하는데 어떻게 중위 순회의 결과만 보고 레벨을 판단 할 수 있을까
 * 주어진 트리가 완전 이진 트리라는것이 주요한 힌트인거같은데 아직은 가늠이 잡히지 않는다....
 * 약간 무식한 방법인데 중위순회 결과의 시작인덱스와 끝 인덱스를 더한 뒤 나누기 2를 하면 그 몫의 인덱스가 루트가 된다.
 * 그렇게 분할 재귀를 하여 레벨을 구해주었고 그 결과를 2차원 배열에 담아서 2차원 배열에 담긴 결과를 출력하였다.
 */
public class Solution66 {
    static int[] buildings;
    static int[][] level;
    static int index = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int K = Integer.parseInt(bufferedReader.readLine());
        int N = (int) Math.pow(2, K) - 1;
        buildings = new int[N];
        level = new int[K][N];
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());

        for (int i = 0; i < N; i++) {
            buildings[i] = Integer.parseInt(tokenizer.nextToken());
        }

        level(0, N - 1, 0);

        for (int i = 0; i < K; i++) {
            for (int j = 0; j < N; j++) {
                if (level[i][j] != 0) {
                    answer.append(level[i][j]).append(" ");
                }
            }
            answer.append("\n");
        }
        System.out.println(answer);
    }

    static void level(int start, int end, int depth) {
        if (start < end) {
            int root = (start + end) / 2;
            level(start, root - 1, depth + 1);
            level[depth][index++] = buildings[root];
            level(root + 1, end, depth + 1);
        } else if (start == end) {
            level[depth][index++] = buildings[start];
        }

    }

    private static void print() {
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                System.out.print(level[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
