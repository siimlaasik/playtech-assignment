package gameLogic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GameSession {
    private final Integer number;
    private final List<Round> roundList = new ArrayList<>();
    public GameSession(Integer number) {
        this.number = number;
    }
    public void addRound(Round round) {
        roundList.add(round);
        roundList.sort(Comparator.comparing(Round::getTimeStamp));
    }

    public String findFaultyMoves() {
        for (Round round : roundList) {
            if (!round.processRound()) {
                return round.getTimeStamp() + "," + number + "," + round.getPlayer().getId() + ","
                        + round.getActionPerson() + " " + round.getAction() + "," + round.getDealerHand() + ","
                        + round.getPlayerHand() + "\n";
            }
        }
        return null;
    }

    public Integer getNumber() {
        return number;
    }
}
