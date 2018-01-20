package com.tictactoe.fun.game1.srivathsan;

import com.tictactoe.fun.game1.ArenaPosition;
import com.tictactoe.fun.game1.Piece;
import com.tictactoe.fun.game1.Player;

/**
 * 
 * @author Srivathsan
 */
public class MyPlayer implements Player {
    private Piece myPiece;
    private String name;
    // Allows to swap the strategy implementation
    private MoveStrategy moveStrategy;


    /** Creates a new instance of MyPlayer */
    public MyPlayer(String name) {
        this.name = name;
        // Can use the strategy setter to change the strategy
        moveStrategy = new FiveInRowMoveStrategy();
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
    
    public ArenaPosition move(Piece[][] board, ArenaPosition lastPosition) 
    {
    	return moveStrategy.move(board, lastPosition, myPiece);
    
    }

    public String toString() {
        return getName();
    }

	public MoveStrategy getMoveStrategy() {
		return moveStrategy;
	}

	public void setMoveStrategy(MoveStrategy moveStrategy) {
		this.moveStrategy = moveStrategy;
	}

}
