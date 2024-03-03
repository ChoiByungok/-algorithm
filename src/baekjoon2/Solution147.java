package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 10431 줄세우기 (Silver5)
 * 처음에는 객체를 만들어서 멤버변수로 키랑 몇번 움직였나 스텝이라는 변수를 두고 계산을 하려고 했는데 보니깐
 * 현재 리스트의 사이즈에서 앞에 키큰 사람이 한명이라도 있을때 그중 제일 앞에 있는 사람의 인덱스값을 뺀 후
 * 누적합을 해주면 되는거였다. 실버5라서 조금 쉽게 봤다가 생각보다 어려웠던 문제
 */
public class Solution147 {
    public static void main(String[] args) throws IOException {
        StringBuilder answer = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        for (int i = 1; i <= N; i++) {
            int sum = 0;
            List<Integer> list = new LinkedList<>();
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int t = Integer.parseInt(tokenizer.nextToken());
            while (tokenizer.hasMoreTokens()) {
                int height = Integer.parseInt(tokenizer.nextToken());
                boolean sort = false;
                for (int j = 0; j < list.size(); j++) {
                    if (list.get(j) > height) {
                        list.add(j, height);
                        sum += list.size() - 1 - j;
                        sort = true;
                        break;
                    }
                }
                if (!sort) {
                    list.add(height);
                }
            }
            answer.append(t).append(" ").append(sum).append("\n");
        }
        System.out.println(answer);
    }
}
