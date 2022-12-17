package Programmers;

/**
 * 점프와 순간 이동
 */
public class Solution32 {
    public int solution(int n) {
        int ans = 0;
        int i = 0;
        while (n != 0){
            int pow = (int) Math.pow(2, i);
            if(pow > n){
                n = n - (int)Math.pow(2,i-1);
                i = -1;
                ans ++;
            }
            i++;
        }

        return ans;
    }

    public static void main(String[] args) {
        int N = 5000;


        System.out.println(new Solution32().solution(N));

        String s = "abc";
        String s1 = "a";
        StringBuilder s2 = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if(String.valueOf(s.charAt(i)).equals(s1)){
                continue;
            }
            s2.append(s.charAt(i));
        }
        System.out.println(s2);


    }
}
