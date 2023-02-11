package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 약수구하기 2501
 */
public class Solution9 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        while (stringTokenizer.hasMoreTokens()){
            int number = Integer.parseInt(stringTokenizer.nextToken());
            int rank = Integer.parseInt(stringTokenizer.nextToken());

            List<Integer> list = new ArrayList<>();

            for (int i = 1; i <= number; i++) {
                if(number % i == 0){
                    list.add(i);
                }
            }
            if(list.size() < rank){
                System.out.println(0);
            }else {
                System.out.println(list.get(rank - 1));
            }
        }
    }
}
