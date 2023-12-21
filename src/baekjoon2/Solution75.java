package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 17219 비밀번호 찾기
 */
public class Solution75 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        StringBuilder answer = new StringBuilder();
        Map<String, String> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            map.put(split[0], split[1]);
        }

        for (int i = 0; i < m; i++) {
            String readLine = bufferedReader.readLine();
            answer.append(map.get(readLine)).append("\n");
        }
        System.out.println(answer);
    }
}
