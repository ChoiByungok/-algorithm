package Programmers2;

import java.util.*;

/**
 * 할인 행사
 * 남의 코드 배껴옴 슬라이딩 윈도우 방식을 채택했다는데 그게 뭔지 모르겠고
 * 로그찍어보고 디버깅 돌려보면서 간신히 이해했지만
 * 이런유형의 또 다른 문제 나오면 절대 못풀듯
 */
public class Solution50 {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;

        Map<String, Integer> matchMap = new HashMap<>();
        for(int i = 0; i < want.length; i++) {
            matchMap.put(want[i], i);
        }
        int total = Arrays.stream(number).sum();
        int ptr1 = 0;
        int ptr2 = total - 1;

        int[] saleNum = new int[number.length];
        for(int i = 0; i < total; i++) {
            if(matchMap.containsKey(discount[i])) {
                saleNum[matchMap.get(discount[i])]++;
            }
        }
        while(true) {
            if(checkMatch(number, saleNum))
                answer++;
            if(matchMap.containsKey(discount[ptr1])) {
                saleNum[matchMap.get(discount[ptr1])]--;
            }
            ptr1++;
            ptr2++;
            if(ptr2 == discount.length) {
                break;
            }
            if(matchMap.containsKey(discount[ptr2])) {
                saleNum[matchMap.get(discount[ptr2])]++;
            }
        }
        return answer;
    }
    Boolean checkMatch(int[] number, int[] saleNum) {
        for(int i = 0; i < number.length; i++) {
            if(number[i]>saleNum[i])
                return false;
        }
        return true;
    }
    public static void main(String[] args) {
        String[] want1 = {"banana", "apple", "rice", "pork", "pot"};
        int[] number1 = {3, 2, 2, 2, 1};
        String[] discount1 = {"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"};

        String[] want2 = {"apple"};
        int[] number2 = {10};
        String[] discount2 = {"banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana"};

        System.out.println(new Solution50().solution(want1, number1, discount1));
    }
}
