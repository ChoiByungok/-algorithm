package Programmers3;

import java.util.*;

/**
 * 가장 큰수
 */
public class Solution18 {
    static class StringNumber {
        private String numbers;

        public StringNumber (String numbers) {
            this.numbers = numbers;
        }
        public String getNumbers() {
            return numbers;
        }
    }
    public String solution(int[] numbers) {
        StringBuilder answer = new StringBuilder();
        List<StringNumber> list = new ArrayList<>();
        for (int number : numbers) {
            String s = String.valueOf(number);
            StringNumber stringNumber = new StringNumber(s);
            list.add(stringNumber);
        }
        list.sort((o1, o2) -> (o2.getNumbers() + o1.getNumbers()).compareTo(o1.getNumbers() + o2.getNumbers()));
        for (StringNumber stringNumber : list) {
            answer.append(stringNumber.getNumbers());
        }
        return answer.toString().charAt(0) != '0' ? answer.toString() : "0";
    }

    public static void main(String[] args) {
        int[] numbers1 = {6, 10, 2};
        int[] numbers2 = {3, 30, 34, 5, 9};
        int[] numbers3 = {70, 0, 0, 0, 0};
        int[] numbers4 = {0, 0, 0, 0};

        System.out.println(new Solution18().solution(numbers3));
    }
}
