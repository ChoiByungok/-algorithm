package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 11659 구간 합 구하기 4
 */
public class Solution34 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();

        String[] nm = bufferedReader.readLine().split(" ");
        List<Integer> list = new ArrayList<>(Integer.parseInt(nm[0]));
        int repeat = Integer.parseInt(nm[1]);

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        list.add(Integer.parseInt(stringTokenizer.nextToken()));
        int index = 1;

        while (stringTokenizer.hasMoreTokens()) {
            list.add(index, list.get(index - 1) + Integer.parseInt(stringTokenizer.nextToken()));
            index++;
        }
        for (int i = 0; i < repeat; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            int min = Integer.parseInt(split[0]) - 1;
            int max = Integer.parseInt(split[1]) - 1;
            if (min == 0) {
                stringBuilder.append(list.get(max)).append("\n");
            } else {
                stringBuilder.append(list.get(max) - list.get(min - 1)).append("\n");
            }
        }

        System.out.println(stringBuilder);
    }
}
