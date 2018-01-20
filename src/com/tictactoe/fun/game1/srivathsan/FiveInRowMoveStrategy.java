package com.tictactoe.fun.game1.srivathsan;

import java.util.List;

import com.tictactoe.fun.game1.ArenaPosition;
import com.tictactoe.fun.game1.Piece;

/**
 * An implementation of the MoveStrategy for a game of Five In A Row
 * 
 * @author Srivathsan
 *
 */
public class FiveInRowMoveStrategy implements MoveStrategy {
	
	ArenaPosition selfLastPosition = new ArenaPosition(0, 0);

	@Override
	public ArenaPosition move(Piece[][] board, ArenaPosition lastPosition, Piece myPiece)  {
	     // Get list of available free spaces
	     List<ArenaPosition> availableFreeSpots = GameUtil.getAvailableFreeSpots(board);

	     if(availableFreeSpots.size() == 0)
	     {
	      // There is no free space available, return
	      return null;
	     }
	     Piece opponentPiece = myPiece == Piece.ROUND ? Piece.CROSS : Piece.ROUND;
	     // Check if with one move I can win. 
	     ArenaPosition chosenCellWithOneMoveSelf = checkWithOneMove(board, myPiece,
				availableFreeSpots);
	     if (chosenCellWithOneMoveSelf != null) {
	    	 selfLastPosition = chosenCellWithOneMoveSelf;
	    	 return chosenCellWithOneMoveSelf;
	     }
	  // Now I can't win with one move. So let me check if the opponent can win with one move
	     ArenaPosition chosenCellWithOneMoveOpponent = checkWithOneMove(board,  opponentPiece,
					availableFreeSpots);
		     if (chosenCellWithOneMoveOpponent != null) {
		    	 selfLastPosition = chosenCellWithOneMoveOpponent;
		    	 return chosenCellWithOneMoveOpponent;
		     }
	     
	     //Check if I can win with two moves
	     ArenaPosition chosenCellWithTwoMovesSelf = checkWithTwoMoves(board, myPiece,  availableFreeSpots, selfLastPosition); 
	     if (chosenCellWithTwoMovesSelf != null) {
	    	 selfLastPosition = chosenCellWithTwoMovesSelf;
	    	 return chosenCellWithTwoMovesSelf;
	     }
	     
	  // Now I can't win with two moves. So let me check if the opponent can win with two moves
	     ArenaPosition chosenCellWithTwoMovesOpponent = checkWithTwoMoves(board,  opponentPiece,
					availableFreeSpots, lastPosition);
		     if (chosenCellWithTwoMovesOpponent != null) {
		    	 selfLastPosition = chosenCellWithTwoMovesOpponent; 
		    	 return chosenCellWithTwoMovesOpponent;
		     }
		     
		   /*//Check if I can win with three moves
		     ArenaPosition chosenCellWithThreeMovesSelf = checkWithThreeMoves(board, myPiece,  availableFreeSpots, selfLastPosition); 
		     if (chosenCellWithThreeMovesSelf != null)
		    	 return chosenCellWithThreeMovesSelf;
		     
		  // Now I can't win with three moves. So let me check if the opponent can win with three moves
		     ArenaPosition chosenCellWithThreeMovesOpponent = checkWithThreeMoves(board,  opponentPiece,
						availableFreeSpots, lastPosition);
			     if (chosenCellWithThreeMovesOpponent != null)
			    	 return chosenCellWithThreeMovesOpponent;*/
			     
	     // Both of us can't win with up to 2 moves. So let me place it in one of the free cells
	     ArenaPosition firstAvailableCell = availableFreeSpots.get(0);
	     selfLastPosition = firstAvailableCell;
	     return firstAvailableCell;
	     
	     
	   }
	

