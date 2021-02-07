package game.window;

import javax.swing.JButton;
import javax.swing.JPanel;

import controlls.KeyInput;
import controlls.MouseInput;
import entities.Handler;
import game.GameState;
import game.GameVariables;
import services.listeners.MenuButtonListener;

public class Menu {

    public void render(Window window, Handler handler) {
        if (!GameVariables.isWindowCleared()) {

            window.getFrame().getContentPane().removeAll();
            window.getFrame().requestFocus();
            window.getFrame().addKeyListener(new KeyInput(handler));
            window.getFrame().addMouseListener(new MouseInput());
            GameVariables.setWindowCleared(true);

            JPanel panel = new JPanel();

            JButton playButton = new JButton("PLAY");
            playButton.addActionListener(new MenuButtonListener(GameState.PLAYING));
            panel.add(playButton);

            JButton settingsButton = new JButton("SETTINGS");
            settingsButton.addActionListener(new MenuButtonListener(GameState.SETTINGS));
            panel.add(settingsButton);

            JButton higscoreButton = new JButton("HIGSCORES");
            higscoreButton.addActionListener(new MenuButtonListener(GameState.HIGHSCORES));
            panel.add(higscoreButton);

            JButton exitButton = new JButton("EXIT");
            exitButton.addActionListener(exit -> System.exit(1));
            panel.add(exitButton);

            window.getFrame().add(panel);
            window.getFrame().revalidate();
        }
    }
}
