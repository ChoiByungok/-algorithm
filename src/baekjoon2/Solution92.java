package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 17503 맥주 축제
 * 어떻게 풀어야 할지 고민하다가 1시간 넘어서 결국 답을 보고 푼 문제
 * 선호도와 도수를 입력받아서 도수기준으로 오름차순으로 정렬함 단 도수가 같으면 선호도를 내림차순함
 * 정렬된 맥주들을 순회 하면서 선호도를 누적합함
 * 그와 동시에 우선순위 큐에 선호도를 집어넣어줌 그러면 선호도는 오름차순 정렬될 거임
 * 큐의 사이즈가 축제기간을 넘어서면 큐에서 제일 낮은 선호도롤 제거해줌 그리고 누적합에서도 빼줌
 * 그러다가 큐의 사이즈가 축제기간이랑 같고 원하는 선호도를 넘어서면
 * 그때 나온 맥주의 알콜도수가 답이되는 문제임 왜냐하면 알콜도수로 오름차순 정렬되어 있기 때문
 * 나는 이걸  다중반복문으로 풀어야 하나 넓이우선 탐색으로 풀어야 하나 고민하다가 해결못했는데
 * 이걸 우선순위큐로 푸는걸보고 나는 아직 멀었다 느낌
 */
public class Solution92 {
    static class Beer {
        int preference;
        int alcohol;

        public Beer(int preference, int alcohol) {
            this.preference = preference;
            this.alcohol = alcohol;
        }

        @Override
        public String toString() {
            return "Beer{" +
                    "preference=" + preference +
                    ", alcohol=" + alcohol +
                    '}';
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] nmk = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(nmk[0]); //축제 기간
        int M = Integer.parseInt(nmk[1]); //선호도의 합
        int K = Integer.parseInt(nmk[2]); //맥주의 종류
        int answer = -1;
        int sum = 0;
        Beer[] beers = new Beer[K];
        Queue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < K; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            beers[i] = new Beer(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
        }

        Arrays.sort(beers, (o1, o2) -> {
            if (o1.alcohol == o2.alcohol) {
                return o2.preference - o1.preference;
            }
            return o1.alcohol - o2.alcohol;
        });

        System.out.println(Arrays.toString(beers));

        for (Beer beer : beers) {
            sum += beer.preference;
            queue.add(beer.preference);

            if (queue.size() > N) {
                sum -= queue.poll();
            }

            if (queue.size() == N && sum >= M) {
                answer = beer.alcohol;
                break;
            }
        }
        System.out.println(answer);
    }
}
