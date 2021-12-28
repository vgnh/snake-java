package wormgame.domain;

public class Piece {
    private int x;
    private int y;

    public Piece(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean runsInto(Piece piece) {
        return this.x == piece.x && this.y == piece.y;
    }

    @Override
    public String toString() {
        return "("+this.x+","+this.y+")";
    }
}
