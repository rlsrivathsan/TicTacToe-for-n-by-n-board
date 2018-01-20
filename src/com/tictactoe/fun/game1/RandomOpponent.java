package com.tictactoe.fun.game1;

/**
 * 
 * Quick hackety hack game for 5-in-a-row game. Here's an example Player
 * implementation which relies on good fortune.
 * 
 * @author Antti
 */
public class RandomOpponent implements Player, Cloneable {
    Piece myPiece;
    String name;

    /** Creates a new instance of RandomOpponent */
    public RandomOpponent(String name) {
        this.name = name;
    }

    public void setSide(Piece p) {
        myPiece = p;
    }

    public Piece getSide() {
        return myPiece;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public ArenaPosition move(Piece[][] board, ArenaPosition lastPosition) {
        if (Math.random() * 1000 <= 3) {
            return new ArenaPosition(-23235, 0);
        }
        while (true) {
	        int x = (int) (Math.random() * board.length);
	        int y = (int) (Math.random() * board.length);
	        if (board[x][y] == null) {
	            return new ArenaPosition(x, y);
	        }
        }
    }

    public String toString() {
        return getName();
    }

}
