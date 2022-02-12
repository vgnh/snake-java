package snakegame.gui;

import snakegame.domain.Piece;
import snakegame.game.SnakeGame;

import javax.swing.*;
import java.awt.*;

public class DrawingBoard extends JPanel implements Updatable {
    private SnakeGame snakeGame;
    private int pieceLength;

    public DrawingBoard(SnakeGame snakeGame, int pieceLength) {
        super.setBackground(Color.BLACK);

        this.snakeGame = snakeGame;
        this.snakeGame.setUpdatable(this);

        this.pieceLength = pieceLength;
    }

    @Override
    public void update() {
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        graphics.setColor(Color.WHITE);
        //Drawing snake
        for(Piece p: this.snakeGame.getSnake().getPieces()) {
            graphics.fill3DRect(p.getX()*this.pieceLength, p.getY()*this.pieceLength, this.pieceLength, this.pieceLength, true);
        }

        graphics.setColor(Color.ORANGE);
        //Drawing apple
        Piece apple = this.snakeGame.getApple();
        graphics.fillOval(apple.getX()*this.pieceLength, apple.getY()*this.pieceLength, this.pieceLength, this.pieceLength);
    }
}
