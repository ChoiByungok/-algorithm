package Programmers3;

import java.util.Arrays;
import java.util.Collections;

/**
 * 숫자 짝꿍
 * 1. 문자열의 각 자리를 분할해서 해당 숫자를 0~9 까지 index를 담을 수 있는 배열에 담음
 * 2. 두 문자열에서 공통된 숫자중 조합 해서 가장 큰 숫자를 반환해야 하니 index의 마지막 값부터 내림차순으로 반복함
 * 3. 두 배열을 비교해서 둘다 0 이상이면 중복된 숫자이므로 해당 조건문에 맞으면 그 숫자를 담고 해당 배열의 값을 내림
 * 4. 반복문을 마친 뒤 예외처리를 함 값이 "" 이면 "-1" 0으로 시작하면 "0" 반환해줌
 */
public class Solution17 {
    public String solution(String X, String Y) {
        StringBuilder answer = new StringBuilder();
        /*StringBuilder x = new StringBuilder(X);
        StringBuilder y = new StringBuilder(Y);

        for (int i = 0; i < y.length(); i++) {
            for (int j = 0; j < x.length(); j++) {
                if (y.charAt(i) == x.charAt(j)) {
                    answer.append(y.charAt(i));
                    x.deleteCharAt(j);
                    break;
                }
            }
        }
        char[] chars = answer.toString().toCharArray();
        Character[] characters = new Character[chars.length];

        for (int i = 0; i < chars.length; i++) {
            characters[i] = chars[i];
        }
        Arrays.sort(characters, Collections.reverseOrder());
        String string = Arrays.toString(characters);
        String s = string.replaceAll("[^0-9]", "");

        if (s.equals("")) {
            s = "-1";
        }
        int integer = Integer.parseInt(s);

        return String.valueOf(integer);*/
        int[] xArr = new int[10];
        int[] yArr = new int[10];

        for (String x : X.split("")) {
            xArr[Integer.parseInt(x)]++;
        }
        for (String y : Y.split("")) {
            yArr[Integer.parseInt(y)]++;
        }
        for (int i = 9; i >= 0; i--) {
            while (xArr[i] > 0 && yArr[i] > 0) {
                answer.append(i);
                xArr[i]--;
                yArr[i]--;
            }
        }
        if (answer.toString().equals("")) {
            return "-1";
        }
        if (answer.toString().startsWith("0")){
            return "0";
        }
        return answer.toString();
    }

    public static void main(String[] args) {
        String[] X = {"100", "100", "100", "12321", "5525"};
        String[] Y = {"2345", "203045", "123450", "42531", "1255"};
        for (int i = 0; i < X.length; i++) {
            System.out.println(new Solution17().solution(X[i], Y[i]));
        }
    }
}
