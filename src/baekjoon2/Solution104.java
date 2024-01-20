package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 4195 친구 네트워크
 * 이전에 풀어봣던 유니온 파인드 알고리즘의 심화버전
 * 파인드 메서드로 해당 노드의 루트를 찾고
 * 두 집합의 루트가 같지 않다면 유니온 메서드로 두 집합을 합쳐준다.
 * 여기까진 일반 유니온 파인드 알고리즘과 비슷하지만
 * 이번엔 먼저들어온 입력값 즉 Fred Barney 라는 입력을 받았을때
 * Fred 라는 사람의 루트를 찾아서 루트부터 친구 관계가 몇명인지 계산을 해야한다.
 * 그러기 위해선 counts 라는 배열을 따로 선언해야 한다.
 * Fred Barney
 * Betty Wilma
 * Barney Betty 의 예제를 예로 들면
 * 첫번째 줄에 의해 Barney 의 루트는 Fred 가 된다.
 * 그리고 먼저들어온 입력값 Fred 의 루트는 Fred 이며 이때 Fred 의 친구관계는 2가 된다.
 * 두번쨰 줄에 의해 마찬가지로 Wilma 의 루트는 Betty 가 되고 이때 Betty 의 루트는 Betty 이므로 Betty 의 친구관계는 2가 된다.
 * 마지막으로 Barney Betty 가 입력되는데 Barney 의 루트는 Fred 이다. Fred 는 친구관계가 2이고
 * Betty 의 친구관계는 2이므로 두 값을 더하여 4가 되게 되는 것이다.
 */
public class Solution104 {
    static int[] parents;
    static int[] counts;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bufferedReader.readLine());
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(bufferedReader.readLine());
            Map<String, Integer> map = new HashMap<>();
            parents = new int[N * 2];
            counts = new int[N * 2];
            for (int j = 0; j < parents.length; j++) {
                parents[j] = j;
                counts[j] = 1;
            }

            for (int j = 0; j < N; j++) {
                String[] split = bufferedReader.readLine().split(" ");
                map.putIfAbsent(split[0], map.size());
                map.putIfAbsent(split[1], map.size());
                Integer parent = map.get(split[0]);
                Integer child = map.get(split[1]);
                int relation = union(child, parent);
                answer.append(relation).append("\n");
            }

        }
        System.out.println(answer);
    }

    public static int find(int x) {
        if (x == parents[x]) {
            return x;
        }

        return parents[x] = find(parents[x]);
    }

    public static int union(int child, int parent) {
        child = find(child);
        parent = find(parent);

        if (child != parent) {
            parents[child] = parent;
            counts[parent] += counts[child];
        }

        return counts[parent];
    }
}
