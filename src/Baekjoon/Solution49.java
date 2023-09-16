package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 10816 숫자카드 2
 */
public class Solution49 {
    static List<Map.Entry<Integer, Integer>> list;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        Map<Integer, Integer> map = new HashMap<>();
        int N = Integer.parseInt(bufferedReader.readLine());
        StringTokenizer st1 = new StringTokenizer(bufferedReader.readLine());
        while (st1.hasMoreTokens()) {
            int n = Integer.parseInt(st1.nextToken());
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        list = new ArrayList<>(map.entrySet());
        int M = Integer.parseInt(bufferedReader.readLine());
        arr = new int[M];

        StringTokenizer st2 = new StringTokenizer(bufferedReader.readLine());

        for (int i = 0; i < M; i++) {
            arr[i] = Integer.parseInt(st2.nextToken());
        }

        list.sort(Comparator.comparingInt(Map.Entry::getKey));

        for (int key : arr) {
            int start = 0;
            int end = list.size() - 1;
            while (true) {
                int mid = (start + end) / 2;
                if (list.get(mid).getKey() == key) {
                    answer.append(list.get(mid).getValue()).append(" ");
                    break;
                }
                if (start >= end) {
                    answer.append("0 ");
                    break;
                }

                if (key > list.get(mid).getKey()) {
                    start = mid + 1;
                }

                if (key < list.get(mid).getKey()) {
                    end = mid - 1;
                }
            }
        }
        System.out.println(answer);
    }
}
