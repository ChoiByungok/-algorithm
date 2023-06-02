package Programmers2;

import java.util.*;

/**
 * 주차 요금 계산
 * 기본요금 + {(이용시간 - 기본시간) / 단위시간 }x 단위요금 = 최종금액
 */
public class Solution47 {
    public int[] solution(int[] fees, String[] records) {
        Map<String, Integer> cars = new HashMap<>();
        Map<String, Integer> fee = new HashMap<>();
        Map<String, String> history = new HashMap<>();
        for (String record : records) {
            String[] s = record.split(" ");
            String s1 = s[0].replaceAll("[^0-9]", "");
            int hour = Integer.parseInt(s1.charAt(0) + String.valueOf(s1.charAt(1)));
            int min = Integer.parseInt(s1.charAt(2) + String.valueOf(s1.charAt(3)));
            int totalMin = (hour * 60) + min;
            System.out.println("번호판 = " + s[1] + " hour = " + hour + " min = " + min + " 내역 = " + s[2]);
            history.put(s[1], s[2]);
            if (s[2].equals("IN")) {
               cars.put(s[1], totalMin);
            } else if (s[2].equals("OUT")) {
                Integer in = cars.get(s[1]);
                int x = totalMin - in;
                System.out.println("번호판 " + s[1] + " 머무른시간 = " + x);
                fee.put(s[1], fee.getOrDefault(s[1], 0) + x);
            }
        }
        Set<String> strings = cars.keySet();
        int maxTime = (23 * 60) + 59;
        for (String string : strings) {
            String s = history.get(string);
            if (s.equals("IN")) {
                Integer integer = cars.get(string);
                int i = maxTime - integer;
                Integer integer1 = fee.get(string);
                if (integer1 == null) {
                    integer1 = 0;
                }
                int i1 = i + integer1;
                fee.put(string, i1);
            }
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<>(fee.entrySet());
        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return Integer.parseInt(o1.getKey()) - Integer.parseInt(o2.getKey());
            }
        });
        System.out.println("cars = " + cars);
        System.out.println("history = " + history);
        System.out.println("list = " + list);
        int[] answer = new int[list.size()];
        int index = 0;
        for (Map.Entry<String, Integer> stringIntegerEntry : list) {
            Integer value = stringIntegerEntry.getValue();
            if (value <= fees[0]) {
                answer[index] = fees[1];
                index++;
                continue;
            }
            int i = value - fees[0];
            if (i % fees[2] != 0) {
                int i1 = i % fees[2];
                i = i + (fees[2] - i1);
            }
            int totalPrice = fees[1] + (i / fees[2]) * fees[3];
            answer[index] = totalPrice;
            index++;
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] fees1 = {180, 5000, 10, 600};
        String[] records1 = {"05:34 5961 IN",
                            "06:00 0000 IN",
                            "06:34 0000 OUT",
                            "07:59 5961 OUT",
                            "07:59 0148 IN",
                            "18:59 0000 IN",
                            "19:09 0148 OUT",
                            "22:59 5961 IN",
                            "23:00 5961 OUT"};

        int[] fees2 = {120, 0, 60, 591};
        String[] records2 = {"16:00 3961 IN",
                             "16:00 0202 IN",
                             "18:00 3961 OUT",
                             "18:00 0202 OUT",
                             "23:58 3961 IN"};

        int[] fees3 = {1, 461, 1, 10};
        String[] records3 = {"00:00 1234 IN"};

        System.out.println(Arrays.toString(new Solution47().solution(fees2, records2)));
    }
}
