package gameLogic;

public class Round {
    private final Integer timeStamp;
    private final String action;
    private final String actionPerson;
    private final Hand dealerHand;
    private final Hand playerHand;
    private final Player player;
    private final String dealerHandString;
    private final String playerHandString;
    public Round(Integer timeStamp, String action, String dealerHandString, String playerHandString, Player player) {
        this.timeStamp = timeStamp;
        this.action = action.split(" ")[1];
        this.actionPerson = action.split(" ")[0];
        this.dealerHand = new Hand(dealerHandString);
        this.playerHand = new Hand(playerHandString);
        this.dealerHandString = dealerHandString;
        this.playerHandString = playerHandString;
        this.player = player;
    }

    public boolean processRound() {
        if (checkForSameCards()) {
            return false;
        }
        if (this.actionPerson.equals("P")) {
            switch (this.action) {
                case "Joined" -> {
                    return !playerHand.getCardsInHand().get(0).getUnknown()
                            && !playerHand.getCardsInHand().get(1).getUnknown()
                            && !dealerHand.getCardsInHand().get(0).getUnknown()
                            && dealerHand.getCardsInHand().get(1).getUnknown();
                }
                case "Hit" -> {
                    return playerHand.getValue() < 20;
                }
                case "Stand" -> {
                    return playerHand.getValue() <= 21;
                }
                case "Win" -> {
                    return (playerHand.getValue() <= 21 && dealerHand.getValue() <= 21
                            && dealerHand.getValue() <= playerHand.getValue() || playerHand.getValue() <= 21
                            && dealerHand.getValue() > 21) && dealerHand.getValue() >= 17;
                }
                case "Left" -> {
                    return playerHand.getCardsInHand().get(0).getUnknown()
                            && dealerHand.getCardsInHand().get(0).getUnknown();
                }
                case "Lose" -> {
                    return (playerHand.getValue() <= 21 && dealerHand.getValue() <= 21
                            && dealerHand.getValue() > playerHand.getValue() || !(playerHand.getValue() <= 21
                            && dealerHand.getValue() > 21)) && dealerHand.getValue() >= 17;
                }
            }
        } else {
            switch (this.action) {
                case "Show" -> {
                    return playerHand.getValue() <= 21;
                }
                case "Hit" -> {
                    return dealerHand.getValue() < 17 && playerHand.getValue() <= 21;
                }
                case "Win" -> {
                    return (dealerHand.getValue() <= 21 && playerHand.getValue() < 21
                            && dealerHand.getValue() > playerHand.getValue() || dealerHand.getValue() <= 21
                            && playerHand.getValue() > 21) && dealerHand.getValue() >= 17;
                }
                case "Redeal" -> {
                    return !playerHand.getCardsInHand().get(0).getUnknown()
                            && !playerHand.getCardsInHand().get(1).getUnknown()
                            && !dealerHand.getCardsInHand().get(0).getUnknown()
                            && dealerHand.getCardsInHand().get(1).getUnknown();
                }
            }
        }
        return true;
    }

    public Integer getTimeStamp() {
        return timeStamp;
    }

    public Player getPlayer() {
        return player;
    }

    public String getAction() {
        return action;
    }

    public String getDealerHand() {
        return dealerHandString;
    }

    public String getPlayerHand() {
        return playerHandString;
    }

    public String getActionPerson() {
        return actionPerson;
    }

    public boolean checkForSameCards() {
        for (Card playerCard : playerHand.getCardsInHand()) {
            for (Card dealerCard : dealerHand.getCardsInHand()) {
                if (dealerCard.getNumber() == null || playerCard.getNumber() == null) {
                    continue;
                }
                if (dealerCard.getNumber().equalsIgnoreCase(playerCard.getNumber())
                        && dealerCard.getSuit() == playerCard.getSuit()) {
                    return true;
                }
            }
        }
        for (Card playerCard : playerHand.getCardsInHand()) {
            for (Card playerCard2 : dealerHand.getCardsInHand()) {
                if (playerCard2 == null || playerCard.getNumber() == null || playerCard == playerCard2) {
                    continue;
                }
                if (playerCard2.getNumber().equalsIgnoreCase(playerCard.getNumber())
                        && playerCard2.getSuit() == playerCard.getSuit()) {
                    return true;
                }
            }
        }
        return false;
    }
}
