package Programmers3;

/**
 * 문자열 겹쳐쓰기
 * 문자열 my_string, overwrite_string과 정수 s가 주어집니다.
 * 문자열 my_string의 인덱스 s부터 overwrite_string의 길이만큼을 문자열 overwrite_string으로 바꾼 문자열을 return 하는 solution 함수를 작성해 주세요.
 */
public class Solution22 {
    public String solution(String my_string, String overwrite_string, int s) {
        String substring1 = my_string.substring(0, s);
        String substring2 = my_string.substring(overwrite_string.length() + s);
        return substring1 + overwrite_string + substring2;
    }

    public static void main(String[] args) {
        String my_string1 = "He11oWor1d";
        String overwrite_string1 = "lloWorl";
        int s1 = 2;

        String my_string2 = "Program29b8UYP";
        String overwrite_string2 = "merS123";
        int s2 = 7;

        System.out.println(new Solution22().solution(my_string1, overwrite_string1, s1));

    }
}
