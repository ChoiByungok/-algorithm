package Programmers;


import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 영어가 싫어요
 */
public class Solution45 {
    public long solution(String numbers) {
        String[] number = {"zero","one","two","three","four","five","six","seven","eight","nine"};
        Map<String,Integer> map = new LinkedHashMap<>();
        for(int i = 0; i < number.length; i++){
            map.put(number[i],i);
        }

        for(Map.Entry<String,Integer> entry : map.entrySet()){
            if(numbers.contains(entry.getKey())){
                numbers = numbers.replaceAll(entry.getKey(),String.valueOf(entry.getValue()));
            }
        }

        return Long.parseLong(numbers);
    }

    public static void main(String[] args) {
        String numbers = "onefourzerosixseven";
        System.out.println(new Solution45().solution(numbers));
    }
}
