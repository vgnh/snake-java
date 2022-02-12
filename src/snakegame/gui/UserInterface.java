package snakegame.gui;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import snakegame.game.SnakeGame;

public class UserInterface implements Runnable {

    private JFrame frame;
    private SnakeGame game;
    private int sideLength;

    private DrawingBoard drawingBoard;
    public UserInterface(SnakeGame game, int sideLength) {
        this.game = game;
        this.sideLength = sideLength;
        this.drawingBoard = null;
    }

    @Override
    public void run() {
        frame = new JFrame("Snake 1.0");
        int width = (game.getWidth() + 1) * sideLength + 10;
        int height = (game.getHeight() + 2) * sideLength + 10;

        frame.setPreferredSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    public void createComponents(Container container) {
        // Create drawing board first which then is added into container-object.
        // After this, create keyboard listener which is added into frame-object
        this.drawingBoard = new DrawingBoard(this.game, this.sideLength);

        container.add(this.drawingBoard);

        frame.addKeyListener(new KeyboardListener(this.game.getSnake()));
    }

    public Updatable getUpdatable() {
        return this.drawingBoard;
    }

    public JFrame getFrame() {
        return frame;
    }
}
