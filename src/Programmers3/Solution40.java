package Programmers3;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 단어 변환 Lv.3
 * 이 문제를 처음 접했을 땐 그냥 무식하게 현재 단어랑 다음단어랑 하나하나 일일히 비교해보고
 * 알파벳이 하나만 다를경우만 탐색을 진행하여 풀어보았다.
 * 단어갯수가 최대 50개고 길이가 최대 10이라고 했으니 많이 비교해봤자 500번이라고 생각했기 때문이다
 * 그래도 시간초과가 나지 않을까 걱정했지만 의외로 한번에 통과되었다.
 * 역시 일단 문제가 틀리던 맞던 한번 찔러보는게 답인거같다.
 */
public class Solution40 {
    static class Node {
        String word;
        int step;

        public Node(String word, int step) {
            this.word = word;
            this.step = step;
        }
    }
    public int solution(String begin, String target, String[] words) {
        if (!isExits(target, words)) {
            return 0;
        }
        return bfs(begin, target, words);
    }
    static boolean isExits(String target, String[] words) {
        for (String word : words) {
            if (target.equals(word)) {
                return true;
            }
        }
        return false;
    }

    static int bfs(String begin, String target, String[] words) {
        boolean[] visited = new boolean[words.length];
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(begin, 0));
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            String now = poll.word;
            int step = poll.step;

            if (now.equals(target)) {
                return step;
            }

            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                if (now.length() == word.length() && !visited[i]) {
                    int count = 0;
                    for (int j = 0; j < word.length(); j++) {
                        if (now.charAt(j) == word.charAt(j)) {
                            count++;
                        }
                    }
                    if (word.length() - 1 == count) {
                        visited[i] = true;
                        queue.add(new Node(word, step + 1));
                    }
                }
            }
        }

        return 0;
    }
    public static void main(String[] args) {
        Solution40 solution40 = new Solution40();
        System.out.println(solution40.solution("hit", "cog", new String[] {"hot", "dot", "dog", "lot", "log", "cog"}));
        System.out.println(solution40.solution("hit", "cog", new String[] {"hot", "dot", "dog", "lot", "log"}));
    }
}
