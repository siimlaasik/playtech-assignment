import gameLogic.GameSession;
import gameLogic.Player;
import gameLogic.Round;

import java.io.*;
import java.util.*;

public class Game {
    static Map<Integer, GameSession> gameSessionMap = new HashMap<>();
    static Round round;
    private static void readGame(String fileName) throws IOException {
        File gameData = new File("resources/" + fileName);
        BufferedReader br = new BufferedReader(new FileReader(gameData));
        String data;
        while ((data = br.readLine()) != null) {
            try {
                round = new Round(Integer.valueOf(data.split(",")[0]), data.split(",")[3],
                        data.split(",")[4], data.split(",")[5],
                        new Player(Integer.valueOf(data.split(",")[2])));
            } catch(Exception e) {
                    continue;
            }
            if (!gameSessionMap.containsKey(Integer.valueOf(data.split(",")[1]))) {
                GameSession gameSession = new GameSession(Integer.valueOf(data.split(",")[1]));
                gameSession.addRound(round);
                gameSessionMap.put(Integer.valueOf(data.split(",")[1]), gameSession);
            } else {
                gameSessionMap.get(Integer.valueOf(data.split(",")[1])).addRound(round);
            }
        }
    }
    private static void writeFile() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("analyzer_results.txt"));
        ArrayList<GameSession> valueList = new ArrayList<>(gameSessionMap.values());
        valueList.sort(Comparator.comparing(GameSession::getNumber));
        for (GameSession gameSession : valueList) {
            if (!(gameSession.findFaultyMoves() == null)) {
                String string = gameSession.findFaultyMoves();
                bw.write(string);
            }
        }
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        readGame("game_data.txt");
        writeFile();
    }


}