package Programmers3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 큰 수 만들기 Lv.2
 * dfs탐색으로 문제를 접근해 보았다 탐색 인덱스도 두어서 최대한 효율적으로 해보려고 했는데
 * 시간초과 메모리 초과가 발생하였다 아무래도 dfs로 접근하는 문제는 아닌가보다.
 */
public class Solution52 {
    static List<String> list = new ArrayList<>();
    static boolean[] visited;
    public String solution(String number, int k) {
        char[] chars = number.toCharArray();
        visited = new boolean[chars.length];
        dfs(k,0, chars, 0);
        System.out.println(list);
        Collections.sort(list);
        return list.get(list.size() -1);
    }

    static void dfs(int k, int depth, char[] chars, int start) {
        if (k == depth) {
            StringBuilder num = new StringBuilder();
            for (int i = 0; i < chars.length; i++) {
                if (!visited[i]) {
                    num.append(chars[i]);
                }
            }
            list.add(num.toString());
            return;
        }

        for (int i = start; i < chars.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(k, depth + 1, chars, i + 1);
                visited[i] = false;
            }
        }
    }
    public static void main(String[] args) {
        Solution52 solution52 = new Solution52();
//        System.out.println(solution52.solution("1924", 2));
//        System.out.println(solution52.solution("1231234", 3));
        System.out.println(solution52.solution("4177252841", 4));
    }
}
