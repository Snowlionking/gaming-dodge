package controlls;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MouseInput extends MouseAdapter {

    private Logger logger = LoggerFactory.getLogger(MouseInput.class);

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        logger.info("X: {} - Y: {}", x, y);
    }

}
