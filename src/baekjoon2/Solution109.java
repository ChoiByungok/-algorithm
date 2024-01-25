package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 14725 개미굴 (Gold3)
 * Trie 자료구조를 사용하여 문제를 해결하였다.
 * 기존에 풀었던 트리에 알고리즘은 노드의 키값을 char 형으로 주었었는데 해당 문제는 String 형으로 주는 차이 밖에 없다.
 * 근데 이 문제의 진짜 고비는 답을 출력하는게 어렵다는 것이었다.
 * 같은 레벨일때는 알파벳 순으로 정렬을 해야 하고 depth 에따라 -- 갯수가 늘어나기 때문이다.
 * 어렴풋이 재귀를 써야 할거같은 느낌이 들었다.
 * 트리에는 루트부터 탐색을 하는데 나는 함수 오버로딩을 이용하여
 * 1레벨에 depth 는 루트랑 붙어있는 자식이기 때문에 depth 정보만 보내고
 * 그 이후부턴 매개변수에 node 가 포함되어 있는 같은 이름의 함수를 사용하여 node 와 depth 를 재귀적으로 보내 문제를 해결하였다.
 * 2레벨 depth 부터는 부모가 누구인지 알기 때문에 루트부터 탐색을 할 필요가 없기 때문이다.
 */
public class Solution109 {
    static StringBuilder answer = new StringBuilder();
    static class Node {
        Map<String, Node> child = new LinkedHashMap<>();
    }
    static class Trie {
        Node root = new Node();

        public void insert(String[] strings) {
            Node node = this.root;
            for (int i = 1; i < strings.length; i++) {
                String key = strings[i];
                node = node.child.computeIfAbsent(key, s -> new Node());
            }
        }

        public void printBuilder(int depth) {
            Node node = this.root;
            if (node.child != null) {
                List<String> list = new ArrayList<>(node.child.keySet());
                Collections.sort(list);
                for (String key : list) {
                    answer.append(key).append("\n");
                    printBuilder(node.child.get(key), depth + 1);
                }
            }
        }

        public void printBuilder(Node node, int depth) {
            if (node.child != null) {
                List<String> list = new ArrayList<>(node.child.keySet());
                Collections.sort(list);
                for (String key : list) {
                    answer.append("--".repeat(Math.max(0, depth)));
                    answer.append(key).append("\n");
                    printBuilder(node.child.get(key), depth + 1);
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        Trie trie = new Trie();
        for (int i = 0; i < N; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            trie.insert(split);
        }
        trie.printBuilder(0);
        System.out.println(answer);
    }
}
