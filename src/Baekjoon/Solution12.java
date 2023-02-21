package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;


/**
 * 동전 0 11047
 */
public class Solution12 {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int N = 0;
        int K = 0;
        int result = 0;

        List<Integer> list = new ArrayList<>();
        
        while (stringTokenizer.hasMoreTokens()){
            N = Integer.parseInt(stringTokenizer.nextToken());
            K = Integer.parseInt(stringTokenizer.nextToken());

            for (int i = 0; i < N; i++) {
                int coin = Integer.parseInt(bufferedReader.readLine());
                list.add(coin);
            }
        }
        Collections.reverse(list);
        int index = 0;
        while (K != 0){
            if(K >= list.get(index)){
                result += (K / list.get(index));
                K %= list.get(index);
            }
            index++;
        }
        System.out.println(result);
    }
}
