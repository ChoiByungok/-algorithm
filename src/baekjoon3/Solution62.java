package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 2250 트리의 높이와 너비 (Gold2)
 * 우선 노드의 수를 열, 깊이를 행으로 판단하여 2차원배열을 하나 만들여야 할거같다는 생각이 들었다.
 * 그 후 노드의 위치를 알아내어 2차원배열을 완성시키고 완성된 2차원배열을 이용하여 계산을 하면 될거같다고 생각했는데
 * 어떻게 2차원배열을 채울 수 있을까
 * 계속 문제를 들여다 보니 중위연산을 이용하면 2차원 배열을 채울수 있을거같다고 생각했다.
 * 그렇게 생각하고 중위연산을 적용시켜봤더니 진짜로 되긴했다.
 * 예제가 통과되긴했는데 제출하니깐 1퍼센트에서 틀렸다고 나왔다.
 * 설마 당연히 루트노드가 1일거라고 생각하고 접근했는데 아닐수도 있는건가
 * 문제를 다시 읽어보니 노드가 순서대로 입력된다고 했지 1번 노드가 루트라는 말은 없었다.
 * 역시 루트노드를 먼저 찾고 그다음 알고리즘을 진행해야 할거같다.
 * 1. 루트노드를 찾는다.
 * 2. 찾은 루트노드를 이용하여 트리의 레벨을 구한다.
 * 3. 찾은 레벨을 이용하여 2차원배열을 생성한다. int[레벨][노드의 수]
 * 4. 중위순회를 이용하여 2차원 배열을 채운다.
 * 5. 채운 2차원 배열을 순회하여 너비를 구한다.
 */
public class Solution62 {
    static int[][] map;
    static int M = Integer.MIN_VALUE;
    static int N;
    static List<ArrayList<Integer>> tree = new ArrayList<>();
    static int index = 0;
    static int width = 0;
    static int level = 0;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        parent = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            int root = Integer.parseInt(tokenizer.nextToken());
            int left = Integer.parseInt(tokenizer.nextToken());
            int right = Integer.parseInt(tokenizer.nextToken());
            tree.get(root).add(left);
            tree.get(root).add(right);
            if (left != -1) {
                parent[left] = root;
            }
            if (right != -1) {
                parent[right] = root;
            }
        }
        int root = findRoot();
        dfs(root, 1);
        map = new int[M][N];
        inOrder(root, 0);
        findMaxWidthAndLevel();
        System.out.println((level + 1) + " " + (width + 1));
    }

    static int findRoot() {
        int root = 1;
        for (int i = 1; i <= N; i++) {
            if (parent[i] == 0) {
                root = i;
                break;
            }
        }
        return root;
    }

    static void dfs(int root, int depth) {
        Integer left = tree.get(root).get(0);
        Integer right = tree.get(root).get(1);

        if (left == -1 && right == -1) {
            M = Math.max(M, depth);
            return;
        }

        if (left != -1) {
            dfs(left, depth + 1);
        }

        if (right != -1) {
            dfs(right, depth + 1);
        }
    }

    static void inOrder(int root, int depth) {
        Integer left = tree.get(root).get(0);
        Integer right = tree.get(root).get(1);

        if (left == -1 && right == -1) {
            map[depth][index++] = root;
            return;
        }

        if (left != -1) {
            inOrder(left, depth + 1);
        }

        map[depth][index++] = root;

        if (right != -1) {
            inOrder(right, depth + 1);
        }
    }

    static void findMaxWidthAndLevel() {
        for (int i = M - 1; i >= 0 ; i--) {
            int max = 0;
            int min = 0;
            for (int j = N - 1; j >= 0; j--) {
                if (map[i][j] != 0) {
                    max = j;
                    break;
                }
            }
            for (int j = 0; j < N; j++) {
                if (map[i][j] != 0) {
                    min = j;
                    break;
                }
            }
            if (max != min) {
                if (max - min >= width) {
                    width = max - min;
                    level = i;
                }
            }
        }
    }

/*
    static void print() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }*/
}
