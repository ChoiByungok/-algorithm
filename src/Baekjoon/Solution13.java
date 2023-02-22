package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * ATM 11399
 */
public class Solution13 {
    public static void main(String[] args) throws IOException {
        int min = 0;
        int result = 0;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bufferedReader.readLine());
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        List<Integer> person = new ArrayList<>();
        while (stringTokenizer.hasMoreTokens()){
            person.add(Integer.parseInt(stringTokenizer.nextToken()));
        }
        Collections.sort(person);
        for (int i = 0; i < person.size(); i++) {
            min += person.get(i);
            person.set(i, min);
            result += person.get(i);
        }
        System.out.println(result);
    }
}
