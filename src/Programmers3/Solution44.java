package Programmers3;

import java.util.Arrays;

/**
 * 길 찾기 게임 Lv.3
 * 처음에는 문제 설명란에 이차원 배열이 존재하고 막 좌표 설명하고 그러길래
 * 2차원 배열을 하나 만들어서 해결해보려고 했다.
 * 그런데 아래 레벨의 노드가 존재할 때 그 노드가 상위 어떤 노드의 자식인지 구분 할 방법이 생각이 안나서
 * 결국 다른사람의 풀이를 보았다. y좌표는 트리의 레벨이라고 생각하고 x좌표로 부모의 왼쪽 서브트리인지 오른쪽 서브트리인지 구분을 해야하는 문제였다.
 * 결국 이진트리 문제이므로 y좌표값이 중복없이 가장 큰 좌표가 있을것이고 그 노드가 루트가된다. 그러므로 입력값을 먼저 정렬한다.
 * 그 후엔 부모노드를 기준으로 x좌표를 비교해가며 이진트리를 채운다.
 * 채운뒤에는 전위순회랑 후위순회를 해야하는데 이건 예전에 해본적이 있어서 간단하게 했다.
 * 문제 이해능력이랑 접근방법을 잘못짚어서 조금 해멨던 문제였다.
 */
public class Solution44 {
    static class Node {
        int num;
        int x;
        int y;
        Node right;
        Node left;

        public Node(int num, int x, int y, Node right, Node left) {
            this.num = num;
            this.x = x;
            this.y = y;
            this.right = right;
            this.left = left;
        }
    }
    static int index;
    public int[][] solution(int[][] nodeinfo) {
        int[][] answer = new int[2][nodeinfo.length];
        Node[] nodes = new Node[nodeinfo.length];
        for (int i = 0; i < nodeinfo.length; i++) {
            int x = nodeinfo[i][0];
            int y = nodeinfo[i][1];
            nodes[i] = new Node(i + 1, x, y, null, null);
        }

        Arrays.sort(nodes, (o1, o2) -> {
            if (o1.y == o2.y) {
                return o1.x - o2.x;
            }
            return o2.y - o1.y;
        });

        Node parent = nodes[0];
        for (int i = 1; i < nodeinfo.length; i++) {
            Node child = nodes[i];
            insert(parent, child);
        }
        index = 0;
        preOrder(parent, answer);
        index = 0;
        postOrder(parent, answer);
        return answer;
    }

    public void insert(Node parent, Node child) {
        if (parent.x > child.x) {
            if (parent.left == null) {
                parent.left = child;
            } else {
                insert(parent.left, child);
            }
        } else if (parent.x < child.x) {
            if (parent.right == null) {
                parent.right = child;
            } else {
                insert(parent.right, child);
            }
        }
    }

    public void preOrder(Node root, int[][] answer) {
        answer[0][index++] = root.num;
        if (root.left != null) {
            preOrder(root.left, answer);
        }
        if (root.right != null) {
            preOrder(root.right, answer);
        }
    }

    public void postOrder(Node root, int[][] answer) {
        if (root.left != null) {
            postOrder(root.left, answer);
        }

        if (root.right != null) {
            postOrder(root.right, answer);
        }

        answer[1][index++] = root.num;
    }

    public static void main(String[] args) {
        Solution44 solution44 = new Solution44();
        int[][] nodeinfo = new int[][] {{5,3}, {11,5}, {13,3}, {3,5}, {6,1}, {1,3}, {8,6}, {7,2}, {2,2}};
        System.out.println(Arrays.deepToString(solution44.solution(nodeinfo)));
    }
}
