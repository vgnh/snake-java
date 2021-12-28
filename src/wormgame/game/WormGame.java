package wormgame.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;
import wormgame.Direction;
import wormgame.domain.Apple;
import wormgame.domain.Worm;
import wormgame.gui.Updatable;

public class WormGame extends Timer implements ActionListener {

    private int width;
    private int height;
    private boolean continues;
    private Updatable updatable;

    private Worm worm;
    private Apple apple;
    public WormGame(int width, int height) {
        super(1000, null);

        this.width = width;
        this.height = height;
        this.continues = true;

        addActionListener(this);
        setInitialDelay(2000);

        this.worm = new Worm(this.width/2, this.height/2, Direction.DOWN);
        //Doing this so that the initial coordinate of Apple isn't same as Worm
        int xApple = 0;
        int yApple = 0;
        Random random = new Random();
        do {
            xApple = random.nextInt(width);
            yApple = random.nextInt(height);
        }while(xApple == this.width/2 && yApple == this.height/2);

        this.apple = new Apple(xApple, yApple);
    }


    public boolean continues() {
        return continues;
    }

    public void setUpdatable(Updatable updatable) {
        this.updatable = updatable;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        this.worm.move();
            if (this.worm.runsInto(this.apple)) {
                this.worm.grow();

                //New apple location
                Random random = new Random();
                do {
                    int xApple = random.nextInt(width);
                    int yApple = random.nextInt(height);
                    this.apple = new Apple(xApple, yApple);
                } while (this.worm.runsInto(this.apple));
            } else if (this.worm.runsIntoItself())
                this.continues = false;
            else { //Border condition
                int wormHeadX = this.worm.getPieces().get(this.getWorm().getLength()-1).getX();
                int wormHeadY = this.worm.getPieces().get(this.getWorm().getLength()-1).getY();
                if(wormHeadX<0 || wormHeadY<0 || wormHeadX>=width || wormHeadY>=height)
                    this.continues = false;
            }

            this.updatable.update();
            this.setDelay(1000 / this.worm.getLength());
            /*if (!continues) {
                return;
            }*/
    }

    public Worm getWorm() {
        return this.worm;
    }
    public void setWorm(Worm worm) {
        this.worm = worm;
    }
    public Apple getApple() {
        return this.apple;
    }
    public void setApple(Apple apple) {
        this.apple = apple;
    }

}
