package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1484 다이어트
 */
public class Solution35 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int G = Integer.parseInt(bufferedReader.readLine());
        int now = 2;
        int previous = 1;
        boolean no = false;

        while (now != previous) {
            int v = (int) (Math.pow(now, 2) - Math.pow(previous, 2));
            if (v >= G) {
                if (v == G) {
                    answer.append(now).append("\n");
                    no = true;
                }
                previous++;
            } else {
                now++;
            }
        }
        System.out.println(no ? answer : -1);
    }
}
