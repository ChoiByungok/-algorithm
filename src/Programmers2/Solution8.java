package Programmers2;

/**
 * 문자열 밀기
 */
public class Solution8 {
    public int solution(String A, String B) {
        boolean push = false;
        int i = 1;
        if(A.equals(B)){
            return 0;
        }

        for (; i <= A.length(); i++) {
            String s = pushString(A);
            A = s;
            if(s.equals(B)){
                push = true;
                break;
            }
        }
        return push ? i : -1;
    }
    public String pushString(String s){
        StringBuilder sb = new StringBuilder(s);
        char c = sb.charAt(sb.length() - 1);
        sb.delete(sb.length() - 1, sb.length());
        sb.insert(0,c);
        System.out.println(sb);
        return sb.toString();
    }

    public static void main(String[] args) {
        String A = "hello";
        String B = "ohell";

        System.out.println(new Solution8().solution(A,B));
    }
}
