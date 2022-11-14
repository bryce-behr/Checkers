import java.util.Stack;

public class Game {
    //Member Variables
    private Stack<Board> boards;

    //Default Constructor
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
     * Go back one move
     * @return The board that has been popped from the boards stack
     */
    public Stack<Board> back(){
        return null;
    }

    /**
     * Current board that should be displayed
     * @return the top board on the boards stack
     */
    public Board currentBoard(){
        return null;
    }
}
