import java.util.ArrayList;

public class Board {
    private final Square[][] positions = new Square[8][8];

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

        /*System.out.print("Possible Moves of (c" + oldSquare.getC() + ", r" + oldSquare.getR() + "): ");
        for(Square sqr: possibleMoves) {
            System.out.print("(c" + sqr.getC() + ", r" + sqr.getR() + "), ");
        }
        System.out.println(); */

        return possibleMoves.contains(newSquare);
    }

    /**
     * this method returns a deep copy of the new Board with the moved piece if it's a valid move and a
     * deep copy of the board is it's not a valid move
     * @param oldSquare the old square you want to move from
     * @param newSquare the new square you want to move to
     * @return if valid move, the new board which is the current state of the game. if not valid, deep copy of changed board
     */
    public Board makeMove(Square oldSquare, Square newSquare) {

        //deep copy of new board
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

        //move the piece if valid
        if(checkValid(oldSquare, newSquare)) {
            //if a piece got jumped set it to 0
            if(Math.abs(newSquare.getR() - oldSquare.getR()) > 1) {
                newBoard.getSquare((oldSquare.getR() + newSquare.getR()) / 2,(oldSquare.getC() + newSquare.getC()) / 2).setTeam(0);
            }

            //if a man reaches the end of the board make it a king otherwise just move the man
            if((newSquare.getR() == 0 || newSquare.getR() == 7) && Math.abs(oldSquare.getTeam()) == 1) {
                newBoard.getSquare(newSquare.getR(), newSquare.getC()).setTeam(oldSquare.getTeam() * 2);
            }
            else {
                newBoard.getSquare(newSquare.getR(), newSquare.getC()).setTeam(oldSquare.getTeam());
            }
            newBoard.getSquare(oldSquare.getR(), oldSquare.getC()).setTeam(0);
        }

        System.out.println(newBoard.toString());
        return newBoard;
    }

    /**
     * this method makes the team of the given color do a completely random move
     * @param color the color to move (-1 for black, 1 for red)
     * @return a new board with the random move
     */
    public Board makeRandomMove(int color) {
        ArrayList<Square> piecesWithMoves = new ArrayList<>();
        for(int r = 0; r < 8; r++) {
            for(int c = 0; c < 8; c++) {
                if(getColor(getSquare(r, c)) == color && getPossibleMoves(getSquare(r, c)).size() != 0) {
                    piecesWithMoves.add(getSquare(r, c));
                }
            }
        }

        int randPiece = (int)(Math.random() * piecesWithMoves.size());
        Square oldSquare = piecesWithMoves.get(randPiece);
        ArrayList<Square> possibleMoves = getPossibleMoves(oldSquare);
        int randMove = (int)(Math.random() * possibleMoves.size());
        Square newSquare = possibleMoves.get(randMove);

        return makeMove(oldSquare, newSquare);
    }

    /**
     * This method tells us if a team has won
     * @return true if this board is a winning board and false if not
     */
    public boolean win() {
        int redCount = 0;
        int blackCount = 0;

        ArrayList<Square> redPossibleMoves = new ArrayList<>();
        ArrayList<Square> blackPossibleMoves = new ArrayList<>();

        for (int r=0; r<8; r++){
            for (int c=0; c<8; c++){
                if (positions[r][c].getTeam() > 0){
                    redCount += 1;
                    redPossibleMoves.addAll(getPossibleMoves(getSquare(r, c)));
                }
                if (positions[r][c].getTeam() < 0){
                    blackCount += 1;
                    blackPossibleMoves.addAll(getPossibleMoves(getSquare(r, c)));
                }
            }
        }

        return redCount == 0 || blackCount == 0 || redPossibleMoves.size() == 0 || blackPossibleMoves.size() == 0;
    }


    /**
     * this method returns all the possible moves of a given square
     * @param sqr the square in question
     * @return an arraylist of all the possible squares that the original square could move to
     **/
    public ArrayList<Square> getPossibleMoves(Square sqr) {
        ArrayList<Square> moves = new ArrayList<>();
        int team = sqr.getTeam();
        int color = getColor(sqr);

        //if it's an empty square there's no possible moves
        if(team == 0) return moves;
        //if it's a king allow upward and downward move
        else if(Math.abs(team) == 2) {
            moves.addAll(getMoves(sqr, color, -1));
            moves.addAll(getMoves(sqr, color, 1));
        }
        //if it's a man allow moves in the appropriate direction
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

        //if there is a next left see if it's a square you can move to or jump over
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

        //if there is a next right see if it's a square you can move to or jump over
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
     * @return -1 = black, 1 = red, 0 if none
     **/
    private int getColor(Square sqr) {
        return Integer.compare(sqr.getTeam(), 0);
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
    @Override
    public String toString() {
        StringBuilder theBoard = new StringBuilder();
        theBoard.append("    a   b   c   d   e   f   g   h\n");
        for(int r = 0; r < 17; r++) {
            if(r%2 != 0) {
                theBoard.append(Math.abs(((r - 1) / 2) - 7) + 1).append(" ");
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
                    int temp = getSquare((r-1)/2, (c-1)/2).getTeam();

                    if(temp < 0)
                    {
                        if(temp == -1) theBoard.append(" x").append(" ");
                        else if(temp == -2) theBoard.append(" X").append(" ");
                    }
                    else if (temp == 0) theBoard.append(" ").append(" ").append(" ");
                    else {
                        if(temp == 1) theBoard.append(" ").append("o").append(" ");
                        else if(temp == 2) theBoard.append(" ").append("O").append(" ");
                    }

                }
            }

            if(r%2 != 0) {
                theBoard.append(" ").append(Math.abs(((r - 1) / 2) - 7) + 1);
            }

            theBoard.append("\n");
        }
        theBoard.append("    a   b   c   d   e   f   g   h\n");
        return theBoard.toString();
    }

    /**
     * this method gets all possible moves of a square that are jumps
     * @param sqr1 the original square in question
     * @return an arraylist of square that the original piece could jump to
     **/
    public ArrayList<Square> getJumpMoves(Square sqr1) {
        int r = sqr1.getR();
        ArrayList<Square> jumps = getPossibleMoves(sqr1);
        ArrayList<Square> doubleJumps = new ArrayList<>();

        for(Square sqr2 : jumps) {
            if(Math.abs(r - sqr2.getR()) == 2) {
                doubleJumps.add(sqr2);
            }
        }

        return doubleJumps;
    }
}
