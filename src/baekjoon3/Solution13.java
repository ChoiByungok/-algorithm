package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 16987 계란으로 계란치기 (Gold5)
 * 최근에 풀었던 백트래킹 문제중에서 가장 어려웠던 문제
 * 문제 이해를 잘못해서 한참헤맸음
 * 나는 가장 오른쪽에 있는 계란을 집었을때 깨진 계란의 수를 세고 재귀를 끝내는줄 알았는데
 * 알고보니 가장 최근에 잡은 계란이 가장 오른쪽에 있는 계란이었을때 재귀를 끝내는거였음
 * 가장오른쪽에 있는 계란이 부셔지지 않았거나 다른 계란중 부셔지지 않은 계란이 있으면
 * 그것도 부딪치고 재귀를 종료시켜야 하는거였음
 * 그리고 나는 계란을 다 부딪치고 나서 깨진 계란수를 카운트 하려고 했는데
 * 재귀문제라 디버깅이 어려워서 참 답답한데 계란이 깨졌을때 그때그때 카운트하며
 * 매개변수로 넘기는거와 다 진행하고 나서 깨진 계란의 수를 계산하는거랑 답이 다르게나옴
 * 이 원인은 못찾겠음 다 진행하고나서 깨진계란수 카운트하면 94퍼센트에서 틀렸다고 함
 */
public class Solution13 {
    static class Egg {
        int durability;
        int weight;

        public Egg(int durability, int weight) {
            this.durability = durability;
            this.weight = weight;
        }
    }
    static int N;
    static Egg[] eggs;
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        eggs = new Egg[N];
        for (int i = 0; i < N; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            int durability = Integer.parseInt(split[0]);
            int weight = Integer.parseInt(split[1]);
            eggs[i] = new Egg(durability, weight);
        }
        backTracking(0, 0);
        System.out.println(answer);
    }

    static void backTracking(int index, int count) {
        if (index == N) {
            answer = Math.max(answer, count);
            return;
        }

        if (eggs[index].durability <= 0 || count == N - 1) {
            backTracking(index + 1, count);
            return;
        }

        int tempCount = 0;
        for (int i = 0; i < N; i++) {
            if (i == index) {
                continue;
            }

            if (eggs[i].durability <= 0) {
                continue;
            }

            hit(index, i);
            if (eggs[index].durability <= 0) {
                tempCount++;
            }
            if (eggs[i].durability <= 0) {
                tempCount++;
            }
            backTracking(index + 1, count + tempCount);
            tempCount = reset(index, i);
        }
    }

    static void hit(int holding, int target) {
        eggs[holding].durability -= eggs[target].weight;
        eggs[target].durability -= eggs[holding].weight;
    }

    static int reset(int holding, int target) {
        eggs[holding].durability += eggs[target].weight;
        eggs[target].durability += eggs[holding].weight;
        return 0;
    }
}
