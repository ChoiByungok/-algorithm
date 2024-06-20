package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 22856 트리 순회 (Gold4)
 * 보기보다 생각해야 할 요소들이 많아서 많이 헤맸던 문제
 * 우선 중위순회를 했을때 마지막에 방문하는 노드가 어떤 노드인지 알아내야 한다.
 * 마지막 방문노드를 알아냈으면 유사 중위순회를 진행해야 하는데
 * 기본 중위순회랑 다른 점은 자식노드 방문이 끝나면 다시 부모노드로 돌아와야 한다는 것이다.
 * 그리고 그 동작도 순회의 횟수에 포함시켜야 한다.
 * 이걸 어떻게 처리할까 고민하다가 재귀를 호출할때 카운트를 한번 증가시키고
 * 재귀를 마쳤을때 카운트를 한번더 증가시키는것으로 해결하였다.
 * 그러면 해당 재귀의 마무리를 어떻게 지어야 할까
 * 유사 중위순회는 중위순회의 마지막 방문노드에 방문했을때 종료시켜야 한다.
 * 그래서 미리 중위순회를 해놓았고 마지막 방문노드를 구해놓은것이다.
 * 무작정 현재 노드가 중위순회의 마지막 방문노드라고 재귀를 종료시키면 안된다.
 * 왜냐하면 모든 노드를 순회해야 하기 때문이다. 그래서 나는 방문배열을 하나 선언하였다.
 * 모든 노드에 방문을 마쳤으며 현재 노드가 중위순회의 마지막 방문노드라면 재귀를 종료시키게 설정하였다.
 * 그런데 제출하자마자 틀렸다고 나온것이다. 질문게시판의 모든 반례가 통과되었는데 왜 틀렸다고 나왔을까 생각하다가
 * 입력이 틀렸다는것을 깨달았다. 예제들은 1번노드부터 순서대로 주어졌지만 뒤죽박죽으로 주어질수도 있다는것을 간과했기 때문이다.
 * 그렇게 입력을 수정하니 통과되었다. 재귀의 종료가 프로그램 강제종료인게 마음에 들지않아 답을 구한뒤
 * 재귀를 모두 빠져나와서 정답을 출력해보려고 했는데 시간초과가 발생하였다. 지금으로서는 어쩔수 없이 이런 로직을 택해야 할듯 싶다.
 */
public class Solution57 {
    static class Tree {
        int left;
        int right;

        public Tree(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
    static int N;
    static boolean[] visited;
    static Tree[] trees;
    static int count = 0;
    static int last;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        visited = new boolean[N];
        trees = new Tree[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());
            trees[a - 1] = new Tree(b, c);
        }
        inOrder(1);

        similarityInOrder(1);
    }

    static void inOrder(int root) {
        int left = trees[root - 1].left;
        int right = trees[root - 1].right;

        if (left != -1) {
            inOrder(left);
        }

        last = root;

        if (right != -1) {
            inOrder(right);
        }
    }

    static void similarityInOrder(int root) {
        visited[root - 1] = true;
        int left = trees[root - 1].left;
        int right = trees[root - 1].right;

        if (left != -1) {
            count++;
            similarityInOrder(left);
            count++;
        }

        if (right != -1) {
            count++;
            similarityInOrder(right);
            count++;
        }

        if (!check() && root == last) {
            System.out.println(count);
            System.exit(0);
        }
    }

    static boolean check() {
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                return true;
            }
        }
        return false;
    }
}
