package Programmers;

/**
 * 이진수 더하기
 */
public class Solution42 {
    public String solution(String bin1, String bin2) {
        return Integer.toBinaryString(binaryToInt(bin1) + binaryToInt(bin2));
    }
    public int binaryToInt(String binary){
        int answer = 0;
        StringBuilder stringBuilder = new StringBuilder(binary);
        stringBuilder.reverse();
        for (int i = 0; i < stringBuilder.length(); i++) {
            if(stringBuilder.charAt(i) == '1') {
                answer += (int) Math.pow(2, i);
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        String bin1 = "10";
        String bin2 = "110";
        System.out.println(new Solution42().solution(bin1,bin2));
    }
}
