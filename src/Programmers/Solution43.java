package Programmers;

/**
 * 숨어있는 숫자의 덧셈 (2)
 */
public class Solution43 {
    public int solution(String my_string) {
        int answer = 0;
        String s = "";
        for (int i = 0; i < my_string.length(); i++) {
            if(my_string.charAt(i) < 65){
                s += my_string.charAt(i);
            }else {
                s += 's';
            }
        }
        String[] split = s.split("s");
        for (int i = 0; i < split.length; i++) {
            if(split[i].equals("")){
                continue;
            }
            answer += Integer.parseInt(split[i]);
        }
        return answer;
    }

    public static void main(String[] args) {
        String my_string = "1a2b3c4d123Z";
        System.out.println(new Solution43().solution(my_string));
        String[][] s = new String[3][];
    }
}
