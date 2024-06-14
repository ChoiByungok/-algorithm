package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1068 트리 (Gold5)
 * 처음에는 트리라는 클래스를 따로 만들고 해결해보려고 했다.
 * 그렇게 트리를 다 구현하고 예제도 통과되는 모습을 본 후
 * 제출했더니 바로 틀렸습니다. 나오길래 질문게시판의 반례를 보았다.
 * 나는 무조건 처음 입력되는 값이 루트 노드일 줄 알았는데 알고보니 나중에도 나올 수 있는거였다.
 * 그래서 지금까지 해오던거 싹다 지우고 연결리스트를 이용하여 해결하였다.
 * 입력 도중 -1 이 들어오면 해당 노드는 루트노드가 된다.
 * 그리고 그 외에는 단방향으로 연결시켜주면 된다.
 * 그 후 알아낸 루트노드부터 탐색을 하여 지워야할 노드를 발견하면 부모노드에서 지워주면 된다.
 * 지운다음에는 다시 루트노드부터 탐색하여 해당 노드가 자식이 있는지 없는지 카운트를 세주면 되는데
 * 지워야할 노드가 만약 루트노드이면 그냥 0출력시켜주면 되고 아니면 카운트를 출력시켜주면 된다.
 */
public class Solution51 {
    static List<ArrayList<Integer>> tree = new ArrayList<>();
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            tree.add(new ArrayList<>());
        }
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        int root = 0;
        for (int child = 0; child < N; child++) {
            int parent = Integer.parseInt(tokenizer.nextToken());
            if (parent == - 1) {
                root = child;
            } else {
                tree.get(parent).add(child);
            }
        }
        int delete = Integer.parseInt(bufferedReader.readLine());
        delete(root, delete);
        leafCount(root);
        System.out.println(root == delete ? 0 : answer);

    }
    static void delete(int root, int delete) {
        if (tree.get(root).contains(delete)) {
            for (Integer integer : tree.get(root)) {
                if (integer == delete) {
                    tree.get(root).remove(integer);
                    break;
                }
            }
            return;
        }

        for (Integer integer : tree.get(root)) {
            delete(integer, delete);
        }
    }

    static void leafCount(int root) {
        if (tree.get(root).isEmpty()) {
            answer++;
            return;
        }

        for (Integer integer : tree.get(root)) {
            leafCount(integer);
        }
    }
}
