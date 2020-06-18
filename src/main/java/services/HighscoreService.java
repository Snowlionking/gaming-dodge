package services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import game.Game;

public class HighscoreService {
	
	public void safeHighscore(String score) {
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("safeFile.txt", true));
			bufferedWriter.write(score);
			bufferedWriter.newLine();
			bufferedWriter.close();
			Game.gameModel.setHighscoreSet(true);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	public List<Integer> loadHighscores() {
		List<Integer> highscores = new ArrayList<>();
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader("safeFile.txt"));
			String line = null;
			while((line = bufferedReader.readLine()) != null) {
				highscores.add(Integer.parseInt(line));
			}
			bufferedReader.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return highscores;
	}

}
