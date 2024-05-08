package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 2961 도영이가 만든 맛있는 음식 (Silver2)
 * 쉽게 접근했다가 생각보다 신경써야 할게 많았던 문제
 * 우선 몇개중에 몇개를 선택해라 이런문제가 아니라 1가지 이상의 재료를 사용하여
 * 모든 경우의수를 판별해내야 한다.
 * 나는 우선 입력받으면서 1가지재료만 사용하였을대 값을 미리 계산하였고
 * 그 이후에 2가지 이상의 재료를 사용한 경우는 재귀반복을 돌면서 모든 경우의수를 체크하였다.
 * 재료가 4가지가 있다고 가정했을때 12 123 1234 124 13 134 14 순으로 경우의 수를 계산해주었다.
 */
public class Solution14 {
    static class Ingredient {
        int sour;
        int bitter;

        public Ingredient(int sour, int bitter) {
            this.sour = sour;
            this.bitter = bitter;
        }
    }
    static int answer = Integer.MAX_VALUE;
    static Ingredient[] ingredients;
    static boolean[] visited;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());

        ingredients = new Ingredient[N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            int sour = Integer.parseInt(split[0]);
            int bitter = Integer.parseInt(split[1]);
            ingredients[i] = new Ingredient(sour, bitter);
            answer = Math.min(answer, Math.abs(sour - bitter));
        }
        for (int i = 0; i < ingredients.length; i++) {
            visited[i] = true;
            backTracking(1, ingredients[i].sour, ingredients[i].bitter, i + 1);
            visited[i] = false;
        }
        System.out.println(answer);
    }

    static void backTracking(int count, int sourMul, int bitterSum, int start) {
        if (count > 1) {
            answer = Math.min(answer, Math.abs(sourMul - bitterSum));
        }

        for (int i = start; i < ingredients.length; i++) {
            if (!visited[i]) {
                Ingredient ingredient = ingredients[i];
                int sour = ingredient.sour;
                int bitter = ingredient.bitter;
                visited[i] = true;
                backTracking(count + 1, sourMul * sour, bitterSum + bitter, i + 1);
                visited[i] = false;
            }
        }
    }
}
