package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 5639 이진 검색 트리 (Gold4)
 * 처음에는 배열로 해결하려고 했다. 그냥 단순하게 노드가 10000개 이하라길래
 * 10001길이의 배열을 생성해서 루트노드의 인덱스를 1로 두고 좌측 노드는 (인덱스 *2) 우측노드는 (인덱스*2 + 1) 로 두고
 * 배열을 채우면 될줄 알았는데 이게 완전 이진트리라면 배열의 범위를 벗어나지 않지만
 * 만약 편향 이진트리라면 배열의 사이즈가 2^10000개가 필요하게 되므로 배열로 푸는건 불가능하다는 결론이 나왔다.
 * 그래서 내가 직접 트리를 만들어서 해결하였다.
 */
public class Solution43 {
    static class Tree {
        int num;
        Tree left;
        Tree right;

        void insert(int num) {
            if (this.num == 0) {
                this.num = num;
                return;
            }

            if (this.num > num) {
                if (this.left == null) {
                    this.left = new Tree();
                    left.insert(num);
                } else {
                    left.insert(num);
                }
                return;
            }

            if (this.num < num) {
                if (this.right == null) {
                    this.right = new Tree();
                    right.insert(num);
                } else {
                    right.insert(num);
                }
            }
        }

        void postOrder() {
            if (this.left != null) {
                left.postOrder();
            }

            if (this.right != null) {
                right.postOrder();
            }

            answer.append(this.num).append("\n");
        }
    }
    static StringBuilder answer = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Tree tree = new Tree();
        while (true) {
            String readLine = bufferedReader.readLine();
            if ("".equals(readLine) || readLine == null) {
                break;
            }
            int num = Integer.parseInt(readLine);
            tree.insert(num);
        }
        tree.postOrder();
        System.out.println(answer);
    }

}
