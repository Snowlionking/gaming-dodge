package services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import game.GameVariables;

public class HighscoreService {

    private Logger logger = Logger.getLogger(HighscoreService.class.getName());

    public void safeHighscore(String score) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("safeFile.txt", true))) {
            bufferedWriter.write(score);
            bufferedWriter.newLine();
            GameVariables.setHighscoreSet(true);
        } catch (Exception e) {
            logger.severe("Error: " + e.getMessage());
        }
    }

    public List<Integer> loadHighscores() {
        List<Integer> highscores = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("safeFile.txt"))) {
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                highscores.add(Integer.parseInt(line));
            }
        } catch (Exception e) {
            logger.severe("Error: " + e.getMessage());
        }
        return highscores;
    }

    public List<Integer> loadSortedHighscores() {
        List<Integer> highscores = loadHighscores();
        Collections.sort(highscores);
        Collections.reverse(highscores);
        return highscores.subList(0, Math.min(highscores.size(), 19));

    }

}
