package snakegame.domain;

import snakegame.Direction;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<Piece> listOfPieces;

    private Direction direction;
    private int x;
    private int y;

    private boolean growCalled;
    public Snake(int originalX, int originalY, Direction originalDirection) {
        this.listOfPieces = new ArrayList<Piece>();
        this.x = originalX;
        this.y = originalY;
        this.direction = originalDirection;

        this.listOfPieces.add(new Piece(this.x, this.y));
        this.growCalled = false;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void setDirection(Direction dir) {
        this.direction = dir;
    }

    public int getLength() {
        return this.listOfPieces.size();
    }

    public List<Piece> getPieces() {
        return this.listOfPieces;
    }

    public void move() {
        //Moves the work one piece forward
        int xOfHead = this.listOfPieces.get(this.listOfPieces.size()-1).getX();
        int yOfHead = this.listOfPieces.get(this.listOfPieces.size()-1).getY();
        Piece newPiece;

        if(this.direction == Direction.RIGHT) {
            newPiece = new Piece(xOfHead+1, yOfHead);
        }
        else if(this.direction == Direction.LEFT) {
            newPiece = new Piece(xOfHead-1, yOfHead);
        }
        else if(this.direction == Direction.UP) {
            newPiece = new Piece(xOfHead, yOfHead-1);
        }
        else {
            newPiece = new Piece(xOfHead, yOfHead+1);
        }

        this.listOfPieces.add(newPiece);

        if(this.growCalled || this.listOfPieces.size()<=3) {
            //System.out.println(this.listOfPieces.size());
            this.growCalled = false;
        }
        else {
            this.listOfPieces.remove(0);
        }
    }

    public void grow() {
        //Grows the snake by one piece. The snake grows together with the following move method call; after the first move method call the snake does not grow any more.
        this.growCalled = true;
    }

    public boolean runsInto(Piece piece) {
        for(Piece p: this.listOfPieces) {
            if(p.runsInto(piece))
                return true;
        }
        return false;
    }

    public boolean runsIntoItself() {
        for(int i=0; i<this.listOfPieces.size()-1; i++) {
            for(int j=i+1; j<this.listOfPieces.size(); j++) {
                if(this.listOfPieces.get(i).runsInto(this.listOfPieces.get(j)))
                    return true;
            }
        }
        return false;
    }
}
