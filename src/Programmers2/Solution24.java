package Programmers2;

import java.util.*;

/**
 * [1차] 캐시
 */
public class Solution24 {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        List<String> list = new ArrayList<>();
        if (cacheSize == 0) {
            return  cities.length * 5;
        }

        for (String s : cities) {
            String city = s.toLowerCase();
            if (list.contains(city)) {
                list.remove(city);
                list.add(city);
                answer += 1;
            } else {
                if (list.size() == cacheSize) {
                    list.remove(0);
                    list.add(cacheSize - 1, city);
                } else {
                    list.add(city);
                }
                answer += 5;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int cacheSize = 3;
        String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};

        System.out.println(new Solution24().solution(cacheSize, cities));
    }
}
