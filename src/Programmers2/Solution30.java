package Programmers2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * 전화번호 목록
 */
public class Solution30 {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        Set<String> set = new HashSet<>(Arrays.asList(phone_book));
        for (String phone : phone_book) {
            for (int j = 0; j < phone.length(); j++) {
                if (set.contains(phone.substring(0, j))) {
                    return false;
                }
            }
        }

        /**
         * 기존 실패코드 효율성 4번에서 실패함
         */
//        Arrays.sort(phone_book, Comparator.comparingInt(String::length));
//
//        if (phone_book[0].length() == phone_book[phone_book.length - 1].length()) {
//            return true;
//        }
//
//        for (int i = 0; i < phone_book.length - 1; i++) {
//            for (int j = i + 1; j < phone_book.length; j++) {
//                if (phone_book[j].startsWith(phone_book[i])) {
//                    return false;
//                }
//            }
//        }
        return answer;
    }

    public static void main(String[] args) {
        String[] phone_book1 = {"119", "97674223", "1195524421"};
        String[] phone_book2 = {"123", "456", "789"};
        String[] phone_book3 = {"12", "123", "1235", "567", "88"};
        String[] phone_book4 = {"123", "1005", "1006", "1007"};

        System.out.println(new Solution30().solution(phone_book3));
    }
}
