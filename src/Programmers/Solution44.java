package Programmers;

/**
 * 잘라서 배열로 저장하기
 */
public class Solution44 {
    public String[] solution(String my_str, int n) {
        int len = 0;
        if(my_str.length() % n == 0){
            len = my_str.length() / n;
        }else{
            len = (my_str.length() / n) + 1;
        }
        String[] answer = new String[len];
        int k = 0;
        for (int i = 0; i < my_str.length(); i++) {
            answer[k] += my_str.charAt(i);
            if((i % n) >= n-1){
                k++;
            }
        }
        for (int i = 0; i < answer.length; i++) {
            answer[i] = answer[i].replaceAll("null","");
        }
        return answer;
    }

    public static void main(String[] args) {
        String my_str = "abc1Addfggg4556b";
        int n = 6;
        System.out.println(new Solution44().solution(my_str,n)[1]);
    }
}
