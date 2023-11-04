package baseball.service;

import baseball.validation.NumberValidator;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class GameService {
    private static List<Integer> ComputerNumber;
    private final NumberValidator validator = new NumberValidator();
    

    public void setNumber() {
        ComputerNumber = new ArrayList<>();
        while (ComputerNumber.size() < 3) {
            int randomNumber = Randoms.pickNumberInRange(1,9);
            if (!ComputerNumber.contains(randomNumber)) {
                ComputerNumber.add(randomNumber);
            }
        }
    }

    public String play() {
        String inputNumber = readLine();
        validator.validateNumber(inputNumber);
        int[] inputSplitNumber = splitNumber(inputNumber);
        int[] result = result(ComputerNumber, inputSplitNumber);
        return resultToString(result);
    }

    private String resultToString(int[] result) {
        StringBuilder resString = new StringBuilder();
        if (result[0]!=0) {
            resString.append(result[0]).append("볼 ");
        }
        if (result[1]!=0) {
            resString.append(result[1]).append("스트라이크 ");
        }
        if (result[0]==0 && result[1]==0) {
            resString.append("낫싱");
        }
        return resString.toString();
    }

    private static int[] splitNumber(String inputNumber) {
        int[] number = new int[3];
        for (int i=0;i<3;i++) {
            number[i] = inputNumber.charAt(i)-48;
        }
        return number;
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

    public boolean stop() {
        String restartInput = readLine();
        return restartInput.equals("2");
    }
}
