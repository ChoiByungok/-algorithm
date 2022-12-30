package Programmers2;

import java.util.ArrayList;
import java.util.List;

/**
 * 다항식 더하기
 */
public class Solution5 {
    public String solution(String polynomial) {
        List<String> integer = new ArrayList<>();
        List<String> poly = new ArrayList<>();
        String[] split = polynomial.split(" ");
        for (int i = 0; i < split.length; i++) {
            if(split[i].equals("+")){
                continue;
            }
            if(split[i].contains("x")){
                poly.add(split[i]);
            }else {
                integer.add(split[i]);
            }
        }
        int sum = 0;
        int sumX = 0;
        for (int i = 0; i < integer.size(); i++) {
            sum += Integer.parseInt(integer.get(i));
        }
        for (int i = 0; i < poly.size(); i++) {
            if(poly.get(i).length() == 1){
                sumX += 1;
            }else {
                sumX += Integer.parseInt(poly.get(i).replace("x",""));
            }
        }
        if(integer.size() == 0){
            if(sumX == 1){
                return "x";
            }
            return sumX + "x";
        }
        if(poly.size() == 0){
            return sum + "";
        }
        if(sumX == 1){
            return "x + " + sum;
        }
        return sumX + "x + " + sum;
    }

    public static void main(String[] args) {

        String polynomial1 = "3x + 7 + x";
        String polynomial2 = "x + x + x";

        System.out.println(new Solution5().solution(polynomial1));
    }
}
