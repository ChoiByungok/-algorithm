package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 5464 주차장
 * 1.숫자가 양수인지 음수인지 파악할것
 * 2.양수일시 현재 주차장이 꽉찼는지 비어있는지 파악함 만약 꽉 차있다면 큐에 대기시킴
 * 3.주차장이 비어있다면 주차장의 번호가 가장 낮은 곳에 차량을 주차시킴
 * 4.음수가 들어온다면 주차장을 순회하여 입력받은 번호의 차가 주차되어있는 위치를 찾아 차량을 내보냄
 * 5.차량이 나간뒤 큐에 차량이 존재한다면 그 차량을 방금 차량이 나간 위치에 주차시켜줌
 * 해당 문제를 2번이나 틀린이유
 * 양수 음수 구분을 startWith("-") 으로 함
 * 이것까지는 상관없는데 문자열을 숫자로 변환할때 Integer.ParseInt(readLine.charAt(1)) 으로 해버림
 * 차량의 수가 10대가 넘어가면 당연히 틀릴 수 밖에없음 하드코딩의 무서움을 느낌
 */
public class Solution91 {
    static class ParkingLot {
        int carNum;
        int price;
        boolean parking;

        public ParkingLot(int price, boolean parking) {
            this.price = price;
            this.parking = parking;
        }

        public void parkingIn(int carNum) {
            this.carNum = carNum;
            this.parking = true;
        }

        public void parkingOut() {
            this.carNum = 0;
            this.parking = false;
        }

        public int calculate(int weight) {
            return this.price * weight;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(nm[0]); //주차장의 크기
        int M = Integer.parseInt(nm[1]); //차량의 수
        ParkingLot[] parkingLots = new ParkingLot[101];
        int[] cars = new int[2001];
        Queue<Integer> wait = new LinkedList<>();
        int count = 0;
        int answer = 0;

        for (int i = 1; i <= N; i++) {
            parkingLots[i] = new ParkingLot(Integer.parseInt(bufferedReader.readLine()), false);
        }

        for (int i = 1; i <= M; i++) {
            cars[i] = Integer.parseInt(bufferedReader.readLine());
        }

        for (int i = 0; i < 2 * M; i++) {
            int carNum = Integer.parseInt(bufferedReader.readLine());
            if (carNum > 0) { //양수일시
                if (count == N) { // 현재 주차장이 꽉 차있다면
                    wait.offer(carNum); //큐에 대기시킴
                } else { //주차장에 공간이 있다면
                    for (int j = 1; j <= parkingLots.length; j++) { //주차장을 순회하여 가장 낮은 번호에 주차시킴
                        ParkingLot parkingLot = parkingLots[j];
                        if (!parkingLot.parking) {
                            parkingLot.parkingIn(carNum);
                            answer += parkingLot.calculate(cars[carNum]);
                            count++;
                            break;
                        }
                    }
                }
            } else { //음수가 들어온다면
                carNum *= -1;
                int place = 0;
                for (int j = 1; j <= parkingLots.length; j++) { //주차장을 순회하여 차량을 뺌
                    ParkingLot parkingLot = parkingLots[j];
                    if (parkingLot.carNum == carNum) {
                        parkingLot.parkingOut();
                        count--;
                        place = j;
                        break;
                    }
                }
                if (!wait.isEmpty()) { //차량이 나갔는데 큐에 차량이 있다면 그 차량을 방금 그 위치에 주차시켜줌
                    ParkingLot parkingLot = parkingLots[place];
                    Integer car = wait.poll();
                    parkingLot.parkingIn(car);
                    answer += parkingLot.calculate(cars[car]);
                    count++;
                }
            }
        }
        System.out.println(answer);
    }
}
