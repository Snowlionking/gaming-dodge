package services.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import game.GameState;
import game.GameVariables;

public class MenuButtonListener implements ActionListener {

    private GameState gameState;

    public MenuButtonListener(GameState gameState) {
        this.setGameState(gameState);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GameVariables.setState(gameState);
        GameVariables.setWindowCleared(false);
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

}
