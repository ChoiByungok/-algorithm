package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * 25192 인사성 밝은 곰곰이
 */
public class Solution36 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Set<String> set = new HashSet<>();
        int answer = 0;
        int repeat = Integer.parseInt(bufferedReader.readLine());
        bufferedReader.readLine();
        for (int i = 0; i < repeat - 1; i++) {
            String readLine = bufferedReader.readLine();
            if (readLine.equals("ENTER")) {
                answer += set.size();
                set.clear();
                continue;
            }
            set.add(readLine);
        }
        answer += set.size();
        System.out.println(answer);
    }
}
