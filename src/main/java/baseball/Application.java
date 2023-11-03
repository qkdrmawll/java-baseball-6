package baseball;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        boolean gameRestartFlag = true;
        System.out.println("숫자 야구 게임을 시작합니다.");
        while (gameRestartFlag){
            game();
            System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
            if (!readLine().equals("1")) {
                gameRestartFlag = false;
            }
        }
    }

    private static void game() {
        boolean flag = false;
        List<Integer> number = generateRandomNumber();
        System.out.print(number.get(0));
        System.out.print(number.get(1));
        System.out.println(number.get(2));
        while (!flag) {
            System.out.print("숫자를 입력해주세요 : ");
            String inputNumber = readLine();
            if (inputNumber.length()!=3) {
                throw new IllegalArgumentException();
            }
            int[] inputSplitNumber = splitNumber(inputNumber);
            int[] result = result(number, inputSplitNumber);
            if (result[1] == 3) {
                flag = true;
            }
            for (int i=0;i<2;i++) {
                //System.out.println(result[i]);
            }
            printResult(result);
        }
    }

    private static void printResult(int[] result) {
        if (result[0]!=0) {
            System.out.print(result[0] + "볼 ");
        }
        if (result[1]!=0) {
            System.out.print(result[1] +"스트라이크 ");
            if (result[1]==3) {
                System.out.print("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
            }
        }
        if (result[0]==0 && result[1]==0) {
            System.out.print("낫싱");
        }
        System.out.println();
    }

    private static int[] result(List<Integer> computerNumber, int[] inputNumber) {
        int[] result = new int[2];
        for (int i=0;i<inputNumber.length;i++) {
            if (computerNumber.contains(inputNumber[i])){
                int bs = ballOrStrike(computerNumber, inputNumber[i], i);
                result[bs]++;
            }
        }
        return result;
    }

    private static int ballOrStrike(List<Integer> computerNumber, int number, int index) {
        if (computerNumber.get(index) == number) {
            return 1;
        }
        return 0;
    }

    private static List<Integer> generateRandomNumber() {
        List<Integer> computer = new ArrayList<>();
        while (computer.size() < 3) {
            int randomNumber = Randoms.pickNumberInRange(1,9);
            if (!computer.contains(randomNumber)) {
                computer.add(randomNumber);
            }
        }
        return computer;
    }

    private static int[] splitNumber(String inputNumber) {
        int[] number = new int[3];
        for (int i=0;i<3;i++) {
            number[i] = inputNumber.charAt(i)-48;
        }
        return number;
    }
}
