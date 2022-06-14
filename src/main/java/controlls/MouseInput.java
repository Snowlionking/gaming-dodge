package controlls;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MouseInput extends MouseAdapter {

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        log.info("X: {} - Y: {}", x, y);
    }

}
