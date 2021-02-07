package game.window;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controlls.KeyInput;
import controlls.MouseInput;
import entities.Handler;
import game.GameState;
import game.GameVariables;
import services.listeners.MenuButtonListener;

public class GameOver {

    public void render(Window window, Handler handler) {
        if (!GameVariables.isWindowCleared()) {

            window.getFrame().getContentPane().removeAll();
            window.getFrame().requestFocus();
            window.getFrame().addKeyListener(new KeyInput(handler));
            window.getFrame().addMouseListener(new MouseInput());
            GameVariables.setWindowCleared(true);

            JPanel panel = new JPanel();

            JButton menuButton = new JButton("MENU");
            menuButton.addActionListener(new MenuButtonListener(GameState.MENU));

            JLabel dieLabel = new JLabel("YOU DIED!");
            JLabel scoreLabel = new JLabel(Integer.toString(GameVariables.getScore()));
            GameVariables.setScore(0);

            panel.add(menuButton);
            panel.add(dieLabel);
            panel.add(scoreLabel);

            window.getFrame().add(panel);
            window.getFrame().revalidate();
        }
    }
}
