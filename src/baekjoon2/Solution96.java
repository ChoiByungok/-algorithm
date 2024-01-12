package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 5052 전화번호 목록
 * 이번문제도 트라이 자료구조를 사용하여 풀어보았다.
 * 우선 입력받은 문자열을 String 배열에 저장한다.
 * 그 이후에 미리 구현한 트라이 자료구조를 생성해놓고
 * (단 여기서 주의 할 점은 각 테스트 케이스마다 트리에 자료구조를 다시 생성해 주어야 한다.)
 * 그 이후에 트라이 자료구조에 입력 받은 문자열을 저장한 후
 * 다시 한번 String 배열을 순회하여 해당 케이스가 일관성이 있는 케이스 인지 아닌지 구분을 한다
 * 구분은 들어온 문자열 길이의 -1 까지 순회하여 중간에 노드가 문자열의 끝인지 아닌지만 구분해주면된다.
 * 왜 -1 까지 했냐면 문자열의 마지막 노드까지 탐색을 하면 마지막 노드는 무조건 문자의 끝이라
 * 일관성을 구분할 수 없기 때문이다.
 * 다른 사람의 풀이를 보니 트라이 자료구조를 사용하지 않고
 * 문자열을 정렬한뒤
 * 현재 문자열이 바로 다음에 올 문자열의 접두사인것으로 문제를 해결하는 것을 보았다.
 * 예를 들어 19 4 193 52 라는 배열이 있다고 치면
 * 숫자일 경우에는 4 19 52 193 으로 정렬이 되지만
 * 문자열일 경우에는 아스키 코드값에 의해서 19 193 4 52 로 정렬이 되기 때문에
 * 현재문자열인 19와 바로 다음문자열인 193으로 비교가 직접 가능해진다
 * 이런 풀이도 있고 저런 풀이도 있는 자유도가 높은 문제여서 좋았다.
 */
public class Solution96 {
    static class Node {
        public Map<Character, Node> child = new HashMap<>();
        boolean isEnd;
    }
    static class Trie {
        Node node = new Node();
        public void insert(String string) {
            Node node = this.node;
            for (int i = 0; i < string.length(); i++) {
                char key = string.charAt(i);
                node = node.child.computeIfAbsent(key, character -> new Node());
            }
            node.isEnd = true;
        }

        public boolean consistency(String string) {
            Node node = this.node;
            for (int i = 0; i < string.length() - 1; i++) {
                char key = string.charAt(i);
                node = node.child.get(key);

                if (node == null) {
                    return false;
                }

                if (node.isEnd) {
                    return false;
                }
            }

            return true;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bufferedReader.readLine());
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < T; i++) {
            Trie trie = new Trie();
            boolean consistency = true;
            int N = Integer.parseInt(bufferedReader.readLine());
            String[] strings = new String[N];
            for (int j = 0; j < N; j++) {
                strings[j] = bufferedReader.readLine();
            }

            for (int j = 0; j < N; j++) {
                trie.insert(strings[j]);
            }

            for (int j = 0; j < N; j++) {
                if (!trie.consistency(strings[j]))  {
                    consistency = false;
                    break;
                }
            }

            if (consistency) {
                answer.append("YES").append("\n");
            } else {
                answer.append("NO").append("\n");
            }
        }
        System.out.println(answer);
    }
}
