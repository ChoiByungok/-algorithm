package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1091 카드 섞기 (Gold4)
 * 문제 설명이 너무 난해함
 * 내가 이해한건 초기에 주어진 카드 배열이 1 2 0 이고
 * 그걸 한번 섞으면 0 1 2 가 되는데 이후엔 아무리 섞어도 0 1 2 임
 * 그래서 이게 주어진 P 배열인 2 0 1 이 될 수 없음 근데 왜 2번만에 가능한건지 모르겠음
 * 그렇게 이해 못한채로 구글링 하던 도중 진짜 친절하게 문제설명 해주는 블로그를 발견해서 풀었음
 * N이 3이면 P 배열을 0 1 2 로 만들어야 하고 N이 6이면 P 배열을 0 1 2 0 1 2 로 만들어야 하는 문제임
 * 초기 P 배열이 2 0 1 이고 카드 섞는 방법이 담겨져 있는 S 배열이 1 2 0 이면
 * P 배열의 첫번쨰 값 2가 S 배열의 첫번째 값인 1 즉 1번째 위치로 가야함
 * 마찬가지로 0 은 2번째 위치 1 은 첫번째 위치로 가게 됨
 * 그래서 한번 섞으면 P 배열은 2 0 1 에서 1 2 0 이 됨
 * 여기서 한번 더 섞으면 1 2 0 은 0 1 2 가 되므로 다 섞은거임
 * 아무리 섞어도 안되는 경우가 있는데 몇 번 섞은 후 초기 P 배열의 모습으로 돌아오는 경우임
 * 그래서 초기 P 배열을 저장하고 반복문을 돌때마다 한번씩 비교해줬음
 * 구현 자체는 진짜 간단한데 문제 이해하는게 훨씬 어려웠던 문제
 */
public class Solution159 {
    public static void main(String[] args) throws IOException {
        int answer = 0;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        int[] P = new int[N];
        int[] S = new int[N];
        int[] original = new int[N];
        int[] cards = new int[N];
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            P[i] = Integer.parseInt(tokenizer.nextToken());
            original[i] = P[i];
        }

        tokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            S[i] = Integer.parseInt(tokenizer.nextToken());
        }

        for (int i = 0; i < N; i++) {
            cards[i] = i % 3;
        }

        if (!cardCompare(P, cards)) {
            while (true) {
                answer++;
                P = shuffle(P, S);
                if (cardCompare(P, cards)) {
                    break;
                }
                if (cardCompare(P, original)) {
                    answer = -1;
                    break;
                }
            }
        }
        System.out.println(answer);
    }

    private static int[] shuffle(int[] p, int[] s) {
        int[] temp = new int[p.length];
        for (int i = 0; i < p.length; i++) {
            temp[s[i]] = p[i];
        }
        return temp;
    }

    private static boolean cardCompare(int[] p, int[] cards) {
        for (int i = 0; i < p.length; i++) {
            if (p[i] != cards[i]) {
                return false;
            }
        }
        return true;
    }
}
