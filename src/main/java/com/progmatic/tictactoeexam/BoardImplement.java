package com.progmatic.tictactoeexam;

import com.progmatic.tictactoeexam.enums.PlayerType;
import com.progmatic.tictactoeexam.exceptions.CellException;
import com.progmatic.tictactoeexam.interfaces.Board;
import java.util.ArrayList;
import java.util.List;

public class BoardImplement implements Board {

    private final Cell[][] board;
    private static final int ROW_COUNT = 3;
    private static final int COL_COUNT = 3;

    public BoardImplement() {
        this.board = new Cell[COL_COUNT][ROW_COUNT];
        for (int i = 0; i < COL_COUNT; i++) {
            for (int j = 0; j < ROW_COUNT; j++) {
                this.board[i][j] = new Cell(j, i);
            }
        }
    }

    @Override
    public PlayerType getCell(int rowIdx, int colIdx) throws CellException {
        if (((rowIdx >= this.board.length) || (rowIdx < 0))
                || ((colIdx >= this.board.length) || (colIdx < 0))) {
            throw new CellException(rowIdx, colIdx, "This cell is not on the board");
        }
        return this.board[colIdx][rowIdx].getCellsPlayer();
    }

    @Override
    public void put(Cell cell) throws CellException {
        int cellRow = cell.getRow();
        int cellCol = cell.getCol();
        if (getCell(cellRow, cellCol) != PlayerType.EMPTY) {
            throw new CellException(cellRow, cellCol, "This cell is not empty");
        } else {
            this.board[cellCol][cellRow].setCellsPlayer(cell.getCellsPlayer());
        }

    }

    @Override
    public boolean hasWon(PlayerType p) {

//        throw new UnsupportedOperationException("Not supported yet.");
        if (p == PlayerType.EMPTY) {
            return false;
        }

        int counter = 0;

        //vertical
        for (int i = 0; i < COL_COUNT; i++) {
            if (this.board[i][0].getCellsPlayer() == this.board[i][1].getCellsPlayer()
                    && this.board[i][1].getCellsPlayer() == this.board[i][2].getCellsPlayer()
                    && this.board[i][0].getCellsPlayer() == p) {
                return true;
            }
        }

        //horizontal
        for (int i = 0; i < this.ROW_COUNT; i++) {
            if (this.board[0][i].getCellsPlayer() == this.board[1][i].getCellsPlayer()
                    && this.board[1][i].getCellsPlayer() == this.board[2][i].getCellsPlayer()
                    && this.board[0][i].getCellsPlayer() == p) {
                return true;
            }
        }

        //diagonal A & B
        if (board[0][0].getCellsPlayer() == board[1][1].getCellsPlayer() &&
            board[1][1].getCellsPlayer() == board[2][2].getCellsPlayer() &&
            board[1][1].getCellsPlayer() == p) {
            return true;
        }
        
        if (board[0][2].getCellsPlayer() == board[1][1].getCellsPlayer() &&
            board[1][1].getCellsPlayer() == board[2][0].getCellsPlayer() &&
            board[1][1].getCellsPlayer() == p) {
            return true;
        }
        return false;
    }

    @Override
    public List<Cell> emptyCells() {
        List<Cell> t = new ArrayList<>();
        for (int i = 0; i < COL_COUNT; i++) {
            for (int j = 0; j < ROW_COUNT; j++) {
                if (board[i][j].getCellsPlayer() == PlayerType.EMPTY) {
                    t.add(new Cell(j, i));
                }
            }
        }
        return t;
    }

}
