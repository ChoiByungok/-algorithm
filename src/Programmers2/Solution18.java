package Programmers2;

/**
 * 크기가 작은 부분문자열
 */

public class Solution18 {

    public int solution(String t, String p) {
        int count = 0;
        int length = p.length() - 1;
        long number = Long.parseLong(p);

        for (int i = 0; i + length < t.length(); i++) {
            long sub = Long.parseLong(t.substring(i, i + length + 1));
            if(number >= sub) {
                count ++;
            }
        }
        
        return count;
    }

    public static void main(String[] args) {

        String t = "500220839878";
        String p = "7";

        System.out.println(new Solution18().solution(t, p));
    }
}

