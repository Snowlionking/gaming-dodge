package game.window;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Menu {

    private boolean test = true;

    public void render(Window window) {
        if (test) {
            JPanel panel = new JPanel();
            panel.add(new JButton("CLICK HERE!"));
            window.getFrame().add(panel);
            window.getFrame().revalidate();
            window.getFrame().repaint();
            test = false;
        }
    }

}
