package Programmers2;

import java.util.*;

/**
 * 기본요금 + {(이용시간 - 기본시간) / 단위시간 }x 단위요금 = 최종금액
 */
public class Solution47 {
    public int[] solution(int[] fees, String[] records) {
        int[] answer = {};
        Map<String, Integer> cars = new HashMap<>();
        Map<String, Integer> fee = new HashMap<>();
        for (String record : records) {
            String[] s = record.split(" ");
            String s1 = s[0].replaceAll("[^0-9]", "");
            int integer = Integer.parseInt(s1);
            System.out.println("번호판 = " + s[1] + " 시간 = " + integer + " 내역 = " + s[2]);
            if (s[2].equals("IN")) {
                cars.put(s[1], integer);
            } else if (s[2].equals("OUT")) {
                Integer integer1 = cars.get(s[1]);
                int i = integer - integer1;
                fee.put(s[1], fee.getOrDefault(s[1], 0) + i);
            }

        }
        System.out.println("fee = " + fee);

        return answer;
    }

    public static void main(String[] args) {
        int[] fees = {180, 5000, 10, 600};
        String[] records = {"05:34 5961 IN",
                            "06:00 0000 IN",
                            "06:34 0000 OUT",
                            "07:59 5961 OUT",
                            "07:59 0148 IN",
                            "18:59 0000 IN",
                            "19:09 0148 OUT",
                            "22:59 5961 IN",
                            "23:00 5961 OUT"};

        System.out.println(Arrays.toString(new Solution47().solution(fees, records)));
    }
}
