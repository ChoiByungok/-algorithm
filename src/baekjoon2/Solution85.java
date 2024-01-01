package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 2257 화학식량
 * 화학식은 수소(H), 탄소(C), 산소(O)만을 포함하고 있는 것만이 입력 되며 H = 1, C = 12, O = 16 이다
 * (H)2(O) 라는 문자열이 들어왔을 때 H 분자가 2개 O 분자가 1개라 이 문자열의 총 화학식량은 18이다.
 * CH(CO2H)3 라는 문자열이 들어오면 괄호 안에 CO2H는 탄소 분자 1개 산소분자 2개 수소분자 1개 라는 소리이며 총량은 45이다.
 * 근데 그 45가 3개 있으므로 135 이며 앞에 C H 의 값을 각각 더해주면 148이라는 화학식량이 나오게 된다.
 * 여기서 문제는 괄호가 나왔을 때 부분적으로 계산을 해 주어 스택자료구조 안에 넣어주는 게 핵심인데
 * (H)2(O)를 계산하여 스택에 넣으면 스택에는 1 2 16이 들어가게 된다. 여기서 어떤 값이 곱해주어야 하는 값이고
 * 어떤 값이 화학식량의 부분 질량인지 알 방법이 없어서 나는 클래스를 만들어 해당 숫자가 곱해주어야 하는 값인지
 * 아니면 화학식의 부분질량인지 아니면 여는 괄호인지 구분을 해 주었다.
 */
public class Solution85 {
    static class Atom {
        int value;
        boolean atom;
        boolean bracket;

        public Atom(int value, boolean atom, boolean bracket) {
            this.value = value;
            this.atom = atom;
            this.bracket = bracket;
        }

        public boolean isAtom() {
            return atom;
        }

        public boolean isBracket() {
            return bracket;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String readLine = bufferedReader.readLine();
        int answer = 0;
        Stack<Atom> stack = new Stack<>();
        for (int i = 0; i < readLine.length(); i++) {
            char c = readLine.charAt(i);
            if (c != ')') {
                switch (c) {
                    case 'H' :
                        Atom atomH = new Atom(1, true, false);
                        stack.push(atomH);
                        break;
                    case 'C' :
                        Atom atomC = new Atom(12, true, false);
                        stack.push(atomC);
                        break;
                    case 'O' :
                        Atom atomO = new Atom(16, true, false);
                        stack.push(atomO);
                        break;
                    case '(' :
                        Atom bracket = new Atom(0, false, true);
                        stack.push(bracket);
                        break;
                    default:
                        Atom mul = new Atom(Character.getNumericValue(c), false, false);
                        stack.push(mul);
                }
            } else {
                int sum = 0;
                int mul = 0;
                while (!stack.peek().isBracket()) {
                    Atom atom = stack.pop();
                    if (atom.isAtom()) {
                        if (mul != 0) {
                            sum += mul * atom.value;
                            mul = 0;
                        } else {
                            sum += atom.value;
                        }
                    } else {
                        mul = atom.value;
                    }
                }
                stack.pop();
                Atom atom = new Atom(sum, true, false);
                stack.push(atom);
            }
        }
        int mul = 0;
        while (!stack.isEmpty()) {
            Atom atom = stack.pop();
            if (atom.isAtom()) {
                if (mul == 0) {
                    answer += atom.value;
                } else {
                    answer += mul * atom.value;
                    mul = 0;
                }
            } else {
                mul = atom.value;
            }
        }
        System.out.println(answer);
    }
}
