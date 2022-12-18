package Programmers;

import java.util.*;

/**
 * 최빈값 구하기
 */

public class Solution33 {
    public int solution(int[] array) {
        if(array.length == 1){
            return array[0];
        }
        int count = 0;
        int answer = 0;

        Map<Integer,Integer> map = new HashMap<>();
        for (int value : array) {
            map.put(value, map.getOrDefault(value, 0) + 1);
        }
        int[] values = map.values().stream().mapToInt(i -> i).toArray();
        int max = -1;
        for(int value : values){
            if(max < value){
                max = value;
            }
        }
        for(Integer key : map.keySet()){
            if(map.get(key) == max){
                count ++;
                answer = key;
            }
        }

        return count == 1 ? answer : -1;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 3, 2};
        System.out.println(new Solution33().solution(array));
    }

}
