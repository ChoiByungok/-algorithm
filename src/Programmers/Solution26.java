package Programmers;
import java.util.*;

/**
 * 영어 끝말잇기
 */
public class Solution26 {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        List<String> list = new ArrayList<>();
        list.add(words[0]);
        for (int i = 0; i < words.length-1; i++) {
            if(list.contains(words[i+1])){
                answer[0] = (i+1) % n +1;
                answer[1] = (i+1) / n +1;
                return answer;
            }
            if(words[i].charAt(words[i].length()-1) != words[i+1].charAt(0)){
                answer[0] = (i+1) % n +1;
                answer[1] = (i+1) / n +1;
                return answer;
            }
            list.add(words[i+1]);
        }
        return answer;
    }

    public static void main(String[] args) {

        int n = 3;
        String[] words = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
        String[] words2 = {"hello", "one", "even", "never", "now", "world", "draw"};
        System.out.println(Arrays.toString(new Solution26().solution(n,words)));
    }
}
