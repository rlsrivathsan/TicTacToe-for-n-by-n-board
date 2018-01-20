package com.tictactoe.fun.game1;

/**
 * 
 * Quick hackety hack game for 5-in-a-row game. 
 * Describes one cell location on the board.
 * @author Antti
 */
public class ArenaPosition {
    public final int row;
    public final int column;
    /** Creates a new instance of ArenaPosition */
    public ArenaPosition(int x, int y) {
        this.row=x;
        this.column=y;
    }
    /**
     * @return Returns the column.
     */
    public int getColumn() {
        return this.column;
    }
    /**
     * @return Returns the row.
     */
    public int getRow() {
        return this.row;
    }
    
    
}
