package Programmers2;

/**
 * 치킨 쿠폰
 */
public class Solution12 {
    public int solution(int chicken) {
        int service = 0;
        int coupon = 0;
        while (chicken != 0){
            coupon += chicken % 10;
            service += chicken / 10;
            chicken = chicken / 10;
            System.out.println("coupon = " + coupon + " service = " + service);
        }
        int coupon2 = 0;
        while (coupon != 0){
            coupon2 += coupon % 10;
            service += coupon / 10;
            coupon = coupon / 10;
            System.out.println("coupon2 = " + coupon2 + " service = " + service);
        }
        while (coupon2 != 0){
            service += coupon2 / 10;
            coupon2  =coupon2 / 10;
        }
        return service;
    }

    public static void main(String[] args) {
        int chicken1 = 100;
        int chicken2 = 1081;
        int chicken3 = 1999;
        int chicken4 = 999999;

        System.out.println(new Solution12().solution(chicken4));
    }
}
