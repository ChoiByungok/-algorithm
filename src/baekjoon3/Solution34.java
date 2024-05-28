package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1956 운동 (Gold4)
 * 플로이드 위셜 응용문제 3번째 문제인데 아직도 가늠이 잡히지 않는다.
 * 머리속에서 이렇게 이렇게 될거같은데 느낌만 들고 정확하게 어떻게 풀어야 할지 정립이 되지않는다.
 * 이말은 즉 이전에 풀었던 2문제도 제대로 이해하지 못하고 넘겼다는 것이다.
 * 앞으로 몇문제 더 풀어보면서 가다를 잡아야할거같다.
 * 어찌어찌 응용해서 제출해봤는데 63퍼에서 자꾸 틀림
 * 경로가없는경우 -1을 출력하라길래 처음에 경로가 이어지지 않는 좌표는 최대길이인 10000보다 1이 큰 10001로 초기화 시켜놓고
 * 경로가 존재해 값이 갱신이 되면 바뀌는 거고 경로가 없으면 그대로 10001 이 남아있게 되는거라
 * answer 값을 작은 값으로 갱신해 나가면서 만약 모든 경로가 존재하지 않아 갱신되지 않고 그대로 10001 이된다면
 * 없는 경로라고 판단하고 -1을 출력하게끔 했는데 여기서 오류가 나는거같다 근데 플로이드 워셜을 제대로 이해한게 아니라
 * 이게 왜 틀린지 모르겠음 질문게시판에 존재하는 모든 반례 다 통과하는데 왜 안되는지 모르겠음
 * 결국 다른사람의 코드를 복사해서 제출함 기존의 내 코드랑 뭐가 다른지 모르겠는데 통과됨
 * 플로이드 워셜 알고리즘을 직관적으로 이해를 하지 못하니깐 응용도 못하는거같음
 * 확실하게 이해해야하는데 아 대충 이렇게 푸는거구나 넘어가니깐 응용버전을 아예 풀지를못함
 */
public class Solution34 {
    static int[][] cities;
    static final int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        int answer = INF;
        int V = Integer.parseInt(tokenizer.nextToken());
        int E = Integer.parseInt(tokenizer.nextToken());
        cities = new int[V][V];

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (i != j) {
                    cities[i][j] = INF;
                }
            }
        }

        for (int i = 0; i < E; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int distance = Integer.parseInt(tokenizer.nextToken());
            cities[a - 1][b - 1] = distance;
        }

        for (int middle = 0; middle < V; middle++) {
            for (int start = 0; start < V; start++) {
                if (middle == start) {
                    continue;
                }
                for (int end = 0; end < V; end++) {
                    if (start == end || middle == end) {
                        continue;
                    }
                    cities[start][end] = Math.min(cities[start][end], cities[start][middle] + cities[middle][end]);
                }
            }
        }

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (i == j) {
                    continue;
                }

                if (cities[i][j] != INF && cities[j][i] != INF) {
                    answer = Math.min(answer, cities[i][j] + cities[j][i]);
                }
            }
        }

        System.out.println(answer == INF ? -1 : answer);
    }

    static void print(int[][] cities) {
        for (int i = 0; i < cities.length; i++) {
            for (int j = 0; j < cities.length; j++) {
                System.out.print(cities[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
