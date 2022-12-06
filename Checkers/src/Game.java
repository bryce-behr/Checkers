import java.util.Stack;

/**
 * This is the class that will contain the entire checkers game
 */
public class Game {
    /**
     * Member Variables
     */
    private Stack<Board> boards;
    private int count;
    private int turn;

    /**
     * Default Constructor
     */
    public Game(){
        boards = new Stack<>();
        boards.push(new Board());
        count = 0;
        turn = 0;
    }

    /**
     * Getter
     * the return value will oscilate between 0 and 1
     * 0 is black's turn and 1 is red's turn
     * @return the current turn
     */
    public int getTurn(){
        return turn;
    }

    public void incTurn(){
        count += 1;
        turn = count % 2;
    }

    /**
     * Getter
     * @return boards member variable
     */
    public Stack<Board> getBoards() {
        return boards;
    }

    /**
     * Method
     * Go back one move
     * @return The board that has been popped from the boards stack
     */
    public Board back(){
        count -= 1;
        turn = count % 2;
        return boards.pop();
    }

    /**
     * Method
     * Current board that should be displayed
     * @return the top board on the boards stack
     */
    public Board currentBoard(){
        return boards.peek();
    }

    /**
     * Method
     * makes a move and updates boards member variable
     * @param oldSquare the old square you want to move from
     * @param newSquare the new square you want to move to
     */
    public void makeMove(Square oldSquare, Square newSquare){
        Board newBoard = boards.peek().makeMove(oldSquare, newSquare);
        //if (currentBoard().getJumpMoves(oldSquare).contains(oldSquare)){
        if ((newBoard.getJumpMoves(newBoard.getSquare((newSquare.getR()), (newSquare.getC())))).size() > 0){
            count -= 1;
        }

        boards.push(newBoard);
        count += 1;
        turn = count % 2;
    }

    public void makeRandomMove(int color){
        boards.push(currentBoard().makeRandomMove(color));
        count += 1;
        turn = count % 2;
    }

    /**
     * Method
     * calls the win() method of the current board to check if the current board is a winning or not
     * @return returns the currentBoard.win() value
     */
    public boolean hasWon(){
        return currentBoard().win();
    }
}
