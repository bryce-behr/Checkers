import java.util.ArrayList;

public class Board {
    private Square[][] positions = new Square[8][8];

    /**
     * this is the board constructor. It creates a board in the starting position
     **/
    public Board() {
        int[][] teams = new int[][]{{0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 1, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {-1, 0, -1, 0, -1, 0, -1, 0},
                {0, -1, 0, -1, 0, -1, 0, -1},
                {-1, 0, -1, 0, -1, 0, -1, 0}};

        for (int y=0; y<8; y++){
            for (int x=0; x<8; x++){
                positions[y][x] = new Square(teams[y][x], x, y);
            }
        }
    }

    /**
     * this method checks to make sure that a move is valid
     * @param oldSquare the square that you're moving from
     * @param newSquare the square that you're going to
     * @return boolean true if valid move, false if invalid
     **/
    public boolean checkValid(Square oldSquare, Square newSquare) {
        return true;
    }

    /**
     * This method still needs code!!!
     * this method returns a new Board with a moved piece
     * @param oldSquare the old square you want to move from
     * @param newSquare the new square you want to move to
     * @return the new board which is the current state of the game
     */
    public Board makeMove(Square oldSquare, Square newSquare) {
        newSquare.setTeam(oldSquare.getTeam());
        return null;
    }

    /**
     * This method still needs code!!
     * @return true if this board is a winning board and false if not
     */
    public boolean win() {
        int redCount = 0;
        int blackCount = 0;

        for (int y=0; y<8; y++){
            for (int x=0; x<8; x++){
                if (positions[y][x].getTeam() > 0){
                    redCount += 1;
                }
                if (positions[y][x].getTeam() < 0){
                    blackCount += 1;
                }
            }
        }
        if ((redCount == 0)||(blackCount == 0)){
            return true;
        }

        /**
         * TODO: check for all possible moves
         */

        return false;
    }

    public ArrayList<ArrayList<Integer>> getPossibleMoves(Square sqr) {
        ArrayList<ArrayList<Integer>> moves = new ArrayList<ArrayList<Integer>>();

        //no piece, no moves
        if(sqr.getTeam() == 0) return moves;
        //man, move forward
        else if(Math.abs(sqr.getTeam()) == 1) {
            //black, move up
            if(sqr.getTeam() < 0) {

            }
            //red, move down
            else {

            }
        }
        //king, move forward or backward
        else if(Math.abs(sqr.getTeam()) == 2) {
            //black, move up
            if (sqr.getTeam() < 0) {

            }
            //red, move down
            else {

            }
        }

        return moves;
    }

    /**
     * this function returns the square at specific coordinates
     * @param r, the row
     * @param c, the colums
     * @return the square at the coordinates
     */
    public Square getSquare(int r, int c) {
        return positions[r][c];
    }
}
