package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 21940 가운데에서 만나기 (Gold4)
 * 우선 플로이드 워셜 알고리즘을 사용하여 모든 경로의 최소값을 구하는거까지는 알겠는데
 * 그 이후의 최대의 사람이 최소의 왕복시간을 가지는 도시를 선택해야 한다.
 * 그 도시가 여러개라면 오름차순으로 출력한다.
 * 출력 부분이 좀 까다로운 문제 최소의 왕복시간은 그냥 구하면 되는데
 * 최대인원의 최소왕복시간 도시를 구하는걸 어떻게 해야할까 이게 또 중복이라면 오름차순으로 정렬해야 하는데
 * 어떤 자료구조를 써서 어떤식으로 진행해야 문제에서 원하는 방식으로 출력할 수 있을까
 * 결국 어떻게 출력해야 할지 몰라서 다른사람의 풀이를 보았다.
 * 문제 자체 설명이 모호했다 내가 완전 잘못이해하고 있었다.
 * 이 문제는 정점마다 왕복시간이 제일 큰 친구의집을 저장하고 그중 가장 최솟값을 출력해야 하는 문제다.
 * 이 글을 쓰면서도 사실 이해가 안간다.
 * 테스트 케이스 두번째거 내가 원래 생각했던 방식은
 * 1번집에 사는 친구가 갈 수 있는 도시 중 왕복 시간이 가장 적은 도시들은 2 3이고
 * 2번집에 사는 친구가 갈 수 있는 도시 중 왕복 시간이 가장 적은 도시들은 1 3 이라
 * 답이 3이 나오는거같은데 왜 1 2 3 인지 이해가 가지 않았다.
 * 근데 각 정점에서 친구집에 갈 수 있는 왕복시간의 최대값중에서 가장 최솟값을 구하라 했으니
 * 1번 정점과 2번친구의 왕복값이 3이고, 2번정점과 1번 친구의 왕복값이 3이다. 그리고 3번정점은 1번 친구 2번친구 모두 왕복값이 3이라 3이다.
 * 그렇게 각 정점의 친구집으로 부터 왕복값의 최대값들은 3 3 3이 나온다.
 * 그중 최소값들을 구하라는데 다 값이 같으니 그냥 인덱스 값을 출력하면 된다.
 * 테스트 케이스 1번같은 경우는 각 정점의 왕복 최대값 리스트가 4 4 4 2가 나와서 마지막 2가 최솟값이라 3번째 인덱스 즉 4를 출력하면 되는것이다.
 */
public class Solution2 {
    static final int INF = 99999999;
    static int N;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(map[i], INF);
            map[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(tokenizer.nextToken()) - 1;
            int b = Integer.parseInt(tokenizer.nextToken()) - 1;
            int c = Integer.parseInt(tokenizer.nextToken());
            map[a][b] = c;
        }

        floyd();
        print();
        int K = Integer.parseInt(bufferedReader.readLine());
        ArrayList<Integer> list = new ArrayList<>();
        tokenizer = new StringTokenizer(bufferedReader.readLine());
        for(int i = 0; i < K ; i++){
            list.add(Integer.parseInt(tokenizer.nextToken()) - 1);
        }

        int[] answer = new int[N];
        for(int i = 0; i < N ; i++){
            for(int j = 0; j < K ; j++){
                int idx = list.get(j);
                answer[i] = Math.max(answer[i], map[i][idx] + map[idx][i]);
            }
        }


        list = new ArrayList<>();
        int minValue = Integer.MAX_VALUE;
        for(int i = 0 ; i < N ; i++){
            if(minValue > answer[i]){
                minValue = answer[i];
                list.clear();
                list.add(i + 1);
            }else if(minValue == answer[i]){
                list.add(i + 1);
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int data : list){
            sb.append(data).append(" ");
        }
        System.out.println(sb);
    }

    static void floyd() {
        for (int mid = 0; mid < N; mid++) {
            for (int start = 0; start < N; start++) {
                for (int end = 0; end < N; end++) {
                    if (start == end || start == mid || mid == end) {
                        continue;
                    }
                    map[start][end] = Math.min(map[start][end], map[start][mid] + map[mid][end]);
                }
            }
        }
    }

    static void print() {
        System.out.println();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
