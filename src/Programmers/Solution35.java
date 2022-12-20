package Programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 진료 순서정하기
 */
public class Solution35 {
    public int[] solution(int[] emergency) {
        List<Integer> list = new ArrayList<>();
        int[] answer = new int[emergency.length];
        for(Integer value : emergency){
            list.add(value);
        }
        list.sort(Collections.reverseOrder());
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < emergency.length; j++) {
                if(list.get(i) == emergency[j]){
                    answer[j] = i+1;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] emergency = {30, 10, 23, 6, 100};
        System.out.println(Arrays.toString(new Solution35().solution(emergency)));
    }
}
