package gameLogic;

import java.util.Objects;

public class Card {
        private String number;
        private Character suit;
        private final Integer value;
        private final Boolean isUnknown;
    public Card(String number, char suit) {
        this.suit = suit;
        this.isUnknown = false;
        this.number = number;
        if (isNumeric(number)) {
            this.value = Integer.valueOf(number);
        } else {
            if (Objects.equals(number, "A")) {
                this.value = 11;
            } else {
                this.value = 10;
            }
        }
    }
    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    public Card() {
        this.isUnknown = true;
        this.value = 0;
    }

    public Boolean getUnknown() {
        return isUnknown;
    }

    public Character getSuit() {
        return suit;
    }

    public String getNumber() {
        return number;
    }

    public Integer getValue() {
        return value;
    }
}
