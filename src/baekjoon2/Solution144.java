package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 17780 새로운 게임 (Gold2)
 * 체스판 위에 체스말이 있고 각각의 체스말은 고유의 이동번호와 순차적인 번호가 매겨져있다.
 * 1번부터 K번까지  체스말이 움직이는데 체스말의 이동경로에 체스말이 존재하면 그 위에 체스말이 겹쳐진다.
 * 예를들면 1번 체스말이 -> 방향으로 움직이는데 그 경로에 2번 체스말이 존재한다면 2번 체스말 위에 1번 체스말이 올라간다고 보면 된다.
 * 그리고 체스판에는 흰색 빨간색 파란색이 존재한다.
 * 예를들어 1번말을 업고 있는 2번 체스말이 -> 으로 움직일 때 해당 경로에 3번 체스말이 존재하고 해당칸이 흰색이면
 * 3 1 2 순서대로 체스말이 쌓인다.(이걸보고 나는 큐를 떠올렸고)
 * 1번말을 업고 있는 2번 체스말이 -> 으로 움직일 때 해당 경로에 3번 체스말이 존재하고 해당칸이 빨간색이면
 * 3 2 1 순서대로 체스말이 쌓인다.(이걸보고 나는 스택을 떠올렸다)
 * 움직이려는 칸이 체스판의 범위를 벗어나거나 파란색일 경우 체스말의 방향을 반대로 바꾸고 반대방향으로 움직이는데
 * 이때 반대방향도 체스판을 벗어나는 범위거나 파란색일 경우 그냥 방향만 바꾸고 가만히 있는다.
 * 이렇게 1~K번 까지 체스말을 움직이면 1회반복한 것이다.
 * 계속 반복하다가 체스말이 4칸이상 쌓이는 순간 반복문을 탈출하고 반복횟수를 출력하면된다.
 * (나는 처음에 이 문구를 보지못해 체스말이 K개 만큼 쌓여야 탈출하는줄 알고 해멨다.)
 * 1000번 이상 반복했는데도 불구하고 4칸이상 쌓이지 않았다면 -1을 출력하면 된다.
 * 움직이려는 칸이 흰색일 경우에는 큐처럼 행동하고 움직이려는 칸이 빨간색일경우 스택처럼 행동한다.
 * 그러므로 나는 덱을 사용하였다. Board 라는 클래스를 만들었고 멤버변수에는 에는 Piece 라는 객체를 담을 수 있는 덱 자료구조와
 * 해당 칸의 색깔 정보를 담고 있는 color 변수를 두었다.
 * Piece 클래스는 멤버변수로 체스말의 고유번호를 담고있는 num 과 이동방향을 담고있는 dir 이 존재한다.
 * Board 클래스로 N * N 크기의 2차원 배열 map 을 만들었고
 * Piece 의 위치정보를 입력받아 해당 map 위치 덱 자료구조에 넣었으며
 * 하라는 대로 반복문을 실행하여 알고리즘을 진행하였다.
 * while 반복문을 포함하여 4중반복을 수행하고 있으며 그 내부에서도 if 문 분기와 switch 분기문이 뒤 섞여있어서
 * 굉장히 가독성이 좋지는 않지만 그래도 풀었다는것에 의의를 두고싶다.
 */
public class Solution144 {
    static class Board {
        Deque<Piece> deque = new ArrayDeque<>();
        int color;

        public Board(int color) {
            this.color = color;
        }

        @Override
        public String toString() {
            return (deque.isEmpty() ? "[]" : deque) + (color == 0 ? " w" : color == 1 ? " r" : " b");
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
    static Board[][] map;
    static int N;
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
                map[i][j] = new Board(Integer.parseInt(tokenizer.nextToken()));
            }
        }

