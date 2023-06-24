package Programmers3;
/**
 * 문자열 여러 번 뒤집기
 */
public class Solution10 {
    public String solution(String my_string, int[][] queries) {
        StringBuilder answer = new StringBuilder(my_string);
        for (int[] query : queries) {
            String substring = answer.substring(query[0], query[1] + 1);
            answer.delete(query[0], query[1] + 1);
            StringBuilder stringBuilder = new StringBuilder(substring);
            stringBuilder.reverse();
            answer.insert(query[0], stringBuilder);
        }
        return answer.toString();
    }

    public static void main(String[] args) {
        String my_string = "rermgorpsam";
        int[][] queries = {{2, 3}, {0, 7}, {5, 9}, {6, 10}};

        System.out.println(new Solution10().solution(my_string, queries));
    }
}
