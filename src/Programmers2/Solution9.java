package Programmers2;

import java.util.*;

/**
 * 특이한 정렬
 */
public class Solution9 {
    public int[] solution(int[] numlist, int n) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int value : numlist) {
            map.put(value,Math.abs(n - value));
        }
        List<Map.Entry<Integer,Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                if(Objects.equals(o1.getValue(), o2.getValue())){
                    return o2.getKey().compareTo(o1.getKey());
                }
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        return list.stream().mapToInt(Map.Entry::getKey).toArray();
    }

    public static void main(String[] args) {
        int[] numlist1 = {1, 2, 3, 4, 5, 6};
        int n1 = 4;

        int[] numlist2 = {10000,20,36,47,40,6,10,7000};
        int n2 = 30;

        int[] numlist3 = {1,3,5,7,9,11};
        int n3 = 7;
        System.out.println(Arrays.toString(new Solution9().solution(numlist1,n1)));
    }
}
