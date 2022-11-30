import java.util.ArrayList;

public class Board {
    private Square[][] positions = new Square[8][8];

    /**
     * this is the board constructor. It creates a board in the starting position
     **/
    public Board() {
        int[][] teams = {{ 0,  1,  0,  1,  0,  1,  0,  1},
                         { 1,  0,  1,  0,  1,  0,  1,  0},
                         { 0,  1,  0,  1,  0,  1,  0,  1},
                         { 0,  0,  0,  0,  0,  0,  0,  0},
                         { 0,  0,  0,  0,  0,  0,  0,  0},
                         {-1,  0, -1,  0, -1,  0, -1,  0},
                         { 0, -1,  0, -1,  0, -1,  0, -1},
                         {-1,  0, -1,  0, -1,  0, -1,  0}};

        for (int r=0; r<8; r++){
            for (int c=0; c<8; c++){
                positions[r][c] = new Square(teams[r][c], r, c);
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
        /**
         * TODO: everything
         */

        return true;
    }

    /**
     * this method returns a new Board with a moved piece
     * @param oldSquare the old square you want to move from
     * @param newSquare the new square you want to move to
     * @return the new board which is the current state of the game
     */
    public Board makeMove(Square oldSquare, Square newSquare) {
        /**
         * TODO: check that move is valid
         */

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


    public Square getUpLeftMove(Square sqr) {
        //make sure square is not on boarders
        if(sqr.getC() == 0) return null;
        if(sqr.getR() == 0) return null;

        Square nextSquare = getSquare(sqr.getC() - 1, sqr.getR() - 1);

        //if next square is open, return nextSquare
        if(nextSquare.getTeam() == 0) return nextSquare;

        //if next square is occupied by the same team, return null
        if(getColor(sqr) == getColor(nextSquare)) {
            return null;
        }

        /*at this point the up left one space is:
            - on the board
            - an enemy piece
         */

        return sqr;
    }

    public ArrayList<Square> getPossibleMoves(Square sqr, int team) {
        ArrayList<Square> moves = new ArrayList<>();

        return moves;
    }

    public ArrayList<Square> getBlackManMoves(Square sqr){
        ArrayList<Square> moves = new ArrayList<>();

        int r = sqr.getR();
        int c = sqr.getC();

        boolean topLimit = false;
        boolean leftLimit = false;
        boolean rightLimit = false;
        if(r == 0) topLimit = true;
        if(c == 0) leftLimit = true;
        if(c == 7) rightLimit = true;

        if(topLimit) return moves;

        //if on left limit
        if(leftLimit) {
            //check right move
            Square nextSquare = getSquare(r-1, c+1);
            int nextColor = getColor(nextSquare);
            //if up right is empty
            if(nextColor == 0) {
                moves.add(nextSquare);
            }
            //if up right is teammate
            else if(nextSquare.getTeam() == -1) {
                return moves;
            }
            //if up right is enemy
            else {
                //make sure he's not in the second from top row and jump spot is empty
                if (r != 1 && getSquare(r - 2, c + 2).getTeam() == 0) {
                    moves.addAll(getBlackManMoves(getSquare(r - 2, c + 2)));
                }
                return moves;
            }
        }
        else if(rightLimit) {
            //check left moves

        }
        else
        {
            //check both left and right
        }

        return moves;
    }

    /**
     * this method returns a number indicating the color of the piece on a square
     * @param sqr, the square in question
     * @return -1 = black, 1 = red
     */
    private int getColor(Square sqr) {
        int temp = sqr.getTeam();
        if(temp < 0) return -1;
        else if(temp > 0) return 1;
        else return 0;
    }


    /**
     * this function returns the square at specific coordinates
     * @param r, the row
     * @param c, the column
     * @return the square at the coordinates
     */
    public Square getSquare(int r, int c) {
        return positions[r][c];
    }
}
