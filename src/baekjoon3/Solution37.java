package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 10159 저울 (Gold4)
 * 주어진 간선만으로 정확하게 알 수 없는 정점의 무게를 출력하는 문제
 * 일단 방향그래프이며 각 노드가 정점과 직간접적으로 연결되어있는가 판단하여
 * 풀어야 할거같은데 그 너머는 어떻게 접근해야할지 가늠이 안잡힘
 * 얼마전에 풀었던 키 순서(2458) 과 비슷한 문제인거같음
 * 우선 플로이드 워셜 알고리즘을 이용하여 간접적으로 연결되어있는 노드들도 행렬에 표시시켜줌
 * 해당 문제도 간선의 가중치가 존재하지 않기 때문에 그냥 boolean형으로 선언하여 연결되어 있는지 아닌지만 판별하면됨
 * 그렇게 간접적으로 연결되어있는 노드들도 행렬에 표시한 후
 * 이제 정확하게 알 수 없는 정점의 무게를 계산하면 되는데
 * 완성된 행렬가지고 현재좌표 i, j 와 j, i 둘중에 하나라도 true이면 카운트를 증가시켜준다.
 * 그리고 전체 노드의 갯수 N 에서 카운트를 빼면 그것이 알 수 없는 정점의 무게인것이다.
 */
public class Solution37 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int N = Integer.parseInt(bufferedReader.readLine());
        int M = Integer.parseInt(bufferedReader.readLine());
        boolean[][] matrix = new boolean[N][N];
        for (int i = 0; i < M; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            int a = Integer.parseInt(split[0]);
            int b = Integer.parseInt(split[1]);
            matrix[a - 1][b - 1] = true;
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (matrix[i][k] && matrix[k][j]) {
                        matrix[i][j] = true;
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            int check = 1;
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] || matrix[j][i]) {
                    check++;
                }
            }
            answer.append(N - check).append("\n");
        }
        System.out.println(answer);
    }
}
