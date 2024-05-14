package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 19942 다이어트 (Gold4)
 * 풀면서 딱히 막히지도 않고 쉽게 풀린거같은데 의외로 티어가 높았던문제
 * 재료라는 클래스를 만들고 값들을 집어넣은뒤 재료배열을 선언한다.
 * 그리고 재료배열과 같은길이의 boolean형 배열을 선언하고
 * 방문처리를 하며 백트래킹을 실새해주면 된다. 재료들의 합을 매개변수로 보내준 뒤
 * 비교를 하여 조건을 만족하면 이전에 갱신된 코스트보다 작은지 확인한 후
 * 작으면 StringBuilder를 새로 생성하여 값을 넣어주고 아니면 그냥 return 해준다.
 * 최종적으로 cost가 갱신된적이 있으면 만들어둔 StringBuilder을 출력해주고 아니면 -1을 출력해주면 된다.
 */
public class Solution20 {
    static class Ingredient {
        int protein;
        int fat;
        int carbohydrate;
        int vitamin;
        int cost;

        public Ingredient(int protein, int fat, int carbohydrate, int vitamin, int cost) {
            this.protein = protein;
            this.fat = fat;
            this.carbohydrate = carbohydrate;
            this.vitamin = vitamin;
            this.cost = cost;
        }
    }
    static int N;
    static Ingredient[] ingredients;
    static boolean[] visited;
    static int mp;
    static int mf;
    static int ms;
    static int mv;
    static int cost = Integer.MAX_VALUE;
    static StringBuilder answer;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        ingredients = new Ingredient[N];
        visited = new boolean[N];
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        mp = Integer.parseInt(tokenizer.nextToken());
        mf = Integer.parseInt(tokenizer.nextToken());
        ms = Integer.parseInt(tokenizer.nextToken());
        mv = Integer.parseInt(tokenizer.nextToken());

        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int protein = Integer.parseInt(tokenizer.nextToken());
            int fat = Integer.parseInt(tokenizer.nextToken());
            int carbohydrate = Integer.parseInt(tokenizer.nextToken());
            int vitamin = Integer.parseInt(tokenizer.nextToken());
            int cost = Integer.parseInt(tokenizer.nextToken());
            ingredients[i] = new Ingredient(protein, fat, carbohydrate, vitamin, cost);
        }
        backTracking(0, 0, 0, 0, 0, 0);
        System.out.println(cost != Integer.MAX_VALUE ? answer : -1);
    }

    static void backTracking(int pt, int ft, int ht, int vt, int ct, int start) {
        if (pt >= mp && ft >= mf && ht >= ms && vt >= mv) {
            if (cost > ct) {
                cost = ct;
                answer = new StringBuilder();
                answer.append(cost).append("\n");
                for (int i = 0; i < visited.length; i++) {
                    if (visited[i]) {
                        answer.append(i + 1).append(" ");
                    }
                }
            }
            return;
        }

        for (int i = start; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                int protein = ingredients[i].protein;
                int fat = ingredients[i].fat;
                int carbohydrate = ingredients[i].carbohydrate;
                int vitamin = ingredients[i].vitamin;
                int cost = ingredients[i].cost;
                backTracking(pt + protein, ft + fat, ht + carbohydrate, vt + vitamin, ct + cost, i + 1);
                visited[i] = false;
            }
        }
    }
}
