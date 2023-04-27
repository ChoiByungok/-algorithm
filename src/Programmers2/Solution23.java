package Programmers2;

/**
 * 커피 심부름
 */
public class Solution23 {
    public int solution(String[] order) {
        int answer = 0;
        for (String coffee : order) {
            if (coffee.contains("americano") || coffee.contains("anything")) {
                answer += 4500;
            } else if (coffee.contains("latte")) {
                answer += 5000;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        String[] order = {"cafelatte", "americanoice", "hotcafelatte", "anything"};
        System.out.println(new Solution23().solution(order));
    }
}
