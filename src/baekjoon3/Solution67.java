package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 20364 부동산 다툼 (Silver1)
 * 처음에는 루트1 부터 왼쪽 자식 오른쪽 자식 전부 뒤져보는 dfs 방식을 사용하려고 했는데
 * 하다보니 헷갈려서 결국 답지를 봄 알고보니 입력받은 땅부터 루트까지 역으로 뒤져보는게 더 효율 적임
 * 거슬러 올라가다가 부모 노드가 이미 점거중이면 받을 수 없는 땅인거임
 * 그래서 그렇게 풀었더니 0퍼센트에서 틀렸습니다 나옴
 * 상식적으로 반복문을 돌다가 부모노드가 이미 점거중이면 그 이후는 볼 필요도 없기때문에 break문을 걸었는데
 * break 문을 걸면 0퍼에서 틀렸다고 뜨고 빼면 통과됨 왜 break문을 걸면 틀리는지 모르겠음
 */
public class Solution67 {
    static boolean[] tree;
    static int N;
    static StringBuilder answer = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] NQ = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(NQ[0]);
        int Q = Integer.parseInt(NQ[1]);
        tree = new boolean[N + 1];
        for (int i = 0; i < Q; i++) {
            int x = Integer.parseInt(bufferedReader.readLine());
            check(x);
        }
        System.out.println(answer);
    }

    static void check(int x) {
        int tmp = x;
        int y = 0;
        while (x >= 1) {
            if (tree[x]) {
                y = x;
            }
            x = x / 2;
        }
        tree[tmp] = true;
        answer.append(y).append("\n");
    }
}
