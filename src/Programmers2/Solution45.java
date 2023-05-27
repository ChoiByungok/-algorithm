package Programmers2;

import java.util.*;

/**
 * 의상
 */
public class Solution45 {
    public int solution(String[][] clothes) {
        int answer = 1;
        Map<String, Integer> map = new HashMap<>();
        for (String[] clothe : clothes) {
            map.put(clothe[1], map.getOrDefault(clothe[1], 1) + 1);
        }
        for (String s : map.keySet()) {
            answer *= map.get(s);
        }

        return answer - 1;
    }

    public static void main(String[] args) {
        String[][] clothes1 = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};
        String[][] clothes2 = {{"crow_mask", "face"}, {"blue_sunglasses", "face"}, {"smoky_makeup", "face"}};
        String[][] clothes3 = {{"glasses", "eyewear"}, {"sun_glasses", "eyewear"}, {"blue_shirt", "shirt"}, {"jean", "pants"}, {"outerwear", "coat"}};
        String[][] clothes4 = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}, {"red_sunglasses", "eyewear"}};
        Map<String[][], Integer> map = new HashMap<>();
        map.put(clothes1, 5);
        map.put(clothes2, 3);
        map.put(clothes3, 23);
        map.put(clothes4, 8);

        Set<String[][]> strings = map.keySet();
        for (String[][] string : strings) {
            System.out.println(new Solution45().solution(string) == map.get(string));
        }
    }
}
