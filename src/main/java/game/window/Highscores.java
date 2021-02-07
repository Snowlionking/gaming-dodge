package game.window;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import controlls.MouseInput;
import entities.Handler;
import game.GameState;
import game.GameVariables;
import services.HighscoreService;
import services.listeners.MenuButtonListener;

public class Highscores {

    private HighscoreService highscoreService = new HighscoreService();

    public void render(Window window, Handler handler) {

        if (!GameVariables.isWindowCleared()) {

            List<Integer> highscores = highscoreService.loadSortedHighscores();
            DefaultListModel<Integer> model = new DefaultListModel<>();
            model.addAll(highscores);

            window.getFrame().getContentPane().removeAll();
            window.getFrame().requestFocus();
            window.getFrame().addMouseListener(new MouseInput());
            GameVariables.setWindowCleared(true);

            JPanel panel = new JPanel();

            JButton backButton = new JButton("BACK");
            backButton.addActionListener(new MenuButtonListener(GameState.MENU));
            panel.add(backButton);

            JLabel highscorelabel = new JLabel("HIGHSCORES");
            panel.add(highscorelabel);

            JList<Integer> highscoreList = new JList<>(model);
            highscoreList.setVisibleRowCount(20);
            panel.add(highscoreList);

            window.getFrame().add(panel);
            window.getFrame().revalidate();
        }
    }
}
