/**
 * 
 */
package com.tictactoe.fun.game1.srivathsan;

import com.tictactoe.fun.game1.ArenaPosition;
import com.tictactoe.fun.game1.Piece;

/**
 * @author Srivathsan
 *
 */
public interface MoveStrategy {
	ArenaPosition move(Piece[][] board, ArenaPosition lastPosition, Piece myPiece) ;
}