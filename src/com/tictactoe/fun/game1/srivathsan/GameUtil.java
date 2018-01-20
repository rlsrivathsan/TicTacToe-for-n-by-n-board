package com.tictactoe.fun.game1.srivathsan;

import java.util.ArrayList;
import java.util.List;

import com.tictactoe.fun.game1.ArenaPosition;
import com.tictactoe.fun.game1.Piece;

public class GameUtil {
	
	/**
	 * Do not create instances of this class
	 */
	private GameUtil() {}
	
	public static  List<ArenaPosition> getAvailableFreeSpots(Piece[][] board) {
		List<ArenaPosition> availableFreeSpots = new ArrayList<ArenaPosition>();

		 for(int row = 0; row < board.length; row++)
		 {
		  for(int col = 0; col < board.length; col++) 
		  {
		   if (board[row][col] == null)
		   {
		    availableFreeSpots.add(new ArenaPosition(row,col));
		   }
		  }
		 }
		return availableFreeSpots;
	}
	
	 // This code is copied from ArenaCanvas.java
    public static boolean checkForWinnerFiveInRow(Piece[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != null) {
                    if (checkForWinnerFiveInRow(i, j, board)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    // This code is copied from ArenaCanvas.java
    private static boolean checkForWinnerFiveInRow(int i, int j, Piece[][] board) 
    {
     Piece p = board[i][j];
    		 
        // horiz check
        try {
            if (board[i][j - 2] == p && board[i][j - 1] == p && board[i][j + 1] == p && board[i][j + 2] == p) {
                
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException iy) { /* ignore */
        }

        // vert check
        try {
            if (board[i - 2][j] == p && board[i - 1][j] == p && board[i + 1][j] == p && board[i + 2][j] == p) {
               
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException iy) { /* ignore */
        }

        // diag lefttop-rightbottom
        try {
            if (board[i - 2][j - 2] == p && board[i - 1][j - 1] == p && board[i + 1][j + 1] == p
                    && board[i + 2][j + 2] == p) {
               
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException iy) { /* ignore */
        }

        // diag righttop-leftbottom
        try {
            if (board[i - 2][j + 2] == p && board[i - 1][j + 1] == p && board[i + 1][j - 1] == p
                    && board[i + 2][j - 2] == p) {
               
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException iy) { /* ignore */
        }

        return false;
    }

    

}
