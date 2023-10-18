package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 9184 신나는 함수 실행
 */
public class Solution11 {
    static int[][][] memory = new int[21][21][21];
    public static void main(String[] args) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine.equals("-1 -1 -1")) {
                break;
            }
            String[] split = readLine.split(" ");
            int a = Integer.parseInt(split[0]);
            int b = Integer.parseInt(split[1]);
            int c = Integer.parseInt(split[2]);

            stringBuilder
                    .append("w(").append(a)
                    .append(", ").append(b)
                    .append(", ").append(c)
                    .append(") = ").append(recursion(a, b, c)).append("\n");

        }
        System.out.println(stringBuilder);
    }

    private static int recursion(int a, int b, int c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            return 1;
        } else if (a > 20 || b > 20 || c > 20) {
            return recursion(20, 20, 20);

        } else if (memory[a][b][c] != 0) {
            return memory[a][b][c];

        } else if (a < b && b < c) {
            memory[a][b][c] = recursion(a, b, c-1)
                    + recursion(a, b-1, c-1)
                    - recursion(a, b-1, c);
            return memory[a][b][c];

        } else {
            memory[a][b][c] = recursion(a-1, b, c)
                    + recursion(a-1, b-1, c)
                    + recursion(a-1, b, c-1)
                    - recursion(a-1, b-1, c-1);
            return memory[a][b][c];
        }
    }
}
