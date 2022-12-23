package Programmers;

import java.util.*;

/**
 * 소인수분해
 */
public class Solution47 {
    public int[] solution(int n) {
        List<Integer> prime = new ArrayList<>();
        Map<Integer,Integer> factorization = new LinkedHashMap<>();
        List<Integer> answer = new ArrayList<>();
        boolean primeCheck = true;

        for (int i = 2; i <= n ; i++) {
            primeCheck = true;
            for (int j = 2; j < i ; j++) {
                if(i % j == 0){
                    primeCheck =false;
                    break;
                }
            }
            if(primeCheck){
                prime.add(i);
            }
        }
        if(prime.get(prime.size()-1) == n){ // 인자값이 소수일 경우
            return new int[]{n};
        }
        int i = 0;
        while (n != 1){
            if(n % prime.get(i) == 0){
                n /= prime.get(i);
                factorization.put(prime.get(i),factorization.getOrDefault(prime.get(i),0)+1);
                i--;
            }
            i++;
        }
        for(Map.Entry<Integer,Integer> entry : factorization.entrySet()){
            answer.add(entry.getKey());
        }
        return answer.stream().mapToInt(x -> x).toArray();
    }

    public static void main(String[] args) {
        int n = 10000;
        System.out.println(Arrays.toString(new Solution47().solution(n)));
    }
}
