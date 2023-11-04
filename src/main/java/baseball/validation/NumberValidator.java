package baseball.validation;

import java.util.HashSet;
import java.util.Set;

public class NumberValidator {
    static private String number;
    public void validateNumber(String inputNumber) {
        number = inputNumber;
        validateNumberSize();
        validateNumberType();
        validateNumberDuplicate();
    }

    private void validateNumberSize() {
        if (number.length()!=3) {
            throw new IllegalArgumentException();
        }
    }
    private void validateNumberType() {
        if (!number.matches("[0-9]+")) {
            throw new IllegalArgumentException();
        }
    }
    private void validateNumberDuplicate() {
        Set<Character> numberSet = new HashSet<>();
        for (char c : number.toCharArray()) {
            numberSet.add(c);
        }
        if (numberSet.size() != number.length()) {
            throw new IllegalArgumentException();
        }
    }

}
