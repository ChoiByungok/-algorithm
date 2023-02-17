package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 17609 회문
 */
public class Solution11 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int repeat = Integer.parseInt(bufferedReader.readLine());
        int result;

        for (int i = 0; i < repeat; i++) {
            StringBuilder line = new StringBuilder(bufferedReader.readLine());
            StringBuilder reverse = new StringBuilder(line).reverse();
            result = 2;
            if (line.toString().equals(reverse.toString())) {
                result = 0;
            } else {
                int left = 0;
                int right = line.length() - 1;
                while (left < right) {
                    if (line.charAt(left) != line.charAt(right)) {
                        StringBuilder delLeft = new StringBuilder(line).deleteCharAt(left);
                        StringBuilder delRight = new StringBuilder(line).deleteCharAt(right);

                        if (delLeft.toString().equals(delLeft.reverse().toString()) ||
                                delRight.toString().equals(delRight.reverse().toString())) {
                            result = 1;
                        }
                        break;
                    }
                    left++;
                    right--;
                }
            }
            System.out.println(result);
        }
    }
}
