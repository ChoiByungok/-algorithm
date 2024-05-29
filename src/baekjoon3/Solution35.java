package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2660 회장뽑기 (Gold5)
 * 드디어 플로이드 워셜 문제를 다른사람의 풀이없이 스스로 풀어보았다.
 * 우선 직접 연결되어 있는 정점들사이의 가중치는 1로 초기화를 시켜놓는다.
 * 그리고 플로이드 워셜 알고리즘을 이용하여 간접적으로 연결되어있는 정점들 사이의 가중치를 계산한다.
 * 모든 정점들은 연결되어 있다고 했으니 인접행렬에는 자기자신을 제외한 모든 칸에 가중치가 들어갔을것이다.
 * 그리고 각 행마다 오름차순을 한다. 그러면 맨 마지막 열이 결국 그 후보의 점수가 된다.
 * 그러면 이제 출력을 해야하는데 이게 생각보다 어려웠다.
 * 나는 인접행렬을 돌면서 후보점수중 가장 작은 점수가 나타나면 카운트를 1로 초기화시키고
 * 그 후보의 번호를 리스트에 넣었다. 물론 리스트에 넣기전에 리스트를 비우는 작업을 해야한다.
 * 그리고 현재 갱신된 가장 작은 후보점수와 같은 점수의 후보가 나오게 된다면 카운트를 증가시키고 후보번호를 리스트에 넣어준다.
 * 그렇게 인접행렬을 다 순회하고 나면 후보점수와 후보의 수 그리고 후보의 번호가 리스트에 담기게 되는데
 * 이 정보들을 StringBuilder에 담아서 출력시켜주면 된다.
 */
public class Solution35 {
    static final int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int V = Integer.parseInt(bufferedReader.readLine());
        int [][] candidates = new int[V][V];

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (i != j) {
                    candidates[i][j] = INF;
                }
            }
        }

        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine.equals("-1 -1")) {
                break;
            }
            String[] split = readLine.split(" ");
            int a = Integer.parseInt(split[0]);
            int b = Integer.parseInt(split[1]);
            candidates[a - 1][b - 1] = 1;
            candidates[b - 1][a - 1] = 1;
        }

        for (int middle = 0; middle < V; middle++) {
            for (int start = 0; start < V; start++) {
                for (int end = 0; end < V; end++) {
                    candidates[start][end] = Math.min(candidates[start][end], candidates[start][middle] + candidates[middle][end]);
                }
            }
        }

        int count = 0;
        int point = 50;
        List<Integer> number = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            Arrays.sort(candidates[i]);
            int cp = candidates[i][candidates.length - 1];
            if (cp < point) {
                point = cp;
                count = 1;
                number.clear();
                number.add(i + 1);
            } else if (cp == point) {
                count++;
                number.add(i + 1);
            }
        }
        answer.append(point).append(" ").append(count).append("\n");
        for (Integer integer : number) {
            answer.append(integer).append(" ");
        }
        System.out.println(answer);
    }

    static void print(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
