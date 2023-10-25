package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1932 정수 삼각형
 */
public class Solution18 {
    public static void main(String[] args) throws IOException {
        List<int[]> triangle = new ArrayList<>();
        List<int[]> dp = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < N; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            int[] ints = new int[split.length];
            for (int j = 0; j < split.length; j++) {
                ints[j] = Integer.parseInt(split[j]);
            }
            triangle.add(ints);
        }

        dp.add(triangle.get(0));

        for (int i = 1; i < N; i++) {
            int[] ints = new int[triangle.get(i).length];
            ints[0] = dp.get(i - 1)[0] + triangle.get(i)[0];
            ints[ints.length - 1] = dp.get(i - 1)[dp.get(i - 1).length - 1] + triangle.get(i)[triangle.get(i).length - 1];
            for (int j = 1; j < triangle.get(i).length - 1; j++) {
                ints[j] = Math.max(dp.get(i - 1)[j - 1], dp.get(i - 1)[j]) + triangle.get(i)[j];
            }
            dp.add(ints);
        }

        int[] ints = dp.get(N - 1);
        Arrays.sort(ints);
        System.out.println(ints[ints.length - 1]);
    }
}
