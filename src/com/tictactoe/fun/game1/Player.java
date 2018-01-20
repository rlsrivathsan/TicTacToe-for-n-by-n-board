package com.tictactoe.fun.game1;

/**
 * Interface for a computer player. Implement your algorithm with this interface. Add your algoritm
 * to a separate package. Your target is to get 5 pieces in a row.
 * 
 * To add your algorithm to the games dropdown menus add it to MainJFrame lines 29...33, look at the example algorithms already added there for advice.
 * 
 * @author Antti
 */
public interface Player extends Cloneable {
    /**
     * This method is called by the game framework to notify you of your side. Side (Round or
     * Cross) is randomly chosen by the game framework at the beginning of the match.
     * 
     * @param p Piece.ROUND or Piece.CROSS depending on your luck.
     */
    public void setSide(Piece p);

    /**
     * This should always return the side that you've been informed with by the setSide-method
     * 
     * @return Piece given with setSide-method
     */
    public Piece getSide();

    /**
     * Here is your main implementation point. This method is called to determine your next move.
     * The given parameters are informational and you can modify them if you want, your
     * modifications will be ignored. The result of your algorithm is announced with the return
     * value.
     * 
     * @param board Current board status (first dimension is the board's rows, and the second is the
     *        board's columns). A single cell in the board table can be either: Piece.CROSS,
     *        Piece.ROUND or null. null means that the cell isn't taken yet.
     * @param last A helper parameter to inform you about the last move made by your opponent.
     * @return ArenaPosition instance that describes on which board cell you want to place your piece.
     */
    public ArenaPosition move(Piece[][] board, ArenaPosition last);

    /**
     * @return your player's name
     */
    public String getName();

    /**
     * @return your player's name
     */
    public String toString();


}