	/**
	 * Checks the winning position, if any, for the given Piece with up to 3 moves
	 * @param board - the current state of the board
	 * @param piece - my Piece or Opponent's Piece
	 * @param availableFreeSpots - currently open slots on the board
	 * @param lastPosition - last position of my piece or Opponent's piece
	 * @return the winning position, if there's one or null
	 */
	/*private ArenaPosition checkWithThreeMoves(Piece[][] board, Piece piece,
			List<ArenaPosition> availableFreeSpots, ArenaPosition lastPosition) {
		//Check if I / Opponent can win with three moves
	     for(ArenaPosition cell1 : availableFreeSpots)
	     {
	      // Set the value of cell 1 in board to given Piece 
	    	 if ((lastPosition.row-5 < cell1.row && cell1.row < lastPosition.row + 5) || (lastPosition.column - 5 < cell1.column && cell1.column < lastPosition.column + 5)) {
	      board[cell1.row][cell1.column] = piece;
	      
	      for(ArenaPosition cell2 : availableFreeSpots)
	         {
	       
	       if (cell1 != cell2)
	       {
	        // Set the value of cell 2 in board to given Piece 
	    	   if ((lastPosition.row - 5 < cell1.row && cell1.row < lastPosition.row + 5) || (lastPosition.column - 5 < cell1.column && cell1.column < lastPosition.column + 5)) {
	           board[cell2.row][cell2.column] = piece;
	           
	           for (ArenaPosition cell3 : availableFreeSpots) {
	            // Set the value of cell 2 in board to given Piece 
	               board[cell3.row][cell3.column] = piece;
	               
	               if (cell2 != cell3) {
	                // Now check if that would win
	            	   if (GameUtil.checkForWinnerFiveInRow(board))
	                   {
	                    //If so clear the values and return cell1
	                    board[cell1.row][cell1.column] = null;
	                    board[cell2.row][cell2.column] = null;
	                    board[cell3.row][cell3.column] = null;
	                    
	                             return cell1;
	                   }
	               }
	               board[cell3.row][cell3.column] = null;
	           }
	       }
	       
	       board[cell2.row][cell2.column] = null;
	       }
	         }
	      
	      board[cell1.row][cell1.column] = null;
	     }
	     
	     }
	     return null;
	}*/
	
	/**
	 * Checks the winning position, if any, for the given Piece with up to 2 moves
	 * @param board - the current state of the board
	 * @param piece - my Piece or Opponent's Piece
	 * @param availableFreeSpots - currently open slots on the board
	 * @param lastPosition - last position of my piece or Opponent's piece
	 * @return the winning position, if there's one or null
	 */
	private ArenaPosition checkWithTwoMoves(Piece[][] board, Piece piece,
			 List<ArenaPosition> availableFreeSpots, ArenaPosition lastPosition) {
		
		for(ArenaPosition cell1 : availableFreeSpots)
	     {
	      // Set the value of cell 1 in board to given Piece
			if ((lastPosition.row-5 < cell1.row && cell1.row < lastPosition.row + 5) || (lastPosition.column - 5 < cell1.column && cell1.column < lastPosition.column + 5)) {
	      board[cell1.row][cell1.column] = piece;
	      
	      for(ArenaPosition cell2 : availableFreeSpots)
	         {
	       
	       if (cell1 != cell2)
	       {
	    	   if ((lastPosition.row - 5 < cell2.row && cell2.row < lastPosition.row + 5) || (lastPosition.column - 5 < cell2.column && cell2.column < lastPosition.column + 5)) {
	        // Set the value of cell 2 in board to given Piece 
	           board[cell2.row][cell2.column] = piece;
	           
	           // Now check if that would win
	           if (GameUtil.checkForWinnerFiveInRow(board))
	           {
	            //If so clear the values and return cell2
	            board[cell1.row][cell1.column] = null;
	            board[cell2.row][cell2.column] = null;
	            
	                     return cell2;
	           }
	       }
	       
	       board[cell2.row][cell2.column] = null;
	       }
	         }
	      
	      board[cell1.row][cell1.column] = null;
	     }
	     
	     }
	     return null;
	    
	}

	/**
	 * Checks the winning position, if any, for the given Piece with a single move
	 * @param board - the current state of the board
	 * @param piece - my Piece or Opponent's Piece
	 * @param availableFreeSpots - currently open slots on the board
	 * @return the winning position, if there's one or null
	 */
	private ArenaPosition checkWithOneMove(Piece[][] board, Piece piece, 
			List<ArenaPosition> availableFreeSpots) {
		
		for(ArenaPosition cell : availableFreeSpots)
	     {
	      //I am temporarily placing my /opponent's coin in board instance to check for win.
	      board [cell.row] [cell.column] = piece;
	      if (GameUtil.checkForWinnerFiveInRow(board))
	      {
	       //Removing the coin after checking for win
	       board[cell.row] [cell.column] = null;
	       // If I make this move then I will win / opponent makes this move he wins, so I am making this move
	                return cell;
	      }
	      board[cell.row] [cell.column] = null;
	     }
	     
	    
		return null;
	}

}
