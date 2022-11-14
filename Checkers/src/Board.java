public class Board {
    private Square[][] positions = new Square[8][8];

    /**
     * this is the board constructor
     **/
    public Board() {

    }

    /**
     * this method moves a piece from one square to the next
     * @param oldSquare the square that you're moving from
     * @param newSquare the square that you're going to
     * @return boolean true if valid move, false if invalid
     **/
    public boolean move(Square oldSquare, Square newSquare) {
        return true;
    }

    /**
     * This method still needs code!!!
     * this method returns a new Board with a moved piece
     * @param oldSquare the old square you want to move from
     * @param newSquare the new square you want to move to
     * @return
     */
    public Board makeMove(Square oldSquare, Square newSquare) {
        return null;
    }

    /**
     * This method still needs code!!
     * @return true if this board is a winning board and false if not
     */
    public boolean win() {
        return false;
    }
}
