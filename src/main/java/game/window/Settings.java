package game.window;

import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import controlls.KeyInput;
import controlls.MouseInput;
import entities.Handler;
import game.GameState;
import game.GameVariables;
import services.listeners.MenuButtonListener;
import services.listeners.MusicSliderListener;
import services.listeners.SoundSliderListener;

public class Settings {

    private static final int MIN_SOUND_VALUE = -80;
    private static final int MAX_SOUND_VALUE = 0;

    public void render(Window window, Handler handler) {

        if (!GameVariables.isWindowCleared()) {
            window.getFrame().getContentPane().removeAll();
            window.getFrame().requestFocus();
            window.getFrame().addKeyListener(new KeyInput(handler));
            window.getFrame().addMouseListener(new MouseInput());
            GameVariables.setWindowCleared(true);

            JPanel panel = new JPanel();

            JButton backButton = new JButton("Back");
            backButton.addActionListener(new MenuButtonListener(GameState.MENU));

            JLabel musicLabel = new JLabel("MUSIC VOLUME");
            JSlider musicSlider = new JSlider(MIN_SOUND_VALUE, MAX_SOUND_VALUE, (int) GameVariables.getMusicVolume());
            musicSlider.addChangeListener(new MusicSliderListener());
            musicSlider.setMajorTickSpacing(10);
            musicSlider.setPaintTicks(true);
            musicSlider.setPaintLabels(true);

            Hashtable<Integer, JLabel> musicHashtable = new Hashtable<>();
            musicHashtable.put(-80, new JLabel("0"));
            musicHashtable.put(-60, new JLabel("25"));
            musicHashtable.put(-40, new JLabel("50"));
            musicHashtable.put(-20, new JLabel("75"));
            musicHashtable.put(0, new JLabel("100"));

            musicSlider.setLabelTable(musicHashtable);

            JLabel soundLabel = new JLabel("SOUND VOLUME");
            JSlider soundSlider = new JSlider(MIN_SOUND_VALUE, MAX_SOUND_VALUE, (int) GameVariables.getSoundVolume());
            soundSlider.addChangeListener(new SoundSliderListener());
            soundSlider.setMajorTickSpacing(10);
            soundSlider.setPaintTicks(true);
            soundSlider.setPaintLabels(true);

            Hashtable<Integer, JLabel> soundHashtable = new Hashtable<>();
            soundHashtable.put(-80, new JLabel("0"));
            soundHashtable.put(-60, new JLabel("25"));
            soundHashtable.put(-40, new JLabel("50"));
            soundHashtable.put(-20, new JLabel("75"));
            soundHashtable.put(0, new JLabel("100"));

            soundSlider.setLabelTable(soundHashtable);

            panel.add(backButton);
            panel.add(musicLabel);
            panel.add(musicSlider);
            panel.add(soundLabel);
            panel.add(soundSlider);
            window.getFrame().add(panel);
            window.getFrame().revalidate();
        }
    }
}
