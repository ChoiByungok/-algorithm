package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 15723 n단 논법 (Silver1)
 * a->b b->c 이면 a->c이다
 * 를 풀어야 하는 문제
 * 입력받은 알파벳을 숫자로 바꾼뒤 boolean형 배열에 true 값을 집어넣는다.
 * a->b 일시 boolean[0][1] 값을 true로 바꿔준다.
 * 그렇게 입력받고 플로이드 와샬 알고리즘을 진행한다.
 */
public class Solution1 {
    static boolean[][] map;
    static int N;
    public static void main(String[] args) throws IOException {
        StringBuilder answer = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        map = new boolean[26][26];
        for (int i = 0; i < N; i++) {
            String readLine = bufferedReader.readLine();
            int a = readLine.charAt(0) - 'a';
            int b = readLine.charAt(readLine.length() - 1) - 'a';
            map[a][b] = true;
        }

        floyd();
        int M = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < M; i++) {
            String readLine = bufferedReader.readLine();
            int a = readLine.charAt(0) - 'a';
            int b = readLine.charAt(readLine.length() - 1) - 'a';
            if (map[a][b]) {
                answer.append("T").append("\n");
            } else {
                answer.append("F").append("\n");
            }
        }
        System.out.println(answer);
    }


    static void floyd() {
        for (int mid = 0; mid < 26; mid++) {
            for (int start = 0; start < 26; start++) {
                for (int end = 0; end < 26; end++) {
                    if (start == end || start == mid || mid == end) {
                        continue;
                    }
                    if (map[start][mid] && map[mid][end]) {
                        map[start][end] = true;
                    }
                }
            }
        }
    }

}