        for (int i = 0; i < K; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());
            int dir = Integer.parseInt(tokenizer.nextToken());
            Piece piece = new Piece(i + 1, dir);
            map[x - 1][y - 1].deque.offerFirst(piece);
        }

        label:
        while (true) {
            answer++;
            for (int i = 1; i <= K; i++) {
                label2:
                for (int j = 0; j < N; j++) {
                    for (int k = 0; k < N; k++) {
                        Board board = map[j][k];
                        if (!board.deque.isEmpty()) {
                            if (board.deque.peekFirst().num == i) {
                                int dir = board.deque.peekFirst().dir;
                                switch (dir) {
                                    case 1:
                                        if (k + 1 < N) {
                                            if (map[j][k + 1].color == 0) {
                                                while (!board.deque.isEmpty()) {
                                                    map[j][k + 1].deque.offerLast(board.deque.pollFirst());
                                                }
                                                if (map[j][k + 1].deque.size() >= 4) {
                                                    break label;
                                                }
                                            } else if (map[j][k + 1].color == 1) {
                                                while (!board.deque.isEmpty()) {
                                                    map[j][k + 1].deque.offerLast(board.deque.pollLast());
                                                }
                                                if (map[j][k + 1].deque.size() >= 4) {
                                                    break label;
                                                }
                                            } else if (map[j][k + 1].color == 2) {
                                                board.deque.peekFirst().dir = 2;
                                                if (k - 1 >= 0) {
                                                    if (map[j][k - 1].color == 0) {
                                                        while (!board.deque.isEmpty()) {
                                                            map[j][k - 1].deque.offerLast(board.deque.pollFirst());
                                                        }
                                                        if (map[j][k - 1].deque.size() >= 4) {
                                                            break label;
                                                        }
                                                    } else if (map[j][k - 1].color == 1) {
                                                        while (!board.deque.isEmpty()) {
                                                            map[j][k - 1].deque.offerLast(board.deque.pollLast());
                                                        }
                                                        if (map[j][k - 1].deque.size() >= 4) {
                                                            break label;
                                                        }
                                                    } else if (map[j][k - 1].color == 2) {
                                                        board.deque.peekFirst().dir = 2;
                                                    }
                                                } else {
                                                    board.deque.peekFirst().dir = 2;
                                                }
                                            }
                                        } else {
                                            board.deque.peekFirst().dir = 2;
                                            if (map[j][k - 1].color == 0) {
                                                while (!board.deque.isEmpty()) {
                                                    map[j][k - 1].deque.offerLast(board.deque.pollFirst());
                                                }
                                                if (map[j][k - 1].deque.size() >= 4) {
                                                    break label;
                                                }
                                            } else if (map[j][k - 1].color == 1) {
                                                while (!board.deque.isEmpty()) {
                                                    map[j][k - 1].deque.offerLast(board.deque.pollLast());
                                                }
                                                if (map[j][k - 1].deque.size() >= 4) {
                                                    break label;
                                                }
                                            } else if (map[j][k - 1].color == 2) {
                                                board.deque.peekFirst().dir = 2;
                                            }
                                        }
                                        break;
                                    case 2:
                                        if (k - 1 >= 0) {
                                            if (map[j][k - 1].color == 0) {
                                                while (!board.deque.isEmpty()) {
                                                    map[j][k - 1].deque.offerLast(board.deque.pollFirst());
                                                }
                                                if (map[j][k - 1].deque.size() >= 4) {
                                                    break label;
                                                }
                                            } else if (map[j][k - 1].color == 1) {
                                                while (!board.deque.isEmpty()) {
                                                    map[j][k - 1].deque.offerLast(board.deque.pollLast());
                                                }
                                                if (map[j][k - 1].deque.size() >= 4) {
                                                    break label;
                                                }
                                            } else if (map[j][k - 1].color == 2) {
                                                board.deque.peekFirst().dir = 1;
                                                if (k + 1 < N) {
                                                    if (map[j][k + 1].color == 0) {
                                                        while (!board.deque.isEmpty()) {
                                                            map[j][k + 1].deque.offerLast(board.deque.pollFirst());
                                                        }
                                                        if (map[j][k + 1].deque.size() >= 4) {
                                                            break label;
                                                        }
                                                    } else if (map[j][k + 1].color == 1) {
                                                        while (!board.deque.isEmpty()) {
                                                            map[j][k + 1].deque.offerLast(board.deque.pollLast());
                                                        }
                                                        if (map[j][k + 1].deque.size() >= 4) {
                                                            break label;
                                                        }
                                                    } else if (map[j][k + 1].color == 2) {
                                                        board.deque.peekFirst().dir = 1;
                                                    }
                                                } else {
                                                    board.deque.peekFirst().dir = 1;
                                                }
                                            }
                                        } else {
                                            board.deque.peekFirst().dir = 1;
                                            if (map[j][k + 1].color == 0) {
                                                while (!board.deque.isEmpty()) {
                                                    map[j][k + 1].deque.offerLast(board.deque.pollFirst());
                                                }
                                                if (map[j][k + 1].deque.size() >= 4) {
                                                    break label;
                                                }
                                            } else if (map[j][k + 1].color == 1) {
                                                while (!board.deque.isEmpty()) {
                                                    map[j][k + 1].deque.offerLast(board.deque.pollLast());
                                                }
                                                if (map[j][k + 1].deque.size() >= 4) {
                                                    break label;
                                                }
                                            } else if (map[j][k + 1].color == 2) {
                                                board.deque.peekFirst().dir = 1;
                                            }
                                        }
                                        break;
                                    case 3:
                                        if (j - 1 >= 0) {
                                            if (map[j - 1][k].color == 0) {
                                                while (!board.deque.isEmpty()) {
                                                    map[j - 1][k].deque.offerLast(board.deque.pollFirst());
                                                }
                                                if (map[j - 1][k].deque.size() >= 4) {
                                                    break label;
                                                }
                                            } else if (map[j - 1][k].color == 1) {
                                                while (!board.deque.isEmpty()) {
                                                    map[j - 1][k].deque.offerLast(board.deque.pollLast());
                                                }
                                                if (map[j - 1][k].deque.size() >= 4) {
                                                    break label;
                                                }
                                            } else if (map[j - 1][k].color == 2) {
                                                board.deque.peekFirst().dir = 4;
                                                if (j + 1 < N) {
                                                    if (map[j + 1][k].color == 0) {
                                                        while (!board.deque.isEmpty()) {
                                                            map[j + 1][k].deque.offerLast(board.deque.pollFirst());
                                                        }
                                                        if (map[j + 1][k].deque.size() >= 4) {
                                                            break label;
                                                        }
                                                    } else if (map[j + 1][k].color == 1) {
                                                        while (!board.deque.isEmpty()) {
                                                            map[j + 1][k].deque.offerLast(board.deque.pollLast());
                                                        }
                                                        if (map[j + 1][k].deque.size() >= 4) {
                                                            break label;
                                                        }
                                                    } else if (map[j + 1][k].color == 2) {
                                                        board.deque.peekFirst().dir = 4;
                                                    }
                                                } else {
                                                    board.deque.peekFirst().dir = 4;
                                                }
                                            }
                                        } else {
                                            board.deque.peekFirst().dir = 4;
                                            if (map[j + 1][k].color == 0) {
                                                while (!board.deque.isEmpty()) {
                                                    map[j + 1][k].deque.offerLast(board.deque.pollFirst());
                                                }
                                                if (map[j + 1][k].deque.size() >= 4) {
                                                    break label;
                                                }
                                            } else if (map[j + 1][k].color == 1) {
                                                while (!board.deque.isEmpty()) {
                                                    map[j + 1][k].deque.offerLast(board.deque.pollLast());
                                                }
                                                if (map[j + 1][k].deque.size() >= 4) {
                                                    break label;
                                                }
                                            } else if (map[j + 1][k].color == 2) {
                                                board.deque.peekFirst().dir = 4;
                                            }
                                        }
                                        break;
                                    case 4:
                                        if (j + 1 < N) {
                                            if (map[j + 1][k].color == 0) {
                                                while (!board.deque.isEmpty()) {
                                                    map[j + 1][k].deque.offerLast(board.deque.pollFirst());
                                                }
                                                if (map[j + 1][k].deque.size() >= 4) {
                                                    break label;
                                                }
                                            } else if (map[j + 1][k].color == 1) {
                                                while (!board.deque.isEmpty()) {
                                                    map[j + 1][k].deque.offerLast(board.deque.pollLast());
                                                }
                                                if (map[j + 1][k].deque.size() >= 4) {
                                                    break label;
                                                }
                                            } else if (map[j + 1][k].color == 2) {
                                                board.deque.peekFirst().dir = 3;
                                                if (j - 1 >= 0) {
                                                    if (map[j - 1][k].color == 0) {
                                                        while (!board.deque.isEmpty()) {
                                                            map[j - 1][k].deque.offerLast(board.deque.pollFirst());
                                                        }
                                                        if (map[j - 1][k].deque.size() >= 4) {
                                                            break label;
                                                        }
                                                    } else if (map[j - 1][k].color == 1) {
                                                        while (!board.deque.isEmpty()) {
                                                            map[j - 1][k].deque.offerLast(board.deque.pollLast());
                                                        }
                                                        if (map[j - 1][k].deque.size() >= 4) {
                                                            break label;
                                                        }
                                                    } else if (map[j - 1][k].color == 2) {
                                                        board.deque.peekFirst().dir = 3;
                                                    }
                                                } else {
                                                    board.deque.peekFirst().dir = 3;
                                                }
                                            }
                                        } else {
                                            board.deque.peekFirst().dir = 3;
                                            if (map[j - 1][k].color == 0) {
                                                while (!board.deque.isEmpty()) {
                                                    map[j - 1][k].deque.offerLast(board.deque.pollFirst());
                                                }
                                                if (map[j - 1][k].deque.size() >= 4) {
                                                    break label;
                                                }
                                            } else if (map[j - 1][k].color == 1) {
                                                while (!board.deque.isEmpty()) {
                                                    map[j - 1][k].deque.offerLast(board.deque.pollLast());
                                                }
                                                if (map[j - 1][k].deque.size() >= 4) {
                                                    break label;
                                                }
                                            } else if (map[j - 1][k].color == 2) {
                                                board.deque.peekFirst().dir = 3;
                                            }
                                        }
                                        break;
                                }
                                break label2;
                            }
                        }
                    }
                }
            }
            if (answer > 1000) {
                answer = -1;
                break;
            }
        }
        System.out.println(answer);
    }
}
