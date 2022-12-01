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


    public ArrayList<Square> getPossibleMoves(Square sqr) {
        ArrayList<Square> moves = new ArrayList<>();
        int team = sqr.getTeam();
        int color = getColor(sqr);

        if(team == 0) return moves;
        else if(Math.abs(team) == 2) {
            moves.addAll(getMoves(sqr, color, -1));
            moves.addAll(getMoves(sqr, color, 1));
        }
        else {
            moves.addAll(getMoves(sqr, color, color));
        }

        return moves;
    }

    public ArrayList<Square> getMoves(Square sqr, int color, int direction){
        ArrayList<Square> moves = new ArrayList<>();

        int r = sqr.getR();
        int c = sqr.getC();

        Square nextLeft = getSquare(r+direction, c-1);
        Square nextRight = getSquare(r+direction, c+1);

        if(nextLeft != null) {
            if(nextLeft.getTeam() == 0) {
                moves.add(nextLeft);
            }
            else if(getColor(nextLeft) == color * -1) {
                Square jumpSquare = getSquare(r+2*direction, c-2);
                if(jumpSquare != null && getColor(jumpSquare) == 0) {
                    moves.addAll(getMoves(jumpSquare, color, direction));
                }
            }
        }
        if(nextRight != null) {
            if(nextRight.getTeam() == 0) {
                moves.add(nextRight);
            }
            else if(getColor(nextRight) == color * -1) {
                Square jumpSquare = getSquare(r+2*direction, c+2);
                if(jumpSquare != null && getColor(jumpSquare) == 0) {
                    moves.addAll(getMoves(jumpSquare, color, direction));
                }
            }
        }
        return moves;
    }

    /**
     * this method returns a number indicating the color of the piece on a square
     * @param sqr, the square in question
     * @return -1 = black, 1 = red
     **/
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
        if(r < 0 || r > 7 || c < 0 || c > 7) return null;
        else return positions[r][c];
    }
}
