package baekjoon3;

import java.io.*;
import java.util.*;

/**
 * 1991 트리 순회 (Silver1)
 * 트리 순회 3종류 전위 중위 후위 순회를 연습하기 위해 풀어본 문제
 */
public class Solution61 {
    static List<ArrayList<String>> tree = new ArrayList<>();
    static StringBuilder answer = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            int root = tokenizer.nextToken().charAt(0) - 'A';
            String left = tokenizer.nextToken();
            String right = tokenizer.nextToken();
            tree.get(root).add(left);
            tree.get(root).add(right);
        }

        preOrder(0);
        answer.append("\n");

        inOrder(0);
        answer.append("\n");

        postOrder(0);

        System.out.println(answer);
    }

    static void preOrder(int root) {
        String left = tree.get(root).get(0);
        String right = tree.get(root).get(1);

        answer.append((char)(root + 'A'));

        if (!left.equals(".")) {
            preOrder(left.charAt(0) - 'A');
        }

        if (!right.equals(".")) {
            preOrder(right.charAt(0) - 'A');
        }
    }

    static void inOrder(int root) {
        String left = tree.get(root).get(0);
        String right = tree.get(root).get(1);

        if (!left.equals(".")) {
            inOrder(left.charAt(0) - 'A');
        }

        answer.append((char)(root + 'A'));

        if (!right.equals(".")) {
            inOrder(right.charAt(0) - 'A');
        }
    }

    static void postOrder(int root) {
        String left = tree.get(root).get(0);
        String right = tree.get(root).get(1);

        if (!left.equals(".")) {
            postOrder(left.charAt(0) - 'A');
        }

        if (!right.equals(".")) {
            postOrder(right.charAt(0) - 'A');
        }

        answer.append((char)(root + 'A'));
    }
}
