package Programmers;
/**
 * 두 개 뽑아서 더하기
 */

import java.util.*;

public class Solution7 {
    public int[] solution(int[] numbers) {

//        List<Integer> list = new ArrayList<>();
//
//        for (int i = 0; i < numbers.length; i++) {
//            for (int j = i+1; j < numbers.length; j++) {
//                if(!list.contains(numbers[i]+numbers[j])){
//                    list.add(numbers[i]+numbers[j]);
//                }
//            }
//        }
//        Collections.sort(list);
//
//        int[] answer = new int[list.size()];
//        for (int i = 0; i < answer.length; i++) {
//            answer[i] = list.get(i);
//        }
        Set<Integer> set = new TreeSet<>();
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i+1; j < numbers.length; j++){
                set.add(numbers[i]+numbers[j]);
            }
        }
        System.out.println(set);
        int[] answer = new int[set.size()];
        Object[] objects = set.toArray();
        System.out.println(Arrays.toString(objects));
        for (int i = 0; i < objects.length; i++) {
            answer[i] = (int) objects[i];
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution7 solution7 = new Solution7();
        int[] numbers = {2,1,3,4,1};

        System.out.println(Arrays.toString(solution7.solution(numbers)));
    }
}
