package snakegame.gui;

import snakegame.Direction;
import snakegame.domain.Snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {
    private Snake snake;

    public KeyboardListener(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.snake.setDirection(Direction.RIGHT);
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.snake.setDirection(Direction.LEFT);
        }
        else if(e.getKeyCode() == KeyEvent.VK_UP) {
            this.snake.setDirection(Direction.UP);
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            this.snake.setDirection(Direction.DOWN);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
