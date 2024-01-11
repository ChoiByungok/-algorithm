package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 14426 접두사 찾기
 * 단순하게 문자열을 해시에 넣고 그 뒤에 입력받는 값을 비교하면서 접두사인지 아닌지 판별하면 시간초과가 발생한다.
 * 이럴때 사용하는 자료구조가 트라이 자료구조이다. 트라이 자료구조는 검색할때 O(N) 시간복잡도를 가지는 좋은 자료구조이지만
 * 메모리를 많이 차지한다는 단점이 있다. 하지만 해당 문제는 메모리를 넉넉하게 주기에 상관없는듯 싶다.
 * for (int i = 0; i < string.length(); i++) {
 *                 if (node.child.containsKey(string.charAt(i))) {
 *                     node = node.child.get(string.charAt(i));
 *                 } else {
 *                     Node child = new Node();
 *                     node.setKey(string.charAt(i), child);
 *                     node = child;
 *                 }
 *             }
 * 이부분은 트라이 자료구조에 문자열을 삽입하는 로직인데
 * 해당 코드는 Map 인터페이스에 존재하는 computeIfAbsent() 라는 메서드로 간단하게 한줄로 처리 가능하지만
 * 이번에 처음 접하는 알고리즘이기에 이해를 위해 직접 구현을 해보았다.
 * for (int i = 0; i < string.length(); i++) {
 *                 if (node.child.containsKey(string.charAt(i))) {
 *                     node = node.child.get(string.charAt(i));
 *                 } else {
 *                     return false;
 *                 }
 *             }
 * 이부분은 문자열을 검색하는 로직인데 여기 또한 getOrDefault() 메서드로 간단하게 처리할 수 있지만
 * 이해를 위하여 직접 구현하였다.
 */
public class Solution95 {
    static class Node {
        Map<Character, Node> child = new LinkedHashMap<>();
        boolean isEnd;

        public void setKey(char key, Node node) {
            this.child.put(key, node);
        }
    }

    static class Trie {
        Node root = new Node();

        public void insert(String string) {
            Node node = this.root;

            for (int i = 0; i < string.length(); i++) {
                char key = string.charAt(i);
                if (node.child.containsKey(key)) {
                    node = node.child.get(key);
                } else {
                    Node child = new Node();
                    node.setKey(key, child);
                    node = child;
                }
            }
            node.isEnd = true;
        }

        public boolean search(String string) {
            Node node = this.root;

            for (int i = 0; i < string.length(); i++) {
                char key = string.charAt(i);
                if (node.child.containsKey(key)) {
                    node = node.child.get(key);
                } else {
                    return false;
                }
            }
            return true;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(nm[0]);
        int M = Integer.parseInt(nm[1]);
        int count = 0;
        Trie trie = new Trie();
        for (int i = 0; i < N; i++) {
            String readLine = bufferedReader.readLine();
            trie.insert(readLine);
        }

        for (int i = 0; i < M; i++) {
            String readLine = bufferedReader.readLine();
            if (trie.search(readLine)) {
                count++;
            }
        }
        System.out.println(count);
    }
}
