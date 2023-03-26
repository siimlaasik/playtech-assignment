package gameLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Hand {
    private final List<Card> cardsInHand = new ArrayList<>();
    public Hand(String cards) {
        for (String cardString : (cards.split("-"))) {
            Card newCard = new Card();
            if (!(Objects.equals(cardString, "?")) && cardString.length() == 2) {
                newCard = new Card(cardString.substring(0, 1), cardString.charAt(1));
            }
            cardsInHand.add(newCard);
        }
    }
    public List<Card> getCardsInHand() {
        return cardsInHand;
    }

    public Integer getValue() {
        int value = 0;
        for (Card card : cardsInHand) {
            value += card.getValue();
        }
        return value;
    }
}
