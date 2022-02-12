package snakegame.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;
import snakegame.Direction;
import snakegame.domain.Apple;
import snakegame.domain.Snake;
import snakegame.gui.Updatable;

public class SnakeGame extends Timer implements ActionListener {

    private int width;
    private int height;
    private boolean continues;
    private Updatable updatable;

    private Snake snake;
    private Apple apple;
    public SnakeGame(int width, int height) {
        super(1000, null);

        this.width = width;
        this.height = height;
        this.continues = true;

        addActionListener(this);
        setInitialDelay(2000);

        this.snake = new Snake(this.width/2, this.height/2, Direction.DOWN);
        //Doing this so that the initial coordinate of Apple isn't same as Snake
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
        this.snake.move();
            if (this.snake.runsInto(this.apple)) {
                this.snake.grow();

                //New apple location
                Random random = new Random();
                do {
                    int xApple = random.nextInt(width);
                    int yApple = random.nextInt(height);
                    this.apple = new Apple(xApple, yApple);
                } while (this.snake.runsInto(this.apple));
            } else if (this.snake.runsIntoItself())
                this.continues = false;
            else { //Border condition
                int snakeHeadX = this.snake.getPieces().get(this.getSnake().getLength()-1).getX();
                int snakeHeadY = this.snake.getPieces().get(this.getSnake().getLength()-1).getY();
                if(snakeHeadX<0 || snakeHeadY<0 || snakeHeadX>=width || snakeHeadY>=height)
                    this.continues = false;
            }

            this.updatable.update();
            this.setDelay(1000 / this.snake.getLength());
            /*if (!continues) {
                return;
            }*/
    }

    public Snake getSnake() {
        return this.snake;
    }
    public void setSnake(Snake snake) {
        this.snake = snake;
    }
    public Apple getApple() {
        return this.apple;
    }
    public void setApple(Apple apple) {
        this.apple = apple;
    }

}
