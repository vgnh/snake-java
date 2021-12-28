package wormgame.gui;

import wormgame.domain.Piece;
import wormgame.game.WormGame;

import javax.swing.*;
import java.awt.*;

public class DrawingBoard extends JPanel implements Updatable {
    private WormGame wormGame;
    private int pieceLength;

    public DrawingBoard(WormGame wormGame, int pieceLength) {
        super.setBackground(Color.GRAY);

        this.wormGame = wormGame;
        this.wormGame.setUpdatable(this);

        this.pieceLength = pieceLength;
    }

    @Override
    public void update() {
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        graphics.setColor(Color.BLACK);
        //Drawing worm
        for(Piece p: this.wormGame.getWorm().getPieces()) {
            graphics.fill3DRect(p.getX()*this.pieceLength, p.getY()*this.pieceLength, this.pieceLength, this.pieceLength, true);
        }

        graphics.setColor(Color.RED);
        //Drawing apple
        Piece apple = this.wormGame.getApple();
        graphics.fillOval(apple.getX()*this.pieceLength, apple.getY()*this.pieceLength, this.pieceLength, this.pieceLength);
    }
}
