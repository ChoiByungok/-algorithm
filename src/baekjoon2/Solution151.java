package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 20546 기적의 매매법 (Silver5)
 * 성민이의 매매법이 살짝 햇갈렸던 문제
 * 다 풀고 나니깐 클래스들의 구현법만 다르고 시그니쳐는 같음
 * 뭔가 깔끔하게 인터페이스 선언해서 풀었으면 멋있었을 듯
 * 그리고 매수 매도 하는 것도 중복되는데 해당 로직을 구현한 클래스를 하나 만들었으면 깔끔햇을듯
 */
public class Solution151 {
    static class JunHyeon {
        int money;
        int quantity;
        public JunHyeon(int money, int quantity) {
            this.money = money;
            this.quantity = quantity;
        }

        public void trade(int stock) {
            int q = money / stock;
            money = money - (q * stock);
            quantity += q;
        }

        public int asset(int stock) {
            return money + (quantity * stock);
        }
    }
    static class SungMin {
        int money;
        int quantity;
        int increase;
        int decrease;
        int stock;
        public SungMin(int money, int quantity) {
            this.money = money;
            this.quantity = quantity;
        }

        public void trade(int stock) {
            if (this.stock < stock) {
                increase++;
                decrease = 0;
            } else if (this.stock > stock) {
                decrease ++;
                increase = 0;
            }
            this.stock = stock;
            if (increase >= 3) {
                money += quantity * stock;
                quantity = 0;
            }

            if (decrease >= 3) {
                int q = money / stock;
                money = money - (q * stock);
                quantity += q;
            }
        }

        public int asset(int stock) {
            return money + (quantity * stock);
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int money = Integer.parseInt(bufferedReader.readLine());
        JunHyeon junHyeon = new JunHyeon(money, 0);
        SungMin sungMin = new SungMin(money, 0);
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        int stock = 0;
        while (tokenizer.hasMoreTokens()) {
            stock = Integer.parseInt(tokenizer.nextToken());
            junHyeon.trade(stock);
            sungMin.trade(stock);
        }

        int asset1 = junHyeon.asset(stock);
        int asset2 = sungMin.asset(stock);

        if (asset1 > asset2) {
            System.out.println("BNP");
        } else if (asset1 == asset2) {
            System.out.println("SAMESAME");
        } else {
            System.out.println("TIMING");
        }
    }
}
