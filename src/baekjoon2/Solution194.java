package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 1759 암호 만들기 (Gold5)
 * 암호는 최소 한 개의 모음(a, e, i, o, u)과 최소 두 개의 자음으로 구성되어 있다고 알려져 있다.
 * 또한 암호를 이루는 알파벳이 암호에서 증가하는 순서로 배열되었을 것이라고 추측된다.
 * 즉, abc는 가능성이 있는 암호이지만 bac는 그렇지 않다.
 * 처음에는 무식하게 모든 조합을 만든 뒤 그 조합이 해당 조건에 맞지 않으면 정답에 출력시키지 않는 방법으로 풀어봤는데
 * 역시나 시간초과가 발생하였다. 저번에 풀었던 치킨배달 (15686) 문제처럼 start 변수를 두고 재귀호출 할때마다 1씩 증가시켜주면
 * 중복이 발생되지 않고 뒤에 문자만 확인하기 때문에 미리 정렬을 진행시켜 놓았다면 항상 오름차순으로 조합되어있을 것이다.
 * 그러면 그 이후에는 해당 단어가 최소 한개의 모음과 두개의 자음으로 구성되어있는지만 확인하면 된다.
 * 확인한 후 조건에 맞으면 출력하고 아니면 출력하지 않으면 된다.
 */
public class Solution194 {
    static int L;
    static int C;
    static boolean[] visited;
    static String[] word;
    static StringBuilder answer = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        L = Integer.parseInt(tokenizer.nextToken());
        C = Integer.parseInt(tokenizer.nextToken());
        word = new String[C];
        visited = new boolean[C];
        tokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < C; i++) {
            word[i] = tokenizer.nextToken();
        }
        Arrays.sort(word);
        permutation(0, 0);
        System.out.println(answer);
    }

    static void permutation(int count, int start) {
        if (count == L && check()) {
            for (int i = 0; i < word.length; i++) {
                if (visited[i]) {
                    answer.append(word[i]);
                }
            }
            answer.append("\n");
            return;
        }

        for (int i = start; i < word.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                permutation(count + 1, i + 1);
                visited[i] = false;
            }
        }
    }

    private static boolean check() {
        int vowel = 0;
        int consonant = 0;
        for (int i = 0; i < word.length; i++) {
            if (visited[i]) {
                String s = word[i];
                if (s.equals("a") || s.equals("e") || s.equals("i") || s.equals("o") || s.equals("u")) {
                    vowel++;
                } else {
                    consonant++;
                }
            }
        }

        return vowel > 0 && consonant >= 2;
    }
}
