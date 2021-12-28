package wormgame.gui;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import wormgame.game.WormGame;

public class UserInterface implements Runnable {

    private JFrame frame;
    private WormGame game;
    private int sideLength;

    private DrawingBoard drawingBoard;
    public UserInterface(WormGame game, int sideLength) {
        this.game = game;
        this.sideLength = sideLength;
        this.drawingBoard = null;
    }

    @Override
    public void run() {
        frame = new JFrame("Worm Game");
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

        frame.addKeyListener(new KeyboardListener(this.game.getWorm()));
    }

    public Updatable getUpdatable() {
        return this.drawingBoard;
    }

    public JFrame getFrame() {
        return frame;
    }
}
