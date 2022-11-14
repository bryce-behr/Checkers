import java.util.Stack;

public class Game {
    /**
     * Member Variables
     */
    private Stack<Board> boards;

    /**
     * Default Constructor
     */
    public Game(){
        boards = new Stack<>();
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
    public Stack<Board> back(){
        return null;
    }

    /**
     * Method
     * Current board that should be displayed
     * @return the top board on the boards stack
     */
    public Board currentBoard(){
        return null;
    }

    /**
     * Method
     * makes a move and updates boards member variable
     * @param oldSquare the old square you want to move from
     * @param newSquare the new square you want to move to
     */
    public void makeMove(Square oldSquare, Square newSquare){
        Board newBoard = boards.peek().makeMove(oldSquare, newSquare);
        boards.push(newBoard);
    }
}
