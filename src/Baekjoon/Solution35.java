package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 2559 수열
 */
public class Solution35 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] split = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(split[0]);
        int K = Integer.parseInt(split[1]);
        Queue<Integer> queue = new LinkedList<>();

        int sum = 0;
        int max = (-100 * K) - 1;
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        while (stringTokenizer.hasMoreTokens()) {
            int number = Integer.parseInt(stringTokenizer.nextToken());
            queue.add(number);
            if (queue.size() > K) {
                Integer poll = queue.poll();
                sum = sum - poll + number;
            } else {
                sum += number;
            }
            if (queue.size() == K) {
                if (sum > max) {
                    max = sum;
                }
            }
        }
        System.out.println(max);
    }
}
