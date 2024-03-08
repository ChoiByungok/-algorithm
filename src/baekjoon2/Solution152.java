package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 17837 새로운 게임2 (Gold2)
 * 예전에 풀었던 새로운 게임(17780)심화버전
 * 그때는 보드위에 겹처져있던 말들이 한번에 우루루 움직이는 거라서
 * 맨 밑에 깔려있는 말의 방향만 알면됐고 중간에 말들은 신경을 쓰지 않아도 되었던 반면
 * 이번에는 모든 말을 전부 신경을 써야하는 문제였다.
 * 그리하여 저번에는 덱을 사용하였지만 이번에는 리스트를 사용하여 풀었다.
 * 조금 특이한건 1 2 3 순으로 말이 쌓여있고 지금 2번 말을 ->방향으로 움직여야 하는 상황이라고 쳤을 떄
 * -> 방향에 4 5 번이 깔려있고 그 블록이 흰색이면 4 5 2 3 으로 깔려야 하고
 * 빨간색이면 4 5 3 2 순으로 깔려야 한다.
 * 그래서 생각보다 꽤 고전했던 문제였다 하지만 1번만에 통과를 할 수 있었다.
 * 다만 저번처험 depth 가 깊어 가독성은 좋지 않은게 단점이다.
 * 그래도 중복되는 코드를 메서드로 뽑아내어 최초로 제출했던 코드보다 코드길이를 2배이상 줄일수 있었다.
 */
public class Solution152 {
    public enum Color {
        WHITE, RED, BLUE;
    }
    static class Board {
        Color color;
        List<Piece> list = new ArrayList<>();

        public Board(Color color) {
            this.color = color;
        }

        @Override
        public String toString() {
            return (list.isEmpty() ? "[]" : list) + (color == Color.WHITE ? "w " : color == Color.RED ? "r " : "b ");
        }
    }

    static class Piece {
        int num;
        int dir;

        public Piece(int num, int dir) {
            this.num = num;
            this.dir = dir;
        }

        @Override
        public String toString() {
            String s = "";
            switch (dir) {
                case 1:
                    s = "→";
                    break;
                case 2:
                    s = "←";
                    break;
                case 3:
                    s = "↑";
                    break;
                case 4:
                    s = "↓";
                    break;
            }
            return num + " " + s;
        }
    }
    static int N;
    static Board[][] map;
    public static void main(String[] args) throws IOException {
        int answer = 0;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());
        map = new Board[N][N];
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                String color = tokenizer.nextToken();
                switch (color) {
                    case "0" :
                        map[i][j] = new Board(Color.WHITE);
                        break;
                    case "1" :
                        map[i][j] = new Board(Color.RED);
                        break;
                    case "2" :
                        map[i][j] = new Board(Color.BLUE);
                        break;
                }
            }
        }

        for (int i = 1; i <= K; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());
            int dir = Integer.parseInt(tokenizer.nextToken());
            Piece piece = new Piece(i, dir);
            map[x - 1][y - 1].list.add(piece);
        }

        label:
        while (true) {
            answer++;
            if (answer > 1000) {
                answer = -1;
                break;
            }
            for (int num = 1; num <= K; num++) {
                label2:
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        Board board = map[i][j];
                        if (!board.list.isEmpty()) {
                            List<Piece> list = board.list;
                            for (int k = 0; k < list.size(); k++) {
                                Piece piece = list.get(k);
                                if (piece.num == num) {
                                    int dir = piece.dir;
                                    if (outOrBlueCheck(i, j ,dir)) {
                                        if (dir == 1) {
                                            piece.dir = 2;
                                        } else if (dir == 2) {
                                            piece.dir = 1;
                                        } else if (dir == 3) {
                                            piece.dir = 4;
                                        } else if (dir == 4) {
                                            piece.dir = 3;
                                        }
                                        if (outOrBlueCheck(i ,j, piece.dir)) {
                                            break label2;
                                        } else {
                                            move(i, j, k, piece.dir);
                                            if (breakCheck(i, j, piece.dir)) {
                                                break label;
                                            }
                                        }
                                    } else {
                                        move(i, j, k, dir);
                                        if (breakCheck(i, j, dir)) {
                                            break label;
                                        }
                                    }
                                    break label2;
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println(answer);
    }

    static public void move(int i, int j, int k, int dir) {
        List<Piece> list = map[i][j].list;
        if (dir == 1 && map[i][j + 1].color == Color.WHITE) {
            int repeat = list.size() - k;
            for (int l = 0; l < repeat; l++) {
                Piece remove = list.get(k);
                map[i][j + 1].list.add(remove);
                list.remove(remove);
            }
            return;
        }

        if (dir == 1 && map[i][j + 1].color == Color.RED) {
            for (int l = list.size() - 1; l >= k; l--) {
                Piece remove = list.get(l);
                map[i][j + 1].list.add(remove);
                list.remove(remove);
            }
            return;
        }

        if (dir == 2 && map[i][j - 1].color == Color.WHITE) {
            int repeat = list.size() - k;
            for (int l = 0; l < repeat; l++) {
                Piece remove = list.get(k);
                map[i][j - 1].list.add(remove);
                list.remove(remove);
            }
            return;
        }

        if (dir == 2 && map[i][j - 1].color == Color.RED) {
            for (int l = list.size() - 1; l >= k; l--) {
                Piece remove = list.get(l);
                map[i][j - 1].list.add(remove);
                list.remove(remove);
            }
            return;
        }

        if (dir == 3 && map[i - 1][j].color == Color.WHITE) {
            int repeat = list.size() - k;
            for (int l = 0; l < repeat; l++) {
                Piece remove = list.get(k);
                map[i - 1][j].list.add(remove);
                list.remove(remove);
            }
            return;
        }

        if (dir == 3 && map[i - 1][j].color == Color.RED) {
            for (int l = list.size() - 1; l >= k; l--) {
                Piece remove = list.get(l);
                map[i - 1][j].list.add(remove);
                list.remove(remove);
            }
            return;
        }

        if (dir == 4 && map[i + 1][j].color == Color.WHITE) {
            int repeat = list.size() - k;
            for (int l = 0; l < repeat; l++) {
                Piece remove = list.get(k);
                map[i + 1][j].list.add(remove);
                list.remove(remove);
            }
            return;
        }

        if (dir == 4 && map[i + 1][j].color == Color.RED) {
            for (int l = list.size() - 1; l >= k; l--) {
                Piece remove = list.get(l);
                map[i + 1][j].list.add(remove);
                list.remove(remove);
            }
        }
    }

    static public boolean outOrBlueCheck(int i, int j, int dir) {
        switch (dir) {
            case 1:
                return j + 1 >= N || map[i][j + 1].color == Color.BLUE;
            case 2:
                return j - 1 < 0 || map[i][j - 1].color == Color.BLUE;
            case 3:
                return i - 1 < 0 || map[i - 1][j].color == Color.BLUE;
            case 4:
                return i + 1 >= N || map[i + 1][j].color == Color.BLUE;
        }
        return false;
    }

    static boolean breakCheck(int i, int j, int dir) {
        switch (dir) {
            case 1:
                return map[i][j + 1].list.size() >= 4;
            case 2:
                return map[i][j - 1].list.size() >= 4;
            case 3:
                return map[i - 1][j].list.size() >= 4;
            case 4:
                return map[i + 1][j].list.size() >= 4;
        }
        return false;
    }
    static public void print() {
        System.out.println();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
}
