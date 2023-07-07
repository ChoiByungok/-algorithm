package Programmers3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * [1차] 뉴스 클러스터링
 * 1. 들어온 문자열을 2글자 씩 끊는다.
 * 2. 대 소문자 구분하지 않는다.
 * 3. 이때 쌍으로 끊은 문자열에 문자가아닌 특수문자나 공백이 포함된 경우 글자 쌍을 버린다.
 * 4. 그렇게 나온 두 문자열 쌍의 교집합과 합집합을 구한다.
 * 5. 자카드 유사도를 구하는 공식은 (교집합 갯수) / (합집합 갯수) 이다 (double)로 형변환 해야함
 * 6. 그렇게 나온 자카드 유사도에 65536을 곱한 뒤 소수점 아래를 버리고 정수값만 반환한다.
 */
public class Solution16 {
    public int solution(String str1, String str2) {
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        List<String> twoLetters1 = new ArrayList<>();
        List<String> twoLetters2 = new ArrayList<>();
        List<String> intersection = new ArrayList<>();
        List<String> union = new ArrayList<>();

        for (int i = 0; i < str1.length() - 1; i++) {
            String substring = str1.substring(i, i + 2);
            if (isPlainText(substring)) {
                twoLetters1.add(substring);
            }
        }
        for (int i = 0; i < str2.length() - 1; i++) {
            String substring = str2.substring(i, i + 2);
            if (isPlainText(substring)) {
                twoLetters2.add(substring);
            }
        }
        Collections.sort(twoLetters1);
        Collections.sort(twoLetters2);

        for (String s : twoLetters1) {
            if (twoLetters2.remove(s)) {
                intersection.add(s);
            }
            union.add(s);
        }
        union.addAll(twoLetters2);

        double jaccard = 0;
        if (union.size() == 0) {
            jaccard = 1;
        } else {
            jaccard = ((double) intersection.size() / (double) union.size());
        }
        return (int) (jaccard * 65536);
    }
    public boolean isPlainText(String str) {
        return str.charAt(0) >= 'a' && str.charAt(0) <= 'z' && str.charAt(1) >= 'a' && str.charAt(1) <= 'z';
    }

    public static void main(String[] args) {
        String[] str1 = {"FRANCE", "handshake", "aa1+aa2", "E=M*C^2"};
        String[] str2 = {"french", "shake hands", "AAAA12", "e=m*c^2"};
        int[] results = {16384, 65536, 43690, 65536};

        for (int i = 0; i < str1.length; i++) {
            System.out.println(new Solution16().solution(str1[i], str2[i]) == results[i]);
        }
    }
}
