package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 11404 플로이드 (Gold4)
 * 우선 제목부터 알수있듯이 플로이드-워셜? 알고리즘을 사용해야 하는데 처음들어봤다.
 * 어쨋든 x 도시에서 y 도시로 갈 수 있는 방법중 가장 비용이 적은 값을 출력해야 하므로
 * 중복되는 경로에서 비용이 큰 값은 제거해야 한다.
 * 우선 입력받을때 이것부터 처리해야 할거같다.
 * 나는 맵을 이용해서 이 문제를 처리하였다.
 * 이제 입력받은 정보를 통해서 그래프 탐색을 진행하면 될거같다.
 * 우선 플로이드 워셜이 뭔지 모르니 bfs로 한번 풀어보았다. 그랬더니 1퍼센트에서 시간초과가 발생하였다.
 * 내방법대로 하면 무조건 시간초과가 발생한다 그럼 플로이드 워셜이 뭔지 배우고 적용시켜봐야 할거같다.
 * 다른사람의 풀이를 한번 봤는데 내코드는 애초에 방향성부터 틀린문제 완전히 갈아엎고 다시시작해야 할듯
 * 우선 플로이드 워셜이란 모든 정점에서 최단 경로를 구하는 알고리즘이다.
 * 비슷한 알고리즘으로 다익스트라가 있는데 다익스트라는 하나의 정점에서 하나의 정점까지 최단거리를 구하는 알고리즘이다.
 * 정점이 5개라면 총 5라운드를 진행하게 되는데
 * 3중 반복문을 사용하기때문에 시간복잡도는  O(n^3) 이 되게 된다. 그러므로 정점의 수가 작을때만 사용가능하다.
 * 제일 바깥쪽 반복문의 값은 중간노드의 값 이고 안쪽의 두 반복문은 출발노드와 목적노드이다.
 * 즉 안쪽 반복문의 두 정점이 바깥쪽 반복문의 정점를 거쳐서 갈 수 있을때 그 값이 배열안에 초기화된 값보다 작으면 갱신시켜주면 되는것이다.
 * 이렇게 총 정점의 수만큼 반복문을 진행하게 되면 모든 정점의 최단거리를 구할 수 있는것이다.
 * 얼추 이해했지만 다른사람의 풀이를 보고 풀었기때문에 비슷한 유형의 문제를 2~3문제 내 힘으로 풀어봐야 할거같다.
 */
public class Solution32 {
    static int N;
    static int[][] answer;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        int m = Integer.parseInt(bufferedReader.readLine());
        answer = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i != j) {
                    answer[i][j] = 987654321;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            int from = Integer.parseInt(tokenizer.nextToken());
            int to = Integer.parseInt(tokenizer.nextToken());
            int cost = Integer.parseInt(tokenizer.nextToken());
            answer[from - 1][to - 1] = Math.min(answer[from - 1][to - 1], cost);
        }


        for (int middle = 0; middle < N; middle++) {
            for (int start = 0; start < N; start++) {
                for (int end = 0; end < N; end++) {
                    if (answer[start][end] > answer[start][middle] + answer[middle][end]) {
                        answer[start][end] = answer[start][middle] + answer[middle][end];
                    }
                }
            }
        }
        print();
    }

    static void print() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                builder.append(answer[i][j] == 987654321 ? 0 : answer[i][j]).append(" ");
            }
            builder.append("\n");
        }
        System.out.println(builder);
    }
}
