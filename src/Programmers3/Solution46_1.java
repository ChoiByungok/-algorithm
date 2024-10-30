package Programmers3;

import java.util.*;

/**
 * 여행경로 Lv.3
 * 검색하면 나오는 풀이이다. 모든 경로를 전부 구한 뒤
 * 오름차순 정렬을 통해 나온 경로중 사전순으로 가장 빠른 한 경로만 반환하면 된다.
 */
public class Solution46_1 {
    static List<String> paths = new ArrayList<>();
    static boolean[] visited;
    public String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];
        dfs("ICN", "ICN", tickets, 0);
        Collections.sort(paths);
        return paths.get(0).split(" ");
    }

    static void dfs(String start, String path, String[][] tickets, int depth) {
        if (depth == tickets.length) {
            paths.add(path);
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            String s = tickets[i][0];
            String e = tickets[i][1];
            if (start.equals(s)  && !visited[i]) {
                visited[i] = true;
                dfs(e, path + " " + e, tickets, depth + 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Solution46_1 solution46 = new Solution46_1();
        /*System.out.println(Arrays.toString(solution46.solution(
                new String[][]{{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}})));*/
        System.out.println(Arrays.toString(solution46.solution(
                new String[][]{{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}})));
    }
}
