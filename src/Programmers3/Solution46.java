package Programmers3;

import java.util.*;

/**
 * 여행경로 Lv.3
 * 나의 풀이 방법은 키값(출발지와) 밸류값(도착지들) 로 구성된 맵을 이용하여
 * 도착지들을 사전순 오름차순으로 정리해둔뒤
 * ICN 부터 탐색하여 모든 티켓을 다 사용했을때 처음 나온값이 모든 경로중 가장 사전순으로 빠른 값이 되므로
 * 거기서 탐색을 중단하는 dfs방식으로 구현을 하였다. 그런데 제출하니깐 2개는 맞고 2개는 틀리다고 나왔다.
 * 다른사람의 풀이를 보니 갈 수 있는 모든 경로를 탐색한뒤 거기서 또 정렬을 하고 반환을 하는 방식으로 하던데
 * 나는 그게 비효율적일거라 생각하고 나만의 방식으로 풀어봤는데 런타임에러가 발생하였다. 이게 과연 스택오버플로우인지 아니면 배열범위를 넘어가는 오류인지
 * 알 방법이 없어서 참 난감하다.
 */
public class Solution46 {
    static class City {
        int index;
        String name;

        public City(int index, String name) {
            this.index = index;
            this.name = name;
        }
    }
    static boolean[] visited;
    static Map<String, List<City>> paths = new HashMap<>();
    static String[] test;
    static boolean arrive = false;
    public String[] solution(String[][] tickets) {
        int N = tickets.length;
        test = new String[N + 1];
        visited = new boolean[N];
        int index = 0;
        for (String[] ticket : tickets) {
            String start = ticket[0];
            String end = ticket[1];
            if (!paths.containsKey(start)) {
                List<City> list = new ArrayList<>();
                list.add(new City(index++, end));
                paths.put(start, list);
            } else {
                paths.get(start).add(new City(index++, end));
            }
        }
        for (List<City> value : paths.values()) {
            value.sort(Comparator.comparing(o -> o.name));
        }
        test[0] = "ICN";
        dfs("ICN", 0, N);
        return test;
    }

    static void dfs(String start, int depth, int N) {
        if (depth == N) {
            arrive = true;
            return;
        }
        for (City city : paths.get(start)) {
            String name = city.name;
            int index = city.index;
            if (!visited[index] && !arrive) {
                visited[index] = true;
                test[depth + 1] = name;
                dfs(name, depth + 1, N);
                if (arrive) {
                    return;
                }
                visited[index] = false;
            }
        }
    }

    public static void main(String[] args) {
        Solution46 solution46 = new Solution46();
        /*System.out.println(Arrays.toString(solution46.solution(
                new String[][]{{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}})));*/
        System.out.println(Arrays.toString(solution46.solution(
                new String[][]{{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}})));
    }
}
