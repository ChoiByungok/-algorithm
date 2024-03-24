package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 16967 배열 복원하기 (Silver3)
 * 문제 설명이 되게 난해했던 문제
 * Bi,j = Ai,j + Ai-X,j-Y 이 공식을
 * map[i][j] -= map[i - X][j - Y] 이렇게 적용시켜서 풀었음
 */
public class Solution169 {
    public static void main(String[] args) throws IOException {
        StringBuilder answer = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        int H = Integer.parseInt(tokenizer.nextToken());
        int W = Integer.parseInt(tokenizer.nextToken());
        int X = Integer.parseInt(tokenizer.nextToken());
        int Y = Integer.parseInt(tokenizer.nextToken());
        int[][] map = new int[H][W];
        for (int i = 0; i < H; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
        for (int i = X; i < H; i++) {
            for (int j = Y; j < W; j++) {
                map[i][j] -= map[i - X][j - Y];
            }
        }

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                answer.append(map[i][j]).append(" ");
            }
            answer.append("\n");
        }
        System.out.println(answer);
    }
}
