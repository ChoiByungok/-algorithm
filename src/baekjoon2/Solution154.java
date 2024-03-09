package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 3048 개미 (Silver4)
 * 어떻게 풀어야 할지 고민하다가
 * 그냥 문자와 방향 정보를 가지고 있는 클래스 배열을 만들어서 해결함
 * -> 방향으로 가는 개미는 true <- 방향으로 가는 개미는 false 임
 * 배열을 초기화 시킨 후 T번 반복하여
 * 배열을 탐색함 현재개미가 -> 방향인데 그 다음 개미가 <- 방향이면 둘이 바꿔줌
 * 이때 주의해야 할 점은 바꿀때 인덱스 값을 1증가 시켜야 함
 */
public class Solution154 {
    static class Alphabet {
        char aChar;
        boolean dir;

        public Alphabet(char aChar, boolean dir) {
            this.aChar = aChar;
            this.dir = dir;
        }
    }
    public static void main(String[] args) throws IOException {
        StringBuilder answer = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] split = bufferedReader.readLine().split(" ");
        int N1 = Integer.parseInt(split[0]);
        int N2 = Integer.parseInt(split[1]);
        Alphabet[] alphabets = new Alphabet[N1 + N2];
        String n1 = bufferedReader.readLine();
        String n2 = bufferedReader.readLine();

        int index = 0;
        for (int i = n1.length() - 1; i >= 0; i--) {
            alphabets[index++] = new Alphabet(n1.charAt(i), true);
        }

        for (int i = 0; i < n2.length(); i++) {
            alphabets[index++] = new Alphabet(n2.charAt(i), false);
        }

        int T = Integer.parseInt(bufferedReader.readLine());

        while (T-- > 0) {
            for (int i = 0; i < alphabets.length - 1; i++) {
                if (alphabets[i].dir && !alphabets[i + 1].dir) {
                    Alphabet temp = alphabets[i];
                    alphabets[i] = alphabets[i + 1];
                    alphabets[i + 1] = temp;
                    i++;
                }
            }
        }

        for (Alphabet alphabet : alphabets) {
            answer.append(alphabet.aChar);
        }

        System.out.println(answer);
    }
}
