package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 1620 나는야 포켓몬 마스터 이다솜
 * 하나의 맵으로 깔끔하게 해결 할 수 없을까 고민하다가 value 값으로 key 값을 찾으려면 stream을 돌리는 수 밖에 없어가지고
 * 시간초과 나버려서 그냥 두개의 자료구조로 해결 함
 */
public class Solution72 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] split = bufferedReader.readLine().split(" ");
        StringBuilder answer = new StringBuilder();
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);
        String[] pokemonString = new String[n + 1];
        Map<String, Integer> pokemonMap = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            String name = bufferedReader.readLine();
            pokemonString[i] = name;
            pokemonMap.put(name, i);
        }

        for (int i = 0; i < m; i++) {
            String readLine = bufferedReader.readLine();
            char c = readLine.charAt(0);
            if (c >= '1' && c <= '9') {
                int num = Integer.parseInt(readLine);
                answer.append(pokemonString[num]).append("\n");
            } else {
                answer.append(pokemonMap.get(readLine)).append("\n");
            }
        }

        System.out.println(answer);
    }
}
