package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 1269 대칭 차집합
 */
public class Solution29 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int count;
        Map<String, Boolean> mapA;
        Map<String, Boolean> mapB;

        String[] ab = bufferedReader.readLine().split(" ");
        int a = Integer.parseInt(ab[0]);
        int b = Integer.parseInt(ab[1]);

        mapA = Arrays.stream(bufferedReader.readLine().split(" ")).collect(Collectors.toMap(s -> s, s -> true, (a1, b1) -> b1, LinkedHashMap::new));
        mapB = Arrays.stream(bufferedReader.readLine().split(" ")).collect(Collectors.toMap(s -> s, s -> true, (a1, b1) -> b1, LinkedHashMap::new));

        for (String s : mapA.keySet()) {
            if (mapB.containsKey(s)) {
                mapA.put(s, false);
                mapB.put(s, false);
            }
        }

        count = (int) mapA.values().stream().filter(aBoolean -> aBoolean).count();
        count += mapB.values().stream().filter(aBoolean -> aBoolean).count();
        System.out.println(count);

    }
}
