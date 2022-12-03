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
        ArrayList<Square> possibleMoves = getPossibleMoves(oldSquare);

        System.out.print("Possible Moves of (" + oldSquare.getR() + ", " + oldSquare.getC() + "): ");
        for(Square sqr: possibleMoves) {
            System.out.print("(r" + sqr.getR() + ", c" + sqr.getC() + "), ");
        }
        System.out.println();

        if(possibleMoves.contains(newSquare))
            return true;
        else return false;
    }

    /**
     * this method returns a deep copy of the new Board with the moved piece
     * @param oldSquare the old square you want to move from
     * @param newSquare the new square you want to move to
     * @return if valid move, the new board which is the current state of the game. if not valid, null
     */
    public Board makeMove(Square oldSquare, Square newSquare) {

        //perform deep copy of new board
        Board newBoard = new Board();
        for(int r = 0; r < 8; r++) {
            for(int c = 0; c < 8; c++) {
                Square tempOld = getSquare(r, c);
                Square tempNew = newBoard.getSquare(r, c);
                tempNew.setTeam(tempOld.getTeam());
                tempNew.setC(tempOld.getC());
                tempNew.setR(tempOld.getR());
            }
        }

        if(checkValid(oldSquare, newSquare)) {
            if(Math.abs(newSquare.getR() - oldSquare.getR()) > 1) {
                newBoard.getSquare((oldSquare.getR() + newSquare.getR()) / 2,(oldSquare.getC() + newSquare.getC()) / 2).setTeam(0);
            }

            if((newSquare.getR() == 0 || newSquare.getR() == 7) && Math.abs(oldSquare.getTeam()) == 1) {
                newBoard.getSquare(newSquare.getR(), newSquare.getC()).setTeam(oldSquare.getTeam() * 2);
            }
            else {
                newBoard.getSquare(newSquare.getR(), newSquare.getC()).setTeam(oldSquare.getTeam());
            }
            newBoard.getSquare(oldSquare.getR(), oldSquare.getC()).setTeam(0);
        }
        return newBoard;
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


    /**
     * this method return all the possible moves of a given square
     * @param sqr the square in question
     * @returnm an arraylist of all the possible square that the original square could move to
     */
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

    /**
     * this method returns all the possible moves of a certain square
     * @param sqr the current sqr that we're trying to get possible moves of
     * @param color the color of the original square (-1 for black, 0 for none, 1 for red)
     * @param direction check moves either upward and downward (-1 for up, 1 for down)
     * @return the list of all the possible moves in the given direction from the original square
     */
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
                    moves.add(jumpSquare);
                    //moves.addAll(getMoves(jumpSquare, color, direction));
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
                    moves.add(jumpSquare);
                    //moves.addAll(getMoves(jumpSquare, color, direction));
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
     * @return the square at the coordinates, null if invalid coordinates
     */
    public Square getSquare(int r, int c) {
        if(r < 0 || r > 7 || c < 0 || c > 7) return null;
        else return positions[r][c];
    }

    /**
     * this method prints out the current board including lines and r and c labels
     * @return a String of the current board
     */
    public String toString() {
        StringBuilder theBoard = new StringBuilder();
        theBoard.append("    0   1   2   3   4   5   6   7\n");
        for(int r = 0; r < 17; r++) {
            if(r%2 != 0) {
                theBoard.append((r-1)/2 + " ");
            }
            else {
                theBoard.append("  ");
            }

            for(int c = 0; c < 17; c++) {
                if(r % 2 == 0) {
                    if(r == 0 || r == 16) {
                        if(c == 16) theBoard.append("-");
                        else theBoard.append("--");
                    }
                    else if(c % 2 == 0 ) {
                        theBoard.append("|");
                    }
                    else {
                        theBoard.append("---");
                    }
                }
                else if(c % 2 == 0) {
                    theBoard.append("|");
                }
                else {
                    //theBoard += " ";
                    int temp = getSquare((r-1)/2, (c-1)/2).getTeam();
                    if(temp < 0) theBoard.append(temp).append(" ");
                    else theBoard.append(" ").append(temp).append(" ");
                }
            }

            if(r%2 != 0) {
                theBoard.append(" " + (r-1)/2);
            }

            theBoard.append("\n");
        }
        theBoard.append("    0   1   2   3   4   5   6   7\n");
        return theBoard.toString();
    }
}
