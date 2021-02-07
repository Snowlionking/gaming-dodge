package services.listeners;

import javax.sound.sampled.FloatControl;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import game.GameVariables;

public class MusicSliderListener implements ChangeListener {

    @Override
    public void stateChanged(ChangeEvent changeEvent) {
        JSlider slider = (JSlider) changeEvent.getSource();
        FloatControl volume = (FloatControl) GameVariables.getClip().getControl(FloatControl.Type.MASTER_GAIN);
        volume.setValue(slider.getValue());
        GameVariables.setMusicVolume((long) volume.getValue());
    }

}
