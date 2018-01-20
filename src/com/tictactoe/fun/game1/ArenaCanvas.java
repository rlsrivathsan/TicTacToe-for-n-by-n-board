/*
 * ArenaCanvas.java
 * 
 * Created on 25. toukokuuta 2006, 19:32
 * 
 * To change this template, choose Tools | Template Manager and open the template in the editor.
 */

package com.tictactoe.fun.game1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Quick hackety hack game for 5-in-a-row game. Game state and display defined
 * in this class.
 * 
 * @author Antti
 */
public class ArenaCanvas extends JPanel {
    private static final long serialVersionUID = 4742160670605646032L;

    JLabel status;
    Piece[][] board;
    Player one;
    Player two;
    Player turn;
    ArenaPosition last;
    Player winner;
    boolean tie;
    ArenaPosition winRowStart;
    ArenaPosition winRowEnd;

    /** Creates a new instance of ArenaCanvas */
    public ArenaCanvas(JLabel status, int size, Player one, Player two) {
        this.status = status;
        board = new Piece[size][size];
        this.one = one;
        this.two = two;
        setPreferredSize(new Dimension(600, 600));
        setOpaque(false);
        status.setText("Start game!");
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (winner == null && !tie) {
                    Dimension d = getSize();
                    Dimension square = new Dimension((d.width / board.length), (d.height / board.length));
                    int x = (e.getY() / square.height);
                    int y = (e.getX() / square.width);
                    if (x >= 0 && x < board.length && y >= 0 && y < board.length) {
                        if (board[x][y] == null) {
                            ArenaPosition ap = new ArenaPosition(x, y);
                            System.out.println("xpos:" + e.getX() + " ypos:" + e.getY());
                            System.out.println("x:" + ap.row + " y:" + ap.column);

                            implementMove(ap);
                        }
                    }
                }
            }

        });

    }

    public void paintComponent(Graphics g1) {
        Graphics2D g = (Graphics2D) g1;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Dimension d = super.getSize();
        // d.setSize(d.width - 1, d.height - 1);
        g.drawRect(0, 0, d.width - 1, d.height - 1);
        Dimension square = new Dimension((d.width / board.length), (d.height / board.length));
        /* draw vertical lines */
        for (int i = 1; i < board.length; i++) {
            int xpos = i * square.width;
            g.drawLine(xpos, 0, xpos, d.height);
        }
        /* draw horiz lines */
        for (int i = 1; i < board.length; i++) {
            int ypos = i * square.height;
            g.drawLine(0, ypos, d.width, ypos);
        }
        g.setStroke(new BasicStroke(square.height / 8));
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                int padding = 10;
                Piece p = board[i][j];
                int xpos = j * square.width;
                int ypos = i * square.height;
                if (p == Piece.CROSS) {
                    Color tmp = g.getColor();
                    g.setColor(Color.red);
                    g.drawLine(xpos + padding, ypos + padding, xpos + square.width - padding, ypos + square.height
                            - padding);
                    g.drawLine(xpos + square.width - padding, ypos + padding, xpos + padding, ypos + square.height
                            - padding);

                    g.setColor(tmp);
                }
                if (p == Piece.ROUND) {
                    Color tmp = g.getColor();
                    g.setColor(Color.blue);
                    g.drawOval(xpos + padding, ypos + padding, square.width - (2 * padding), square.height
                            - (2 * padding));
                    g.setColor(tmp);
                }
            }
        }
        if (winner != null && winRowEnd != null && winRowStart != null) {
            g.setColor(Color.YELLOW);
            g.drawLine(winRowStart.column * square.width + (square.width / 2), winRowStart.row * square.height
                    + (square.height / 2), winRowEnd.column * square.width + (square.width / 2), winRowEnd.row
                    * square.height + (square.height / 2));

        }

    }

    public void start() {
        /*int x = (int) (Math.random() * board.length);
        int y = (int) (Math.random() * board.length);*/
        this.last = new ArenaPosition(0, 0);
        if ((Math.random() * 10) < 5) {
            board[0][0] = Piece.CROSS;
            turn = two;
        } else {
            board[0][0] = Piece.ROUND;
            turn = one;
        }
    }

    public void move() {
        if (winner == null && !tie)
            doMove();
    }

    private void doMove() {
        ArenaPosition result = null;
        try {
            result = turn.move(board.clone(), this.last);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            badMove("" + turn.getName() + ": threw an exception.");
        }
        if (result != null) {
            if (result.row < 0 || result.row > board.length || result.column < 0 || result.column > board.length) {
                badMove("" + turn.getName() + ": moved out of the board.");
            } else {
                implementMove(result);
            }
        }

    }

    private void implementMove(ArenaPosition result) {
        System.out.println("Current Move: " + result.row + "," + result.column);
        if (board[result.row][result.column] != null) {
            badMove("" + turn.getName() + ": moved to a taken position.");
        } else {
            board[result.row][result.column] = turn.getSide();
        }
        this.last = result;
        checkForWinner();
        checkForTie();
        changeTurn();
        super.repaint();
    }

    private void changeTurn() {
        if (turn == one) {
            turn = two;
        } else {
            turn = one;
        }
    }

    private void badMove(String message) {
        super.repaint();
        try {
            wait(3000);
        } catch (Exception ignore) {
        }
        if (turn == one) {
            winner = two;
        } else {
            winner = one;
        }
        status.setText(message + " " + winner.getName() + " won!");
    }

    private void checkForWinner() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != null) {
                    if (checkForWinner(i, j)) {
                        winner = turn;
                        status.setText("Player " + winner.getName() + " won!");

                    }
                }
            }
        }
    }

    private boolean checkForWinner(int i, int j) {
        Piece p = board[i][j];
        // horiz check
        try {
            if (board[i][j - 2] == p && board[i][j - 1] == p && board[i][j + 1] == p && board[i][j + 2] == p) {
                winRowStart = new ArenaPosition(i, j - 2);
                winRowEnd = new ArenaPosition(i, j + 2);
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException iy) { /* ignore */
        }

        // vert check
        try {
            if (board[i - 2][j] == p && board[i - 1][j] == p && board[i + 1][j] == p && board[i + 2][j] == p) {
                winRowStart = new ArenaPosition(i - 2, j);
                winRowEnd = new ArenaPosition(i + 2, j);
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException iy) { /* ignore */
        }

        // diag lefttop-rightbottom
        try {
            if (board[i - 2][j - 2] == p && board[i - 1][j - 1] == p && board[i + 1][j + 1] == p
                    && board[i + 2][j + 2] == p) {
                winRowStart = new ArenaPosition(i - 2, j - 2);
                winRowEnd = new ArenaPosition(i + 2, j + 2);
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException iy) { /* ignore */
        }

        // diag righttop-leftbottom
        try {
            if (board[i - 2][j + 2] == p && board[i - 1][j + 1] == p && board[i + 1][j - 1] == p
                    && board[i + 2][j - 2] == p) {
                winRowStart = new ArenaPosition(i - 2, j + 2);
                winRowEnd = new ArenaPosition(i + 2, j - 2);
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException iy) { /* ignore */
        }

        return false;
    }

    private void checkForTie() {
        if (winner == null) {
            boolean emptySpotSeen = false;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (board[i][j] == null) {
                        emptySpotSeen = true;
                    }
                }
            }
            if (!emptySpotSeen) {
                tie = true;
                status.setText("It's a tie!");
            }
        }
    }

    public void autoplay() {
        while (winner == null && !tie) {
            move();
            paintComponent(super.getGraphics());
            // try { Thread.sleep(100); }catch (InterruptedException e) { }

        }
    }

}
