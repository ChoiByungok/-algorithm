package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1541 잃어버린 괄호
 */
public class Solution39 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String readLine = bufferedReader.readLine();
        StringTokenizer tokenizer = new StringTokenizer(readLine, "-");
        int answer = -100000;
        while (tokenizer.hasMoreTokens()) {
            int sum = 0;
            String[] split = tokenizer.nextToken().split("\\+");
            for (String s : split) {
                sum += Integer.parseInt(s);
            }
            if (answer == -100000) {
                answer = sum;
            } else {
                answer -= sum;
            }
        }
        System.out.println(answer);
    }
}
