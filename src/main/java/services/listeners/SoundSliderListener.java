package services.listeners;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import game.GameVariables;
import game.music.Music;

public class SoundSliderListener implements ChangeListener {

    private static final String HURT_SOUND = "hurt.wav";

    private Music music = new Music();

    @Override
    public void stateChanged(ChangeEvent changeEvent) {
        JSlider slider = (JSlider) changeEvent.getSource();

        if (!slider.getValueIsAdjusting()) {
            GameVariables.setSoundVolume(slider.getValue());
            music.playSound(HURT_SOUND);
        }
    }
}
