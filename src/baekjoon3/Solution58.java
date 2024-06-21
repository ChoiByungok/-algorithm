package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 3584 가장 가까운 공통 조상 (Gold4)
 * 배열을 하나 생성하여 부모 자식관계를 입력한다.
 * 주어진 두 노드의 모든 조상을 앞서 만든 배열을 이용하여 재귀 반복을 사용하여 리스트에 담는다.
 * 그렇게 a 와 b 의 모든 조상들이 두 리스트에 담긴다.
 * 두개의 리스트를 서로 비교하여 공통되는 숫자가 나올때까지 반복한다.
 * 그렇게 먼저 나온 공통의 숫자가 공통조상이 된다.
 */
public class Solution58 {
    static int[] parents;
    static List<Integer> aParents;
    static List<Integer> bParents;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int T = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(bufferedReader.readLine());
            parents = new int[N + 1];
            for (int j = 0; j < N - 1; j++) {
                StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
                int parent = Integer.parseInt(tokenizer.nextToken());
                int child = Integer.parseInt(tokenizer.nextToken());
                parents[child] = parent;
            }
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            aParents = new ArrayList<>();
            bParents = new ArrayList<>();
            findAncestors(a, true);
            findAncestors(b, false);
            for (Integer aParent : aParents) {
                if (bParents.contains(aParent)) {
                    answer.append(aParent).append("\n");
                    break;
                }
            }
        }
        System.out.println(answer);
    }

    static void findAncestors(int start, boolean a) {
        if (parents[start] == 0) {
            if (a) {
                aParents.add(start);
            } else {
                bParents.add(start);
            }
            return;
        }

        if (a) {
            aParents.add(start);
        } else {
            bParents.add(start);
        }


        findAncestors(parents[start], a);
    }
}
