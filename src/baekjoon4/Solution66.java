package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 25601 자바의 형변환 (Silver1)
 * String, List<String> 형태의 map 자료구조를 tree형태로 만들어
 * 입력을 받고 탐색을 진행한다. 이때 방향은 단방향이어야 한다.
 * 마지막으로 클래스 2개를 입력받은 뒤 두번 탐색을 진행하여 하나라도 캐스팅이 가능하면 1 아니면 0을 출력한다.
 */
public class Solution66 {
    static Map<String, List<String>> tree = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            String A = tokenizer.nextToken();
            String B = tokenizer.nextToken();
            if (!tree.containsKey(A)) {
                tree.put(A, new ArrayList<>());
            }
            tree.get(A).add(B);
        }

        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        String class1 = tokenizer.nextToken();
        String class2 = tokenizer.nextToken();
        System.out.println(casting(class1, class2) || casting(class2, class1) ? 1 : 0);
    }

    static boolean casting(String class1, String class2) {
        if (class1.equals(class2)) {
            return true;
        }

        if (tree.get(class1) == null) {
            return false;
        }

        for (String cast : tree.get(class1)) {
            if (casting(cast, class2)) {
                return true;
            }
        }

        return false;
    }
}
