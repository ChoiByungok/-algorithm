package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 알람 시계 2884
 */
public class Solution7 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        List<Integer> list = new ArrayList<>();
        while (stringTokenizer.hasMoreTokens()){
            list.add(Integer.parseInt(stringTokenizer.nextToken()));
        }
        Integer hour = list.get(0);
        Integer min = list.get(1);

        if(min < 45){
            if(hour == 0){
                hour = 24;
            }
            min = min + 15;
            hour--;
        }else{
            min = min - 45;
        }
        System.out.println(hour + " " + min );
    }
}
